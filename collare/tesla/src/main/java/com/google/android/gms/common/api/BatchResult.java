package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status mStatus;
    private final PendingResult<?>[] zabc;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.mStatus = status;
        this.zabc = pendingResultArr;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.mStatus;
    }

    public final <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        Preconditions.checkArgument(batchResultToken.mId < this.zabc.length, "The result token does not belong to this batch");
        return (R) this.zabc[batchResultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
