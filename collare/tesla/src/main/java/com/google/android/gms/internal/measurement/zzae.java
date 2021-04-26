package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@VisibleForTesting
public final class zzae extends zzi<zzae> {
    private String zzuk;
    private int zzul;
    private int zzum;
    private String zzun;
    private String zzuo;
    private boolean zzup;
    private boolean zzuq;

    public zzae() {
        this(false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private zzae(boolean r5) {
        /*
            r4 = this;
            java.util.UUID r5 = java.util.UUID.randomUUID()
            long r0 = r5.getLeastSignificantBits()
            r2 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r0 = r0 & r2
            int r1 = (int) r0
            if (r1 == 0) goto L_0x0010
            goto L_0x0023
        L_0x0010:
            long r0 = r5.getMostSignificantBits()
            long r0 = r0 & r2
            int r1 = (int) r0
            if (r1 == 0) goto L_0x0019
            goto L_0x0023
        L_0x0019:
            java.lang.String r5 = "GAv4"
            java.lang.String r0 = "UUID.randomUUID() returned 0."
            android.util.Log.e(r5, r0)
            r1 = 2147483647(0x7fffffff, float:NaN)
        L_0x0023:
            r5 = 0
            r4.<init>(r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzae.<init>(boolean):void");
    }

    @VisibleForTesting
    private zzae(boolean z, int i) {
        Preconditions.checkNotZero(i);
        this.zzul = i;
        this.zzuq = false;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzuk);
        hashMap.put("interstitial", Boolean.valueOf(this.zzup));
        hashMap.put("automatic", Boolean.valueOf(this.zzuq));
        hashMap.put("screenId", Integer.valueOf(this.zzul));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzum));
        hashMap.put("referrerScreenName", this.zzun);
        hashMap.put("referrerUri", this.zzuo);
        return zza((Object) hashMap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzae zzae) {
        zzae zzae2 = zzae;
        if (!TextUtils.isEmpty(this.zzuk)) {
            zzae2.zzuk = this.zzuk;
        }
        int i = this.zzul;
        if (i != 0) {
            zzae2.zzul = i;
        }
        int i2 = this.zzum;
        if (i2 != 0) {
            zzae2.zzum = i2;
        }
        if (!TextUtils.isEmpty(this.zzun)) {
            zzae2.zzun = this.zzun;
        }
        if (!TextUtils.isEmpty(this.zzuo)) {
            String str = this.zzuo;
            if (TextUtils.isEmpty(str)) {
                str = null;
            }
            zzae2.zzuo = str;
        }
        boolean z = this.zzup;
        if (z) {
            zzae2.zzup = z;
        }
        boolean z2 = this.zzuq;
        if (z2) {
            zzae2.zzuq = z2;
        }
    }

    public final String zzbg() {
        return this.zzuk;
    }

    public final int zzbh() {
        return this.zzul;
    }

    public final String zzbi() {
        return this.zzuo;
    }
}
