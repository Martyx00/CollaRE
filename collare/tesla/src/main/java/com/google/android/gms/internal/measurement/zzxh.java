package com.google.android.gms.internal.measurement;

import android.net.Uri;

public final class zzxh {
    private final String zzbpu;
    private final Uri zzbpv;
    private final String zzbpw;
    private final String zzbpx;
    private final boolean zzbpy;
    private final boolean zzbpz;

    public zzxh(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    private zzxh(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.zzbpu = null;
        this.zzbpv = uri;
        this.zzbpw = str2;
        this.zzbpx = str3;
        this.zzbpy = false;
        this.zzbpz = false;
    }

    public final zzwx<Double> zzb(String str, double d2) {
        return zzwx.zza(this, str, d2);
    }

    public final zzwx<Integer> zzd(String str, int i) {
        return zzwx.zza(this, str, i);
    }

    public final zzwx<Long> zze(String str, long j) {
        return zzwx.zza(this, str, j);
    }

    public final zzwx<Boolean> zzf(String str, boolean z) {
        return zzwx.zza(this, str, z);
    }

    public final zzwx<String> zzv(String str, String str2) {
        return zzwx.zza(this, str, str2);
    }
}
