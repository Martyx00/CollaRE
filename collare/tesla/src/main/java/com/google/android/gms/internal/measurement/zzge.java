package com.google.android.gms.internal.measurement;

final class zzge implements Runnable {
    private final /* synthetic */ zzgn zzana;
    private final /* synthetic */ zzfi zzanb;

    zzge(zzgd zzgd, zzgn zzgn, zzfi zzfi) {
        this.zzana = zzgn;
        this.zzanb = zzfi;
    }

    public final void run() {
        if (this.zzana.zzjz() == null) {
            this.zzanb.zziv().log("Install Referrer Reporter is null");
        } else {
            this.zzana.zzjz().zzjr();
        }
    }
}
