package com.facebook.imagepipeline.n;

import android.os.SystemClock;
import com.facebook.imagepipeline.j.d;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: JobScheduler */
public class v {

    /* renamed from: a  reason: collision with root package name */
    d f2433a = null;

    /* renamed from: b  reason: collision with root package name */
    int f2434b = 0;

    /* renamed from: c  reason: collision with root package name */
    c f2435c = c.IDLE;

    /* renamed from: d  reason: collision with root package name */
    long f2436d = 0;
    long e = 0;
    private final Executor f;
    private final a g;
    private final Runnable h = new Runnable() {
        /* class com.facebook.imagepipeline.n.v.AnonymousClass1 */

        public void run() {
            v.this.e();
        }
    };
    private final Runnable i = new Runnable() {
        /* class com.facebook.imagepipeline.n.v.AnonymousClass2 */

        public void run() {
            v.this.d();
        }
    };
    private final int j;

    /* compiled from: JobScheduler */
    public interface a {
        void a(d dVar, int i);
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobScheduler */
    public enum c {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    /* access modifiers changed from: package-private */
    /* compiled from: JobScheduler */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private static ScheduledExecutorService f2440a;

        static ScheduledExecutorService a() {
            if (f2440a == null) {
                f2440a = Executors.newSingleThreadScheduledExecutor();
            }
            return f2440a;
        }
    }

    public v(Executor executor, a aVar, int i2) {
        this.f = executor;
        this.g = aVar;
        this.j = i2;
    }

    public void a() {
        d dVar;
        synchronized (this) {
            dVar = this.f2433a;
            this.f2433a = null;
            this.f2434b = 0;
        }
        d.d(dVar);
    }

    public boolean a(d dVar, int i2) {
        d dVar2;
        if (!b(dVar, i2)) {
            return false;
        }
        synchronized (this) {
            dVar2 = this.f2433a;
            this.f2433a = d.a(dVar);
            this.f2434b = i2;
        }
        d.d(dVar2);
        return true;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003c, code lost:
        if (r3 == false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        a(r5 - r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b() {
        /*
            r7 = this;
            long r0 = android.os.SystemClock.uptimeMillis()
            monitor-enter(r7)
            com.facebook.imagepipeline.j.d r2 = r7.f2433a     // Catch:{ all -> 0x0043 }
            int r3 = r7.f2434b     // Catch:{ all -> 0x0043 }
            boolean r2 = b(r2, r3)     // Catch:{ all -> 0x0043 }
            r3 = 0
            if (r2 != 0) goto L_0x0012
            monitor-exit(r7)     // Catch:{ all -> 0x0043 }
            return r3
        L_0x0012:
            int[] r2 = com.facebook.imagepipeline.n.v.AnonymousClass3.f2439a     // Catch:{ all -> 0x0043 }
            com.facebook.imagepipeline.n.v$c r4 = r7.f2435c     // Catch:{ all -> 0x0043 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0043 }
            r2 = r2[r4]     // Catch:{ all -> 0x0043 }
            r4 = 1
            switch(r2) {
                case 1: goto L_0x0026;
                case 2: goto L_0x0039;
                case 3: goto L_0x0021;
                default: goto L_0x0020;
            }     // Catch:{ all -> 0x0043 }
        L_0x0020:
            goto L_0x0039
        L_0x0021:
            com.facebook.imagepipeline.n.v$c r2 = com.facebook.imagepipeline.n.v.c.RUNNING_AND_PENDING     // Catch:{ all -> 0x0043 }
            r7.f2435c = r2     // Catch:{ all -> 0x0043 }
            goto L_0x0039
        L_0x0026:
            long r2 = r7.e     // Catch:{ all -> 0x0043 }
            int r5 = r7.j     // Catch:{ all -> 0x0043 }
            long r5 = (long) r5     // Catch:{ all -> 0x0043 }
            long r2 = r2 + r5
            long r2 = java.lang.Math.max(r2, r0)     // Catch:{ all -> 0x0043 }
            r7.f2436d = r0     // Catch:{ all -> 0x0043 }
            com.facebook.imagepipeline.n.v$c r5 = com.facebook.imagepipeline.n.v.c.QUEUED     // Catch:{ all -> 0x0043 }
            r7.f2435c = r5     // Catch:{ all -> 0x0043 }
            r5 = r2
            r3 = 1
            goto L_0x003b
        L_0x0039:
            r5 = 0
        L_0x003b:
            monitor-exit(r7)     // Catch:{ all -> 0x0043 }
            if (r3 == 0) goto L_0x0042
            long r5 = r5 - r0
            r7.a(r5)
        L_0x0042:
            return r4
        L_0x0043:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
            switch-data {1->0x0026, 2->0x0039, 3->0x0021, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.v.b():boolean");
    }

    private void a(long j2) {
        if (j2 > 0) {
            b.a().schedule(this.i, j2, TimeUnit.MILLISECONDS);
        } else {
            this.i.run();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d() {
        this.f.execute(this.h);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e() {
        d dVar;
        int i2;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            dVar = this.f2433a;
            i2 = this.f2434b;
            this.f2433a = null;
            this.f2434b = 0;
            this.f2435c = c.RUNNING;
            this.e = uptimeMillis;
        }
        try {
            if (b(dVar, i2)) {
                this.g.a(dVar, i2);
            }
        } finally {
            d.d(dVar);
            f();
        }
    }

    private void f() {
        boolean z;
        long j2;
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (this) {
            if (this.f2435c == c.RUNNING_AND_PENDING) {
                j2 = Math.max(this.e + ((long) this.j), uptimeMillis);
                z = true;
                this.f2436d = uptimeMillis;
                this.f2435c = c.QUEUED;
            } else {
                this.f2435c = c.IDLE;
                j2 = 0;
                z = false;
            }
        }
        if (z) {
            a(j2 - uptimeMillis);
        }
    }

    private static boolean b(d dVar, int i2) {
        return b.a(i2) || b.b(i2, 4) || d.e(dVar);
    }

    public synchronized long c() {
        return this.e - this.f2436d;
    }
}
