package com.google.android.gms.internal.measurement;

final class zzjq extends zzep {
    private final /* synthetic */ zzjt zzark;
    private final /* synthetic */ zzjp zzarq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjq(zzjp zzjp, zzhk zzhk, zzjt zzjt) {
        super(zzhk);
        this.zzarq = zzjp;
        this.zzark = zzjt;
    }

    @Override // com.google.android.gms.internal.measurement.zzep
    public final void run() {
        this.zzarq.cancel();
        this.zzarq.zzgi().zzjc().log("Starting upload from DelayedRunnable");
        this.zzark.zzle();
    }
}
