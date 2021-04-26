package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

/* access modifiers changed from: package-private */
public final class zabc extends zabr {
    private WeakReference<zaaw> zahm;

    zabc(zaaw zaaw) {
        this.zahm = new WeakReference<>(zaaw);
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zas() {
        zaaw zaaw = this.zahm.get();
        if (zaaw != null) {
            zaaw.resume();
        }
    }
}
