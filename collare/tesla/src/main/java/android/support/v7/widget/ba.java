package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import java.lang.ref.WeakReference;

/* compiled from: VectorEnabledTintResources */
public class ba extends Resources {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f1216a = false;

    /* renamed from: b  reason: collision with root package name */
    private final WeakReference<Context> f1217b;

    public static boolean a() {
        return b() && Build.VERSION.SDK_INT <= 20;
    }

    public ba(Context context, Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.f1217b = new WeakReference<>(context);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int i) {
        Context context = this.f1217b.get();
        if (context != null) {
            return k.a().a(context, this, i);
        }
        return super.getDrawable(i);
    }

    /* access modifiers changed from: package-private */
    public final Drawable a(int i) {
        return super.getDrawable(i);
    }

    public static boolean b() {
        return f1216a;
    }
}
