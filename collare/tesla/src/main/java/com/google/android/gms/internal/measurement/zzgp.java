package com.google.android.gms.internal.measurement;

import android.os.Binder;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public final class zzgp extends zzfb {
    private final zzjt zzalo;
    private Boolean zzapb;
    private String zzapc;

    public zzgp(zzjt zzjt) {
        this(zzjt, null);
    }

    private zzgp(zzjt zzjt, String str) {
        Preconditions.checkNotNull(zzjt);
        this.zzalo = zzjt;
        this.zzapc = null;
    }

    private final void zzb(zzeb zzeb, boolean z) {
        Preconditions.checkNotNull(zzeb);
        zzc(zzeb.packageName, false);
        this.zzalo.zzgg().zzck(zzeb.zzafa);
    }

    private final void zzc(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzapb == null) {
                        if (!"com.google.android.gms".equals(this.zzapc) && !UidVerifier.isGooglePlayServicesUid(this.zzalo.getContext(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zzalo.getContext()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzapb = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzapb = Boolean.valueOf(z2);
                    }
                    if (this.zzapb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zzalo.zzgi().zziv().zzg("Measurement Service called with invalid calling package. appId", zzfi.zzbp(str));
                    throw e;
                }
            }
            if (this.zzapc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zzalo.getContext(), Binder.getCallingUid(), str)) {
                this.zzapc = str;
            }
            if (!str.equals(this.zzapc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
            }
            return;
        }
        this.zzalo.zzgi().zziv().log("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    @VisibleForTesting
    private final void zze(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (!zzez.zzajw.get().booleanValue() || !this.zzalo.zzgh().zzju()) {
            this.zzalo.zzgh().zzc(runnable);
        } else {
            runnable.run();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(zzeb zzeb, boolean z) {
        zzb(zzeb, false);
        try {
            List<zzkc> list = (List) this.zzalo.zzgh().zzb(new zzhf(this, zzeb)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkc zzkc : list) {
                if (z || !zzkd.zzcm(zzkc.name)) {
                    arrayList.add(new zzka(zzkc));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zze("Failed to get user attributes. appId", zzfi.zzbp(zzeb.packageName), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzef> zza(String str, String str2, zzeb zzeb) {
        zzb(zzeb, false);
        try {
            return (List) this.zzalo.zzgh().zzb(new zzgx(this, zzeb, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(String str, String str2, String str3, boolean z) {
        zzc(str, true);
        try {
            List<zzkc> list = (List) this.zzalo.zzgh().zzb(new zzgw(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkc zzkc : list) {
                if (z || !zzkd.zzcm(zzkc.name)) {
                    arrayList.add(new zzka(zzkc));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zze("Failed to get user attributes. appId", zzfi.zzbp(str), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzka> zza(String str, String str2, boolean z, zzeb zzeb) {
        zzb(zzeb, false);
        try {
            List<zzkc> list = (List) this.zzalo.zzgh().zzb(new zzgv(this, zzeb, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkc zzkc : list) {
                if (z || !zzkd.zzcm(zzkc.name)) {
                    arrayList.add(new zzka(zzkc));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zze("Failed to get user attributes. appId", zzfi.zzbp(zzeb.packageName), e);
            return Collections.emptyList();
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(long j, String str, String str2, String str3) {
        zze(new zzhh(this, str2, str3, str, j));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzeb zzeb) {
        zzb(zzeb, false);
        zze(new zzhg(this, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzef zzef, zzeb zzeb) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotNull(zzef.zzage);
        zzb(zzeb, false);
        zzef zzef2 = new zzef(zzef);
        zzef2.packageName = zzeb.packageName;
        zze(zzef.zzage.getValue() == null ? new zzgr(this, zzef2, zzeb) : new zzgs(this, zzef2, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzex zzex, zzeb zzeb) {
        Preconditions.checkNotNull(zzex);
        zzb(zzeb, false);
        zze(new zzha(this, zzex, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzex zzex, String str, String str2) {
        Preconditions.checkNotNull(zzex);
        Preconditions.checkNotEmpty(str);
        zzc(str, true);
        zze(new zzhb(this, zzex, str));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zza(zzka zzka, zzeb zzeb) {
        Preconditions.checkNotNull(zzka);
        zzb(zzeb, false);
        zze(zzka.getValue() == null ? new zzhd(this, zzka, zzeb) : new zzhe(this, zzka, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final byte[] zza(zzex zzex, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzex);
        zzc(str, true);
        this.zzalo.zzgi().zzjb().zzg("Log and bundle. event", this.zzalo.zzgf().zzbm(zzex.name));
        long nanoTime = this.zzalo.zzbt().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzalo.zzgh().zzc(new zzhc(this, zzex, str)).get();
            if (bArr == null) {
                this.zzalo.zzgi().zziv().zzg("Log and bundle returned null. appId", zzfi.zzbp(str));
                bArr = new byte[0];
            }
            this.zzalo.zzgi().zzjb().zzd("Log and bundle processed. event, size, time_ms", this.zzalo.zzgf().zzbm(zzex.name), Integer.valueOf(bArr.length), Long.valueOf((this.zzalo.zzbt().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zzd("Failed to log and bundle. appId, event, error", zzfi.zzbp(str), this.zzalo.zzgf().zzbm(zzex.name), e);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzb(zzeb zzeb) {
        zzb(zzeb, false);
        zze(new zzgq(this, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzb(zzef zzef) {
        Preconditions.checkNotNull(zzef);
        Preconditions.checkNotNull(zzef.zzage);
        zzc(zzef.packageName, true);
        zzef zzef2 = new zzef(zzef);
        zze(zzef.zzage.getValue() == null ? new zzgt(this, zzef2) : new zzgu(this, zzef2));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final String zzc(zzeb zzeb) {
        zzb(zzeb, false);
        return this.zzalo.zzh(zzeb);
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final void zzd(zzeb zzeb) {
        zzc(zzeb.packageName, false);
        zze(new zzgz(this, zzeb));
    }

    @Override // com.google.android.gms.internal.measurement.zzfa
    public final List<zzef> zze(String str, String str2, String str3) {
        zzc(str, true);
        try {
            return (List) this.zzalo.zzgh().zzb(new zzgy(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zzalo.zzgi().zziv().zzg("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }
}
