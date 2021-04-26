package android.support.v7.view;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.a.a;
import android.view.LayoutInflater;

/* compiled from: ContextThemeWrapper */
public class d extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    private int f911a;

    /* renamed from: b  reason: collision with root package name */
    private Resources.Theme f912b;

    /* renamed from: c  reason: collision with root package name */
    private LayoutInflater f913c;

    /* renamed from: d  reason: collision with root package name */
    private Configuration f914d;
    private Resources e;

    public d() {
        super(null);
    }

    public d(Context context, int i) {
        super(context);
        this.f911a = i;
    }

    public d(Context context, Resources.Theme theme) {
        super(context);
        this.f912b = theme;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    public Resources getResources() {
        return b();
    }

    private Resources b() {
        if (this.e == null) {
            if (this.f914d == null) {
                this.e = super.getResources();
            } else if (Build.VERSION.SDK_INT >= 17) {
                this.e = createConfigurationContext(this.f914d).getResources();
            }
        }
        return this.e;
    }

    public void setTheme(int i) {
        if (this.f911a != i) {
            this.f911a = i;
            c();
        }
    }

    public int a() {
        return this.f911a;
    }

    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f912b;
        if (theme != null) {
            return theme;
        }
        if (this.f911a == 0) {
            this.f911a = a.i.Theme_AppCompat_Light;
        }
        c();
        return this.f912b;
    }

    @Override // android.content.Context, android.content.ContextWrapper
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f913c == null) {
            this.f913c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f913c;
    }

    /* access modifiers changed from: protected */
    public void a(Resources.Theme theme, int i, boolean z) {
        theme.applyStyle(i, true);
    }

    private void c() {
        boolean z = this.f912b == null;
        if (z) {
            this.f912b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f912b.setTo(theme);
            }
        }
        a(this.f912b, this.f911a, z);
    }

    public AssetManager getAssets() {
        return getResources().getAssets();
    }
}
