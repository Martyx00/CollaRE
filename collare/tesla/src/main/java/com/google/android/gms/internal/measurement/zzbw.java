package com.google.android.gms.internal.measurement;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

/* access modifiers changed from: package-private */
public abstract class zzbw {
    private static volatile Handler handler;
    private final zzat zzvm;
    private final Runnable zzyc = new zzbx(this);
    private volatile long zzyd;

    zzbw(zzat zzat) {
        Preconditions.checkNotNull(zzat);
        this.zzvm = zzat;
    }

    private final Handler getHandler() {
        Handler handler2;
        if (handler != null) {
            return handler;
        }
        synchronized (zzbw.class) {
            if (handler == null) {
                handler = new Handler(this.zzvm.getContext().getMainLooper());
            }
            handler2 = handler;
        }
        return handler2;
    }

    public final void cancel() {
        this.zzyd = 0;
        getHandler().removeCallbacks(this.zzyc);
    }

    public abstract void run();

    public final long zzee() {
        if (this.zzyd == 0) {
            return 0;
        }
        return Math.abs(this.zzvm.zzbt().currentTimeMillis() - this.zzyd);
    }

    public final boolean zzef() {
        return this.zzyd != 0;
    }

    public final void zzh(long j) {
        cancel();
        if (j >= 0) {
            this.zzyd = this.zzvm.zzbt().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzyc, j)) {
                this.zzvm.zzbu().zze("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final void zzi(long j) {
        if (zzef()) {
            if (j < 0) {
                cancel();
                return;
            }
            long abs = j - Math.abs(this.zzvm.zzbt().currentTimeMillis() - this.zzyd);
            if (abs < 0) {
                abs = 0;
            }
            getHandler().removeCallbacks(this.zzyc);
            if (!getHandler().postDelayed(this.zzyc, abs)) {
                this.zzvm.zzbu().zze("Failed to adjust delayed post. time", Long.valueOf(abs));
            }
        }
    }
}
