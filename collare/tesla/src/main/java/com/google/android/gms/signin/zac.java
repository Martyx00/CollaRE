package com.google.android.gms.signin;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.internal.SignInClientImpl;

final class zac extends Api.AbstractClientBuilder<SignInClientImpl, Object> {
    zac() {
    }

    /* Return type fixed from 'com.google.android.gms.common.api.Api$Client' to match base method */
    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ SignInClientImpl buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        throw new NoSuchMethodError();
    }
}
