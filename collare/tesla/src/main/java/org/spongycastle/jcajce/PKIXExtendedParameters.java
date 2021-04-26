package org.spongycastle.jcajce;

import java.security.cert.CertPathParameters;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.jcajce.PKIXCertStoreSelector;

public class PKIXExtendedParameters implements CertPathParameters {
    public static final int CHAIN_VALIDITY_MODEL = 1;
    public static final int PKIX_VALIDITY_MODEL = 0;
    private final PKIXParameters baseParameters;
    private final Date date;
    private final List<PKIXCRLStore> extraCRLStores;
    private final List<PKIXCertStore> extraCertStores;
    private final Map<GeneralName, PKIXCRLStore> namedCRLStoreMap;
    private final Map<GeneralName, PKIXCertStore> namedCertificateStoreMap;
    private final boolean revocationEnabled;
    private final PKIXCertStoreSelector targetConstraints;
    private final Set<TrustAnchor> trustAnchors;
    private final boolean useDeltas;
    private final int validityModel;

    @Override // java.lang.Object
    public Object clone() {
        return this;
    }

    public static class Builder {
        private final PKIXParameters baseParameters;
        private final Date date;
        private List<PKIXCRLStore> extraCRLStores = new ArrayList();
        private List<PKIXCertStore> extraCertStores = new ArrayList();
        private Map<GeneralName, PKIXCRLStore> namedCRLStoreMap = new HashMap();
        private Map<GeneralName, PKIXCertStore> namedCertificateStoreMap = new HashMap();
        private boolean revocationEnabled;
        private PKIXCertStoreSelector targetConstraints;
        private Set<TrustAnchor> trustAnchors;
        private boolean useDeltas = false;
        private int validityModel = 0;

        public Builder(PKIXParameters pKIXParameters) {
            this.baseParameters = (PKIXParameters) pKIXParameters.clone();
            CertSelector targetCertConstraints = pKIXParameters.getTargetCertConstraints();
            if (targetCertConstraints != null) {
                this.targetConstraints = new PKIXCertStoreSelector.Builder(targetCertConstraints).build();
            }
            Date date2 = pKIXParameters.getDate();
            this.date = date2 == null ? new Date() : date2;
            this.revocationEnabled = pKIXParameters.isRevocationEnabled();
            this.trustAnchors = pKIXParameters.getTrustAnchors();
        }

        public Builder(PKIXExtendedParameters pKIXExtendedParameters) {
            this.baseParameters = pKIXExtendedParameters.baseParameters;
            this.date = pKIXExtendedParameters.date;
            this.targetConstraints = pKIXExtendedParameters.targetConstraints;
            this.extraCertStores = new ArrayList(pKIXExtendedParameters.extraCertStores);
            this.namedCertificateStoreMap = new HashMap(pKIXExtendedParameters.namedCertificateStoreMap);
            this.extraCRLStores = new ArrayList(pKIXExtendedParameters.extraCRLStores);
            this.namedCRLStoreMap = new HashMap(pKIXExtendedParameters.namedCRLStoreMap);
            this.useDeltas = pKIXExtendedParameters.useDeltas;
            this.validityModel = pKIXExtendedParameters.validityModel;
            this.revocationEnabled = pKIXExtendedParameters.isRevocationEnabled();
            this.trustAnchors = pKIXExtendedParameters.getTrustAnchors();
        }

        public Builder addCertificateStore(PKIXCertStore pKIXCertStore) {
            this.extraCertStores.add(pKIXCertStore);
            return this;
        }

        public Builder addNamedCertificateStore(GeneralName generalName, PKIXCertStore pKIXCertStore) {
            this.namedCertificateStoreMap.put(generalName, pKIXCertStore);
            return this;
        }

        public Builder addCRLStore(PKIXCRLStore pKIXCRLStore) {
            this.extraCRLStores.add(pKIXCRLStore);
            return this;
        }

        public Builder addNamedCRLStore(GeneralName generalName, PKIXCRLStore pKIXCRLStore) {
            this.namedCRLStoreMap.put(generalName, pKIXCRLStore);
            return this;
        }

        public Builder setTargetConstraints(PKIXCertStoreSelector pKIXCertStoreSelector) {
            this.targetConstraints = pKIXCertStoreSelector;
            return this;
        }

        public Builder setUseDeltasEnabled(boolean z) {
            this.useDeltas = z;
            return this;
        }

        public Builder setValidityModel(int i) {
            this.validityModel = i;
            return this;
        }

        public Builder setTrustAnchor(TrustAnchor trustAnchor) {
            this.trustAnchors = Collections.singleton(trustAnchor);
            return this;
        }

        public Builder setTrustAnchors(Set<TrustAnchor> set) {
            this.trustAnchors = set;
            return this;
        }

        public void setRevocationEnabled(boolean z) {
            this.revocationEnabled = z;
        }

        public PKIXExtendedParameters build() {
            return new PKIXExtendedParameters(this);
        }
    }

    private PKIXExtendedParameters(Builder builder) {
        this.baseParameters = builder.baseParameters;
        this.date = builder.date;
        this.extraCertStores = Collections.unmodifiableList(builder.extraCertStores);
        this.namedCertificateStoreMap = Collections.unmodifiableMap(new HashMap(builder.namedCertificateStoreMap));
        this.extraCRLStores = Collections.unmodifiableList(builder.extraCRLStores);
        this.namedCRLStoreMap = Collections.unmodifiableMap(new HashMap(builder.namedCRLStoreMap));
        this.targetConstraints = builder.targetConstraints;
        this.revocationEnabled = builder.revocationEnabled;
        this.useDeltas = builder.useDeltas;
        this.validityModel = builder.validityModel;
        this.trustAnchors = Collections.unmodifiableSet(builder.trustAnchors);
    }

    public List<PKIXCertStore> getCertificateStores() {
        return this.extraCertStores;
    }

    public Map<GeneralName, PKIXCertStore> getNamedCertificateStoreMap() {
        return this.namedCertificateStoreMap;
    }

    public List<PKIXCRLStore> getCRLStores() {
        return this.extraCRLStores;
    }

    public Map<GeneralName, PKIXCRLStore> getNamedCRLStoreMap() {
        return this.namedCRLStoreMap;
    }

    public Date getDate() {
        return new Date(this.date.getTime());
    }

    public boolean isUseDeltasEnabled() {
        return this.useDeltas;
    }

    public int getValidityModel() {
        return this.validityModel;
    }

    public PKIXCertStoreSelector getTargetConstraints() {
        return this.targetConstraints;
    }

    public Set getTrustAnchors() {
        return this.trustAnchors;
    }

    public Set getInitialPolicies() {
        return this.baseParameters.getInitialPolicies();
    }

    public String getSigProvider() {
        return this.baseParameters.getSigProvider();
    }

    public boolean isExplicitPolicyRequired() {
        return this.baseParameters.isExplicitPolicyRequired();
    }

    public boolean isAnyPolicyInhibited() {
        return this.baseParameters.isAnyPolicyInhibited();
    }

    public boolean isPolicyMappingInhibited() {
        return this.baseParameters.isPolicyMappingInhibited();
    }

    public List getCertPathCheckers() {
        return this.baseParameters.getCertPathCheckers();
    }

    public List<CertStore> getCertStores() {
        return this.baseParameters.getCertStores();
    }

    public boolean isRevocationEnabled() {
        return this.revocationEnabled;
    }
}
