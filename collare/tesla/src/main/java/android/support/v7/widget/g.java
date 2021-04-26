package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.g.s;
import android.support.v4.widget.b;
import android.support.v4.widget.p;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

/* compiled from: AppCompatButton */
public class g extends Button implements s, b {

    /* renamed from: b  reason: collision with root package name */
    private final f f1246b;

    /* renamed from: c  reason: collision with root package name */
    private final y f1247c;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.C0020a.buttonStyle);
    }

    public g(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1246b = new f(this);
        this.f1246b.a(attributeSet, i);
        this.f1247c = new y(this);
        this.f1247c.a(attributeSet, i);
        this.f1247c.a();
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1246b;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1246b;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1246b;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1246b;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1246b;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1246b;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1246b;
        if (fVar != null) {
            fVar.c();
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a();
        }
    }

    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(context, i);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(z, i, i2, i3, i4);
        }
    }

    public void setTextSize(int i, float f) {
        if (f692a) {
            super.setTextSize(i, f);
            return;
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(i, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.f1247c != null && !f692a && this.f1247c.c()) {
            this.f1247c.b();
        }
    }

    public void setAutoSizeTextTypeWithDefaults(int i) {
        if (f692a) {
            super.setAutoSizeTextTypeWithDefaults(i);
            return;
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(i);
        }
    }

    public void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        if (f692a) {
            super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
            return;
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(i, i2, i3, i4);
        }
    }

    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        if (f692a) {
            super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
            return;
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(iArr, i);
        }
    }

    public int getAutoSizeTextType() {
        if (!f692a) {
            y yVar = this.f1247c;
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
        y yVar = this.f1247c;
        if (yVar != null) {
            return yVar.e();
        }
        return -1;
    }

    public int getAutoSizeMinTextSize() {
        if (f692a) {
            return super.getAutoSizeMinTextSize();
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            return yVar.f();
        }
        return -1;
    }

    public int getAutoSizeMaxTextSize() {
        if (f692a) {
            return super.getAutoSizeMaxTextSize();
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            return yVar.g();
        }
        return -1;
    }

    public int[] getAutoSizeTextAvailableSizes() {
        if (f692a) {
            return super.getAutoSizeTextAvailableSizes();
        }
        y yVar = this.f1247c;
        if (yVar != null) {
            return yVar.h();
        }
        return new int[0];
    }

    public void setSupportAllCaps(boolean z) {
        y yVar = this.f1247c;
        if (yVar != null) {
            yVar.a(z);
        }
    }

    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(p.a(this, callback));
    }
}
