package com.facebook.common.g;

import java.io.Closeable;

/* compiled from: PooledByteBuffer */
public interface g extends Closeable {
    byte a(int i);

    int a();

    int a(int i, byte[] bArr, int i2, int i3);

    boolean b();

    /* compiled from: PooledByteBuffer */
    public static class a extends RuntimeException {
        public a() {
            super("Invalid bytebuf. Already closed");
        }
    }
}
