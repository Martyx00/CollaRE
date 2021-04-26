package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: TintContextWrapper */
public class as extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f1184a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private static ArrayList<WeakReference<as>> f1185b;

    /* renamed from: c  reason: collision with root package name */
    private final Resources f1186c;

    /* renamed from: d  reason: collision with root package name */
    private final Resources.Theme f1187d;

    public static Context a(Context context) {
        if (!b(context)) {
            return context;
        }
        synchronized (f1184a) {
            if (f1185b == null) {
                f1185b = new ArrayList<>();
            } else {
                for (int size = f1185b.size() - 1; size >= 0; size--) {
                    WeakReference<as> weakReference = f1185b.get(size);
                    if (weakReference == null || weakReference.get() == null) {
                        f1185b.remove(size);
                    }
                }
                for (int size2 = f1185b.size() - 1; size2 >= 0; size2--) {
                    WeakReference<as> weakReference2 = f1185b.get(size2);
                    as asVar = weakReference2 != null ? weakReference2.get() : null;
                    if (asVar != null && asVar.getBaseContext() == context) {
                        return asVar;
                    }
                }
            }
            as asVar2 = new as(context);
            f1185b.add(new WeakReference<>(asVar2));
            return asVar2;
        }
    }

    private static boolean b(Context context) {
        if ((context instanceof as) || (context.getResources() instanceof au) || (context.getResources() instanceof ba)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21 || ba.a()) {
            return true;
        }
        return false;
    }

    private as(Context context) {
        super(context);
        if (ba.a()) {
            this.f1186c = new ba(this, context.getResources());
            this.f1187d = this.f1186c.newTheme();
            this.f1187d.setTo(context.getTheme());
            return;
        }
        this.f1186c = new au(this, context.getResources());
        this.f1187d = null;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f1187d;
        return theme == null ? super.getTheme() : theme;
    }

    public void setTheme(int i) {
        Resources.Theme theme = this.f1187d;
        if (theme == null) {
            super.setTheme(i);
        } else {
            theme.applyStyle(i, true);
        }
    }

    public Resources getResources() {
        return this.f1186c;
    }

    public AssetManager getAssets() {
        return this.f1186c.getAssets();
    }
}
