package com.google.android.gms.internal.measurement;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;

public final class zzbu {
    private final zzat zzqm;
    private volatile Boolean zzxy;
    private String zzxz;
    private Set<Integer> zzya;

    protected zzbu(zzat zzat) {
        Preconditions.checkNotNull(zzat);
        this.zzqm = zzat;
    }

    public static boolean zzdt() {
        return zzcc.zzyk.get().booleanValue();
    }

    public static int zzdu() {
        return zzcc.zzzh.get().intValue();
    }

    public static long zzdv() {
        return zzcc.zzys.get().longValue();
    }

    public static long zzdw() {
        return zzcc.zzyv.get().longValue();
    }

    public static int zzdx() {
        return zzcc.zzyx.get().intValue();
    }

    public static int zzdy() {
        return zzcc.zzyy.get().intValue();
    }

    @VisibleForTesting
    public static String zzdz() {
        return zzcc.zzza.get();
    }

    @VisibleForTesting
    public static String zzea() {
        return zzcc.zzyz.get();
    }

    public static String zzeb() {
        return zzcc.zzzb.get();
    }

    public static long zzed() {
        return zzcc.zzzp.get().longValue();
    }

    public final boolean zzds() {
        if (this.zzxy == null) {
            synchronized (this) {
                if (this.zzxy == null) {
                    ApplicationInfo applicationInfo = this.zzqm.getContext().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        this.zzxy = Boolean.valueOf(str != null && str.equals(myProcessName));
                    }
                    if ((this.zzxy == null || !this.zzxy.booleanValue()) && "com.google.android.gms.analytics".equals(myProcessName)) {
                        this.zzxy = Boolean.TRUE;
                    }
                    if (this.zzxy == null) {
                        this.zzxy = Boolean.TRUE;
                        this.zzqm.zzbu().zzu("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzxy.booleanValue();
    }

    public final Set<Integer> zzec() {
        String str;
        String str2 = zzcc.zzzk.get();
        if (this.zzya == null || (str = this.zzxz) == null || !str.equals(str2)) {
            String[] split = TextUtils.split(str2, ",");
            HashSet hashSet = new HashSet();
            for (String str3 : split) {
                try {
                    hashSet.add(Integer.valueOf(Integer.parseInt(str3)));
                } catch (NumberFormatException unused) {
                }
            }
            this.zzxz = str2;
            this.zzya = hashSet;
        }
        return this.zzya;
    }
}
