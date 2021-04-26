package com.google.android.gms.internal.measurement;

final class zzeq implements Runnable {
    private final /* synthetic */ zzhk zzaha;
    private final /* synthetic */ zzep zzahb;

    zzeq(zzep zzep, zzhk zzhk) {
        this.zzahb = zzep;
        this.zzaha = zzhk;
    }

    public final void run() {
        this.zzaha.zzgl();
        if (zzee.isMainThread()) {
            this.zzaha.zzgh().zzc(this);
            return;
        }
        boolean zzef = this.zzahb.zzef();
        zzep.zza(this.zzahb, 0);
        if (zzef) {
            this.zzahb.run();
        }
    }
}
