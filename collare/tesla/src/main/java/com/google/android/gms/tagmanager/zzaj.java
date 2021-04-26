package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

@VisibleForTesting
final class zzaj extends zzbq {
    private static final String ID = zza.CONTAINER_VERSION.toString();
    private final String version;

    public zzaj(String str) {
        super(ID, new String[0]);
        this.version = str;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String str = this.version;
        return str == null ? zzgj.zzpo() : zzgj.zzj(str);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
