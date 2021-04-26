package org.spongycastle.crypto.tls;

public class ByteQueue {
    private static final int DEFAULT_CAPACITY = 1024;
    private int available;
    private byte[] databuf;
    private int skipped;

    public static int nextTwoPow(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        return (i5 | (i5 >> 16)) + 1;
    }

    public ByteQueue() {
        this(1024);
    }

    public ByteQueue(int i) {
        this.skipped = 0;
        this.available = 0;
        this.databuf = new byte[i];
    }

    public void read(byte[] bArr, int i, int i2, int i3) {
        if (bArr.length - i < i2) {
            throw new IllegalArgumentException("Buffer size of " + bArr.length + " is too small for a read of " + i2 + " bytes");
        } else if (this.available - i3 >= i2) {
            System.arraycopy(this.databuf, this.skipped + i3, bArr, i, i2);
        } else {
            throw new IllegalStateException("Not enough data to read");
        }
    }

    public void addData(byte[] bArr, int i, int i2) {
        int i3 = this.skipped;
        int i4 = this.available;
        if (i3 + i4 + i2 > this.databuf.length) {
            int nextTwoPow = nextTwoPow(i4 + i2);
            byte[] bArr2 = this.databuf;
            if (nextTwoPow > bArr2.length) {
                byte[] bArr3 = new byte[nextTwoPow];
                System.arraycopy(bArr2, this.skipped, bArr3, 0, this.available);
                this.databuf = bArr3;
            } else {
                System.arraycopy(bArr2, this.skipped, bArr2, 0, this.available);
            }
            this.skipped = 0;
        }
        System.arraycopy(bArr, i, this.databuf, this.skipped + this.available, i2);
        this.available += i2;
    }

    public void removeData(int i) {
        int i2 = this.available;
        if (i <= i2) {
            this.available = i2 - i;
            this.skipped += i;
            return;
        }
        throw new IllegalStateException("Cannot remove " + i + " bytes, only got " + this.available);
    }

    public void removeData(byte[] bArr, int i, int i2, int i3) {
        read(bArr, i, i2, i3);
        removeData(i3 + i2);
    }

    public byte[] removeData(int i, int i2) {
        byte[] bArr = new byte[i];
        removeData(bArr, 0, i, i2);
        return bArr;
    }

    public int size() {
        return this.available;
    }

    public int available() {
        return this.available;
    }
}
