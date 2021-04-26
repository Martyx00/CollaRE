package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzwi {
    private final String version;
    private final List<zzwk> zzbmt;
    private final Map<String, List<zzwg>> zzbmu;
    private final int zzow;

    private zzwi(List<zzwk> list, Map<String, List<zzwg>> map, String str, int i) {
        this.zzbmt = Collections.unmodifiableList(list);
        this.zzbmu = Collections.unmodifiableMap(map);
        this.version = str;
        this.zzow = i;
    }

    public static zzwj zzrz() {
        return new zzwj();
    }

    public final String getVersion() {
        return this.version;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbmt);
        String valueOf2 = String.valueOf(this.zzbmu);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 17 + String.valueOf(valueOf2).length());
        sb.append("Rules: ");
        sb.append(valueOf);
        sb.append("  Macros: ");
        sb.append(valueOf2);
        return sb.toString();
    }

    public final List<zzwk> zzre() {
        return this.zzbmt;
    }

    public final Map<String, List<zzwg>> zzsa() {
        return this.zzbmu;
    }
}
