package com.facebook.f.a.a.a.a;

import android.graphics.drawable.Animatable;
import com.facebook.common.time.b;
import com.facebook.f.a.a.a.g;
import com.facebook.f.a.a.a.h;
import com.facebook.f.c.c;
import com.facebook.imagepipeline.j.e;

/* compiled from: ImagePerfControllerListener */
public class a extends c<e> {

    /* renamed from: a  reason: collision with root package name */
    private final b f1811a;

    /* renamed from: b  reason: collision with root package name */
    private final h f1812b;

    /* renamed from: c  reason: collision with root package name */
    private final g f1813c;

    public a(b bVar, h hVar, g gVar) {
        this.f1811a = bVar;
        this.f1812b = hVar;
        this.f1813c = gVar;
    }

    @Override // com.facebook.f.c.c, com.facebook.f.c.d
    public void a(String str, Object obj) {
        this.f1812b.a(this.f1811a.now());
        this.f1812b.a(str);
        this.f1812b.a(obj);
        this.f1813c.a(this.f1812b, 0);
    }

    /* renamed from: a */
    public void b(String str, e eVar) {
        this.f1812b.b(this.f1811a.now());
        this.f1812b.a(str);
        this.f1812b.a(eVar);
        this.f1813c.a(this.f1812b, 2);
    }

    public void a(String str, e eVar, Animatable animatable) {
        this.f1812b.c(this.f1811a.now());
        this.f1812b.a(str);
        this.f1812b.a(eVar);
        this.f1812b.b(true);
        this.f1813c.a(this.f1812b, 3);
    }

    @Override // com.facebook.f.c.c, com.facebook.f.c.d
    public void a(String str, Throwable th) {
        this.f1812b.d(this.f1811a.now());
        this.f1812b.a(str);
        this.f1812b.b(false);
        this.f1813c.a(this.f1812b, 5);
    }

    @Override // com.facebook.f.c.c, com.facebook.f.c.d
    public void a(String str) {
        super.a(str);
        int b2 = this.f1812b.b();
        if (b2 != 3 && b2 != 5) {
            this.f1812b.e(this.f1811a.now());
            this.f1812b.a(str);
            this.f1812b.a(true);
            this.f1813c.a(this.f1812b, 4);
        }
    }
}
