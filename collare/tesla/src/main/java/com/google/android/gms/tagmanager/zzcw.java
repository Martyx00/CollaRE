package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public class zzcw {
    private static String zzbar;
    @VisibleForTesting
    static Map<String, String> zzbas = new HashMap();

    public static void zzdh(String str) {
        synchronized (zzcw.class) {
            zzbar = str;
        }
    }

    static void zzf(Context context, String str) {
        zzft.zza(context, "gtm_install_referrer", "referrer", str);
        zzh(context, str);
    }

    public static String zzg(Context context, String str) {
        if (zzbar == null) {
            synchronized (zzcw.class) {
                if (zzbar == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    zzbar = sharedPreferences != null ? sharedPreferences.getString("referrer", "") : "";
                }
            }
        }
        return zzt(zzbar, str);
    }

    public static void zzh(Context context, String str) {
        String zzt = zzt(str, "conv");
        if (zzt != null && zzt.length() > 0) {
            zzbas.put(zzt, str);
            zzft.zza(context, "gtm_click_referrers", zzt, str);
        }
    }

    public static String zzt(String str, String str2) {
        if (str2 != null) {
            String valueOf = String.valueOf(str);
            return Uri.parse(valueOf.length() != 0 ? "http://hostname/?".concat(valueOf) : new String("http://hostname/?")).getQueryParameter(str2);
        } else if (str.length() > 0) {
            return str;
        } else {
            return null;
        }
    }
}
