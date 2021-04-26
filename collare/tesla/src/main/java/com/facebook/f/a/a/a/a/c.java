package com.facebook.f.a.a.a.a;

import com.facebook.common.time.b;
import com.facebook.f.a.a.a.h;
import com.facebook.imagepipeline.k.a;

/* compiled from: ImagePerfRequestListener */
public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    private final b f1816a;

    /* renamed from: b  reason: collision with root package name */
    private final h f1817b;

    public c(b bVar, h hVar) {
        this.f1816a = bVar;
        this.f1817b = hVar;
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, Object obj, String str, boolean z) {
        this.f1817b.f(this.f1816a.now());
        this.f1817b.a(bVar);
        this.f1817b.a(obj);
        this.f1817b.b(str);
        this.f1817b.c(z);
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, boolean z) {
        this.f1817b.g(this.f1816a.now());
        this.f1817b.a(bVar);
        this.f1817b.b(str);
        this.f1817b.c(z);
        this.f1817b.b(true);
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a(com.facebook.imagepipeline.o.b bVar, String str, Throwable th, boolean z) {
        this.f1817b.g(this.f1816a.now());
        this.f1817b.a(bVar);
        this.f1817b.b(str);
        this.f1817b.c(z);
        this.f1817b.b(false);
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.k.c
    public void a_(String str) {
        this.f1817b.g(this.f1816a.now());
        this.f1817b.b(str);
        this.f1817b.a(true);
    }
}
