package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

public class zzaq {
    private final zzat zzvm;

    protected zzaq(zzat zzat) {
        Preconditions.checkNotNull(zzat);
        this.zzvm = zzat;
    }

    private final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzat zzat = this.zzvm;
        zzcm zzcj = zzat != null ? zzat.zzcj() : null;
        if (zzcj != null) {
            String str2 = zzcc.zzyl.get();
            if (Log.isLoggable(str2, i)) {
                Log.println(i, str2, zzcm.zzc(str, obj, obj2, obj3));
            }
            if (i >= 5) {
                zzcj.zzb(i, str, obj, obj2, obj3);
                return;
            }
            return;
        }
        String str3 = zzcc.zzyl.get();
        if (Log.isLoggable(str3, i)) {
            Log.println(i, str3, zzc(str, obj, obj2, obj3));
        }
    }

    private static String zzb(Object obj) {
        return obj == null ? "" : obj instanceof String ? (String) obj : obj instanceof Boolean ? obj == Boolean.TRUE ? "true" : "false" : obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zzb = zzb(obj);
        String zzb2 = zzb(obj2);
        String zzb3 = zzb(obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzb)) {
            sb.append(str2);
            sb.append(zzb);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzb2)) {
            sb.append(str2);
            sb.append(zzb2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzb3)) {
            sb.append(str2);
            sb.append(zzb3);
        }
        return sb.toString();
    }

    public static boolean zzcg() {
        return Log.isLoggable(zzcc.zzyl.get(), 2);
    }

    /* access modifiers changed from: protected */
    public final Context getContext() {
        return this.zzvm.getContext();
    }

    public final void zza(String str, Object obj) {
        zza(2, str, obj, null, null);
    }

    public final void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, null);
    }

    public final void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public final void zzb(String str, Object obj) {
        zza(3, str, obj, null, null);
    }

    public final void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, null);
    }

    public final void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public final zzat zzbs() {
        return this.zzvm;
    }

    /* access modifiers changed from: protected */
    public final Clock zzbt() {
        return this.zzvm.zzbt();
    }

    /* access modifiers changed from: protected */
    public final zzcm zzbu() {
        return this.zzvm.zzbu();
    }

    /* access modifiers changed from: protected */
    public final zzbu zzbv() {
        return this.zzvm.zzbv();
    }

    /* access modifiers changed from: protected */
    public final zzk zzbw() {
        return this.zzvm.zzbw();
    }

    public final GoogleAnalytics zzbx() {
        return this.zzvm.zzck();
    }

    /* access modifiers changed from: protected */
    public final zzai zzby() {
        return this.zzvm.zzby();
    }

    /* access modifiers changed from: protected */
    public final zzbz zzbz() {
        return this.zzvm.zzbz();
    }

    public final void zzc(String str, Object obj) {
        zza(4, str, obj, null, null);
    }

    public final void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, null);
    }

    /* access modifiers changed from: protected */
    public final zzde zzca() {
        return this.zzvm.zzca();
    }

    /* access modifiers changed from: protected */
    public final zzcq zzcb() {
        return this.zzvm.zzcb();
    }

    /* access modifiers changed from: protected */
    public final zzbl zzcc() {
        return this.zzvm.zzcn();
    }

    /* access modifiers changed from: protected */
    public final zzah zzcd() {
        return this.zzvm.zzcm();
    }

    /* access modifiers changed from: protected */
    public final zzbe zzce() {
        return this.zzvm.zzce();
    }

    /* access modifiers changed from: protected */
    public final zzby zzcf() {
        return this.zzvm.zzcf();
    }

    public final void zzd(String str, Object obj) {
        zza(5, str, obj, null, null);
    }

    public final void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, null);
    }

    public final void zze(String str, Object obj) {
        zza(6, str, obj, null, null);
    }

    public final void zzq(String str) {
        zza(2, str, null, null, null);
    }

    public final void zzr(String str) {
        zza(3, str, null, null, null);
    }

    public final void zzs(String str) {
        zza(4, str, null, null, null);
    }

    public final void zzt(String str) {
        zza(5, str, null, null, null);
    }

    public final void zzu(String str) {
        zza(6, str, null, null, null);
    }
}
