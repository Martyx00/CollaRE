package com.facebook.imagepipeline.n;

import com.facebook.common.d.i;
import com.facebook.common.e.a;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.o.b;

/* compiled from: DownsampleUtil */
public class q {
    static int b(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            int i2 = i * 2;
            double d2 = 1.0d / ((double) i2);
            if (d2 + (0.3333333432674408d * d2) <= ((double) f)) {
                return i;
            }
            i = i2;
        }
    }

    public static int a(b bVar, d dVar) {
        int i;
        if (!d.c(dVar)) {
            return 1;
        }
        float b2 = b(bVar, dVar);
        if (dVar.e() == com.facebook.g.b.f1987a) {
            i = b(b2);
        } else {
            i = a(b2);
        }
        int max = Math.max(dVar.i(), dVar.h());
        e f = bVar.f();
        float f2 = f != null ? f.f2092c : 2048.0f;
        while (((float) (max / i)) > f2) {
            i = dVar.e() == com.facebook.g.b.f1987a ? i * 2 : i + 1;
        }
        return i;
    }

    static float b(b bVar, d dVar) {
        i.a(d.c(dVar));
        e f = bVar.f();
        if (f == null || f.f2091b <= 0 || f.f2090a <= 0 || dVar.h() == 0 || dVar.i() == 0) {
            return 1.0f;
        }
        int c2 = c(bVar, dVar);
        boolean z = c2 == 90 || c2 == 270;
        int i = z ? dVar.i() : dVar.h();
        int h = z ? dVar.h() : dVar.i();
        float f2 = ((float) f.f2090a) / ((float) i);
        float f3 = ((float) f.f2091b) / ((float) h);
        float max = Math.max(f2, f3);
        a.a("DownsampleUtil", "Downsample - Specified size: %dx%d, image size: %dx%d ratio: %.1f x %.1f, ratio: %.3f for %s", Integer.valueOf(f.f2090a), Integer.valueOf(f.f2091b), Integer.valueOf(i), Integer.valueOf(h), Float.valueOf(f2), Float.valueOf(f3), Float.valueOf(max), bVar.b().toString());
        return max;
    }

    static int a(float f) {
        if (f > 0.6666667f) {
            return 1;
        }
        int i = 2;
        while (true) {
            double d2 = (double) i;
            if ((1.0d / d2) + ((1.0d / (Math.pow(d2, 2.0d) - d2)) * 0.3333333432674408d) <= ((double) f)) {
                return i - 1;
            }
            i++;
        }
    }

    private static int c(b bVar, d dVar) {
        boolean z = false;
        if (!bVar.g().d()) {
            return 0;
        }
        int f = dVar.f();
        if (f == 0 || f == 90 || f == 180 || f == 270) {
            z = true;
        }
        i.a(z);
        return f;
    }
}
