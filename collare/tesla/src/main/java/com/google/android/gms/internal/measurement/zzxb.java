package com.google.android.gms.internal.measurement;

import android.util.Log;

/* access modifiers changed from: package-private */
public final class zzxb extends zzwx<Long> {
    zzxb(zzxh zzxh, String str, Long l) {
        super(zzxh, str, l, null);
    }

    /* access modifiers changed from: private */
    /* renamed from: zzfb */
    public final Long zzfa(String str) {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException unused) {
            String str2 = this.zzbpk;
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 25 + String.valueOf(str).length());
            sb.append("Invalid long value for ");
            sb.append(str2);
            sb.append(": ");
            sb.append(str);
            Log.e("PhenotypeFlag", sb.toString());
            return null;
        }
    }
}
