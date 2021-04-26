package org.spongycastle.util.io.pem;

public interface PemObjectParser {
    Object parseObject(PemObject pemObject);
}
