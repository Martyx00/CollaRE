package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzcv extends zzbq {
    private static final String ID = zza.INSTALL_REFERRER.toString();
    private static final String zzaxe = zzb.COMPONENT.toString();
    private final Context zzqx;

    public zzcv(Context context) {
        super(ID, new String[0]);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String zzg = zzcw.zzg(this.zzqx, map.get(zzaxe) != null ? zzgj.zzc(map.get(zzaxe)) : null);
        return zzg != null ? zzgj.zzj(zzg) : zzgj.zzpo();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
