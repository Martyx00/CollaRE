package com.facebook.react.modules.debug;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.a;
import com.facebook.react.uimanager.UIManagerModule;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: FpsDebugFrameCallback */
public class b extends a.AbstractC0055a {

    /* renamed from: a  reason: collision with root package name */
    private com.facebook.react.modules.core.a f2856a;

    /* renamed from: b  reason: collision with root package name */
    private final ReactContext f2857b;

    /* renamed from: c  reason: collision with root package name */
    private final UIManagerModule f2858c;

    /* renamed from: d  reason: collision with root package name */
    private final a f2859d;
    private boolean e = false;
    private long f = -1;
    private long g = -1;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private boolean l = false;
    private TreeMap<Long, a> m;

    /* compiled from: FpsDebugFrameCallback */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final int f2862a;

        /* renamed from: b  reason: collision with root package name */
        public final int f2863b;

        /* renamed from: c  reason: collision with root package name */
        public final int f2864c;

        /* renamed from: d  reason: collision with root package name */
        public final int f2865d;
        public final double e;
        public final double f;
        public final int g;

        public a(int i, int i2, int i3, int i4, double d2, double d3, int i5) {
            this.f2862a = i;
            this.f2863b = i2;
            this.f2864c = i3;
            this.f2865d = i4;
            this.e = d2;
            this.f = d3;
            this.g = i5;
        }
    }

    public b(ReactContext reactContext) {
        this.f2857b = reactContext;
        this.f2858c = (UIManagerModule) reactContext.getNativeModule(UIManagerModule.class);
        this.f2859d = new a();
    }

    @Override // com.facebook.react.modules.core.a.AbstractC0055a
    public void b(long j2) {
        if (!this.e) {
            if (this.f == -1) {
                this.f = j2;
            }
            long j3 = this.g;
            this.g = j2;
            if (this.f2859d.a(j3, j2)) {
                this.k++;
            }
            this.h++;
            int i2 = i();
            if ((i2 - this.i) - 1 >= 4) {
                this.j++;
            }
            if (this.l) {
                com.facebook.i.a.a.a(this.m);
                this.m.put(Long.valueOf(System.currentTimeMillis()), new a(g(), h(), i2, this.j, e(), f(), j()));
            }
            this.i = i2;
            com.facebook.react.modules.core.a aVar = this.f2856a;
            if (aVar != null) {
                aVar.a(this);
            }
        }
    }

    public void b() {
        this.e = false;
        this.f2857b.getCatalystInstance().addBridgeIdleDebugListener(this.f2859d);
        this.f2858c.setViewHierarchyUpdateDebugListener(this.f2859d);
        UiThreadUtil.runOnUiThread(new Runnable() {
            /* class com.facebook.react.modules.debug.b.AnonymousClass1 */

            public void run() {
                b.this.f2856a = com.facebook.react.modules.core.a.a();
                b.this.f2856a.a(this);
            }
        });
    }

    public void c() {
        this.m = new TreeMap<>();
        this.l = true;
        b();
    }

    public void d() {
        this.e = true;
        this.f2857b.getCatalystInstance().removeBridgeIdleDebugListener(this.f2859d);
        this.f2858c.setViewHierarchyUpdateDebugListener(null);
    }

    public double e() {
        if (this.g == this.f) {
            return 0.0d;
        }
        return (((double) g()) * 1.0E9d) / ((double) (this.g - this.f));
    }

    public double f() {
        if (this.g == this.f) {
            return 0.0d;
        }
        return (((double) h()) * 1.0E9d) / ((double) (this.g - this.f));
    }

    public int g() {
        return this.h - 1;
    }

    public int h() {
        return this.k - 1;
    }

    public int i() {
        return (int) ((((double) j()) / 16.9d) + 1.0d);
    }

    public int j() {
        return ((int) (((double) this.g) - ((double) this.f))) / 1000000;
    }

    public a a(long j2) {
        com.facebook.i.a.a.a(this.m, "FPS was not recorded at each frame!");
        Map.Entry<Long, a> floorEntry = this.m.floorEntry(Long.valueOf(j2));
        if (floorEntry == null) {
            return null;
        }
        return floorEntry.getValue();
    }
}
