package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashMap;

public final class zzad extends zzi<zzad> {
    private String zzuc;
    private String zzud;
    private String zzue;
    private String zzuf;
    private boolean zzug;
    private String zzuh;
    private boolean zzui;
    private double zzuj;

    public final void setClientId(String str) {
        this.zzud = str;
    }

    public final void setUserId(String str) {
        this.zzue = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zzuc);
        hashMap.put("clientId", this.zzud);
        hashMap.put("userId", this.zzue);
        hashMap.put("androidAdId", this.zzuf);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzug));
        hashMap.put("sessionControl", this.zzuh);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzui));
        hashMap.put("sampleRate", Double.valueOf(this.zzuj));
        return zza((Object) hashMap);
    }

    public final void zza(boolean z) {
        this.zzug = z;
    }

    public final String zzay() {
        return this.zzuc;
    }

    public final String zzaz() {
        return this.zzud;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzad zzad) {
        zzad zzad2 = zzad;
        if (!TextUtils.isEmpty(this.zzuc)) {
            zzad2.zzuc = this.zzuc;
        }
        if (!TextUtils.isEmpty(this.zzud)) {
            zzad2.zzud = this.zzud;
        }
        if (!TextUtils.isEmpty(this.zzue)) {
            zzad2.zzue = this.zzue;
        }
        if (!TextUtils.isEmpty(this.zzuf)) {
            zzad2.zzuf = this.zzuf;
        }
        boolean z = true;
        if (this.zzug) {
            zzad2.zzug = true;
        }
        if (!TextUtils.isEmpty(this.zzuh)) {
            zzad2.zzuh = this.zzuh;
        }
        boolean z2 = this.zzui;
        if (z2) {
            zzad2.zzui = z2;
        }
        double d2 = this.zzuj;
        if (d2 != 0.0d) {
            if (d2 < 0.0d || d2 > 100.0d) {
                z = false;
            }
            Preconditions.checkArgument(z, "Sample rate must be between 0% and 100%");
            zzad2.zzuj = d2;
        }
    }

    public final void zzb(boolean z) {
        this.zzui = true;
    }

    public final String zzba() {
        return this.zzue;
    }

    public final String zzbb() {
        return this.zzuf;
    }

    public final boolean zzbc() {
        return this.zzug;
    }

    public final String zzbd() {
        return this.zzuh;
    }

    public final boolean zzbe() {
        return this.zzui;
    }

    public final double zzbf() {
        return this.zzuj;
    }

    public final void zzl(String str) {
        this.zzuc = str;
    }

    public final void zzm(String str) {
        this.zzuf = str;
    }
}
