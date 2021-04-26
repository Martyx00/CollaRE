package org.spongycastle.crypto.generators;

import org.spongycastle.crypto.Digest;

public class KDF2BytesGenerator extends BaseKDFBytesGenerator {
    public KDF2BytesGenerator(Digest digest) {
        super(1, digest);
    }
}
