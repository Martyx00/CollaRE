package com.facebook.f.a.a;

import android.content.Context;
import com.facebook.common.e.a;
import com.facebook.f.i.f;
import com.facebook.imagepipeline.f.g;
import com.facebook.imagepipeline.f.h;
import com.facebook.imagepipeline.f.j;
import com.facebook.soloader.SoLoader;
import java.io.IOException;

/* compiled from: Fresco */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final Class<?> f1835a = c.class;

    /* renamed from: b  reason: collision with root package name */
    private static f f1836b = null;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f1837c = false;

    private c() {
    }

    public static void a(Context context, h hVar) {
        a(context, hVar, null);
    }

    public static void a(Context context, h hVar, b bVar) {
        if (f1837c) {
            a.b(f1835a, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
        } else {
            f1837c = true;
        }
        try {
            SoLoader.a(context, 0);
            Context applicationContext = context.getApplicationContext();
            if (hVar == null) {
                j.a(applicationContext);
            } else {
                j.a(hVar);
            }
            a(applicationContext, bVar);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize SoLoader", e);
        }
    }

    private static void a(Context context, b bVar) {
        f1836b = new f(context, bVar);
        f.a(f1836b);
    }

    public static e a() {
        return f1836b.b();
    }

    public static j b() {
        return j.a();
    }

    public static g c() {
        return b().h();
    }
}
