package com.google.android.gms.internal.measurement;

import com.google.firebase.analytics.FirebaseAnalytics;
import io.a.a.a.a.d.b;
import java.util.List;
import java.util.Map;

final class zzaas {
    static String zza(zzaaq zzaaq, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzaaq, sb, 0);
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01f8, code lost:
        if (((java.lang.Boolean) r6).booleanValue() == false) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x020a, code lost:
        if (((java.lang.Integer) r6).intValue() == 0) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x021b, code lost:
        if (((java.lang.Float) r6).floatValue() == com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED) goto L_0x01fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x022d, code lost:
        if (((java.lang.Double) r6).doubleValue() == 0.0d) goto L_0x01fa;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0260  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0262  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzaaq r12, java.lang.StringBuilder r13, int r14) {
        /*
        // Method dump skipped, instructions count: 679
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaas.zza(com.google.android.gms.internal.measurement.zzaaq, java.lang.StringBuilder, int):void");
    }

    static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                zzb(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                zzb(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzabl.zza(zzzb.zzfn((String) obj)));
                sb.append('\"');
            } else if (obj instanceof zzzb) {
                sb.append(": \"");
                sb.append(zzabl.zza((zzzb) obj));
                sb.append('\"');
            } else if (obj instanceof zzzv) {
                sb.append(" {");
                zza((zzzv) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                zzb(sb, i4, "key", entry2.getKey());
                zzb(sb, i4, FirebaseAnalytics.b.VALUE, entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    private static final String zzfp(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append(b.ROLL_OVER_FILE_NAME_SEPARATOR);
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
