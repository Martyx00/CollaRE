package com.google.android.gms.signin;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.signin.internal.SignInClientImpl;

public final class zaa {
    public static final Api<SignInOptions> API = new Api<>("SignIn.API", zaph, CLIENT_KEY);
    private static final Api.ClientKey<SignInClientImpl> CLIENT_KEY = new Api.ClientKey<>();
    public static final Api.AbstractClientBuilder<SignInClientImpl, SignInOptions> zaph = new zab();
    private static final Scope zar = new Scope(Scopes.PROFILE);
    @ShowFirstParty
    private static final Api.ClientKey<SignInClientImpl> zars = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<SignInClientImpl, Object> zart = new zac();
    private static final Api<Object> zaru = new Api<>("SignIn.INTERNAL_API", zart, zars);
    private static final Scope zas = new Scope(Scopes.EMAIL);
}
