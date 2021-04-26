package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class b implements a {

    /* renamed from: c  reason: collision with root package name */
    private static volatile a f3796c;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final Map<String, Object> f3797a = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    private final AppMeasurement f3798b;

    private b(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.f3798b = appMeasurement;
    }

    @KeepForSdk
    public static a a(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (f3796c == null) {
            synchronized (a.class) {
                if (f3796c == null) {
                    f3796c = new b(AppMeasurement.getInstance(context));
                }
            }
        }
        return f3796c;
    }

    @Override // com.google.firebase.analytics.connector.a
    @KeepForSdk
    public void a(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (com.google.firebase.analytics.connector.internal.b.a(str) && com.google.firebase.analytics.connector.internal.b.a(str2, bundle) && com.google.firebase.analytics.connector.internal.b.a(str, str2, bundle)) {
            this.f3798b.logEventInternal(str, str2, bundle);
        }
    }

    @Override // com.google.firebase.analytics.connector.a
    @KeepForSdk
    public void a(String str, String str2, Object obj) {
        if (com.google.firebase.analytics.connector.internal.b.a(str) && com.google.firebase.analytics.connector.internal.b.a(str, str2)) {
            this.f3798b.setUserPropertyInternal(str, str2, obj);
        }
    }
}
