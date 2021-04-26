package com.google.android.gms.analytics;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;

public class zzj<T extends zzj> {
    private final zzk zzrx;
    protected final zzg zzry;
    private final List<zzh> zzrz = new ArrayList();

    @VisibleForTesting
    protected zzj(zzk zzk, Clock clock) {
        Preconditions.checkNotNull(zzk);
        this.zzrx = zzk;
        zzg zzg = new zzg(this, clock);
        zzg.zzx();
        this.zzry = zzg;
    }

    /* access modifiers changed from: protected */
    public void zza(zzg zzg) {
    }

    /* access modifiers changed from: protected */
    public final void zzd(zzg zzg) {
        for (zzh zzh : this.zzrz) {
            zzh.zza(this, zzg);
        }
    }

    public zzg zzi() {
        zzg zzo = this.zzry.zzo();
        zzd(zzo);
        return zzo;
    }

    /* access modifiers changed from: protected */
    public final zzk zzy() {
        return this.zzrx;
    }
}
