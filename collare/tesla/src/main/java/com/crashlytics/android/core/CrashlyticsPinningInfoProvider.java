package com.crashlytics.android.core;

import io.a.a.a.a.e.g;
import java.io.InputStream;

/* access modifiers changed from: package-private */
public class CrashlyticsPinningInfoProvider implements g {
    private final PinningInfoProvider pinningInfo;

    @Override // io.a.a.a.a.e.g
    public long getPinCreationTimeInMillis() {
        return -1;
    }

    public CrashlyticsPinningInfoProvider(PinningInfoProvider pinningInfoProvider) {
        this.pinningInfo = pinningInfoProvider;
    }

    @Override // io.a.a.a.a.e.g
    public InputStream getKeyStoreStream() {
        return this.pinningInfo.getKeyStoreStream();
    }

    @Override // io.a.a.a.a.e.g
    public String getKeyStorePassword() {
        return this.pinningInfo.getKeyStorePassword();
    }

    @Override // io.a.a.a.a.e.g
    public String[] getPins() {
        return this.pinningInfo.getPins();
    }
}
