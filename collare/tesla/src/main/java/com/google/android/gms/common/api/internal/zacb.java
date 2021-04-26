package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zacb extends UnregisterListenerMethod<A, L> {
    private final /* synthetic */ RegistrationMethods.Builder zakh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zacb(RegistrationMethods.Builder builder, ListenerHolder.ListenerKey listenerKey) {
        super(listenerKey);
        this.zakh = builder;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    public final void unregisterListener(A a2, TaskCompletionSource<Boolean> taskCompletionSource) {
        this.zakh.zakc.accept(a2, taskCompletionSource);
    }
}
