package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import java.util.HashMap;

public final class zzag extends zzi<zzag> {
    public String mCategory;
    public String zzuu;
    public long zzuv;
    public String zzuw;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("variableName", this.zzuu);
        hashMap.put("timeInMillis", Long.valueOf(this.zzuv));
        hashMap.put("category", this.mCategory);
        hashMap.put("label", this.zzuw);
        return zza((Object) hashMap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzag zzag) {
        zzag zzag2 = zzag;
        if (!TextUtils.isEmpty(this.zzuu)) {
            zzag2.zzuu = this.zzuu;
        }
        long j = this.zzuv;
        if (j != 0) {
            zzag2.zzuv = j;
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzag2.mCategory = this.mCategory;
        }
        if (!TextUtils.isEmpty(this.zzuw)) {
            zzag2.zzuw = this.zzuw;
        }
    }
}
