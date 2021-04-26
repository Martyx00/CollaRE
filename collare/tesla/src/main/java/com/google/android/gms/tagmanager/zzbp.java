package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzbp extends zzbq {
    private static final String ID = zza.EVENT.toString();
    private final zzfb zzaxo;

    public zzbp(zzfb zzfb) {
        super(ID, new String[0]);
        this.zzaxo = zzfb;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String zzor = this.zzaxo.zzor();
        return zzor == null ? zzgj.zzpo() : zzgj.zzj(zzor);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }
}
