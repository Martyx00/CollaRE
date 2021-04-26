package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzwc;

final class zzez implements Runnable {
    private final /* synthetic */ zzex zzbcu;
    private final /* synthetic */ zzwc zzbcv;

    zzez(zzex zzex, zzwc zzwc) {
        this.zzbcu = zzex;
        this.zzbcv = zzwc;
    }

    public final void run() {
        this.zzbcu.zzb(this.zzbcv);
    }
}
