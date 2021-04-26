package org.spongycastle.jcajce;

import java.io.IOException;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.X509CertSelector;
import java.util.Collection;
import org.spongycastle.util.Selector;

public class PKIXCertStoreSelector<T extends Certificate> implements Selector<T> {
    private final CertSelector baseSelector;

    public static class Builder {
        private final CertSelector baseSelector;

        public Builder(CertSelector certSelector) {
            this.baseSelector = (CertSelector) certSelector.clone();
        }

        public PKIXCertStoreSelector<? extends Certificate> build() {
            return new PKIXCertStoreSelector<>(this.baseSelector);
        }
    }

    private PKIXCertStoreSelector(CertSelector certSelector) {
        this.baseSelector = certSelector;
    }

    public boolean match(Certificate certificate) {
        return this.baseSelector.match(certificate);
    }

    @Override // org.spongycastle.util.Selector, java.lang.Object
    public Object clone() {
        return new PKIXCertStoreSelector(this.baseSelector);
    }

    public static Collection<? extends Certificate> getCertificates(PKIXCertStoreSelector pKIXCertStoreSelector, CertStore certStore) {
        return certStore.getCertificates(new SelectorClone(pKIXCertStoreSelector));
    }

    private static class SelectorClone extends X509CertSelector {
        private final PKIXCertStoreSelector selector;

        SelectorClone(PKIXCertStoreSelector pKIXCertStoreSelector) {
            this.selector = pKIXCertStoreSelector;
            if (pKIXCertStoreSelector.baseSelector instanceof X509CertSelector) {
                X509CertSelector x509CertSelector = (X509CertSelector) pKIXCertStoreSelector.baseSelector;
                setAuthorityKeyIdentifier(x509CertSelector.getAuthorityKeyIdentifier());
                setBasicConstraints(x509CertSelector.getBasicConstraints());
                setCertificate(x509CertSelector.getCertificate());
                setCertificateValid(x509CertSelector.getCertificateValid());
                setKeyUsage(x509CertSelector.getKeyUsage());
                setMatchAllSubjectAltNames(x509CertSelector.getMatchAllSubjectAltNames());
                setPrivateKeyValid(x509CertSelector.getPrivateKeyValid());
                setSerialNumber(x509CertSelector.getSerialNumber());
                setSubjectKeyIdentifier(x509CertSelector.getSubjectKeyIdentifier());
                setSubjectPublicKey(x509CertSelector.getSubjectPublicKey());
                try {
                    setExtendedKeyUsage(x509CertSelector.getExtendedKeyUsage());
                    setIssuer(x509CertSelector.getIssuerAsBytes());
                    setNameConstraints(x509CertSelector.getNameConstraints());
                    setPathToNames(x509CertSelector.getPathToNames());
                    setPolicy(x509CertSelector.getPolicy());
                    setSubject(x509CertSelector.getSubjectAsBytes());
                    setSubjectAlternativeNames(x509CertSelector.getSubjectAlternativeNames());
                    setSubjectPublicKeyAlgID(x509CertSelector.getSubjectPublicKeyAlgID());
                } catch (IOException e) {
                    throw new IllegalStateException("base selector invalid: " + e.getMessage(), e);
                }
            }
        }

        public boolean match(Certificate certificate) {
            PKIXCertStoreSelector pKIXCertStoreSelector = this.selector;
            if (pKIXCertStoreSelector == null) {
                return certificate != null;
            }
            return pKIXCertStoreSelector.match(certificate);
        }
    }
}
