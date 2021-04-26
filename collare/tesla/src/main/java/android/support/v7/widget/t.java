package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.q;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* compiled from: AppCompatRadioButton */
public class t extends RadioButton implements q {

    /* renamed from: a  reason: collision with root package name */
    private final j f1277a;

    /* renamed from: b  reason: collision with root package name */
    private final y f1278b;

    public t(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.radioButtonStyle);
    }

    public t(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1277a = new j(this);
        this.f1277a.a(attributeSet, i);
        this.f1278b = new y(this);
        this.f1278b.a(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        j jVar = this.f1277a;
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
        j jVar = this.f1277a;
        return jVar != null ? jVar.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // android.support.v4.widget.q
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        j jVar = this.f1277a;
        if (jVar != null) {
            jVar.a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        j jVar = this.f1277a;
        if (jVar != null) {
            return jVar.a();
        }
        return null;
    }

    @Override // android.support.v4.widget.q
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        j jVar = this.f1277a;
        if (jVar != null) {
            jVar.a(mode);
        }
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        j jVar = this.f1277a;
        if (jVar != null) {
            return jVar.b();
        }
        return null;
    }
}
