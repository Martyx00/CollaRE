package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzi;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@VisibleForTesting
public abstract class zzi<T extends zzi> {
    public static String zza(Object obj) {
        return zza(obj, 0);
    }

    private static String zza(Object obj, int i) {
        if (i > 10) {
            return "ERROR: Recursive toString calls";
        }
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return TextUtils.isEmpty((String) obj) ? "" : obj.toString();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0 ? "" : obj.toString();
        }
        if (obj instanceof Long) {
            return ((Long) obj).longValue() == 0 ? "" : obj.toString();
        }
        if (obj instanceof Double) {
            return ((Double) obj).doubleValue() == 0.0d ? "" : obj.toString();
        }
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue() ? "" : obj.toString();
        }
        if (obj instanceof List) {
            StringBuilder sb = new StringBuilder();
            if (i > 0) {
                sb.append("[");
            }
            int length = sb.length();
            for (Object obj2 : (List) obj) {
                if (sb.length() > length) {
                    sb.append(", ");
                }
                sb.append(zza(obj2, i + 1));
            }
            if (i > 0) {
                sb.append("]");
            }
            return sb.toString();
        } else if (!(obj instanceof Map)) {
            return obj.toString();
        } else {
            StringBuilder sb2 = new StringBuilder();
            boolean z = false;
            int i2 = 0;
            for (Map.Entry entry : new TreeMap((Map) obj).entrySet()) {
                String zza = zza(entry.getValue(), i + 1);
                if (!TextUtils.isEmpty(zza)) {
                    if (i > 0 && !z) {
                        sb2.append("{");
                        i2 = sb2.length();
                        z = true;
                    }
                    if (sb2.length() > i2) {
                        sb2.append(", ");
                    }
                    sb2.append((String) entry.getKey());
                    sb2.append('=');
                    sb2.append(zza);
                }
            }
            if (z) {
                sb2.append("}");
            }
            return sb2.toString();
        }
    }

    public static String zza(Map map) {
        return zza(map, 1);
    }

    public abstract void zzb(T t);
}
