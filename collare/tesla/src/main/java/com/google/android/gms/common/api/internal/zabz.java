package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zabz implements RemoteCall {
    private final RegistrationMethods.Builder zakg;

    zabz(RegistrationMethods.Builder builder) {
        this.zakg = builder;
    }

    @Override // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object obj, Object obj2) {
        this.zakg.zaa((Api.AnyClient) obj, (TaskCompletionSource) obj2);
    }
}
