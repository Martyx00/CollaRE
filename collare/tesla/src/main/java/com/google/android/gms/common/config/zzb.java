package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

/* access modifiers changed from: package-private */
public final class zzb extends GservicesValue<Long> {
    zzb(String str, Long l) {
        super(str, l);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.config.GservicesValue
    public final /* synthetic */ Long zzd(String str) {
        GservicesValue.zza zza = null;
        return zza.getLong(this.mKey, (Long) this.zzbq);
    }
}
