package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzl extends zzbq {
    private static final String ID = zza.APP_VERSION_NAME.toString();
    private final Context zzqx;

    public zzl(Context context) {
        super(ID, new String[0]);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        try {
            return zzgj.zzj(this.zzqx.getPackageManager().getPackageInfo(this.zzqx.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            String packageName = this.zzqx.getPackageName();
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder(String.valueOf(packageName).length() + 25 + String.valueOf(message).length());
            sb.append("Package name ");
            sb.append(packageName);
            sb.append(" not found. ");
            sb.append(message);
            zzdi.e(sb.toString());
            return zzgj.zzpo();
        }
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
