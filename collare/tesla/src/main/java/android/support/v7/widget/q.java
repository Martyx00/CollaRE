package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.g.s;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;

/* compiled from: AppCompatMultiAutoCompleteTextView */
public class q extends MultiAutoCompleteTextView implements s {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1269a = {16843126};

    /* renamed from: b  reason: collision with root package name */
    private final f f1270b;

    /* renamed from: c  reason: collision with root package name */
    private final y f1271c;

    public q(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.autoCompleteTextViewStyle);
    }

    public q(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        av a2 = av.a(getContext(), attributeSet, f1269a, i, 0);
        if (a2.g(0)) {
            setDropDownBackgroundDrawable(a2.a(0));
        }
        a2.a();
        this.f1270b = new f(this);
        this.f1270b.a(attributeSet, i);
        this.f1271c = new y(this);
        this.f1271c.a(attributeSet, i);
        this.f1271c.a();
    }

    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1270b;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1270b;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1270b;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1270b;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1270b;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1270b;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1270b;
        if (fVar != null) {
            fVar.c();
        }
        y yVar = this.f1271c;
        if (yVar != null) {
            yVar.a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1271c;
        if (yVar != null) {
            yVar.a(context, i);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }
}
