package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.f.b;
import android.support.v4.g.s;
import android.support.v4.widget.b;
import android.support.v4.widget.p;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* compiled from: AppCompatTextView */
public class z extends TextView implements s, b {

    /* renamed from: b  reason: collision with root package name */
    private final f f1306b;

    /* renamed from: c  reason: collision with root package name */
    private final y f1307c;

    /* renamed from: d  reason: collision with root package name */
    private Future<android.support.v4.f.b> f1308d;

    public z(Context context) {
        this(context, null);
    }

    public z(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public z(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1306b = new f(this);
        this.f1306b.a(attributeSet, i);
        this.f1307c = new y(this);
        this.f1307c.a(attributeSet, i);
        this.f1307c.a();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1306b;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1306b;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1306b;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1306b;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1306b;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1306b;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(context, i);
        }
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1306b;
        if (fVar != null) {
            fVar.c();
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(z, i, i2, i3, i4);
        }
    }

    public void setTextSize(int i, float f) {
        if (f692a) {
            super.setTextSize(i, f);
            return;
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(i, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.f1307c != null && !f692a && this.f1307c.c()) {
            this.f1307c.b();
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (f692a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(i);
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (f692a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (f692a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            yVar.a(iArr, i);
        }
    }

    public int getAutoSizeTextType() {
        if (!f692a) {
            y yVar = this.f1307c;
            if (yVar != null) {
                return yVar.d();
            }
            return 0;
        } else if (super.getAutoSizeTextType() == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public int getAutoSizeStepGranularity() {
        if (f692a) {
            return super.getAutoSizeStepGranularity();
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            return yVar.e();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (f692a) {
            return super.getAutoSizeMinTextSize();
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            return yVar.f();
        }
        return -1;
    }

    public int getAutoSizeMaxTextSize() {
        if (f692a) {
            return super.getAutoSizeMaxTextSize();
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            return yVar.g();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (f692a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        y yVar = this.f1307c;
        if (yVar != null) {
            return yVar.h();
        }
        return new int[0];
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return m.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    public void setFirstBaselineToTopHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setFirstBaselineToTopHeight(i);
        } else {
            p.a(this, i);
        }
    }

    public void setLastBaselineToBottomHeight(int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            super.setLastBaselineToBottomHeight(i);
        } else {
            p.b(this, i);
        }
    }

    public int getFirstBaselineToTopHeight() {
        return p.a(this);
    }

    public int getLastBaselineToBottomHeight() {
        return p.b(this);
    }

    public void setLineHeight(int i) {
        p.c(this, i);
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(p.a(this, callback));
    }

    public b.a getTextMetricsParamsCompat() {
        return p.c(this);
    }

    public void setTextMetricsParamsCompat(b.a aVar) {
        p.a(this, aVar);
    }

    public void setPrecomputedText(android.support.v4.f.b bVar) {
        p.a(this, bVar);
    }

    private void a() {
        Future<android.support.v4.f.b> future = this.f1308d;
        if (future != null) {
            try {
                this.f1308d = null;
                p.a(this, future.get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
    }

    public CharSequence getText() {
        a();
        return super.getText();
    }

    public void setTextFuture(Future<android.support.v4.f.b> future) {
        this.f1308d = future;
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        a();
        super.onMeasure(i, i2);
    }
}
