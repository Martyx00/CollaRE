package com.facebook.common.d;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Closeables */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    static final Logger f1737a = Logger.getLogger(b.class.getName());

    private b() {
    }

    public static void a(Closeable closeable, boolean z) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (z) {
                    f1737a.log(Level.WARNING, "IOException thrown while closing Closeable.", (Throwable) e);
                    return;
                }
                throw e;
            }
        }
    }

    public static void a(InputStream inputStream) {
        try {
            a(inputStream, true);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
