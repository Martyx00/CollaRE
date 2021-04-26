package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import android.support.v4.content.a.c;
import android.support.v4.content.a.f;
import android.support.v4.e.b;
import android.support.v4.util.g;

/* compiled from: TypefaceCompat */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final h f549a;

    /* renamed from: b  reason: collision with root package name */
    private static final g<String, Typeface> f550b = new g<>(16);

    static {
        if (Build.VERSION.SDK_INT >= 28) {
            f549a = new g();
        } else if (Build.VERSION.SDK_INT >= 26) {
            f549a = new f();
        } else if (Build.VERSION.SDK_INT >= 24 && e.a()) {
            f549a = new e();
        } else if (Build.VERSION.SDK_INT >= 21) {
            f549a = new d();
        } else {
            f549a = new h();
        }
    }

    public static Typeface a(Resources resources, int i, int i2) {
        return f550b.get(b(resources, i, i2));
    }

    private static String b(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    public static Typeface a(Context context, c.a aVar, Resources resources, int i, int i2, f.a aVar2, Handler handler, boolean z) {
        Typeface typeface;
        if (aVar instanceof c.d) {
            c.d dVar = (c.d) aVar;
            boolean z2 = false;
            if (!z ? aVar2 == null : dVar.b() == 0) {
                z2 = true;
            }
            typeface = b.a(context, dVar.a(), aVar2, handler, z2, z ? dVar.c() : -1, i2);
        } else {
            typeface = f549a.a(context, (c.b) aVar, resources, i2);
            if (aVar2 != null) {
                if (typeface != null) {
                    aVar2.a(typeface, handler);
                } else {
                    aVar2.a(-3, handler);
                }
            }
        }
        if (typeface != null) {
            f550b.put(b(resources, i, i2), typeface);
        }
        return typeface;
    }

    public static Typeface a(Context context, Resources resources, int i, String str, int i2) {
        Typeface a2 = f549a.a(context, resources, i, str, i2);
        if (a2 != null) {
            f550b.put(b(resources, i, i2), a2);
        }
        return a2;
    }

    public static Typeface a(Context context, CancellationSignal cancellationSignal, b.C0008b[] bVarArr, int i) {
        return f549a.a(context, cancellationSignal, bVarArr, i);
    }
}
