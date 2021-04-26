package org.spongycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.eac.CertificateBody;

public class ASN1OutputStream {
    private OutputStream os;

    public ASN1OutputStream(OutputStream outputStream) {
        this.os = outputStream;
    }

    /* access modifiers changed from: package-private */
    public void writeLength(int i) {
        if (i > 127) {
            int i2 = i;
            int i3 = 1;
            while (true) {
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3++;
            }
            write((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                write((byte) (i >> i4));
            }
            return;
        }
        write((byte) i);
    }

    /* access modifiers changed from: package-private */
    public void write(int i) {
        this.os.write(i);
    }

    /* access modifiers changed from: package-private */
    public void write(byte[] bArr) {
        this.os.write(bArr);
    }

    /* access modifiers changed from: package-private */
    public void write(byte[] bArr, int i, int i2) {
        this.os.write(bArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public void writeEncoded(int i, byte[] bArr) {
        write(i);
        writeLength(bArr.length);
        write(bArr);
    }

    /* access modifiers changed from: package-private */
    public void writeTag(int i, int i2) {
        if (i2 < 31) {
            write(i | i2);
            return;
        }
        write(i | 31);
        if (i2 < 128) {
            write(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int length = bArr.length - 1;
        bArr[length] = (byte) (i2 & CertificateBody.profileType);
        do {
            i2 >>= 7;
            length--;
            bArr[length] = (byte) ((i2 & CertificateBody.profileType) | 128);
        } while (i2 > 127);
        write(bArr, length, bArr.length - length);
    }

    /* access modifiers changed from: package-private */
    public void writeEncoded(int i, int i2, byte[] bArr) {
        writeTag(i, i2);
        writeLength(bArr.length);
        write(bArr);
    }

    /* access modifiers changed from: protected */
    public void writeNull() {
        this.os.write(5);
        this.os.write(0);
    }

    public void writeObject(ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1Encodable.toASN1Primitive().encode(this);
            return;
        }
        throw new IOException("null object detected");
    }

    /* access modifiers changed from: package-private */
    public void writeImplicitObject(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive != null) {
            aSN1Primitive.encode(new ImplicitOutputStream(this.os));
            return;
        }
        throw new IOException("null object detected");
    }

    public void close() {
        this.os.close();
    }

    public void flush() {
        this.os.flush();
    }

    /* access modifiers changed from: package-private */
    public ASN1OutputStream getDERSubStream() {
        return new DEROutputStream(this.os);
    }

    /* access modifiers changed from: package-private */
    public ASN1OutputStream getDLSubStream() {
        return new DLOutputStream(this.os);
    }

    private class ImplicitOutputStream extends ASN1OutputStream {
        private boolean first = true;

        public ImplicitOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        @Override // org.spongycastle.asn1.ASN1OutputStream
        public void write(int i) {
            if (this.first) {
                this.first = false;
            } else {
                ASN1OutputStream.super.write(i);
            }
        }
    }
}
