package com.facebook.f.d.a;

import android.graphics.drawable.Animatable;
import com.facebook.f.c.c;

/* compiled from: ImageLoadingTimeControllerListener */
public class a extends c {

    /* renamed from: a  reason: collision with root package name */
    private long f1895a = -1;

    /* renamed from: b  reason: collision with root package name */
    private long f1896b = -1;

    /* renamed from: c  reason: collision with root package name */
    private b f1897c;

    public a(b bVar) {
        this.f1897c = bVar;
    }

    @Override // com.facebook.f.c.c, com.facebook.f.c.d
    public void a(String str, Object obj) {
        this.f1895a = System.currentTimeMillis();
    }

    @Override // com.facebook.f.c.c, com.facebook.f.c.d
    public void a(String str, Object obj, Animatable animatable) {
        this.f1896b = System.currentTimeMillis();
        b bVar = this.f1897c;
        if (bVar != null) {
            bVar.a(this.f1896b - this.f1895a);
        }
    }
}
