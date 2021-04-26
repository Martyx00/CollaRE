package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzfd extends zzdz {
    private String zzafa;
    private String zzafh;
    private long zzafl;
    private int zzagb;
    private int zzakd;
    private long zzake;
    private String zztf;
    private String zztg;
    private String zzth;

    zzfd(zzgn zzgn) {
        super(zzgn);
    }

    private final String zzgr() {
        zzab();
        zzfv();
        if (zzgk().zzbb(this.zzth) && !this.zzacv.isEnabled()) {
            return null;
        }
        try {
            return FirebaseInstanceId.a().c();
        } catch (IllegalStateException unused) {
            zzgi().zziy().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final String getGmpAppId() {
        zzch();
        return this.zzafa;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    public final String zzah() {
        zzch();
        return this.zzth;
    }

    /* access modifiers changed from: package-private */
    public final zzeb zzbl(String str) {
        zzab();
        zzfv();
        String zzah = zzah();
        String gmpAppId = getGmpAppId();
        zzch();
        String str2 = this.zztg;
        long zzis = (long) zzis();
        zzch();
        String str3 = this.zzafh;
        long zzgw = zzgk().zzgw();
        zzch();
        zzab();
        if (this.zzake == 0) {
            this.zzake = this.zzacv.zzgg().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzake;
        boolean isEnabled = this.zzacv.isEnabled();
        boolean z = !zzgj().zzamm;
        String zzgr = zzgr();
        zzch();
        long j2 = this.zzafl;
        long zzke = this.zzacv.zzke();
        int zzit = zzit();
        zzeh zzgk = zzgk();
        zzgk.zzfv();
        Boolean zzat = zzgk.zzat("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(zzat == null || zzat.booleanValue()).booleanValue();
        zzeh zzgk2 = zzgk();
        zzgk2.zzfv();
        Boolean zzat2 = zzgk2.zzat("google_analytics_ssaid_collection_enabled");
        return new zzeb(zzah, gmpAppId, str2, zzis, str3, zzgw, j, str, isEnabled, z, zzgr, j2, zzke, zzit, booleanValue, Boolean.valueOf(zzat2 == null || zzat2.booleanValue()).booleanValue(), zzgj().zzjo());
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzdu zzfx() {
        return super.zzfx();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzhm zzfy() {
        return super.zzfy();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfd zzfz() {
        return super.zzfz();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzik zzga() {
        return super.zzga();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzih zzgb() {
        return super.zzgb();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzfe zzgc() {
        return super.zzgc();
    }

    @Override // com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ zzjj zzgd() {
        return super.zzgd();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzer zzge() {
        return super.zzge();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfg zzgf() {
        return super.zzgf();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzkd zzgg() {
        return super.zzgg();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzgi zzgh() {
        return super.zzgh();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzfi zzgi() {
        return super.zzgi();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzft zzgj() {
        return super.zzgj();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzeh zzgk() {
        return super.zzgk();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ zzee zzgl() {
        return super.zzgl();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzdz
    public final boolean zzgn() {
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0148 A[SYNTHETIC, Splitter:B:50:0x0148] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x018d  */
    @Override // com.google.android.gms.internal.measurement.zzdz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzgo() {
        /*
        // Method dump skipped, instructions count: 400
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfd.zzgo():void");
    }

    /* access modifiers changed from: package-private */
    public final String zzir() {
        byte[] bArr = new byte[16];
        zzgg().zzlo().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new BigInteger(1, bArr));
    }

    /* access modifiers changed from: package-private */
    public final int zzis() {
        zzch();
        return this.zzakd;
    }

    /* access modifiers changed from: package-private */
    public final int zzit() {
        zzch();
        return this.zzagb;
    }
}
