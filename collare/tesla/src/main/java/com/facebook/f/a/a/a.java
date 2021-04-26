package com.facebook.f.a.a;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.f.e.i;
import com.facebook.imagepipeline.j.b;
import com.facebook.imagepipeline.j.c;

/* compiled from: DefaultDrawableFactory */
public class a implements com.facebook.imagepipeline.i.a {

    /* renamed from: a  reason: collision with root package name */
    private final Resources f1808a;

    /* renamed from: b  reason: collision with root package name */
    private final com.facebook.imagepipeline.i.a f1809b;

    @Override // com.facebook.imagepipeline.i.a
    public boolean a(b bVar) {
        return true;
    }

    public a(Resources resources, com.facebook.imagepipeline.i.a aVar) {
        this.f1808a = resources;
        this.f1809b = aVar;
    }

    @Override // com.facebook.imagepipeline.i.a
    public Drawable b(b bVar) {
        if (bVar instanceof c) {
            c cVar = (c) bVar;
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.f1808a, cVar.a());
            if (a(cVar) || b(cVar)) {
                return new i(bitmapDrawable, cVar.h(), cVar.i());
            }
            return bitmapDrawable;
        }
        com.facebook.imagepipeline.i.a aVar = this.f1809b;
        if (aVar == null || !aVar.a(bVar)) {
            return null;
        }
        return this.f1809b.b(bVar);
    }

    private static boolean a(c cVar) {
        return (cVar.h() == 0 || cVar.h() == -1) ? false : true;
    }

    private static boolean b(c cVar) {
        if (cVar.i() == 1 || cVar.i() == 0) {
            return false;
        }
        return true;
    }
}
