package org.spongycastle.jcajce;

import org.spongycastle.crypto.CharToByteConverter;

public class PBKDF1Key implements PBKDFKey {
    private final CharToByteConverter converter;
    private final char[] password;

    public String getAlgorithm() {
        return "PBKDF1";
    }

    public PBKDF1Key(char[] cArr, CharToByteConverter charToByteConverter) {
        this.password = new char[cArr.length];
        this.converter = charToByteConverter;
        System.arraycopy(cArr, 0, this.password, 0, cArr.length);
    }

    public char[] getPassword() {
        return this.password;
    }

    public String getFormat() {
        return this.converter.getType();
    }

    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }
}
