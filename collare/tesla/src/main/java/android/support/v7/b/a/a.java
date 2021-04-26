package android.support.v7.b.a;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.c;
import android.support.v7.widget.k;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

/* compiled from: AppCompatResources */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f878a = new ThreadLocal<>();

    /* renamed from: b  reason: collision with root package name */
    private static final WeakHashMap<Context, SparseArray<C0024a>> f879b = new WeakHashMap<>(0);

    /* renamed from: c  reason: collision with root package name */
    private static final Object f880c = new Object();

    public static ColorStateList a(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(i);
        }
        ColorStateList d2 = d(context, i);
        if (d2 != null) {
            return d2;
        }
        ColorStateList c2 = c(context, i);
        if (c2 == null) {
            return c.b(context, i);
        }
        a(context, i, c2);
        return c2;
    }

    public static Drawable b(Context context, int i) {
        return k.a().a(context, i);
    }

    private static ColorStateList c(Context context, int i) {
        if (e(context, i)) {
            return null;
        }
        Resources resources = context.getResources();
        try {
            return android.support.v4.content.a.a.a(resources, resources.getXml(i), context.getTheme());
        } catch (Exception e) {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    private static ColorStateList d(Context context, int i) {
        C0024a aVar;
        synchronized (f880c) {
            SparseArray<C0024a> sparseArray = f879b.get(context);
            if (!(sparseArray == null || sparseArray.size() <= 0 || (aVar = sparseArray.get(i)) == null)) {
                if (aVar.f882b.equals(context.getResources().getConfiguration())) {
                    return aVar.f881a;
                }
                sparseArray.remove(i);
            }
            return null;
        }
    }

    private static void a(Context context, int i, ColorStateList colorStateList) {
        synchronized (f880c) {
            SparseArray<C0024a> sparseArray = f879b.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
                f879b.put(context, sparseArray);
            }
            sparseArray.append(i, new C0024a(colorStateList, context.getResources().getConfiguration()));
        }
    }

    private static boolean e(Context context, int i) {
        Resources resources = context.getResources();
        TypedValue a2 = a();
        resources.getValue(i, a2, true);
        if (a2.type < 28 || a2.type > 31) {
            return false;
        }
        return true;
    }

    private static TypedValue a() {
        TypedValue typedValue = f878a.get();
        if (typedValue != null) {
            return typedValue;
        }
        TypedValue typedValue2 = new TypedValue();
        f878a.set(typedValue2);
        return typedValue2;
    }

    /* access modifiers changed from: private */
    /* renamed from: android.support.v7.b.a.a$a  reason: collision with other inner class name */
    /* compiled from: AppCompatResources */
    public static class C0024a {

        /* renamed from: a  reason: collision with root package name */
        final ColorStateList f881a;

        /* renamed from: b  reason: collision with root package name */
        final Configuration f882b;

        C0024a(ColorStateList colorStateList, Configuration configuration) {
            this.f881a = colorStateList;
            this.f882b = configuration;
        }
    }
}
