package com.facebook.react.views.switchview;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ap;

/* access modifiers changed from: package-private */
/* compiled from: ReactSwitch */
public class a extends ap {

    /* renamed from: c  reason: collision with root package name */
    private boolean f3425c = true;

    /* renamed from: d  reason: collision with root package name */
    private Integer f3426d = null;
    private Integer e = null;

    public a(Context context) {
        super(context);
    }

    @Override // android.support.v7.widget.ap
    public void setChecked(boolean z) {
        if (this.f3425c && isChecked() != z) {
            this.f3425c = false;
            super.setChecked(z);
            b(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Drawable drawable, Integer num) {
        if (num == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(num.intValue(), PorterDuff.Mode.MULTIPLY);
        }
    }

    public void a(Integer num) {
        a(super.getTrackDrawable(), num);
    }

    public void b(Integer num) {
        a(super.getThumbDrawable(), num);
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            b(z);
        }
        this.f3425c = true;
    }

    public void c(Integer num) {
        if (num != this.e) {
            this.e = num;
            if (isChecked()) {
                a(this.e);
            }
        }
    }

    public void d(Integer num) {
        if (num != this.f3426d) {
            this.f3426d = num;
            if (!isChecked()) {
                a(this.f3426d);
            }
        }
    }

    private void b(boolean z) {
        if (this.e != null || this.f3426d != null) {
            a(z ? this.e : this.f3426d);
        }
    }
}
