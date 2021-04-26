package com.facebook.b.b;

import android.content.Context;
import com.facebook.b.a.a;
import com.facebook.b.a.c;
import com.facebook.b.a.d;
import com.facebook.b.a.j;
import com.facebook.b.b.d;
import com.facebook.common.i.a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: DiskStorageCache */
public class e implements i, com.facebook.common.a.a {

    /* renamed from: b  reason: collision with root package name */
    private static final Class<?> f1680b = e.class;

    /* renamed from: c  reason: collision with root package name */
    private static final long f1681c = TimeUnit.HOURS.toMillis(2);

    /* renamed from: d  reason: collision with root package name */
    private static final long f1682d = TimeUnit.MINUTES.toMillis(30);

    /* renamed from: a  reason: collision with root package name */
    final Set<String> f1683a;
    private final long e;
    private final long f;
    private final CountDownLatch g;
    private long h;
    private final c i;
    private long j;
    private final long k;
    private final com.facebook.common.i.a l;
    private final d m;
    private final h n;
    private final com.facebook.b.a.a o;
    private final boolean p;
    private final a q;
    private final com.facebook.common.time.a r;
    private final Object s = new Object();
    private boolean t;

    /* access modifiers changed from: package-private */
    /* compiled from: DiskStorageCache */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private boolean f1685a = false;

        /* renamed from: b  reason: collision with root package name */
        private long f1686b = -1;

        /* renamed from: c  reason: collision with root package name */
        private long f1687c = -1;

        a() {
        }

        public synchronized boolean a() {
            return this.f1685a;
        }

        public synchronized void b() {
            this.f1685a = false;
            this.f1687c = -1;
            this.f1686b = -1;
        }

        public synchronized void a(long j, long j2) {
            this.f1687c = j2;
            this.f1686b = j;
            this.f1685a = true;
        }

        public synchronized void b(long j, long j2) {
            if (this.f1685a) {
                this.f1686b += j;
                this.f1687c += j2;
            }
        }

        public synchronized long c() {
            return this.f1686b;
        }

        public synchronized long d() {
            return this.f1687c;
        }
    }

    /* compiled from: DiskStorageCache */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final long f1688a;

        /* renamed from: b  reason: collision with root package name */
        public final long f1689b;

        /* renamed from: c  reason: collision with root package name */
        public final long f1690c;

        public b(long j, long j2, long j3) {
            this.f1688a = j;
            this.f1689b = j2;
            this.f1690c = j3;
        }
    }

    public e(d dVar, h hVar, b bVar, c cVar, com.facebook.b.a.a aVar, com.facebook.common.a.b bVar2, Context context, Executor executor, boolean z) {
        this.e = bVar.f1689b;
        this.f = bVar.f1690c;
        this.h = bVar.f1690c;
        this.l = com.facebook.common.i.a.a();
        this.m = dVar;
        this.n = hVar;
        this.j = -1;
        this.i = cVar;
        this.k = bVar.f1688a;
        this.o = aVar;
        this.q = new a();
        if (bVar2 != null) {
            bVar2.a(this);
        }
        this.r = com.facebook.common.time.c.b();
        this.p = z;
        this.f1683a = new HashSet();
        if (this.p) {
            this.g = new CountDownLatch(1);
            executor.execute(new Runnable() {
                /* class com.facebook.b.b.e.AnonymousClass1 */

                public void run() {
                    synchronized (e.this.s) {
                        e.this.d();
                    }
                    e.this.t = true;
                    e.this.g.countDown();
                }
            });
            return;
        }
        this.g = new CountDownLatch(0);
    }

    @Override // com.facebook.b.b.i
    public com.facebook.a.a a(d dVar) {
        com.facebook.a.a aVar;
        j a2 = j.a().a(dVar);
        try {
            synchronized (this.s) {
                List<String> a3 = com.facebook.b.a.e.a(dVar);
                int i2 = 0;
                String str = null;
                aVar = null;
                while (true) {
                    if (i2 >= a3.size()) {
                        break;
                    }
                    str = a3.get(i2);
                    a2.a(str);
                    aVar = this.m.b(str, dVar);
                    if (aVar != null) {
                        break;
                    }
                    i2++;
                }
                if (aVar == null) {
                    this.i.b(a2);
                    this.f1683a.remove(str);
                } else {
                    this.i.a(a2);
                    this.f1683a.add(str);
                }
            }
            return aVar;
        } catch (IOException e2) {
            this.o.a(a.EnumC0036a.GENERIC_IO, f1680b, "getResource", e2);
            a2.a(e2);
            this.i.e(a2);
            return null;
        } finally {
            a2.b();
        }
    }

    private d.b a(String str, com.facebook.b.a.d dVar) {
        b();
        return this.m.a(str, dVar);
    }

    private com.facebook.a.a a(d.b bVar, com.facebook.b.a.d dVar, String str) {
        com.facebook.a.a a2;
        synchronized (this.s) {
            a2 = bVar.a(dVar);
            this.f1683a.add(str);
            this.q.b(a2.b(), 1);
        }
        return a2;
    }

    @Override // com.facebook.b.b.i
    public com.facebook.a.a a(com.facebook.b.a.d dVar, j jVar) {
        String b2;
        j a2 = j.a().a(dVar);
        this.i.c(a2);
        synchronized (this.s) {
            b2 = com.facebook.b.a.e.b(dVar);
        }
        a2.a(b2);
        try {
            d.b a3 = a(b2, dVar);
            try {
                a3.a(jVar, dVar);
                com.facebook.a.a a4 = a(a3, dVar, b2);
                a2.a(a4.b()).b(this.q.c());
                this.i.d(a2);
                a2.b();
                return a4;
            } finally {
                if (!a3.a()) {
                    com.facebook.common.e.a.c(f1680b, "Failed to delete temp file");
                }
            }
        } catch (IOException e2) {
            a2.a(e2);
            this.i.f(a2);
            com.facebook.common.e.a.b(f1680b, "Failed inserting a file into the cache", (Throwable) e2);
            throw e2;
        } catch (Throwable th) {
            a2.b();
            throw th;
        }
    }

    @Override // com.facebook.b.b.i
    public void b(com.facebook.b.a.d dVar) {
        synchronized (this.s) {
            try {
                List<String> a2 = com.facebook.b.a.e.a(dVar);
                for (int i2 = 0; i2 < a2.size(); i2++) {
                    String str = a2.get(i2);
                    this.m.b(str);
                    this.f1683a.remove(str);
                }
            } catch (IOException e2) {
                com.facebook.b.a.a aVar = this.o;
                a.EnumC0036a aVar2 = a.EnumC0036a.DELETE_FILE;
                Class<?> cls = f1680b;
                aVar.a(aVar2, cls, "delete: " + e2.getMessage(), e2);
            }
        }
    }

    private void b() {
        synchronized (this.s) {
            boolean d2 = d();
            c();
            long c2 = this.q.c();
            if (c2 > this.h && !d2) {
                this.q.b();
                d();
            }
            if (c2 > this.h) {
                a((this.h * 9) / 10, c.a.CACHE_FULL);
            }
        }
    }

    private void a(long j2, c.a aVar) {
        try {
            Collection<d.a> a2 = a(this.m.e());
            long c2 = this.q.c();
            long j3 = c2 - j2;
            int i2 = 0;
            long j4 = 0;
            for (d.a aVar2 : a2) {
                if (j4 > j3) {
                    break;
                }
                long a3 = this.m.a(aVar2);
                this.f1683a.remove(aVar2.a());
                if (a3 > 0) {
                    i2++;
                    j4 += a3;
                    j c3 = j.a().a(aVar2.a()).a(aVar).a(a3).b(c2 - j4).c(j2);
                    this.i.g(c3);
                    c3.b();
                }
            }
            this.q.b(-j4, (long) (-i2));
            this.m.b();
        } catch (IOException e2) {
            com.facebook.b.a.a aVar3 = this.o;
            a.EnumC0036a aVar4 = a.EnumC0036a.EVICTION;
            Class<?> cls = f1680b;
            aVar3.a(aVar4, cls, "evictAboveSize: " + e2.getMessage(), e2);
            throw e2;
        }
    }

    private Collection<d.a> a(Collection<d.a> collection) {
        long a2 = this.r.a() + f1681c;
        ArrayList arrayList = new ArrayList(collection.size());
        ArrayList arrayList2 = new ArrayList(collection.size());
        for (d.a aVar : collection) {
            if (aVar.b() > a2) {
                arrayList.add(aVar);
            } else {
                arrayList2.add(aVar);
            }
        }
        Collections.sort(arrayList2, this.n.a());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    private void c() {
        if (this.l.a(this.m.a() ? a.EnumC0043a.EXTERNAL : a.EnumC0043a.INTERNAL, this.f - this.q.c())) {
            this.h = this.e;
        } else {
            this.h = this.f;
        }
    }

    @Override // com.facebook.b.b.i
    public void a() {
        synchronized (this.s) {
            try {
                this.m.c();
                this.f1683a.clear();
                this.i.a();
            } catch (IOException e2) {
                com.facebook.b.a.a aVar = this.o;
                a.EnumC0036a aVar2 = a.EnumC0036a.EVICTION;
                Class<?> cls = f1680b;
                aVar.a(aVar2, cls, "clearAll: " + e2.getMessage(), e2);
            }
            this.q.b();
        }
    }

    @Override // com.facebook.b.b.i
    public boolean c(com.facebook.b.a.d dVar) {
        synchronized (this.s) {
            List<String> a2 = com.facebook.b.a.e.a(dVar);
            for (int i2 = 0; i2 < a2.size(); i2++) {
                if (this.f1683a.contains(a2.get(i2))) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.facebook.b.b.i
    public boolean d(com.facebook.b.a.d dVar) {
        synchronized (this.s) {
            if (c(dVar)) {
                return true;
            }
            try {
                List<String> a2 = com.facebook.b.a.e.a(dVar);
                for (int i2 = 0; i2 < a2.size(); i2++) {
                    String str = a2.get(i2);
                    if (this.m.c(str, dVar)) {
                        this.f1683a.add(str);
                        return true;
                    }
                }
                return false;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean d() {
        long a2 = this.r.a();
        if (this.q.a()) {
            long j2 = this.j;
            if (j2 != -1 && a2 - j2 <= f1682d) {
                return false;
            }
        }
        return e();
    }

    private boolean e() {
        Set<String> set;
        long j2;
        long a2 = this.r.a();
        long j3 = f1681c + a2;
        if (!this.p || !this.f1683a.isEmpty()) {
            set = this.p ? new HashSet<>() : null;
        } else {
            set = this.f1683a;
        }
        try {
            long j4 = -1;
            int i2 = 0;
            int i3 = 0;
            long j5 = 0;
            boolean z = false;
            int i4 = 0;
            for (d.a aVar : this.m.e()) {
                i4++;
                j5 += aVar.d();
                if (aVar.b() > j3) {
                    i2++;
                    j2 = j3;
                    int d2 = (int) (((long) i3) + aVar.d());
                    j4 = Math.max(aVar.b() - a2, j4);
                    i3 = d2;
                    z = true;
                } else {
                    j2 = j3;
                    if (this.p) {
                        set.add(aVar.a());
                    }
                }
                j3 = j2;
            }
            if (z) {
                this.o.a(a.EnumC0036a.READ_INVALID_ENTRY, f1680b, "Future timestamp found in " + i2 + " files , with a total size of " + i3 + " bytes, and a maximum time delta of " + j4 + "ms", null);
            }
            long j6 = (long) i4;
            if (!(this.q.d() == j6 && this.q.c() == j5)) {
                if (this.p && this.f1683a != set) {
                    this.f1683a.clear();
                    this.f1683a.addAll(set);
                }
                this.q.a(j5, j6);
            }
            this.j = a2;
            return true;
        } catch (IOException e2) {
            this.o.a(a.EnumC0036a.GENERIC_IO, f1680b, "calcFileCacheSize: " + e2.getMessage(), e2);
            return false;
        }
    }
}
