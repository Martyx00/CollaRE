package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* access modifiers changed from: package-private */
public final class zzir implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzik zzaqv;

    zzir(zzik zzik, zzeb zzeb) {
        this.zzaqv = zzik;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzfa zzfa = this.zzaqv.zzaqp;
        if (zzfa == null) {
            this.zzaqv.zzgi().zziv().log("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzfa.zzb(this.zzapd);
            this.zzaqv.zzcu();
        } catch (RemoteException e) {
            this.zzaqv.zzgi().zziv().zzg("Failed to send measurementEnabled to the service", e);
        }
    }
}
