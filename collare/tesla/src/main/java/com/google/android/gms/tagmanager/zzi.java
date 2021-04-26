package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzi extends zzbq {
    private static final String ID = zza.APP_ID.toString();
    private final Context zzqx;

    public zzi(Context context) {
        super(ID, new String[0]);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        return zzgj.zzj(this.zzqx.getPackageName());
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
