package com.facebook.imagepipeline.n;

import com.facebook.common.d.i;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.j.d;

/* compiled from: ThumbnailBranchProducer */
public class aw implements ak<d> {

    /* renamed from: a  reason: collision with root package name */
    private final ax<d>[] f2342a;

    public aw(ax<d>... axVarArr) {
        this.f2342a = (ax[]) i.a(axVarArr);
        i.a(0, this.f2342a.length);
    }

    @Override // com.facebook.imagepipeline.n.ak
    public void a(k<d> kVar, al alVar) {
        if (alVar.a().f() == null) {
            kVar.b(null, 1);
        } else if (!a(0, kVar, alVar)) {
            kVar.b(null, 1);
        }
    }

    /* access modifiers changed from: private */
    /* compiled from: ThumbnailBranchProducer */
    public class a extends n<d, d> {

        /* renamed from: b  reason: collision with root package name */
        private final al f2344b;

        /* renamed from: c  reason: collision with root package name */
        private final int f2345c;

        /* renamed from: d  reason: collision with root package name */
        private final e f2346d = this.f2344b.a().f();

        public a(k<d> kVar, al alVar, int i) {
            super(kVar);
            this.f2344b = alVar;
            this.f2345c = i;
        }

        /* access modifiers changed from: protected */
        public void a(d dVar, int i) {
            if (dVar != null && (b(i) || ay.a(dVar, this.f2346d))) {
                d().b(dVar, i);
            } else if (a(i)) {
                d.d(dVar);
                if (!aw.this.a(this.f2345c + 1, d(), this.f2344b)) {
                    d().b(null, 1);
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.n.n, com.facebook.imagepipeline.n.b
        public void a(Throwable th) {
            if (!aw.this.a(this.f2345c + 1, d(), this.f2344b)) {
                d().b(th);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean a(int i, k<d> kVar, al alVar) {
        int a2 = a(i, alVar.a().f());
        if (a2 == -1) {
            return false;
        }
        this.f2342a[a2].a(new a(kVar, alVar, a2), alVar);
        return true;
    }

    private int a(int i, e eVar) {
        while (true) {
            ax<d>[] axVarArr = this.f2342a;
            if (i >= axVarArr.length) {
                return -1;
            }
            if (axVarArr[i].a(eVar)) {
                return i;
            }
            i++;
        }
    }
}
