package com.google.android.gms.tagmanager;

final class zzaw implements Runnable {
    private final /* synthetic */ zzat zzazm;
    private final /* synthetic */ String zzazo;

    zzaw(zzat zzat, String str) {
        this.zzazm = zzat;
        this.zzazo = str;
    }

    public final void run() {
        this.zzazm.zzcx(this.zzazo);
    }
}
