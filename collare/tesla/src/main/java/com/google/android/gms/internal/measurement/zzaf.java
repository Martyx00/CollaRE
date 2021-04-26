package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzaf extends zzi<zzaf> {
    public String zzur;
    public String zzus;
    public String zzut;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("network", this.zzur);
        hashMap.put("action", this.zzus);
        hashMap.put("target", this.zzut);
        return zza((Object) hashMap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzaf zzaf) {
        zzaf zzaf2 = zzaf;
        if (!TextUtils.isEmpty(this.zzur)) {
            zzaf2.zzur = this.zzur;
        }
        if (!TextUtils.isEmpty(this.zzus)) {
            zzaf2.zzus = this.zzus;
        }
        if (!TextUtils.isEmpty(this.zzut)) {
            zzaf2.zzut = this.zzut;
        }
    }
}
