package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
public final class zzwh {
    private zzm zzbdn;
    private final Map<String, zzm> zzbmv;

    private zzwh() {
        this.zzbmv = new HashMap();
    }

    public final zzwh zzb(String str, zzm zzm) {
        this.zzbmv.put(str, zzm);
        return this;
    }

    public final zzwh zzm(zzm zzm) {
        this.zzbdn = zzm;
        return this;
    }

    public final zzwg zzry() {
        return new zzwg(this.zzbmv, this.zzbdn);
    }
}
