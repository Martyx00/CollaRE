package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.g.s;
import android.support.v4.widget.p;
import android.support.v7.a.a;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/* compiled from: AppCompatEditText */
public class l extends EditText implements s {

    /* renamed from: a  reason: collision with root package name */
    private final f f1259a;

    /* renamed from: b  reason: collision with root package name */
    private final y f1260b;

    public l(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.editTextStyle);
    }

    public l(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1259a = new f(this);
        this.f1259a.a(attributeSet, i);
        this.f1260b = new y(this);
        this.f1260b.a(attributeSet, i);
        this.f1260b.a();
    }

    @Override // android.widget.EditText, android.widget.EditText
    public Editable getText() {
        if (Build.VERSION.SDK_INT >= 28) {
            return super.getText();
        }
        return super.getEditableText();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1259a;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1259a;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1259a;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1259a;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1259a;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1259a;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1259a;
        if (fVar != null) {
            fVar.c();
        }
        y yVar = this.f1260b;
        if (yVar != null) {
            yVar.a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1260b;
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
