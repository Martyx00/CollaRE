package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzv implements Runnable {
    private final /* synthetic */ Callable val$callable;
    private final /* synthetic */ zzu zzad;

    zzv(zzu zzu, Callable callable) {
        this.zzad = zzu;
        this.val$callable = callable;
    }

    public final void run() {
        try {
            this.zzad.setResult(this.val$callable.call());
        } catch (Exception e) {
            this.zzad.setException(e);
        }
    }
}
