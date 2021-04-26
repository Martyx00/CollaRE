package org.spongycastle.jce.provider;

import java.util.Collection;
import org.spongycastle.util.CollectionStore;
import org.spongycastle.util.Selector;
import org.spongycastle.x509.X509CollectionStoreParameters;
import org.spongycastle.x509.X509StoreParameters;
import org.spongycastle.x509.X509StoreSpi;

public class X509StoreCRLCollection extends X509StoreSpi {
    private CollectionStore _store;

    @Override // org.spongycastle.x509.X509StoreSpi
    public void engineInit(X509StoreParameters x509StoreParameters) {
        if (x509StoreParameters instanceof X509CollectionStoreParameters) {
            this._store = new CollectionStore(((X509CollectionStoreParameters) x509StoreParameters).getCollection());
            return;
        }
        throw new IllegalArgumentException(x509StoreParameters.toString());
    }

    @Override // org.spongycastle.x509.X509StoreSpi
    public Collection engineGetMatches(Selector selector) {
        return this._store.getMatches(selector);
    }
}
