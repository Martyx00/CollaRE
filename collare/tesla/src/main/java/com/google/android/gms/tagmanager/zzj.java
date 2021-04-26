package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzj extends zzbq {
    private static final String ID = zza.APP_NAME.toString();
    private final Context zzqx;

    public zzj(Context context) {
        super(ID, new String[0]);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        try {
            PackageManager packageManager = this.zzqx.getPackageManager();
            return zzgj.zzj(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.zzqx.getPackageName(), 0)).toString());
        } catch (PackageManager.NameNotFoundException e) {
            zzdi.zza("App name is not found.", e);
            return zzgj.zzpo();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
