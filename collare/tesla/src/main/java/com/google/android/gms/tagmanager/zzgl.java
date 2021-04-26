package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzgl extends zzbq {
    private static final String ID = zza.UPPERCASE_STRING.toString();
    private static final String zzbaa = zzb.ARG0.toString();

    public zzgl() {
        super(ID, zzbaa);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return zzgj.zzj(zzgj.zzc(map.get(zzbaa)).toUpperCase());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
