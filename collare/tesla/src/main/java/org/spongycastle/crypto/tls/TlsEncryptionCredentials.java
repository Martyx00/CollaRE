package org.spongycastle.crypto.tls;

public interface TlsEncryptionCredentials extends TlsCredentials {
    byte[] decryptPreMasterSecret(byte[] bArr);
}
