package org.spongycastle.crypto.tls;

public interface TlsSRPIdentityManager {
    TlsSRPLoginParameters getLoginParameters(byte[] bArr);
}
