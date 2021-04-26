package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzm;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* access modifiers changed from: package-private */
public final class zzgn {
    private static zzdz<zzm> zza(zzdz<zzm> zzdz) {
        try {
            return new zzdz<>(zzgj.zzj(zzdv(zzgj.zzc(zzdz.getObject()))), zzdz.zzog());
        } catch (UnsupportedEncodingException e) {
            zzdi.zza("Escape URI: unsupported encoding", e);
            return zzdz;
        }
    }

    static zzdz<zzm> zza(zzdz<zzm> zzdz, int... iArr) {
        String sb;
        for (int i : iArr) {
            if (!(zzgj.zzh(zzdz.getObject()) instanceof String)) {
                sb = "Escaping can only be applied to strings.";
            } else if (i != 12) {
                StringBuilder sb2 = new StringBuilder(39);
                sb2.append("Unsupported Value Escaping: ");
                sb2.append(i);
                sb = sb2.toString();
            } else {
                zzdz = zza(zzdz);
            }
            zzdi.e(sb);
        }
        return zzdz;
    }

    static String zzdv(String str) {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }
}
