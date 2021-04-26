package com.oney.WebRTCModule;

import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import org.webrtc.VideoFrame;
import org.webrtc.VideoSink;
import org.webrtc.VideoTrack;

/* compiled from: VideoTrackAdapter */
public class i {

    /* renamed from: a  reason: collision with root package name */
    static final String f4730a = i.class.getCanonicalName();

    /* renamed from: b  reason: collision with root package name */
    private Map<String, a> f4731b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private Timer f4732c = new Timer("VideoTrackMutedTimer");

    /* renamed from: d  reason: collision with root package name */
    private final int f4733d;
    private final WebRTCModule e;

    public i(WebRTCModule webRTCModule, int i) {
        this.f4733d = i;
        this.e = webRTCModule;
    }

    public void a(String str, VideoTrack videoTrack) {
        String id = videoTrack.id();
        if (!this.f4731b.containsKey(id)) {
            a aVar = new a(str, id);
            String str2 = f4730a;
            Log.d(str2, "Created adapter for " + id);
            this.f4731b.put(id, aVar);
            videoTrack.addSink(aVar);
            aVar.b();
            return;
        }
        String str3 = f4730a;
        Log.w(str3, "Attempted to add adapter twice for track ID: " + id);
    }

    public void a(VideoTrack videoTrack) {
        String id = videoTrack.id();
        a remove = this.f4731b.remove(id);
        if (remove != null) {
            videoTrack.removeSink(remove);
            remove.a();
            String str = f4730a;
            Log.d(str, "Deleted adapter for " + id);
            return;
        }
        String str2 = f4730a;
        Log.w(str2, "removeAdapter - no adapter for " + id);
    }

    /* access modifiers changed from: private */
    /* compiled from: VideoTrackAdapter */
    public class a implements VideoSink {

        /* renamed from: b  reason: collision with root package name */
        private TimerTask f4735b;

        /* renamed from: c  reason: collision with root package name */
        private volatile boolean f4736c;

        /* renamed from: d  reason: collision with root package name */
        private AtomicInteger f4737d = new AtomicInteger();
        private boolean e;
        private final String f;
        private final String g;

        a(String str, String str2) {
            this.f = str;
            this.g = str2;
        }

        @Override // org.webrtc.VideoSink
        public void onFrame(VideoFrame videoFrame) {
            this.f4737d.addAndGet(1);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void b() {
            if (!this.f4736c) {
                synchronized (this) {
                    if (this.f4735b != null) {
                        this.f4735b.cancel();
                    }
                    this.f4735b = new TimerTask() {
                        /* class com.oney.WebRTCModule.i.a.AnonymousClass1 */

                        /* renamed from: b  reason: collision with root package name */
                        private int f4739b = a.this.f4737d.get();

                        public void run() {
                            if (!a.this.f4736c) {
                                boolean z = this.f4739b == a.this.f4737d.get();
                                if (z != a.this.e) {
                                    a.this.e = z;
                                    a.this.a((a) z);
                                }
                                this.f4739b = a.this.f4737d.get();
                            }
                        }
                    };
                    i.this.f4732c.schedule(this.f4735b, 3000, 1500);
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(boolean z) {
            WritableMap createMap = Arguments.createMap();
            createMap.putInt("peerConnectionId", i.this.f4733d);
            createMap.putString("streamReactTag", this.f);
            createMap.putString("trackId", this.g);
            createMap.putBoolean("muted", z);
            String str = i.f4730a;
            StringBuilder sb = new StringBuilder();
            sb.append(z ? "Mute" : "Unmute");
            sb.append(" event pcId: ");
            sb.append(i.this.f4733d);
            sb.append(" streamTag: ");
            sb.append(this.f);
            sb.append(" trackId: ");
            sb.append(this.g);
            Log.d(str, sb.toString());
            i.this.e.sendEvent("mediaStreamTrackMuteChanged", createMap);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            this.f4736c = true;
            synchronized (this) {
                if (this.f4735b != null) {
                    this.f4735b.cancel();
                    this.f4735b = null;
                }
            }
        }
    }
}
