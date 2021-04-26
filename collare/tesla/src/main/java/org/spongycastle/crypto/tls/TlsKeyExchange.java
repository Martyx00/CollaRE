package org.spongycastle.crypto.tls;

import java.io.InputStream;
import java.io.OutputStream;

public interface TlsKeyExchange {
    void generateClientKeyExchange(OutputStream outputStream);

    byte[] generatePremasterSecret();

    byte[] generateServerKeyExchange();

    void init(TlsContext tlsContext);

    void processClientCertificate(Certificate certificate);

    void processClientCredentials(TlsCredentials tlsCredentials);

    void processClientKeyExchange(InputStream inputStream);

    void processServerCertificate(Certificate certificate);

    void processServerCredentials(TlsCredentials tlsCredentials);

    void processServerKeyExchange(InputStream inputStream);

    boolean requiresServerKeyExchange();

    void skipClientCredentials();

    void skipServerCredentials();

    void skipServerKeyExchange();

    void validateCertificateRequest(CertificateRequest certificateRequest);
}
