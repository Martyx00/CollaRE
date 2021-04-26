package com.facebook.imagepipeline.n;

import com.facebook.g.c;
import com.facebook.imagepipeline.d.e;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;

/* compiled from: DiskCacheWriteProducer */
public class p implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final e f2406a;

    /* renamed from: b  reason: collision with root package name */
    private final e f2407b;

    /* renamed from: c  reason: collision with root package name */
    private final f f2408c;

    /* renamed from: d  reason: collision with root package name */
    private final ak<d> f2409d;

    public p(e eVar, e eVar2, f fVar, ak<d> akVar) {
        this.f2406a = eVar;
        this.f2407b = eVar2;
        this.f2408c = fVar;
        this.f2409d = akVar;
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        b(kVar, alVar);
    }

    private void b(k<d> kVar, al alVar) {
        if (alVar.e().a() >= b.EnumC0051b.DISK_CACHE.a()) {
            kVar.b(null, 1);
            return;
        }
        if (alVar.a().n()) {
            kVar = new a(kVar, alVar, this.f2406a, this.f2407b, this.f2408c);
        }
        this.f2409d.a(kVar, alVar);
    }

    /* access modifiers changed from: private */
    /* compiled from: DiskCacheWriteProducer */
    public static class a extends n<d, d> {

        /* renamed from: a  reason: collision with root package name */
        private final al f2410a;

        /* renamed from: b  reason: collision with root package name */
        private final e f2411b;

        /* renamed from: c  reason: collision with root package name */
        private final e f2412c;

        /* renamed from: d  reason: collision with root package name */
        private final f f2413d;

        private a(k<d> kVar, al alVar, e eVar, e eVar2, f fVar) {
            super(kVar);
            this.f2410a = alVar;
            this.f2411b = eVar;
            this.f2412c = eVar2;
            this.f2413d = fVar;
        }

        public void a(d dVar, int i) {
            if (b(i) || dVar == null || c(i, 10) || dVar.e() == c.f1991a) {
                d().b(dVar, i);
                return;
            }
            b a2 = this.f2410a.a();
            com.facebook.b.a.d c2 = this.f2413d.c(a2, this.f2410a.d());
            if (a2.a() == b.a.SMALL) {
                this.f2412c.a(c2, dVar);
            } else {
                this.f2411b.a(c2, dVar);
            }
            d().b(dVar, i);
        }
    }
}
