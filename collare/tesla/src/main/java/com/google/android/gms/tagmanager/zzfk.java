package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzfk extends zzbq {
    private static final String ID = zza.SDK_VERSION.toString();

    public zzfk() {
        super(ID, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return zzgj.zzj(Integer.valueOf(Build.VERSION.SDK_INT));
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
