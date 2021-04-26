package com.facebook.imagepipeline.a.a;

import com.facebook.b.a.d;
import com.facebook.imagepipeline.c.f;
import com.facebook.imagepipeline.d.h;
import com.facebook.imagepipeline.f.e;

/* compiled from: AnimatedFactoryProvider */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f2004a;

    /* renamed from: b  reason: collision with root package name */
    private static a f2005b;

    public static a a(f fVar, e eVar, h<d, com.facebook.imagepipeline.j.b> hVar) {
        if (!f2004a) {
            try {
                f2005b = (a) Class.forName("com.facebook.fresco.animation.factory.AnimatedFactoryV2Impl").getConstructor(f.class, e.class, h.class).newInstance(fVar, eVar, hVar);
            } catch (Throwable unused) {
            }
            if (f2005b != null) {
                f2004a = true;
            }
        }
        return f2005b;
    }
}
