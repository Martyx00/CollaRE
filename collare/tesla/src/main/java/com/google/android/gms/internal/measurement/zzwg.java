package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Map;

public final class zzwg {
    private final zzm zzbdn;
    private final Map<String, zzm> zzbmv;

    private zzwg(Map<String, zzm> map, zzm zzm) {
        this.zzbmv = map;
        this.zzbdn = zzm;
    }

    public static zzwh zzrx() {
        return new zzwh();
    }

    public final String toString() {
        String valueOf = String.valueOf(Collections.unmodifiableMap(this.zzbmv));
        String valueOf2 = String.valueOf(this.zzbdn);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32 + String.valueOf(valueOf2).length());
        sb.append("Properties: ");
        sb.append(valueOf);
        sb.append(" pushAfterEvaluate: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final void zza(String str, zzm zzm) {
        this.zzbmv.put(str, zzm);
    }

    public final zzm zzou() {
        return this.zzbdn;
    }

    public final Map<String, zzm> zzrg() {
        return Collections.unmodifiableMap(this.zzbmv);
    }
}
