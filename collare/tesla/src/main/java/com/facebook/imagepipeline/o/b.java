package com.facebook.imagepipeline.o;

import android.net.Uri;
import com.facebook.common.d.h;
import com.facebook.imagepipeline.e.d;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.e.f;
import com.facebook.imagepipeline.k.c;
import java.io.File;

/* compiled from: ImageRequest */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final a f2463a;

    /* renamed from: b  reason: collision with root package name */
    private final Uri f2464b;

    /* renamed from: c  reason: collision with root package name */
    private final int f2465c = a(this.f2464b);

    /* renamed from: d  reason: collision with root package name */
    private File f2466d;
    private final boolean e;
    private final boolean f;
    private final com.facebook.imagepipeline.e.b g;
    private final e h;
    private final f i;
    private final com.facebook.imagepipeline.e.a j;
    private final d k;
    private final EnumC0051b l;
    private final boolean m;
    private final boolean n;
    private final d o;
    private final c p;

    /* compiled from: ImageRequest */
    public enum a {
        SMALL,
        DEFAULT
    }

    protected b(c cVar) {
        this.f2463a = cVar.g();
        this.f2464b = cVar.a();
        this.e = cVar.h();
        this.f = cVar.i();
        this.g = cVar.f();
        this.h = cVar.c();
        this.i = cVar.d() == null ? f.a() : cVar.d();
        this.j = cVar.e();
        this.k = cVar.l();
        this.l = cVar.b();
        this.m = cVar.j();
        this.n = cVar.k();
        this.o = cVar.m();
        this.p = cVar.n();
    }

    public a a() {
        return this.f2463a;
    }

    public Uri b() {
        return this.f2464b;
    }

    public int c() {
        return this.f2465c;
    }

    public int d() {
        e eVar = this.h;
        if (eVar != null) {
            return eVar.f2090a;
        }
        return 2048;
    }

    public int e() {
        e eVar = this.h;
        if (eVar != null) {
            return eVar.f2091b;
        }
        return 2048;
    }

    public e f() {
        return this.h;
    }

    public f g() {
        return this.i;
    }

    public com.facebook.imagepipeline.e.a h() {
        return this.j;
    }

    public com.facebook.imagepipeline.e.b i() {
        return this.g;
    }

    public boolean j() {
        return this.e;
    }

    public boolean k() {
        return this.f;
    }

    public d l() {
        return this.k;
    }

    public EnumC0051b m() {
        return this.l;
    }

    public boolean n() {
        return this.m;
    }

    public boolean o() {
        return this.n;
    }

    public synchronized File p() {
        if (this.f2466d == null) {
            this.f2466d = new File(this.f2464b.getPath());
        }
        return this.f2466d;
    }

    public d q() {
        return this.o;
    }

    public c r() {
        return this.p;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (!h.a(this.f2464b, bVar.f2464b) || !h.a(this.f2463a, bVar.f2463a) || !h.a(this.f2466d, bVar.f2466d) || !h.a(this.j, bVar.j) || !h.a(this.g, bVar.g) || !h.a(this.h, bVar.h) || !h.a(this.i, bVar.i)) {
            return false;
        }
        d dVar = this.o;
        com.facebook.b.a.d dVar2 = null;
        com.facebook.b.a.d a2 = dVar != null ? dVar.a() : null;
        d dVar3 = bVar.o;
        if (dVar3 != null) {
            dVar2 = dVar3.a();
        }
        return h.a(a2, dVar2);
    }

    public int hashCode() {
        d dVar = this.o;
        return h.a(this.f2463a, this.f2464b, this.f2466d, this.j, this.g, this.h, this.i, dVar != null ? dVar.a() : null);
    }

    public String toString() {
        return h.a(this).a("uri", this.f2464b).a("cacheChoice", this.f2463a).a("decodeOptions", this.g).a("postprocessor", this.o).a("priority", this.k).a("resizeOptions", this.h).a("rotationOptions", this.i).a("bytesRange", this.j).toString();
    }

    /* renamed from: com.facebook.imagepipeline.o.b$b  reason: collision with other inner class name */
    /* compiled from: ImageRequest */
    public enum EnumC0051b {
        FULL_FETCH(1),
        DISK_CACHE(2),
        ENCODED_MEMORY_CACHE(3),
        BITMAP_MEMORY_CACHE(4);
        
        private int e;

        private EnumC0051b(int i) {
            this.e = i;
        }

        public int a() {
            return this.e;
        }

        public static EnumC0051b a(EnumC0051b bVar, EnumC0051b bVar2) {
            return bVar.a() > bVar2.a() ? bVar : bVar2;
        }
    }

    private static int a(Uri uri) {
        if (uri == null) {
            return -1;
        }
        if (com.facebook.common.k.f.b(uri)) {
            return 0;
        }
        if (com.facebook.common.k.f.c(uri)) {
            return com.facebook.common.f.a.a(com.facebook.common.f.a.b(uri.getPath())) ? 2 : 3;
        }
        if (com.facebook.common.k.f.d(uri)) {
            return 4;
        }
        if (com.facebook.common.k.f.g(uri)) {
            return 5;
        }
        if (com.facebook.common.k.f.h(uri)) {
            return 6;
        }
        if (com.facebook.common.k.f.j(uri)) {
            return 7;
        }
        if (com.facebook.common.k.f.i(uri)) {
            return 8;
        }
        return -1;
    }
}
