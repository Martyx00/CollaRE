package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbu extends zzbq {
    private static final String ID = zza.GTM_VERSION.toString();

    public zzbu() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return zzgj.zzj("4.00");
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
