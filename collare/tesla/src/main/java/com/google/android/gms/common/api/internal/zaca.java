package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zaca extends RegisterListenerMethod<A, L> {
    private final /* synthetic */ RegistrationMethods.Builder zakh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zaca(RegistrationMethods.Builder builder, ListenerHolder listenerHolder, Feature[] featureArr, boolean z) {
        super(listenerHolder, featureArr, z);
        this.zakh = builder;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    public final void registerListener(A a2, TaskCompletionSource<Void> taskCompletionSource) {
        this.zakh.zakb.accept(a2, taskCompletionSource);
    }
}
