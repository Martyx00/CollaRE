package com.google.android.gms.common.internal;

import android.content.Intent;
import android.support.v4.app.g;

/* access modifiers changed from: package-private */
public final class zad extends DialogRedirect {
    private final /* synthetic */ g val$fragment;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;

    zad(Intent intent, g gVar, int i) {
        this.zaoh = intent;
        this.val$fragment = gVar;
        this.val$requestCode = i;
    }

    @Override // com.google.android.gms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.zaoh;
        if (intent != null) {
            this.val$fragment.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
