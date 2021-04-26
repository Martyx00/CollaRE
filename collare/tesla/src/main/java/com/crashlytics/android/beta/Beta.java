package com.crashlytics.android.beta;

import io.a.a.a.a.b.m;
import io.a.a.a.a.b.r;
import io.a.a.a.c;
import io.a.a.a.i;
import java.util.Collections;
import java.util.Map;

public class Beta extends i<Boolean> implements m {
    public static final String TAG = "Beta";

    @Override // io.a.a.a.i
    public String getIdentifier() {
        return "com.crashlytics.sdk.android:beta";
    }

    @Override // io.a.a.a.i
    public String getVersion() {
        return "1.2.10.27";
    }

    public static Beta getInstance() {
        return (Beta) c.a(Beta.class);
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.i
    public Boolean doInBackground() {
        c.g().a(TAG, "Beta kit initializing...");
        return true;
    }

    @Override // io.a.a.a.a.b.m
    public Map<r.a, String> getDeviceIdentifiers() {
        return Collections.emptyMap();
    }
}
