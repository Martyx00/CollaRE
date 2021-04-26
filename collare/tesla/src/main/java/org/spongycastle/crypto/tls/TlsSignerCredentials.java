package org.spongycastle.crypto.tls;

public interface TlsSignerCredentials extends TlsCredentials {
    byte[] generateCertificateSignature(byte[] bArr);

    SignatureAndHashAlgorithm getSignatureAndHashAlgorithm();
}
