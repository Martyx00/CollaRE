package com.google.firebase.analytics.connector.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.a;
import com.google.firebase.components.d;
import com.google.firebase.components.e;
import java.util.Collections;
import java.util.List;

@Keep
@KeepForSdk
public class AnalyticsConnectorRegistrar implements d {
    @Override // com.google.firebase.components.d
    @Keep
    @SuppressLint({"MissingPermission"})
    @KeepForSdk
    public List<a<?>> getComponents() {
        return Collections.singletonList(a.a(com.google.firebase.analytics.connector.a.class).a(e.a(FirebaseApp.class)).a(e.a(Context.class)).a(a.f3799a).b().c());
    }
}
