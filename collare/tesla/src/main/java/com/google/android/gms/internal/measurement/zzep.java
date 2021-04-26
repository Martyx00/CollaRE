package com.google.android.gms.internal.measurement;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public abstract class zzep {
    private static volatile Handler handler;
    private final zzhk zzagz;
    private final Runnable zzyc;
    private volatile long zzyd;

    zzep(zzhk zzhk) {
        Preconditions.checkNotNull(zzhk);
        this.zzagz = zzhk;
        this.zzyc = new zzeq(this, zzhk);
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzep.class) {
            if (handler == null) {
                handler = new Handler(this.zzagz.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    /* access modifiers changed from: package-private */
    public final void cancel() {
        this.zzyd = 0;
        getHandler().removeCallbacks(this.zzyc);
    }

    public abstract void run();

    public final boolean zzef() {
        return this.zzyd != 0;
    }

    public final void zzh(long j) {
        cancel();
        if (j >= 0) {
            this.zzyd = this.zzagz.zzbt().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzyc, j)) {
                this.zzagz.zzgi().zziv().zzg("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
