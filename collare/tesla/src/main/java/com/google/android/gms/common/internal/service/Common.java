package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;

public final class Common {
    @KeepForSdk
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Common.API", zaph, CLIENT_KEY);
    @KeepForSdk
    public static final Api.ClientKey<zai> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zai, Api.ApiOptions.NoOptions> zaph = new zab();
    public static final zac zapi = new zad();
}
