package com.facebook.common.g;

import com.facebook.common.d.i;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PooledByteStreams */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private final int f1762a;

    /* renamed from: b  reason: collision with root package name */
    private final a f1763b;

    public k(a aVar) {
        this(aVar, 16384);
    }

    public k(a aVar, int i) {
        i.a(i > 0);
        this.f1762a = i;
        this.f1763b = aVar;
    }

    public long a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = (byte[]) this.f1763b.a(this.f1762a);
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, this.f1762a);
                if (read == -1) {
                    return j;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
            } finally {
                this.f1763b.a(bArr);
            }
        }
    }
}
