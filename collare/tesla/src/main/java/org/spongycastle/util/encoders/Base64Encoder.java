package org.spongycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

public class Base64Encoder implements Encoder {
    protected final byte[] decodingTable = new byte[128];
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;

    private boolean ignore(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    /* access modifiers changed from: protected */
    public void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i < bArr2.length) {
                this.decodingTable[bArr2[i]] = (byte) i;
                i++;
            } else {
                return;
            }
        }
    }

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3;
        int i4;
        int i5 = i2 % 3;
        int i6 = i2 - i5;
        int i7 = i;
        while (true) {
            i3 = i + i6;
            i4 = 4;
            if (i7 >= i3) {
                break;
            }
            int i8 = bArr[i7] & 255;
            int i9 = bArr[i7 + 1] & 255;
            int i10 = bArr[i7 + 2] & 255;
            outputStream.write(this.encodingTable[(i8 >>> 2) & 63]);
            outputStream.write(this.encodingTable[((i8 << 4) | (i9 >>> 4)) & 63]);
            outputStream.write(this.encodingTable[((i9 << 2) | (i10 >>> 6)) & 63]);
            outputStream.write(this.encodingTable[i10 & 63]);
            i7 += 3;
        }
        switch (i5) {
            case 1:
                int i11 = bArr[i3] & 255;
                outputStream.write(this.encodingTable[(i11 >>> 2) & 63]);
                outputStream.write(this.encodingTable[(i11 << 4) & 63]);
                outputStream.write(this.padding);
                outputStream.write(this.padding);
                break;
            case 2:
                int i12 = bArr[i3] & 255;
                int i13 = bArr[i3 + 1] & 255;
                outputStream.write(this.encodingTable[(i12 >>> 2) & 63]);
                outputStream.write(this.encodingTable[((i12 << 4) | (i13 >>> 4)) & 63]);
                outputStream.write(this.encodingTable[(i13 << 2) & 63]);
                outputStream.write(this.padding);
                break;
        }
        int i14 = (i6 / 3) * 4;
        if (i5 == 0) {
            i4 = 0;
        }
        return i14 + i4;
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3 = i2 + i;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = i3 - 4;
        int nextI = nextI(bArr, i, i4);
        int i5 = 0;
        while (nextI < i4) {
            int i6 = nextI + 1;
            byte b2 = this.decodingTable[bArr[nextI]];
            int nextI2 = nextI(bArr, i6, i4);
            int i7 = nextI2 + 1;
            byte b3 = this.decodingTable[bArr[nextI2]];
            int nextI3 = nextI(bArr, i7, i4);
            int i8 = nextI3 + 1;
            byte b4 = this.decodingTable[bArr[nextI3]];
            int nextI4 = nextI(bArr, i8, i4);
            int i9 = nextI4 + 1;
            byte b5 = this.decodingTable[bArr[nextI4]];
            if ((b2 | b3 | b4 | b5) >= 0) {
                outputStream.write((b2 << 2) | (b3 >> 4));
                outputStream.write((b3 << 4) | (b4 >> 2));
                outputStream.write((b4 << 6) | b5);
                i5 += 3;
                nextI = nextI(bArr, i9, i4);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        return i5 + decodeLastBlock(outputStream, (char) bArr[i4], (char) bArr[i3 - 3], (char) bArr[i3 - 2], (char) bArr[i3 - 1]);
    }

    private int nextI(byte[] bArr, int i, int i2) {
        while (i < i2 && ignore((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // org.spongycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i = length - 4;
        int i2 = 0;
        int nextI = nextI(str, 0, i);
        while (nextI < i) {
            int i3 = nextI + 1;
            byte b2 = this.decodingTable[str.charAt(nextI)];
            int nextI2 = nextI(str, i3, i);
            int i4 = nextI2 + 1;
            byte b3 = this.decodingTable[str.charAt(nextI2)];
            int nextI3 = nextI(str, i4, i);
            int i5 = nextI3 + 1;
            byte b4 = this.decodingTable[str.charAt(nextI3)];
            int nextI4 = nextI(str, i5, i);
            int i6 = nextI4 + 1;
            byte b5 = this.decodingTable[str.charAt(nextI4)];
            if ((b2 | b3 | b4 | b5) >= 0) {
                outputStream.write((b2 << 2) | (b3 >> 4));
                outputStream.write((b3 << 4) | (b4 >> 2));
                outputStream.write((b4 << 6) | b5);
                i2 += 3;
                nextI = nextI(str, i6, i);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        return i2 + decodeLastBlock(outputStream, str.charAt(i), str.charAt(length - 3), str.charAt(length - 2), str.charAt(length - 1));
    }

    private int decodeLastBlock(OutputStream outputStream, char c2, char c3, char c4, char c5) {
        byte b2 = this.padding;
        if (c4 == b2) {
            byte[] bArr = this.decodingTable;
            byte b3 = bArr[c2];
            byte b4 = bArr[c3];
            if ((b3 | b4) >= 0) {
                outputStream.write((b3 << 2) | (b4 >> 4));
                return 1;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c5 == b2) {
            byte[] bArr2 = this.decodingTable;
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            byte b7 = bArr2[c4];
            if ((b5 | b6 | b7) >= 0) {
                outputStream.write((b5 << 2) | (b6 >> 4));
                outputStream.write((b6 << 4) | (b7 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.decodingTable;
            byte b8 = bArr3[c2];
            byte b9 = bArr3[c3];
            byte b10 = bArr3[c4];
            byte b11 = bArr3[c5];
            if ((b8 | b9 | b10 | b11) >= 0) {
                outputStream.write((b8 << 2) | (b9 >> 4));
                outputStream.write((b9 << 4) | (b10 >> 2));
                outputStream.write((b10 << 6) | b11);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    private int nextI(String str, int i, int i2) {
        while (i < i2 && ignore(str.charAt(i))) {
            i++;
        }
        return i;
    }
}
