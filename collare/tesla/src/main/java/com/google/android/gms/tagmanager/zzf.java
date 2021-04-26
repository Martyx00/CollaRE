package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzf extends zzbq {
    private static final String ID = zza.ADVERTISING_TRACKING_ENABLED.toString();
    private final zza zzaxd;

    public zzf(Context context) {
        this(zza.zzh(context));
    }

    @VisibleForTesting
    private zzf(zza zza) {
        super(ID, new String[0]);
        this.zzaxd = zza;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return zzgj.zzj(Boolean.valueOf(!this.zzaxd.isLimitAdTrackingEnabled()));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }
}
