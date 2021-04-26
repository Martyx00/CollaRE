package com.oney.WebRTCModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.support.v4.g.t;
import android.util.Log;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReactContext;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.spongycastle.crypto.tls.CipherSuite;
import org.webrtc.EglBase;
import org.webrtc.MediaStream;
import org.webrtc.RendererCommon;
import org.webrtc.SurfaceViewRenderer;
import org.webrtc.VideoTrack;

/* compiled from: WebRTCView */
public class k extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private static final RendererCommon.ScalingType f4740a = RendererCommon.ScalingType.SCALE_ASPECT_FIT;

    /* renamed from: b  reason: collision with root package name */
    private static final Method f4741b;

    /* renamed from: c  reason: collision with root package name */
    private static final String f4742c = WebRTCModule.TAG;

    /* renamed from: d  reason: collision with root package name */
    private int f4743d;
    private int e;
    private int f;
    private final Object g = new Object();
    private boolean h;
    private boolean i;
    private final RendererCommon.RendererEvents j = new RendererCommon.RendererEvents() {
        /* class com.oney.WebRTCModule.k.AnonymousClass1 */

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFirstFrameRendered() {
            k.this.c();
        }

        @Override // org.webrtc.RendererCommon.RendererEvents
        public void onFrameResolutionChanged(int i, int i2, int i3) {
            k.this.a(i, i2, i3);
        }
    };
    private final Runnable k = new Runnable() {
        /* class com.oney.WebRTCModule.k.AnonymousClass2 */

        public void run() {
            k.this.e();
        }
    };
    private RendererCommon.ScalingType l;
    private String m;
    private final SurfaceViewRenderer n;
    private VideoTrack o;

    static {
        Method method = null;
        try {
            Method method2 = k.class.getMethod("isInLayout", new Class[0]);
            if (Boolean.TYPE.isAssignableFrom(method2.getReturnType())) {
                method = method2;
            }
        } catch (NoSuchMethodException unused) {
        }
        f4741b = method;
    }

    public k(Context context) {
        super(context);
        this.n = new SurfaceViewRenderer(context);
        addView(this.n);
        setMirror(false);
        setScalingType(f4740a);
    }

    private void a() {
        this.n.setBackgroundColor(-16777216);
        this.n.clearImage();
    }

    private VideoTrack getVideoTrack() {
        VideoTrack videoTrack = this.o;
        if (videoTrack == null || videoTrack == a(this.m)) {
            return videoTrack;
        }
        return null;
    }

    private VideoTrack a(String str) {
        MediaStream streamForReactTag;
        if (!(str == null || (streamForReactTag = ((WebRTCModule) ((ReactContext) getContext()).getNativeModule(WebRTCModule.class)).getStreamForReactTag(str)) == null)) {
            List<VideoTrack> list = streamForReactTag.videoTracks;
            if (!list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    private boolean b() {
        Method method = f4741b;
        if (method == null) {
            return false;
        }
        try {
            return ((Boolean) method.invoke(this, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        try {
            f();
        } finally {
            super.onAttachedToWindow();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        try {
            d();
        } finally {
            super.onDetachedFromWindow();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        post(new Runnable() {
            /* class com.oney.WebRTCModule.$$Lambda$k$wZHfv2C4sTowgWhZcJCk4tCGro */

            public final void run() {
                k.this.g();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() {
        Log.d(f4742c, "First frame rendered.");
        this.n.setBackgroundColor(0);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(int i2, int i3, int i4) {
        boolean z;
        synchronized (this.g) {
            if (this.f4743d != i3) {
                this.f4743d = i3;
                z = true;
            } else {
                z = false;
            }
            if (this.e != i4) {
                this.e = i4;
                z = true;
            }
            if (this.f != i2) {
                this.f = i2;
                z = true;
            }
        }
        if (z) {
            post(this.k);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8;
        int i9;
        RendererCommon.ScalingType scalingType;
        float f2;
        float f3;
        int i10 = i5 - i3;
        int i11 = i4 - i2;
        int i12 = 0;
        if (i10 == 0 || i11 == 0) {
            i6 = 0;
            i11 = 0;
            i10 = 0;
        } else {
            synchronized (this.g) {
                i7 = this.f4743d;
                i8 = this.e;
                i9 = this.f;
                scalingType = this.l;
            }
            if (AnonymousClass3.f4746a[scalingType.ordinal()] == 1) {
                i6 = 0;
            } else if (i7 == 0 || i9 == 0) {
                i6 = 0;
                i11 = 0;
                i10 = 0;
            } else {
                if (i8 % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 == 0) {
                    f3 = (float) i9;
                    f2 = (float) i7;
                } else {
                    f3 = (float) i7;
                    f2 = (float) i9;
                }
                Point displaySize = RendererCommon.getDisplaySize(scalingType, f3 / f2, i11, i10);
                int i13 = (i11 - displaySize.x) / 2;
                i6 = (i10 - displaySize.y) / 2;
                i11 = displaySize.x + i13;
                i10 = i6 + displaySize.y;
                i12 = i13;
            }
        }
        this.n.layout(i12, i6, i11, i10);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oney.WebRTCModule.k$3  reason: invalid class name */
    /* compiled from: WebRTCView */
    public static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f4746a = new int[RendererCommon.ScalingType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                org.webrtc.RendererCommon$ScalingType[] r0 = org.webrtc.RendererCommon.ScalingType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oney.WebRTCModule.k.AnonymousClass3.f4746a = r0
                int[] r0 = com.oney.WebRTCModule.k.AnonymousClass3.f4746a     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.webrtc.RendererCommon$ScalingType r1 = org.webrtc.RendererCommon.ScalingType.SCALE_ASPECT_FILL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oney.WebRTCModule.k.AnonymousClass3.f4746a     // Catch:{ NoSuchFieldError -> 0x001f }
                org.webrtc.RendererCommon$ScalingType r1 = org.webrtc.RendererCommon.ScalingType.SCALE_ASPECT_FIT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oney.WebRTCModule.k.AnonymousClass3.<clinit>():void");
        }
    }

    private void d() {
        if (this.i) {
            VideoTrack videoTrack = getVideoTrack();
            if (videoTrack != null) {
                videoTrack.removeSink(this.n);
            }
            this.n.release();
            this.i = false;
            synchronized (this.g) {
                this.f4743d = 0;
                this.e = 0;
                this.f = 0;
            }
            e();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @SuppressLint({"WrongCall"})
    private void e() {
        this.n.requestLayout();
        if (!b()) {
            onLayout(false, getLeft(), getTop(), getRight(), getBottom());
        }
    }

    public void setMirror(boolean z) {
        if (this.h != z) {
            this.h = z;
            this.n.setMirror(z);
            e();
        }
    }

    public void setObjectFit(String str) {
        setScalingType("cover".equals(str) ? RendererCommon.ScalingType.SCALE_ASPECT_FILL : RendererCommon.ScalingType.SCALE_ASPECT_FIT);
    }

    private void setScalingType(RendererCommon.ScalingType scalingType) {
        synchronized (this.g) {
            if (this.l != scalingType) {
                this.l = scalingType;
                this.n.setScalingType(scalingType);
                e();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setStreamURL(String str) {
        if (str == null) {
            if (this.m == null) {
                return;
            }
        } else if (str.equals(this.m)) {
            return;
        }
        VideoTrack a2 = a(str);
        if (this.o != a2) {
            setVideoTrack(null);
        }
        this.m = str;
        setVideoTrack(a2);
    }

    private void setVideoTrack(VideoTrack videoTrack) {
        VideoTrack videoTrack2 = this.o;
        if (videoTrack2 != videoTrack) {
            if (videoTrack2 != null) {
                if (videoTrack == null) {
                    a();
                }
                d();
            }
            this.o = videoTrack;
            if (videoTrack != null) {
                f();
                if (videoTrack2 == null) {
                    a();
                }
            }
        }
    }

    public void setZOrder(int i2) {
        switch (i2) {
            case 0:
                this.n.setZOrderMediaOverlay(false);
                return;
            case 1:
                this.n.setZOrderMediaOverlay(true);
                return;
            case 2:
                this.n.setZOrderOnTop(true);
                return;
            default:
                return;
        }
    }

    private void f() {
        VideoTrack videoTrack;
        if (!this.i && (videoTrack = getVideoTrack()) != null && t.u(this)) {
            EglBase.Context b2 = c.b();
            if (b2 == null) {
                Log.e(f4742c, "Failed to render a VideoTrack!");
                return;
            }
            this.n.init(b2, this.j);
            videoTrack.addSink(this.n);
            this.i = true;
        }
    }
}
