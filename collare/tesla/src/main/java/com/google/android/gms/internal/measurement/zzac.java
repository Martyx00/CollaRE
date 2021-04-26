package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;

public final class zzac extends zzi<zzac> {
    public String zzua;
    public boolean zzub;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("description", this.zzua);
        hashMap.put(AppMeasurement.Param.FATAL, Boolean.valueOf(this.zzub));
        return zza((Object) hashMap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzac zzac) {
        zzac zzac2 = zzac;
        if (!TextUtils.isEmpty(this.zzua)) {
            zzac2.zzua = this.zzua;
        }
        boolean z = this.zzub;
        if (z) {
            zzac2.zzub = z;
        }
    }
}
