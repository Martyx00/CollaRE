package org.spongycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRL;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.extension.X509ExtensionUtil;

public class X509CRLStoreSelector extends X509CRLSelector implements Selector {
    private X509AttributeCertificate attrCertChecking;
    private boolean completeCRLEnabled = false;
    private boolean deltaCRLIndicator = false;
    private byte[] issuingDistributionPoint = null;
    private boolean issuingDistributionPointEnabled = false;
    private BigInteger maxBaseCRLNumber = null;

    public boolean isIssuingDistributionPointEnabled() {
        return this.issuingDistributionPointEnabled;
    }

    public void setIssuingDistributionPointEnabled(boolean z) {
        this.issuingDistributionPointEnabled = z;
    }

    public void setAttrCertificateChecking(X509AttributeCertificate x509AttributeCertificate) {
        this.attrCertChecking = x509AttributeCertificate;
    }

    public X509AttributeCertificate getAttrCertificateChecking() {
        return this.attrCertChecking;
    }

    @Override // org.spongycastle.util.Selector
    public boolean match(Object obj) {
        if (!(obj instanceof X509CRL)) {
            return false;
        }
        X509CRL x509crl = (X509CRL) obj;
        ASN1Integer aSN1Integer = null;
        try {
            byte[] extensionValue = x509crl.getExtensionValue(X509Extensions.DeltaCRLIndicator.getId());
            if (extensionValue != null) {
                aSN1Integer = ASN1Integer.getInstance(X509ExtensionUtil.fromExtensionValue(extensionValue));
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
                byte[] extensionValue2 = x509crl.getExtensionValue(X509Extensions.IssuingDistributionPoint.getId());
                byte[] bArr = this.issuingDistributionPoint;
                if (bArr == null) {
                    if (extensionValue2 != null) {
                        return false;
                    }
                } else if (!Arrays.areEqual(extensionValue2, bArr)) {
                    return false;
                }
            }
            return super.match((CRL) x509crl);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean match(CRL crl) {
        return match((Object) crl);
    }

    public boolean isDeltaCRLIndicatorEnabled() {
        return this.deltaCRLIndicator;
    }

    public void setDeltaCRLIndicatorEnabled(boolean z) {
        this.deltaCRLIndicator = z;
    }

    public static X509CRLStoreSelector getInstance(X509CRLSelector x509CRLSelector) {
        if (x509CRLSelector != null) {
            X509CRLStoreSelector x509CRLStoreSelector = new X509CRLStoreSelector();
            x509CRLStoreSelector.setCertificateChecking(x509CRLSelector.getCertificateChecking());
            x509CRLStoreSelector.setDateAndTime(x509CRLSelector.getDateAndTime());
            try {
                x509CRLStoreSelector.setIssuerNames(x509CRLSelector.getIssuerNames());
                x509CRLStoreSelector.setIssuers(x509CRLSelector.getIssuers());
                x509CRLStoreSelector.setMaxCRLNumber(x509CRLSelector.getMaxCRL());
                x509CRLStoreSelector.setMinCRLNumber(x509CRLSelector.getMinCRL());
                return x509CRLStoreSelector;
            } catch (IOException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("cannot create from null selector");
        }
    }

    @Override // java.lang.Object, org.spongycastle.util.Selector
    public Object clone() {
        X509CRLStoreSelector instance = getInstance(this);
        instance.deltaCRLIndicator = this.deltaCRLIndicator;
        instance.completeCRLEnabled = this.completeCRLEnabled;
        instance.maxBaseCRLNumber = this.maxBaseCRLNumber;
        instance.attrCertChecking = this.attrCertChecking;
        instance.issuingDistributionPointEnabled = this.issuingDistributionPointEnabled;
        instance.issuingDistributionPoint = Arrays.clone(this.issuingDistributionPoint);
        return instance;
    }

    public boolean isCompleteCRLEnabled() {
        return this.completeCRLEnabled;
    }

    public void setCompleteCRLEnabled(boolean z) {
        this.completeCRLEnabled = z;
    }

    public BigInteger getMaxBaseCRLNumber() {
        return this.maxBaseCRLNumber;
    }

    public void setMaxBaseCRLNumber(BigInteger bigInteger) {
        this.maxBaseCRLNumber = bigInteger;
    }

    public byte[] getIssuingDistributionPoint() {
        return Arrays.clone(this.issuingDistributionPoint);
    }

    public void setIssuingDistributionPoint(byte[] bArr) {
        this.issuingDistributionPoint = Arrays.clone(bArr);
    }
}
