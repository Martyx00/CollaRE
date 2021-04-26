package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.facebook.b.a.d;
import com.facebook.b.a.i;
import com.facebook.imagepipeline.o.b;

/* compiled from: DefaultCacheKeyFactory */
public class j implements f {

    /* renamed from: a  reason: collision with root package name */
    private static j f2064a;

    /* access modifiers changed from: protected */
    public Uri a(Uri uri) {
        return uri;
    }

    protected j() {
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (f2064a == null) {
                f2064a = new j();
            }
            jVar = f2064a;
        }
        return jVar;
    }

    @Override // com.facebook.imagepipeline.d.f
    public d a(b bVar, Object obj) {
        return new c(a(bVar.b()).toString(), bVar.f(), bVar.g(), bVar.i(), null, null, obj);
    }

    @Override // com.facebook.imagepipeline.d.f
    public d b(b bVar, Object obj) {
        String str;
        d dVar;
        com.facebook.imagepipeline.o.d q = bVar.q();
        if (q != null) {
            d a2 = q.a();
            str = q.getClass().getName();
            dVar = a2;
        } else {
            dVar = null;
            str = null;
        }
        return new c(a(bVar.b()).toString(), bVar.f(), bVar.g(), bVar.i(), dVar, str, obj);
    }

    @Override // com.facebook.imagepipeline.d.f
    public d c(b bVar, Object obj) {
        return a(bVar, bVar.b(), obj);
    }

    @Override // com.facebook.imagepipeline.d.f
    public d a(b bVar, Uri uri, Object obj) {
        return new i(a(uri).toString());
    }
}
