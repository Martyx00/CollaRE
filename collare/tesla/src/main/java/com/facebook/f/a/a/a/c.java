package com.facebook.f.a.a.a;

import com.facebook.imagepipeline.k.a;

/* compiled from: ImageOriginRequestListener */
public class c extends a {

    /* renamed from: a  reason: collision with root package name */
    private String f1818a;

    /* renamed from: b  reason: collision with root package name */
    private final b f1819b;

    public c(String str, b bVar) {
        this.f1819b = bVar;
        a(str);
    }

    public void a(String str) {
        this.f1818a = str;
    }

    @Override // com.facebook.imagepipeline.k.a, com.facebook.imagepipeline.n.an
    public void a(String str, String str2, boolean z) {
        b bVar = this.f1819b;
        if (bVar != null) {
            bVar.a(this.f1818a, d.a(str2), z);
        }
    }
}
