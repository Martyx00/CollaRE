package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXParameters;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.x509.AccessDescription;
import org.spongycastle.asn1.x509.AuthorityInformationAccess;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.BasicConstraints;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.DistributionPoint;
import org.spongycastle.asn1.x509.DistributionPointName;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.asn1.x509.NameConstraints;
import org.spongycastle.asn1.x509.PolicyInformation;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.asn1.x509.qualified.MonetaryValue;
import org.spongycastle.asn1.x509.qualified.QCStatement;
import org.spongycastle.i18n.ErrorBundle;
import org.spongycastle.i18n.filter.TrustedInput;
import org.spongycastle.i18n.filter.UntrustedInput;
import org.spongycastle.jce.provider.AnnotatedException;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.PKIXNameConstraintValidator;
import org.spongycastle.jce.provider.PKIXNameConstraintValidatorException;
import org.spongycastle.jce.provider.PKIXPolicyNode;
import org.spongycastle.jce.provider.RFC3280CertPathUtilities;
import org.spongycastle.util.Integers;

public class PKIXCertPathReviewer extends CertPathValidatorUtilities {
    private static final String AUTH_INFO_ACCESS = X509Extensions.AuthorityInfoAccess.getId();
    private static final String CRL_DIST_POINTS = X509Extensions.CRLDistributionPoints.getId();
    private static final String QC_STATEMENT = X509Extensions.QCStatements.getId();
    private static final String RESOURCE_NAME = "org.spongycastle.x509.CertPathReviewerMessages";
    protected CertPath certPath;
    protected List certs;
    protected List[] errors;
    private boolean initialized;
    protected int n;
    protected List[] notifications;
    protected PKIXParameters pkixParams;
    protected PolicyNode policyTree;
    protected PublicKey subjectPublicKey;
    protected TrustAnchor trustAnchor;
    protected Date validDate;

    public void init(CertPath certPath2, PKIXParameters pKIXParameters) {
        if (!this.initialized) {
            this.initialized = true;
            if (certPath2 != null) {
                this.certPath = certPath2;
                this.certs = certPath2.getCertificates();
                this.n = this.certs.size();
                if (!this.certs.isEmpty()) {
                    this.pkixParams = (PKIXParameters) pKIXParameters.clone();
                    this.validDate = getValidDate(this.pkixParams);
                    this.notifications = null;
                    this.errors = null;
                    this.trustAnchor = null;
                    this.subjectPublicKey = null;
                    this.policyTree = null;
                    return;
                }
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.emptyCertPath"));
            }
            throw new NullPointerException("certPath was null");
        }
        throw new IllegalStateException("object is already initialized!");
    }

    public PKIXCertPathReviewer(CertPath certPath2, PKIXParameters pKIXParameters) {
        init(certPath2, pKIXParameters);
    }

    public PKIXCertPathReviewer() {
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getCertPathSize() {
        return this.n;
    }

    public List[] getErrors() {
        doChecks();
        return this.errors;
    }

    public List getErrors(int i) {
        doChecks();
        return this.errors[i + 1];
    }

    public List[] getNotifications() {
        doChecks();
        return this.notifications;
    }

    public List getNotifications(int i) {
        doChecks();
        return this.notifications[i + 1];
    }

    public PolicyNode getPolicyTree() {
        doChecks();
        return this.policyTree;
    }

    public PublicKey getSubjectPublicKey() {
        doChecks();
        return this.subjectPublicKey;
    }

    public TrustAnchor getTrustAnchor() {
        doChecks();
        return this.trustAnchor;
    }

    public boolean isValidCertPath() {
        doChecks();
        int i = 0;
        while (true) {
            List[] listArr = this.errors;
            if (i >= listArr.length) {
                return true;
            }
            if (!listArr[i].isEmpty()) {
                return false;
            }
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle errorBundle) {
        this.notifications[0].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addNotification(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.notifications[i + 1].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle errorBundle) {
        this.errors[0].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void addError(ErrorBundle errorBundle, int i) {
        if (i < -1 || i >= this.n) {
            throw new IndexOutOfBoundsException();
        }
        this.errors[i + 1].add(errorBundle);
    }

    /* access modifiers changed from: protected */
    public void doChecks() {
        if (!this.initialized) {
            throw new IllegalStateException("Object not initialized. Call init() first.");
        } else if (this.notifications == null) {
            int i = this.n;
            this.notifications = new List[(i + 1)];
            this.errors = new List[(i + 1)];
            int i2 = 0;
            while (true) {
                List[] listArr = this.notifications;
                if (i2 < listArr.length) {
                    listArr[i2] = new ArrayList();
                    this.errors[i2] = new ArrayList();
                    i2++;
                } else {
                    checkSignatures();
                    checkNameConstraints();
                    checkPathLength();
                    checkPolicy();
                    checkCriticalExtensions();
                    return;
                }
            }
        }
    }

    private void checkNameConstraints() {
        PKIXNameConstraintValidator pKIXNameConstraintValidator = new PKIXNameConstraintValidator();
        try {
            for (int size = this.certs.size() - 1; size > 0; size--) {
                int i = this.n;
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                if (!isSelfIssued(x509Certificate)) {
                    X500Principal subjectPrincipal = getSubjectPrincipal(x509Certificate);
                    try {
                        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(new ByteArrayInputStream(subjectPrincipal.getEncoded())).readObject();
                        try {
                            pKIXNameConstraintValidator.checkPermittedDN(aSN1Sequence);
                            try {
                                pKIXNameConstraintValidator.checkExcludedDN(aSN1Sequence);
                                try {
                                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) getExtensionValue(x509Certificate, SUBJECT_ALTERNATIVE_NAME);
                                    if (aSN1Sequence2 != null) {
                                        for (int i2 = 0; i2 < aSN1Sequence2.size(); i2++) {
                                            GeneralName instance = GeneralName.getInstance(aSN1Sequence2.getObjectAt(i2));
                                            try {
                                                pKIXNameConstraintValidator.checkPermitted(instance);
                                                pKIXNameConstraintValidator.checkExcluded(instance);
                                            } catch (PKIXNameConstraintValidatorException e) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedEmail", new Object[]{new UntrustedInput(instance)}), e, this.certPath, size);
                                            }
                                        }
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.subjAltNameExtError"), e2, this.certPath, size);
                                }
                            } catch (PKIXNameConstraintValidatorException e3) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.excludedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e3, this.certPath, size);
                            }
                        } catch (PKIXNameConstraintValidatorException e4) {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.notPermittedDN", new Object[]{new UntrustedInput(subjectPrincipal.getName())}), e4, this.certPath, size);
                        }
                    } catch (IOException e5) {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncSubjectNameError", new Object[]{new UntrustedInput(subjectPrincipal)}), e5, this.certPath, size);
                    }
                }
                try {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence) getExtensionValue(x509Certificate, NAME_CONSTRAINTS);
                    if (aSN1Sequence3 != null) {
                        NameConstraints instance2 = NameConstraints.getInstance(aSN1Sequence3);
                        GeneralSubtree[] permittedSubtrees = instance2.getPermittedSubtrees();
                        if (permittedSubtrees != null) {
                            pKIXNameConstraintValidator.intersectPermittedSubtree(permittedSubtrees);
                        }
                        GeneralSubtree[] excludedSubtrees = instance2.getExcludedSubtrees();
                        if (excludedSubtrees != null) {
                            for (int i3 = 0; i3 != excludedSubtrees.length; i3++) {
                                pKIXNameConstraintValidator.addExcludedSubtree(excludedSubtrees[i3]);
                            }
                        }
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.ncExtError"), e6, this.certPath, size);
                }
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkPathLength() {
        BasicConstraints basicConstraints;
        BigInteger pathLenConstraint;
        int intValue;
        int i = this.n;
        int i2 = i;
        int i3 = 0;
        for (int size = this.certs.size() - 1; size > 0; size--) {
            int i4 = this.n;
            X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
            if (!isSelfIssued(x509Certificate)) {
                if (i2 <= 0) {
                    addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.pathLengthExtended"));
                }
                i2--;
                i3++;
            }
            try {
                basicConstraints = BasicConstraints.getInstance(getExtensionValue(x509Certificate, BASIC_CONSTRAINTS));
            } catch (AnnotatedException unused) {
                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.processLengthConstError"), size);
                basicConstraints = null;
            }
            if (!(basicConstraints == null || (pathLenConstraint = basicConstraints.getPathLenConstraint()) == null || (intValue = pathLenConstraint.intValue()) >= i2)) {
                i2 = intValue;
            }
        }
        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.totalPathLength", new Object[]{Integers.valueOf(i3)}));
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x03a7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x014e  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0187  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkSignatures() {
        /*
        // Method dump skipped, instructions count: 1083
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkSignatures():void");
    }

    private void checkPolicy() {
        int i;
        int i2;
        int i3;
        int i4;
        PKIXPolicyNode pKIXPolicyNode;
        X509Certificate x509Certificate;
        PKIXPolicyNode pKIXPolicyNode2;
        HashSet hashSet;
        X509Certificate x509Certificate2;
        int i5;
        HashSet hashSet2;
        HashSet hashSet3;
        String str;
        X509Certificate x509Certificate3;
        PKIXPolicyNode pKIXPolicyNode3;
        Set<String> initialPolicies = this.pkixParams.getInitialPolicies();
        ArrayList[] arrayListArr = new ArrayList[(this.n + 1)];
        for (int i6 = 0; i6 < arrayListArr.length; i6++) {
            arrayListArr[i6] = new ArrayList();
        }
        HashSet hashSet4 = new HashSet();
        hashSet4.add(RFC3280CertPathUtilities.ANY_POLICY);
        PKIXPolicyNode pKIXPolicyNode4 = new PKIXPolicyNode(new ArrayList(), 0, hashSet4, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
        arrayListArr[0].add(pKIXPolicyNode4);
        if (this.pkixParams.isExplicitPolicyRequired()) {
            i = 0;
        } else {
            i = this.n + 1;
        }
        if (this.pkixParams.isAnyPolicyInhibited()) {
            i2 = 0;
        } else {
            i2 = this.n + 1;
        }
        if (this.pkixParams.isPolicyMappingInhibited()) {
            i3 = 0;
        } else {
            i3 = this.n + 1;
        }
        try {
            int size = this.certs.size() - 1;
            int i7 = i2;
            int i8 = i3;
            HashSet hashSet5 = null;
            PKIXPolicyNode pKIXPolicyNode5 = pKIXPolicyNode4;
            X509Certificate x509Certificate4 = null;
            while (size >= 0) {
                int i9 = this.n - size;
                X509Certificate x509Certificate5 = (X509Certificate) this.certs.get(size);
                try {
                    ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate5, CERTIFICATE_POLICIES);
                    if (aSN1Sequence == null || pKIXPolicyNode5 == null) {
                        x509Certificate = x509Certificate5;
                        pKIXPolicyNode2 = pKIXPolicyNode5;
                    } else {
                        Enumeration objects = aSN1Sequence.getObjects();
                        HashSet hashSet6 = new HashSet();
                        while (objects.hasMoreElements()) {
                            PolicyInformation instance = PolicyInformation.getInstance(objects.nextElement());
                            ASN1ObjectIdentifier policyIdentifier = instance.getPolicyIdentifier();
                            hashSet6.add(policyIdentifier.getId());
                            if (!RFC3280CertPathUtilities.ANY_POLICY.equals(policyIdentifier.getId())) {
                                try {
                                    Set qualifierSet = getQualifierSet(instance.getPolicyQualifiers());
                                    if (!processCertD1i(i9, arrayListArr, policyIdentifier, qualifierSet)) {
                                        processCertD1ii(i9, arrayListArr, policyIdentifier, qualifierSet);
                                    }
                                } catch (CertPathValidatorException e) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e, this.certPath, size);
                                }
                            }
                        }
                        if (hashSet5 == null || hashSet5.contains(RFC3280CertPathUtilities.ANY_POLICY)) {
                            hashSet2 = hashSet6;
                        } else {
                            hashSet2 = new HashSet();
                            for (Object obj : hashSet5) {
                                if (hashSet6.contains(obj)) {
                                    hashSet2.add(obj);
                                }
                            }
                        }
                        if (i7 > 0 || (i9 < this.n && isSelfIssued(x509Certificate5))) {
                            Enumeration objects2 = aSN1Sequence.getObjects();
                            while (true) {
                                if (objects2.hasMoreElements()) {
                                    PolicyInformation instance2 = PolicyInformation.getInstance(objects2.nextElement());
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(instance2.getPolicyIdentifier().getId())) {
                                        try {
                                            Set qualifierSet2 = getQualifierSet(instance2.getPolicyQualifiers());
                                            ArrayList arrayList = arrayListArr[i9 - 1];
                                            for (int i10 = 0; i10 < arrayList.size(); i10++) {
                                                PKIXPolicyNode pKIXPolicyNode6 = (PKIXPolicyNode) arrayList.get(i10);
                                                for (Object obj2 : pKIXPolicyNode6.getExpectedPolicies()) {
                                                    if (obj2 instanceof String) {
                                                        str = (String) obj2;
                                                    } else if (obj2 instanceof ASN1ObjectIdentifier) {
                                                        str = ((ASN1ObjectIdentifier) obj2).getId();
                                                    } else {
                                                        hashSet2 = hashSet2;
                                                    }
                                                    Iterator children = pKIXPolicyNode6.getChildren();
                                                    boolean z = false;
                                                    while (children.hasNext()) {
                                                        if (str.equals(((PKIXPolicyNode) children.next()).getValidPolicy())) {
                                                            z = true;
                                                        }
                                                        arrayList = arrayList;
                                                    }
                                                    if (!z) {
                                                        HashSet hashSet7 = new HashSet();
                                                        hashSet7.add(str);
                                                        x509Certificate3 = x509Certificate5;
                                                        PKIXPolicyNode pKIXPolicyNode7 = new PKIXPolicyNode(new ArrayList(), i9, hashSet7, pKIXPolicyNode6, qualifierSet2, str, false);
                                                        pKIXPolicyNode3 = pKIXPolicyNode6;
                                                        pKIXPolicyNode3.addChild(pKIXPolicyNode7);
                                                        arrayListArr[i9].add(pKIXPolicyNode7);
                                                    } else {
                                                        pKIXPolicyNode3 = pKIXPolicyNode6;
                                                        x509Certificate3 = x509Certificate5;
                                                    }
                                                    pKIXPolicyNode6 = pKIXPolicyNode3;
                                                    hashSet2 = hashSet2;
                                                    arrayList = arrayList;
                                                    x509Certificate5 = x509Certificate3;
                                                }
                                            }
                                            hashSet3 = hashSet2;
                                            x509Certificate = x509Certificate5;
                                        } catch (CertPathValidatorException e2) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e2, this.certPath, size);
                                        }
                                    }
                                } else {
                                    hashSet3 = hashSet2;
                                    x509Certificate = x509Certificate5;
                                }
                            }
                        } else {
                            hashSet3 = hashSet2;
                            x509Certificate = x509Certificate5;
                        }
                        for (int i11 = i9 - 1; i11 >= 0; i11--) {
                            ArrayList arrayList2 = arrayListArr[i11];
                            PKIXPolicyNode pKIXPolicyNode8 = pKIXPolicyNode5;
                            for (int i12 = 0; i12 < arrayList2.size(); i12++) {
                                PKIXPolicyNode pKIXPolicyNode9 = (PKIXPolicyNode) arrayList2.get(i12);
                                if (!pKIXPolicyNode9.hasChildren() && (pKIXPolicyNode8 = removePolicyNode(pKIXPolicyNode8, arrayListArr, pKIXPolicyNode9)) == null) {
                                    pKIXPolicyNode5 = pKIXPolicyNode8;
                                }
                            }
                            pKIXPolicyNode5 = pKIXPolicyNode8;
                        }
                        Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                        if (criticalExtensionOIDs != null) {
                            boolean contains = criticalExtensionOIDs.contains(CERTIFICATE_POLICIES);
                            ArrayList arrayList3 = arrayListArr[i9];
                            for (int i13 = 0; i13 < arrayList3.size(); i13++) {
                                ((PKIXPolicyNode) arrayList3.get(i13)).setCritical(contains);
                            }
                        }
                        pKIXPolicyNode2 = pKIXPolicyNode5;
                        hashSet5 = hashSet3;
                    }
                    if (aSN1Sequence == null) {
                        pKIXPolicyNode2 = null;
                    }
                    if (i > 0 || pKIXPolicyNode2 != null) {
                        if (i9 != this.n) {
                            try {
                                x509Certificate2 = x509Certificate;
                                ASN1Primitive extensionValue = getExtensionValue(x509Certificate2, POLICY_MAPPINGS);
                                if (extensionValue != null) {
                                    int i14 = 0;
                                    for (ASN1Sequence aSN1Sequence2 = (ASN1Sequence) extensionValue; i14 < aSN1Sequence2.size(); aSN1Sequence2 = aSN1Sequence2) {
                                        ASN1Sequence aSN1Sequence3 = (ASN1Sequence) aSN1Sequence2.getObjectAt(i14);
                                        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(1);
                                        if (RFC3280CertPathUtilities.ANY_POLICY.equals(((ASN1ObjectIdentifier) aSN1Sequence3.getObjectAt(0)).getId())) {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                        } else if (!RFC3280CertPathUtilities.ANY_POLICY.equals(aSN1ObjectIdentifier.getId())) {
                                            i14++;
                                        } else {
                                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicyMapping"), this.certPath, size);
                                        }
                                    }
                                }
                                if (extensionValue != null) {
                                    ASN1Sequence aSN1Sequence4 = (ASN1Sequence) extensionValue;
                                    HashMap hashMap = new HashMap();
                                    HashSet<String> hashSet8 = new HashSet();
                                    int i15 = 0;
                                    while (i15 < aSN1Sequence4.size()) {
                                        ASN1Sequence aSN1Sequence5 = (ASN1Sequence) aSN1Sequence4.getObjectAt(i15);
                                        String id = ((ASN1ObjectIdentifier) aSN1Sequence5.getObjectAt(0)).getId();
                                        String id2 = ((ASN1ObjectIdentifier) aSN1Sequence5.getObjectAt(1)).getId();
                                        if (!hashMap.containsKey(id)) {
                                            HashSet hashSet9 = new HashSet();
                                            hashSet9.add(id2);
                                            hashMap.put(id, hashSet9);
                                            hashSet8.add(id);
                                        } else {
                                            ((Set) hashMap.get(id)).add(id2);
                                        }
                                        i15++;
                                        aSN1Sequence4 = aSN1Sequence4;
                                        hashSet5 = hashSet5;
                                    }
                                    hashSet = hashSet5;
                                    for (String str2 : hashSet8) {
                                        if (i8 > 0) {
                                            try {
                                                prepareNextCertB1(i9, arrayListArr, str2, hashMap, x509Certificate2);
                                            } catch (AnnotatedException e3) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyExtError"), e3, this.certPath, size);
                                            } catch (CertPathValidatorException e4) {
                                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyQualifierError"), e4, this.certPath, size);
                                            }
                                        } else if (i8 <= 0) {
                                            pKIXPolicyNode2 = prepareNextCertB2(i9, arrayListArr, str2, pKIXPolicyNode2);
                                        }
                                    }
                                } else {
                                    hashSet = hashSet5;
                                }
                                if (!isSelfIssued(x509Certificate2)) {
                                    if (i != 0) {
                                        i--;
                                    }
                                    if (i8 != 0) {
                                        i8--;
                                    }
                                    if (i7 != 0) {
                                        i7--;
                                    }
                                }
                                try {
                                    ASN1Sequence aSN1Sequence6 = (ASN1Sequence) getExtensionValue(x509Certificate2, POLICY_CONSTRAINTS);
                                    if (aSN1Sequence6 != null) {
                                        Enumeration objects3 = aSN1Sequence6.getObjects();
                                        while (objects3.hasMoreElements()) {
                                            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects3.nextElement();
                                            switch (aSN1TaggedObject.getTagNo()) {
                                                case 0:
                                                    int intValue = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue();
                                                    if (intValue < i) {
                                                        i = intValue;
                                                        break;
                                                    } else {
                                                        break;
                                                    }
                                                case 1:
                                                    int intValue2 = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue().intValue();
                                                    if (intValue2 < i8) {
                                                        i8 = intValue2;
                                                        break;
                                                    } else {
                                                        break;
                                                    }
                                            }
                                        }
                                    }
                                    try {
                                        ASN1Integer aSN1Integer = (ASN1Integer) getExtensionValue(x509Certificate2, INHIBIT_ANY_POLICY);
                                        if (aSN1Integer == null || (i5 = aSN1Integer.getValue().intValue()) >= i7) {
                                            i5 = i7;
                                        }
                                        i7 = i5;
                                    } catch (AnnotatedException unused) {
                                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyInhibitExtError"), this.certPath, size);
                                    }
                                } catch (AnnotatedException unused2) {
                                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
                                }
                            } catch (AnnotatedException e5) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyMapExtError"), e5, this.certPath, size);
                            }
                        } else {
                            hashSet = hashSet5;
                            x509Certificate2 = x509Certificate;
                        }
                        pKIXPolicyNode5 = pKIXPolicyNode2;
                        size--;
                        x509Certificate4 = x509Certificate2;
                        hashSet5 = hashSet;
                    } else {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.noValidPolicyTree"));
                    }
                } catch (AnnotatedException e6) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyExtError"), e6, this.certPath, size);
                }
            }
            if (!isSelfIssued(x509Certificate4) && i > 0) {
                i--;
            }
            try {
                ASN1Sequence aSN1Sequence7 = (ASN1Sequence) getExtensionValue(x509Certificate4, POLICY_CONSTRAINTS);
                if (aSN1Sequence7 != null) {
                    Enumeration objects4 = aSN1Sequence7.getObjects();
                    i4 = i;
                    while (objects4.hasMoreElements()) {
                        ASN1TaggedObject aSN1TaggedObject2 = (ASN1TaggedObject) objects4.nextElement();
                        if (aSN1TaggedObject2.getTagNo() == 0) {
                            if (ASN1Integer.getInstance(aSN1TaggedObject2, false).getValue().intValue() == 0) {
                                i4 = 0;
                            }
                        }
                    }
                } else {
                    i4 = i;
                }
                if (pKIXPolicyNode5 == null) {
                    if (!this.pkixParams.isExplicitPolicyRequired()) {
                        pKIXPolicyNode = null;
                    } else {
                        throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                    }
                } else if (isAnyPolicy(initialPolicies)) {
                    if (this.pkixParams.isExplicitPolicyRequired()) {
                        if (!hashSet5.isEmpty()) {
                            HashSet<PKIXPolicyNode> hashSet10 = new HashSet();
                            for (ArrayList arrayList4 : arrayListArr) {
                                for (int i16 = 0; i16 < arrayList4.size(); i16++) {
                                    PKIXPolicyNode pKIXPolicyNode10 = (PKIXPolicyNode) arrayList4.get(i16);
                                    if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode10.getValidPolicy())) {
                                        Iterator children2 = pKIXPolicyNode10.getChildren();
                                        while (children2.hasNext()) {
                                            hashSet10.add(children2.next());
                                        }
                                    }
                                }
                            }
                            for (PKIXPolicyNode pKIXPolicyNode11 : hashSet10) {
                                hashSet5.contains(pKIXPolicyNode11.getValidPolicy());
                            }
                            if (pKIXPolicyNode5 != null) {
                                for (int i17 = this.n - 1; i17 >= 0; i17--) {
                                    ArrayList arrayList5 = arrayListArr[i17];
                                    for (int i18 = 0; i18 < arrayList5.size(); i18++) {
                                        PKIXPolicyNode pKIXPolicyNode12 = (PKIXPolicyNode) arrayList5.get(i18);
                                        if (!pKIXPolicyNode12.hasChildren()) {
                                            pKIXPolicyNode5 = removePolicyNode(pKIXPolicyNode5, arrayListArr, pKIXPolicyNode12);
                                        }
                                    }
                                }
                                pKIXPolicyNode = pKIXPolicyNode5;
                            }
                        } else {
                            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.explicitPolicy"), this.certPath, size);
                        }
                    }
                    pKIXPolicyNode = pKIXPolicyNode5;
                } else {
                    HashSet<PKIXPolicyNode> hashSet11 = new HashSet();
                    for (ArrayList arrayList6 : arrayListArr) {
                        for (int i19 = 0; i19 < arrayList6.size(); i19++) {
                            PKIXPolicyNode pKIXPolicyNode13 = (PKIXPolicyNode) arrayList6.get(i19);
                            if (RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode13.getValidPolicy())) {
                                Iterator children3 = pKIXPolicyNode13.getChildren();
                                while (children3.hasNext()) {
                                    PKIXPolicyNode pKIXPolicyNode14 = (PKIXPolicyNode) children3.next();
                                    if (!RFC3280CertPathUtilities.ANY_POLICY.equals(pKIXPolicyNode14.getValidPolicy())) {
                                        hashSet11.add(pKIXPolicyNode14);
                                    }
                                }
                            }
                        }
                    }
                    for (PKIXPolicyNode pKIXPolicyNode15 : hashSet11) {
                        if (!initialPolicies.contains(pKIXPolicyNode15.getValidPolicy())) {
                            pKIXPolicyNode5 = removePolicyNode(pKIXPolicyNode5, arrayListArr, pKIXPolicyNode15);
                        }
                    }
                    if (pKIXPolicyNode5 != null) {
                        for (int i20 = this.n - 1; i20 >= 0; i20--) {
                            ArrayList arrayList7 = arrayListArr[i20];
                            for (int i21 = 0; i21 < arrayList7.size(); i21++) {
                                PKIXPolicyNode pKIXPolicyNode16 = (PKIXPolicyNode) arrayList7.get(i21);
                                if (!pKIXPolicyNode16.hasChildren()) {
                                    pKIXPolicyNode5 = removePolicyNode(pKIXPolicyNode5, arrayListArr, pKIXPolicyNode16);
                                }
                            }
                        }
                        pKIXPolicyNode = pKIXPolicyNode5;
                    } else {
                        pKIXPolicyNode = pKIXPolicyNode5;
                    }
                }
                if (i4 <= 0 && pKIXPolicyNode == null) {
                    throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.invalidPolicy"));
                }
            } catch (AnnotatedException unused3) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.policyConstExtError"), this.certPath, size);
            }
        } catch (CertPathReviewerException e7) {
            addError(e7.getErrorMessage(), e7.getIndex());
        }
    }

    private void checkCriticalExtensions() {
        List<PKIXCertPathChecker> certPathCheckers = this.pkixParams.getCertPathCheckers();
        for (PKIXCertPathChecker pKIXCertPathChecker : certPathCheckers) {
            try {
                pKIXCertPathChecker.init(false);
            } catch (CertPathValidatorException e) {
                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.certPathCheckerError", new Object[]{e.getMessage(), e, e.getClass().getName()}), e);
            }
        }
        try {
            for (int size = this.certs.size() - 1; size >= 0; size--) {
                X509Certificate x509Certificate = (X509Certificate) this.certs.get(size);
                Set<String> criticalExtensionOIDs = x509Certificate.getCriticalExtensionOIDs();
                if (criticalExtensionOIDs != null) {
                    if (!criticalExtensionOIDs.isEmpty()) {
                        criticalExtensionOIDs.remove(KEY_USAGE);
                        criticalExtensionOIDs.remove(CERTIFICATE_POLICIES);
                        criticalExtensionOIDs.remove(POLICY_MAPPINGS);
                        criticalExtensionOIDs.remove(INHIBIT_ANY_POLICY);
                        criticalExtensionOIDs.remove(ISSUING_DISTRIBUTION_POINT);
                        criticalExtensionOIDs.remove(DELTA_CRL_INDICATOR);
                        criticalExtensionOIDs.remove(POLICY_CONSTRAINTS);
                        criticalExtensionOIDs.remove(BASIC_CONSTRAINTS);
                        criticalExtensionOIDs.remove(SUBJECT_ALTERNATIVE_NAME);
                        criticalExtensionOIDs.remove(NAME_CONSTRAINTS);
                        if (criticalExtensionOIDs.contains(QC_STATEMENT) && processQcStatements(x509Certificate, size)) {
                            criticalExtensionOIDs.remove(QC_STATEMENT);
                        }
                        for (PKIXCertPathChecker pKIXCertPathChecker2 : certPathCheckers) {
                            try {
                                pKIXCertPathChecker2.check(x509Certificate, criticalExtensionOIDs);
                            } catch (CertPathValidatorException e2) {
                                throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.criticalExtensionError", new Object[]{e2.getMessage(), e2, e2.getClass().getName()}), e2.getCause(), this.certPath, size);
                            }
                        }
                        if (!criticalExtensionOIDs.isEmpty()) {
                            Iterator<String> it = criticalExtensionOIDs.iterator();
                            while (it.hasNext()) {
                                addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.unknownCriticalExt", new Object[]{new ASN1ObjectIdentifier(it.next())}), size);
                            }
                        }
                    }
                }
            }
        } catch (CertPathReviewerException e3) {
            addError(e3.getErrorMessage(), e3.getIndex());
        }
    }

    private boolean processQcStatements(X509Certificate x509Certificate, int i) {
        ErrorBundle errorBundle;
        char c2 = 0;
        try {
            ASN1Sequence aSN1Sequence = (ASN1Sequence) getExtensionValue(x509Certificate, QC_STATEMENT);
            int i2 = 0;
            boolean z = false;
            while (i2 < aSN1Sequence.size()) {
                QCStatement instance = QCStatement.getInstance(aSN1Sequence.getObjectAt(i2));
                if (QCStatement.id_etsi_qcs_QcCompliance.equals(instance.getStatementId())) {
                    addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcEuCompliance"), i);
                } else if (!QCStatement.id_qcs_pkixQCSyntax_v1.equals(instance.getStatementId())) {
                    if (QCStatement.id_etsi_qcs_QcSSCD.equals(instance.getStatementId())) {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcSSCD"), i);
                    } else if (QCStatement.id_etsi_qcs_LimiteValue.equals(instance.getStatementId())) {
                        MonetaryValue instance2 = MonetaryValue.getInstance(instance.getStatementInfo());
                        instance2.getCurrency();
                        double doubleValue = instance2.getAmount().doubleValue() * Math.pow(10.0d, instance2.getExponent().doubleValue());
                        if (instance2.getCurrency().isAlphabetic()) {
                            Object[] objArr = new Object[3];
                            objArr[c2] = instance2.getCurrency().getAlphabetic();
                            objArr[1] = new TrustedInput(new Double(doubleValue));
                            objArr[2] = instance2;
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueAlpha", objArr);
                        } else {
                            errorBundle = new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcLimitValueNum", new Object[]{Integers.valueOf(instance2.getCurrency().getNumeric()), new TrustedInput(new Double(doubleValue)), instance2});
                        }
                        addNotification(errorBundle, i);
                    } else {
                        addNotification(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcUnknownStatement", new Object[]{instance.getStatementId(), new UntrustedInput(instance)}), i);
                        z = true;
                    }
                }
                i2++;
                c2 = 0;
            }
            return !z;
        } catch (AnnotatedException unused) {
            addError(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.QcStatementExtError"), i);
            return false;
        }
    }

    private String IPtoString(byte[] bArr) {
        try {
            return InetAddress.getByAddress(bArr).getHostAddress();
        } catch (Exception unused) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i != bArr.length; i++) {
                stringBuffer.append(Integer.toHexString(bArr[i] & 255));
                stringBuffer.append(' ');
            }
            return stringBuffer.toString();
        }
    }

    /* access modifiers changed from: protected */
    public void checkRevocation(PKIXParameters pKIXParameters, X509Certificate x509Certificate, Date date, X509Certificate x509Certificate2, PublicKey publicKey, Vector vector, Vector vector2, int i) {
        checkCRLs(pKIXParameters, x509Certificate, date, x509Certificate2, publicKey, vector, i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x02c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkCRLs(java.security.cert.PKIXParameters r18, java.security.cert.X509Certificate r19, java.util.Date r20, java.security.cert.X509Certificate r21, java.security.PublicKey r22, java.util.Vector r23, int r24) {
        /*
        // Method dump skipped, instructions count: 1206
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.x509.PKIXCertPathReviewer.checkCRLs(java.security.cert.PKIXParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.Vector, int):void");
    }

    /* access modifiers changed from: protected */
    public Vector getCRLDistUrls(CRLDistPoint cRLDistPoint) {
        DistributionPoint[] distributionPoints;
        Vector vector = new Vector();
        if (cRLDistPoint != null) {
            for (DistributionPoint distributionPoint : cRLDistPoint.getDistributionPoints()) {
                DistributionPointName distributionPoint2 = distributionPoint.getDistributionPoint();
                if (distributionPoint2.getType() == 0) {
                    GeneralName[] names = GeneralNames.getInstance(distributionPoint2.getName()).getNames();
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].getTagNo() == 6) {
                            vector.add(((DERIA5String) names[i].getName()).getString());
                        }
                    }
                }
            }
        }
        return vector;
    }

    /* access modifiers changed from: protected */
    public Vector getOCSPUrls(AuthorityInformationAccess authorityInformationAccess) {
        Vector vector = new Vector();
        if (authorityInformationAccess != null) {
            AccessDescription[] accessDescriptions = authorityInformationAccess.getAccessDescriptions();
            for (int i = 0; i < accessDescriptions.length; i++) {
                if (accessDescriptions[i].getAccessMethod().equals(AccessDescription.id_ad_ocsp)) {
                    GeneralName accessLocation = accessDescriptions[i].getAccessLocation();
                    if (accessLocation.getTagNo() == 6) {
                        vector.add(((DERIA5String) accessLocation.getName()).getString());
                    }
                }
            }
        }
        return vector;
    }

    private X509CRL getCRL(String str) {
        try {
            URL url = new URL(str);
            if (!url.getProtocol().equals("http")) {
                if (!url.getProtocol().equals("https")) {
                    return null;
                }
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                return (X509CRL) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME).generateCRL(httpURLConnection.getInputStream());
            }
            throw new Exception(httpURLConnection.getResponseMessage());
        } catch (Exception e) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.loadCrlDistPointError", new Object[]{new UntrustedInput(str), e.getMessage(), e, e.getClass().getName()}));
        }
    }

    /* access modifiers changed from: protected */
    public Collection getTrustAnchors(X509Certificate x509Certificate, Set set) {
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        X509CertSelector x509CertSelector = new X509CertSelector();
        try {
            x509CertSelector.setSubject(getEncodedIssuerPrincipal(x509Certificate).getEncoded());
            byte[] extensionValue = x509Certificate.getExtensionValue(X509Extensions.AuthorityKeyIdentifier.getId());
            if (extensionValue != null) {
                AuthorityKeyIdentifier instance = AuthorityKeyIdentifier.getInstance(ASN1Primitive.fromByteArray(((ASN1OctetString) ASN1Primitive.fromByteArray(extensionValue)).getOctets()));
                x509CertSelector.setSerialNumber(instance.getAuthorityCertSerialNumber());
                byte[] keyIdentifier = instance.getKeyIdentifier();
                if (keyIdentifier != null) {
                    x509CertSelector.setSubjectKeyIdentifier(new DEROctetString(keyIdentifier).getEncoded());
                }
            }
            while (it.hasNext()) {
                TrustAnchor trustAnchor2 = (TrustAnchor) it.next();
                if (trustAnchor2.getTrustedCert() != null) {
                    if (x509CertSelector.match(trustAnchor2.getTrustedCert())) {
                        arrayList.add(trustAnchor2);
                    }
                } else if (!(trustAnchor2.getCAName() == null || trustAnchor2.getCAPublicKey() == null || !getEncodedIssuerPrincipal(x509Certificate).equals(new X500Principal(trustAnchor2.getCAName())))) {
                    arrayList.add(trustAnchor2);
                }
            }
            return arrayList;
        } catch (IOException unused) {
            throw new CertPathReviewerException(new ErrorBundle(RESOURCE_NAME, "CertPathReviewer.trustAnchorIssuerError"));
        }
    }
}
