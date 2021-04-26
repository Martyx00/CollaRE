package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

abstract class zzdy extends zzef {
    public zzdy(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.tagmanager.zzef
    public final boolean zza(zzm zzm, zzm zzm2, Map<String, zzm> map) {
        zzgi zzd = zzgj.zzd(zzm);
        zzgi zzd2 = zzgj.zzd(zzm2);
        if (zzd == zzgj.zzpm() || zzd2 == zzgj.zzpm()) {
            return false;
        }
        return zza(zzd, zzd2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzgi zzgi, zzgi zzgi2, Map<String, zzm> map);
}
