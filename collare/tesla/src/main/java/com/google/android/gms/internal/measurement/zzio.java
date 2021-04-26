package com.google.android.gms.internal.measurement;

import android.os.RemoteException;

/* access modifiers changed from: package-private */
public final class zzio implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzik zzaqv;

    zzio(zzik zzik, zzeb zzeb) {
        this.zzaqv = zzik;
        this.zzapd = zzeb;
    }

    public final void run() {
        zzfa zzfa = this.zzaqv.zzaqp;
        if (zzfa == null) {
            this.zzaqv.zzgi().zziv().log("Discarding data. Failed to send app launch");
            return;
        }
        try {
            zzfa.zza(this.zzapd);
            this.zzaqv.zza(zzfa, null, this.zzapd);
            this.zzaqv.zzcu();
        } catch (RemoteException e) {
            this.zzaqv.zzgi().zziv().zzg("Failed to send app launch to the service", e);
        }
    }
}
