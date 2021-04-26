package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zacd;

public abstract class ResultTransform<R extends Result, S extends Result> {
    public Status onFailure(Status status) {
        return status;
    }

    public abstract PendingResult<S> onSuccess(R r);

    public final PendingResult<S> createFailedResult(Status status) {
        return new zacd(status);
    }
}
