package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zze extends zzbq {
    private static final String ID = zza.ADVERTISER_ID.toString();
    private final zza zzaxd;

    public zze(Context context) {
        this(zza.zzh(context));
    }

    @VisibleForTesting
    private zze(zza zza) {
        super(ID, new String[0]);
        this.zzaxd = zza;
        this.zzaxd.zzmd();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String zzmd = this.zzaxd.zzmd();
        return zzmd == null ? zzgj.zzpo() : zzgj.zzj(zzmd);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }
}
