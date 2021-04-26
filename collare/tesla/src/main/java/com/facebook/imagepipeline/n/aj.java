package com.facebook.imagepipeline.n;

import android.graphics.Bitmap;
import com.facebook.common.d.i;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.o.d;
import com.facebook.imagepipeline.o.e;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: PostprocessorProducer */
public class aj implements ak<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> {

    /* renamed from: a  reason: collision with root package name */
    private final ak<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> f2290a;

    /* renamed from: b  reason: collision with root package name */
    private final f f2291b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f2292c;

    public aj(ak<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> akVar, f fVar, Executor executor) {
        this.f2290a = (ak) i.a(akVar);
        this.f2291b = fVar;
        this.f2292c = (Executor) i.a(executor);
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, al alVar) {
        k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar2;
        an c2 = alVar.c();
        d q = alVar.a().q();
        a aVar = new a(kVar, c2, alVar.b(), q, alVar);
        if (q instanceof e) {
            kVar2 = new b(aVar, (e) q, alVar);
        } else {
            kVar2 = new c(aVar);
        }
        this.f2290a.a(kVar2, alVar);
    }

    /* compiled from: PostprocessorProducer */
    private class a extends n<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>, com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> {

        /* renamed from: b  reason: collision with root package name */
        private final an f2294b;

        /* renamed from: c  reason: collision with root package name */
        private final String f2295c;

        /* renamed from: d  reason: collision with root package name */
        private final d f2296d;
        private boolean e;
        private com.facebook.common.h.a<com.facebook.imagepipeline.j.b> f = null;
        private int g = 0;
        private boolean h = false;
        private boolean i = false;

        public a(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, an anVar, String str, d dVar, al alVar) {
            super(kVar);
            this.f2294b = anVar;
            this.f2295c = str;
            this.f2296d = dVar;
            alVar.a(new e(aj.this) {
                /* class com.facebook.imagepipeline.n.aj.a.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void a() {
                    a.this.g();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void a(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar, int i2) {
            if (com.facebook.common.h.a.a((com.facebook.common.h.a<?>) aVar)) {
                b(aVar, i2);
            } else if (a(i2)) {
                d(null, i2);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            c(th);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a() {
            g();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            c();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
            com.facebook.common.h.a.c(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
            if (r2 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void b(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> r2, int r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.e     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                return
            L_0x0007:
                com.facebook.common.h.a<com.facebook.imagepipeline.j.b> r0 = r1.f     // Catch:{ all -> 0x0022 }
                com.facebook.common.h.a r2 = com.facebook.common.h.a.b(r2)     // Catch:{ all -> 0x0022 }
                r1.f = r2     // Catch:{ all -> 0x0022 }
                r1.g = r3     // Catch:{ all -> 0x0022 }
                r2 = 1
                r1.h = r2     // Catch:{ all -> 0x0022 }
                boolean r2 = r1.f()     // Catch:{ all -> 0x0022 }
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                com.facebook.common.h.a.c(r0)
                if (r2 == 0) goto L_0x0021
                r1.c()
            L_0x0021:
                return
            L_0x0022:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.aj.a.b(com.facebook.common.h.a, int):void");
        }

        private void c() {
            aj.this.f2292c.execute(new Runnable() {
                /* class com.facebook.imagepipeline.n.aj.a.AnonymousClass2 */

                public void run() {
                    com.facebook.common.h.a aVar;
                    int i;
                    synchronized (a.this) {
                        aVar = a.this.f;
                        i = a.this.g;
                        a.this.f = null;
                        a.this.h = false;
                    }
                    if (com.facebook.common.h.a.a((com.facebook.common.h.a<?>) aVar)) {
                        try {
                            a.this.c((a) aVar, (com.facebook.common.h.a) i);
                        } finally {
                            com.facebook.common.h.a.c(aVar);
                        }
                    }
                    a.this.e();
                }
            });
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void e() {
            boolean f2;
            synchronized (this) {
                this.i = false;
                f2 = f();
            }
            if (f2) {
                c();
            }
        }

        private synchronized boolean f() {
            if (this.e || !this.h || this.i || !com.facebook.common.h.a.a((com.facebook.common.h.a<?>) this.f)) {
                return false;
            }
            this.i = true;
            return true;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void c(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar, int i2) {
            i.a(com.facebook.common.h.a.a((com.facebook.common.h.a<?>) aVar));
            if (!a(aVar.a())) {
                d(aVar, i2);
                return;
            }
            this.f2294b.a(this.f2295c, "PostprocessorProducer");
            try {
                com.facebook.common.h.a<com.facebook.imagepipeline.j.b> b2 = b(aVar.a());
                try {
                    this.f2294b.a(this.f2295c, "PostprocessorProducer", a(this.f2294b, this.f2295c, this.f2296d));
                    d(b2, i2);
                } finally {
                    com.facebook.common.h.a.c(b2);
                }
            } catch (Exception e2) {
                this.f2294b.a(this.f2295c, "PostprocessorProducer", e2, a(this.f2294b, this.f2295c, this.f2296d));
                c(e2);
                com.facebook.common.h.a.c(null);
            }
        }

        private Map<String, String> a(an anVar, String str, d dVar) {
            if (!anVar.b(str)) {
                return null;
            }
            return com.facebook.common.d.f.a("Postprocessor", dVar.b());
        }

        private boolean a(com.facebook.imagepipeline.j.b bVar) {
            return bVar instanceof com.facebook.imagepipeline.j.c;
        }

        private com.facebook.common.h.a<com.facebook.imagepipeline.j.b> b(com.facebook.imagepipeline.j.b bVar) {
            com.facebook.imagepipeline.j.c cVar = (com.facebook.imagepipeline.j.c) bVar;
            com.facebook.common.h.a<Bitmap> a2 = this.f2296d.a(cVar.a(), aj.this.f2291b);
            try {
                return com.facebook.common.h.a.a(new com.facebook.imagepipeline.j.c(a2, bVar.d(), cVar.h(), cVar.i()));
            } finally {
                com.facebook.common.h.a.c(a2);
            }
        }

        private void d(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar, int i2) {
            boolean a2 = a(i2);
            if ((!a2 && !h()) || (a2 && i())) {
                d().b(aVar, i2);
            }
        }

        private void c(Throwable th) {
            if (i()) {
                d().b(th);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void g() {
            if (i()) {
                d().b();
            }
        }

        private synchronized boolean h() {
            return this.e;
        }

        private boolean i() {
            synchronized (this) {
                if (this.e) {
                    return false;
                }
                com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar = this.f;
                this.f = null;
                this.e = true;
                com.facebook.common.h.a.c(aVar);
                return true;
            }
        }
    }

    /* compiled from: PostprocessorProducer */
    class c extends n<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>, com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> {
        private c(a aVar) {
            super(aVar);
        }

        /* access modifiers changed from: protected */
        public void a(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar, int i) {
            if (!b(i)) {
                d().b(aVar, i);
            }
        }
    }

    /* compiled from: PostprocessorProducer */
    class b extends n<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>, com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> implements com.facebook.imagepipeline.o.f {

        /* renamed from: b  reason: collision with root package name */
        private boolean f2301b;

        /* renamed from: c  reason: collision with root package name */
        private com.facebook.common.h.a<com.facebook.imagepipeline.j.b> f2302c;

        private b(a aVar, e eVar, al alVar) {
            super(aVar);
            this.f2301b = false;
            this.f2302c = null;
            eVar.a(this);
            alVar.a(new e(aj.this) {
                /* class com.facebook.imagepipeline.n.aj.b.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void a() {
                    if (b.this.e()) {
                        b.this.d().b();
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public void a(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar, int i) {
            if (!b(i)) {
                a(aVar);
                c();
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            if (e()) {
                d().b(th);
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a() {
            if (e()) {
                d().b();
            }
        }

        private void c() {
            synchronized (this) {
                if (!this.f2301b) {
                    com.facebook.common.h.a b2 = com.facebook.common.h.a.b(this.f2302c);
                    try {
                        d().b(b2, 0);
                    } finally {
                        com.facebook.common.h.a.c(b2);
                    }
                }
            }
        }

        private void a(com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar) {
            synchronized (this) {
                if (!this.f2301b) {
                    com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar2 = this.f2302c;
                    this.f2302c = com.facebook.common.h.a.b(aVar);
                    com.facebook.common.h.a.c(aVar2);
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean e() {
            synchronized (this) {
                if (this.f2301b) {
                    return false;
                }
                com.facebook.common.h.a<com.facebook.imagepipeline.j.b> aVar = this.f2302c;
                this.f2302c = null;
                this.f2301b = true;
                com.facebook.common.h.a.c(aVar);
                return true;
            }
        }
    }
}
