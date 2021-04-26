package com.airbnb.android.react.maps;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.facebook.c.c;
import com.facebook.common.h.a;
import com.facebook.f.c.d;
import com.facebook.f.e.q;
import com.facebook.f.i.b;
import com.facebook.imagepipeline.j.e;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ImageReader */
public class n {

    /* renamed from: a  reason: collision with root package name */
    private final m f1626a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f1627b;

    /* renamed from: c  reason: collision with root package name */
    private final Resources f1628c;

    /* renamed from: d  reason: collision with root package name */
    private final b<?> f1629d;
    private c<a<com.facebook.imagepipeline.j.b>> e;
    private final d<e> f = new com.facebook.f.c.c<e>() {
        /* class com.airbnb.android.react.maps.n.AnonymousClass1 */

        /* JADX WARNING: Removed duplicated region for block: B:22:0x006a  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(java.lang.String r3, com.facebook.imagepipeline.j.e r4, android.graphics.drawable.Animatable r5) {
            /*
            // Method dump skipped, instructions count: 110
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.react.maps.n.AnonymousClass1.a(java.lang.String, com.facebook.imagepipeline.j.e, android.graphics.drawable.Animatable):void");
        }
    };

    public n(Context context, Resources resources, m mVar) {
        this.f1627b = context;
        this.f1628c = resources;
        this.f1626a = mVar;
        this.f1629d = b.a(a(resources), context);
        this.f1629d.b();
    }

    private com.facebook.f.f.a a(Resources resources) {
        return new com.facebook.f.f.b(resources).a(q.b.f1945c).a(0).r();
    }

    public void a(String str) {
        if (str == null) {
            this.f1626a.setIconBitmapDescriptor(null);
            this.f1626a.a();
        } else if (str.startsWith("http://") || str.startsWith("https://") || str.startsWith("file://") || str.startsWith("asset://")) {
            com.facebook.imagepipeline.o.b o = com.facebook.imagepipeline.o.c.a(Uri.parse(str)).o();
            this.e = com.facebook.f.a.a.c.c().b(o, this);
            this.f1629d.a((com.facebook.f.h.a) ((com.facebook.f.a.a.e) ((com.facebook.f.a.a.e) ((com.facebook.f.a.a.e) com.facebook.f.a.a.c.a().b(o)).a((d) this.f)).b(this.f1629d.d())).j());
        } else {
            BitmapDescriptor c2 = c(str);
            if (c2 != null) {
                this.f1626a.setIconBitmapDescriptor(c2);
                this.f1626a.setIconBitmap(BitmapFactory.decodeResource(this.f1628c, b(str)));
            }
            this.f1626a.a();
        }
    }

    private int b(String str) {
        return this.f1628c.getIdentifier(str, "drawable", this.f1627b.getPackageName());
    }

    private BitmapDescriptor c(String str) {
        return BitmapDescriptorFactory.fromResource(b(str));
    }
}
