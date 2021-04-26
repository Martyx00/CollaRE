package com.facebook.f.f;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import com.facebook.common.d.i;
import com.facebook.common.e.a;
import com.facebook.f.e.c;
import com.facebook.f.e.g;
import com.facebook.f.e.j;
import com.facebook.f.e.k;
import com.facebook.f.e.l;
import com.facebook.f.e.m;
import com.facebook.f.e.o;
import com.facebook.f.e.p;
import com.facebook.f.e.q;
import com.facebook.f.f.d;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: WrappingUtils */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final Drawable f1964a = new ColorDrawable(0);

    static Drawable a(Drawable drawable, q.b bVar) {
        return a(drawable, bVar, (PointF) null);
    }

    static Drawable a(Drawable drawable, q.b bVar, PointF pointF) {
        if (drawable == null || bVar == null) {
            return drawable;
        }
        p pVar = new p(drawable, bVar);
        if (pointF != null) {
            pVar.a(pointF);
        }
        return pVar;
    }

    static p a(c cVar, q.b bVar) {
        Drawable a2 = a(cVar.a(f1964a), bVar);
        cVar.a(a2);
        i.a(a2, "Parent has no child drawable!");
        return (p) a2;
    }

    static void a(c cVar, d dVar) {
        Drawable a2 = cVar.a();
        if (dVar == null || dVar.c() != d.a.OVERLAY_COLOR) {
            if (a2 instanceof m) {
                cVar.a(((m) a2).b(f1964a));
                f1964a.setCallback(null);
            }
        } else if (a2 instanceof m) {
            m mVar = (m) a2;
            a((j) mVar, dVar);
            mVar.a(dVar.d());
        } else {
            cVar.a(a(cVar.a(f1964a), dVar));
        }
    }

    static void a(c cVar, d dVar, Resources resources) {
        c a2 = a(cVar);
        Drawable a3 = a2.a();
        if (dVar == null || dVar.c() != d.a.BITMAP_ONLY) {
            if (a3 instanceof j) {
                a((j) a3);
            }
        } else if (a3 instanceof j) {
            a((j) a3, dVar);
        } else if (a3 != null) {
            a2.a(f1964a);
            a2.a(b(a3, dVar, resources));
        }
    }

    static Drawable a(Drawable drawable, d dVar) {
        if (drawable == null || dVar == null || dVar.c() != d.a.OVERLAY_COLOR) {
            return drawable;
        }
        m mVar = new m(drawable);
        a((j) mVar, dVar);
        mVar.a(dVar.d());
        return mVar;
    }

    static Drawable a(Drawable drawable, d dVar, Resources resources) {
        if (drawable == null || dVar == null || dVar.c() != d.a.BITMAP_ONLY) {
            return drawable;
        }
        if (!(drawable instanceof g)) {
            return b(drawable, dVar, resources);
        }
        c a2 = a((g) drawable);
        a2.a(b(a2.a(f1964a), dVar, resources));
        return drawable;
    }

    private static Drawable b(Drawable drawable, d dVar, Resources resources) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            k kVar = new k(resources, bitmapDrawable.getBitmap(), bitmapDrawable.getPaint());
            a((j) kVar, dVar);
            return kVar;
        } else if (drawable instanceof NinePatchDrawable) {
            o oVar = new o((NinePatchDrawable) drawable);
            a((j) oVar, dVar);
            return oVar;
        } else if (!(drawable instanceof ColorDrawable) || Build.VERSION.SDK_INT < 11) {
            a.b("WrappingUtils", "Don't know how to round that drawable: %s", drawable);
            return drawable;
        } else {
            l a2 = l.a((ColorDrawable) drawable);
            a((j) a2, dVar);
            return a2;
        }
    }

    static void a(j jVar, d dVar) {
        jVar.a(dVar.a());
        jVar.a(dVar.b());
        jVar.a(dVar.f(), dVar.e());
        jVar.b(dVar.g());
        jVar.b(dVar.h());
    }

    static void a(j jVar) {
        jVar.a(false);
        jVar.a(BitmapDescriptorFactory.HUE_RED);
        jVar.a(0, BitmapDescriptorFactory.HUE_RED);
        jVar.b(BitmapDescriptorFactory.HUE_RED);
        jVar.b(false);
    }

    static c a(c cVar) {
        while (true) {
            Drawable a2 = cVar.a();
            if (a2 == cVar || !(a2 instanceof c)) {
                return cVar;
            }
            cVar = (c) a2;
        }
        return cVar;
    }
}
