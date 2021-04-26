package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzh extends zzbq {
    private static final String ID = zza.ADWORDS_CLICK_REFERRER.toString();
    private static final String zzaxe = zzb.COMPONENT.toString();
    private static final String zzaxf = zzb.CONVERSION_ID.toString();
    private final Context zzqx;

    public zzh(Context context) {
        super(ID, zzaxf);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        zzm zzm = map.get(zzaxf);
        if (zzm == null) {
            return zzgj.zzpo();
        }
        String zzc = zzgj.zzc(zzm);
        zzm zzm2 = map.get(zzaxe);
        String zzc2 = zzm2 != null ? zzgj.zzc(zzm2) : null;
        Context context = this.zzqx;
        String str = zzcw.zzbas.get(zzc);
        if (str == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str = sharedPreferences != null ? sharedPreferences.getString(zzc, "") : "";
            zzcw.zzbas.put(zzc, str);
        }
        String zzt = zzcw.zzt(str, zzc2);
        return zzt != null ? zzgj.zzj(zzt) : zzgj.zzpo();
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
