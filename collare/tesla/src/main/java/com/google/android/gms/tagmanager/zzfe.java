package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zzwg;
import com.google.android.gms.internal.measurement.zzwk;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* access modifiers changed from: package-private */
public final class zzfe implements zzfg {
    private final /* synthetic */ Map zzbdi;
    private final /* synthetic */ Map zzbdj;
    private final /* synthetic */ Map zzbdk;
    private final /* synthetic */ Map zzbdl;

    zzfe(zzfb zzfb, Map map, Map map2, Map map3, Map map4) {
        this.zzbdi = map;
        this.zzbdj = map2;
        this.zzbdk = map3;
        this.zzbdl = map4;
    }

    @Override // com.google.android.gms.tagmanager.zzfg
    public final void zza(zzwk zzwk, Set<zzwg> set, Set<zzwg> set2, zzeq zzeq) {
        List list = (List) this.zzbdi.get(zzwk);
        this.zzbdj.get(zzwk);
        if (list != null) {
            set.addAll(list);
            zzeq.zzoa();
        }
        List list2 = (List) this.zzbdk.get(zzwk);
        this.zzbdl.get(zzwk);
        if (list2 != null) {
            set2.addAll(list2);
            zzeq.zzob();
        }
    }
}
