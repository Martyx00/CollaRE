package org.spongycastle.jcajce;

import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertStore;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Selector;

public class PKIXCRLStoreSelector<T extends CRL> implements Selector<T> {
    private final CRLSelector baseSelector;
    private final boolean completeCRLEnabled;
    private final boolean deltaCRLIndicator;
    private final byte[] issuingDistributionPoint;
    private final boolean issuingDistributionPointEnabled;
    private final BigInteger maxBaseCRLNumber;

    @Override // org.spongycastle.util.Selector, java.lang.Object
    public Object clone() {
        return this;
    }

    public static class Builder {
        private final CRLSelector baseSelector;
        private boolean completeCRLEnabled = false;
        private boolean deltaCRLIndicator = false;
        private byte[] issuingDistributionPoint = null;
        private boolean issuingDistributionPointEnabled = false;
        private BigInteger maxBaseCRLNumber = null;

        public Builder(CRLSelector cRLSelector) {
            this.baseSelector = (CRLSelector) cRLSelector.clone();
        }

        public Builder setCompleteCRLEnabled(boolean z) {
            this.completeCRLEnabled = z;
            return this;
        }

        public Builder setDeltaCRLIndicatorEnabled(boolean z) {
            this.deltaCRLIndicator = z;
            return this;
        }

        public void setMaxBaseCRLNumber(BigInteger bigInteger) {
            this.maxBaseCRLNumber = bigInteger;
        }

        public void setIssuingDistributionPointEnabled(boolean z) {
            this.issuingDistributionPointEnabled = z;
        }

        public void setIssuingDistributionPoint(byte[] bArr) {
            this.issuingDistributionPoint = Arrays.clone(bArr);
        }

        public PKIXCRLStoreSelector<? extends CRL> build() {
            return new PKIXCRLStoreSelector<>(this);
        }
    }

    private PKIXCRLStoreSelector(Builder builder) {
        this.baseSelector = builder.baseSelector;
        this.deltaCRLIndicator = builder.deltaCRLIndicator;
        this.completeCRLEnabled = builder.completeCRLEnabled;
        this.maxBaseCRLNumber = builder.maxBaseCRLNumber;
        this.issuingDistributionPoint = builder.issuingDistributionPoint;
        this.issuingDistributionPointEnabled = builder.issuingDistributionPointEnabled;
    }

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    public boolean match(CRL crl) {
        if (!(crl instanceof X509CRL)) {
            return this.baseSelector.match(crl);
        }
        X509CRL x509crl = (X509CRL) crl;
        ASN1Integer aSN1Integer = null;
        try {
            byte[] extensionValue = x509crl.getExtensionValue(Extension.deltaCRLIndicator.getId());
            if (extensionValue != null) {
                aSN1Integer = ASN1Integer.getInstance(ASN1OctetString.getInstance(extensionValue).getOctets());
            }
            if (isDeltaCRLIndicatorEnabled() && aSN1Integer == null) {
                return false;
            }
            if (isCompleteCRLEnabled() && aSN1Integer != null) {
                return false;
            }
            if (aSN1Integer != null && this.maxBaseCRLNumber != null && aSN1Integer.getPositiveValue().compareTo(this.maxBaseCRLNumber) == 1) {
                return false;
            }
            if (this.issuingDistributionPointEnabled) {
                byte[] extensionValue2 = x509crl.getExtensionValue(Extension.issuingDistributionPoint.getId());
                byte[] bArr = this.issuingDistributionPoint;
                if (bArr == null) {
                    if (extensionValue2 != null) {
                        return false;
                    }
                } else if (!Arrays.areEqual(extensionValue2, bArr)) {
                    return false;
                }
            }
            return this.baseSelector.match(crl);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public X509Certificate getCertificateChecking() {
        CRLSelector cRLSelector = this.baseSelector;
        if (cRLSelector instanceof X509CRLSelector) {
            return ((X509CRLSelector) cRLSelector).getCertificateChecking();
        }
        return null;
    }

    public static Collection<? extends CRL> getCRLs(PKIXCRLStoreSelector pKIXCRLStoreSelector, CertStore certStore) {
        return certStore.getCRLs(new SelectorClone(pKIXCRLStoreSelector));
    }

    private static class SelectorClone extends X509CRLSelector {
        private final PKIXCRLStoreSelector selector;

        SelectorClone(PKIXCRLStoreSelector pKIXCRLStoreSelector) {
            this.selector = pKIXCRLStoreSelector;
            if (pKIXCRLStoreSelector.baseSelector instanceof X509CRLSelector) {
                X509CRLSelector x509CRLSelector = (X509CRLSelector) pKIXCRLStoreSelector.baseSelector;
                setCertificateChecking(x509CRLSelector.getCertificateChecking());
                setDateAndTime(x509CRLSelector.getDateAndTime());
                setIssuers(x509CRLSelector.getIssuers());
                setMinCRLNumber(x509CRLSelector.getMinCRL());
                setMaxCRLNumber(x509CRLSelector.getMaxCRL());
            }
        }

        public boolean match(CRL crl) {
            PKIXCRLStoreSelector pKIXCRLStoreSelector = this.selector;
            if (pKIXCRLStoreSelector == null) {
                return crl != null;
            }
            return pKIXCRLStoreSelector.match(crl);
        }
    }
}
