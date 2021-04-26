package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;

public class zzcm extends zzar {
    private static zzcm zzaau;

    public zzcm(zzat zzat) {
        super(zzat);
    }

    @VisibleForTesting
    private static String zzd(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (!(obj instanceof Long)) {
            return obj instanceof Boolean ? String.valueOf(obj) : obj instanceof Throwable ? obj.getClass().getCanonicalName() : "-";
        }
        Long l = (Long) obj;
        if (Math.abs(l.longValue()) < 100) {
            return String.valueOf(obj);
        }
        String str = String.valueOf(obj).charAt(0) == '-' ? "-" : "";
        String valueOf = String.valueOf(Math.abs(l.longValue()));
        return str + Math.round(Math.pow(10.0d, (double) (valueOf.length() - 1))) + "..." + str + Math.round(Math.pow(10.0d, (double) valueOf.length()) - 1.0d);
    }

    public static zzcm zzet() {
        return zzaau;
    }

    public final void zza(zzch zzch, String str) {
        String zzch2 = zzch != null ? zzch.toString() : "no hit data";
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), zzch2);
    }

    public final void zza(Map<String, String> map, String str) {
        String str2;
        if (map != null) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sb.length() > 0) {
                    sb.append(',');
                }
                sb.append(entry.getKey());
                sb.append('=');
                sb.append(entry.getValue());
            }
            str2 = sb.toString();
        } else {
            str2 = "no hit data";
        }
        String valueOf = String.valueOf(str);
        zzd(valueOf.length() != 0 ? "Discarding hit. ".concat(valueOf) : new String("Discarding hit. "), str2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzar
    public final void zzac() {
        synchronized (zzcm.class) {
            zzaau = this;
        }
    }

    public final synchronized void zzb(int i, String str, Object obj, Object obj2, Object obj3) {
        Preconditions.checkNotNull(str);
        if (i < 0) {
            i = 0;
        }
        if (i >= 9) {
            i = 8;
        }
        char c2 = zzbv().zzds() ? 'C' : 'c';
        char charAt = "01VDIWEA?".charAt(i);
        String str2 = zzas.VERSION;
        String zzc = zzc(str, zzd(obj), zzd(obj2), zzd(obj3));
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 4 + String.valueOf(zzc).length());
        sb.append("3");
        sb.append(charAt);
        sb.append(c2);
        sb.append(str2);
        sb.append(":");
        sb.append(zzc);
        String sb2 = sb.toString();
        if (sb2.length() > 1024) {
            sb2 = sb2.substring(0, 1024);
        }
        zzcq zzcl = zzbs().zzcl();
        if (zzcl != null) {
            zzcl.zzfg().zzad(sb2);
        }
    }
}
