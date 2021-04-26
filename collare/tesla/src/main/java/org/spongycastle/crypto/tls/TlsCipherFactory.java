package org.spongycastle.crypto.tls;

public interface TlsCipherFactory {
    TlsCipher createCipher(TlsContext tlsContext, int i, int i2);
}
