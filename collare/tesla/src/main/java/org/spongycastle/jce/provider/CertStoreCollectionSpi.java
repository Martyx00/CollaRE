package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.cert.CRL;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CertStoreCollectionSpi extends CertStoreSpi {
    private CollectionCertStoreParameters params;

    public CertStoreCollectionSpi(CertStoreParameters certStoreParameters) {
        super(certStoreParameters);
        if (certStoreParameters instanceof CollectionCertStoreParameters) {
            this.params = (CollectionCertStoreParameters) certStoreParameters;
            return;
        }
        throw new InvalidAlgorithmParameterException("org.spongycastle.jce.provider.CertStoreCollectionSpi: parameter must be a CollectionCertStoreParameters object\n" + certStoreParameters.toString());
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCertificates(CertSelector certSelector) {
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = this.params.getCollection().iterator();
        if (certSelector == null) {
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof Certificate) {
                    arrayList.add(next);
                }
            }
        } else {
            while (it.hasNext()) {
                Object next2 = it.next();
                if ((next2 instanceof Certificate) && certSelector.match((Certificate) next2)) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }

    @Override // java.security.cert.CertStoreSpi
    public Collection engineGetCRLs(CRLSelector cRLSelector) {
        ArrayList arrayList = new ArrayList();
        Iterator<?> it = this.params.getCollection().iterator();
        if (cRLSelector == null) {
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof CRL) {
                    arrayList.add(next);
                }
            }
        } else {
            while (it.hasNext()) {
                Object next2 = it.next();
                if ((next2 instanceof CRL) && cRLSelector.match((CRL) next2)) {
                    arrayList.add(next2);
                }
            }
        }
        return arrayList;
    }
}
