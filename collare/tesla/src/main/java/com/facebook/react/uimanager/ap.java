package com.facebook.react.uimanager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.modules.core.e;
import com.facebook.react.uimanager.aj;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: UIViewOperationQueue */
public class ap {

    /* renamed from: a  reason: collision with root package name */
    private final int[] f3099a = new int[4];

    /* renamed from: b  reason: collision with root package name */
    private final k f3100b;

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.react.a.d f3101c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f3102d = new Object();
    private final Object e = new Object();
    private final h f;
    private final ReactApplicationContext g;
    private ArrayList<u> h = new ArrayList<>();
    private ArrayList<Runnable> i = new ArrayList<>();
    private ArrayDeque<u> j = new ArrayDeque<>();
    private com.facebook.react.uimanager.b.a k;
    private boolean l = false;
    private boolean m = false;
    private boolean n = false;
    private long o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    private long u;
    private long v;

    /* compiled from: UIViewOperationQueue */
    public interface u {
        void a();
    }

    /* compiled from: UIViewOperationQueue */
    private abstract class y implements u {

        /* renamed from: b  reason: collision with root package name */
        public int f3156b;

        public y(int i) {
            this.f3156b = i;
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class p extends y {
        public p(int i) {
            super(i);
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.c(this.f3156b);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class w extends y {

        /* renamed from: d  reason: collision with root package name */
        private final y f3153d;

        private w(int i, y yVar) {
            super(i);
            this.f3153d = yVar;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3153d);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class v extends y {

        /* renamed from: d  reason: collision with root package name */
        private final int f3151d;
        private final int e;
        private final int f;
        private final int g;
        private final int h;

        public v(int i, int i2, int i3, int i4, int i5, int i6) {
            super(i2);
            this.f3151d = i;
            this.e = i3;
            this.f = i4;
            this.g = i5;
            this.h = i6;
            com.facebook.systrace.a.d(0, "updateLayout", this.f3156b);
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            com.facebook.systrace.a.e(0, "updateLayout", this.f3156b);
            ap.this.f3100b.a(this.f3151d, this.f3156b, this.e, this.f, this.g, this.h);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class e extends y {

        /* renamed from: d  reason: collision with root package name */
        private final af f3117d;
        private final String e;
        private final y f;

        public e(af afVar, int i, String str, y yVar) {
            super(i);
            this.f3117d = afVar;
            this.e = str;
            this.f = yVar;
            com.facebook.systrace.a.d(0, "createView", this.f3156b);
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            com.facebook.systrace.a.e(0, "createView", this.f3156b);
            ap.this.f3100b.a(this.f3117d, this.f3156b, this.e, this.f);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class k extends y {

        /* renamed from: d  reason: collision with root package name */
        private final int[] f3131d;
        private final aq[] e;
        private final int[] f;

        public k(int i, int[] iArr, aq[] aqVarArr, int[] iArr2) {
            super(i);
            this.f3131d = iArr;
            this.e = aqVarArr;
            this.f = iArr2;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3131d, this.e, this.f);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: UIViewOperationQueue */
    public final class x extends y {

        /* renamed from: d  reason: collision with root package name */
        private final Object f3155d;

        public x(int i, Object obj) {
            super(i);
            this.f3155d = obj;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3155d);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class c extends y {

        /* renamed from: d  reason: collision with root package name */
        private final int f3113d;
        private final boolean e;
        private final boolean f;

        public c(int i, int i2, boolean z, boolean z2) {
            super(i);
            this.f3113d = i2;
            this.f = z;
            this.e = z2;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            if (!this.f) {
                ap.this.f3100b.a(this.f3156b, this.f3113d, this.e);
            } else {
                ap.this.f3100b.b();
            }
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class g extends y {

        /* renamed from: d  reason: collision with root package name */
        private final int f3120d;
        private final ReadableArray e;

        public g(int i, int i2, ReadableArray readableArray) {
            super(i);
            this.f3120d = i2;
            this.e = readableArray;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3120d, this.e);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class s extends y {

        /* renamed from: d  reason: collision with root package name */
        private final ReadableArray f3147d;
        private final Callback e;
        private final Callback f;

        public s(int i, ReadableArray readableArray, Callback callback, Callback callback2) {
            super(i);
            this.f3147d = readableArray;
            this.e = callback;
            this.f = callback2;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3147d, this.f, this.e);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class f implements u {
        private f() {
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.d();
        }
    }

    /* compiled from: UIViewOperationQueue */
    private static abstract class b implements u {

        /* renamed from: b  reason: collision with root package name */
        protected final int f3111b;

        public b(int i) {
            this.f3111b = i;
        }
    }

    /* compiled from: UIViewOperationQueue */
    private class n extends b {

        /* renamed from: c  reason: collision with root package name */
        private final com.facebook.react.a.a f3139c;

        private n(com.facebook.react.a.a aVar) {
            super(aVar.c());
            this.f3139c = aVar;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3101c.a(this.f3139c);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private class a extends b {

        /* renamed from: c  reason: collision with root package name */
        private final int f3109c;

        /* renamed from: d  reason: collision with root package name */
        private final Callback f3110d;

        private a(int i, int i2, Callback callback) {
            super(i2);
            this.f3109c = i;
            this.f3110d = callback;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            com.facebook.react.a.a a2 = ap.this.f3101c.a(this.f3111b);
            if (a2 != null) {
                ap.this.f3100b.a(this.f3109c, a2, this.f3110d);
                return;
            }
            throw new f("Animation with id " + this.f3111b + " was not found");
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class o extends b {
        private o(int i) {
            super(i);
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            com.facebook.react.a.a a2 = ap.this.f3101c.a(this.f3111b);
            if (a2 != null) {
                a2.b();
            }
        }
    }

    /* compiled from: UIViewOperationQueue */
    private class r implements u {

        /* renamed from: b  reason: collision with root package name */
        private final boolean f3145b;

        private r(boolean z) {
            this.f3145b = z;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3145b);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private class d implements u {

        /* renamed from: b  reason: collision with root package name */
        private final ReadableMap f3115b;

        private d(ReadableMap readableMap) {
            this.f3115b = readableMap;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3115b);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class m implements u {

        /* renamed from: b  reason: collision with root package name */
        private final int f3136b;

        /* renamed from: c  reason: collision with root package name */
        private final Callback f3137c;

        private m(int i, Callback callback) {
            this.f3136b = i;
            this.f3137c = callback;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            try {
                ap.this.f3100b.a(this.f3136b, ap.this.f3099a);
                float d2 = o.d((float) ap.this.f3099a[0]);
                float d3 = o.d((float) ap.this.f3099a[1]);
                float d4 = o.d((float) ap.this.f3099a[2]);
                float d5 = o.d((float) ap.this.f3099a[3]);
                this.f3137c.invoke(0, 0, Float.valueOf(d4), Float.valueOf(d5), Float.valueOf(d2), Float.valueOf(d3));
            } catch (m unused) {
                this.f3137c.invoke(new Object[0]);
            }
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class l implements u {

        /* renamed from: b  reason: collision with root package name */
        private final int f3133b;

        /* renamed from: c  reason: collision with root package name */
        private final Callback f3134c;

        private l(int i, Callback callback) {
            this.f3133b = i;
            this.f3134c = callback;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            try {
                ap.this.f3100b.b(this.f3133b, ap.this.f3099a);
                float d2 = o.d((float) ap.this.f3099a[0]);
                float d3 = o.d((float) ap.this.f3099a[1]);
                float d4 = o.d((float) ap.this.f3099a[2]);
                float d5 = o.d((float) ap.this.f3099a[3]);
                this.f3134c.invoke(Float.valueOf(d2), Float.valueOf(d3), Float.valueOf(d4), Float.valueOf(d5));
            } catch (m unused) {
                this.f3134c.invoke(new Object[0]);
            }
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class i implements u {

        /* renamed from: b  reason: collision with root package name */
        private final int f3124b;

        /* renamed from: c  reason: collision with root package name */
        private final float f3125c;

        /* renamed from: d  reason: collision with root package name */
        private final float f3126d;
        private final Callback e;

        private i(int i, float f, float f2, Callback callback) {
            this.f3124b = i;
            this.f3125c = f;
            this.f3126d = f2;
            this.e = callback;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            try {
                ap.this.f3100b.a(this.f3124b, ap.this.f3099a);
                float f = (float) ap.this.f3099a[0];
                float f2 = (float) ap.this.f3099a[1];
                int a2 = ap.this.f3100b.a(this.f3124b, this.f3125c, this.f3126d);
                try {
                    ap.this.f3100b.a(a2, ap.this.f3099a);
                    float d2 = o.d(((float) ap.this.f3099a[0]) - f);
                    float d3 = o.d(((float) ap.this.f3099a[1]) - f2);
                    float d4 = o.d((float) ap.this.f3099a[2]);
                    float d5 = o.d((float) ap.this.f3099a[3]);
                    this.e.invoke(Integer.valueOf(a2), Float.valueOf(d2), Float.valueOf(d3), Float.valueOf(d4), Float.valueOf(d5));
                } catch (f unused) {
                    this.e.invoke(new Object[0]);
                }
            } catch (f unused2) {
                this.e.invoke(new Object[0]);
            }
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class j implements u {

        /* renamed from: b  reason: collision with root package name */
        private final w f3128b;

        /* renamed from: c  reason: collision with root package name */
        private final aj.a f3129c;

        private j(w wVar, aj.a aVar) {
            this.f3128b = wVar;
            this.f3129c = aVar;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            this.f3129c.a(this.f3128b);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private class t implements u {

        /* renamed from: b  reason: collision with root package name */
        private final ai f3149b;

        public t(ai aiVar) {
            this.f3149b = aiVar;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            this.f3149b.a(ap.this.f3100b);
        }
    }

    /* compiled from: UIViewOperationQueue */
    private final class q extends y {

        /* renamed from: d  reason: collision with root package name */
        private final int f3143d;

        private q(int i, int i2) {
            super(i);
            this.f3143d = i2;
        }

        @Override // com.facebook.react.uimanager.ap.u
        public void a() {
            ap.this.f3100b.a(this.f3156b, this.f3143d);
        }
    }

    public ap(ReactApplicationContext reactApplicationContext, k kVar, int i2) {
        this.f3100b = kVar;
        this.f3101c = kVar.a();
        this.f = new h(reactApplicationContext, i2 == -1 ? 8 : i2);
        this.g = reactApplicationContext;
    }

    /* access modifiers changed from: package-private */
    public k a() {
        return this.f3100b;
    }

    public void a(com.facebook.react.uimanager.b.a aVar) {
        this.k = aVar;
    }

    public void b() {
        this.n = true;
        this.p = 0;
    }

    public Map<String, Long> c() {
        HashMap hashMap = new HashMap();
        hashMap.put("CommitStartTime", Long.valueOf(this.p));
        hashMap.put("LayoutTime", Long.valueOf(this.q));
        hashMap.put("DispatchViewUpdatesTime", Long.valueOf(this.r));
        hashMap.put("RunStartTime", Long.valueOf(this.s));
        hashMap.put("BatchedExecutionTime", Long.valueOf(this.t));
        hashMap.put("NonBatchedExecutionTime", Long.valueOf(this.u));
        hashMap.put("NativeModulesThreadCpuTime", Long.valueOf(this.v));
        return hashMap;
    }

    public boolean d() {
        return this.h.isEmpty();
    }

    public void a(int i2, SizeMonitoringFrameLayout sizeMonitoringFrameLayout, af afVar) {
        this.f3100b.a(i2, sizeMonitoringFrameLayout, afVar);
    }

    public void a(int i2) {
        this.h.add(new p(i2));
    }

    public void a(int i2, int i3, boolean z) {
        this.h.add(new c(i2, i3, false, z));
    }

    public void e() {
        this.h.add(new c(0, 0, true, false));
    }

    public void a(int i2, int i3, ReadableArray readableArray) {
        this.h.add(new g(i2, i3, readableArray));
    }

    public void a(int i2, Object obj) {
        this.h.add(new x(i2, obj));
    }

    public void a(int i2, ReadableArray readableArray, Callback callback, Callback callback2) {
        this.h.add(new s(i2, readableArray, callback, callback2));
    }

    public void f() {
        this.h.add(new f());
    }

    public void a(af afVar, int i2, String str, y yVar) {
        synchronized (this.e) {
            this.j.addLast(new e(afVar, i2, str, yVar));
        }
    }

    public void a(int i2, String str, y yVar) {
        this.h.add(new w(i2, yVar));
    }

    public void a(int i2, int i3, int i4, int i5, int i6, int i7) {
        this.h.add(new v(i2, i3, i4, i5, i6, i7));
    }

    public void a(int i2, int[] iArr, aq[] aqVarArr, int[] iArr2) {
        this.h.add(new k(i2, iArr, aqVarArr, iArr2));
    }

    public void a(com.facebook.react.a.a aVar) {
        this.h.add(new n(aVar));
    }

    public void a(int i2, int i3, Callback callback) {
        this.h.add(new a(i2, i3, callback));
    }

    public void b(int i2) {
        this.h.add(new o(i2));
    }

    public void a(boolean z) {
        this.h.add(new r(z));
    }

    public void a(ReadableMap readableMap, Callback callback, Callback callback2) {
        this.h.add(new d(readableMap));
    }

    public void a(int i2, Callback callback) {
        this.h.add(new m(i2, callback));
    }

    public void b(int i2, Callback callback) {
        this.h.add(new l(i2, callback));
    }

    public void a(int i2, float f2, float f3, Callback callback) {
        this.h.add(new i(i2, f2, f3, callback));
    }

    public void a(int i2, int i3) {
        this.h.add(new q(i2, i3));
    }

    public void a(w wVar, aj.a aVar) {
        this.h.add(new j(wVar, aVar));
    }

    public void a(ai aiVar) {
        this.h.add(new t(aiVar));
    }

    public void b(ai aiVar) {
        this.h.add(0, new t(aiVar));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a2, code lost:
        r0 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final int r19, final long r20, final long r22) {
        /*
        // Method dump skipped, instructions count: 170
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ap.a(int, long, long):void");
    }

    /* access modifiers changed from: package-private */
    public void g() {
        this.l = true;
        com.facebook.react.modules.core.e.b().a(e.a.DISPATCH_UI, this.f);
    }

    /* access modifiers changed from: package-private */
    public void h() {
        this.l = false;
        com.facebook.react.modules.core.e.b().b(e.a.DISPATCH_UI, this.f);
        i();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r2 = android.os.SystemClock.uptimeMillis();
        r0 = r1.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r0.hasNext() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        r0.next().run();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003d, code lost:
        if (r12.n == false) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        r12.t = android.os.SystemClock.uptimeMillis() - r2;
        r12.u = r12.o;
        r12.n = false;
        com.facebook.systrace.a.a(0, "batchedExecutionTime", 0, 1000000 * r2);
        com.facebook.systrace.a.b(0, "batchedExecutionTime", 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005f, code lost:
        r12.o = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void i() {
        /*
        // Method dump skipped, instructions count: 103
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ap.i():void");
    }

    /* compiled from: UIViewOperationQueue */
    private class h extends e {

        /* renamed from: b  reason: collision with root package name */
        private final int f3122b;

        private h(ReactContext reactContext, int i) {
            super(reactContext);
            this.f3122b = i;
        }

        /* JADX INFO: finally extract failed */
        @Override // com.facebook.react.uimanager.e
        public void a(long j) {
            if (ap.this.m) {
                com.facebook.common.e.a.c("ReactNative", "Not flushing pending UI operations because of previously thrown Exception");
                return;
            }
            com.facebook.systrace.a.a(0, "dispatchNonBatchedUIOperations");
            try {
                c(j);
                com.facebook.systrace.a.b(0);
                ap.this.i();
                com.facebook.react.modules.core.e.b().a(e.a.DISPATCH_UI, this);
            } catch (Throwable th) {
                com.facebook.systrace.a.b(0);
                throw th;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            r2 = android.os.SystemClock.uptimeMillis();
            r1.a();
            r8.f3121a.o += android.os.SystemClock.uptimeMillis() - r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x004f, code lost:
            r9 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0050, code lost:
            r8.f3121a.m = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
            throw r9;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void c(long r9) {
            /*
                r8 = this;
            L_0x0000:
                r0 = 16
                long r2 = java.lang.System.nanoTime()
                long r2 = r2 - r9
                r4 = 1000000(0xf4240, double:4.940656E-318)
                long r2 = r2 / r4
                long r0 = r0 - r2
                int r2 = r8.f3122b
                long r2 = (long) r2
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 >= 0) goto L_0x0014
                goto L_0x0028
            L_0x0014:
                com.facebook.react.uimanager.ap r0 = com.facebook.react.uimanager.ap.this
                java.lang.Object r0 = com.facebook.react.uimanager.ap.k(r0)
                monitor-enter(r0)
                com.facebook.react.uimanager.ap r1 = com.facebook.react.uimanager.ap.this     // Catch:{ all -> 0x0057 }
                java.util.ArrayDeque r1 = com.facebook.react.uimanager.ap.l(r1)     // Catch:{ all -> 0x0057 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0057 }
                if (r1 == 0) goto L_0x0029
                monitor-exit(r0)     // Catch:{ all -> 0x0057 }
            L_0x0028:
                return
            L_0x0029:
                com.facebook.react.uimanager.ap r1 = com.facebook.react.uimanager.ap.this     // Catch:{ all -> 0x0057 }
                java.util.ArrayDeque r1 = com.facebook.react.uimanager.ap.l(r1)     // Catch:{ all -> 0x0057 }
                java.lang.Object r1 = r1.pollFirst()     // Catch:{ all -> 0x0057 }
                com.facebook.react.uimanager.ap$u r1 = (com.facebook.react.uimanager.ap.u) r1     // Catch:{ all -> 0x0057 }
                monitor-exit(r0)     // Catch:{ all -> 0x0057 }
                long r2 = android.os.SystemClock.uptimeMillis()     // Catch:{ Exception -> 0x004f }
                r1.a()     // Catch:{ Exception -> 0x004f }
                com.facebook.react.uimanager.ap r0 = com.facebook.react.uimanager.ap.this     // Catch:{ Exception -> 0x004f }
                com.facebook.react.uimanager.ap r1 = com.facebook.react.uimanager.ap.this     // Catch:{ Exception -> 0x004f }
                long r4 = com.facebook.react.uimanager.ap.m(r1)     // Catch:{ Exception -> 0x004f }
                long r6 = android.os.SystemClock.uptimeMillis()     // Catch:{ Exception -> 0x004f }
                long r6 = r6 - r2
                long r4 = r4 + r6
                com.facebook.react.uimanager.ap.f(r0, r4)     // Catch:{ Exception -> 0x004f }
                goto L_0x0000
            L_0x004f:
                r9 = move-exception
                com.facebook.react.uimanager.ap r10 = com.facebook.react.uimanager.ap.this
                r0 = 1
                com.facebook.react.uimanager.ap.a(r10, r0)
                throw r9
            L_0x0057:
                r9 = move-exception
                monitor-exit(r0)
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.ap.h.c(long):void");
        }
    }
}
