package com.google.android.gms.common.api.internal;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/* access modifiers changed from: package-private */
public final class zaba implements ResultCallback<Status> {
    private final /* synthetic */ zaaw zahh;
    private final /* synthetic */ StatusPendingResult zahj;
    private final /* synthetic */ boolean zahk;
    private final /* synthetic */ GoogleApiClient zahl;

    zaba(zaaw zaaw, StatusPendingResult statusPendingResult, boolean z, GoogleApiClient googleApiClient) {
        this.zahh = zaaw;
        this.zahj = statusPendingResult;
        this.zahk = z;
        this.zahl = googleApiClient;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.common.api.Result] */
    @Override // com.google.android.gms.common.api.ResultCallback
    public final /* synthetic */ void onResult(Status status) {
        Status status2 = status;
        Storage.getInstance(this.zahh.mContext).zaf();
        if (status2.isSuccess() && this.zahh.isConnected()) {
            this.zahh.reconnect();
        }
        this.zahj.setResult(status2);
        if (this.zahk) {
            this.zahl.disconnect();
        }
    }
}
