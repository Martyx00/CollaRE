package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class zzbb implements Runnable {
    private final /* synthetic */ ComponentName val$name;
    private final /* synthetic */ zzaz zzws;

    zzbb(zzaz zzaz, ComponentName componentName) {
        this.zzws = zzaz;
        this.val$name = componentName;
    }

    public final void run() {
        this.zzws.zzwo.onServiceDisconnected(this.val$name);
    }
}
