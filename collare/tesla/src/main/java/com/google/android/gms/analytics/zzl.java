package com.google.android.gms.analytics;

/* access modifiers changed from: package-private */
public final class zzl implements Runnable {
    private final /* synthetic */ zzg zzsg;
    private final /* synthetic */ zzk zzsh;

    zzl(zzk zzk, zzg zzg) {
        this.zzsh = zzk;
        this.zzsg = zzg;
    }

    public final void run() {
        this.zzsg.zzv().zza(this.zzsg);
        for (zzn zzn : this.zzsh.zzsb) {
            zzn.zza(this.zzsg);
        }
        zzk zzk = this.zzsh;
        zzk.zzb((zzk) this.zzsg);
    }
}
