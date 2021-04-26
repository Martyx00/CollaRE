package com.zmxv.RNSound;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.webrtc.MediaStreamTrack;

public class RNSoundModule extends ReactContextBaseJavaModule implements AudioManager.OnAudioFocusChangeListener {
    static final Object NULL = null;
    String category;
    ReactApplicationContext context;
    Double focusedPlayerKey;
    Boolean mixWithOthers = true;
    Map<Double, MediaPlayer> playerPool = new HashMap();
    Boolean wasPlayingBeforeFocusChange = false;

    @ReactMethod
    public void enable(Boolean bool) {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSound";
    }

    public RNSoundModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext;
        this.category = null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setOnPlay(boolean z, Double d2) {
        ReactApplicationContext reactApplicationContext = this.context;
        WritableMap createMap = Arguments.createMap();
        createMap.putBoolean("isPlaying", z);
        createMap.putDouble("playerKey", d2.doubleValue());
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onPlayChange", createMap);
    }

    @ReactMethod
    public void prepare(String str, Double d2, ReadableMap readableMap, final Callback callback) {
        MediaPlayer createMediaPlayer = createMediaPlayer(str);
        char c2 = 65535;
        if (createMediaPlayer == null) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("code", -1);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, "resource not found");
            callback.invoke(createMap, NULL);
            return;
        }
        this.playerPool.put(d2, createMediaPlayer);
        String str2 = this.category;
        if (str2 != null) {
            Integer num = null;
            switch (str2.hashCode()) {
                case -1803461041:
                    if (str2.equals("System")) {
                        c2 = 2;
                        break;
                    }
                    break;
                case 2547280:
                    if (str2.equals("Ring")) {
                        c2 = 4;
                        break;
                    }
                    break;
                case 82833682:
                    if (str2.equals("Voice")) {
                        c2 = 3;
                        break;
                    }
                    break;
                case 772508280:
                    if (str2.equals("Ambient")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 1943812667:
                    if (str2.equals("Playback")) {
                        c2 = 0;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                    num = 3;
                    break;
                case 1:
                    num = 5;
                    break;
                case 2:
                    num = 1;
                    break;
                case 3:
                    num = 0;
                    break;
                case 4:
                    num = 2;
                    break;
                default:
                    Log.e("RNSoundModule", String.format("Unrecognised category %s", this.category));
                    break;
            }
            if (num != null) {
                createMediaPlayer.setAudioStreamType(num.intValue());
            }
        }
        createMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            /* class com.zmxv.RNSound.RNSoundModule.AnonymousClass1 */

            /* renamed from: a  reason: collision with root package name */
            boolean f5670a = false;

            public synchronized void onPrepared(MediaPlayer mediaPlayer) {
                if (!this.f5670a) {
                    this.f5670a = true;
                    WritableMap createMap = Arguments.createMap();
                    createMap.putDouble("duration", ((double) mediaPlayer.getDuration()) * 0.001d);
                    try {
                        callback.invoke(RNSoundModule.NULL, createMap);
                    } catch (RuntimeException e) {
                        Log.e("RNSoundModule", "Exception", e);
                    }
                }
            }
        });
        createMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            /* class com.zmxv.RNSound.RNSoundModule.AnonymousClass2 */

            /* renamed from: a  reason: collision with root package name */
            boolean f5673a = false;

            public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (this.f5673a) {
                    return true;
                }
                this.f5673a = true;
                try {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("what", i);
                    createMap.putInt("extra", i2);
                    callback.invoke(createMap, RNSoundModule.NULL);
                } catch (RuntimeException e) {
                    Log.e("RNSoundModule", "Exception", e);
                }
                return true;
            }
        });
        try {
            if (!readableMap.hasKey("loadSync") || !readableMap.getBoolean("loadSync")) {
                createMediaPlayer.prepareAsync();
            } else {
                createMediaPlayer.prepare();
            }
        } catch (Exception e) {
            Log.e("RNSoundModule", "Exception", e);
        }
    }

    /* access modifiers changed from: protected */
    public MediaPlayer createMediaPlayer(String str) {
        int identifier = this.context.getResources().getIdentifier(str, "raw", this.context.getPackageName());
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (identifier != 0) {
            try {
                AssetFileDescriptor openRawResourceFd = this.context.getResources().openRawResourceFd(identifier);
                mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                openRawResourceFd.close();
                return mediaPlayer;
            } catch (IOException e) {
                Log.e("RNSoundModule", "Exception", e);
                return null;
            }
        } else if (str.startsWith("http://") || str.startsWith("https://")) {
            mediaPlayer.setAudioStreamType(3);
            Log.i("RNSoundModule", str);
            try {
                mediaPlayer.setDataSource(str);
                return mediaPlayer;
            } catch (IOException e2) {
                Log.e("RNSoundModule", "Exception", e2);
                return null;
            }
        } else if (str.startsWith("asset:/")) {
            try {
                AssetFileDescriptor openFd = this.context.getAssets().openFd(str.replace("asset:/", ""));
                mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                openFd.close();
                return mediaPlayer;
            } catch (IOException e3) {
                Log.e("RNSoundModule", "Exception", e3);
                return null;
            }
        } else if (!new File(str).exists()) {
            return null;
        } else {
            mediaPlayer.setAudioStreamType(3);
            Log.i("RNSoundModule", str);
            try {
                mediaPlayer.setDataSource(str);
                return mediaPlayer;
            } catch (IOException e4) {
                Log.e("RNSoundModule", "Exception", e4);
                return null;
            }
        }
    }

    @ReactMethod
    public void play(final Double d2, final Callback callback) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer == null) {
            setOnPlay(false, d2);
            if (callback != null) {
                callback.invoke(false);
            }
        } else if (!mediaPlayer.isPlaying()) {
            if (!this.mixWithOthers.booleanValue()) {
                ((AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).requestAudioFocus(this, 3, 1);
                this.focusedPlayerKey = d2;
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /* class com.zmxv.RNSound.RNSoundModule.AnonymousClass3 */

                /* renamed from: a  reason: collision with root package name */
                boolean f5676a = false;

                public synchronized void onCompletion(MediaPlayer mediaPlayer) {
                    if (!mediaPlayer.isLooping()) {
                        RNSoundModule.this.setOnPlay(false, d2);
                        if (!this.f5676a) {
                            this.f5676a = true;
                            try {
                                callback.invoke(true);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                /* class com.zmxv.RNSound.RNSoundModule.AnonymousClass4 */

                /* renamed from: a  reason: collision with root package name */
                boolean f5680a = false;

                public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    RNSoundModule.this.setOnPlay(false, d2);
                    if (this.f5680a) {
                        return true;
                    }
                    this.f5680a = true;
                    try {
                        callback.invoke(true);
                    } catch (Exception unused) {
                    }
                    return true;
                }
            });
            mediaPlayer.start();
            setOnPlay(true, d2);
        }
    }

    @ReactMethod
    public void pause(Double d2, Callback callback) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void stop(Double d2, Callback callback) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
        if (!this.mixWithOthers.booleanValue() && d2 == this.focusedPlayerKey) {
            ((AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).abandonAudioFocus(this);
        }
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public void reset(Double d2) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    @ReactMethod
    public void release(Double d2) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            this.playerPool.remove(d2);
            if (!this.mixWithOthers.booleanValue() && d2 == this.focusedPlayerKey) {
                ((AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND)).abandonAudioFocus(this);
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        Iterator<Map.Entry<Double, MediaPlayer>> it = this.playerPool.entrySet().iterator();
        while (it.hasNext()) {
            MediaPlayer value = it.next().getValue();
            if (value != null) {
                value.reset();
                value.release();
            }
            it.remove();
        }
    }

    @ReactMethod
    public void setVolume(Double d2, Float f, Float f2) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f.floatValue(), f2.floatValue());
        }
    }

    @ReactMethod
    public void getSystemVolume(Callback callback) {
        try {
            AudioManager audioManager = (AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            callback.invoke(NULL, Float.valueOf(((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3))));
        } catch (Exception e) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("code", -1);
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, e.getMessage());
            callback.invoke(createMap);
        }
    }

    @ReactMethod
    public void setSystemVolume(Float f) {
        AudioManager audioManager = (AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
        audioManager.setStreamVolume(3, Math.round(((float) audioManager.getStreamMaxVolume(3)) * f.floatValue()), 0);
    }

    @ReactMethod
    public void setLooping(Double d2, Boolean bool) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setSpeed(Double d2, Float f) {
        if (Build.VERSION.SDK_INT < 23) {
            Log.w("RNSoundModule", "setSpeed ignored due to sdk limit");
            return;
        }
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(f.floatValue()));
        }
    }

    @ReactMethod
    public void setCurrentTime(Double d2, Float f) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(Math.round(f.floatValue() * 1000.0f));
        }
    }

    @ReactMethod
    public void getCurrentTime(Double d2, Callback callback) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer == null) {
            callback.invoke(-1, false);
            return;
        }
        callback.invoke(Double.valueOf(((double) mediaPlayer.getCurrentPosition()) * 0.001d), Boolean.valueOf(mediaPlayer.isPlaying()));
    }

    @ReactMethod
    public void setSpeakerphoneOn(Double d2, Boolean bool) {
        MediaPlayer mediaPlayer = this.playerPool.get(d2);
        if (mediaPlayer != null) {
            mediaPlayer.setAudioStreamType(3);
            AudioManager audioManager = (AudioManager) this.context.getSystemService(MediaStreamTrack.AUDIO_TRACK_KIND);
            if (bool.booleanValue()) {
                audioManager.setMode(3);
            } else {
                audioManager.setMode(0);
            }
            audioManager.setSpeakerphoneOn(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setCategory(String str, Boolean bool) {
        this.category = str;
        this.mixWithOthers = bool;
    }

    public void onAudioFocusChange(int i) {
        MediaPlayer mediaPlayer;
        if (!this.mixWithOthers.booleanValue() && (mediaPlayer = this.playerPool.get(this.focusedPlayerKey)) != null) {
            if (i <= 0) {
                this.wasPlayingBeforeFocusChange = Boolean.valueOf(mediaPlayer.isPlaying());
                if (this.wasPlayingBeforeFocusChange.booleanValue()) {
                    pause(this.focusedPlayerKey, null);
                }
            } else if (this.wasPlayingBeforeFocusChange.booleanValue()) {
                play(this.focusedPlayerKey, null);
                this.wasPlayingBeforeFocusChange = false;
            }
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("IsAndroid", true);
        return hashMap;
    }
}
