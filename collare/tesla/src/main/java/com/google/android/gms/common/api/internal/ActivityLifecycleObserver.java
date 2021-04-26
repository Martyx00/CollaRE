package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class ActivityLifecycleObserver {
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(Runnable runnable);

    @KeepForSdk
    public static final ActivityLifecycleObserver of(Activity activity) {
        return new zaa(activity);
    }
}
