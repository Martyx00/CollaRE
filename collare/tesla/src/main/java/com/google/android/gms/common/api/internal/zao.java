package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zao extends zabr {
    private final /* synthetic */ Dialog zadl;
    private final /* synthetic */ zan zadm;

    zao(zan zan, Dialog dialog) {
        this.zadm = zan;
        this.zadl = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabr
    public final void zas() {
        this.zadm.zadk.zaq();
        if (this.zadl.isShowing()) {
            this.zadl.dismiss();
        }
    }
}
