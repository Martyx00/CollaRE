package com.facebook.f.a.a;

import android.content.Context;
import com.facebook.common.d.l;
import com.facebook.f.b.a;
import com.facebook.f.c.d;
import com.facebook.imagepipeline.f.g;
import com.facebook.imagepipeline.f.j;
import java.util.Set;

/* compiled from: PipelineDraweeControllerBuilderSupplier */
public class f implements l<e> {

    /* renamed from: a  reason: collision with root package name */
    private final Context f1847a;

    /* renamed from: b  reason: collision with root package name */
    private final g f1848b;

    /* renamed from: c  reason: collision with root package name */
    private final g f1849c;

    /* renamed from: d  reason: collision with root package name */
    private final Set<d> f1850d;

    public f(Context context, b bVar) {
        this(context, j.a(), bVar);
    }

    public f(Context context, j jVar, b bVar) {
        this(context, jVar, null, bVar);
    }

    public f(Context context, j jVar, Set<d> set, b bVar) {
        this.f1847a = context;
        this.f1848b = jVar.h();
        if (bVar == null || bVar.b() == null) {
            this.f1849c = new g();
        } else {
            this.f1849c = bVar.b();
        }
        this.f1849c.a(context.getResources(), a.a(), jVar.b(context), com.facebook.common.b.f.b(), this.f1848b.d(), bVar != null ? bVar.a() : null, bVar != null ? bVar.c() : null);
        this.f1850d = set;
    }

    /* renamed from: a */
    public e b() {
        return new e(this.f1847a, this.f1849c, this.f1848b, this.f1850d);
    }
}
