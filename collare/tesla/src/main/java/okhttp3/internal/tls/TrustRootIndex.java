package okhttp3.internal.tls;

import java.security.cert.X509Certificate;

public interface TrustRootIndex {
    X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate);
}
