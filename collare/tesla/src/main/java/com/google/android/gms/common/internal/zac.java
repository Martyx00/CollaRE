package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* access modifiers changed from: package-private */
public final class zac extends DialogRedirect {
    private final /* synthetic */ Activity val$activity;
    private final /* synthetic */ int val$requestCode;
    private final /* synthetic */ Intent zaoh;

    zac(Intent intent, Activity activity, int i) {
        this.zaoh = intent;
        this.val$activity = activity;
        this.val$requestCode = i;
    }

    @Override // com.google.android.gms.common.internal.DialogRedirect
    public final void redirect() {
        Intent intent = this.zaoh;
        if (intent != null) {
            this.val$activity.startActivityForResult(intent, this.val$requestCode);
        }
    }
}
