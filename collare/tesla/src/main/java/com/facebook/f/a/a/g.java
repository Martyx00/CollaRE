package com.facebook.f.a.a;

import android.content.res.Resources;
import com.facebook.b.a.d;
import com.facebook.common.d.e;
import com.facebook.common.d.l;
import com.facebook.f.b.a;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.j.b;
import java.util.concurrent.Executor;

/* compiled from: PipelineDraweeControllerFactory */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private Resources f1851a;

    /* renamed from: b  reason: collision with root package name */
    private a f1852b;

    /* renamed from: c  reason: collision with root package name */
    private com.facebook.imagepipeline.i.a f1853c;

    /* renamed from: d  reason: collision with root package name */
    private Executor f1854d;
    private p<d, b> e;
    private e<com.facebook.imagepipeline.i.a> f;
    private l<Boolean> g;

    public void a(Resources resources, a aVar, com.facebook.imagepipeline.i.a aVar2, Executor executor, p<d, b> pVar, e<com.facebook.imagepipeline.i.a> eVar, l<Boolean> lVar) {
        this.f1851a = resources;
        this.f1852b = aVar;
        this.f1853c = aVar2;
        this.f1854d = executor;
        this.e = pVar;
        this.f = eVar;
        this.g = lVar;
    }

    public d a() {
        d a2 = a(this.f1851a, this.f1852b, this.f1853c, this.f1854d, this.e, this.f);
        l<Boolean> lVar = this.g;
        if (lVar != null) {
            a2.a(lVar.b().booleanValue());
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public d a(Resources resources, a aVar, com.facebook.imagepipeline.i.a aVar2, Executor executor, p<d, b> pVar, e<com.facebook.imagepipeline.i.a> eVar) {
        return new d(resources, aVar, aVar2, executor, pVar, eVar);
    }
}
