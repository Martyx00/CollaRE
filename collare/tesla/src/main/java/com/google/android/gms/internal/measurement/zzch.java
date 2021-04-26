package com.google.android.gms.internal.measurement;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

public final class zzch {
    private final List<zzbo> zzaai;
    private final long zzaaj;
    private final long zzaak;
    private final int zzaal;
    private final boolean zzaam;
    private final String zzaan;
    private final Map<String, String> zzsm;

    public zzch(zzaq zzaq, Map<String, String> map, long j, boolean z) {
        this(zzaq, map, j, z, 0, 0, null);
    }

    public zzch(zzaq zzaq, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzaq, map, j, z, j2, i, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzch(com.google.android.gms.internal.measurement.zzaq r1, java.util.Map<java.lang.String, java.lang.String> r2, long r3, boolean r5, long r6, int r8, java.util.List<com.google.android.gms.internal.measurement.zzbo> r9) {
        /*
        // Method dump skipped, instructions count: 230
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzch.<init>(com.google.android.gms.internal.measurement.zzaq, java.util.Map, long, boolean, long, int, java.util.List):void");
    }

    private static String zza(zzaq zzaq, Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzaq.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static String zzb(zzaq zzaq, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        String substring = obj2.substring(0, PKIFailureInfo.certRevoked);
        zzaq.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }

    private static boolean zzc(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    private final String zzd(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkArgument(!str.startsWith("&"), "Short param name required");
        String str3 = this.zzsm.get(str);
        return str3 != null ? str3 : str2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ht=");
        sb.append(this.zzaak);
        if (this.zzaaj != 0) {
            sb.append(", dbId=");
            sb.append(this.zzaaj);
        }
        if (this.zzaal != 0) {
            sb.append(", appUID=");
            sb.append(this.zzaal);
        }
        ArrayList arrayList = new ArrayList(this.zzsm.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String str = (String) obj;
            sb.append(", ");
            sb.append(str);
            sb.append("=");
            sb.append(this.zzsm.get(str));
        }
        return sb.toString();
    }

    public final Map<String, String> zzcs() {
        return this.zzsm;
    }

    public final int zzel() {
        return this.zzaal;
    }

    public final long zzem() {
        return this.zzaaj;
    }

    public final long zzen() {
        return this.zzaak;
    }

    public final List<zzbo> zzeo() {
        return this.zzaai;
    }

    public final boolean zzep() {
        return this.zzaam;
    }

    public final long zzeq() {
        return zzdd.zzaf(zzd("_s", "0"));
    }

    public final String zzer() {
        return zzd("_m", "");
    }
}
