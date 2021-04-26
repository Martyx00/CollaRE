package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.d.i;
import com.facebook.common.d.k;
import com.facebook.common.g.e;
import com.google.android.gms.common.api.Api;
import java.util.Set;

/* compiled from: BasePool */
public abstract class a<V> implements e<V> {

    /* renamed from: a  reason: collision with root package name */
    final com.facebook.common.g.c f2190a;

    /* renamed from: b  reason: collision with root package name */
    final t f2191b;

    /* renamed from: c  reason: collision with root package name */
    final SparseArray<e<V>> f2192c;

    /* renamed from: d  reason: collision with root package name */
    final Set<V> f2193d;
    final C0048a e;
    final C0048a f;
    private final Class<?> g = getClass();
    private boolean h;
    private final u i;

    /* access modifiers changed from: protected */
    public abstract V b(int i2);

    /* access modifiers changed from: protected */
    public abstract void b(V v);

    /* access modifiers changed from: protected */
    public abstract int c(int i2);

    /* access modifiers changed from: protected */
    public abstract int c(V v);

    /* access modifiers changed from: protected */
    public abstract int d(int i2);

    public a(com.facebook.common.g.c cVar, t tVar, u uVar) {
        this.f2190a = (com.facebook.common.g.c) i.a(cVar);
        this.f2191b = (t) i.a(tVar);
        this.i = (u) i.a(uVar);
        this.f2192c = new SparseArray<>();
        if (this.f2191b.f) {
            e();
        } else {
            a(new SparseIntArray(0));
        }
        this.f2193d = k.a();
        this.f = new C0048a();
        this.e = new C0048a();
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.f2190a.a(this);
        this.i.a(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0 = b(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006e, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006f, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r5.e.b(r2);
        r4 = f(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
        if (r4 != null) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007b, code lost:
        r4.f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007f, code lost:
        com.facebook.common.d.n.a(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0082, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        com.facebook.common.d.i.b(r5.f2193d.add(r0));
        b();
        r5.i.b(r2);
        f();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009b, code lost:
        if (com.facebook.common.e.a.a(2) == false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009d, code lost:
        com.facebook.common.e.a.a(r5.g, "get (alloc) (object, size) = (%x, %s)", java.lang.Integer.valueOf(java.lang.System.identityHashCode(r0)), java.lang.Integer.valueOf(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b1, code lost:
        return r0;
     */
    @Override // com.facebook.common.g.e
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V a(int r6) {
        /*
        // Method dump skipped, instructions count: 205
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.a.a(int):java.lang.Object");
    }

    @Override // com.facebook.common.h.c, com.facebook.common.g.e
    public void a(V v) {
        i.a(v);
        int c2 = c(v);
        int d2 = d(c2);
        synchronized (this) {
            e<V> i2 = i(c2);
            if (!this.f2193d.remove(v)) {
                com.facebook.common.e.a.c(this.g, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(c2));
                b((Object) v);
                this.i.c(d2);
            } else {
                if (i2 != null && !i2.a() && !c()) {
                    if (d(v)) {
                        i2.a(v);
                        this.f.a(d2);
                        this.e.b(d2);
                        this.i.d(d2);
                        if (com.facebook.common.e.a.a(2)) {
                            com.facebook.common.e.a.a(this.g, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(c2));
                        }
                    }
                }
                if (i2 != null) {
                    i2.f();
                }
                if (com.facebook.common.e.a.a(2)) {
                    com.facebook.common.e.a.a(this.g, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(c2));
                }
                b((Object) v);
                this.e.b(d2);
                this.i.c(d2);
            }
            f();
        }
    }

    /* access modifiers changed from: protected */
    public boolean d(V v) {
        i.a(v);
        return true;
    }

    private synchronized void d() {
        boolean z;
        if (c()) {
            if (this.f.f2195b != 0) {
                z = false;
                i.b(z);
            }
        }
        z = true;
        i.b(z);
    }

    private synchronized void a(SparseIntArray sparseIntArray) {
        i.a(sparseIntArray);
        this.f2192c.clear();
        SparseIntArray sparseIntArray2 = this.f2191b.f2237c;
        if (sparseIntArray2 != null) {
            for (int i2 = 0; i2 < sparseIntArray2.size(); i2++) {
                int keyAt = sparseIntArray2.keyAt(i2);
                this.f2192c.put(keyAt, new e<>(d(keyAt), sparseIntArray2.valueAt(i2), sparseIntArray.get(keyAt, 0), this.f2191b.f));
            }
            this.h = false;
        } else {
            this.h = true;
        }
    }

    private synchronized void e() {
        SparseIntArray sparseIntArray = this.f2191b.f2237c;
        if (sparseIntArray != null) {
            b(sparseIntArray);
            this.h = false;
        } else {
            this.h = true;
        }
    }

    private void b(SparseIntArray sparseIntArray) {
        this.f2192c.clear();
        for (int i2 = 0; i2 < sparseIntArray.size(); i2++) {
            int keyAt = sparseIntArray.keyAt(i2);
            this.f2192c.put(keyAt, new e<>(d(keyAt), sparseIntArray.valueAt(i2), 0, this.f2191b.f));
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void b() {
        if (c()) {
            e(this.f2191b.f2236b);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void e(int i2) {
        int min = Math.min((this.e.f2195b + this.f.f2195b) - i2, this.f.f2195b);
        if (min > 0) {
            if (com.facebook.common.e.a.a(2)) {
                com.facebook.common.e.a.a(this.g, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i2), Integer.valueOf(this.e.f2195b + this.f.f2195b), Integer.valueOf(min));
            }
            f();
            for (int i3 = 0; i3 < this.f2192c.size() && min > 0; i3++) {
                e<V> valueAt = this.f2192c.valueAt(i3);
                while (min > 0) {
                    V d2 = valueAt.d();
                    if (d2 == null) {
                        break;
                    }
                    b((Object) d2);
                    min -= valueAt.f2205a;
                    this.f.b(valueAt.f2205a);
                }
            }
            f();
            if (com.facebook.common.e.a.a(2)) {
                com.facebook.common.e.a.a(this.g, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(i2), Integer.valueOf(this.e.f2195b + this.f.f2195b));
            }
        }
    }

    private synchronized e<V> i(int i2) {
        return this.f2192c.get(i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized e<V> f(int i2) {
        e<V> eVar = this.f2192c.get(i2);
        if (eVar == null) {
            if (this.h) {
                if (com.facebook.common.e.a.a(2)) {
                    com.facebook.common.e.a.a(this.g, "creating new bucket %s", Integer.valueOf(i2));
                }
                e<V> g2 = g(i2);
                this.f2192c.put(i2, g2);
                return g2;
            }
        }
        return eVar;
    }

    /* access modifiers changed from: package-private */
    public e<V> g(int i2) {
        return new e<>(d(i2), Api.BaseClientBuilder.API_PRIORITY_OTHER, 0, this.f2191b.f);
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean c() {
        boolean z;
        z = this.e.f2195b + this.f.f2195b > this.f2191b.f2236b;
        if (z) {
            this.i.b();
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean h(int i2) {
        int i3 = this.f2191b.f2235a;
        if (i2 > i3 - this.e.f2195b) {
            this.i.c();
            return false;
        }
        int i4 = this.f2191b.f2236b;
        if (i2 > i4 - (this.e.f2195b + this.f.f2195b)) {
            e(i4 - i2);
        }
        if (i2 <= i3 - (this.e.f2195b + this.f.f2195b)) {
            return true;
        }
        this.i.c();
        return false;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void f() {
        if (com.facebook.common.e.a.a(2)) {
            com.facebook.common.e.a.a(this.g, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.e.f2194a), Integer.valueOf(this.e.f2195b), Integer.valueOf(this.f.f2194a), Integer.valueOf(this.f.f2195b));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.facebook.imagepipeline.memory.a$a  reason: collision with other inner class name */
    /* compiled from: BasePool */
    public static class C0048a {

        /* renamed from: a  reason: collision with root package name */
        int f2194a;

        /* renamed from: b  reason: collision with root package name */
        int f2195b;

        C0048a() {
        }

        public void a(int i) {
            this.f2194a++;
            this.f2195b += i;
        }

        public void b(int i) {
            int i2;
            int i3 = this.f2195b;
            if (i3 < i || (i2 = this.f2194a) <= 0) {
                com.facebook.common.e.a.d("com.facebook.imagepipeline.memory.BasePool.Counter", "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i), Integer.valueOf(this.f2195b), Integer.valueOf(this.f2194a));
                return;
            }
            this.f2194a = i2 - 1;
            this.f2195b = i3 - i;
        }
    }

    /* compiled from: BasePool */
    public static class b extends RuntimeException {
        public b(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    /* compiled from: BasePool */
    public static class c extends RuntimeException {
        public c(int i, int i2, int i3, int i4) {
            super("Pool hard cap violation? Hard cap = " + i + " Used size = " + i2 + " Free size = " + i3 + " Request size = " + i4);
        }
    }
}
