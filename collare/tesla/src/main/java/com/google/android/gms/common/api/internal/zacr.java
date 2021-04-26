package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

/* access modifiers changed from: package-private */
public final class zacr implements IBinder.DeathRecipient, zacs {
    private final WeakReference<BasePendingResult<?>> zalc;
    private final WeakReference<zac> zald;
    private final WeakReference<IBinder> zale;

    private zacr(BasePendingResult<?> basePendingResult, zac zac, IBinder iBinder) {
        this.zald = new WeakReference<>(zac);
        this.zalc = new WeakReference<>(basePendingResult);
        this.zale = new WeakReference<>(iBinder);
    }

    @Override // com.google.android.gms.common.api.internal.zacs
    public final void zac(BasePendingResult<?> basePendingResult) {
        zaby();
    }

    public final void binderDied() {
        zaby();
    }

    private final void zaby() {
        BasePendingResult<?> basePendingResult = this.zalc.get();
        zac zac = this.zald.get();
        if (!(zac == null || basePendingResult == null)) {
            zac.remove(basePendingResult.zam().intValue());
        }
        IBinder iBinder = this.zale.get();
        if (iBinder != null) {
            try {
                iBinder.unlinkToDeath(this, 0);
            } catch (NoSuchElementException unused) {
            }
        }
    }

    /* synthetic */ zacr(BasePendingResult basePendingResult, zac zac, IBinder iBinder, zacq zacq) {
        this(basePendingResult, null, iBinder);
    }
}
