package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

public final class zzav {
    private final Context zzwe;
    private final Context zzwf;

    public zzav(Context context) {
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext, "Application context can't be null");
        this.zzwe = applicationContext;
        this.zzwf = applicationContext;
    }

    public final Context getApplicationContext() {
        return this.zzwe;
    }

    public final Context zzci() {
        return this.zzwf;
    }
}
