package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Build;
import android.support.v4.content.h;
import android.util.Log;

public abstract class zzwx<T> {
    private static final Object zzbpg = new Object();
    private static boolean zzbph = false;
    private static volatile Boolean zzbpi = null;
    @SuppressLint({"StaticFieldLeak"})
    private static Context zzqx = null;
    private final zzxh zzbpj;
    final String zzbpk;
    private final String zzbpl;
    private final T zzbpm;
    private T zzbpn;
    private volatile zzwu zzbpo;
    private volatile SharedPreferences zzbpp;

    private zzwx(zzxh zzxh, String str, T t) {
        this.zzbpn = null;
        this.zzbpo = null;
        this.zzbpp = null;
        if (zzxh.zza(zzxh) != null) {
            this.zzbpj = zzxh;
            String valueOf = String.valueOf(zzxh.zzb(zzxh));
            String valueOf2 = String.valueOf(str);
            this.zzbpl = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
            String valueOf3 = String.valueOf(zzxh.zzc(zzxh));
            String valueOf4 = String.valueOf(str);
            this.zzbpk = valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3);
            this.zzbpm = t;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    /* synthetic */ zzwx(zzxh zzxh, String str, Object obj, zzxb zzxb) {
        this(zzxh, str, obj);
    }

    public static void init(Context context) {
        synchronized (zzbpg) {
            if (Build.VERSION.SDK_INT < 24 || !context.isDeviceProtectedStorage()) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
            }
            if (zzqx != context) {
                zzbpi = null;
            }
            zzqx = context;
        }
        zzbph = false;
    }

    /* access modifiers changed from: private */
    public static zzwx<Double> zza(zzxh zzxh, String str, double d2) {
        return new zzxe(zzxh, str, Double.valueOf(d2));
    }

    /* access modifiers changed from: private */
    public static zzwx<Integer> zza(zzxh zzxh, String str, int i) {
        return new zzxc(zzxh, str, Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public static zzwx<Long> zza(zzxh zzxh, String str, long j) {
        return new zzxb(zzxh, str, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static zzwx<String> zza(zzxh zzxh, String str, String str2) {
        return new zzxf(zzxh, str, str2);
    }

    /* access modifiers changed from: private */
    public static zzwx<Boolean> zza(zzxh zzxh, String str, boolean z) {
        return new zzxd(zzxh, str, Boolean.valueOf(z));
    }

    private static <V> V zza(zzxg<V> zzxg) {
        long clearCallingIdentity;
        try {
            return zzxg.zzsq();
        } catch (SecurityException unused) {
            clearCallingIdentity = Binder.clearCallingIdentity();
            V zzsq = zzxg.zzsq();
            Binder.restoreCallingIdentity(clearCallingIdentity);
            return zzsq;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(clearCallingIdentity);
            throw th;
        }
    }

    static boolean zzd(String str, boolean z) {
        try {
            if (zzso()) {
                return ((Boolean) zza(new zzxa(str, false))).booleanValue();
            }
            return false;
        } catch (SecurityException e) {
            Log.e("PhenotypeFlag", "Unable to read GServices, returning default value.", e);
            return false;
        }
    }

    @TargetApi(24)
    private final T zzsm() {
        if (zzd("gms:phenotype:phenotype_flag:debug_bypass_phenotype", false)) {
            String valueOf = String.valueOf(this.zzbpk);
            Log.w("PhenotypeFlag", valueOf.length() != 0 ? "Bypass reading Phenotype values for flag: ".concat(valueOf) : new String("Bypass reading Phenotype values for flag: "));
            return null;
        } else if (zzxh.zza(this.zzbpj) != null) {
            if (this.zzbpo == null) {
                this.zzbpo = zzwu.zza(zzqx.getContentResolver(), zzxh.zza(this.zzbpj));
            }
            String str = (String) zza(new zzwy(this, this.zzbpo));
            if (str != null) {
                return zzfa(str);
            }
            return null;
        } else {
            zzxh zzxh = this.zzbpj;
            return null;
        }
    }

    private final T zzsn() {
        zzxh zzxh = this.zzbpj;
        if (!zzso()) {
            return null;
        }
        try {
            String str = (String) zza(new zzwz(this));
            if (str != null) {
                return zzfa(str);
            }
            return null;
        } catch (SecurityException e) {
            String valueOf = String.valueOf(this.zzbpk);
            Log.e("PhenotypeFlag", valueOf.length() != 0 ? "Unable to read GServices for flag: ".concat(valueOf) : new String("Unable to read GServices for flag: "), e);
            return null;
        }
    }

    private static boolean zzso() {
        if (zzbpi == null) {
            Context context = zzqx;
            boolean z = false;
            if (context == null) {
                return false;
            }
            if (h.a(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                z = true;
            }
            zzbpi = Boolean.valueOf(z);
        }
        return zzbpi.booleanValue();
    }

    public final T get() {
        if (zzqx != null) {
            zzxh zzxh = this.zzbpj;
            T zzsm = zzsm();
            if (zzsm != null) {
                return zzsm;
            }
            T zzsn = zzsn();
            return zzsn != null ? zzsn : this.zzbpm;
        }
        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
    }

    /* access modifiers changed from: protected */
    public abstract T zzfa(String str);

    /* access modifiers changed from: package-private */
    public final /* synthetic */ String zzsp() {
        return zzws.zza(zzqx.getContentResolver(), this.zzbpl, (String) null);
    }
}
