package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class zzja implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zziy zzare;

    zzja(zziy zziy, ComponentName componentName) {
        this.zzare = zziy;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzare.zzaqv.onServiceDisconnected(this.val$name);
    }
}
