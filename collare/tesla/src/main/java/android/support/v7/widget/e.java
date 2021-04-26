package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.g.s;
import android.support.v4.widget.p;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* compiled from: AppCompatAutoCompleteTextView */
public class e extends AutoCompleteTextView implements s {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f1239a = {16843126};

    /* renamed from: b  reason: collision with root package name */
    private final f f1240b;

    /* renamed from: c  reason: collision with root package name */
    private final y f1241c;

    public e(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.autoCompleteTextViewStyle);
    }

    public e(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        av a2 = av.a(getContext(), attributeSet, f1239a, i, 0);
        if (a2.g(0)) {
            setDropDownBackgroundDrawable(a2.a(0));
        }
        a2.a();
        this.f1240b = new f(this);
        this.f1240b.a(attributeSet, i);
        this.f1241c = new y(this);
        this.f1241c.a(attributeSet, i);
        this.f1241c.a();
    }

    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(android.support.v7.b.a.a.b(getContext(), i));
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1240b;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1240b;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1240b;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1240b;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1240b;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1240b;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1240b;
        if (fVar != null) {
            fVar.c();
        }
        y yVar = this.f1241c;
        if (yVar != null) {
            yVar.a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1241c;
        if (yVar != null) {
            yVar.a(context, i);
        }
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(p.a(this, callback));
    }
}
