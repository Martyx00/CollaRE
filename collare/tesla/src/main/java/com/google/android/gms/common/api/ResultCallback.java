package com.google.android.gms.common.api;

import com.google.android.gms.common.api.Result;

public interface ResultCallback<R extends Result> {
    void onResult(R r);
}
