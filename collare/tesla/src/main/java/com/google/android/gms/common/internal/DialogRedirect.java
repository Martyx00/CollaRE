package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.g;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleFragment;

public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    /* access modifiers changed from: protected */
    public abstract void redirect();

    public static DialogRedirect getInstance(Activity activity, Intent intent, int i) {
        return new zac(intent, activity, i);
    }

    public static DialogRedirect getInstance(g gVar, Intent intent, int i) {
        return new zad(intent, gVar, i);
    }

    public static DialogRedirect getInstance(LifecycleFragment lifecycleFragment, Intent intent, int i) {
        return new zae(intent, lifecycleFragment, i);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        try {
            redirect();
        } catch (ActivityNotFoundException e) {
            Log.e("DialogRedirect", "Failed to start resolution intent", e);
        } finally {
            dialogInterface.dismiss();
        }
    }
}
