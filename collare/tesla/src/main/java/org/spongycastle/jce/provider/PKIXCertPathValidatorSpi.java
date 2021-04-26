package org.spongycastle.jce.provider;

import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.jcajce.PKIXExtendedBuilderParameters;
import org.spongycastle.jcajce.PKIXExtendedParameters;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.exception.ExtCertPathValidatorException;
import org.spongycastle.x509.ExtendedPKIXParameters;

public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters certPathParameters) {
        PKIXExtendedParameters pKIXExtendedParameters;
        PublicKey publicKey;
        X500Name x500Name;
        HashSet hashSet;
        int i;
        ArrayList[] arrayListArr;
        HashSet hashSet2;
        CertPathValidatorException e;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi = this;
        if (certPathParameters instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder builder = new PKIXExtendedParameters.Builder((PKIXParameters) certPathParameters);
            if (certPathParameters instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extendedPKIXParameters = (ExtendedPKIXParameters) certPathParameters;
                builder.setUseDeltasEnabled(extendedPKIXParameters.isUseDeltasEnabled());
                builder.setValidityModel(extendedPKIXParameters.getValidityModel());
            }
            pKIXExtendedParameters = builder.build();
        } else if (certPathParameters instanceof PKIXExtendedBuilderParameters) {
            pKIXExtendedParameters = ((PKIXExtendedBuilderParameters) certPathParameters).getBaseParameters();
        } else if (certPathParameters instanceof PKIXExtendedParameters) {
            pKIXExtendedParameters = (PKIXExtendedParameters) certPathParameters;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (pKIXExtendedParameters.getTrustAnchors() != null) {
            List<? extends Certificate> certificates = certPath.getCertificates();
            int size = certificates.size();
            if (!certificates.isEmpty()) {
                Set initialPolicies = pKIXExtendedParameters.getInitialPolicies();
                try {
                    TrustAnchor findTrustAnchor = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) certificates.get(certificates.size() - 1), pKIXExtendedParameters.getTrustAnchors(), pKIXExtendedParameters.getSigProvider());
                    if (findTrustAnchor != null) {
                        PKIXExtendedParameters build = new PKIXExtendedParameters.Builder(pKIXExtendedParameters).setTrustAnchor(findTrustAnchor).build();
                        int i2 = size + 1;
                        ArrayList[] arrayListArr2 = new ArrayList[i2];
                        for (int i3 = 0; i3 < arrayListArr2.length; i3++) {
                            arrayListArr2[i3] = new ArrayList();
                        }
                        HashSet hashSet3 = new HashSet();
                        hashSet3.add(RFC3280CertPathUtilities.ANY_POLICY);
                        PKIXPolicyNode pKIXPolicyNode = new PKIXPolicyNode(new ArrayList(), 0, hashSet3, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                        arrayListArr2[0].add(pKIXPolicyNode);
                        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
                        HashSet hashSet4 = new HashSet();
                        int i4 = build.isExplicitPolicyRequired() ? 0 : i2;
                        int i5 = build.isAnyPolicyInhibited() ? 0 : i2;
                        if (build.isPolicyMappingInhibited()) {
                            i2 = 0;
                        }
                        X509Certificate trustedCert = findTrustAnchor.getTrustedCert();
                        if (trustedCert != null) {
                            try {
                                x500Name = PrincipalUtils.getSubjectPrincipal(trustedCert);
                                publicKey = trustedCert.getPublicKey();
                            } catch (IllegalArgumentException e2) {
                                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", e2, certPath, -1);
                            }
                        } else {
                            x500Name = PrincipalUtils.getCA(findTrustAnchor);
                            publicKey = findTrustAnchor.getCAPublicKey();
                        }
                        try {
                            AlgorithmIdentifier algorithmIdentifier = CertPathValidatorUtilities.getAlgorithmIdentifier(publicKey);
                            algorithmIdentifier.getAlgorithm();
                            algorithmIdentifier.getParameters();
                            if (build.getTargetConstraints() == null || build.getTargetConstraints().match((Certificate) ((X509Certificate) certificates.get(0)))) {
                                List<PKIXCertPathChecker> certPathCheckers = build.getCertPathCheckers();
                                for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
                                    pKIXCertPathChecker.init(false);
                                }
                                int i6 = i2;
                                int i7 = size;
                                int i8 = i5;
                                PKIXPolicyNode pKIXPolicyNode2 = pKIXPolicyNode;
                                int size2 = certificates.size() - 1;
                                X509Certificate x509Certificate = null;
                                while (size2 >= 0) {
                                    int i9 = size - size2;
                                    X509Certificate x509Certificate2 = (X509Certificate) certificates.get(size2);
                                    RFC3280CertPathUtilities.processCertA(certPath, build, size2, publicKey, size2 == certificates.size() + -1, x500Name, trustedCert, pKIXCertPathValidatorSpi.helper);
                                    RFC3280CertPathUtilities.processCertBC(certPath, size2, pKIXNameConstraintValidator);
                                    PKIXPolicyNode processCertE = RFC3280CertPathUtilities.processCertE(certPath, size2, RFC3280CertPathUtilities.processCertD(certPath, size2, hashSet4, pKIXPolicyNode2, arrayListArr2, i8));
                                    RFC3280CertPathUtilities.processCertF(certPath, size2, processCertE, i4);
                                    if (i9 == size) {
                                        i = size2;
                                        arrayListArr = arrayListArr2;
                                        pKIXCertPathValidatorSpi = this;
                                        pKIXPolicyNode2 = processCertE;
                                        i8 = i8;
                                        i4 = i4;
                                    } else if (x509Certificate2 == null || x509Certificate2.getVersion() != 1) {
                                        RFC3280CertPathUtilities.prepareNextCertA(certPath, size2);
                                        arrayListArr = arrayListArr2;
                                        PKIXPolicyNode prepareCertB = RFC3280CertPathUtilities.prepareCertB(certPath, size2, arrayListArr, processCertE, i6);
                                        RFC3280CertPathUtilities.prepareNextCertG(certPath, size2, pKIXNameConstraintValidator);
                                        int prepareNextCertH1 = RFC3280CertPathUtilities.prepareNextCertH1(certPath, size2, i4);
                                        int prepareNextCertH2 = RFC3280CertPathUtilities.prepareNextCertH2(certPath, size2, i6);
                                        int prepareNextCertH3 = RFC3280CertPathUtilities.prepareNextCertH3(certPath, size2, i8);
                                        i4 = RFC3280CertPathUtilities.prepareNextCertI1(certPath, size2, prepareNextCertH1);
                                        int prepareNextCertI2 = RFC3280CertPathUtilities.prepareNextCertI2(certPath, size2, prepareNextCertH2);
                                        int prepareNextCertJ = RFC3280CertPathUtilities.prepareNextCertJ(certPath, size2, prepareNextCertH3);
                                        RFC3280CertPathUtilities.prepareNextCertK(certPath, size2);
                                        int prepareNextCertM = RFC3280CertPathUtilities.prepareNextCertM(certPath, size2, RFC3280CertPathUtilities.prepareNextCertL(certPath, size2, i7));
                                        RFC3280CertPathUtilities.prepareNextCertN(certPath, size2);
                                        Set<String> criticalExtensionOIDs = x509Certificate2.getCriticalExtensionOIDs();
                                        if (criticalExtensionOIDs != null) {
                                            hashSet2 = new HashSet(criticalExtensionOIDs);
                                            hashSet2.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                            hashSet2.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                            hashSet2.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                            hashSet2.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                            hashSet2.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                            hashSet2.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                            hashSet2.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                            hashSet2.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                            hashSet2.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                            hashSet2.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                        } else {
                                            hashSet2 = new HashSet();
                                        }
                                        RFC3280CertPathUtilities.prepareNextCertO(certPath, size2, hashSet2, certPathCheckers);
                                        x500Name = PrincipalUtils.getSubjectPrincipal(x509Certificate2);
                                        try {
                                            i = size2;
                                            pKIXCertPathValidatorSpi = this;
                                            try {
                                                publicKey = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), i, pKIXCertPathValidatorSpi.helper);
                                                AlgorithmIdentifier algorithmIdentifier2 = CertPathValidatorUtilities.getAlgorithmIdentifier(publicKey);
                                                algorithmIdentifier2.getAlgorithm();
                                                algorithmIdentifier2.getParameters();
                                                pKIXPolicyNode2 = prepareCertB;
                                                i6 = prepareNextCertI2;
                                                i8 = prepareNextCertJ;
                                                i7 = prepareNextCertM;
                                                trustedCert = x509Certificate2;
                                            } catch (CertPathValidatorException e3) {
                                                e = e3;
                                                throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i);
                                            }
                                        } catch (CertPathValidatorException e4) {
                                            e = e4;
                                            i = size2;
                                            throw new CertPathValidatorException("Next working key could not be retrieved.", e, certPath, i);
                                        }
                                    } else {
                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", null, certPath, size2);
                                    }
                                    size2 = i - 1;
                                    arrayListArr2 = arrayListArr;
                                    x509Certificate = x509Certificate2;
                                    certificates = certificates;
                                    initialPolicies = initialPolicies;
                                    findTrustAnchor = findTrustAnchor;
                                    pKIXNameConstraintValidator = pKIXNameConstraintValidator;
                                    build = build;
                                }
                                int i10 = size2 + 1;
                                int wrapupCertB = RFC3280CertPathUtilities.wrapupCertB(certPath, i10, RFC3280CertPathUtilities.wrapupCertA(i4, x509Certificate));
                                Set<String> criticalExtensionOIDs2 = x509Certificate.getCriticalExtensionOIDs();
                                if (criticalExtensionOIDs2 != null) {
                                    hashSet = new HashSet(criticalExtensionOIDs2);
                                    hashSet.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                    hashSet.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                    hashSet.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                    hashSet.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                    hashSet.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                    hashSet.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                    hashSet.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                    hashSet.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                    hashSet.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                                    hashSet.remove(Extension.extendedKeyUsage.getId());
                                } else {
                                    hashSet = new HashSet();
                                }
                                RFC3280CertPathUtilities.wrapupCertF(certPath, i10, certPathCheckers, hashSet);
                                PKIXPolicyNode wrapupCertG = RFC3280CertPathUtilities.wrapupCertG(certPath, build, initialPolicies, i10, arrayListArr2, pKIXPolicyNode2, hashSet4);
                                if (wrapupCertB > 0 || wrapupCertG != null) {
                                    return new PKIXCertPathValidatorResult(findTrustAnchor, wrapupCertG, x509Certificate.getPublicKey());
                                }
                                throw new CertPathValidatorException("Path processing failed on policy.", null, certPath, size2);
                            }
                            throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath, 0);
                        } catch (CertPathValidatorException e5) {
                            throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e5, certPath, -1);
                        }
                    } else {
                        throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath, -1);
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathValidatorException(e6.getMessage(), e6, certPath, certificates.size() - 1);
                }
            } else {
                throw new CertPathValidatorException("Certification path is empty.", null, certPath, -1);
            }
        } else {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
    }
}
