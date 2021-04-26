package com.facebook.imagepipeline.f;

import android.content.Context;
import com.facebook.b.a.d;
import com.facebook.common.d.l;
import com.facebook.common.g.g;
import com.facebook.common.g.h;
import com.facebook.common.l.b;
import com.facebook.imagepipeline.d.f;
import com.facebook.imagepipeline.d.p;
import com.facebook.imagepipeline.f.h;
import com.facebook.imagepipeline.h.e;

/* compiled from: ImagePipelineExperiments */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private final boolean f2121a;

    /* renamed from: b  reason: collision with root package name */
    private final b.a f2122b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f2123c;

    /* renamed from: d  reason: collision with root package name */
    private final com.facebook.common.l.b f2124d;
    private final boolean e;
    private final boolean f;
    private final int g;
    private final int h;
    private boolean i;
    private final boolean j;
    private final c k;
    private final l<Boolean> l;

    /* compiled from: ImagePipelineExperiments */
    public interface c {
        l a(Context context, com.facebook.common.g.a aVar, com.facebook.imagepipeline.h.c cVar, e eVar, boolean z, boolean z2, boolean z3, e eVar2, h hVar, p<d, com.facebook.imagepipeline.j.b> pVar, p<d, g> pVar2, com.facebook.imagepipeline.d.e eVar3, com.facebook.imagepipeline.d.e eVar4, f fVar, com.facebook.imagepipeline.c.f fVar2, int i, int i2, boolean z4);
    }

    private i(a aVar) {
        this.f2121a = aVar.f2128d;
        this.f2122b = aVar.e;
        this.f2123c = aVar.f;
        this.f2124d = aVar.g;
        this.e = aVar.h;
        this.f = aVar.i;
        this.g = aVar.j;
        this.h = aVar.k;
        this.i = aVar.f2125a;
        this.j = aVar.l;
        if (aVar.m == null) {
            this.k = new b();
        } else {
            this.k = aVar.m;
        }
        this.l = aVar.f2126b;
    }

    public boolean a() {
        return this.e;
    }

    public boolean b() {
        return this.f2121a;
    }

    public boolean c() {
        return this.f2123c;
    }

    public b.a d() {
        return this.f2122b;
    }

    public com.facebook.common.l.b e() {
        return this.f2124d;
    }

    public boolean f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public boolean i() {
        return this.j;
    }

    public c j() {
        return this.k;
    }

    public boolean k() {
        return this.i;
    }

    public l<Boolean> l() {
        return this.l;
    }

    /* compiled from: ImagePipelineExperiments */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public boolean f2125a = false;

        /* renamed from: b  reason: collision with root package name */
        public l<Boolean> f2126b;

        /* renamed from: c  reason: collision with root package name */
        private final h.a f2127c;

        /* renamed from: d  reason: collision with root package name */
        private boolean f2128d = false;
        private b.a e;
        private boolean f = false;
        private com.facebook.common.l.b g;
        private boolean h = false;
        private boolean i = false;
        private int j = 0;
        private int k = 0;
        private boolean l = false;
        private c m;

        public a(h.a aVar) {
            this.f2127c = aVar;
        }

        public i a() {
            return new i(this);
        }
    }

    /* compiled from: ImagePipelineExperiments */
    public static class b implements c {
        @Override // com.facebook.imagepipeline.f.i.c
        public l a(Context context, com.facebook.common.g.a aVar, com.facebook.imagepipeline.h.c cVar, e eVar, boolean z, boolean z2, boolean z3, e eVar2, com.facebook.common.g.h hVar, p<d, com.facebook.imagepipeline.j.b> pVar, p<d, g> pVar2, com.facebook.imagepipeline.d.e eVar3, com.facebook.imagepipeline.d.e eVar4, f fVar, com.facebook.imagepipeline.c.f fVar2, int i, int i2, boolean z4) {
            return new l(context, aVar, cVar, eVar, z, z2, z3, eVar2, hVar, pVar, pVar2, eVar3, eVar4, fVar, fVar2, i, i2, z4);
        }
    }
}
