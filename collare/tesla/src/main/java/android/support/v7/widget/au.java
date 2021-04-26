package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
/* compiled from: TintResources */
public class au extends al {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Context> f1192a;

    public au(Context context, Resources resources) {
        super(resources);
        this.f1192a = new WeakReference<>(context);
    }

    @Override // android.support.v7.widget.al, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f1192a.get();
        if (!(drawable == null || context == null)) {
            k.a();
            k.a(context, i, drawable);
        }
        return drawable;
    }
}
