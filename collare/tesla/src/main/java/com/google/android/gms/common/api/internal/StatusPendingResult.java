package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepForSdk
public class StatusPendingResult extends BasePendingResult<Status> {
    public StatusPendingResult(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* Return type fixed from 'com.google.android.gms.common.api.Result' to match base method */
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ Status createFailedResult(Status status) {
        return status;
    }

    @Deprecated
    public StatusPendingResult(Looper looper) {
        super(looper);
    }
}
