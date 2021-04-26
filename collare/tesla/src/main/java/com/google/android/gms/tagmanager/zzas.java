package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

@VisibleForTesting
final class zzas extends zzbq {
    private static final String ID = zza.CUSTOM_VAR.toString();
    private static final String NAME = zzb.NAME.toString();
    private static final String zzazf = zzb.DEFAULT_VALUE.toString();
    private final DataLayer zzaxn;

    public zzas(DataLayer dataLayer) {
        super(ID, NAME);
        this.zzaxn = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        Object obj = this.zzaxn.get(zzgj.zzc(map.get(NAME)));
        if (obj != null) {
            return zzgj.zzj(obj);
        }
        zzm zzm = map.get(zzazf);
        return zzm != null ? zzm : zzgj.zzpo();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }
}
