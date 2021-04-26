package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashMap;

public final class zzv extends zzi<zzv> {
    private String name;
    private String zzno;
    private String zztj;
    private String zztk;
    private String zztl;
    private String zztm;
    private String zztn;
    private String zzto;
    private String zztp;
    private String zztq;

    public final String getId() {
        return this.zzno;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSource() {
        return this.zztj;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.name);
        hashMap.put(FirebaseAnalytics.b.SOURCE, this.zztj);
        hashMap.put(FirebaseAnalytics.b.MEDIUM, this.zztk);
        hashMap.put("keyword", this.zztl);
        hashMap.put(FirebaseAnalytics.b.CONTENT, this.zztm);
        hashMap.put("id", this.zzno);
        hashMap.put("adNetworkId", this.zztn);
        hashMap.put("gclid", this.zzto);
        hashMap.put("dclid", this.zztp);
        hashMap.put(FirebaseAnalytics.b.ACLID, this.zztq);
        return zza((Object) hashMap);
    }

    public final String zzaj() {
        return this.zztk;
    }

    public final String zzak() {
        return this.zztl;
    }

    public final String zzal() {
        return this.zztm;
    }

    public final String zzam() {
        return this.zztn;
    }

    public final String zzan() {
        return this.zzto;
    }

    public final String zzao() {
        return this.zztp;
    }

    public final String zzap() {
        return this.zztq;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.analytics.zzi] */
    @Override // com.google.android.gms.analytics.zzi
    public final /* synthetic */ void zzb(zzv zzv) {
        zzv zzv2 = zzv;
        if (!TextUtils.isEmpty(this.name)) {
            zzv2.name = this.name;
        }
        if (!TextUtils.isEmpty(this.zztj)) {
            zzv2.zztj = this.zztj;
        }
        if (!TextUtils.isEmpty(this.zztk)) {
            zzv2.zztk = this.zztk;
        }
        if (!TextUtils.isEmpty(this.zztl)) {
            zzv2.zztl = this.zztl;
        }
        if (!TextUtils.isEmpty(this.zztm)) {
            zzv2.zztm = this.zztm;
        }
        if (!TextUtils.isEmpty(this.zzno)) {
            zzv2.zzno = this.zzno;
        }
        if (!TextUtils.isEmpty(this.zztn)) {
            zzv2.zztn = this.zztn;
        }
        if (!TextUtils.isEmpty(this.zzto)) {
            zzv2.zzto = this.zzto;
        }
        if (!TextUtils.isEmpty(this.zztp)) {
            zzv2.zztp = this.zztp;
        }
        if (!TextUtils.isEmpty(this.zztq)) {
            zzv2.zztq = this.zztq;
        }
    }

    public final void zzc(String str) {
        this.zztj = str;
    }

    public final void zzd(String str) {
        this.zztk = str;
    }

    public final void zze(String str) {
        this.zztl = str;
    }

    public final void zzf(String str) {
        this.zztm = str;
    }

    public final void zzg(String str) {
        this.zzno = str;
    }

    public final void zzh(String str) {
        this.zztn = str;
    }

    public final void zzi(String str) {
        this.zzto = str;
    }

    public final void zzj(String str) {
        this.zztp = str;
    }

    public final void zzk(String str) {
        this.zztq = str;
    }
}
