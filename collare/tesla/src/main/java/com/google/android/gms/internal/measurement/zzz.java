package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@VisibleForTesting
public final class zzz extends zzi<zzz> {
    private String zztt;
    public int zztu;
    public int zztv;
    public int zztw;
    public int zztx;
    public int zzty;

    public final String getLanguage() {
        return this.zztt;
    }

    public final void setLanguage(String str) {
        this.zztt = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zztt);
        hashMap.put("screenColors", Integer.valueOf(this.zztu));
        hashMap.put("screenWidth", Integer.valueOf(this.zztv));
        hashMap.put("screenHeight", Integer.valueOf(this.zztw));
        hashMap.put("viewportWidth", Integer.valueOf(this.zztx));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzty));
        return zza((Object) hashMap);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzz zzz) {
        zzz zzz2 = zzz;
        int i = this.zztu;
        if (i != 0) {
            zzz2.zztu = i;
        }
        int i2 = this.zztv;
        if (i2 != 0) {
            zzz2.zztv = i2;
        }
        int i3 = this.zztw;
        if (i3 != 0) {
            zzz2.zztw = i3;
        }
        int i4 = this.zztx;
        if (i4 != 0) {
            zzz2.zztx = i4;
        }
        int i5 = this.zzty;
        if (i5 != 0) {
            zzz2.zzty = i5;
        }
        if (!TextUtils.isEmpty(this.zztt)) {
            zzz2.zztt = this.zztt;
        }
    }
}
