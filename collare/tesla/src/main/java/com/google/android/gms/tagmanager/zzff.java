package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzwg;
import com.google.android.gms.internal.measurement.zzwk;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class zzff implements zzfg {
    zzff(zzfb zzfb) {
    }

    @Override // com.google.android.gms.tagmanager.zzfg
    public final void zza(zzwk zzwk, Set<zzwg> set, Set<zzwg> set2, zzeq zzeq) {
        set.addAll(zzwk.zzrk());
        set2.addAll(zzwk.zzrl());
        zzeq.zzoc();
        zzeq.zzod();
    }
}
