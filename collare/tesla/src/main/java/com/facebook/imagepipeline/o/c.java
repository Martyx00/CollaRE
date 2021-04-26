package com.facebook.imagepipeline.o;

import android.net.Uri;
import com.facebook.common.d.i;
import com.facebook.imagepipeline.e.d;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.e.f;
import com.facebook.imagepipeline.f.h;
import com.facebook.imagepipeline.o.b;

/* compiled from: ImageRequestBuilder */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private Uri f2474a = null;

    /* renamed from: b  reason: collision with root package name */
    private b.EnumC0051b f2475b = b.EnumC0051b.FULL_FETCH;

    /* renamed from: c  reason: collision with root package name */
    private e f2476c = null;

    /* renamed from: d  reason: collision with root package name */
    private f f2477d = null;
    private com.facebook.imagepipeline.e.b e = com.facebook.imagepipeline.e.b.a();
    private b.a f = b.a.DEFAULT;
    private boolean g = h.f().a();
    private boolean h = false;
    private d i = d.HIGH;
    private d j = null;
    private boolean k = true;
    private boolean l = true;
    private com.facebook.imagepipeline.k.c m;
    private com.facebook.imagepipeline.e.a n = null;

    public static c a(Uri uri) {
        return new c().b(uri);
    }

    public static c a(b bVar) {
        return a(bVar.b()).a(bVar.i()).a(bVar.h()).a(bVar.a()).c(bVar.k()).a(bVar.m()).a(bVar.q()).b(bVar.j()).a(bVar.l()).a(bVar.f()).a(bVar.r()).a(bVar.g());
    }

    private c() {
    }

    public c b(Uri uri) {
        i.a(uri);
        this.f2474a = uri;
        return this;
    }

    public Uri a() {
        return this.f2474a;
    }

    public c a(b.EnumC0051b bVar) {
        this.f2475b = bVar;
        return this;
    }

    public b.EnumC0051b b() {
        return this.f2475b;
    }

    @Deprecated
    public c a(boolean z) {
        if (z) {
            return a(f.a());
        }
        return a(f.b());
    }

    public c a(e eVar) {
        this.f2476c = eVar;
        return this;
    }

    public e c() {
        return this.f2476c;
    }

    public c a(f fVar) {
        this.f2477d = fVar;
        return this;
    }

    public f d() {
        return this.f2477d;
    }

    public c a(com.facebook.imagepipeline.e.a aVar) {
        this.n = aVar;
        return this;
    }

    public com.facebook.imagepipeline.e.a e() {
        return this.n;
    }

    public c a(com.facebook.imagepipeline.e.b bVar) {
        this.e = bVar;
        return this;
    }

    public com.facebook.imagepipeline.e.b f() {
        return this.e;
    }

    public c a(b.a aVar) {
        this.f = aVar;
        return this;
    }

    public b.a g() {
        return this.f;
    }

    public c b(boolean z) {
        this.g = z;
        return this;
    }

    public boolean h() {
        return this.g;
    }

    public c c(boolean z) {
        this.h = z;
        return this;
    }

    public boolean i() {
        return this.h;
    }

    public boolean j() {
        return this.k && com.facebook.common.k.f.b(this.f2474a);
    }

    public boolean k() {
        return this.l;
    }

    public c a(d dVar) {
        this.i = dVar;
        return this;
    }

    public d l() {
        return this.i;
    }

    public c a(d dVar) {
        this.j = dVar;
        return this;
    }

    public d m() {
        return this.j;
    }

    public c a(com.facebook.imagepipeline.k.c cVar) {
        this.m = cVar;
        return this;
    }

    public com.facebook.imagepipeline.k.c n() {
        return this.m;
    }

    public b o() {
        p();
        return new b(this);
    }

    /* compiled from: ImageRequestBuilder */
    public static class a extends RuntimeException {
        public a(String str) {
            super("Invalid request builder: " + str);
        }
    }

    /* access modifiers changed from: protected */
    public void p() {
        Uri uri = this.f2474a;
        if (uri != null) {
            if (com.facebook.common.k.f.h(uri)) {
                if (!this.f2474a.isAbsolute()) {
                    throw new a("Resource URI path must be absolute.");
                } else if (!this.f2474a.getPath().isEmpty()) {
                    try {
                        Integer.parseInt(this.f2474a.getPath().substring(1));
                    } catch (NumberFormatException unused) {
                        throw new a("Resource URI path must be a resource id.");
                    }
                } else {
                    throw new a("Resource URI must not be empty");
                }
            }
            if (com.facebook.common.k.f.g(this.f2474a) && !this.f2474a.isAbsolute()) {
                throw new a("Asset URI path must be absolute.");
            }
            return;
        }
        throw new a("Source must be set!");
    }
}
