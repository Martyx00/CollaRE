package com.facebook.f.i;

import android.net.Uri;
import com.facebook.common.d.l;
import com.facebook.f.c.b;

/* compiled from: SimpleDraweeView */
public class f extends d {

    /* renamed from: a  reason: collision with root package name */
    private static l<? extends b> f1981a;

    /* renamed from: b  reason: collision with root package name */
    private b f1982b;

    public static void a(l<? extends b> lVar) {
        f1981a = lVar;
    }

    /* access modifiers changed from: protected */
    public b getControllerBuilder() {
        return this.f1982b;
    }

    public void setImageRequest(com.facebook.imagepipeline.o.b bVar) {
        setController(this.f1982b.b(bVar).c(getController()).o());
    }

    @Override // com.facebook.f.i.c
    public void setImageURI(Uri uri) {
        a(uri, (Object) null);
    }

    public void setImageURI(String str) {
        a(str, (Object) null);
    }

    public void a(Uri uri, Object obj) {
        setController(this.f1982b.a(obj).b(uri).c(getController()).o());
    }

    public void a(String str, Object obj) {
        a(str != null ? Uri.parse(str) : null, obj);
    }

    public void setActualImageResource(int i) {
        a(i, (Object) null);
    }

    public void a(int i, Object obj) {
        a(com.facebook.common.k.f.a(i), obj);
    }

    @Override // com.facebook.f.i.c
    public void setImageResource(int i) {
        super.setImageResource(i);
    }
}
