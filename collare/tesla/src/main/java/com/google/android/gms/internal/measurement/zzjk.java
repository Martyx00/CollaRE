package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final class zzjk extends zzep {
    private final /* synthetic */ zzjj zzaro;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzjk(zzjj zzjj, zzhk zzhk) {
        super(zzhk);
        this.zzaro = zzjj;
    }

    @Override // com.google.android.gms.internal.measurement.zzep
    public final void run() {
        zzjj zzjj = this.zzaro;
        zzjj.zzab();
        zzjj.zzgi().zzjc().zzg("Session started, time", Long.valueOf(zzjj.zzbt().elapsedRealtime()));
        zzjj.zzgj().zzamj.set(false);
        zzjj.zzfy().zza("auto", "_s", new Bundle());
        zzjj.zzgj().zzamk.set(zzjj.zzbt().currentTimeMillis());
    }
}
