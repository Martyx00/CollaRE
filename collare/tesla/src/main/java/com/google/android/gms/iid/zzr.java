package com.google.android.gms.iid;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzr {
    private final ScheduledExecutorService zzbz;
    private zzt zzca;
    private int zzcb;
    private final Context zzk;

    public zzr(Context context) {
        this(context, Executors.newSingleThreadScheduledExecutor());
    }

    private zzr(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.zzca = new zzt(this);
        this.zzcb = 1;
        this.zzk = context.getApplicationContext();
        this.zzbz = scheduledExecutorService;
    }

    private final synchronized <T> Task<T> zzd(zzz<T> zzz) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(zzz);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 9);
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.zzca.zze(zzz)) {
            this.zzca = new zzt(this);
            this.zzca.zze(zzz);
        }
        return zzz.zzcl.getTask();
    }

    private final synchronized int zzq() {
        int i;
        i = this.zzcb;
        this.zzcb = i + 1;
        return i;
    }

    public final Task<Bundle> zzd(int i, Bundle bundle) {
        return zzd(new zzab(zzq(), 1, bundle));
    }
}
