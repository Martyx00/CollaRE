package io.a.a.a.a.g;

import android.content.Context;
import android.graphics.BitmapFactory;
import io.a.a.a.a.b.i;
import io.a.a.a.c;
import io.a.a.a.l;

/* compiled from: IconRequest */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public final String f6048a;

    /* renamed from: b  reason: collision with root package name */
    public final int f6049b;

    /* renamed from: c  reason: collision with root package name */
    public final int f6050c;

    /* renamed from: d  reason: collision with root package name */
    public final int f6051d;

    public n(String str, int i, int i2, int i3) {
        this.f6048a = str;
        this.f6049b = i;
        this.f6050c = i2;
        this.f6051d = i3;
    }

    public static n a(Context context, String str) {
        if (str != null) {
            try {
                int l = i.l(context);
                l g = c.g();
                g.a("Fabric", "App icon resource ID is " + l);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), l, options);
                return new n(str, l, options.outWidth, options.outHeight);
            } catch (Exception e) {
                c.g().e("Fabric", "Failed to load icon", e);
            }
        }
        return null;
    }
}
