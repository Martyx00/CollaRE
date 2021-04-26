package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tagmanager.zzgj;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzwj {
    private String version;
    private final List<zzwk> zzbmt;
    private final Map<String, List<zzwg>> zzbmu;
    private int zzow;

    private zzwj() {
        this.zzbmt = new ArrayList();
        this.zzbmu = new HashMap();
        this.version = "";
        this.zzow = 0;
    }

    public final zzwj zzad(int i) {
        this.zzow = i;
        return this;
    }

    public final zzwj zzb(zzwk zzwk) {
        this.zzbmt.add(zzwk);
        return this;
    }

    public final zzwj zzc(zzwg zzwg) {
        String zzc = zzgj.zzc(zzwg.zzrg().get(zzb.INSTANCE_NAME.toString()));
        List<zzwg> list = this.zzbmu.get(zzc);
        if (list == null) {
            list = new ArrayList<>();
            this.zzbmu.put(zzc, list);
        }
        list.add(zzwg);
        return this;
    }

    public final zzwj zzev(String str) {
        this.version = str;
        return this;
    }

    public final zzwi zzsb() {
        return new zzwi(this.zzbmt, this.zzbmu, this.version, this.zzow);
    }
}
