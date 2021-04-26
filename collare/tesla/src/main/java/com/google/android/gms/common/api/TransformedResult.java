package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

public abstract class TransformedResult<R extends Result> {
    public abstract void andFinally(ResultCallbacks<? super R> resultCallbacks);

    public abstract <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform);
}
