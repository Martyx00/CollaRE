package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

final class zzb implements DynamiteModule.VersionPolicy {
    zzb() {
    }

    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zzb zza(Context context, String str, DynamiteModule.VersionPolicy.zza zza) {
        DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
        zzb.zzis = zza.zza(context, str, true);
        if (zzb.zzis != 0) {
            zzb.zzit = 1;
        } else {
            zzb.zzir = zza.getLocalVersion(context, str);
            if (zzb.zzir != 0) {
                zzb.zzit = -1;
            }
        }
        return zzb;
    }
}
