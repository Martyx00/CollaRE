package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.spongycastle.jce.MultiCertStoreParameters;

public class MultiCertStoreSpi extends CertStoreSpi {
    private MultiCertStoreParameters params;

    public MultiCertStoreSpi(CertStoreParameters certStoreParameters) {
        super(certStoreParameters);
        if (certStoreParameters instanceof MultiCertStoreParameters) {
            this.params = (MultiCertStoreParameters) certStoreParameters;
            return;
        }
        throw new InvalidAlgorithmParameterException("org.spongycastle.jce.provider.MultiCertStoreSpi: parameter must be a MultiCertStoreParameters object\n" + certStoreParameters.toString());
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCertificates(CertSelector certSelector) {
        boolean searchAllStores = this.params.getSearchAllStores();
        List arrayList = searchAllStores ? new ArrayList() : Collections.EMPTY_LIST;
        for (CertStore certStore : this.params.getCertStores()) {
            Collection<? extends Certificate> certificates = certStore.getCertificates(certSelector);
            if (searchAllStores) {
                arrayList.addAll(certificates);
            } else if (!certificates.isEmpty()) {
                return certificates;
            }
        }
        return arrayList;
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCRLs(CRLSelector cRLSelector) {
        boolean searchAllStores = this.params.getSearchAllStores();
        List arrayList = searchAllStores ? new ArrayList() : Collections.EMPTY_LIST;
        for (CertStore certStore : this.params.getCertStores()) {
            Collection<? extends CRL> cRLs = certStore.getCRLs(cRLSelector);
            if (searchAllStores) {
                arrayList.addAll(cRLs);
            } else if (!cRLs.isEmpty()) {
                return cRLs;
            }
        }
        return arrayList;
    }
}
