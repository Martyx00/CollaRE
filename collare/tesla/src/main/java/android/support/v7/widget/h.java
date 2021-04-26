package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.q;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* compiled from: AppCompatCheckBox */
public class h extends CheckBox implements q {

    /* renamed from: a  reason: collision with root package name */
    private final j f1248a;

    public h(Context context) {
        this(context, null);
    }

    public h(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.checkboxStyle);
    }

    public h(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1248a = new j(this);
        this.f1248a.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        j jVar = this.f1248a;
        if (jVar != null) {
            jVar.c();
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        j jVar = this.f1248a;
        return jVar != null ? jVar.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // android.support.v4.widget.q
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        j jVar = this.f1248a;
        if (jVar != null) {
            jVar.a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        j jVar = this.f1248a;
        if (jVar != null) {
            return jVar.a();
        }
        return null;
    }

    @Override // android.support.v4.widget.q
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        j jVar = this.f1248a;
        if (jVar != null) {
            jVar.a(mode);
        }
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        j jVar = this.f1248a;
        if (jVar != null) {
            return jVar.b();
        }
        return null;
    }
}
