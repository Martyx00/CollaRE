package org.spongycastle.crypto.io;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import org.spongycastle.crypto.BufferedBlockCipher;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.modes.AEADBlockCipher;

public class CipherOutputStream extends FilterOutputStream {
    private AEADBlockCipher aeadBlockCipher;
    private byte[] buf;
    private BufferedBlockCipher bufferedBlockCipher;
    private final byte[] oneByte;
    private StreamCipher streamCipher;

    public CipherOutputStream(OutputStream outputStream, BufferedBlockCipher bufferedBlockCipher2) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.bufferedBlockCipher = bufferedBlockCipher2;
    }

    public CipherOutputStream(OutputStream outputStream, StreamCipher streamCipher2) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.streamCipher = streamCipher2;
    }

    public CipherOutputStream(OutputStream outputStream, AEADBlockCipher aEADBlockCipher) {
        super(outputStream);
        this.oneByte = new byte[1];
        this.aeadBlockCipher = aEADBlockCipher;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(int i) {
        byte[] bArr = this.oneByte;
        byte b2 = (byte) i;
        bArr[0] = b2;
        if (this.streamCipher != null) {
            this.out.write(this.streamCipher.returnByte(b2));
        } else {
            write(bArr, 0, 1);
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        ensureCapacity(i2, false);
        BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
        if (bufferedBlockCipher2 != null) {
            int processBytes = bufferedBlockCipher2.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes != 0) {
                this.out.write(this.buf, 0, processBytes);
                return;
            }
            return;
        }
        AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
        if (aEADBlockCipher != null) {
            int processBytes2 = aEADBlockCipher.processBytes(bArr, i, i2, this.buf, 0);
            if (processBytes2 != 0) {
                this.out.write(this.buf, 0, processBytes2);
                return;
            }
            return;
        }
        this.streamCipher.processBytes(bArr, i, i2, this.buf, 0);
        this.out.write(this.buf, 0, i2);
    }

    private void ensureCapacity(int i, boolean z) {
        if (z) {
            BufferedBlockCipher bufferedBlockCipher2 = this.bufferedBlockCipher;
            if (bufferedBlockCipher2 != null) {
                i = bufferedBlockCipher2.getOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher = this.aeadBlockCipher;
                if (aEADBlockCipher != null) {
                    i = aEADBlockCipher.getOutputSize(i);
                }
            }
        } else {
            BufferedBlockCipher bufferedBlockCipher3 = this.bufferedBlockCipher;
            if (bufferedBlockCipher3 != null) {
                i = bufferedBlockCipher3.getUpdateOutputSize(i);
            } else {
                AEADBlockCipher aEADBlockCipher2 = this.aeadBlockCipher;
                if (aEADBlockCipher2 != null) {
                    i = aEADBlockCipher2.getUpdateOutputSize(i);
                }
            }
        }
        byte[] bArr = this.buf;
        if (bArr == null || bArr.length < i) {
            this.buf = new byte[i];
        }
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0058, code lost:
        if (r1 != null) goto L_0x005b;
     */
    @Override // java.io.OutputStream, java.io.Closeable, java.io.FilterOutputStream, java.lang.AutoCloseable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() {
        /*
            r4 = this;
            r0 = 0
            r1 = 1
            r4.ensureCapacity(r0, r1)
            org.spongycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            if (r1 == 0) goto L_0x001b
            org.spongycastle.crypto.BufferedBlockCipher r1 = r4.bufferedBlockCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            byte[] r2 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            int r1 = r1.doFinal(r2, r0)     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            if (r1 == 0) goto L_0x003a
            java.io.OutputStream r2 = r4.out     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            byte[] r3 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            r2.write(r3, r0, r1)     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            goto L_0x003a
        L_0x001b:
            org.spongycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            if (r1 == 0) goto L_0x0031
            org.spongycastle.crypto.modes.AEADBlockCipher r1 = r4.aeadBlockCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            byte[] r2 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            int r1 = r1.doFinal(r2, r0)     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            if (r1 == 0) goto L_0x003a
            java.io.OutputStream r2 = r4.out     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            byte[] r3 = r4.buf     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            r2.write(r3, r0, r1)     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            goto L_0x003a
        L_0x0031:
            org.spongycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            if (r0 == 0) goto L_0x003a
            org.spongycastle.crypto.StreamCipher r0 = r4.streamCipher     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
            r0.reset()     // Catch:{ InvalidCipherTextException -> 0x0046, Exception -> 0x003d }
        L_0x003a:
            r0 = 0
            r1 = r0
            goto L_0x004e
        L_0x003d:
            r0 = move-exception
            org.spongycastle.crypto.io.CipherIOException r1 = new org.spongycastle.crypto.io.CipherIOException
            java.lang.String r2 = "Error closing stream: "
            r1.<init>(r2, r0)
            goto L_0x004e
        L_0x0046:
            r0 = move-exception
            org.spongycastle.crypto.io.InvalidCipherTextIOException r1 = new org.spongycastle.crypto.io.InvalidCipherTextIOException
            java.lang.String r2 = "Error finalising cipher data"
            r1.<init>(r2, r0)
        L_0x004e:
            r4.flush()     // Catch:{ IOException -> 0x0057 }
            java.io.OutputStream r0 = r4.out     // Catch:{ IOException -> 0x0057 }
            r0.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            if (r1 != 0) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            r0 = r1
        L_0x005c:
            if (r0 != 0) goto L_0x005f
            return
        L_0x005f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.io.CipherOutputStream.close():void");
    }
}
