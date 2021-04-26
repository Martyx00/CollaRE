package com.facebook.f.a.a.a.a;

import com.facebook.f.a.a.a.g;
import com.facebook.f.a.a.a.h;

/* compiled from: ImagePerfImageOriginListener */
public class b implements com.facebook.f.a.a.a.b {

    /* renamed from: a  reason: collision with root package name */
    private final h f1814a;

    /* renamed from: b  reason: collision with root package name */
    private final g f1815b;

    public b(h hVar, g gVar) {
        this.f1814a = hVar;
        this.f1815b = gVar;
    }

    @Override // com.facebook.f.a.a.a.b
    public void a(String str, int i, boolean z) {
        this.f1814a.b(i);
        this.f1814a.b(z);
        this.f1815b.a(this.f1814a, 1);
    }
}
