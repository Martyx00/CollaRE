package org.spongycastle.jcajce;

import org.spongycastle.crypto.CharToByteConverter;
import org.spongycastle.util.Arrays;

public class PBKDF2Key implements PBKDFKey {
    private final CharToByteConverter converter;
    private final char[] password;

    public String getAlgorithm() {
        return "PBKDF2";
    }

    public PBKDF2Key(char[] cArr, CharToByteConverter charToByteConverter) {
        this.password = Arrays.clone(cArr);
        this.converter = charToByteConverter;
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
