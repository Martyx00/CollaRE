package com.facebook.h;

import java.io.IOException;
import java.io.InputStream;

/* compiled from: StreamProcessor */
class c {
    public static int a(InputStream inputStream, int i, boolean z) {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int read = inputStream.read();
            if (read != -1) {
                if (z) {
                    i2 = (read & 255) << (i4 * 8);
                } else {
                    i3 <<= 8;
                    i2 = read & 255;
                }
                i3 |= i2;
            } else {
                throw new IOException("no more bytes");
            }
        }
        return i3;
    }
}
