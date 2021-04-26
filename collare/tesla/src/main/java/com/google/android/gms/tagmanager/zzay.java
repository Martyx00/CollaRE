package com.google.android.gms.tagmanager;

import java.util.Arrays;

/* access modifiers changed from: package-private */
public final class zzay {
    final byte[] zzazp;
    final String zzny;

    zzay(String str, byte[] bArr) {
        this.zzny = str;
        this.zzazp = bArr;
    }

    public final String toString() {
        String str = this.zzny;
        int hashCode = Arrays.hashCode(this.zzazp);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
        sb.append("KeyAndSerialized: key = ");
        sb.append(str);
        sb.append(" serialized hash = ");
        sb.append(hashCode);
        return sb.toString();
    }
}
