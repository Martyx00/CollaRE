package android.support.v7.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.a.a;
import android.view.ViewConfiguration;

/* compiled from: ActionBarPolicy */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private Context f908a;

    public static a a(Context context) {
        return new a(context);
    }

    private a(Context context) {
        this.f908a = context;
    }

    public int a() {
        Configuration configuration = this.f908a.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600) {
            return 5;
        }
        if (i > 960 && i2 > 720) {
            return 5;
        }
        if (i > 720 && i2 > 960) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i > 640 && i2 > 480) {
            return 4;
        }
        if (i <= 480 || i2 <= 640) {
            return i >= 360 ? 3 : 2;
        }
        return 4;
    }

    public boolean b() {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return !ViewConfiguration.get(this.f908a).hasPermanentMenuKey();
    }

    public int c() {
        return this.f908a.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public boolean d() {
        return this.f908a.getResources().getBoolean(a.b.abc_action_bar_embed_tabs);
    }

    public int e() {
        TypedArray obtainStyledAttributes = this.f908a.obtainStyledAttributes(null, a.j.ActionBar, a.C0020a.actionBarStyle, 0);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(a.j.ActionBar_height, 0);
        Resources resources = this.f908a.getResources();
        if (!d()) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(a.d.abc_action_bar_stacked_max_height));
        }
        obtainStyledAttributes.recycle();
        return layoutDimension;
    }

    public boolean f() {
        return this.f908a.getApplicationInfo().targetSdkVersion < 14;
    }

    public int g() {
        return this.f908a.getResources().getDimensionPixelSize(a.d.abc_action_bar_stacked_tab_max_width);
    }
}
