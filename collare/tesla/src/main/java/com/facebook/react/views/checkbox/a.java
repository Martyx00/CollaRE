package com.facebook.react.views.checkbox;

import android.content.Context;
import android.support.v7.widget.h;

/* access modifiers changed from: package-private */
/* compiled from: ReactCheckBox */
public class a extends h {

    /* renamed from: a  reason: collision with root package name */
    private boolean f3315a = true;

    public a(Context context) {
        super(context);
    }

    public void setChecked(boolean z) {
        if (this.f3315a) {
            this.f3315a = false;
            super.setChecked(z);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
        }
        this.f3315a = true;
    }
}
