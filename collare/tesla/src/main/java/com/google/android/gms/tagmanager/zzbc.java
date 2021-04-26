package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzm;
import java.util.Map;

/* access modifiers changed from: package-private */
public final class zzbc extends zzbq {
    private static final String ID = zza.DEVICE_ID.toString();
    private final Context zzqx;

    public zzbc(Context context) {
        super(ID, new String[0]);
        this.zzqx = context;
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final zzm zze(Map<String, zzm> map) {
        String string = Settings.Secure.getString(this.zzqx.getContentResolver(), "android_id");
        return string == null ? zzgj.zzpo() : zzgj.zzj(string);
    }

    @Override // com.google.android.gms.tagmanager.zzbq
    public final boolean zzmj() {
        return true;
    }
}
