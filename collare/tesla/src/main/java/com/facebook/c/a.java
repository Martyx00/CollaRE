package com.facebook.c;

import android.util.Pair;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

/* compiled from: AbstractDataSource */
public abstract class a<T> implements c<T> {

    /* renamed from: a  reason: collision with root package name */
    private EnumC0038a f1701a = EnumC0038a.IN_PROGRESS;

    /* renamed from: b  reason: collision with root package name */
    private boolean f1702b = false;

    /* renamed from: c  reason: collision with root package name */
    private T f1703c = null;

    /* renamed from: d  reason: collision with root package name */
    private Throwable f1704d = null;
    private float e = BitmapDescriptorFactory.HUE_RED;
    private final ConcurrentLinkedQueue<Pair<e<T>, Executor>> f = new ConcurrentLinkedQueue<>();

    /* access modifiers changed from: private */
    /* renamed from: com.facebook.c.a$a  reason: collision with other inner class name */
    /* compiled from: AbstractDataSource */
    public enum EnumC0038a {
        IN_PROGRESS,
        SUCCESS,
        FAILURE
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
    }

    protected a() {
    }

    public synchronized boolean a() {
        return this.f1702b;
    }

    @Override // com.facebook.c.c
    public synchronized boolean b() {
        return this.f1701a != EnumC0038a.IN_PROGRESS;
    }

    @Override // com.facebook.c.c
    public synchronized boolean c() {
        return this.f1703c != null;
    }

    @Override // com.facebook.c.c
    public synchronized T d() {
        return this.f1703c;
    }

    public synchronized boolean e() {
        return this.f1701a == EnumC0038a.FAILURE;
    }

    @Override // com.facebook.c.c
    public synchronized Throwable f() {
        return this.f1704d;
    }

    @Override // com.facebook.c.c
    public synchronized float g() {
        return this.e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        a((java.lang.Object) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001a, code lost:
        if (b() != false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        j();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001f, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r3.f.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0025, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0026, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r1 == null) goto L_0x0016;
     */
    @Override // com.facebook.c.c
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean h() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.f1702b     // Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            return r0
        L_0x0008:
            r0 = 1
            r3.f1702b = r0     // Catch:{ all -> 0x002a }
            T r1 = r3.f1703c     // Catch:{ all -> 0x002a }
            r2 = 0
            r3.f1703c = r2     // Catch:{ all -> 0x002a }
            monitor-exit(r3)     // Catch:{ all -> 0x002a }
            if (r1 == 0) goto L_0x0016
            r3.a(r1)
        L_0x0016:
            boolean r1 = r3.b()
            if (r1 != 0) goto L_0x001f
            r3.j()
        L_0x001f:
            monitor-enter(r3)
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<com.facebook.c.e<T>, java.util.concurrent.Executor>> r1 = r3.f     // Catch:{ all -> 0x0027 }
            r1.clear()     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            return r0
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            throw r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.c.a.h():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0035, code lost:
        a(r3, r4, e(), k());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    @Override // com.facebook.c.c
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.facebook.c.e<T> r3, java.util.concurrent.Executor r4) {
        /*
            r2 = this;
            com.facebook.common.d.i.a(r3)
            com.facebook.common.d.i.a(r4)
            monitor-enter(r2)
            boolean r0 = r2.f1702b     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r2)     // Catch:{ all -> 0x0041 }
            return
        L_0x000d:
            com.facebook.c.a$a r0 = r2.f1701a     // Catch:{ all -> 0x0041 }
            com.facebook.c.a$a r1 = com.facebook.c.a.EnumC0038a.IN_PROGRESS     // Catch:{ all -> 0x0041 }
            if (r0 != r1) goto L_0x001c
            java.util.concurrent.ConcurrentLinkedQueue<android.util.Pair<com.facebook.c.e<T>, java.util.concurrent.Executor>> r0 = r2.f     // Catch:{ all -> 0x0041 }
            android.util.Pair r1 = android.util.Pair.create(r3, r4)     // Catch:{ all -> 0x0041 }
            r0.add(r1)     // Catch:{ all -> 0x0041 }
        L_0x001c:
            boolean r0 = r2.c()     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0031
            boolean r0 = r2.b()     // Catch:{ all -> 0x0041 }
            if (r0 != 0) goto L_0x0031
            boolean r0 = r2.k()     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x002f
            goto L_0x0031
        L_0x002f:
            r0 = 0
            goto L_0x0032
        L_0x0031:
            r0 = 1
        L_0x0032:
            monitor-exit(r2)     // Catch:{ all -> 0x0041 }
            if (r0 == 0) goto L_0x0040
            boolean r0 = r2.e()
            boolean r1 = r2.k()
            r2.a(r3, r4, r0, r1)
        L_0x0040:
            return
        L_0x0041:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.c.a.a(com.facebook.c.e, java.util.concurrent.Executor):void");
    }

    private void j() {
        boolean e2 = e();
        boolean k = k();
        Iterator<Pair<e<T>, Executor>> it = this.f.iterator();
        while (it.hasNext()) {
            Pair<e<T>, Executor> next = it.next();
            a((e) next.first, (Executor) next.second, e2, k);
        }
    }

    private void a(final e<T> eVar, Executor executor, final boolean z, final boolean z2) {
        executor.execute(new Runnable() {
            /* class com.facebook.c.a.AnonymousClass1 */

            public void run() {
                if (z) {
                    eVar.b(a.this);
                } else if (z2) {
                    eVar.c(a.this);
                } else {
                    eVar.a(a.this);
                }
            }
        });
    }

    private synchronized boolean k() {
        return a() && !b();
    }

    /* access modifiers changed from: protected */
    public boolean a(T t, boolean z) {
        boolean b2 = b(t, z);
        if (b2) {
            j();
        }
        return b2;
    }

    /* access modifiers changed from: protected */
    public boolean a(Throwable th) {
        boolean b2 = b(th);
        if (b2) {
            j();
        }
        return b2;
    }

    /* access modifiers changed from: protected */
    public boolean a(float f2) {
        boolean b2 = b(f2);
        if (b2) {
            i();
        }
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0027, code lost:
        if (r4 == null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0029, code lost:
        a((java.lang.Object) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x002c, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0034, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(T r4, boolean r5) {
        /*
            r3 = this;
            r0 = 0
            monitor-enter(r3)     // Catch:{ all -> 0x003c }
            boolean r1 = r3.f1702b     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x002d
            com.facebook.c.a$a r1 = r3.f1701a     // Catch:{ all -> 0x0039 }
            com.facebook.c.a$a r2 = com.facebook.c.a.EnumC0038a.IN_PROGRESS     // Catch:{ all -> 0x0039 }
            if (r1 == r2) goto L_0x000d
            goto L_0x002d
        L_0x000d:
            if (r5 == 0) goto L_0x0017
            com.facebook.c.a$a r5 = com.facebook.c.a.EnumC0038a.SUCCESS     // Catch:{ all -> 0x0039 }
            r3.f1701a = r5     // Catch:{ all -> 0x0039 }
            r5 = 1065353216(0x3f800000, float:1.0)
            r3.e = r5     // Catch:{ all -> 0x0039 }
        L_0x0017:
            T r5 = r3.f1703c     // Catch:{ all -> 0x0039 }
            if (r5 == r4) goto L_0x0024
            T r5 = r3.f1703c     // Catch:{ all -> 0x0039 }
            r3.f1703c = r4     // Catch:{ all -> 0x0021 }
            r4 = r5
            goto L_0x0025
        L_0x0021:
            r4 = move-exception
            r0 = r5
            goto L_0x003a
        L_0x0024:
            r4 = r0
        L_0x0025:
            r5 = 1
            monitor-exit(r3)     // Catch:{ all -> 0x0035 }
            if (r4 == 0) goto L_0x002c
            r3.a(r4)
        L_0x002c:
            return r5
        L_0x002d:
            r5 = 0
            monitor-exit(r3)
            if (r4 == 0) goto L_0x0034
            r3.a(r4)
        L_0x0034:
            return r5
        L_0x0035:
            r5 = move-exception
            r0 = r4
            r4 = r5
            goto L_0x003a
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            monitor-exit(r3)
            throw r4
        L_0x003c:
            r4 = move-exception
            if (r0 == 0) goto L_0x0042
            r3.a(r0)
        L_0x0042:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.c.a.b(java.lang.Object, boolean):boolean");
    }

    private synchronized boolean b(Throwable th) {
        if (!this.f1702b) {
            if (this.f1701a == EnumC0038a.IN_PROGRESS) {
                this.f1701a = EnumC0038a.FAILURE;
                this.f1704d = th;
                return true;
            }
        }
        return false;
    }

    private synchronized boolean b(float f2) {
        if (!this.f1702b) {
            if (this.f1701a == EnumC0038a.IN_PROGRESS) {
                if (f2 < this.e) {
                    return false;
                }
                this.e = f2;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void i() {
        Iterator<Pair<e<T>, Executor>> it = this.f.iterator();
        while (it.hasNext()) {
            Pair<e<T>, Executor> next = it.next();
            final e eVar = (e) next.first;
            ((Executor) next.second).execute(new Runnable() {
                /* class com.facebook.c.a.AnonymousClass2 */

                public void run() {
                    eVar.d(a.this);
                }
            });
        }
    }
}
