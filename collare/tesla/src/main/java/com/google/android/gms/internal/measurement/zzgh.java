package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.v4.util.a;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.Map;

public final class zzgh extends zzjs implements zzej {
    @VisibleForTesting
    private static int zzane = 65535;
    @VisibleForTesting
    private static int zzanf = 2;
    private final Map<String, Map<String, String>> zzang = new a();
    private final Map<String, Map<String, Boolean>> zzanh = new a();
    private final Map<String, Map<String, Boolean>> zzani = new a();
    private final Map<String, zzkn> zzanj = new a();
    private final Map<String, Map<String, Integer>> zzank = new a();
    private final Map<String, String> zzanl = new a();

    zzgh(zzjt zzjt) {
        super(zzjt);
    }

    private final zzkn zza(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzkn();
        }
        zzaca zza = zzaca.zza(bArr, 0, bArr.length);
        zzkn zzkn = new zzkn();
        try {
            zzkn.zzb(zza);
            zzgi().zzjc().zze("Parsed config. version, gmp_app_id", zzkn.zzaum, zzkn.zzafa);
            return zzkn;
        } catch (IOException e) {
            zzgi().zziy().zze("Unable to merge remote config. appId", zzfi.zzbp(str), e);
            return new zzkn();
        }
    }

    private static Map<String, String> zza(zzkn zzkn) {
        a aVar = new a();
        if (!(zzkn == null || zzkn.zzauo == null)) {
            zzko[] zzkoArr = zzkn.zzauo;
            for (zzko zzko : zzkoArr) {
                if (zzko != null) {
                    aVar.put(zzko.zzny, zzko.value);
                }
            }
        }
        return aVar;
    }

    private final void zza(String str, zzkn zzkn) {
        a aVar = new a();
        a aVar2 = new a();
        a aVar3 = new a();
        if (!(zzkn == null || zzkn.zzaup == null)) {
            zzkm[] zzkmArr = zzkn.zzaup;
            for (zzkm zzkm : zzkmArr) {
                if (TextUtils.isEmpty(zzkm.name)) {
                    zzgi().zziy().log("EventConfig contained null event name");
                } else {
                    String zzal = AppMeasurement.Event.zzal(zzkm.name);
                    if (!TextUtils.isEmpty(zzal)) {
                        zzkm.name = zzal;
                    }
                    aVar.put(zzkm.name, zzkm.zzauj);
                    aVar2.put(zzkm.name, zzkm.zzauk);
                    if (zzkm.zzaul != null) {
                        if (zzkm.zzaul.intValue() < zzanf || zzkm.zzaul.intValue() > zzane) {
                            zzgi().zziy().zze("Invalid sampling rate. Event name, sample rate", zzkm.name, zzkm.zzaul);
                        } else {
                            aVar3.put(zzkm.name, zzkm.zzaul);
                        }
                    }
                }
            }
        }
        this.zzanh.put(str, aVar);
        this.zzani.put(str, aVar2);
        this.zzank.put(str, aVar3);
    }

    private final void zzbw(String str) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        if (this.zzanj.get(str) == null) {
            byte[] zzbh = zzjh().zzbh(str);
            if (zzbh == null) {
                this.zzang.put(str, null);
                this.zzanh.put(str, null);
                this.zzani.put(str, null);
                this.zzanj.put(str, null);
                this.zzanl.put(str, null);
                this.zzank.put(str, null);
                return;
            }
            zzkn zza = zza(str, zzbh);
            this.zzang.put(str, zza(zza));
            zza(str, zza);
            this.zzanj.put(str, zza);
            this.zzanl.put(str, null);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        zzkn zza = zza(str, bArr);
        if (zza == null) {
            return false;
        }
        zza(str, zza);
        this.zzanj.put(str, zza);
        this.zzanl.put(str, str2);
        this.zzang.put(str, zza(zza));
        zzjg().zza(str, zza.zzauq);
        try {
            zza.zzauq = null;
            byte[] bArr2 = new byte[zza.zzwb()];
            zza.zza(zzacb.zzb(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            zzgi().zziy().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzfi.zzbp(str), e);
        }
        zzek zzjh = zzjh();
        Preconditions.checkNotEmpty(str);
        zzjh.zzab();
        zzjh.zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzjh.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzjh.zzgi().zziv().zzg("Failed to update remote config (got 0). appId", zzfi.zzbp(str));
            }
        } catch (SQLiteException e2) {
            zzjh.zzgi().zziv().zze("Error storing remote config. appId", zzfi.zzbp(str), e2);
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    public final zzkn zzbx(String str) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        zzbw(str);
        return this.zzanj.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzby(String str) {
        zzab();
        return this.zzanl.get(str);
    }

    /* access modifiers changed from: protected */
    public final void zzbz(String str) {
        zzab();
        this.zzanl.put(str, null);
    }

    /* access modifiers changed from: package-private */
    public final void zzca(String str) {
        zzab();
        this.zzanj.remove(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcb(String str) {
        return "1".equals(zze(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzcc(String str) {
        return "1".equals(zze(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.internal.measurement.zzej
    public final String zze(String str, String str2) {
        zzab();
        zzbw(str);
        Map<String, String> map = this.zzang.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfu() {
        super.zzfu();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfv() {
        super.zzfv();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzfw() {
        super.zzfw();
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
    @Override // com.google.android.gms.internal.measurement.zzjs
    public final boolean zzgn() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzjz zzjf() {
        return super.zzjf();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzed zzjg() {
        return super.zzjg();
    }

    @Override // com.google.android.gms.internal.measurement.zzjr
    public final /* bridge */ /* synthetic */ zzek zzjh() {
        return super.zzjh();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzn(String str, String str2) {
        Boolean bool;
        zzab();
        zzbw(str);
        if (zzcb(str) && zzkd.zzcm(str2)) {
            return true;
        }
        if (zzcc(str) && zzkd.zzcg(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzanh.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzo(String str, String str2) {
        Boolean bool;
        zzab();
        zzbw(str);
        if (FirebaseAnalytics.a.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.zzani.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final int zzp(String str, String str2) {
        Integer num;
        zzab();
        zzbw(str);
        Map<String, Integer> map = this.zzank.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }
}
