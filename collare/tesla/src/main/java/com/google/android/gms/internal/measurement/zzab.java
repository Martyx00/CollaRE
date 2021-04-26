package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

public final class zzab extends zzi<zzab> {
    private String category;
    private String label;
    private long value;
    private String zztz;

    public final String getAction() {
        return this.zztz;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getValue() {
        return this.value;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.category);
        hashMap.put("action", this.zztz);
        hashMap.put("label", this.label);
        hashMap.put(FirebaseAnalytics.b.VALUE, Long.valueOf(this.value));
        return zza((Object) hashMap);
    }

    public final String zzax() {
        return this.category;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzab zzab) {
        zzab zzab2 = zzab;
        if (!TextUtils.isEmpty(this.category)) {
            zzab2.category = this.category;
        }
        if (!TextUtils.isEmpty(this.zztz)) {
            zzab2.zztz = this.zztz;
        }
        if (!TextUtils.isEmpty(this.label)) {
            zzab2.label = this.label;
        }
        long j = this.value;
        if (j != 0) {
            zzab2.value = j;
        }
    }
}
