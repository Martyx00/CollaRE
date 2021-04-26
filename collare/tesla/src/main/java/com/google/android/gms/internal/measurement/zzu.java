package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;

@VisibleForTesting
public final class zzu extends zzi<zzu> {
    private String zztf;
    private String zztg;
    private String zzth;
    private String zzti;

    public final void setAppId(String str) {
        this.zzth = str;
    }

    public final void setAppInstallerId(String str) {
        this.zzti = str;
    }

    public final void setAppName(String str) {
        this.zztf = str;
    }

    public final void setAppVersion(String str) {
        this.zztg = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("appName", this.zztf);
        hashMap.put("appVersion", this.zztg);
        hashMap.put("appId", this.zzth);
        hashMap.put("appInstallerId", this.zzti);
        return zza((Object) hashMap);
    }

    /* renamed from: zza */
    public final void zzb(zzu zzu) {
        if (!TextUtils.isEmpty(this.zztf)) {
            zzu.zztf = this.zztf;
        }
        if (!TextUtils.isEmpty(this.zztg)) {
            zzu.zztg = this.zztg;
        }
        if (!TextUtils.isEmpty(this.zzth)) {
            zzu.zzth = this.zzth;
        }
        if (!TextUtils.isEmpty(this.zzti)) {
            zzu.zzti = this.zzti;
        }
    }

    public final String zzaf() {
        return this.zztf;
    }

    public final String zzag() {
        return this.zztg;
    }

    public final String zzah() {
        return this.zzth;
    }

    public final String zzai() {
        return this.zzti;
    }
}
