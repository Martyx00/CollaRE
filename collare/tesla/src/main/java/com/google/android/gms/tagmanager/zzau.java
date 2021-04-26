package com.google.android.gms.tagmanager;

import java.util.List;

final class zzau implements Runnable {
    private final /* synthetic */ List zzazk;
    private final /* synthetic */ long zzazl;
    private final /* synthetic */ zzat zzazm;

    zzau(zzat zzat, List list, long j) {
        this.zzazm = zzat;
        this.zzazk = list;
        this.zzazl = j;
    }

    public final void run() {
        this.zzazm.zzb(this.zzazk, this.zzazl);
    }
}
