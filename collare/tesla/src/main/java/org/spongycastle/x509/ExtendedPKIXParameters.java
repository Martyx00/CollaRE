package org.spongycastle.x509;

import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CertSelector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.spongycastle.util.Selector;
import org.spongycastle.util.Store;

public class ExtendedPKIXParameters extends PKIXParameters {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    private boolean additionalLocationsEnabled;
    private List additionalStores = new ArrayList();
    private Set attrCertCheckers = new HashSet();
    private Set necessaryACAttributes = new HashSet();
    private Set prohibitedACAttributes = new HashSet();
    private Selector selector;
    private List stores = new ArrayList();
    private Set trustedACIssuers = new HashSet();
    private boolean useDeltas = false;
    private int validityModel = 0;

    public ExtendedPKIXParameters(Set set) {
        super(set);
    }

    public static ExtendedPKIXParameters getInstance(PKIXParameters pKIXParameters) {
        try {
            ExtendedPKIXParameters extendedPKIXParameters = new ExtendedPKIXParameters(pKIXParameters.getTrustAnchors());
            extendedPKIXParameters.setParams(pKIXParameters);
            return extendedPKIXParameters;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void setParams(PKIXParameters pKIXParameters) {
        Selector selector2;
        setDate(pKIXParameters.getDate());
        setCertPathCheckers(pKIXParameters.getCertPathCheckers());
        setCertStores(pKIXParameters.getCertStores());
        setAnyPolicyInhibited(pKIXParameters.isAnyPolicyInhibited());
        setExplicitPolicyRequired(pKIXParameters.isExplicitPolicyRequired());
        setPolicyMappingInhibited(pKIXParameters.isPolicyMappingInhibited());
        setRevocationEnabled(pKIXParameters.isRevocationEnabled());
        setInitialPolicies(pKIXParameters.getInitialPolicies());
        setPolicyQualifiersRejected(pKIXParameters.getPolicyQualifiersRejected());
        setSigProvider(pKIXParameters.getSigProvider());
        setTargetCertConstraints(pKIXParameters.getTargetCertConstraints());
        try {
            setTrustAnchors(pKIXParameters.getTrustAnchors());
            if (pKIXParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) pKIXParameters;
                this.validityModel = extendedPKIXParameters.validityModel;
                this.useDeltas = extendedPKIXParameters.useDeltas;
                this.additionalLocationsEnabled = extendedPKIXParameters.additionalLocationsEnabled;
                Selector selector3 = extendedPKIXParameters.selector;
                if (selector3 == null) {
                    selector2 = null;
                } else {
                    selector2 = (Selector) selector3.clone();
                }
                this.selector = selector2;
                this.stores = new ArrayList(extendedPKIXParameters.stores);
                this.additionalStores = new ArrayList(extendedPKIXParameters.additionalStores);
                this.trustedACIssuers = new HashSet(extendedPKIXParameters.trustedACIssuers);
                this.prohibitedACAttributes = new HashSet(extendedPKIXParameters.prohibitedACAttributes);
                this.necessaryACAttributes = new HashSet(extendedPKIXParameters.necessaryACAttributes);
                this.attrCertCheckers = new HashSet(extendedPKIXParameters.attrCertCheckers);
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isUseDeltasEnabled() {
        return this.useDeltas;
    }

    public void setUseDeltasEnabled(boolean z) {
        this.useDeltas = z;
    }

    public int getValidityModel() {
        return this.validityModel;
    }

    @Override // java.security.cert.PKIXParameters
    public void setCertStores(List list) {
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                addCertStore((CertStore) it.next());
            }
        }
    }

    public void setStores(List list) {
        if (list == null) {
            this.stores = new ArrayList();
            return;
        }
        for (Object obj : list) {
            if (!(obj instanceof Store)) {
                throw new ClassCastException("All elements of list must be of type org.spongycastle.util.Store.");
            }
        }
        this.stores = new ArrayList(list);
    }

    public void addStore(Store store) {
        if (store != null) {
            this.stores.add(store);
        }
    }

    public void addAdditionalStore(Store store) {
        if (store != null) {
            this.additionalStores.add(store);
        }
    }

    public void addAddionalStore(Store store) {
        addAdditionalStore(store);
    }

    public List getAdditionalStores() {
        return Collections.unmodifiableList(this.additionalStores);
    }

    public List getStores() {
        return Collections.unmodifiableList(new ArrayList(this.stores));
    }

    public void setValidityModel(int i) {
        this.validityModel = i;
    }

    @Override // java.lang.Object
    public Object clone() {
        try {
            ExtendedPKIXParameters extendedPKIXParameters = new ExtendedPKIXParameters(getTrustAnchors());
            extendedPKIXParameters.setParams(this);
            return extendedPKIXParameters;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public boolean isAdditionalLocationsEnabled() {
        return this.additionalLocationsEnabled;
    }

    public void setAdditionalLocationsEnabled(boolean z) {
        this.additionalLocationsEnabled = z;
    }

    public Selector getTargetConstraints() {
        Selector selector2 = this.selector;
        if (selector2 != null) {
            return (Selector) selector2.clone();
        }
        return null;
    }

    public void setTargetConstraints(Selector selector2) {
        if (selector2 != null) {
            this.selector = (Selector) selector2.clone();
        } else {
            this.selector = null;
        }
    }

    public void setTargetCertConstraints(CertSelector certSelector) {
        super.setTargetCertConstraints(certSelector);
        if (certSelector != null) {
            this.selector = X509CertStoreSelector.getInstance((X509CertSelector) certSelector);
        } else {
            this.selector = null;
        }
    }

    public Set getTrustedACIssuers() {
        return Collections.unmodifiableSet(this.trustedACIssuers);
    }

    public void setTrustedACIssuers(Set set) {
        if (set == null) {
            this.trustedACIssuers.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof TrustAnchor)) {
                throw new ClassCastException("All elements of set must be of type " + TrustAnchor.class.getName() + ".");
            }
        }
        this.trustedACIssuers.clear();
        this.trustedACIssuers.addAll(set);
    }

    public Set getNecessaryACAttributes() {
        return Collections.unmodifiableSet(this.necessaryACAttributes);
    }

    public void setNecessaryACAttributes(Set set) {
        if (set == null) {
            this.necessaryACAttributes.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof String)) {
                throw new ClassCastException("All elements of set must be of type String.");
            }
        }
        this.necessaryACAttributes.clear();
        this.necessaryACAttributes.addAll(set);
    }

    public Set getProhibitedACAttributes() {
        return Collections.unmodifiableSet(this.prohibitedACAttributes);
    }

    public void setProhibitedACAttributes(Set set) {
        if (set == null) {
            this.prohibitedACAttributes.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof String)) {
                throw new ClassCastException("All elements of set must be of type String.");
            }
        }
        this.prohibitedACAttributes.clear();
        this.prohibitedACAttributes.addAll(set);
    }

    public Set getAttrCertCheckers() {
        return Collections.unmodifiableSet(this.attrCertCheckers);
    }

    public void setAttrCertCheckers(Set set) {
        if (set == null) {
            this.attrCertCheckers.clear();
            return;
        }
        for (Object obj : set) {
            if (!(obj instanceof PKIXAttrCertChecker)) {
                throw new ClassCastException("All elements of set must be of type " + PKIXAttrCertChecker.class.getName() + ".");
            }
        }
        this.attrCertCheckers.clear();
        this.attrCertCheckers.addAll(set);
    }
}
