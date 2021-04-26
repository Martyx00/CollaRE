package com.oney.WebRTCModule;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.Camera1Enumerator;
import org.webrtc.Camera2Enumerator;
import org.webrtc.CameraEnumerator;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaSource;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

/* access modifiers changed from: package-private */
/* compiled from: GetUserMediaImpl */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4708a = WebRTCModule.TAG;

    /* renamed from: b  reason: collision with root package name */
    private final CameraEnumerator f4709b;

    /* renamed from: c  reason: collision with root package name */
    private final ReactApplicationContext f4710c;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, a> f4711d = new HashMap();
    private final WebRTCModule e;

    d(WebRTCModule webRTCModule, ReactApplicationContext reactApplicationContext) {
        this.e = webRTCModule;
        this.f4710c = reactApplicationContext;
        if (Camera2Enumerator.isSupported(reactApplicationContext)) {
            Log.d(f4708a, "Creating video capturer using Camera2 API.");
            this.f4709b = new Camera2Enumerator(reactApplicationContext);
            return;
        }
        Log.d(f4708a, "Creating video capturer using Camera1 API.");
        this.f4709b = new Camera1Enumerator(false);
    }

    private AudioTrack a(ReadableMap readableMap) {
        MediaConstraints parseMediaConstraints = this.e.parseMediaConstraints(readableMap.getMap(MediaStreamTrack.AUDIO_TRACK_KIND));
        String str = f4708a;
        Log.d(str, "getUserMedia(audio): " + parseMediaConstraints);
        String uuid = UUID.randomUUID().toString();
        PeerConnectionFactory peerConnectionFactory = this.e.mFactory;
        AudioSource createAudioSource = peerConnectionFactory.createAudioSource(parseMediaConstraints);
        AudioTrack createAudioTrack = peerConnectionFactory.createAudioTrack(uuid, createAudioSource);
        this.f4711d.put(uuid, new a(createAudioTrack, createAudioSource, null));
        return createAudioTrack;
    }

    private VideoTrack b(ReadableMap readableMap) {
        ReadableMap map = readableMap.getMap(MediaStreamTrack.VIDEO_TRACK_KIND);
        String str = f4708a;
        Log.d(str, "getUserMedia(video): " + map);
        h hVar = new h(this.f4709b, map);
        VideoCapturer b2 = hVar.b();
        if (b2 == null) {
            return null;
        }
        PeerConnectionFactory peerConnectionFactory = this.e.mFactory;
        VideoSource createVideoSource = peerConnectionFactory.createVideoSource(b2);
        String uuid = UUID.randomUUID().toString();
        VideoTrack createVideoTrack = peerConnectionFactory.createVideoTrack(uuid, createVideoSource);
        createVideoTrack.setEnabled(true);
        hVar.c();
        this.f4711d.put(uuid, new a(createVideoTrack, createVideoSource, hVar));
        return createVideoTrack;
    }

    /* access modifiers changed from: package-private */
    public ReadableArray a() {
        WritableArray createArray = Arguments.createArray();
        String[] deviceNames = this.f4709b.getDeviceNames();
        for (int i = 0; i < deviceNames.length; i++) {
            WritableMap createMap = Arguments.createMap();
            if (this.f4709b.isFrontFacing(deviceNames[i])) {
                createMap.putString("facing", "front");
            } else {
                createMap.putString("facing", "back");
            }
            createMap.putString("deviceId", "" + i);
            createMap.putString("groupId", "");
            createMap.putString("label", deviceNames[i]);
            createMap.putString("kind", "videoinput");
            createArray.pushMap(createMap);
        }
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putString("deviceId", "audio-1");
        createMap2.putString("groupId", "");
        createMap2.putString("label", "Audio");
        createMap2.putString("kind", "audioinput");
        createArray.pushMap(createMap2);
        return createArray;
    }

    /* access modifiers changed from: package-private */
    public MediaStreamTrack a(String str) {
        a aVar = this.f4711d.get(str);
        if (aVar == null) {
            return null;
        }
        return aVar.f4713b;
    }

    /* access modifiers changed from: package-private */
    public void a(ReadableMap readableMap, Callback callback, Callback callback2) {
        VideoTrack videoTrack = null;
        AudioTrack a2 = readableMap.hasKey(MediaStreamTrack.AUDIO_TRACK_KIND) ? a(readableMap) : null;
        if (readableMap.hasKey(MediaStreamTrack.VIDEO_TRACK_KIND)) {
            videoTrack = b(readableMap);
        }
        if (a2 == null && videoTrack == null) {
            callback2.invoke("DOMException", "AbortError");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        MediaStream createLocalMediaStream = this.e.mFactory.createLocalMediaStream(uuid);
        WritableArray createArray = Arguments.createArray();
        MediaStreamTrack[] mediaStreamTrackArr = {a2, videoTrack};
        for (MediaStreamTrack mediaStreamTrack : mediaStreamTrackArr) {
            if (mediaStreamTrack != null) {
                if (mediaStreamTrack instanceof AudioTrack) {
                    createLocalMediaStream.addTrack((AudioTrack) mediaStreamTrack);
                } else {
                    createLocalMediaStream.addTrack((VideoTrack) mediaStreamTrack);
                }
                WritableMap createMap = Arguments.createMap();
                String id = mediaStreamTrack.id();
                createMap.putBoolean("enabled", mediaStreamTrack.enabled());
                createMap.putString("id", id);
                createMap.putString("kind", mediaStreamTrack.kind());
                createMap.putString("label", id);
                createMap.putString("readyState", mediaStreamTrack.state().toString());
                createMap.putBoolean("remote", false);
                createArray.pushMap(createMap);
            }
        }
        Log.d(f4708a, "MediaStream id: " + uuid);
        this.e.localStreams.put(uuid, createLocalMediaStream);
        callback.invoke(uuid, createArray);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, boolean z) {
        a aVar = this.f4711d.get(str);
        if (aVar != null && aVar.f4714c != null) {
            if (z) {
                aVar.f4714c.c();
            } else {
                aVar.f4714c.d();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b(String str) {
        MediaStreamTrack a2 = a(str);
        if (a2 == null) {
            String str2 = f4708a;
            Log.d(str2, "mediaStreamTrackStop() No local MediaStreamTrack with id " + str);
            return;
        }
        a2.setEnabled(false);
        d(str);
    }

    private void d(String str) {
        a remove = this.f4711d.remove(str);
        if (remove != null) {
            h hVar = remove.f4714c;
            if (hVar != null && hVar.d()) {
                hVar.a();
            }
            remove.f4712a.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public void c(String str) {
        a aVar = this.f4711d.get(str);
        if (aVar != null && aVar.f4714c != null) {
            aVar.f4714c.e();
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: GetUserMediaImpl */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final MediaSource f4712a;

        /* renamed from: b  reason: collision with root package name */
        public final MediaStreamTrack f4713b;

        /* renamed from: c  reason: collision with root package name */
        public final h f4714c;

        public a(MediaStreamTrack mediaStreamTrack, MediaSource mediaSource, h hVar) {
            this.f4713b = mediaStreamTrack;
            this.f4712a = mediaSource;
            this.f4714c = hVar;
        }
    }
}
