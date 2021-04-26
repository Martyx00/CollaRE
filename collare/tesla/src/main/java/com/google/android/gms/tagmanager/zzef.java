package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public abstract class zzef extends zzbq {
    private static final String zzbaa = zzb.ARG0.toString();
    private static final String zzbbw = zzb.ARG1.toString();

    public zzef(String str) {
        super(str, zzbaa, zzbbw);
    }

    /* access modifiers changed from: protected */
    public abstract boolean zza(zzm zzm, zzm zzm2, Map<String, zzm> map);

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        boolean z;
        Iterator<zzm> it = map.values().iterator();
        while (true) {
            z = false;
            if (it.hasNext()) {
                if (it.next() == zzgj.zzpo()) {
                    break;
                }
            } else {
                zzm zzm = map.get(zzbaa);
                zzm zzm2 = map.get(zzbbw);
                if (zzm != null && zzm2 != null) {
                    z = zza(zzm, zzm2, map);
                }
            }
        }
        return zzgj.zzj(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ String zzns() {
        return super.zzns();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final /* bridge */ /* synthetic */ Set zznt() {
        return super.zznt();
    }
}
