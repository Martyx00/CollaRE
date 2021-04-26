package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.a;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.util.Map;

public final class zzdu extends zzdy {
    private final Map<String, Long> zzaeu = new a();
    private final Map<String, Integer> zzaev = new a();
    private long zzaew;

    public zzdu(zzgn zzgn) {
        super(zzgn);
    }

    private final void zza(long j, zzig zzig) {
        if (zzig == null) {
            zzgi().zzjc().log("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            zzgi().zzjc().zzg("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzih.zza(zzig, bundle, true);
            zzfy().logEvent("am", "_xa", bundle);
        }
    }

    /* access modifiers changed from: public */
    private final void zza(String str, long j) {
        zzfv();
        zzab();
        Preconditions.checkNotEmpty(str);
        if (this.zzaev.isEmpty()) {
            this.zzaew = j;
        }
        Integer num = this.zzaev.get(str);
        if (num != null) {
            this.zzaev.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.zzaev.size() >= 100) {
            zzgi().zziy().log("Too many ads visible");
        } else {
            this.zzaev.put(str, 1);
            this.zzaeu.put(str, Long.valueOf(j));
        }
    }

    private final void zza(String str, long j, zzig zzig) {
        if (zzig == null) {
            zzgi().zzjc().log("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            zzgi().zzjc().zzg("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzih.zza(zzig, bundle, true);
            zzfy().logEvent("am", "_xu", bundle);
        }
    }

    /* access modifiers changed from: public */
    private final void zzb(String str, long j) {
        zzfv();
        zzab();
        Preconditions.checkNotEmpty(str);
        Integer num = this.zzaev.get(str);
        if (num != null) {
            zzig zzkn = zzgb().zzkn();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.zzaev.remove(str);
                Long l = this.zzaeu.get(str);
                if (l == null) {
                    zzgi().zziv().log("First ad unit exposure time was never set");
                } else {
                    this.zzaeu.remove(str);
                    zza(str, j - l.longValue(), zzkn);
                }
                if (this.zzaev.isEmpty()) {
                    long j2 = this.zzaew;
                    if (j2 == 0) {
                        zzgi().zziv().log("First ad exposure time was never set");
                        return;
                    }
                    zza(j - j2, zzkn);
                    this.zzaew = 0;
                    return;
                }
                return;
            }
            this.zzaev.put(str, Integer.valueOf(intValue));
            return;
        }
        zzgi().zziv().zzg("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    /* access modifiers changed from: public */
    private final void zzq(long j) {
        for (String str : this.zzaeu.keySet()) {
            this.zzaeu.put(str, Long.valueOf(j));
        }
        if (!this.zzaeu.isEmpty()) {
            this.zzaew = j;
        }
    }

    public final void beginAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzgi().zziv().log("Ad unit id must be a non-empty string");
            return;
        }
        zzgh().zzc(new zzdv(this, str, zzbt().elapsedRealtime()));
    }

    public final void endAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzgi().zziv().log("Ad unit id must be a non-empty string");
            return;
        }
        zzgh().zzc(new zzdw(this, str, zzbt().elapsedRealtime()));
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Override // com.google.android.gms.internal.measurement.zzhi, com.google.android.gms.internal.measurement.zzdy
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
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

    public final void zzp(long j) {
        zzig zzkn = zzgb().zzkn();
        for (String str : this.zzaeu.keySet()) {
            zza(str, j - this.zzaeu.get(str).longValue(), zzkn);
        }
        if (!this.zzaeu.isEmpty()) {
            zza(j - this.zzaew, zzkn);
        }
        zzq(j);
    }
}
