package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

abstract class zzgh extends zzbq {
    public zzgh(String str, String... strArr) {
        super(str, strArr);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public zzm zze(Map<String, zzm> map) {
        zzg(map);
        return zzgj.zzpo();
    }

    public abstract void zzg(Map<String, zzm> map);

    @Override // com.google.android.gms.tagmanager.zzbq
    public boolean zzmj() {
        return false;
    }
}
