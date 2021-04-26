package android.support.v7.widget;

import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Build;
import android.support.v4.graphics.drawable.c;

/* compiled from: DrawableUtils */
public class ad {

    /* renamed from: a  reason: collision with root package name */
    public static final Rect f1121a = new Rect();

    /* renamed from: b  reason: collision with root package name */
    private static Class<?> f1122b;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                f1122b = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0078 A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0079 A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0080 A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0087 A[Catch:{ Exception -> 0x0098 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008e A[Catch:{ Exception -> 0x0098 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Rect a(android.graphics.drawable.Drawable r10) {
        /*
        // Method dump skipped, instructions count: 174
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ad.a(android.graphics.drawable.Drawable):android.graphics.Rect");
    }

    static void b(Drawable drawable) {
        if (Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            d(drawable);
        }
    }

    public static boolean c(Drawable drawable) {
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 && (drawable instanceof LayerDrawable)) {
            return false;
        }
        if (drawable instanceof DrawableContainer) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (!(constantState instanceof DrawableContainer.DrawableContainerState)) {
                return true;
            }
            for (Drawable drawable2 : ((DrawableContainer.DrawableContainerState) constantState).getChildren()) {
                if (!c(drawable2)) {
                    return false;
                }
            }
            return true;
        } else if (drawable instanceof c) {
            return c(((c) drawable).a());
        } else {
            if (drawable instanceof android.support.v7.c.a.c) {
                return c(((android.support.v7.c.a.c) drawable).a());
            }
            if (drawable instanceof ScaleDrawable) {
                return c(((ScaleDrawable) drawable).getDrawable());
            }
            return true;
        }
    }

    private static void d(Drawable drawable) {
        int[] state = drawable.getState();
        if (state == null || state.length == 0) {
            drawable.setState(aq.e);
        } else {
            drawable.setState(aq.h);
        }
        drawable.setState(state);
    }

    public static PorterDuff.Mode a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
