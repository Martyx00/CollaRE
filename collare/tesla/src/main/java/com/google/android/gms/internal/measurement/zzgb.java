package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

final class zzgb implements ServiceConnection {
    final /* synthetic */ zzfz zzamx;

    private zzgb(zzfz zzfz) {
        this.zzamx = zzfz;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            zzfz.zza(this.zzamx).zzgi().zziy().log("Install Referrer connection returned with null binder");
            return;
        }
        try {
            this.zzamx.zzamv = zzs.zza(iBinder);
            if (this.zzamx.zzamv == null) {
                zzfz.zza(this.zzamx).zzgi().zziy().log("Install Referrer Service implementation was not found");
                return;
            }
            zzfz.zza(this.zzamx).zzgi().zzja().log("Install Referrer Service connected");
            zzfz.zza(this.zzamx).zzgh().zzc(new zzgc(this));
        } catch (Exception e) {
            zzfz.zza(this.zzamx).zzgi().zziy().zzg("Exception occurred while calling Install Referrer API", e);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzfz zzfz = this.zzamx;
        zzfz.zzamv = null;
        zzfz.zza(zzfz).zzgi().zzja().log("Install Referrer Service disconnected");
    }
}
