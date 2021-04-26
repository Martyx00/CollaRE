package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.api.Api;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ActionBarContainer extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    Drawable f1025a;

    /* renamed from: b  reason: collision with root package name */
    Drawable f1026b;

    /* renamed from: c  reason: collision with root package name */
    Drawable f1027c;

    /* renamed from: d  reason: collision with root package name */
    boolean f1028d;
    boolean e;
    private boolean f;
    private View g;
    private View h;
    private View i;
    private int j;

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        t.a(this, new b(this));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ActionBar);
        this.f1025a = obtainStyledAttributes.getDrawable(a.j.ActionBar_background);
        this.f1026b = obtainStyledAttributes.getDrawable(a.j.ActionBar_backgroundStacked);
        this.j = obtainStyledAttributes.getDimensionPixelSize(a.j.ActionBar_height, -1);
        if (getId() == a.f.split_action_bar) {
            this.f1028d = true;
            this.f1027c = obtainStyledAttributes.getDrawable(a.j.ActionBar_backgroundSplit);
        }
        obtainStyledAttributes.recycle();
        boolean z = false;
        if (!this.f1028d ? this.f1025a == null && this.f1026b == null : this.f1027c == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.h = findViewById(a.f.action_bar);
        this.i = findViewById(a.f.action_context_bar);
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.f1025a;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f1025a);
        }
        this.f1025a = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.h;
            if (view != null) {
                this.f1025a.setBounds(view.getLeft(), this.h.getTop(), this.h.getRight(), this.h.getBottom());
            }
        }
        boolean z = true;
        if (!this.f1028d ? !(this.f1025a == null && this.f1026b == null) : this.f1027c != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1026b;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1026b);
        }
        this.f1026b = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.e && (drawable2 = this.f1026b) != null) {
                drawable2.setBounds(this.g.getLeft(), this.g.getTop(), this.g.getRight(), this.g.getBottom());
            }
        }
        boolean z = true;
        if (!this.f1028d ? !(this.f1025a == null && this.f1026b == null) : this.f1027c != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f1027c;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f1027c);
        }
        this.f1027c = drawable;
        boolean z = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f1028d && (drawable2 = this.f1027c) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            }
        }
        if (!this.f1028d ? this.f1025a == null && this.f1026b == null : this.f1027c == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        Drawable drawable = this.f1025a;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
        Drawable drawable2 = this.f1026b;
        if (drawable2 != null) {
            drawable2.setVisible(z, false);
        }
        Drawable drawable3 = this.f1027c;
        if (drawable3 != null) {
            drawable3.setVisible(z, false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f1025a && !this.f1028d) || (drawable == this.f1026b && this.e) || ((drawable == this.f1027c && this.f1028d) || super.verifyDrawable(drawable));
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f1025a;
        if (drawable != null && drawable.isStateful()) {
            this.f1025a.setState(getDrawableState());
        }
        Drawable drawable2 = this.f1026b;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f1026b.setState(getDrawableState());
        }
        Drawable drawable3 = this.f1027c;
        if (drawable3 != null && drawable3.isStateful()) {
            this.f1027c.setState(getDrawableState());
        }
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f1025a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f1026b;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f1027c;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    public void setTransitioning(boolean z) {
        this.f = z;
        setDescendantFocusability(z ? 393216 : PKIFailureInfo.transactionIdInUse);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    public void setTabContainer(an anVar) {
        View view = this.g;
        if (view != null) {
            removeView(view);
        }
        this.g = anVar;
        if (anVar != null) {
            addView(anVar);
            ViewGroup.LayoutParams layoutParams = anVar.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            anVar.setAllowCollapse(false);
        }
    }

    public View getTabContainer() {
        return this.g;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i2) {
        if (i2 != 0) {
            return super.startActionModeForChild(view, callback, i2);
        }
        return null;
    }

    private boolean a(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    private int b(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        if (this.h == null && View.MeasureSpec.getMode(i3) == Integer.MIN_VALUE && (i5 = this.j) >= 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(i5, View.MeasureSpec.getSize(i3)), PKIFailureInfo.systemUnavail);
        }
        super.onMeasure(i2, i3);
        if (this.h != null) {
            int mode = View.MeasureSpec.getMode(i3);
            View view = this.g;
            if (view != null && view.getVisibility() != 8 && mode != 1073741824) {
                if (!a(this.h)) {
                    i4 = b(this.h);
                } else {
                    i4 = !a(this.i) ? b(this.i) : 0;
                }
                setMeasuredDimension(getMeasuredWidth(), Math.min(i4 + b(this.g), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i3) : Api.BaseClientBuilder.API_PRIORITY_OTHER));
            }
        }
    }

    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        Drawable drawable;
        super.onLayout(z, i2, i3, i4, i5);
        View view = this.g;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = (view == null || view.getVisibility() == 8) ? false : true;
        if (!(view == null || view.getVisibility() == 8)) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i2, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i4, measuredHeight - layoutParams.bottomMargin);
        }
        if (this.f1028d) {
            Drawable drawable2 = this.f1027c;
            if (drawable2 != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z2 = false;
            }
        } else {
            if (this.f1025a != null) {
                if (this.h.getVisibility() == 0) {
                    this.f1025a.setBounds(this.h.getLeft(), this.h.getTop(), this.h.getRight(), this.h.getBottom());
                } else {
                    View view2 = this.i;
                    if (view2 == null || view2.getVisibility() != 0) {
                        this.f1025a.setBounds(0, 0, 0, 0);
                    } else {
                        this.f1025a.setBounds(this.i.getLeft(), this.i.getTop(), this.i.getRight(), this.i.getBottom());
                    }
                }
                z3 = true;
            }
            this.e = z4;
            if (!z4 || (drawable = this.f1026b) == null) {
                z2 = z3;
            } else {
                drawable.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
            }
        }
        if (z2) {
            invalidate();
        }
    }
}
