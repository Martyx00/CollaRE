package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ButtonBarLayout extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1057a;

    /* renamed from: b  reason: collision with root package name */
    private int f1058b = -1;

    /* renamed from: c  reason: collision with root package name */
    private int f1059c = 0;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ButtonBarLayout);
        this.f1057a = obtainStyledAttributes.getBoolean(a.j.ButtonBarLayout_allowStacking, true);
        obtainStyledAttributes.recycle();
    }

    public void setAllowStacking(boolean z) {
        if (this.f1057a != z) {
            this.f1057a = z;
            if (!this.f1057a && getOrientation() == 1) {
                setStacked(false);
            }
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int i4 = 0;
        if (this.f1057a) {
            if (size > this.f1058b && a()) {
                setStacked(false);
            }
            this.f1058b = size;
        }
        if (a() || View.MeasureSpec.getMode(i) != 1073741824) {
            i3 = i;
            z = false;
        } else {
            i3 = View.MeasureSpec.makeMeasureSpec(size, PKIFailureInfo.systemUnavail);
            z = true;
        }
        super.onMeasure(i3, i2);
        if (this.f1057a && !a()) {
            if ((getMeasuredWidthAndState() & -16777216) == 16777216) {
                setStacked(true);
                z = true;
            }
        }
        if (z) {
            super.onMeasure(i, i2);
        }
        int a2 = a(0);
        if (a2 >= 0) {
            View childAt = getChildAt(a2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt.getLayoutParams();
            int paddingTop = getPaddingTop() + childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin + 0;
            if (a()) {
                int a3 = a(a2 + 1);
                i4 = a3 >= 0 ? paddingTop + getChildAt(a3).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) : paddingTop;
            } else {
                i4 = paddingTop + getPaddingBottom();
            }
        }
        if (t.h(this) != i4) {
            setMinimumHeight(i4);
        }
    }

    private int a(int i) {
        int childCount = getChildCount();
        while (i < childCount) {
            if (getChildAt(i).getVisibility() == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int getMinimumHeight() {
        return Math.max(this.f1059c, super.getMinimumHeight());
    }

    private void setStacked(boolean z) {
        setOrientation(z ? 1 : 0);
        setGravity(z ? 5 : 80);
        View findViewById = findViewById(a.f.spacer);
        if (findViewById != null) {
            findViewById.setVisibility(z ? 8 : 4);
        }
        for (int childCount = getChildCount() - 2; childCount >= 0; childCount--) {
            bringChildToFront(getChildAt(childCount));
        }
    }

    private boolean a() {
        return getOrientation() == 1;
    }
}
