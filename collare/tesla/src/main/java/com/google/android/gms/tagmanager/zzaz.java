package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzaz extends zzgh {
    private static final String ID = zza.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzb.VALUE.toString();
    private static final String zzazq = zzb.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzaxn;

    public zzaz(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzaxn = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzgh
    public final void zzg(Map<String, zzm> map) {
        String zzc;
        zzm zzm = map.get(VALUE);
        if (!(zzm == null || zzm == zzgj.zzpi())) {
            Object zzh = zzgj.zzh(zzm);
            if (zzh instanceof List) {
                for (Object obj : (List) zzh) {
                    if (obj instanceof Map) {
                        this.zzaxn.push((Map) obj);
                    }
                }
            }
        }
        zzm zzm2 = map.get(zzazq);
        if (!(zzm2 == null || zzm2 == zzgj.zzpi() || (zzc = zzgj.zzc(zzm2)) == zzgj.zzpn())) {
            this.zzaxn.zzcu(zzc);
        }
    }
}
