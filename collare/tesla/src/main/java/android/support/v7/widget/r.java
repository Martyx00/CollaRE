package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.widget.m;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatPopupWindow */
public class r extends PopupWindow {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f1272a = (Build.VERSION.SDK_INT < 21);

    /* renamed from: b  reason: collision with root package name */
    private boolean f1273b;

    public r(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        av a2 = av.a(context, attributeSet, a.j.PopupWindow, i, i2);
        if (a2.g(a.j.PopupWindow_overlapAnchor)) {
            a(a2.a(a.j.PopupWindow_overlapAnchor, false));
        }
        setBackgroundDrawable(a2.a(a.j.PopupWindow_android_popupBackground));
        a2.a();
    }

    public void showAsDropDown(View view, int i, int i2) {
        if (f1272a && this.f1273b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f1272a && this.f1273b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        super.update(view, i, (!f1272a || !this.f1273b) ? i2 : i2 - view.getHeight(), i3, i4);
    }

    private void a(boolean z) {
        if (f1272a) {
            this.f1273b = z;
        } else {
            m.a(this, z);
        }
    }
}
