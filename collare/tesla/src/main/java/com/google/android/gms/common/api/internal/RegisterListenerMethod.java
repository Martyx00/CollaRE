package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
    private final ListenerHolder<L> zaju;
    private final Feature[] zajv;
    private final boolean zajw;

    @KeepForSdk
    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder) {
        this.zaju = listenerHolder;
        this.zajv = null;
        this.zajw = false;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void registerListener(A a2, TaskCompletionSource<Void> taskCompletionSource);

    @KeepForSdk
    protected RegisterListenerMethod(ListenerHolder<L> listenerHolder, Feature[] featureArr, boolean z) {
        this.zaju = listenerHolder;
        this.zajv = featureArr;
        this.zajw = z;
    }

    @KeepForSdk
    public ListenerHolder.ListenerKey<L> getListenerKey() {
        return this.zaju.getListenerKey();
    }

    @KeepForSdk
    public void clearListener() {
        this.zaju.clear();
    }

    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return this.zajv;
    }

    public final boolean shouldAutoResolveMissingFeatures() {
        return this.zajw;
    }
}
