package com.google.android.gms.dynamic;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.LifecycleDelegate;

@KeepForSdk
public interface OnDelegateCreatedListener<T extends LifecycleDelegate> {
    @KeepForSdk
    void onDelegateCreated(T t);
}
