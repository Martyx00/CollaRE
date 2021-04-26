package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzju implements Runnable {
    private final /* synthetic */ zzjy zzasm;
    private final /* synthetic */ zzjt zzasn;

    zzju(zzjt zzjt, zzjy zzjy) {
        this.zzasn = zzjt;
        this.zzasm = zzjy;
    }

    public final void run() {
        this.zzasn.zza((zzjt) this.zzasm);
        this.zzasn.start();
    }
}
