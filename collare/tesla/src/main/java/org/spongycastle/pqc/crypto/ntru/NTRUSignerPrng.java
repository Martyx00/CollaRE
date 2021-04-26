package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.Digest;

public class NTRUSignerPrng {
    private int counter = 0;
    private Digest hashAlg;
    private byte[] seed;

    NTRUSignerPrng(byte[] bArr, Digest digest) {
        this.seed = bArr;
        this.hashAlg = digest;
    }

    /* access modifiers changed from: package-private */
    public byte[] nextBytes(int i) {
        ByteBuffer allocate = ByteBuffer.allocate(i);
        while (allocate.hasRemaining()) {
            ByteBuffer allocate2 = ByteBuffer.allocate(this.seed.length + 4);
            allocate2.put(this.seed);
            allocate2.putInt(this.counter);
            byte[] array = allocate2.array();
            byte[] bArr = new byte[this.hashAlg.getDigestSize()];
            this.hashAlg.update(array, 0, array.length);
            this.hashAlg.doFinal(bArr, 0);
            if (allocate.remaining() < bArr.length) {
                allocate.put(bArr, 0, allocate.remaining());
            } else {
                allocate.put(bArr);
            }
            this.counter++;
        }
        return allocate.array();
    }
}
