package com.google.android.gms.internal.measurement;

import android.util.Log;

/* access modifiers changed from: package-private */
public final class zzxc extends zzwx<Integer> {
    zzxc(zzxh zzxh, String str, Integer num) {
        super(zzxh, str, num, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzfc */
    public final Integer zzfa(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException unused) {
            String str2 = this.zzbpk;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 28 + String.valueOf(str).length());
            sb.append("Invalid integer value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
