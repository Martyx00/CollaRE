package com.google.android.gms.analytics;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzcl;

@VisibleForTesting
public final class zzd {
    private static String zza(String str, int i) {
        if (i <= 0) {
            zzcl.zzf("index out of range for prefix", str);
            return "";
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11);
        sb.append(str);
        sb.append(i);
        return sb.toString();
    }

    public static String zzb(int i) {
        return zza("&cd", i);
    }

    public static String zzc(int i) {
        return zza("cd", i);
    }

    public static String zzd(int i) {
        return zza("&cm", i);
    }

    public static String zze(int i) {
        return zza("cm", i);
    }

    public static String zzf(int i) {
        return zza("&pr", i);
    }

    public static String zzg(int i) {
        return zza("pr", i);
    }

    public static String zzh(int i) {
        return zza("&promo", i);
    }

    public static String zzi(int i) {
        return zza("promo", i);
    }

    public static String zzj(int i) {
        return zza("pi", i);
    }

    public static String zzk(int i) {
        return zza("&il", i);
    }

    public static String zzl(int i) {
        return zza("il", i);
    }

    public static String zzm(int i) {
        return zza("cd", i);
    }

    public static String zzn(int i) {
        return zza("cm", i);
    }
}
