package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;

/* access modifiers changed from: package-private */
public final class zzit implements Runnable {
    private final /* synthetic */ zzeb zzapd;
    private final /* synthetic */ zzik zzaqv;
    private final /* synthetic */ boolean zzaqx;
    private final /* synthetic */ boolean zzaqy;
    private final /* synthetic */ zzef zzaqz;
    private final /* synthetic */ zzef zzara;

    zzit(zzik zzik, boolean z, boolean z2, zzef zzef, zzeb zzeb, zzef zzef2) {
        this.zzaqv = zzik;
        this.zzaqx = z;
        this.zzaqy = z2;
        this.zzaqz = zzef;
        this.zzapd = zzeb;
        this.zzara = zzef2;
    }

    public final void run() {
        zzfa zzfa = this.zzaqv.zzaqp;
        if (zzfa == null) {
            this.zzaqv.zzgi().zziv().log("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zzaqx) {
            this.zzaqv.zza(zzfa, this.zzaqy ? null : this.zzaqz, this.zzapd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zzara.packageName)) {
                    zzfa.zza(this.zzaqz, this.zzapd);
                } else {
                    zzfa.zzb(this.zzaqz);
                }
            } catch (RemoteException e) {
                this.zzaqv.zzgi().zziv().zzg("Failed to send conditional user property to the service", e);
            }
        }
        this.zzaqv.zzcu();
    }
}
