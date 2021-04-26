package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzij implements Runnable {
    private final /* synthetic */ zzih zzaqm;
    private final /* synthetic */ zzig zzaqn;

    zzij(zzih zzih, zzig zzig) {
        this.zzaqm = zzih;
        this.zzaqn = zzig;
    }

    public final void run() {
        this.zzaqm.zza(this.zzaqn);
        zzih zzih = this.zzaqm;
        zzih.zzaqd = null;
        zzih.zzga().zzb((zzig) null);
    }
}
