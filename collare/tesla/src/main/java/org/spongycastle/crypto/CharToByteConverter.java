package org.spongycastle.crypto;

public interface CharToByteConverter {
    byte[] convert(char[] cArr);

    String getType();
}
