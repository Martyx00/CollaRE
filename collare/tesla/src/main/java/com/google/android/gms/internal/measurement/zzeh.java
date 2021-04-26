package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzez;
import java.lang.reflect.InvocationTargetException;

public final class zzeh extends zzhi {
    private Boolean zzagi;
    private zzej zzagj = zzei.zzagk;
    private Boolean zzxy;

    zzeh(zzgn zzgn) {
        super(zzgn);
    }

    static String zzhn() {
        return zzez.zzaie.get();
    }

    public static long zzhq() {
        return zzez.zzajh.get().longValue();
    }

    public static long zzhr() {
        return zzez.zzaih.get().longValue();
    }

    public static boolean zzht() {
        return zzez.zzaid.get().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final long zza(String str, zzez.zza<Long> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return zza.get(Long.valueOf(Long.parseLong(zze))).longValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zza.get().longValue();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzej zzej) {
        this.zzagj = zzej;
    }

    @Override // com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final int zzas(String str) {
        return zzb(str, zzez.zzais);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final Boolean zzat(String str) {
        Preconditions.checkNotEmpty(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzgi().zziv().log("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
            if (applicationInfo == null) {
                zzgi().zziv().log("Failed to load metadata: ApplicationInfo is null");
                return null;
            } else if (applicationInfo.metaData == null) {
                zzgi().zziv().log("Failed to load metadata: Metadata bundle is null");
                return null;
            } else if (!applicationInfo.metaData.containsKey(str)) {
                return null;
            } else {
                return Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
            }
        } catch (PackageManager.NameNotFoundException e) {
            zzgi().zziv().zzg("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    public final boolean zzau(String str) {
        return "1".equals(this.zzagj.zze(str, "gaia_collection_enabled"));
    }

    public final boolean zzav(String str) {
        return "1".equals(this.zzagj.zze(str, "measurement.event_sampling_enabled"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaw(String str) {
        return zzd(str, zzez.zzajq);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzax(String str) {
        return zzd(str, zzez.zzajs);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzay(String str) {
        return zzd(str, zzez.zzajt);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaz(String str) {
        return zzd(str, zzez.zzajl);
    }

    public final int zzb(String str, zzez.zza<Integer> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return zza.get(Integer.valueOf(Integer.parseInt(zze))).intValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zza.get().intValue();
    }

    /* access modifiers changed from: package-private */
    public final String zzba(String str) {
        zzez.zza<String> zza = zzez.zzajm;
        return str == null ? zza.get() : zza.get(this.zzagj.zze(str, zza.getKey()));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbb(String str) {
        return zzd(str, zzez.zzaju);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbc(String str) {
        return zzd(str, zzez.zzajv);
    }

    /* access modifiers changed from: package-private */
    public final boolean zzbd(String str) {
        return zzd(str, zzez.zzajy);
    }

    @Override // com.google.android.gms.internal.measurement.zzhk, com.google.android.gms.internal.measurement.zzhi
    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final double zzc(String str, zzez.zza<Double> zza) {
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                try {
                    return zza.get(Double.valueOf(Double.parseDouble(zze))).doubleValue();
                } catch (NumberFormatException unused) {
                }
            }
        }
        return zza.get().doubleValue();
    }

    public final boolean zzd(String str, zzez.zza<Boolean> zza) {
        Boolean bool;
        if (str != null) {
            String zze = this.zzagj.zze(str, zza.getKey());
            if (!TextUtils.isEmpty(zze)) {
                bool = zza.get(Boolean.valueOf(Boolean.parseBoolean(zze)));
                return bool.booleanValue();
            }
        }
        bool = zza.get();
        return bool.booleanValue();
    }

    public final boolean zzds() {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzxy = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if (this.zzxy == null) {
                        this.zzxy = Boolean.TRUE;
                        zzgi().zziv().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzxy.booleanValue();
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

    public final long zzgw() {
        zzgl();
        return 12780;
    }

    public final boolean zzho() {
        zzgl();
        Boolean zzat = zzat("firebase_analytics_collection_deactivated");
        return zzat != null && zzat.booleanValue();
    }

    public final Boolean zzhp() {
        zzgl();
        return zzat("firebase_analytics_collection_enabled");
    }

    public final String zzhs() {
        String str;
        zzfk zzfk;
        Object e;
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "debug.firebase.analytics.app", "");
        } catch (ClassNotFoundException e2) {
            e = e2;
            zzfk = zzgi().zziv();
            str = "Could not find SystemProperties class";
            zzfk.zzg(str, e);
            return "";
        } catch (NoSuchMethodException e3) {
            e = e3;
            zzfk = zzgi().zziv();
            str = "Could not find SystemProperties.get() method";
            zzfk.zzg(str, e);
            return "";
        } catch (IllegalAccessException e4) {
            e = e4;
            zzfk = zzgi().zziv();
            str = "Could not access SystemProperties.get()";
            zzfk.zzg(str, e);
            return "";
        } catch (InvocationTargetException e5) {
            e = e5;
            zzfk = zzgi().zziv();
            str = "SystemProperties.get() threw an exception";
            zzfk.zzg(str, e);
            return "";
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzhu() {
        if (this.zzagi == null) {
            this.zzagi = zzat("app_measurement_lite");
            if (this.zzagi == null) {
                this.zzagi = false;
            }
        }
        return this.zzagi.booleanValue();
    }
}
