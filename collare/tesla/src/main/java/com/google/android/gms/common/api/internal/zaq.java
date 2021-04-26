package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

public final class zaq implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final Api<?> mApi;
    private final boolean zaec;
    private zar zaed;

    public zaq(Api<?> api, boolean z) {
        this.mApi = api;
        this.zaec = z;
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnected(Bundle bundle) {
        zav();
        this.zaed.onConnected(bundle);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        zav();
        this.zaed.onConnectionSuspended(i);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zav();
        this.zaed.zaa(connectionResult, this.mApi, this.zaec);
    }

    public final void zaa(zar zar) {
        this.zaed = zar;
    }

    private final void zav() {
        Preconditions.checkNotNull(this.zaed, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }
}
