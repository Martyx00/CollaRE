package org.spongycastle.jcajce.io;

import java.io.FilterInputStream;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import org.spongycastle.crypto.io.InvalidCipherTextIOException;

public class CipherInputStream extends FilterInputStream {
    private byte[] buf;
    private int bufOff;
    private final Cipher cipher;
    private boolean finalized = false;
    private final byte[] inputBuffer = new byte[512];
    private int maxBuf;

    public void mark(int i) {
    }

    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() {
    }

    public CipherInputStream(InputStream inputStream, Cipher cipher2) {
        super(inputStream);
        this.cipher = cipher2;
    }

    private int nextChunk() {
        if (this.finalized) {
            return -1;
        }
        this.bufOff = 0;
        this.maxBuf = 0;
        while (true) {
            int i = this.maxBuf;
            if (i != 0) {
                return i;
            }
            int read = this.in.read(this.inputBuffer);
            if (read == -1) {
                this.buf = finaliseCipher();
                byte[] bArr = this.buf;
                if (bArr == null || bArr.length == 0) {
                    return -1;
                }
                this.maxBuf = bArr.length;
                return this.maxBuf;
            }
            this.buf = this.cipher.update(this.inputBuffer, 0, read);
            byte[] bArr2 = this.buf;
            if (bArr2 != null) {
                this.maxBuf = bArr2.length;
            }
        }
    }

    private byte[] finaliseCipher() {
        try {
            this.finalized = true;
            return this.cipher.doFinal();
        } catch (GeneralSecurityException e) {
            throw new InvalidCipherTextIOException("Error finalising cipher", e);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() {
        if (this.bufOff >= this.maxBuf && nextChunk() < 0) {
            return -1;
        }
        byte[] bArr = this.buf;
        int i = this.bufOff;
        this.bufOff = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (this.bufOff >= this.maxBuf && nextChunk() < 0) {
            return -1;
        }
        int min = Math.min(i2, available());
        System.arraycopy(this.buf, this.bufOff, bArr, i, min);
        this.bufOff += min;
        return min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) {
        if (j <= 0) {
            return 0;
        }
        int min = (int) Math.min(j, (long) available());
        this.bufOff += min;
        return (long) min;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() {
        return this.maxBuf - this.bufOff;
    }

    @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        try {
            this.in.close();
            this.bufOff = 0;
            this.maxBuf = 0;
        } finally {
            if (!this.finalized) {
                finaliseCipher();
            }
        }
    }
}
