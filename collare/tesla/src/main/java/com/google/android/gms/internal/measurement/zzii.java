package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final class zzii implements Runnable {
    private final /* synthetic */ boolean zzaqj;
    private final /* synthetic */ zzig zzaqk;
    private final /* synthetic */ zzig zzaql;
    private final /* synthetic */ zzih zzaqm;

    zzii(zzih zzih, boolean z, zzig zzig, zzig zzig2) {
        this.zzaqm = zzih;
        this.zzaqj = z;
        this.zzaqk = zzig;
        this.zzaql = zzig2;
    }

    public final void run() {
        if (this.zzaqj && this.zzaqm.zzaqd != null) {
            zzih zzih = this.zzaqm;
            zzih.zza(zzih.zzaqd);
        }
        zzig zzig = this.zzaqk;
        if (zzig == null || zzig.zzaqb != this.zzaql.zzaqb || !zzkd.zzs(this.zzaqk.zzaqa, this.zzaql.zzaqa) || !zzkd.zzs(this.zzaqk.zzuk, this.zzaql.zzuk)) {
            Bundle bundle = new Bundle();
            zzih.zza(this.zzaql, bundle, true);
            zzig zzig2 = this.zzaqk;
            if (zzig2 != null) {
                if (zzig2.zzuk != null) {
                    bundle.putString("_pn", this.zzaqk.zzuk);
                }
                bundle.putString("_pc", this.zzaqk.zzaqa);
                bundle.putLong("_pi", this.zzaqk.zzaqb);
            }
            this.zzaqm.zzfy().zza("auto", "_vs", bundle);
        }
        zzih zzih2 = this.zzaqm;
        zzih2.zzaqd = this.zzaql;
        zzih2.zzga().zzb(this.zzaql);
    }
}
