package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zziq extends zzep {
    private final /* synthetic */ zzik zzaqv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zziq(zzik zzik, zzhk zzhk) {
        super(zzhk);
        this.zzaqv = zzik;
    }

    @Override // com.google.android.gms.internal.measurement.zzep
    public final void run() {
        this.zzaqv.zzgi().zziy().log("Tasks have been queued for a long time");
    }
}
