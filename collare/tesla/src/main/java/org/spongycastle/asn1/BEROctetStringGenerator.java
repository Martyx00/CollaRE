package org.spongycastle.asn1;

import java.io.OutputStream;

public class BEROctetStringGenerator extends BERGenerator {
    public BEROctetStringGenerator(OutputStream outputStream) {
        super(outputStream);
        writeBERHeader(36);
    }

    public BEROctetStringGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream, i, z);
        writeBERHeader(36);
    }

    public OutputStream getOctetOutputStream() {
        return getOctetOutputStream(new byte[1000]);
    }

    public OutputStream getOctetOutputStream(byte[] bArr) {
        return new BufferedBEROctetStream(bArr);
    }

    /* access modifiers changed from: private */
    public class BufferedBEROctetStream extends OutputStream {
        private byte[] _buf;
        private DEROutputStream _derOut;
        private int _off = 0;

        BufferedBEROctetStream(byte[] bArr) {
            this._buf = bArr;
            this._derOut = new DEROutputStream(BEROctetStringGenerator.this._out);
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            byte[] bArr = this._buf;
            int i2 = this._off;
            this._off = i2 + 1;
            bArr[i2] = (byte) i;
            if (this._off == bArr.length) {
                DEROctetString.encode(this._derOut, bArr);
                this._off = 0;
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            while (i2 > 0) {
                int min = Math.min(i2, this._buf.length - this._off);
                System.arraycopy(bArr, i, this._buf, this._off, min);
                this._off += min;
                int i3 = this._off;
                byte[] bArr2 = this._buf;
                if (i3 >= bArr2.length) {
                    DEROctetString.encode(this._derOut, bArr2);
                    this._off = 0;
                    i += min;
                    i2 -= min;
                } else {
                    return;
                }
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            int i = this._off;
            if (i != 0) {
                byte[] bArr = new byte[i];
                System.arraycopy(this._buf, 0, bArr, 0, i);
                DEROctetString.encode(this._derOut, bArr);
            }
            BEROctetStringGenerator.this.writeBEREnd();
        }
    }
}
