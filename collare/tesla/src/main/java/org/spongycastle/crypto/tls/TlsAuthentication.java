package org.spongycastle.crypto.tls;

public interface TlsAuthentication {
    TlsCredentials getClientCredentials(CertificateRequest certificateRequest);

    void notifyServerCertificate(Certificate certificate);
}
