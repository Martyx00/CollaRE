package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.support.v4.g.y;
import android.support.v7.a.a;
import android.support.v7.view.b;
import android.support.v7.view.menu.h;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public class ActionBarContextView extends a {
    private CharSequence g;
    private CharSequence h;
    private View i;
    private View j;
    private LinearLayout k;
    private TextView l;
    private TextView m;
    private int n;
    private int o;
    private boolean p;
    private int q;

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ y a(int i2, long j2) {
        return super.a(i2, j2);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.support.v7.widget.a
    public /* bridge */ /* synthetic */ void setVisibility(int i2) {
        super.setVisibility(i2);
    }

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        av a2 = av.a(context, attributeSet, a.j.ActionMode, i2, 0);
        t.a(this, a2.a(a.j.ActionMode_background));
        this.n = a2.g(a.j.ActionMode_titleTextStyle, 0);
        this.o = a2.g(a.j.ActionMode_subtitleTextStyle, 0);
        this.e = a2.f(a.j.ActionMode_height, 0);
        this.q = a2.g(a.j.ActionMode_closeItemLayout, a.g.abc_action_mode_close_item_material);
        a2.a();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f1113d != null) {
            this.f1113d.e();
            this.f1113d.g();
        }
    }

    @Override // android.support.v7.widget.a
    public void setContentHeight(int i2) {
        this.e = i2;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.j;
        if (view2 != null) {
            removeView(view2);
        }
        this.j = view;
        if (!(view == null || (linearLayout = this.k) == null)) {
            removeView(linearLayout);
            this.k = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setTitle(CharSequence charSequence) {
        this.g = charSequence;
        e();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.h = charSequence;
        e();
    }

    public CharSequence getTitle() {
        return this.g;
    }

    public CharSequence getSubtitle() {
        return this.h;
    }

    private void e() {
        if (this.k == null) {
            LayoutInflater.from(getContext()).inflate(a.g.abc_action_bar_title_item, this);
            this.k = (LinearLayout) getChildAt(getChildCount() - 1);
            this.l = (TextView) this.k.findViewById(a.f.action_bar_title);
            this.m = (TextView) this.k.findViewById(a.f.action_bar_subtitle);
            if (this.n != 0) {
                this.l.setTextAppearance(getContext(), this.n);
            }
            if (this.o != 0) {
                this.m.setTextAppearance(getContext(), this.o);
            }
        }
        this.l.setText(this.g);
        this.m.setText(this.h);
        boolean z = !TextUtils.isEmpty(this.g);
        boolean z2 = !TextUtils.isEmpty(this.h);
        int i2 = 0;
        this.m.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout = this.k;
        if (!z && !z2) {
            i2 = 8;
        }
        linearLayout.setVisibility(i2);
        if (this.k.getParent() == null) {
            addView(this.k);
        }
    }

    public void a(final b bVar) {
        View view = this.i;
        if (view == null) {
            this.i = LayoutInflater.from(getContext()).inflate(this.q, (ViewGroup) this, false);
            addView(this.i);
        } else if (view.getParent() == null) {
            addView(this.i);
        }
        this.i.findViewById(a.f.action_mode_close_button).setOnClickListener(new View.OnClickListener() {
            /* class android.support.v7.widget.ActionBarContextView.AnonymousClass1 */

            public void onClick(View view) {
                bVar.c();
            }
        });
        h hVar = (h) bVar.b();
        if (this.f1113d != null) {
            this.f1113d.f();
        }
        this.f1113d = new c(getContext());
        this.f1113d.c(true);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        hVar.a(this.f1113d, this.f1111b);
        this.f1112c = (ActionMenuView) this.f1113d.a(this);
        t.a(this.f1112c, (Drawable) null);
        addView(this.f1112c, layoutParams);
    }

    public void b() {
        if (this.i == null) {
            c();
        }
    }

    public void c() {
        removeAllViews();
        this.j = null;
        this.f1112c = null;
    }

    @Override // android.support.v7.widget.a
    public boolean a() {
        if (this.f1113d != null) {
            return this.f1113d.d();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int i4;
        int i5 = 1073741824;
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i3) != 0) {
            int size = View.MeasureSpec.getSize(i2);
            if (this.e > 0) {
                i4 = this.e;
            } else {
                i4 = View.MeasureSpec.getSize(i3);
            }
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i6 = i4 - paddingTop;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i6, PKIFailureInfo.systemUnavail);
            View view = this.i;
            if (view != null) {
                int a2 = a(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i.getLayoutParams();
                paddingLeft = a2 - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            if (this.f1112c != null && this.f1112c.getParent() == this) {
                paddingLeft = a(this.f1112c, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.k;
            if (linearLayout != null && this.j == null) {
                if (this.p) {
                    this.k.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.k.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.k.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = a(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.j;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i7 = layoutParams.width != -2 ? 1073741824 : PKIFailureInfo.systemUnavail;
                if (layoutParams.width >= 0) {
                    paddingLeft = Math.min(layoutParams.width, paddingLeft);
                }
                if (layoutParams.height == -2) {
                    i5 = PKIFailureInfo.systemUnavail;
                }
                if (layoutParams.height >= 0) {
                    i6 = Math.min(layoutParams.height, i6);
                }
                this.j.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i7), View.MeasureSpec.makeMeasureSpec(i6, i5));
            }
            if (this.e <= 0) {
                int childCount = getChildCount();
                int i8 = 0;
                for (int i9 = 0; i9 < childCount; i9++) {
                    int measuredHeight = getChildAt(i9).getMeasuredHeight() + paddingTop;
                    if (measuredHeight > i8) {
                        i8 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i8);
                return;
            }
            setMeasuredDimension(size, i4);
        } else {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used " + "with android:layout_height=\"wrap_content\"");
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        boolean a2 = bb.a(this);
        int paddingRight = a2 ? (i4 - i2) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i5 - i3) - getPaddingTop()) - getPaddingBottom();
        View view = this.i;
        if (view == null || view.getVisibility() == 8) {
            i6 = paddingRight;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.i.getLayoutParams();
            int i7 = a2 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i8 = a2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int a3 = a(paddingRight, i7, a2);
            i6 = a(a3 + a(this.i, a3, paddingTop, paddingTop2, a2), i8, a2);
        }
        LinearLayout linearLayout = this.k;
        int a4 = (linearLayout == null || this.j != null || linearLayout.getVisibility() == 8) ? i6 : i6 + a(this.k, i6, paddingTop, paddingTop2, a2);
        View view2 = this.j;
        if (view2 != null) {
            a(view2, a4, paddingTop, paddingTop2, a2);
        }
        int paddingLeft = a2 ? getPaddingLeft() : (i4 - i2) - getPaddingRight();
        if (this.f1112c != null) {
            a(this.f1112c, paddingLeft, paddingTop, paddingTop2, !a2);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.g);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.p) {
            requestLayout();
        }
        this.p = z;
    }

    public boolean d() {
        return this.p;
    }
}
