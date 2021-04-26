package com.facebook.imagepipeline.n;

import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.j.d;

/* compiled from: ThumbnailSizeChecker */
public final class ay {
    public static int a(int i) {
        return (int) (((float) i) * 1.3333334f);
    }

    public static boolean a(int i, int i2, e eVar) {
        if (eVar == null) {
            if (((float) a(i)) < 2048.0f || a(i2) < 2048) {
                return false;
            }
            return true;
        } else if (a(i) < eVar.f2090a || a(i2) < eVar.f2091b) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean a(d dVar, e eVar) {
        if (dVar == null) {
            return false;
        }
        int f = dVar.f();
        if (f == 90 || f == 270) {
            return a(dVar.i(), dVar.h(), eVar);
        }
        return a(dVar.h(), dVar.i(), eVar);
    }
}
