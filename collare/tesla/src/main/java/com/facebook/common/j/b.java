package com.facebook.common.j;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: TailAppendingInputStream */
public class b extends FilterInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f1784a;

    /* renamed from: b  reason: collision with root package name */
    private int f1785b;

    /* renamed from: c  reason: collision with root package name */
    private int f1786c;

    public b(InputStream inputStream, byte[] bArr) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (bArr != null) {
            this.f1784a = bArr;
        } else {
            throw new NullPointerException();
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        int read = this.in.read();
        if (read != -1) {
            return read;
        }
        return a();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            return read;
        }
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        while (i3 < i2) {
            int a2 = a();
            if (a2 == -1) {
                break;
            }
            bArr[i + i3] = (byte) a2;
            i3++;
        }
        if (i3 > 0) {
            return i3;
        }
        return -1;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
        if (this.in.markSupported()) {
            this.in.reset();
            this.f1785b = this.f1786c;
            return;
        }
        throw new IOException("mark is not supported");
    }

    public void mark(int i) {
        if (this.in.markSupported()) {
            super.mark(i);
            this.f1786c = this.f1785b;
        }
    }

    private int a() {
        int i = this.f1785b;
        byte[] bArr = this.f1784a;
        if (i >= bArr.length) {
            return -1;
        }
        this.f1785b = i + 1;
        return bArr[i] & 255;
    }
}
