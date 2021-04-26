package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.io.Streams;

public abstract class ASN1BitString extends ASN1Primitive implements ASN1String {
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected final byte[] data;
    protected final int padBits;

    protected static int getPadBits(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            }
            if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
            } else if (i != 0) {
                i2 = i & 255;
                break;
            }
            i3--;
        }
        if (i2 == 0) {
            return 0;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public abstract void encode(ASN1OutputStream aSN1OutputStream);

    protected static byte[] getBytes(int i) {
        if (i == 0) {
            return new byte[0];
        }
        int i2 = 4;
        int i3 = 3;
        while (i3 >= 1 && ((255 << (i3 * 8)) & i) == 0) {
            i2--;
            i3--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    public ASN1BitString(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("data cannot be null");
        } else if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        } else if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        } else {
            this.data = Arrays.clone(bArr);
            this.padBits = i;
        }
    }

    @Override // org.spongycastle.asn1.ASN1String
    public String getString() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ASN1OutputStream(byteArrayOutputStream).writeObject(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(table[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(table[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new ASN1ParsingException("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    public int intValue() {
        byte[] bArr = this.data;
        int i = this.padBits;
        if (i > 0 && bArr.length <= 4) {
            bArr = derForm(bArr, i);
        }
        int i2 = 0;
        int i3 = 0;
        while (i2 != bArr.length && i2 != 4) {
            i3 |= (bArr[i2] & 255) << (i2 * 8);
            i2++;
        }
        return i3;
    }

    public byte[] getOctets() {
        if (this.padBits == 0) {
            return Arrays.clone(this.data);
        }
        throw new IllegalStateException("attempt to get non-octet aligned data from BIT STRING");
    }

    public byte[] getBytes() {
        return derForm(this.data, this.padBits);
    }

    public int getPadBits() {
        return this.padBits;
    }

    public String toString() {
        return getString();
    }

    @Override // org.spongycastle.asn1.ASN1Primitive, org.spongycastle.asn1.ASN1Object
    public int hashCode() {
        return this.padBits ^ Arrays.hashCode(getBytes());
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (!(aSN1Primitive instanceof ASN1BitString)) {
            return false;
        }
        ASN1BitString aSN1BitString = (ASN1BitString) aSN1Primitive;
        if (this.padBits != aSN1BitString.padBits || !Arrays.areEqual(getBytes(), aSN1BitString.getBytes())) {
            return false;
        }
        return true;
    }

    protected static byte[] derForm(byte[] bArr, int i) {
        byte[] clone = Arrays.clone(bArr);
        if (i > 0) {
            int length = bArr.length - 1;
            clone[length] = (byte) ((255 << i) & clone[length]);
        }
        return clone;
    }

    static ASN1BitString fromInputStream(int i, InputStream inputStream) {
        if (i >= 1) {
            int read = inputStream.read();
            byte[] bArr = new byte[(i - 1)];
            if (bArr.length != 0) {
                if (Streams.readFully(inputStream, bArr) != bArr.length) {
                    throw new EOFException("EOF encountered in middle of BIT STRING");
                } else if (read > 0 && read < 8 && bArr[bArr.length - 1] != ((byte) (bArr[bArr.length - 1] & (255 << read)))) {
                    return new DLBitString(bArr, read);
                }
            }
            return new DERBitString(bArr, read);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    public ASN1Primitive getLoadedObject() {
        return toASN1Primitive();
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERBitString(this.data, this.padBits);
    }

    /* access modifiers changed from: package-private */
    @Override // org.spongycastle.asn1.ASN1Primitive
    public ASN1Primitive toDLObject() {
        return new DLBitString(this.data, this.padBits);
    }
}
