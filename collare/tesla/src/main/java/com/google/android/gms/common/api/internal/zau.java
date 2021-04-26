package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* access modifiers changed from: package-private */
public final class zau implements zabt {
    private final /* synthetic */ zas zaeq;

    private zau(zas zas) {
        this.zaeq = zas;
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(Bundle bundle) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zaa((zas) bundle);
            this.zaeq.zael = ConnectionResult.RESULT_SUCCESS;
            this.zaeq.zax();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zac(ConnectionResult connectionResult) {
        this.zaeq.zaeo.lock();
        try {
            this.zaeq.zael = connectionResult;
            this.zaeq.zax();
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    @Override // com.google.android.gms.common.api.internal.zabt
    public final void zab(int i, boolean z) {
        this.zaeq.zaeo.lock();
        try {
            if (!(this.zaeq.zaen) && this.zaeq.zaem != null) {
                if (this.zaeq.zaem.isSuccess()) {
                    this.zaeq.zaen = true;
                    this.zaeq.zaeg.onConnectionSuspended(i);
                    this.zaeq.zaeo.unlock();
                    return;
                }
            }
            this.zaeq.zaen = false;
            this.zaeq.zaa((zas) i, (int) z);
        } finally {
            this.zaeq.zaeo.unlock();
        }
    }

    /* synthetic */ zau(zas zas, zat zat) {
        this(zas);
    }
}
