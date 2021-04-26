package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.ref.WeakReference;

final class zaam implements BaseGmsClient.ConnectionProgressReportCallbacks {
    private final Api<?> mApi;
    private final boolean zaec;
    private final WeakReference<zaak> zagk;

    public zaam(zaak zaak, Api<?> api, boolean z) {
        this.zagk = new WeakReference<>(zaak);
        this.mApi = api;
        this.zaec = z;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
    public final void onReportServiceBinding(ConnectionResult connectionResult) {
        zaak zaak = this.zagk.get();
        if (zaak != null) {
            Preconditions.checkState(Looper.myLooper() == zaak.zad(zaak).zaee.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zaak.zac(zaak).lock();
            try {
                if (zaak.zaa(zaak, 0)) {
                    if (!connectionResult.isSuccess()) {
                        zaak.zaa(zaak, connectionResult, this.mApi, this.zaec);
                    }
                    if (zaak.zal(zaak)) {
                        zaak.zak(zaak);
                    }
                    zaak.zac(zaak).unlock();
                }
            } finally {
                zaak.zac(zaak).unlock();
            }
        }
    }
}
