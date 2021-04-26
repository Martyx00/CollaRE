package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public final class zzea {
    private final zzgn zzacv;
    private String zzaez;
    private String zzafa;
    private String zzafb;
    private String zzafc;
    private long zzafd;
    private long zzafe;
    private long zzaff;
    private long zzafg;
    private String zzafh;
    private long zzafi;
    private long zzafj;
    private boolean zzafk;
    private long zzafl;
    private boolean zzafm;
    private boolean zzafn;
    private long zzafo;
    private long zzafp;
    private long zzafq;
    private long zzafr;
    private long zzafs;
    private long zzaft;
    private String zzafu;
    private boolean zzafv;
    private long zzafw;
    private long zzafx;
    private String zztg;
    private final String zzth;

    zzea(zzgn zzgn, String str) {
        Preconditions.checkNotNull(zzgn);
        Preconditions.checkNotEmpty(str);
        this.zzacv = zzgn;
        this.zzth = str;
        this.zzacv.zzgh().zzab();
    }

    public final String getAppInstanceId() {
        this.zzacv.zzgh().zzab();
        return this.zzaez;
    }

    public final String getGmpAppId() {
        this.zzacv.zzgh().zzab();
        return this.zzafa;
    }

    public final boolean isMeasurementEnabled() {
        this.zzacv.zzgh().zzab();
        return this.zzafk;
    }

    public final void setAppVersion(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zztg, str);
        this.zztg = str;
    }

    public final void setMeasurementEnabled(boolean z) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafk != z;
        this.zzafk = z;
    }

    public final void zzaa(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafp != j;
        this.zzafp = j;
    }

    public final void zzab(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafq != j;
        this.zzafq = j;
    }

    public final void zzac(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafr != j;
        this.zzafr = j;
    }

    public final void zzad(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzaft != j;
        this.zzaft = j;
    }

    public final void zzae(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafs != j;
        this.zzafs = j;
    }

    public final void zzaf(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafl != j;
        this.zzafl = j;
    }

    public final String zzag() {
        this.zzacv.zzgh().zzab();
        return this.zztg;
    }

    public final String zzah() {
        this.zzacv.zzgh().zzab();
        return this.zzth;
    }

    public final void zzam(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zzaez, str);
        this.zzaez = str;
    }

    public final void zzan(String str) {
        this.zzacv.zzgh().zzab();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.zzafv |= !zzkd.zzs(this.zzafa, str);
        this.zzafa = str;
    }

    public final void zzao(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zzafb, str);
        this.zzafb = str;
    }

    public final void zzap(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zzafc, str);
        this.zzafc = str;
    }

    public final void zzaq(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zzafh, str);
        this.zzafh = str;
    }

    public final void zzar(String str) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= !zzkd.zzs(this.zzafu, str);
        this.zzafu = str;
    }

    public final void zzd(boolean z) {
        this.zzacv.zzgh().zzab();
        this.zzafv = this.zzafm != z;
        this.zzafm = z;
    }

    public final void zze(boolean z) {
        this.zzacv.zzgh().zzab();
        this.zzafv = this.zzafn != z;
        this.zzafn = z;
    }

    public final void zzgp() {
        this.zzacv.zzgh().zzab();
        this.zzafv = false;
    }

    public final String zzgq() {
        this.zzacv.zzgh().zzab();
        return this.zzafb;
    }

    public final String zzgr() {
        this.zzacv.zzgh().zzab();
        return this.zzafc;
    }

    public final long zzgs() {
        this.zzacv.zzgh().zzab();
        return this.zzafe;
    }

    public final long zzgt() {
        this.zzacv.zzgh().zzab();
        return this.zzaff;
    }

    public final long zzgu() {
        this.zzacv.zzgh().zzab();
        return this.zzafg;
    }

    public final String zzgv() {
        this.zzacv.zzgh().zzab();
        return this.zzafh;
    }

    public final long zzgw() {
        this.zzacv.zzgh().zzab();
        return this.zzafi;
    }

    public final long zzgx() {
        this.zzacv.zzgh().zzab();
        return this.zzafj;
    }

    public final long zzgy() {
        this.zzacv.zzgh().zzab();
        return this.zzafd;
    }

    public final long zzgz() {
        this.zzacv.zzgh().zzab();
        return this.zzafw;
    }

    public final long zzha() {
        this.zzacv.zzgh().zzab();
        return this.zzafx;
    }

    public final void zzhb() {
        this.zzacv.zzgh().zzab();
        long j = this.zzafd + 1;
        if (j > 2147483647L) {
            this.zzacv.zzgi().zziy().zzg("Bundle index overflow. appId", zzfi.zzbp(this.zzth));
            j = 0;
        }
        this.zzafv = true;
        this.zzafd = j;
    }

    public final long zzhc() {
        this.zzacv.zzgh().zzab();
        return this.zzafo;
    }

    public final long zzhd() {
        this.zzacv.zzgh().zzab();
        return this.zzafp;
    }

    public final long zzhe() {
        this.zzacv.zzgh().zzab();
        return this.zzafq;
    }

    public final long zzhf() {
        this.zzacv.zzgh().zzab();
        return this.zzafr;
    }

    public final long zzhg() {
        this.zzacv.zzgh().zzab();
        return this.zzaft;
    }

    public final long zzhh() {
        this.zzacv.zzgh().zzab();
        return this.zzafs;
    }

    public final String zzhi() {
        this.zzacv.zzgh().zzab();
        return this.zzafu;
    }

    public final String zzhj() {
        this.zzacv.zzgh().zzab();
        String str = this.zzafu;
        zzar(null);
        return str;
    }

    public final long zzhk() {
        this.zzacv.zzgh().zzab();
        return this.zzafl;
    }

    public final boolean zzhl() {
        this.zzacv.zzgh().zzab();
        return this.zzafm;
    }

    public final boolean zzhm() {
        this.zzacv.zzgh().zzab();
        return this.zzafn;
    }

    public final void zzr(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafe != j;
        this.zzafe = j;
    }

    public final void zzs(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzaff != j;
        this.zzaff = j;
    }

    public final void zzt(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafg != j;
        this.zzafg = j;
    }

    public final void zzu(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafi != j;
        this.zzafi = j;
    }

    public final void zzv(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafj != j;
        this.zzafj = j;
    }

    public final void zzw(long j) {
        boolean z = true;
        Preconditions.checkArgument(j >= 0);
        this.zzacv.zzgh().zzab();
        boolean z2 = this.zzafv;
        if (this.zzafd == j) {
            z = false;
        }
        this.zzafv = z | z2;
        this.zzafd = j;
    }

    public final void zzx(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafw != j;
        this.zzafw = j;
    }

    public final void zzy(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafx != j;
        this.zzafx = j;
    }

    public final void zzz(long j) {
        this.zzacv.zzgh().zzab();
        this.zzafv |= this.zzafo != j;
        this.zzafo = j;
    }
}
