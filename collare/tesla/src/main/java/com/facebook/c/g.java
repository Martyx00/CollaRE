package com.facebook.c;

import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: IncreasingQualityDataSourceSupplier */
public class g<T> implements l<c<T>> {

    /* renamed from: a  reason: collision with root package name */
    private final List<l<c<T>>> f1722a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f1723b;

    private g(List<l<c<T>>> list, boolean z) {
        i.a(!list.isEmpty(), "List of suppliers is empty!");
        this.f1722a = list;
        this.f1723b = z;
    }

    public static <T> g<T> a(List<l<c<T>>> list, boolean z) {
        return new g<>(list, z);
    }

    /* renamed from: a */
    public c<T> b() {
        return new a();
    }

    public int hashCode() {
        return this.f1722a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        return h.a(this.f1722a, ((g) obj).f1722a);
    }

    public String toString() {
        return h.a(this).a("list", this.f1722a).toString();
    }

    /* access modifiers changed from: private */
    /* compiled from: IncreasingQualityDataSourceSupplier */
    public class a extends a<T> {

        /* renamed from: b  reason: collision with root package name */
        private ArrayList<c<T>> f1725b;

        /* renamed from: c  reason: collision with root package name */
        private int f1726c;

        /* renamed from: d  reason: collision with root package name */
        private int f1727d;
        private AtomicInteger e;
        private Throwable f;

        public a() {
            if (!g.this.f1723b) {
                j();
            }
        }

        private void j() {
            if (this.e == null) {
                synchronized (this) {
                    if (this.e == null) {
                        int i = 0;
                        this.e = new AtomicInteger(0);
                        int size = g.this.f1722a.size();
                        this.f1727d = size;
                        this.f1726c = size;
                        this.f1725b = new ArrayList<>(size);
                        while (true) {
                            if (i >= size) {
                                break;
                            }
                            c<T> cVar = (c) ((l) g.this.f1722a.get(i)).b();
                            this.f1725b.add(cVar);
                            cVar.a(new C0040a(i), com.facebook.common.b.a.a());
                            if (cVar.c()) {
                                break;
                            }
                            i++;
                        }
                    }
                }
            }
        }

        private synchronized c<T> a(int i) {
            return (this.f1725b == null || i >= this.f1725b.size()) ? null : this.f1725b.get(i);
        }

        private synchronized c<T> b(int i) {
            c<T> cVar;
            cVar = null;
            if (this.f1725b != null && i < this.f1725b.size()) {
                cVar = this.f1725b.set(i, null);
            }
            return cVar;
        }

        private synchronized c<T> k() {
            return a(this.f1726c);
        }

        @Override // com.facebook.c.c, com.facebook.c.a
        public synchronized T d() {
            c<T> k;
            if (g.this.f1723b) {
                j();
            }
            k = k();
            return k != null ? k.d() : null;
        }

        @Override // com.facebook.c.c, com.facebook.c.a
        public synchronized boolean c() {
            c<T> k;
            if (g.this.f1723b) {
                j();
            }
            k = k();
            return k != null && k.c();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
            if (r0 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
            if (r1 >= r0.size()) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
            a((com.facebook.c.c) r0.get(r1));
            r1 = r1 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
            return true;
         */
        @Override // com.facebook.c.c, com.facebook.c.a
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean h() {
            /*
                r3 = this;
                com.facebook.c.g r0 = com.facebook.c.g.this
                boolean r0 = com.facebook.c.g.a(r0)
                if (r0 == 0) goto L_0x000b
                r3.j()
            L_0x000b:
                monitor-enter(r3)
                boolean r0 = super.h()     // Catch:{ all -> 0x0031 }
                r1 = 0
                if (r0 != 0) goto L_0x0015
                monitor-exit(r3)     // Catch:{ all -> 0x0031 }
                return r1
            L_0x0015:
                java.util.ArrayList<com.facebook.c.c<T>> r0 = r3.f1725b     // Catch:{ all -> 0x0031 }
                r2 = 0
                r3.f1725b = r2     // Catch:{ all -> 0x0031 }
                monitor-exit(r3)     // Catch:{ all -> 0x0031 }
                if (r0 == 0) goto L_0x002f
            L_0x001d:
                int r2 = r0.size()
                if (r1 >= r2) goto L_0x002f
                java.lang.Object r2 = r0.get(r1)
                com.facebook.c.c r2 = (com.facebook.c.c) r2
                r3.a(r2)
                int r1 = r1 + 1
                goto L_0x001d
            L_0x002f:
                r0 = 1
                return r0
            L_0x0031:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.c.g.a.h():boolean");
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(int i, c<T> cVar) {
            a(i, cVar, cVar.b());
            if (cVar == k()) {
                a((Object) null, i == 0 && cVar.b());
            }
            l();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void b(int i, c<T> cVar) {
            a((c) c(i, cVar));
            if (i == 0) {
                this.f = cVar.f();
            }
            l();
        }

        private void l() {
            Throwable th;
            if (this.e.incrementAndGet() == this.f1727d && (th = this.f) != null) {
                a(th);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0022, code lost:
            if (r0 <= r4) goto L_0x002e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
            a((com.facebook.c.c) b(r0));
            r0 = r0 - 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void a(int r4, com.facebook.c.c<T> r5, boolean r6) {
            /*
                r3 = this;
                monitor-enter(r3)
                int r0 = r3.f1726c     // Catch:{ all -> 0x0031 }
                int r1 = r3.f1726c     // Catch:{ all -> 0x0031 }
                com.facebook.c.c r2 = r3.a(r4)     // Catch:{ all -> 0x0031 }
                if (r5 != r2) goto L_0x002f
                int r5 = r3.f1726c     // Catch:{ all -> 0x0031 }
                if (r4 != r5) goto L_0x0010
                goto L_0x002f
            L_0x0010:
                com.facebook.c.c r5 = r3.k()     // Catch:{ all -> 0x0031 }
                if (r5 == 0) goto L_0x001f
                if (r6 == 0) goto L_0x001d
                int r5 = r3.f1726c     // Catch:{ all -> 0x0031 }
                if (r4 >= r5) goto L_0x001d
                goto L_0x001f
            L_0x001d:
                r4 = r1
                goto L_0x0021
            L_0x001f:
                r3.f1726c = r4     // Catch:{ all -> 0x0031 }
            L_0x0021:
                monitor-exit(r3)     // Catch:{ all -> 0x0031 }
            L_0x0022:
                if (r0 <= r4) goto L_0x002e
                com.facebook.c.c r5 = r3.b(r0)
                r3.a(r5)
                int r0 = r0 + -1
                goto L_0x0022
            L_0x002e:
                return
            L_0x002f:
                monitor-exit(r3)
                return
            L_0x0031:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.c.g.a.a(int, com.facebook.c.c, boolean):void");
        }

        private synchronized c<T> c(int i, c<T> cVar) {
            if (cVar == k()) {
                return null;
            }
            if (cVar != a(i)) {
                return cVar;
            }
            return b(i);
        }

        private void a(c<T> cVar) {
            if (cVar != null) {
                cVar.h();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: com.facebook.c.g$a$a  reason: collision with other inner class name */
        /* compiled from: IncreasingQualityDataSourceSupplier */
        public class C0040a implements e<T> {

            /* renamed from: b  reason: collision with root package name */
            private int f1729b;

            @Override // com.facebook.c.e
            public void c(c<T> cVar) {
            }

            public C0040a(int i) {
                this.f1729b = i;
            }

            @Override // com.facebook.c.e
            public void a(c<T> cVar) {
                if (cVar.c()) {
                    a.this.a((a) this.f1729b, (int) cVar);
                } else if (cVar.b()) {
                    a.this.b((a) this.f1729b, (int) cVar);
                }
            }

            @Override // com.facebook.c.e
            public void b(c<T> cVar) {
                a.this.b((a) this.f1729b, (int) cVar);
            }

            @Override // com.facebook.c.e
            public void d(c<T> cVar) {
                if (this.f1729b == 0) {
                    a.this.a(cVar.g());
                }
            }
        }
    }
}
