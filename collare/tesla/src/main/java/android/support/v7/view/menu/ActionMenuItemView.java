package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.a.a;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.p;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.ag;
import android.support.v7.widget.ax;
import android.support.v7.widget.z;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ActionMenuItemView extends z implements p.a, ActionMenuView.a, View.OnClickListener {

    /* renamed from: b  reason: collision with root package name */
    j f944b;

    /* renamed from: c  reason: collision with root package name */
    h.b f945c;

    /* renamed from: d  reason: collision with root package name */
    b f946d;
    private CharSequence e;
    private Drawable f;
    private ag g;
    private boolean h;
    private boolean i;
    private int j;
    private int k;
    private int l;

    public static abstract class b {
        public abstract s a();
    }

    @Override // android.support.v7.view.menu.p.a
    public boolean a() {
        return true;
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Resources resources = context.getResources();
        this.h = e();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.j.ActionMenuItemView, i2, 0);
        this.j = obtainStyledAttributes.getDimensionPixelSize(a.j.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.l = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.k = -1;
        setSaveEnabled(false);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.h = e();
        f();
    }

    private boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        return i2 >= 480 || (i2 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
        this.k = i2;
        super.setPadding(i2, i3, i4, i5);
    }

    @Override // android.support.v7.view.menu.p.a
    public j getItemData() {
        return this.f944b;
    }

    @Override // android.support.v7.view.menu.p.a
    public void a(j jVar, int i2) {
        this.f944b = jVar;
        setIcon(jVar.getIcon());
        setTitle(jVar.a((p.a) this));
        setId(jVar.getItemId());
        setVisibility(jVar.isVisible() ? 0 : 8);
        setEnabled(jVar.isEnabled());
        if (jVar.hasSubMenu() && this.g == null) {
            this.g = new a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ag agVar;
        if (!this.f944b.hasSubMenu() || (agVar = this.g) == null || !agVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void onClick(View view) {
        h.b bVar = this.f945c;
        if (bVar != null) {
            bVar.a(this.f944b);
        }
    }

    public void setItemInvoker(h.b bVar) {
        this.f945c = bVar;
    }

    public void setPopupCallback(b bVar) {
        this.f946d = bVar;
    }

    public void setExpandedFormat(boolean z) {
        if (this.i != z) {
            this.i = z;
            j jVar = this.f944b;
            if (jVar != null) {
                jVar.h();
            }
        }
    }

    private void f() {
        CharSequence charSequence;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.e);
        if (this.f != null && (!this.f944b.m() || (!this.h && !this.i))) {
            z = false;
        }
        boolean z3 = z2 & z;
        CharSequence charSequence2 = null;
        setText(z3 ? this.e : null);
        CharSequence contentDescription = this.f944b.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            if (z3) {
                charSequence = null;
            } else {
                charSequence = this.f944b.getTitle();
            }
            setContentDescription(charSequence);
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.f944b.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (!z3) {
                charSequence2 = this.f944b.getTitle();
            }
            ax.a(this, charSequence2);
            return;
        }
        ax.a(this, tooltipText);
    }

    public void setIcon(Drawable drawable) {
        this.f = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = this.l;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i2) / ((float) intrinsicWidth)));
                intrinsicWidth = i2;
            }
            int i3 = this.l;
            if (intrinsicHeight > i3) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i3) / ((float) intrinsicHeight)));
                intrinsicHeight = i3;
            }
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        }
        setCompoundDrawables(drawable, null, null, null);
        f();
    }

    public boolean b() {
        return !TextUtils.isEmpty(getText());
    }

    public void setTitle(CharSequence charSequence) {
        this.e = charSequence;
        f();
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean c() {
        return b() && this.f944b.getIcon() == null;
    }

    @Override // android.support.v7.widget.ActionMenuView.a
    public boolean d() {
        return b();
    }

    /* access modifiers changed from: protected */
    @Override // android.support.v7.widget.z
    public void onMeasure(int i2, int i3) {
        int i4;
        boolean b2 = b();
        if (b2 && (i4 = this.k) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        int min = mode == Integer.MIN_VALUE ? Math.min(size, this.j) : this.j;
        if (mode != 1073741824 && this.j > 0 && measuredWidth < min) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(min, 1073741824), i3);
        }
        if (!b2 && this.f != null) {
            super.setPadding((getMeasuredWidth() - this.f.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    private class a extends ag {
        public a() {
            super(ActionMenuItemView.this);
        }

        @Override // android.support.v7.widget.ag
        public s a() {
            if (ActionMenuItemView.this.f946d != null) {
                return ActionMenuItemView.this.f946d.a();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // android.support.v7.widget.ag
        public boolean b() {
            s a2;
            if (ActionMenuItemView.this.f945c == null || !ActionMenuItemView.this.f945c.a(ActionMenuItemView.this.f944b) || (a2 = a()) == null || !a2.d()) {
                return false;
            }
            return true;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }
}
