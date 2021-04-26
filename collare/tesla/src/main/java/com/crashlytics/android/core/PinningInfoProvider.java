package com.crashlytics.android.core;

import java.io.InputStream;

public interface PinningInfoProvider {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    String[] getPins();
}
