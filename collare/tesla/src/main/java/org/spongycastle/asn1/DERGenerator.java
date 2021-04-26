package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public abstract class DERGenerator extends ASN1Generator {
    private boolean _isExplicit;
    private int _tagNo;
    private boolean _tagged;

    protected DERGenerator(OutputStream outputStream) {
        super(outputStream);
        this._tagged = false;
    }

    public DERGenerator(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this._tagged = false;
        this._tagged = true;
        this._isExplicit = z;
        this._tagNo = i;
    }

    private void writeLength(OutputStream outputStream, int i) {
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
            outputStream.write((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                outputStream.write((byte) (i >> i4));
            }
            return;
        }
        outputStream.write((byte) i);
    }

    /* access modifiers changed from: package-private */
    public void writeDEREncoded(OutputStream outputStream, int i, byte[] bArr) {
        outputStream.write(i);
        writeLength(outputStream, bArr.length);
        outputStream.write(bArr);
    }

    /* access modifiers changed from: package-private */
    public void writeDEREncoded(int i, byte[] bArr) {
        if (this._tagged) {
            int i2 = this._tagNo;
            int i3 = i2 | 128;
            if (this._isExplicit) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                writeDEREncoded(byteArrayOutputStream, i, bArr);
                writeDEREncoded(this._out, i2 | 32 | 128, byteArrayOutputStream.toByteArray());
            } else if ((i & 32) != 0) {
                writeDEREncoded(this._out, i3 | 32, bArr);
            } else {
                writeDEREncoded(this._out, i3, bArr);
            }
        } else {
            writeDEREncoded(this._out, i, bArr);
        }
    }
}
