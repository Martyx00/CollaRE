package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public class SimpleClientAdapter<T extends IInterface> extends GmsClient<T> {
    private final Api.SimpleClient<T> zapg;

    public SimpleClientAdapter(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, ClientSettings clientSettings, Api.SimpleClient<T> simpleClient) {
        super(context, looper, i, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zapg = simpleClient;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getStartServiceAction() {
        return this.zapg.getStartServiceAction();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public String getServiceDescriptor() {
        return this.zapg.getServiceDescriptor();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public T createServiceInterface(IBinder iBinder) {
        return this.zapg.createServiceInterface(iBinder);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public void onSetConnectState(int i, T t) {
        this.zapg.setState(i, t);
    }

    public Api.SimpleClient<T> getClient() {
        return this.zapg;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client, com.google.android.gms.common.internal.GmsClient
    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
}
