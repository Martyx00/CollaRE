package com.facebook.imagepipeline.n;

import android.util.Pair;
import com.facebook.common.d.i;
import com.facebook.common.d.k;
import com.facebook.imagepipeline.e.d;
import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: MultiplexProducer */
public abstract class ae<K, T extends Closeable> implements ak<T> {

    /* renamed from: a  reason: collision with root package name */
    final Map<K, ae<K, T>.a> f2255a = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    private final ak<T> f2256b;

    /* access modifiers changed from: protected */
    public abstract T a(T t);

    /* access modifiers changed from: protected */
    public abstract K b(al alVar);

    protected ae(ak<T> akVar) {
        this.f2256b = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<T> kVar, al alVar) {
        boolean z;
        ae<K, T>.a a2;
        K b2 = b(alVar);
        do {
            z = false;
            synchronized (this) {
                a2 = a((Object) b2);
                if (a2 == null) {
                    a2 = b((Object) b2);
                    z = true;
                }
            }
        } while (!a2.a(kVar, alVar));
        if (z) {
            a2.a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized ae<K, T>.a a(K k) {
        return this.f2255a.get(k);
    }

    private synchronized ae<K, T>.a b(K k) {
        ae<K, T>.a aVar;
        aVar = new a(k);
        this.f2255a.put(k, aVar);
        return aVar;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void a(K k, ae<K, T>.a aVar) {
        if (this.f2255a.get(k) == aVar) {
            this.f2255a.remove(k);
        }
    }

    /* access modifiers changed from: package-private */
    /* compiled from: MultiplexProducer */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        private final K f2258b;

        /* renamed from: c  reason: collision with root package name */
        private final CopyOnWriteArraySet<Pair<k<T>, al>> f2259c = k.b();

        /* renamed from: d  reason: collision with root package name */
        private T f2260d;
        private float e;
        private int f;
        private d g;
        private ae<K, T>.a.C0050a h;

        public a(K k) {
            this.f2258b = k;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: com.facebook.imagepipeline.n.k<T extends java.io.Closeable> */
        /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: com.facebook.imagepipeline.n.ae */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
            com.facebook.imagepipeline.n.d.b(r1);
            com.facebook.imagepipeline.n.d.d(r2);
            com.facebook.imagepipeline.n.d.c(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0033, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
            if (r4 == r7.f2260d) goto L_0x003b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
            r4 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x003b, code lost:
            if (r4 == null) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
            r4 = r7.f2257a.a(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0044, code lost:
            if (r4 == null) goto L_0x0054;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
            if (r5 <= com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED) goto L_0x004e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
            r8.b(r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004e, code lost:
            r8.b(r4, r6);
            a(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0054, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
            a(r0, r9);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0059, code lost:
            return true;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(com.facebook.imagepipeline.n.k<T> r8, com.facebook.imagepipeline.n.al r9) {
            /*
                r7 = this;
                android.util.Pair r0 = android.util.Pair.create(r8, r9)
                monitor-enter(r7)
                com.facebook.imagepipeline.n.ae r1 = com.facebook.imagepipeline.n.ae.this     // Catch:{ all -> 0x0060 }
                K r2 = r7.f2258b     // Catch:{ all -> 0x0060 }
                com.facebook.imagepipeline.n.ae$a r1 = com.facebook.imagepipeline.n.ae.a(r1, r2)     // Catch:{ all -> 0x0060 }
                if (r1 == r7) goto L_0x0012
                r8 = 0
                monitor-exit(r7)     // Catch:{ all -> 0x0060 }
                return r8
            L_0x0012:
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r1 = r7.f2259c     // Catch:{ all -> 0x0060 }
                r1.add(r0)     // Catch:{ all -> 0x0060 }
                java.util.List r1 = r7.b()     // Catch:{ all -> 0x0060 }
                java.util.List r2 = r7.f()     // Catch:{ all -> 0x0060 }
                java.util.List r3 = r7.d()     // Catch:{ all -> 0x0060 }
                T r4 = r7.f2260d     // Catch:{ all -> 0x0060 }
                float r5 = r7.e     // Catch:{ all -> 0x0060 }
                int r6 = r7.f     // Catch:{ all -> 0x0060 }
                monitor-exit(r7)     // Catch:{ all -> 0x0060 }
                com.facebook.imagepipeline.n.d.b(r1)
                com.facebook.imagepipeline.n.d.d(r2)
                com.facebook.imagepipeline.n.d.c(r3)
                monitor-enter(r0)
                monitor-enter(r7)     // Catch:{ all -> 0x005d }
                T r1 = r7.f2260d     // Catch:{ all -> 0x005a }
                if (r4 == r1) goto L_0x003b
                r4 = 0
                goto L_0x0043
            L_0x003b:
                if (r4 == 0) goto L_0x0043
                com.facebook.imagepipeline.n.ae r1 = com.facebook.imagepipeline.n.ae.this     // Catch:{ all -> 0x005a }
                java.io.Closeable r4 = r1.a(r4)     // Catch:{ all -> 0x005a }
            L_0x0043:
                monitor-exit(r7)     // Catch:{ all -> 0x005a }
                if (r4 == 0) goto L_0x0054
                r1 = 0
                int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                if (r1 <= 0) goto L_0x004e
                r8.b(r5)
            L_0x004e:
                r8.b(r4, r6)
                r7.a(r4)
            L_0x0054:
                monitor-exit(r0)
                r7.a(r0, r9)
                r8 = 1
                return r8
            L_0x005a:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            L_0x005d:
                r8 = move-exception
                monitor-exit(r0)
                throw r8
            L_0x0060:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.ae.a.a(com.facebook.imagepipeline.n.k, com.facebook.imagepipeline.n.al):boolean");
        }

        private void a(final Pair<k<T>, al> pair, al alVar) {
            alVar.a(new e() {
                /* class com.facebook.imagepipeline.n.ae.a.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void a() {
                    boolean remove;
                    List list;
                    List list2;
                    List list3;
                    d dVar;
                    synchronized (a.this) {
                        remove = a.this.f2259c.remove(pair);
                        list = null;
                        if (!remove) {
                            dVar = null;
                            list3 = null;
                            list2 = null;
                        } else if (a.this.f2259c.isEmpty()) {
                            dVar = a.this.g;
                            list3 = null;
                            list2 = null;
                        } else {
                            List b2 = a.this.b();
                            list3 = a.this.f();
                            list2 = a.this.d();
                            dVar = null;
                            list = b2;
                        }
                    }
                    d.b(list);
                    d.d(list3);
                    d.c(list2);
                    if (dVar != null) {
                        dVar.i();
                    }
                    if (remove) {
                        ((k) pair.first).b();
                    }
                }

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void b() {
                    d.b(a.this.b());
                }

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void c() {
                    d.c(a.this.d());
                }

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void d() {
                    d.d(a.this.f());
                }
            });
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a() {
            synchronized (this) {
                boolean z = true;
                i.a(this.g == null);
                if (this.h != null) {
                    z = false;
                }
                i.a(z);
                if (this.f2259c.isEmpty()) {
                    ae.this.a((ae) this.f2258b, (Object) this);
                    return;
                }
                al alVar = (al) this.f2259c.iterator().next().second;
                this.g = new d(alVar.a(), alVar.b(), alVar.c(), alVar.d(), alVar.e(), c(), e(), g());
                this.h = new C0050a();
                ae.this.f2256b.a(this.h, this.g);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized List<am> b() {
            if (this.g == null) {
                return null;
            }
            return this.g.a(c());
        }

        private synchronized boolean c() {
            Iterator<Pair<k<T>, al>> it = this.f2259c.iterator();
            while (it.hasNext()) {
                if (!((al) it.next().second).f()) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized List<am> d() {
            if (this.g == null) {
                return null;
            }
            return this.g.b(e());
        }

        private synchronized boolean e() {
            Iterator<Pair<k<T>, al>> it = this.f2259c.iterator();
            while (it.hasNext()) {
                if (((al) it.next().second).h()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private synchronized List<am> f() {
            if (this.g == null) {
                return null;
            }
            return this.g.a(g());
        }

        private synchronized d g() {
            d dVar;
            dVar = d.LOW;
            Iterator<Pair<k<T>, al>> it = this.f2259c.iterator();
            while (it.hasNext()) {
                dVar = d.a(dVar, ((al) it.next().second).g());
            }
            return dVar;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
            r0 = r3.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            ((com.facebook.imagepipeline.n.k) r0.first).b(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
            if (r3.hasNext() == false) goto L_0x003b;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.facebook.imagepipeline.n.ae<K, T>.a.C0050a r3, java.lang.Throwable r4) {
            /*
                r2 = this;
                monitor-enter(r2)
                com.facebook.imagepipeline.n.ae<K, T>$a$a r0 = r2.h     // Catch:{ all -> 0x003c }
                if (r0 == r3) goto L_0x0007
                monitor-exit(r2)     // Catch:{ all -> 0x003c }
                return
            L_0x0007:
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r3 = r2.f2259c     // Catch:{ all -> 0x003c }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x003c }
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r0 = r2.f2259c     // Catch:{ all -> 0x003c }
                r0.clear()     // Catch:{ all -> 0x003c }
                com.facebook.imagepipeline.n.ae r0 = com.facebook.imagepipeline.n.ae.this     // Catch:{ all -> 0x003c }
                K r1 = r2.f2258b     // Catch:{ all -> 0x003c }
                com.facebook.imagepipeline.n.ae.a(r0, r1, r2)     // Catch:{ all -> 0x003c }
                T r0 = r2.f2260d     // Catch:{ all -> 0x003c }
                r2.a(r0)     // Catch:{ all -> 0x003c }
                r0 = 0
                r2.f2260d = r0     // Catch:{ all -> 0x003c }
                monitor-exit(r2)     // Catch:{ all -> 0x003c }
            L_0x0022:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x003b
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                monitor-enter(r0)
                java.lang.Object r1 = r0.first     // Catch:{ all -> 0x0038 }
                com.facebook.imagepipeline.n.k r1 = (com.facebook.imagepipeline.n.k) r1     // Catch:{ all -> 0x0038 }
                r1.b(r4)     // Catch:{ all -> 0x0038 }
                monitor-exit(r0)     // Catch:{ all -> 0x0038 }
                goto L_0x0022
            L_0x0038:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0038 }
                throw r3
            L_0x003b:
                return
            L_0x003c:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.ae.a.a(com.facebook.imagepipeline.n.ae$a$a, java.lang.Throwable):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
            if (r3.hasNext() == false) goto L_0x004c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0039, code lost:
            r0 = r3.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            ((com.facebook.imagepipeline.n.k) r0.first).b(r4, r5);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.facebook.imagepipeline.n.ae<K, T>.a.C0050a r3, T r4, int r5) {
            /*
                r2 = this;
                monitor-enter(r2)
                com.facebook.imagepipeline.n.ae<K, T>$a$a r0 = r2.h     // Catch:{ all -> 0x004d }
                if (r0 == r3) goto L_0x0007
                monitor-exit(r2)     // Catch:{ all -> 0x004d }
                return
            L_0x0007:
                T r3 = r2.f2260d     // Catch:{ all -> 0x004d }
                r2.a(r3)     // Catch:{ all -> 0x004d }
                r3 = 0
                r2.f2260d = r3     // Catch:{ all -> 0x004d }
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r3 = r2.f2259c     // Catch:{ all -> 0x004d }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x004d }
                boolean r0 = com.facebook.imagepipeline.n.b.b(r5)     // Catch:{ all -> 0x004d }
                if (r0 == 0) goto L_0x0026
                com.facebook.imagepipeline.n.ae r0 = com.facebook.imagepipeline.n.ae.this     // Catch:{ all -> 0x004d }
                java.io.Closeable r0 = r0.a(r4)     // Catch:{ all -> 0x004d }
                r2.f2260d = r0     // Catch:{ all -> 0x004d }
                r2.f = r5     // Catch:{ all -> 0x004d }
                goto L_0x0032
            L_0x0026:
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r0 = r2.f2259c     // Catch:{ all -> 0x004d }
                r0.clear()     // Catch:{ all -> 0x004d }
                com.facebook.imagepipeline.n.ae r0 = com.facebook.imagepipeline.n.ae.this     // Catch:{ all -> 0x004d }
                K r1 = r2.f2258b     // Catch:{ all -> 0x004d }
                com.facebook.imagepipeline.n.ae.a(r0, r1, r2)     // Catch:{ all -> 0x004d }
            L_0x0032:
                monitor-exit(r2)     // Catch:{ all -> 0x004d }
            L_0x0033:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x004c
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                monitor-enter(r0)
                java.lang.Object r1 = r0.first     // Catch:{ all -> 0x0049 }
                com.facebook.imagepipeline.n.k r1 = (com.facebook.imagepipeline.n.k) r1     // Catch:{ all -> 0x0049 }
                r1.b(r4, r5)     // Catch:{ all -> 0x0049 }
                monitor-exit(r0)     // Catch:{ all -> 0x0049 }
                goto L_0x0033
            L_0x0049:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0049 }
                throw r3
            L_0x004c:
                return
            L_0x004d:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.ae.a.a(com.facebook.imagepipeline.n.ae$a$a, java.io.Closeable, int):void");
        }

        public void a(ae<K, T>.a.C0050a aVar) {
            synchronized (this) {
                if (this.h == aVar) {
                    this.h = null;
                    this.g = null;
                    a((Closeable) this.f2260d);
                    this.f2260d = null;
                    a();
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
            r0 = r3.next();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            ((com.facebook.imagepipeline.n.k) r0.first).b(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0029, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0014, code lost:
            if (r3.hasNext() == false) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(com.facebook.imagepipeline.n.ae<K, T>.a.C0050a r3, float r4) {
            /*
                r2 = this;
                monitor-enter(r2)
                com.facebook.imagepipeline.n.ae<K, T>$a$a r0 = r2.h     // Catch:{ all -> 0x002a }
                if (r0 == r3) goto L_0x0007
                monitor-exit(r2)     // Catch:{ all -> 0x002a }
                return
            L_0x0007:
                r2.e = r4     // Catch:{ all -> 0x002a }
                java.util.concurrent.CopyOnWriteArraySet<android.util.Pair<com.facebook.imagepipeline.n.k<T>, com.facebook.imagepipeline.n.al>> r3 = r2.f2259c     // Catch:{ all -> 0x002a }
                java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x002a }
                monitor-exit(r2)     // Catch:{ all -> 0x002a }
            L_0x0010:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x0029
                java.lang.Object r0 = r3.next()
                android.util.Pair r0 = (android.util.Pair) r0
                monitor-enter(r0)
                java.lang.Object r1 = r0.first     // Catch:{ all -> 0x0026 }
                com.facebook.imagepipeline.n.k r1 = (com.facebook.imagepipeline.n.k) r1     // Catch:{ all -> 0x0026 }
                r1.b(r4)     // Catch:{ all -> 0x0026 }
                monitor-exit(r0)     // Catch:{ all -> 0x0026 }
                goto L_0x0010
            L_0x0026:
                r3 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0026 }
                throw r3
            L_0x0029:
                return
            L_0x002a:
                r3 = move-exception
                monitor-exit(r2)
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.ae.a.a(com.facebook.imagepipeline.n.ae$a$a, float):void");
        }

        private void a(Closeable closeable) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: com.facebook.imagepipeline.n.ae$a$a  reason: collision with other inner class name */
        /* compiled from: MultiplexProducer */
        public class C0050a extends b<T> {
            private C0050a() {
            }

            /* access modifiers changed from: protected */
            public void a(T t, int i) {
                a.this.a(this, t, i);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(Throwable th) {
                a.this.a(this, th);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a() {
                a.this.a(this);
            }

            /* access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.n.b
            public void a(float f) {
                a.this.a(this, f);
            }
        }
    }
}
