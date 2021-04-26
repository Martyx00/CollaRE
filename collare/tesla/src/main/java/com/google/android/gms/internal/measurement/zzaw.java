package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzaw {
    private final Map<String, String> zzsm;
    private final String zzud;
    private final long zzwg = 0;
    private final String zzwh;
    private final boolean zzwi;
    private long zzwj;

    public zzaw(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        this.zzud = str;
        this.zzwh = str2;
        this.zzwi = z;
        this.zzwj = j2;
        this.zzsm = map != null ? new HashMap<>(map) : Collections.emptyMap();
    }

    public final String zzaz() {
        return this.zzud;
    }

    public final void zzb(long j) {
        this.zzwj = j;
    }

    public final long zzco() {
        return this.zzwg;
    }

    public final String zzcp() {
        return this.zzwh;
    }

    public final boolean zzcq() {
        return this.zzwi;
    }

    public final long zzcr() {
        return this.zzwj;
    }

    public final Map<String, String> zzcs() {
        return this.zzsm;
    }
}
