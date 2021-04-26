package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.HttpUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.a.a.a.a.b.a;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@VisibleForTesting
public final class zzdd {
    private static final char[] zzacc = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static MessageDigest getMessageDigest(String str) {
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return null;
    }

    public static double zza(String str, double d2) {
        if (str == null) {
            return 100.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            return 100.0d;
        }
    }

    public static zzv zza(zzcm zzcm, String str) {
        Preconditions.checkNotNull(zzcm);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        new HashMap();
        try {
            String valueOf = String.valueOf(str);
            Map<String, String> parse = HttpUtils.parse(new URI(valueOf.length() != 0 ? "?".concat(valueOf) : new String("?")), "UTF-8");
            zzv zzv = new zzv();
            zzv.zzf(parse.get("utm_content"));
            zzv.zzd(parse.get("utm_medium"));
            zzv.setName(parse.get("utm_campaign"));
            zzv.zzc(parse.get("utm_source"));
            zzv.zze(parse.get("utm_term"));
            zzv.zzg(parse.get("utm_id"));
            zzv.zzh(parse.get("anid"));
            zzv.zzi(parse.get("gclid"));
            zzv.zzj(parse.get("dclid"));
            zzv.zzk(parse.get(FirebaseAnalytics.b.ACLID));
            return zzv;
        } catch (URISyntaxException e) {
            zzcm.zzd("No valid campaign data found", e);
            return null;
        }
    }

    public static String zza(Locale locale) {
        if (locale == null) {
            return null;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language.toLowerCase(locale));
        if (!TextUtils.isEmpty(locale.getCountry())) {
            sb.append("-");
            sb.append(locale.getCountry().toLowerCase(locale));
        }
        return sb.toString();
    }

    public static void zza(Map<String, String> map, String str, Map<String, String> map2) {
        zzb(map, str, map2.get(str));
    }

    public static boolean zza(double d2, String str) {
        int i;
        if (d2 > 0.0d && d2 < 100.0d) {
            if (!TextUtils.isEmpty(str)) {
                i = 0;
                for (int length = str.length() - 1; length >= 0; length--) {
                    char charAt = str.charAt(length);
                    i = ((i << 6) & 268435455) + charAt + (charAt << 14);
                    int i2 = 266338304 & i;
                    if (i2 != 0) {
                        i ^= i2 >> 21;
                    }
                }
            } else {
                i = 1;
            }
            if (((double) (i % a.DEFAULT_TIMEOUT)) >= d2 * 100.0d) {
                return true;
            }
        }
        return false;
    }

    public static boolean zza(Context context, String str, boolean z) {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 0);
            return receiverInfo != null && receiverInfo.enabled && (!z || receiverInfo.exported);
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    public static Map<String, String> zzae(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            String[] split = str2.split("=", 3);
            String str3 = null;
            if (split.length > 1) {
                hashMap.put(split[0], TextUtils.isEmpty(split[1]) ? null : split[1]);
                if (split.length == 3 && !TextUtils.isEmpty(split[1]) && !hashMap.containsKey(split[1])) {
                    String str4 = split[1];
                    if (!TextUtils.isEmpty(split[2])) {
                        str3 = split[2];
                    }
                    hashMap.put(str4, str3);
                }
            } else if (split.length == 1 && split[0].length() != 0) {
                hashMap.put(split[0], null);
            }
        }
        return hashMap;
    }

    public static long zzaf(String str) {
        if (str == null) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static String zzag(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.contains("?")) {
            String[] split = str.split("[\\?]");
            if (split.length > 1) {
                str = split[1];
            }
        }
        if (str.contains("%3D")) {
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        } else if (!str.contains("=")) {
            return null;
        }
        Map<String, String> zzae = zzae(str);
        String[] strArr = {"dclid", "utm_source", "gclid", FirebaseAnalytics.b.ACLID, "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "anid", "gmob_t"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (!TextUtils.isEmpty(zzae.get(strArr[i]))) {
                if (sb.length() > 0) {
                    sb.append("&");
                }
                sb.append(strArr[i]);
                sb.append("=");
                sb.append(zzae.get(strArr[i]));
            }
        }
        return sb.toString();
    }

    public static boolean zzah(String str) {
        return TextUtils.isEmpty(str) || !str.startsWith("http:");
    }

    public static void zzb(Map<String, String> map, String str, String str2) {
        if (str2 != null && !map.containsKey(str)) {
            map.put(str, str2);
        }
    }

    public static void zzb(Map<String, String> map, String str, boolean z) {
        if (!map.containsKey(str)) {
            map.put(str, z ? "1" : "0");
        }
    }

    public static boolean zzb(String str, boolean z) {
        return str == null || str.equalsIgnoreCase("true") || str.equalsIgnoreCase("yes") || str.equalsIgnoreCase("1") || (!str.equalsIgnoreCase("false") && !str.equalsIgnoreCase("no") && !str.equalsIgnoreCase("0"));
    }

    public static String zzc(boolean z) {
        return z ? "1" : "0";
    }

    public static void zzc(Map<String, String> map, String str, String str2) {
        if (str2 != null && TextUtils.isEmpty(map.get(str))) {
            map.put(str, str2);
        }
    }

    public static boolean zzc(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 0);
            return serviceInfo != null && serviceInfo.enabled;
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
