package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* access modifiers changed from: package-private */
public final class zai extends Drawable.ConstantState {
    int mChangingConfigurations;
    int zanw;

    zai(zai zai) {
        if (zai != null) {
            this.mChangingConfigurations = zai.mChangingConfigurations;
            this.zanw = zai.zanw;
        }
    }

    public final Drawable newDrawable() {
        return new zae(this);
    }

    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
}
