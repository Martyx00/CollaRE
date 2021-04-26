package org.spongycastle.util.encoders;

import java.io.OutputStream;

public interface Encoder {
    int decode(String str, OutputStream outputStream);

    int decode(byte[] bArr, int i, int i2, OutputStream outputStream);

    int encode(byte[] bArr, int i, int i2, OutputStream outputStream);
}
