package com.facebook.common.g;

import com.facebook.common.d.n;
import java.io.IOException;
import java.io.OutputStream;

/* compiled from: PooledByteBufferOutputStream */
public abstract class j extends OutputStream {
    public abstract g a();

    public abstract int b();

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            super.close();
        } catch (IOException e) {
            n.b(e);
        }
    }
}
