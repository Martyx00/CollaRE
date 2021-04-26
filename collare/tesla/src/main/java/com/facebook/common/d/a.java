package com.facebook.common.d;

import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: ByteStreams */
public final class a {
    public static long a(InputStream inputStream, OutputStream outputStream) {
        i.a(inputStream);
        i.a(outputStream);
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return j;
            }
            outputStream.write(bArr, 0, read);
            j += (long) read;
        }
    }

    public static int a(InputStream inputStream, byte[] bArr, int i, int i2) {
        i.a(inputStream);
        i.a(bArr);
        if (i2 >= 0) {
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i + i3, i2 - i3);
                if (read == -1) {
                    break;
                }
                i3 += read;
            }
            return i3;
        }
        throw new IndexOutOfBoundsException("len is negative");
    }
}
