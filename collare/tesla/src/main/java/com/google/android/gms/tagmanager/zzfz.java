package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

abstract class zzfz extends zzef {
    public zzfz(String str) {
        super(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.tagmanager.zzef
    public final boolean zza(zzm zzm, zzm zzm2, Map<String, zzm> map) {
        String zzc = zzgj.zzc(zzm);
        String zzc2 = zzgj.zzc(zzm2);
        if (zzc == zzgj.zzpn() || zzc2 == zzgj.zzpn()) {
            return false;
        }
        return zza(zzc, zzc2, map);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(String str, String str2, Map<String, zzm> map);
}
