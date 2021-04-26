package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import com.google.android.gms.internal.measurement.zzm;
import java.util.HashMap;
import java.util.Map;

@VisibleForTesting
final class zzam extends zzbq {
    private static final String ID = zza.FUNCTION_CALL.toString();
    private static final String zzaxg = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzayu = zzb.FUNCTION_CALL_NAME.toString();
    private final zzan zzayv;

    public zzam(zzan zzan) {
        super(ID, zzayu);
        this.zzayv = zzan;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String sb;
        String zzc = zzgj.zzc(map.get(zzayu));
        HashMap hashMap = new HashMap();
        zzm zzm = map.get(zzaxg);
        if (zzm != null) {
            Object zzh = zzgj.zzh(zzm);
            if (!(zzh instanceof Map)) {
                sb = "FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.";
                zzdi.zzab(sb);
                return zzgj.zzpo();
            }
            for (Map.Entry entry : ((Map) zzh).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzgj.zzj(this.zzayv.zza(zzc, hashMap));
        } catch (Exception e) {
            String message = e.getMessage();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzc).length() + 34 + String.valueOf(message).length());
            sb2.append("Custom macro/tag ");
            sb2.append(zzc);
            sb2.append(" threw exception ");
            sb2.append(message);
            sb = sb2.toString();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return false;
    }
}
