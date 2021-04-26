package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public abstract class UnregisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder.ListenerKey<L> zajl;

    @KeepForSdk
    protected UnregisterListenerMethod(ListenerHolder.ListenerKey<L> listenerKey) {
        this.zajl = listenerKey;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void unregisterListener(A a2, TaskCompletionSource<Boolean> taskCompletionSource);

    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zajl;
    }
}
