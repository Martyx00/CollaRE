package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* access modifiers changed from: package-private */
public final class zav implements zabt {
    private final /* synthetic */ zas zaeq;

    private zav(zas zas) {
        this.zaeq = zas;
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zaem = ConnectionResult.RESULT_SUCCESS;
            this.zaeq.zax();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zaem = connectionResult;
            this.zaeq.zax();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i, boolean z) {
        this.zaeq.zaeo.lock();
        try {
            if (this.zaeq.zaen) {
                this.zaeq.zaen = false;
                this.zaeq.zaa((zas) i, (int) z);
                return;
            }
            this.zaeq.zaen = true;
            this.zaeq.zaef.onConnectionSuspended(i);
            this.zaeq.zaeo.unlock();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    /* synthetic */ zav(zas zas, zat zat) {
        this(zas);
    }
}
