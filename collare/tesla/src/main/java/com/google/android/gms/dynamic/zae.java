package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/* access modifiers changed from: package-private */
public final class zae implements View.OnClickListener {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Intent zaro;

    zae(Context context, Intent intent) {
        this.val$context = context;
        this.zaro = intent;
    }

    public final void onClick(View view) {
        try {
            this.val$context.startActivity(this.zaro);
        } catch (ActivityNotFoundException e) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}
