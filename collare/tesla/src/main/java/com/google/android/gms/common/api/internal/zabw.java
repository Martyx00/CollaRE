package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Api;

public final class zabw {
    public final RegisterListenerMethod<Api.AnyClient, ?> zajx;
    public final UnregisterListenerMethod<Api.AnyClient, ?> zajy;

    public zabw(RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        this.zajx = registerListenerMethod;
        this.zajy = unregisterListenerMethod;
    }
}
