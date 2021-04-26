package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaat implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private final /* synthetic */ zaak zagj;

    private zaat(zaak zaak) {
        this.zagj = zaak;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        if (this.zagj.zaet.isSignInClientDisconnectFixEnabled()) {
            this.zagj.zaeo.lock();
            try {
                if (this.zagj.zagb != null) {
                    this.zagj.zagb.zaa(new zaar(this.zagj));
                    this.zagj.zaeo.unlock();
                }
            } finally {
                this.zagj.zaeo.unlock();
            }
        } else {
            this.zagj.zagb.zaa(new zaar(this.zagj));
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zagj.zaeo.lock();
        try {
            if (this.zagj.zad((zaak) connectionResult)) {
                this.zagj.zaar();
                this.zagj.zaap();
            } else {
                this.zagj.zae((zaak) connectionResult);
            }
        } finally {
            this.zagj.zaeo.unlock();
        }
    }

    /* synthetic */ zaat(zaak zaak, zaal zaal) {
        this(zaak);
    }
}
