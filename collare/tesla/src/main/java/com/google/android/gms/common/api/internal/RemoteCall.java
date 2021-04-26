package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface RemoteCall<T, U> {
    @KeepForSdk
    void accept(T t, U u);
}
