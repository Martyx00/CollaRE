package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzt extends zzbq {
    private static final String ID = zza.CONSTANT.toString();
    private static final String VALUE = zzb.VALUE.toString();

    public zzt() {
        super(ID, VALUE);
    }

    public static String zzml() {
        return ID;
    }

    public static String zzmm() {
        return VALUE;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return map.get(VALUE);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
