package com.oney.WebRTCModule;

import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.webrtc.AudioTrack;
import org.webrtc.DefaultVideoDecoderFactory;
import org.webrtc.DefaultVideoEncoderFactory;
import org.webrtc.EglBase;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.SoftwareVideoEncoderFactory;
import org.webrtc.VideoDecoderFactory;
import org.webrtc.VideoEncoderFactory;
import org.webrtc.VideoTrack;

public class WebRTCModule extends ReactContextBaseJavaModule {
    static final String TAG = WebRTCModule.class.getCanonicalName();
    private d getUserMediaImpl;
    final Map<String, MediaStream> localStreams = new HashMap();
    PeerConnectionFactory mFactory;
    private final SparseArray<e> mPeerConnectionObservers = new SparseArray<>();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "WebRTCModule";
    }

    public WebRTCModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        g.a(new Runnable() {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$T9WIHBBwOXq3znC0jdgs3RSomME */

            public final void run() {
                WebRTCModule.lambda$new$0(WebRTCModule.this);
            }
        });
    }

    /* access modifiers changed from: public */
    private void initAsync() {
        VideoDecoderFactory videoDecoderFactory;
        VideoEncoderFactory videoEncoderFactory;
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        EglBase.Context b2 = c.b();
        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(reactApplicationContext).setEnableVideoHwAcceleration(b2 != null).createInitializationOptions());
        if (b2 != null) {
            videoEncoderFactory = new DefaultVideoEncoderFactory(b2, true, false);
            videoDecoderFactory = new DefaultVideoDecoderFactory(b2);
        } else {
            videoEncoderFactory = new SoftwareVideoEncoderFactory();
            videoDecoderFactory = new SoftwareVideoDecoderFactory();
        }
        this.mFactory = PeerConnectionFactory.builder().setVideoEncoderFactory(videoEncoderFactory).setVideoDecoderFactory(videoDecoderFactory).createPeerConnectionFactory();
        if (b2 != null) {
            this.mFactory.setVideoHwAccelerationOptions(b2, b2);
        }
        this.getUserMediaImpl = new d(this, reactApplicationContext);
    }

    private PeerConnection getPeerConnection(int i) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null) {
            return null;
        }
        return eVar.a();
    }

    public void sendEvent(String str, WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, writableMap);
    }

    private PeerConnection.IceServer createIceServer(String str) {
        return PeerConnection.IceServer.builder(str).createIceServer();
    }

    private PeerConnection.IceServer createIceServer(String str, String str2, String str3) {
        return PeerConnection.IceServer.builder(str).setUsername(str2).setPassword(str3).createIceServer();
    }

    private List<PeerConnection.IceServer> createIceServers(ReadableArray readableArray) {
        int size = readableArray == null ? 0 : readableArray.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            boolean z = map.hasKey("username") && map.hasKey("credential");
            if (map.hasKey(ImagesContract.URL)) {
                if (z) {
                    arrayList.add(createIceServer(map.getString(ImagesContract.URL), map.getString("username"), map.getString("credential")));
                } else {
                    arrayList.add(createIceServer(map.getString(ImagesContract.URL)));
                }
            } else if (map.hasKey("urls")) {
                switch (map.getType("urls")) {
                    case String:
                        if (z) {
                            arrayList.add(createIceServer(map.getString("urls"), map.getString("username"), map.getString("credential")));
                            break;
                        } else {
                            arrayList.add(createIceServer(map.getString("urls")));
                            continue;
                        }
                    case Array:
                        ReadableArray array = map.getArray("urls");
                        for (int i2 = 0; i2 < array.size(); i2++) {
                            String string = array.getString(i2);
                            if (z) {
                                arrayList.add(createIceServer(string, map.getString("username"), map.getString("credential")));
                            } else {
                                arrayList.add(createIceServer(string));
                            }
                        }
                        continue;
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:159:0x025f, code lost:
        if (r0.equals("gather_continually") == false) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cb, code lost:
        if (r0.equals("max-bundle") != false) goto L_0x00d9;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0194  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01dc  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x022c  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.webrtc.PeerConnection.RTCConfiguration parseRTCConfiguration(com.facebook.react.bridge.ReadableMap r10) {
        /*
        // Method dump skipped, instructions count: 856
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.WebRTCModule.parseRTCConfiguration(com.facebook.react.bridge.ReadableMap):org.webrtc.PeerConnection$RTCConfiguration");
    }

    public void peerConnectionInit(ReadableMap readableMap, int i) {
        g.a(new Runnable(parseRTCConfiguration(readableMap), i) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$bhkV4CyAlnIosc6CFXBLABiJ07E */
            private final /* synthetic */ PeerConnection.RTCConfiguration f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionInit$1(WebRTCModule.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$peerConnectionInit$1(WebRTCModule webRTCModule, PeerConnection.RTCConfiguration rTCConfiguration, int i) {
        webRTCModule.peerConnectionInitAsync(rTCConfiguration, i);
    }

    private void peerConnectionInitAsync(PeerConnection.RTCConfiguration rTCConfiguration, int i) {
        e eVar = new e(this, i);
        eVar.a(this.mFactory.createPeerConnection(rTCConfiguration, eVar));
        this.mPeerConnectionObservers.put(i, eVar);
    }

    public MediaStream getStreamForReactTag(String str) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            int size = this.mPeerConnectionObservers.size();
            for (int i = 0; i < size; i++) {
                mediaStream = this.mPeerConnectionObservers.valueAt(i).f4717b.get(str);
                if (mediaStream != null) {
                    break;
                }
            }
        }
        return mediaStream;
    }

    private MediaStreamTrack getTrack(String str) {
        MediaStreamTrack localTrack = getLocalTrack(str);
        if (localTrack == null) {
            int size = this.mPeerConnectionObservers.size();
            for (int i = 0; i < size; i++) {
                localTrack = this.mPeerConnectionObservers.valueAt(i).f4718c.get(str);
                if (localTrack != null) {
                    break;
                }
            }
        }
        return localTrack;
    }

    public MediaStreamTrack getLocalTrack(String str) {
        return this.getUserMediaImpl.a(str);
    }

    private static MediaStreamTrack getLocalTrack(MediaStream mediaStream, String str) {
        for (AudioTrack audioTrack : mediaStream.audioTracks) {
            if (audioTrack.id().equals(str)) {
                return audioTrack;
            }
        }
        for (VideoTrack videoTrack : mediaStream.videoTracks) {
            if (videoTrack.id().equals(str)) {
                return videoTrack;
            }
        }
        return null;
    }

    private void parseConstraints(ReadableMap readableMap, List<MediaConstraints.KeyValuePair> list) {
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            list.add(new MediaConstraints.KeyValuePair(nextKey, f.a(readableMap, nextKey)));
        }
    }

    public MediaConstraints parseMediaConstraints(ReadableMap readableMap) {
        MediaConstraints mediaConstraints = new MediaConstraints();
        if (!readableMap.hasKey("mandatory") || readableMap.getType("mandatory") != ReadableType.Map) {
            Log.d(TAG, "mandatory constraints are not a map");
        } else {
            parseConstraints(readableMap.getMap("mandatory"), mediaConstraints.mandatory);
        }
        if (!readableMap.hasKey("optional") || readableMap.getType("optional") != ReadableType.Array) {
            Log.d(TAG, "optional constraints are not an array");
        } else {
            ReadableArray array = readableMap.getArray("optional");
            int size = array.size();
            for (int i = 0; i < size; i++) {
                if (array.getType(i) == ReadableType.Map) {
                    parseConstraints(array.getMap(i), mediaConstraints.optional);
                }
            }
        }
        return mediaConstraints;
    }

    public void getUserMedia(ReadableMap readableMap, Callback callback, Callback callback2) {
        g.a(new Runnable(readableMap, callback, callback2) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$C_Gitx4KOYySlWXaI1K4M1yoJoA */
            private final /* synthetic */ ReadableMap f$1;
            private final /* synthetic */ Callback f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$getUserMedia$2(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public void mediaStreamRelease(String str) {
        g.a(new Runnable(str) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$gWaNahvqvAuptDk8l9NoZ6jPUw */
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                WebRTCModule.this.mediaStreamReleaseAsync(this.f$1);
            }
        });
    }

    private void mediaStreamReleaseAsync(String str) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "mediaStreamRelease() stream is null");
            return;
        }
        ArrayList<MediaStreamTrack> arrayList = new ArrayList(mediaStream.audioTracks.size() + mediaStream.videoTracks.size());
        arrayList.addAll(mediaStream.audioTracks);
        arrayList.addAll(mediaStream.videoTracks);
        for (MediaStreamTrack mediaStreamTrack : arrayList) {
            mediaStreamTrackRelease(str, mediaStreamTrack.id());
        }
        this.localStreams.remove(str);
        int size = this.mPeerConnectionObservers.size();
        for (int i = 0; i < size; i++) {
            this.mPeerConnectionObservers.valueAt(i).b(mediaStream);
        }
        mediaStream.dispose();
    }

    public void enumerateDevices(Callback callback) {
        g.a(new Runnable(callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$UT_4BQuNyvZGKydjDrtTL0ViOwo */
            private final /* synthetic */ Callback f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                WebRTCModule.lambda$enumerateDevices$4(WebRTCModule.this, this.f$1);
            }
        });
    }

    public static /* synthetic */ void lambda$enumerateDevices$4(WebRTCModule webRTCModule, Callback callback) {
        callback.invoke(webRTCModule.getUserMediaImpl.a());
    }

    public void mediaStreamTrackRelease(String str, String str2) {
        g.a(new Runnable(str, str2) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$oHix3WCImTVSzDGk950dd1UVyA */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.this.mediaStreamTrackReleaseAsync(this.f$1, this.f$2);
            }
        });
    }

    private void mediaStreamTrackReleaseAsync(String str, String str2) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "mediaStreamTrackRelease() stream is null");
            return;
        }
        MediaStreamTrack localTrack = getLocalTrack(str2);
        if (localTrack == null) {
            localTrack = getLocalTrack(mediaStream, str2);
            if (localTrack == null) {
                String str3 = TAG;
                Log.d(str3, "mediaStreamTrackRelease() No local MediaStreamTrack with id " + str2);
                return;
            }
        } else {
            mediaStreamTrackStop(str2);
        }
        String kind = localTrack.kind();
        if (MediaStreamTrack.AUDIO_TRACK_KIND.equals(kind)) {
            mediaStream.removeTrack((AudioTrack) localTrack);
        } else if (MediaStreamTrack.VIDEO_TRACK_KIND.equals(kind)) {
            mediaStream.removeTrack((VideoTrack) localTrack);
        }
        localTrack.dispose();
    }

    public void mediaStreamTrackSetEnabled(String str, boolean z) {
        g.a(new Runnable(str, z) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$diX6p9RxXXxMSV8fKfljTIU84 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ boolean f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.lambda$mediaStreamTrackSetEnabled$6(WebRTCModule.this, this.f$1, this.f$2);
            }
        });
    }

    public static /* synthetic */ void lambda$mediaStreamTrackSetEnabled$6(WebRTCModule webRTCModule, String str, boolean z) {
        webRTCModule.mediaStreamTrackSetEnabledAsync(str, z);
    }

    private void mediaStreamTrackSetEnabledAsync(String str, boolean z) {
        MediaStreamTrack track = getTrack(str);
        if (track == null) {
            Log.d(TAG, "mediaStreamTrackSetEnabled() track is null");
        } else if (track.enabled() != z) {
            track.setEnabled(z);
            this.getUserMediaImpl.a(str, z);
        }
    }

    public void mediaStreamTrackStop(String str) {
        this.getUserMediaImpl.b(str);
    }

    public void mediaStreamTrackSwitchCamera(String str) {
        if (getLocalTrack(str) != null) {
            this.getUserMediaImpl.c(str);
        }
    }

    public void peerConnectionSetConfiguration(ReadableMap readableMap, int i) {
        g.a(new Runnable(readableMap, i) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$SEs2I1sc7MQAoKMR8qo3yDtWck */
            private final /* synthetic */ ReadableMap f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionSetConfiguration$7(WebRTCModule.this, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionSetConfigurationAsync(ReadableMap readableMap, int i) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection == null) {
            Log.d(TAG, "peerConnectionSetConfiguration() peerConnection is null");
        } else {
            peerConnection.setConfiguration(parseRTCConfiguration(readableMap));
        }
    }

    public void peerConnectionAddStream(String str, int i) {
        g.a(new Runnable(str, i) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$yfN2UyulHAkmGD_qjdv96I1bYU4 */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.this.peerConnectionAddStreamAsync(this.f$1, this.f$2);
            }
        });
    }

    private void peerConnectionAddStreamAsync(String str, int i) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "peerConnectionAddStream() mediaStream is null");
            return;
        }
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || !eVar.a(mediaStream)) {
            Log.e(TAG, "peerConnectionAddStream() failed");
        }
    }

    public void peerConnectionRemoveStream(String str, int i) {
        g.a(new Runnable(str, i) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$2BZtyqSX0MjlAdwI7TokF8Ykw */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionRemoveStream$9(WebRTCModule.this, this.f$1, this.f$2);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionRemoveStreamAsync(String str, int i) {
        MediaStream mediaStream = this.localStreams.get(str);
        if (mediaStream == null) {
            Log.d(TAG, "peerConnectionRemoveStream() mediaStream is null");
            return;
        }
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || !eVar.b(mediaStream)) {
            Log.e(TAG, "peerConnectionRemoveStream() failed");
        }
    }

    public void peerConnectionCreateOffer(int i, ReadableMap readableMap, Callback callback) {
        g.a(new Runnable(i, readableMap, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$R5cgm7zjgYLdQEsknNtPKDtLrzI */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ ReadableMap f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionCreateOffer$10(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionCreateOfferAsync(int i, ReadableMap readableMap, final Callback callback) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection != null) {
            peerConnection.createOffer(new SdpObserver() {
                /* class com.oney.WebRTCModule.WebRTCModule.AnonymousClass1 */

                @Override // org.webrtc.SdpObserver
                public void onSetFailure(String str) {
                }

                @Override // org.webrtc.SdpObserver
                public void onSetSuccess() {
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateFailure(String str) {
                    callback.invoke(false, str);
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription sessionDescription) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("sdp", sessionDescription.description);
                    createMap.putString(AppMeasurement.Param.TYPE, sessionDescription.type.canonicalForm());
                    callback.invoke(true, createMap);
                }
            }, parseMediaConstraints(readableMap));
            return;
        }
        Log.d(TAG, "peerConnectionCreateOffer() peerConnection is null");
        callback.invoke(false, "peerConnection is null");
    }

    public void peerConnectionCreateAnswer(int i, ReadableMap readableMap, Callback callback) {
        g.a(new Runnable(i, readableMap, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$9pHLAWhXvWdDd5KzKeO0QcLZBzk */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ ReadableMap f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionCreateAnswer$11(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionCreateAnswerAsync(int i, ReadableMap readableMap, final Callback callback) {
        PeerConnection peerConnection = getPeerConnection(i);
        if (peerConnection != null) {
            peerConnection.createAnswer(new SdpObserver() {
                /* class com.oney.WebRTCModule.WebRTCModule.AnonymousClass2 */

                @Override // org.webrtc.SdpObserver
                public void onSetFailure(String str) {
                }

                @Override // org.webrtc.SdpObserver
                public void onSetSuccess() {
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateFailure(String str) {
                    callback.invoke(false, str);
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription sessionDescription) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putString("sdp", sessionDescription.description);
                    createMap.putString(AppMeasurement.Param.TYPE, sessionDescription.type.canonicalForm());
                    callback.invoke(true, createMap);
                }
            }, parseMediaConstraints(readableMap));
            return;
        }
        Log.d(TAG, "peerConnectionCreateAnswer() peerConnection is null");
        callback.invoke(false, "peerConnection is null");
    }

    public void peerConnectionSetLocalDescription(ReadableMap readableMap, int i, Callback callback) {
        g.a(new Runnable(readableMap, i, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$UNnfFD3zEfNqSgWbghrLFAA4UIE */
            private final /* synthetic */ ReadableMap f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionSetLocalDescription$12(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public static /* synthetic */ void lambda$peerConnectionSetLocalDescription$12(WebRTCModule webRTCModule, ReadableMap readableMap, int i, Callback callback) {
        webRTCModule.peerConnectionSetLocalDescriptionAsync(readableMap, i, callback);
    }

    private void peerConnectionSetLocalDescriptionAsync(ReadableMap readableMap, int i, final Callback callback) {
        PeerConnection peerConnection = getPeerConnection(i);
        Log.d(TAG, "peerConnectionSetLocalDescription() start");
        if (peerConnection != null) {
            peerConnection.setLocalDescription(new SdpObserver() {
                /* class com.oney.WebRTCModule.WebRTCModule.AnonymousClass3 */

                @Override // org.webrtc.SdpObserver
                public void onCreateFailure(String str) {
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription sessionDescription) {
                }

                @Override // org.webrtc.SdpObserver
                public void onSetSuccess() {
                    callback.invoke(true);
                }

                @Override // org.webrtc.SdpObserver
                public void onSetFailure(String str) {
                    callback.invoke(false, str);
                }
            }, new SessionDescription(SessionDescription.Type.fromCanonicalForm(readableMap.getString(AppMeasurement.Param.TYPE)), readableMap.getString("sdp")));
        } else {
            Log.d(TAG, "peerConnectionSetLocalDescription() peerConnection is null");
            callback.invoke(false, "peerConnection is null");
        }
        Log.d(TAG, "peerConnectionSetLocalDescription() end");
    }

    public void peerConnectionSetRemoteDescription(ReadableMap readableMap, int i, Callback callback) {
        g.a(new Runnable(readableMap, i, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$2_B4OVuwD8T1oz2Mw0ftjOOe73g */
            private final /* synthetic */ ReadableMap f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionSetRemoteDescription$13(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionSetRemoteDescriptionAsync(ReadableMap readableMap, int i, final Callback callback) {
        PeerConnection peerConnection = getPeerConnection(i);
        Log.d(TAG, "peerConnectionSetRemoteDescription() start");
        if (peerConnection != null) {
            peerConnection.setRemoteDescription(new SdpObserver() {
                /* class com.oney.WebRTCModule.WebRTCModule.AnonymousClass4 */

                @Override // org.webrtc.SdpObserver
                public void onCreateFailure(String str) {
                }

                @Override // org.webrtc.SdpObserver
                public void onCreateSuccess(SessionDescription sessionDescription) {
                }

                @Override // org.webrtc.SdpObserver
                public void onSetSuccess() {
                    callback.invoke(true);
                }

                @Override // org.webrtc.SdpObserver
                public void onSetFailure(String str) {
                    callback.invoke(false, str);
                }
            }, new SessionDescription(SessionDescription.Type.fromCanonicalForm(readableMap.getString(AppMeasurement.Param.TYPE)), readableMap.getString("sdp")));
        } else {
            Log.d(TAG, "peerConnectionSetRemoteDescription() peerConnection is null");
            callback.invoke(false, "peerConnection is null");
        }
        Log.d(TAG, "peerConnectionSetRemoteDescription() end");
    }

    public void peerConnectionAddICECandidate(ReadableMap readableMap, int i, Callback callback) {
        g.a(new Runnable(readableMap, i, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$X8lGedDOyqxPVfd3IwrEGohvIBQ */
            private final /* synthetic */ ReadableMap f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionAddICECandidate$14(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public static /* synthetic */ void lambda$peerConnectionAddICECandidate$14(WebRTCModule webRTCModule, ReadableMap readableMap, int i, Callback callback) {
        webRTCModule.peerConnectionAddICECandidateAsync(readableMap, i, callback);
    }

    private void peerConnectionAddICECandidateAsync(ReadableMap readableMap, int i, Callback callback) {
        boolean z;
        PeerConnection peerConnection = getPeerConnection(i);
        Log.d(TAG, "peerConnectionAddICECandidate() start");
        if (peerConnection != null) {
            z = peerConnection.addIceCandidate(new IceCandidate(readableMap.getString("sdpMid"), readableMap.getInt("sdpMLineIndex"), readableMap.getString("candidate")));
        } else {
            Log.d(TAG, "peerConnectionAddICECandidate() peerConnection is null");
            z = false;
        }
        callback.invoke(Boolean.valueOf(z));
        Log.d(TAG, "peerConnectionAddICECandidate() end");
    }

    public void peerConnectionGetStats(String str, int i, Callback callback) {
        g.a(new Runnable(str, i, callback) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$K3SBcOSDkJFQUaaPI1i1yFZtwM */
            private final /* synthetic */ String f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ Callback f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.lambda$peerConnectionGetStats$15(WebRTCModule.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    /* access modifiers changed from: public */
    private void peerConnectionGetStatsAsync(String str, int i, Callback callback) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || eVar.a() == null) {
            Log.d(TAG, "peerConnectionGetStats() peerConnection is null");
            callback.invoke(false, "PeerConnection ID not found");
            return;
        }
        eVar.a(str, callback);
    }

    public void peerConnectionClose(int i) {
        g.a(new Runnable(i) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$mPS_N0abbNQnEl2DvAxjIn1Q6ak */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                WebRTCModule.this.peerConnectionCloseAsync(this.f$1);
            }
        });
    }

    private void peerConnectionCloseAsync(int i) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || eVar.a() == null) {
            Log.d(TAG, "peerConnectionClose() peerConnection is null");
            return;
        }
        eVar.b();
        this.mPeerConnectionObservers.remove(i);
    }

    public void createDataChannel(int i, String str, ReadableMap readableMap) {
        g.a(new Runnable(i, str, readableMap) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$rEEhwwdz0tAnRDm752ZUupPwuM */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ ReadableMap f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void run() {
                WebRTCModule.this.createDataChannelAsync(this.f$1, this.f$2, this.f$3);
            }
        });
    }

    private void createDataChannelAsync(int i, String str, ReadableMap readableMap) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || eVar.a() == null) {
            Log.d(TAG, "createDataChannel() peerConnection is null");
        } else {
            eVar.a(str, readableMap);
        }
    }

    public void dataChannelClose(int i, int i2) {
        g.a(new Runnable(i, i2) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$envNAcA_3P927rOAI8gna3mxkg */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                WebRTCModule.this.dataChannelCloseAsync(this.f$1, this.f$2);
            }
        });
    }

    private void dataChannelCloseAsync(int i, int i2) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || eVar.a() == null) {
            Log.d(TAG, "dataChannelClose() peerConnection is null");
        } else {
            eVar.a(i2);
        }
    }

    public void dataChannelSend(int i, int i2, String str, String str2) {
        g.a(new Runnable(i, i2, str, str2) {
            /* class com.oney.WebRTCModule.$$Lambda$WebRTCModule$ncQqqE_jKDqU1L56nQEcxKf_Bnc */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ int f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ String f$4;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
            }

            public final void run() {
                WebRTCModule.this.dataChannelSendAsync(this.f$1, this.f$2, this.f$3, this.f$4);
            }
        });
    }

    private void dataChannelSendAsync(int i, int i2, String str, String str2) {
        e eVar = this.mPeerConnectionObservers.get(i);
        if (eVar == null || eVar.a() == null) {
            Log.d(TAG, "dataChannelSend() peerConnection is null");
        } else {
            eVar.a(i2, str, str2);
        }
    }
}
