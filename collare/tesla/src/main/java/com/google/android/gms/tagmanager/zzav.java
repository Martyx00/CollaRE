package com.google.android.gms.tagmanager;

final class zzav implements Runnable {
    private final /* synthetic */ zzat zzazm;
    private final /* synthetic */ zzaq zzazn;

    zzav(zzat zzat, zzaq zzaq) {
        this.zzazm = zzat;
        this.zzazn = zzaq;
    }

    public final void run() {
        this.zzazn.zzd(this.zzazm.zzng());
    }
}
