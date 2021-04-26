package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzjv implements zzfo {
    private final /* synthetic */ zzjt zzasn;
    private final /* synthetic */ String zzaso;

    zzjv(zzjt zzjt, String str) {
        this.zzasn = zzjt;
        this.zzaso = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzfo
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzasn.zza(i, th, bArr, this.zzaso);
    }
}
