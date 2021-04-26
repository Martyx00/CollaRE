package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

public final class BatchResultToken<R extends Result> {
    protected final int mId;

    BatchResultToken(int i) {
        this.mId = i;
    }
}
