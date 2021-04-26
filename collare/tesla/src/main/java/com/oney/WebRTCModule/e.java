package com.oney.WebRTCModule;

import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.spongycastle.i18n.TextBundle;
import org.webrtc.AudioTrack;
import org.webrtc.CalledByNative;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.MediaStreamTrack;
import org.webrtc.PeerConnection;
import org.webrtc.RtpReceiver;
import org.webrtc.RtpTransceiver;
import org.webrtc.StatsObserver;
import org.webrtc.StatsReport;
import org.webrtc.VideoTrack;

/* access modifiers changed from: package-private */
/* compiled from: PeerConnectionObserver */
public class e implements PeerConnection.Observer {

    /* renamed from: d  reason: collision with root package name */
    private static final String f4715d = WebRTCModule.TAG;

    /* renamed from: a  reason: collision with root package name */
    final List<MediaStream> f4716a;

    /* renamed from: b  reason: collision with root package name */
    final Map<String, MediaStream> f4717b;

    /* renamed from: c  reason: collision with root package name */
    final Map<String, MediaStreamTrack> f4718c;
    private final SparseArray<DataChannel> e = new SparseArray<>();
    private final int f;
    private PeerConnection g;
    private final i h;
    private final WebRTCModule i;
    private SoftReference<StringBuilder> j = new SoftReference<>(null);

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceConnectionReceivingChange(boolean z) {
    }

    @Override // org.webrtc.PeerConnection.Observer
    @CalledByNative("Observer")
    public /* synthetic */ void onTrack(RtpTransceiver rtpTransceiver) {
        PeerConnection.Observer.CC.$default$onTrack(this, rtpTransceiver);
    }

    e(WebRTCModule webRTCModule, int i2) {
        this.i = webRTCModule;
        this.f = i2;
        this.f4716a = new ArrayList();
        this.f4717b = new HashMap();
        this.f4718c = new HashMap();
        this.h = new i(webRTCModule, i2);
    }

    /* access modifiers changed from: package-private */
    public boolean a(MediaStream mediaStream) {
        PeerConnection peerConnection = this.g;
        if (peerConnection == null || !peerConnection.addStream(mediaStream)) {
            return false;
        }
        this.f4716a.add(mediaStream);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean b(MediaStream mediaStream) {
        PeerConnection peerConnection = this.g;
        if (peerConnection != null) {
            peerConnection.removeStream(mediaStream);
        }
        return this.f4716a.remove(mediaStream);
    }

    /* access modifiers changed from: package-private */
    public PeerConnection a() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public void a(PeerConnection peerConnection) {
        this.g = peerConnection;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        this.g.close();
        Iterator it = new ArrayList(this.f4716a).iterator();
        while (it.hasNext()) {
            b((MediaStream) it.next());
        }
        for (MediaStreamTrack mediaStreamTrack : this.f4718c.values()) {
            if (mediaStreamTrack.kind().equals(MediaStreamTrack.VIDEO_TRACK_KIND)) {
                this.h.a((VideoTrack) mediaStreamTrack);
            }
        }
        this.g.dispose();
        this.f4717b.clear();
        this.f4718c.clear();
        this.e.clear();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, ReadableMap readableMap) {
        DataChannel.Init init = new DataChannel.Init();
        if (readableMap != null) {
            if (readableMap.hasKey("id")) {
                init.id = readableMap.getInt("id");
            }
            if (readableMap.hasKey("ordered")) {
                init.ordered = readableMap.getBoolean("ordered");
            }
            if (readableMap.hasKey("maxRetransmitTime")) {
                init.maxRetransmitTimeMs = readableMap.getInt("maxRetransmitTime");
            }
            if (readableMap.hasKey("maxRetransmits")) {
                init.maxRetransmits = readableMap.getInt("maxRetransmits");
            }
            if (readableMap.hasKey("protocol")) {
                init.protocol = readableMap.getString("protocol");
            }
            if (readableMap.hasKey("negotiated")) {
                init.negotiated = readableMap.getBoolean("negotiated");
            }
        }
        DataChannel createDataChannel = this.g.createDataChannel(str, init);
        int i2 = init.id;
        if (-1 != i2) {
            this.e.put(i2, createDataChannel);
            a(i2, createDataChannel);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        DataChannel dataChannel = this.e.get(i2);
        if (dataChannel != null) {
            dataChannel.close();
            this.e.remove(i2);
            return;
        }
        Log.d(f4715d, "dataChannelClose() dataChannel is null");
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str, String str2) {
        byte[] bArr;
        DataChannel dataChannel = this.e.get(i2);
        if (dataChannel != null) {
            if (str2.equals(TextBundle.TEXT_ENTRY)) {
                try {
                    bArr = str.getBytes("UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    Log.d(f4715d, "Could not encode text string as UTF-8.");
                    return;
                }
            } else if (str2.equals("binary")) {
                bArr = Base64.decode(str, 2);
            } else {
                String str3 = f4715d;
                Log.e(str3, "Unsupported data type: " + str2);
                return;
            }
            dataChannel.send(new DataChannel.Buffer(ByteBuffer.wrap(bArr), str2.equals("binary")));
            return;
        }
        Log.d(f4715d, "dataChannelSend() dataChannel is null");
    }

    /* access modifiers changed from: package-private */
    public void a(String str, final Callback callback) {
        MediaStreamTrack mediaStreamTrack;
        if (str == null || str.isEmpty()) {
            mediaStreamTrack = null;
        } else {
            mediaStreamTrack = this.i.getLocalTrack(str);
            if (mediaStreamTrack == null && (mediaStreamTrack = this.f4718c.get(str)) == null) {
                String str2 = f4715d;
                Log.e(str2, "peerConnectionGetStats() MediaStreamTrack not found for id: " + str);
                callback.invoke(false, "Track not found");
                return;
            }
        }
        this.g.getStats(new StatsObserver() {
            /* class com.oney.WebRTCModule.e.AnonymousClass1 */

            @Override // org.webrtc.StatsObserver
            public void onComplete(StatsReport[] statsReportArr) {
                callback.invoke(true, e.this.a((e) statsReportArr));
            }
        }, mediaStreamTrack);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String a(StatsReport[] statsReportArr) {
        StringBuilder sb = this.j.get();
        if (sb == null) {
            sb = new StringBuilder();
            this.j = new SoftReference<>(sb);
        }
        sb.append('[');
        int length = statsReportArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            StatsReport statsReport = statsReportArr[i2];
            if (i2 != 0) {
                sb.append(',');
            }
            sb.append("{\"id\":\"");
            sb.append(statsReport.id);
            sb.append("\",\"type\":\"");
            sb.append(statsReport.type);
            sb.append("\",\"timestamp\":");
            sb.append(statsReport.timestamp);
            sb.append(",\"values\":[");
            StatsReport.Value[] valueArr = statsReport.values;
            int length2 = valueArr.length;
            for (int i3 = 0; i3 < length2; i3++) {
                StatsReport.Value value = valueArr[i3];
                if (i3 != 0) {
                    sb.append(',');
                }
                sb.append("{\"");
                sb.append(value.name);
                sb.append("\":\"");
                sb.append(value.value);
                sb.append("\"}");
            }
            sb.append("]}");
        }
        sb.append("]");
        String sb2 = sb.toString();
        sb.setLength(0);
        return sb2;
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceCandidate(IceCandidate iceCandidate) {
        Log.d(f4715d, "onIceCandidate");
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("sdpMLineIndex", iceCandidate.sdpMLineIndex);
        createMap2.putString("sdpMid", iceCandidate.sdpMid);
        createMap2.putString("candidate", iceCandidate.sdp);
        createMap.putMap("candidate", createMap2);
        this.i.sendEvent("peerConnectionGotICECandidate", createMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
        Log.d(f4715d, "onIceCandidatesRemoved");
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        createMap.putString("iceConnectionState", a(iceConnectionState));
        this.i.sendEvent("peerConnectionIceConnectionChanged", createMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
        String str = f4715d;
        Log.d(str, "onIceGatheringChange" + iceGatheringState.name());
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        createMap.putString("iceGatheringState", a(iceGatheringState));
        this.i.sendEvent("peerConnectionIceGatheringChanged", createMap);
    }

    private String c(MediaStream mediaStream) {
        for (Map.Entry<String, MediaStream> entry : this.f4717b.entrySet()) {
            if (entry.getValue().equals(mediaStream)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0069 A[LOOP:1: B:12:0x0060->B:14:0x0069, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00c1 A[LOOP:2: B:15:0x00b9->B:17:0x00c1, LOOP_END] */
    @Override // org.webrtc.PeerConnection.Observer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAddStream(org.webrtc.MediaStream r11) {
        /*
        // Method dump skipped, instructions count: 281
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.e.onAddStream(org.webrtc.MediaStream):void");
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onRemoveStream(MediaStream mediaStream) {
        String c2 = c(mediaStream);
        if (c2 == null) {
            String str = f4715d;
            Log.w(str, "onRemoveStream - no remote stream for id: " + mediaStream.getId());
            return;
        }
        for (VideoTrack videoTrack : mediaStream.videoTracks) {
            this.h.a(videoTrack);
            this.f4718c.remove(videoTrack.id());
        }
        for (AudioTrack audioTrack : mediaStream.audioTracks) {
            this.f4718c.remove(audioTrack.id());
        }
        this.f4717b.remove(c2);
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        createMap.putString("streamId", c2);
        this.i.sendEvent("peerConnectionRemovedStream", createMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onDataChannel(DataChannel dataChannel) {
        int id = dataChannel.id();
        if (-1 != id) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("id", id);
            createMap.putString("label", dataChannel.label());
            WritableMap createMap2 = Arguments.createMap();
            createMap2.putInt("id", this.f);
            createMap2.putMap("dataChannel", createMap);
            this.e.put(id, dataChannel);
            a(id, dataChannel);
            this.i.sendEvent("peerConnectionDidOpenDataChannel", createMap2);
        }
    }

    private void a(int i2, DataChannel dataChannel) {
        dataChannel.registerObserver(new b(this.i, this.f, i2, dataChannel));
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onRenegotiationNeeded() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        this.i.sendEvent("peerConnectionOnRenegotiationNeeded", createMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onSignalingChange(PeerConnection.SignalingState signalingState) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("id", this.f);
        createMap.putString("signalingState", a(signalingState));
        this.i.sendEvent("peerConnectionSignalingStateChanged", createMap);
    }

    @Override // org.webrtc.PeerConnection.Observer
    public void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
        Log.d(f4715d, "onAddTrack");
    }

    private String a(PeerConnection.IceConnectionState iceConnectionState) {
        switch (iceConnectionState) {
            case NEW:
                return "new";
            case CHECKING:
                return "checking";
            case CONNECTED:
                return "connected";
            case COMPLETED:
                return "completed";
            case FAILED:
                return "failed";
            case DISCONNECTED:
                return "disconnected";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }

    private String a(PeerConnection.IceGatheringState iceGatheringState) {
        switch (iceGatheringState) {
            case NEW:
                return "new";
            case GATHERING:
                return "gathering";
            case COMPLETE:
                return "complete";
            default:
                return null;
        }
    }

    private String a(PeerConnection.SignalingState signalingState) {
        switch (signalingState) {
            case STABLE:
                return "stable";
            case HAVE_LOCAL_OFFER:
                return "have-local-offer";
            case HAVE_LOCAL_PRANSWER:
                return "have-local-pranswer";
            case HAVE_REMOTE_OFFER:
                return "have-remote-offer";
            case HAVE_REMOTE_PRANSWER:
                return "have-remote-pranswer";
            case CLOSED:
                return "closed";
            default:
                return null;
        }
    }
}
