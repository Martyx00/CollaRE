package android.support.v4.g;

import android.view.View;
import android.view.ViewParent;

/* compiled from: NestedScrollingChildHelper */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private ViewParent f494a;

    /* renamed from: b  reason: collision with root package name */
    private ViewParent f495b;

    /* renamed from: c  reason: collision with root package name */
    private final View f496c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f497d;
    private int[] e;

    public m(View view) {
        this.f496c = view;
    }

    public void a(boolean z) {
        if (this.f497d) {
            t.s(this.f496c);
        }
        this.f497d = z;
    }

    public boolean a() {
        return this.f497d;
    }

    public boolean b() {
        return a(0);
    }

    public boolean a(int i) {
        return d(i) != null;
    }

    public boolean b(int i) {
        return a(i, 0);
    }

    public boolean a(int i, int i2) {
        if (a(i2)) {
            return true;
        }
        if (!a()) {
            return false;
        }
        View view = this.f496c;
        for (ViewParent parent = this.f496c.getParent(); parent != null; parent = parent.getParent()) {
            if (x.a(parent, view, this.f496c, i, i2)) {
                a(i2, parent);
                x.b(parent, view, this.f496c, i, i2);
                return true;
            }
            if (parent instanceof View) {
                view = (View) parent;
            }
        }
        return false;
    }

    public void c() {
        c(0);
    }

    public void c(int i) {
        ViewParent d2 = d(i);
        if (d2 != null) {
            x.a(d2, this.f496c, i);
            a(i, (ViewParent) null);
        }
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr) {
        return a(i, i2, i3, i4, iArr, 0);
    }

    public boolean a(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        ViewParent d2;
        int i6;
        int i7;
        if (!a() || (d2 = d(i5)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        if (iArr != null) {
            this.f496c.getLocationInWindow(iArr);
            i7 = iArr[0];
            i6 = iArr[1];
        } else {
            i7 = 0;
            i6 = 0;
        }
        x.a(d2, this.f496c, i, i2, i3, i4, i5);
        if (iArr != null) {
            this.f496c.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i7;
            iArr[1] = iArr[1] - i6;
        }
        return true;
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2) {
        return a(i, i2, iArr, iArr2, 0);
    }

    public boolean a(int i, int i2, int[] iArr, int[] iArr2, int i3) {
        ViewParent d2;
        int i4;
        int i5;
        if (!a() || (d2 = d(i3)) == null) {
            return false;
        }
        if (i == 0 && i2 == 0) {
            if (iArr2 != null) {
                iArr2[0] = 0;
                iArr2[1] = 0;
            }
            return false;
        }
        if (iArr2 != null) {
            this.f496c.getLocationInWindow(iArr2);
            i5 = iArr2[0];
            i4 = iArr2[1];
        } else {
            i5 = 0;
            i4 = 0;
        }
        if (iArr == null) {
            if (this.e == null) {
                this.e = new int[2];
            }
            iArr = this.e;
        }
        iArr[0] = 0;
        iArr[1] = 0;
        x.a(d2, this.f496c, i, i2, iArr, i3);
        if (iArr2 != null) {
            this.f496c.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i5;
            iArr2[1] = iArr2[1] - i4;
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            return false;
        }
        return true;
    }

    public boolean a(float f, float f2, boolean z) {
        ViewParent d2;
        if (!a() || (d2 = d(0)) == null) {
            return false;
        }
        return x.a(d2, this.f496c, f, f2, z);
    }

    public boolean a(float f, float f2) {
        ViewParent d2;
        if (!a() || (d2 = d(0)) == null) {
            return false;
        }
        return x.a(d2, this.f496c, f, f2);
    }

    private ViewParent d(int i) {
        switch (i) {
            case 0:
                return this.f494a;
            case 1:
                return this.f495b;
            default:
                return null;
        }
    }

    private void a(int i, ViewParent viewParent) {
        switch (i) {
            case 0:
                this.f494a = viewParent;
                return;
            case 1:
                this.f495b = viewParent;
                return;
            default:
                return;
        }
    }
}
