package io.a.a.a.a.e;

import java.io.InputStream;

/* compiled from: PinningInfoProvider */
public interface g {
    String getKeyStorePassword();

    InputStream getKeyStoreStream();

    long getPinCreationTimeInMillis();

    String[] getPins();
}
