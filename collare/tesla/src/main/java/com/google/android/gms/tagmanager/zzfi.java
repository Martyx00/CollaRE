package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzwg;
import com.google.android.gms.internal.measurement.zzwk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class zzfi {
    private final Set<zzwk> zzbde = new HashSet();
    private final Map<zzwk, List<zzwg>> zzbdo = new HashMap();
    private final Map<zzwk, List<zzwg>> zzbdp = new HashMap();
    private final Map<zzwk, List<String>> zzbdq = new HashMap();
    private final Map<zzwk, List<String>> zzbdr = new HashMap();
    private zzwg zzbds;

    public final void zza(zzwk zzwk) {
        this.zzbde.add(zzwk);
    }

    public final void zza(zzwk zzwk, zzwg zzwg) {
        List<zzwg> list = this.zzbdo.get(zzwk);
        if (list == null) {
            list = new ArrayList<>();
            this.zzbdo.put(zzwk, list);
        }
        list.add(zzwg);
    }

    public final void zza(zzwk zzwk, String str) {
        List<String> list = this.zzbdq.get(zzwk);
        if (list == null) {
            list = new ArrayList<>();
            this.zzbdq.put(zzwk, list);
        }
        list.add(str);
    }

    public final void zzb(zzwg zzwg) {
        this.zzbds = zzwg;
    }

    public final void zzb(zzwk zzwk, zzwg zzwg) {
        List<zzwg> list = this.zzbdp.get(zzwk);
        if (list == null) {
            list = new ArrayList<>();
            this.zzbdp.put(zzwk, list);
        }
        list.add(zzwg);
    }

    public final void zzb(zzwk zzwk, String str) {
        List<String> list = this.zzbdr.get(zzwk);
        if (list == null) {
            list = new ArrayList<>();
            this.zzbdr.put(zzwk, list);
        }
        list.add(str);
    }

    public final Set<zzwk> zzov() {
        return this.zzbde;
    }

    public final Map<zzwk, List<zzwg>> zzow() {
        return this.zzbdo;
    }

    public final Map<zzwk, List<String>> zzox() {
        return this.zzbdq;
    }

    public final Map<zzwk, List<String>> zzoy() {
        return this.zzbdr;
    }

    public final Map<zzwk, List<zzwg>> zzoz() {
        return this.zzbdp;
    }

    public final zzwg zzpa() {
        return this.zzbds;
    }
}
