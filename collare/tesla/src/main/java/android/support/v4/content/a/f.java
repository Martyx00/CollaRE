package android.support.v4.content.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;

/* compiled from: ResourcesCompat */
public final class f {
    public static Drawable a(Resources resources, int i, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= 21) {
            return resources.getDrawable(i, theme);
        }
        return resources.getDrawable(i);
    }

    /* compiled from: ResourcesCompat */
    public static abstract class a {
        public abstract void a(int i);

        public abstract void a(Typeface typeface);

        public final void a(final Typeface typeface, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                /* class android.support.v4.content.a.f.a.AnonymousClass1 */

                public void run() {
                    a.this.a(typeface);
                }
            });
        }

        public final void a(final int i, Handler handler) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
            handler.post(new Runnable() {
                /* class android.support.v4.content.a.f.a.AnonymousClass2 */

                public void run() {
                    a.this.a(i);
                }
            });
        }
    }

    public static Typeface a(Context context, int i, TypedValue typedValue, int i2, a aVar) {
        if (context.isRestricted()) {
            return null;
        }
        return a(context, i, typedValue, i2, aVar, null, true);
    }

    private static Typeface a(Context context, int i, TypedValue typedValue, int i2, a aVar, Handler handler, boolean z) {
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        Typeface a2 = a(context, resources, typedValue, i, i2, aVar, handler, z);
        if (a2 != null || aVar != null) {
            return a2;
        }
        throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(i) + " could not be retrieved.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.graphics.Typeface a(android.content.Context r14, android.content.res.Resources r15, android.util.TypedValue r16, int r17, int r18, android.support.v4.content.a.f.a r19, android.os.Handler r20, boolean r21) {
        /*
        // Method dump skipped, instructions count: 218
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.a.f.a(android.content.Context, android.content.res.Resources, android.util.TypedValue, int, int, android.support.v4.content.a.f$a, android.os.Handler, boolean):android.graphics.Typeface");
    }
}
