package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.GoogleApiClient;

final class zae extends zah {
    zae(zad zad, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [com.google.android.gms.common.api.Api$AnyClient] */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(zai zai) {
        ((zal) zai.getService()).zaa(new zaf(this));
    }
}
