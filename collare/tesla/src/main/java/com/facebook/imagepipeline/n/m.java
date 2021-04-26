package com.facebook.imagepipeline.n;

import android.graphics.Bitmap;
import com.facebook.common.d.i;
import com.facebook.common.k.f;
import com.facebook.imagepipeline.h.e;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.j.g;
import com.facebook.imagepipeline.n.v;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: DecodeProducer */
public class m implements ak<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> {

    /* renamed from: a  reason: collision with root package name */
    private final com.facebook.common.g.a f2377a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f2378b;

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.imagepipeline.h.c f2379c;

    /* renamed from: d  reason: collision with root package name */
    private final e f2380d;
    private final ak<d> e;
    private final boolean f;
    private final boolean g;
    private final boolean h;

    public m(com.facebook.common.g.a aVar, Executor executor, com.facebook.imagepipeline.h.c cVar, e eVar, boolean z, boolean z2, boolean z3, ak<d> akVar) {
        this.f2377a = (com.facebook.common.g.a) i.a(aVar);
        this.f2378b = (Executor) i.a(executor);
        this.f2379c = (com.facebook.imagepipeline.h.c) i.a(cVar);
        this.f2380d = (e) i.a(eVar);
        this.f = z;
        this.g = z2;
        this.e = (ak) i.a(akVar);
        this.h = z3;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, al alVar) {
        k<d> kVar2;
        if (!f.b(alVar.a().b())) {
            kVar2 = new a(kVar, alVar, this.h);
        } else {
            kVar2 = new b(kVar, alVar, new com.facebook.imagepipeline.h.f(this.f2377a), this.f2380d, this.h);
        }
        this.e.a(kVar2, alVar);
    }

    /* compiled from: DecodeProducer */
    private abstract class c extends n<d, com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> {

        /* renamed from: a  reason: collision with root package name */
        private final String f2385a = "ProgressiveDecoder";

        /* renamed from: c  reason: collision with root package name */
        private final al f2387c;

        /* renamed from: d  reason: collision with root package name */
        private final an f2388d;
        private final com.facebook.imagepipeline.e.b e;
        private boolean f;
        private final v g;

        /* access modifiers changed from: protected */
        public abstract int a(d dVar);

        /* access modifiers changed from: protected */
        public abstract g c();

        public c(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, final al alVar, final boolean z) {
            super(kVar);
            this.f2387c = alVar;
            this.f2388d = alVar.c();
            this.e = alVar.a().i();
            this.f = false;
            this.g = new v(m.this.f2378b, new v.a(m.this) {
                /* class com.facebook.imagepipeline.n.m.c.AnonymousClass1 */

                @Override // com.facebook.imagepipeline.n.v.a
                public void a(d dVar, int i) {
                    if (dVar != null) {
                        if (m.this.f || !b.b(i, 16)) {
                            com.facebook.imagepipeline.o.b a2 = alVar.a();
                            if (m.this.g || !f.b(a2.b())) {
                                dVar.e(q.a(a2, dVar));
                            }
                        }
                        c.this.c((c) dVar, (d) i);
                    }
                }
            }, this.e.f2078a);
            this.f2387c.a(new e(m.this) {
                /* class com.facebook.imagepipeline.n.m.c.AnonymousClass2 */

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void c() {
                    if (c.this.f2387c.h()) {
                        c.this.g.b();
                    }
                }

                @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
                public void a() {
                    if (z) {
                        c.this.f();
                    }
                }
            });
        }

        /* renamed from: b */
        public void a(d dVar, int i) {
            boolean a2 = a(i);
            if (a2 && !d.e(dVar)) {
                c(new com.facebook.common.k.a("Encoded image is not valid."));
            } else if (a(dVar, i)) {
                boolean b2 = b(i, 4);
                if (a2 || b2 || this.f2387c.h()) {
                    this.g.b();
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(float f2) {
            super.a(f2 * 0.99f);
        }

        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            c(th);
        }

        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a() {
            f();
        }

        /* access modifiers changed from: protected */
        public boolean a(d dVar, int i) {
            return this.g.a(dVar, i);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00b5 A[Catch:{ all -> 0x0152 }] */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x00e3  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void c(com.facebook.imagepipeline.j.d r19, int r20) {
            /*
            // Method dump skipped, instructions count: 344
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.m.c.c(com.facebook.imagepipeline.j.d, int):void");
        }

        private Map<String, String> a(com.facebook.imagepipeline.j.b bVar, long j, g gVar, boolean z, String str, String str2, String str3, String str4) {
            if (!this.f2388d.b(this.f2387c.b())) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(gVar.b());
            String valueOf3 = String.valueOf(z);
            if (bVar instanceof com.facebook.imagepipeline.j.c) {
                Bitmap a2 = ((com.facebook.imagepipeline.j.c) bVar).a();
                HashMap hashMap = new HashMap(8);
                hashMap.put("bitmapSize", a2.getWidth() + "x" + a2.getHeight());
                hashMap.put("queueTime", valueOf);
                hashMap.put("hasGoodQuality", valueOf2);
                hashMap.put("isFinal", valueOf3);
                hashMap.put("encodedImageSize", str2);
                hashMap.put("imageFormat", str);
                hashMap.put("requestedImageSize", str3);
                hashMap.put("sampleSize", str4);
                return com.facebook.common.d.f.a(hashMap);
            }
            HashMap hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put("hasGoodQuality", valueOf2);
            hashMap2.put("isFinal", valueOf3);
            hashMap2.put("encodedImageSize", str2);
            hashMap2.put("imageFormat", str);
            hashMap2.put("requestedImageSize", str3);
            hashMap2.put("sampleSize", str4);
            return com.facebook.common.d.f.a(hashMap2);
        }

        private synchronized boolean e() {
            return this.f;
        }

        private void b(boolean z) {
            synchronized (this) {
                if (z) {
                    if (!this.f) {
                        d().b(1.0f);
                        this.f = true;
                        this.g.a();
                    }
                }
            }
        }

        private void a(com.facebook.imagepipeline.j.b bVar, int i) {
            com.facebook.common.h.a a2 = com.facebook.common.h.a.a(bVar);
            try {
                b(a(i));
                d().b(a2, i);
            } finally {
                com.facebook.common.h.a.c(a2);
            }
        }

        private void c(Throwable th) {
            b(true);
            d().b(th);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void f() {
            b(true);
            d().b();
        }
    }

    /* compiled from: DecodeProducer */
    private class a extends c {
        public a(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, al alVar, boolean z) {
            super(kVar, alVar, z);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public synchronized boolean a(d dVar, int i) {
            if (b(i)) {
                return false;
            }
            return super.a(dVar, i);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public int a(d dVar) {
            return dVar.l();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public g c() {
            return com.facebook.imagepipeline.j.f.a(0, false, false);
        }
    }

    /* compiled from: DecodeProducer */
    private class b extends c {

        /* renamed from: c  reason: collision with root package name */
        private final com.facebook.imagepipeline.h.f f2383c;

        /* renamed from: d  reason: collision with root package name */
        private final e f2384d;
        private int e = 0;

        public b(k<com.facebook.common.h.a<com.facebook.imagepipeline.j.b>> kVar, al alVar, com.facebook.imagepipeline.h.f fVar, e eVar, boolean z) {
            super(kVar, alVar, z);
            this.f2383c = (com.facebook.imagepipeline.h.f) i.a(fVar);
            this.f2384d = (e) i.a(eVar);
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public synchronized boolean a(d dVar, int i) {
            boolean a2 = super.a(dVar, i);
            if ((b(i) || b(i, 8)) && !b(i, 4) && d.e(dVar) && dVar.e() == com.facebook.g.b.f1987a) {
                if (!this.f2383c.a(dVar)) {
                    return false;
                }
                int b2 = this.f2383c.b();
                if (b2 <= this.e) {
                    return false;
                }
                if (b2 < this.f2384d.a(this.e) && !this.f2383c.c()) {
                    return false;
                }
                this.e = b2;
            }
            return a2;
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public int a(d dVar) {
            return this.f2383c.a();
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.m.c
        public g c() {
            return this.f2384d.b(this.f2383c.b());
        }
    }
}
