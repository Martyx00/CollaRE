package android.support.v4.g;

import android.os.Build;
import android.view.WindowInsets;

/* compiled from: WindowInsetsCompat */
public class ac {

    /* renamed from: a  reason: collision with root package name */
    private final Object f469a;

    private ac(Object obj) {
        this.f469a = obj;
    }

    public int a() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f469a).getSystemWindowInsetLeft();
        }
        return 0;
    }

    public int b() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f469a).getSystemWindowInsetTop();
        }
        return 0;
    }

    public int c() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f469a).getSystemWindowInsetRight();
        }
        return 0;
    }

    public int d() {
        if (Build.VERSION.SDK_INT >= 20) {
            return ((WindowInsets) this.f469a).getSystemWindowInsetBottom();
        }
        return 0;
    }

    public boolean e() {
        if (Build.VERSION.SDK_INT >= 21) {
            return ((WindowInsets) this.f469a).isConsumed();
        }
        return false;
    }

    public ac a(int i, int i2, int i3, int i4) {
        if (Build.VERSION.SDK_INT >= 20) {
            return new ac(((WindowInsets) this.f469a).replaceSystemWindowInsets(i, i2, i3, i4));
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ac acVar = (ac) obj;
        Object obj2 = this.f469a;
        if (obj2 != null) {
            return obj2.equals(acVar.f469a);
        }
        if (acVar.f469a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f469a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    static ac a(Object obj) {
        if (obj == null) {
            return null;
        }
        return new ac(obj);
    }

    static Object a(ac acVar) {
        if (acVar == null) {
            return null;
        }
        return acVar.f469a;
    }
}
