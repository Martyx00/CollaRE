package okhttp3.internal.tls;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import okhttp3.internal.platform.Platform;

public abstract class CertificateChainCleaner {
    public abstract List<Certificate> clean(List<Certificate> list, String str);

    public static CertificateChainCleaner get(X509TrustManager x509TrustManager) {
        return Platform.get().buildCertificateChainCleaner(x509TrustManager);
    }

    public static CertificateChainCleaner get(X509Certificate... x509CertificateArr) {
        return new BasicCertificateChainCleaner(new BasicTrustRootIndex(x509CertificateArr));
    }
}
