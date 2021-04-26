package org.spongycastle.jcajce.provider.keystore.pkcs12;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.BEROctetString;
import org.spongycastle.asn1.BEROutputStream;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DEROutputStream;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST28147Parameters;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.AuthenticatedSafe;
import org.spongycastle.asn1.pkcs.CertBag;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.EncryptedData;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.pkcs.MacData;
import org.spongycastle.asn1.pkcs.PBES2Parameters;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.Pfx;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.SafeBag;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.SubjectKeyIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12StoreParameter;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.PBKDF2KeySpec;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.BCKeyStore;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.JDKPKCS12StoreParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private ASN1ObjectIdentifier keyAlgorithm;
    private Hashtable keyCerts = new Hashtable();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private Hashtable localIds = new Hashtable();
    protected SecureRandom random = new SecureRandom();

    /* access modifiers changed from: private */
    public class CertId {
        byte[] id;

        CertId(PublicKey publicKey) {
            this.id = PKCS12KeyStoreSpi.this.createSubjectKeyId(publicKey).getKeyIdentifier();
        }

        CertId(byte[] bArr) {
            this.id = bArr;
        }

        public int hashCode() {
            return Arrays.hashCode(this.id);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof CertId)) {
                return false;
            }
            return Arrays.areEqual(this.id, ((CertId) obj).id);
        }
    }

    public PKCS12KeyStoreSpi(Provider provider, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        this.keyAlgorithm = aSN1ObjectIdentifier;
        this.certAlgorithm = aSN1ObjectIdentifier2;
        if (provider != null) {
            try {
                this.certFact = CertificateFactory.getInstance("X.509", provider);
            } catch (Exception e) {
                throw new IllegalArgumentException("can't create cert factory - " + e.toString());
            }
        } else {
            this.certFact = CertificateFactory.getInstance("X.509");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SubjectKeyIdentifier createSubjectKeyId(PublicKey publicKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded())));
        } catch (Exception unused) {
            throw new RuntimeException("error creating key");
        }
    }

    private static byte[] getDigest(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        Digest createSHA1 = DigestFactory.createSHA1();
        byte[] bArr = new byte[createSHA1.getDigestSize()];
        byte[] bytes = subjectPublicKeyInfo.getPublicKeyData().getBytes();
        createSHA1.update(bytes, 0, bytes.length);
        createSHA1.doFinal(bArr, 0);
        return bArr;
    }

    @Override // org.spongycastle.jce.interfaces.BCKeyStore
    public void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration engineAliases() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.keys();
    }

    public boolean engineContainsAlias(String str) {
        return (this.certs.get(str) == null && this.keys.get(str) == null) ? false : true;
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) {
        Key key = (Key) this.keys.remove(str);
        Certificate certificate = (Certificate) this.certs.remove(str);
        if (certificate != null) {
            this.chainCerts.remove(new CertId(certificate.getPublicKey()));
        }
        if (key != null) {
            String str2 = (String) this.localIds.remove(str);
            if (str2 != null) {
                certificate = (Certificate) this.keyCerts.remove(str2);
            }
            if (certificate != null) {
                this.chainCerts.remove(new CertId(certificate.getPublicKey()));
            }
        }
    }

    public Certificate engineGetCertificate(String str) {
        if (str != null) {
            Certificate certificate = (Certificate) this.certs.get(str);
            if (certificate != null) {
                return certificate;
            }
            String str2 = (String) this.localIds.get(str);
            if (str2 != null) {
                return (Certificate) this.keyCerts.get(str2);
            }
            return (Certificate) this.keyCerts.get(str);
        }
        throw new IllegalArgumentException("null alias passed to getCertificate.");
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        Enumeration elements = this.certs.elements();
        Enumeration keys2 = this.certs.keys();
        while (elements.hasMoreElements()) {
            String str = (String) keys2.nextElement();
            if (((Certificate) elements.nextElement()).equals(certificate)) {
                return str;
            }
        }
        Enumeration elements2 = this.keyCerts.elements();
        Enumeration keys3 = this.keyCerts.keys();
        while (elements2.hasMoreElements()) {
            String str2 = (String) keys3.nextElement();
            if (((Certificate) elements2.nextElement()).equals(certificate)) {
                return str2;
            }
        }
        return null;
    }

    public Certificate[] engineGetCertificateChain(String str) {
        Certificate engineGetCertificate;
        X509Certificate x509Certificate;
        if (str == null) {
            throw new IllegalArgumentException("null alias passed to getCertificateChain.");
        } else if (!engineIsKeyEntry(str) || (engineGetCertificate = engineGetCertificate(str)) == null) {
            return null;
        } else {
            Vector vector = new Vector();
            while (engineGetCertificate != null) {
                X509Certificate x509Certificate2 = (X509Certificate) engineGetCertificate;
                byte[] extensionValue = x509Certificate2.getExtensionValue(Extension.authorityKeyIdentifier.getId());
                if (extensionValue != null) {
                    try {
                        AuthorityKeyIdentifier instance = AuthorityKeyIdentifier.getInstance(new ASN1InputStream(((ASN1OctetString) new ASN1InputStream(extensionValue).readObject()).getOctets()).readObject());
                        x509Certificate = instance.getKeyIdentifier() != null ? (Certificate) this.chainCerts.get(new CertId(instance.getKeyIdentifier())) : null;
                    } catch (IOException e) {
                        throw new RuntimeException(e.toString());
                    }
                } else {
                    x509Certificate = null;
                }
                if (x509Certificate == null) {
                    Principal issuerDN = x509Certificate2.getIssuerDN();
                    if (!issuerDN.equals(x509Certificate2.getSubjectDN())) {
                        Enumeration keys2 = this.chainCerts.keys();
                        while (true) {
                            if (!keys2.hasMoreElements()) {
                                break;
                            }
                            X509Certificate x509Certificate3 = (X509Certificate) this.chainCerts.get(keys2.nextElement());
                            if (x509Certificate3.getSubjectDN().equals(issuerDN)) {
                                try {
                                    x509Certificate2.verify(x509Certificate3.getPublicKey());
                                    x509Certificate = x509Certificate3;
                                    break;
                                } catch (Exception unused) {
                                    continue;
                                }
                            }
                        }
                    }
                }
                if (vector.contains(engineGetCertificate)) {
                    engineGetCertificate = null;
                } else {
                    vector.addElement(engineGetCertificate);
                    engineGetCertificate = x509Certificate != engineGetCertificate ? x509Certificate : null;
                }
            }
            Certificate[] certificateArr = new Certificate[vector.size()];
            for (int i = 0; i != certificateArr.length; i++) {
                certificateArr[i] = (Certificate) vector.elementAt(i);
            }
            return certificateArr;
        }
    }

    public Date engineGetCreationDate(String str) {
        if (str == null) {
            throw new NullPointerException("alias == null");
        } else if (this.keys.get(str) == null && this.certs.get(str) == null) {
            return null;
        } else {
            return new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) {
        if (str != null) {
            return (Key) this.keys.get(str);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    public boolean engineIsCertificateEntry(String str) {
        return this.certs.get(str) != null && this.keys.get(str) == null;
    }

    public boolean engineIsKeyEntry(String str) {
        return this.keys.get(str) != null;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) {
        if (this.keys.get(str) == null) {
            this.certs.put(str, certificate);
            this.chainCerts.put(new CertId(certificate.getPublicKey()), certificate);
            return;
        }
        throw new KeyStoreException("There is a key entry with the name " + str + ".");
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
        throw new RuntimeException("operation not supported");
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
        boolean z = key instanceof PrivateKey;
        if (!z) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!z || certificateArr != null) {
            if (this.keys.get(str) != null) {
                engineDeleteEntry(str);
            }
            this.keys.put(str, key);
            if (certificateArr != null) {
                this.certs.put(str, certificateArr[0]);
                for (int i = 0; i != certificateArr.length; i++) {
                    this.chainCerts.put(new CertId(certificateArr[i].getPublicKey()), certificateArr[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public int engineSize() {
        Hashtable hashtable = new Hashtable();
        Enumeration keys2 = this.certs.keys();
        while (keys2.hasMoreElements()) {
            hashtable.put(keys2.nextElement(), "cert");
        }
        Enumeration keys3 = this.keys.keys();
        while (keys3.hasMoreElements()) {
            String str = (String) keys3.nextElement();
            if (hashtable.get(str) == null) {
                hashtable.put(str, "key");
            }
        }
        return hashtable.size();
    }

    /* access modifiers changed from: protected */
    public PrivateKey unwrapKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, char[] cArr, boolean z) {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        try {
            if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), instance.getIterations().intValue());
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(4, new PKCS12Key(cArr, z), pBEParameterSpec);
                return (PrivateKey) createCipher.unwrap(bArr, "", 2);
            } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                return (PrivateKey) createCipher(4, cArr, algorithmIdentifier).unwrap(bArr, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] wrapKey(String str, Key key, PKCS12PBEParams pKCS12PBEParams, char[] cArr) {
        PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr);
        try {
            SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(str);
            PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
            Cipher createCipher = this.helper.createCipher(str);
            createCipher.init(3, createSecretKeyFactory.generateSecret(pBEKeySpec), pBEParameterSpec);
            return createCipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] cryptData(boolean z, AlgorithmIdentifier algorithmIdentifier, char[] cArr, boolean z2, byte[] bArr) {
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        int i = z ? 1 : 2;
        if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams instance = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
            new PBEKeySpec(cArr);
            try {
                PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(instance.getIV(), instance.getIterations().intValue());
                PKCS12Key pKCS12Key = new PKCS12Key(cArr, z2);
                Cipher createCipher = this.helper.createCipher(algorithm.getId());
                createCipher.init(i, pKCS12Key, pBEParameterSpec);
                return createCipher.doFinal(bArr);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
            try {
                return createCipher(i, cArr, algorithmIdentifier).doFinal(bArr);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: " + algorithm);
        }
    }

    private Cipher createCipher(int i, char[] cArr, AlgorithmIdentifier algorithmIdentifier) {
        SecretKey secretKey;
        PBES2Parameters instance = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
        PBKDF2Params instance2 = PBKDF2Params.getInstance(instance.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier instance3 = AlgorithmIdentifier.getInstance(instance.getEncryptionScheme());
        SecretKeyFactory createSecretKeyFactory = this.helper.createSecretKeyFactory(instance.getKeyDerivationFunc().getAlgorithm().getId());
        if (instance2.isDefaultPrf()) {
            secretKey = createSecretKeyFactory.generateSecret(new PBEKeySpec(cArr, instance2.getSalt(), instance2.getIterationCount().intValue(), keySizeProvider.getKeySize(instance3)));
        } else {
            secretKey = createSecretKeyFactory.generateSecret(new PBKDF2KeySpec(cArr, instance2.getSalt(), instance2.getIterationCount().intValue(), keySizeProvider.getKeySize(instance3), instance2.getPrf()));
        }
        Cipher instance4 = Cipher.getInstance(instance.getEncryptionScheme().getAlgorithm().getId());
        AlgorithmIdentifier.getInstance(instance.getEncryptionScheme());
        ASN1Encodable parameters = instance.getEncryptionScheme().getParameters();
        if (parameters instanceof ASN1OctetString) {
            instance4.init(i, secretKey, new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets()));
        } else {
            GOST28147Parameters instance5 = GOST28147Parameters.getInstance(parameters);
            instance4.init(i, secretKey, new GOST28147ParameterSpec(instance5.getEncryptionParamSet(), instance5.getIV()));
        }
        return instance4;
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) {
        boolean z;
        boolean z2;
        ASN1OctetString aSN1OctetString;
        String str;
        boolean z3;
        boolean z4;
        ASN1Primitive aSN1Primitive;
        String str2;
        String str3;
        ASN1Primitive aSN1Primitive2;
        if (inputStream != null) {
            if (cArr != null) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                bufferedInputStream.mark(10);
                if (bufferedInputStream.read() == 48) {
                    bufferedInputStream.reset();
                    Pfx instance = Pfx.getInstance((ASN1Sequence) new ASN1InputStream(bufferedInputStream).readObject());
                    ContentInfo authSafe = instance.getAuthSafe();
                    Vector vector = new Vector();
                    int i = 1;
                    int i2 = 0;
                    if (instance.getMacData() != null) {
                        MacData macData = instance.getMacData();
                        DigestInfo mac = macData.getMac();
                        AlgorithmIdentifier algorithmId = mac.getAlgorithmId();
                        byte[] salt = macData.getSalt();
                        int intValue = macData.getIterationCount().intValue();
                        byte[] octets = ((ASN1OctetString) authSafe.getContent()).getOctets();
                        try {
                            byte[] calculatePbeMac = calculatePbeMac(algorithmId.getAlgorithm(), salt, intValue, cArr, false, octets);
                            byte[] digest = mac.getDigest();
                            if (Arrays.constantTimeAreEqual(calculatePbeMac, digest)) {
                                z = false;
                            } else if (cArr.length > 0) {
                                throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                            } else if (Arrays.constantTimeAreEqual(calculatePbeMac(algorithmId.getAlgorithm(), salt, intValue, cArr, true, octets), digest)) {
                                z = true;
                            } else {
                                throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                            }
                        } catch (IOException e) {
                            throw e;
                        } catch (Exception e2) {
                            throw new IOException("error constructing MAC: " + e2.toString());
                        }
                    } else {
                        z = false;
                    }
                    ASN1OctetString aSN1OctetString2 = null;
                    this.keys = new IgnoresCaseHashtable();
                    this.localIds = new Hashtable();
                    if (authSafe.getContentType().equals(data)) {
                        ContentInfo[] contentInfo = AuthenticatedSafe.getInstance(new ASN1InputStream(((ASN1OctetString) authSafe.getContent()).getOctets()).readObject()).getContentInfo();
                        int i3 = 0;
                        z2 = false;
                        while (i3 != contentInfo.length) {
                            if (contentInfo[i3].getContentType().equals(data)) {
                                ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(((ASN1OctetString) contentInfo[i3].getContent()).getOctets()).readObject();
                                int i4 = 0;
                                while (i4 != aSN1Sequence.size()) {
                                    SafeBag instance2 = SafeBag.getInstance(aSN1Sequence.getObjectAt(i4));
                                    if (instance2.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                        EncryptedPrivateKeyInfo instance3 = EncryptedPrivateKeyInfo.getInstance(instance2.getBagValue());
                                        PrivateKey unwrapKey = unwrapKey(instance3.getEncryptionAlgorithm(), instance3.getEncryptedData(), cArr, z);
                                        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) unwrapKey;
                                        if (instance2.getBagAttributes() != null) {
                                            Enumeration objects = instance2.getBagAttributes().getObjects();
                                            str3 = aSN1OctetString2;
                                            str2 = str3;
                                            while (objects.hasMoreElements()) {
                                                ASN1Sequence aSN1Sequence2 = (ASN1Sequence) objects.nextElement();
                                                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence2.getObjectAt(i2);
                                                ASN1Set aSN1Set = (ASN1Set) aSN1Sequence2.getObjectAt(i);
                                                if (aSN1Set.size() > 0) {
                                                    aSN1Primitive2 = (ASN1Primitive) aSN1Set.getObjectAt(0);
                                                    ASN1Encodable bagAttribute = pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier);
                                                    if (bagAttribute == null) {
                                                        pKCS12BagAttributeCarrier.setBagAttribute(aSN1ObjectIdentifier, aSN1Primitive2);
                                                    } else if (!bagAttribute.toASN1Primitive().equals(aSN1Primitive2)) {
                                                        throw new IOException("attempt to add existing attribute with different value");
                                                    }
                                                } else {
                                                    aSN1Primitive2 = null;
                                                }
                                                if (aSN1ObjectIdentifier.equals(pkcs_9_at_friendlyName)) {
                                                    str3 = ((DERBMPString) aSN1Primitive2).getString();
                                                    this.keys.put(str3, unwrapKey);
                                                } else if (aSN1ObjectIdentifier.equals(pkcs_9_at_localKeyId)) {
                                                    str2 = (ASN1OctetString) aSN1Primitive2;
                                                }
                                                i = 1;
                                                i2 = 0;
                                            }
                                        } else {
                                            str3 = null;
                                            str2 = null;
                                        }
                                        if (str2 != null) {
                                            String str4 = new String(Hex.encode(str2.getOctets()));
                                            if (str3 == null) {
                                                this.keys.put(str4, unwrapKey);
                                            } else {
                                                this.localIds.put(str3, str4);
                                            }
                                        } else {
                                            this.keys.put("unmarked", unwrapKey);
                                            z2 = true;
                                        }
                                    } else if (instance2.getBagId().equals(certBag)) {
                                        vector.addElement(instance2);
                                    } else {
                                        System.out.println("extra in data " + instance2.getBagId());
                                        System.out.println(ASN1Dump.dumpAsString(instance2));
                                    }
                                    i4++;
                                    aSN1OctetString2 = null;
                                    i = 1;
                                    i2 = 0;
                                }
                                z3 = z;
                            } else if (contentInfo[i3].getContentType().equals(encryptedData)) {
                                EncryptedData instance4 = EncryptedData.getInstance(contentInfo[i3].getContent());
                                ASN1Sequence aSN1Sequence3 = (ASN1Sequence) ASN1Primitive.fromByteArray(cryptData(false, instance4.getEncryptionAlgorithm(), cArr, z, instance4.getContent().getOctets()));
                                int i5 = 0;
                                while (i5 != aSN1Sequence3.size()) {
                                    SafeBag instance5 = SafeBag.getInstance(aSN1Sequence3.getObjectAt(i5));
                                    if (instance5.getBagId().equals(certBag)) {
                                        vector.addElement(instance5);
                                        z4 = z;
                                    } else if (instance5.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                        EncryptedPrivateKeyInfo instance6 = EncryptedPrivateKeyInfo.getInstance(instance5.getBagValue());
                                        PrivateKey unwrapKey2 = unwrapKey(instance6.getEncryptionAlgorithm(), instance6.getEncryptedData(), cArr, z);
                                        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier2 = (PKCS12BagAttributeCarrier) unwrapKey2;
                                        Enumeration objects2 = instance5.getBagAttributes().getObjects();
                                        ASN1OctetString aSN1OctetString3 = null;
                                        String str5 = null;
                                        while (objects2.hasMoreElements()) {
                                            ASN1Sequence aSN1Sequence4 = (ASN1Sequence) objects2.nextElement();
                                            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) aSN1Sequence4.getObjectAt(0);
                                            ASN1Set aSN1Set2 = (ASN1Set) aSN1Sequence4.getObjectAt(1);
                                            if (aSN1Set2.size() > 0) {
                                                aSN1Primitive = (ASN1Primitive) aSN1Set2.getObjectAt(0);
                                                ASN1Encodable bagAttribute2 = pKCS12BagAttributeCarrier2.getBagAttribute(aSN1ObjectIdentifier2);
                                                if (bagAttribute2 == null) {
                                                    pKCS12BagAttributeCarrier2.setBagAttribute(aSN1ObjectIdentifier2, aSN1Primitive);
                                                } else if (!bagAttribute2.toASN1Primitive().equals(aSN1Primitive)) {
                                                    throw new IOException("attempt to add existing attribute with different value");
                                                }
                                            } else {
                                                aSN1Primitive = null;
                                            }
                                            if (aSN1ObjectIdentifier2.equals(pkcs_9_at_friendlyName)) {
                                                String string = ((DERBMPString) aSN1Primitive).getString();
                                                this.keys.put(string, unwrapKey2);
                                                str5 = string;
                                            } else if (aSN1ObjectIdentifier2.equals(pkcs_9_at_localKeyId)) {
                                                aSN1OctetString3 = (ASN1OctetString) aSN1Primitive;
                                            }
                                            z = z;
                                        }
                                        z4 = z;
                                        String str6 = new String(Hex.encode(aSN1OctetString3.getOctets()));
                                        if (str5 == null) {
                                            this.keys.put(str6, unwrapKey2);
                                        } else {
                                            this.localIds.put(str5, str6);
                                        }
                                    } else {
                                        z4 = z;
                                        if (instance5.getBagId().equals(keyBag)) {
                                            PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(instance5.getBagValue()));
                                            PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier3 = (PKCS12BagAttributeCarrier) privateKey;
                                            Enumeration objects3 = instance5.getBagAttributes().getObjects();
                                            ASN1OctetString aSN1OctetString4 = null;
                                            String str7 = null;
                                            while (objects3.hasMoreElements()) {
                                                ASN1Sequence instance7 = ASN1Sequence.getInstance(objects3.nextElement());
                                                ASN1ObjectIdentifier instance8 = ASN1ObjectIdentifier.getInstance(instance7.getObjectAt(0));
                                                ASN1Set instance9 = ASN1Set.getInstance(instance7.getObjectAt(1));
                                                if (instance9.size() > 0) {
                                                    ASN1Primitive aSN1Primitive3 = (ASN1Primitive) instance9.getObjectAt(0);
                                                    ASN1Encodable bagAttribute3 = pKCS12BagAttributeCarrier3.getBagAttribute(instance8);
                                                    if (bagAttribute3 == null) {
                                                        pKCS12BagAttributeCarrier3.setBagAttribute(instance8, aSN1Primitive3);
                                                    } else if (!bagAttribute3.toASN1Primitive().equals(aSN1Primitive3)) {
                                                        throw new IOException("attempt to add existing attribute with different value");
                                                    }
                                                    if (instance8.equals(pkcs_9_at_friendlyName)) {
                                                        str7 = ((DERBMPString) aSN1Primitive3).getString();
                                                        this.keys.put(str7, privateKey);
                                                    } else if (instance8.equals(pkcs_9_at_localKeyId)) {
                                                        aSN1OctetString4 = (ASN1OctetString) aSN1Primitive3;
                                                    }
                                                }
                                            }
                                            String str8 = new String(Hex.encode(aSN1OctetString4.getOctets()));
                                            if (str7 == null) {
                                                this.keys.put(str8, privateKey);
                                            } else {
                                                this.localIds.put(str7, str8);
                                            }
                                        } else {
                                            System.out.println("extra in encryptedData " + instance5.getBagId());
                                            System.out.println(ASN1Dump.dumpAsString(instance5));
                                        }
                                    }
                                    i5++;
                                    z = z4;
                                }
                                z3 = z;
                            } else {
                                z3 = z;
                                System.out.println("extra " + contentInfo[i3].getContentType().getId());
                                System.out.println("extra " + ASN1Dump.dumpAsString(contentInfo[i3].getContent()));
                            }
                            i3++;
                            z = z3;
                            aSN1OctetString2 = null;
                            i = 1;
                            i2 = 0;
                        }
                    } else {
                        z2 = false;
                    }
                    this.certs = new IgnoresCaseHashtable();
                    this.chainCerts = new Hashtable();
                    this.keyCerts = new Hashtable();
                    for (int i6 = 0; i6 != vector.size(); i6++) {
                        SafeBag safeBag = (SafeBag) vector.elementAt(i6);
                        CertBag instance10 = CertBag.getInstance(safeBag.getBagValue());
                        if (instance10.getCertId().equals(x509Certificate)) {
                            try {
                                Certificate generateCertificate = this.certFact.generateCertificate(new ByteArrayInputStream(((ASN1OctetString) instance10.getCertValue()).getOctets()));
                                if (safeBag.getBagAttributes() != null) {
                                    Enumeration objects4 = safeBag.getBagAttributes().getObjects();
                                    str = null;
                                    aSN1OctetString = null;
                                    while (objects4.hasMoreElements()) {
                                        ASN1Sequence instance11 = ASN1Sequence.getInstance(objects4.nextElement());
                                        ASN1ObjectIdentifier instance12 = ASN1ObjectIdentifier.getInstance(instance11.getObjectAt(0));
                                        ASN1Set instance13 = ASN1Set.getInstance(instance11.getObjectAt(1));
                                        if (instance13.size() > 0) {
                                            ASN1Primitive aSN1Primitive4 = (ASN1Primitive) instance13.getObjectAt(0);
                                            if (generateCertificate instanceof PKCS12BagAttributeCarrier) {
                                                PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier4 = (PKCS12BagAttributeCarrier) generateCertificate;
                                                ASN1Encodable bagAttribute4 = pKCS12BagAttributeCarrier4.getBagAttribute(instance12);
                                                if (bagAttribute4 == null) {
                                                    pKCS12BagAttributeCarrier4.setBagAttribute(instance12, aSN1Primitive4);
                                                } else if (!bagAttribute4.toASN1Primitive().equals(aSN1Primitive4)) {
                                                    throw new IOException("attempt to add existing attribute with different value");
                                                }
                                            }
                                            if (instance12.equals(pkcs_9_at_friendlyName)) {
                                                str = ((DERBMPString) aSN1Primitive4).getString();
                                            } else if (instance12.equals(pkcs_9_at_localKeyId)) {
                                                aSN1OctetString = (ASN1OctetString) aSN1Primitive4;
                                            }
                                        }
                                    }
                                } else {
                                    str = null;
                                    aSN1OctetString = null;
                                }
                                this.chainCerts.put(new CertId(generateCertificate.getPublicKey()), generateCertificate);
                                if (!z2) {
                                    if (aSN1OctetString != null) {
                                        this.keyCerts.put(new String(Hex.encode(aSN1OctetString.getOctets())), generateCertificate);
                                    }
                                    if (str != null) {
                                        this.certs.put(str, generateCertificate);
                                    }
                                } else if (this.keyCerts.isEmpty()) {
                                    String str9 = new String(Hex.encode(createSubjectKeyId(generateCertificate.getPublicKey()).getKeyIdentifier()));
                                    this.keyCerts.put(str9, generateCertificate);
                                    IgnoresCaseHashtable ignoresCaseHashtable = this.keys;
                                    ignoresCaseHashtable.put(str9, ignoresCaseHashtable.remove("unmarked"));
                                }
                            } catch (Exception e3) {
                                throw new RuntimeException(e3.toString());
                            }
                        } else {
                            throw new RuntimeException("Unsupported certificate type: " + instance10.getCertId());
                        }
                    }
                    return;
                }
                throw new IOException("stream does not represent a PKCS12 key store");
            }
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(KeyStore.LoadStoreParameter loadStoreParameter) {
        PKCS12StoreParameter pKCS12StoreParameter;
        char[] cArr;
        if (loadStoreParameter != null) {
            boolean z = loadStoreParameter instanceof PKCS12StoreParameter;
            if (z || (loadStoreParameter instanceof JDKPKCS12StoreParameter)) {
                if (z) {
                    pKCS12StoreParameter = (PKCS12StoreParameter) loadStoreParameter;
                } else {
                    JDKPKCS12StoreParameter jDKPKCS12StoreParameter = (JDKPKCS12StoreParameter) loadStoreParameter;
                    pKCS12StoreParameter = new PKCS12StoreParameter(jDKPKCS12StoreParameter.getOutputStream(), loadStoreParameter.getProtectionParameter(), jDKPKCS12StoreParameter.isUseDEREncoding());
                }
                KeyStore.ProtectionParameter protectionParameter = loadStoreParameter.getProtectionParameter();
                if (protectionParameter == null) {
                    cArr = null;
                } else if (protectionParameter instanceof KeyStore.PasswordProtection) {
                    cArr = ((KeyStore.PasswordProtection) protectionParameter).getPassword();
                } else {
                    throw new IllegalArgumentException("No support for protection parameter of type " + protectionParameter.getClass().getName());
                }
                doStore(pKCS12StoreParameter.getOutputStream(), cArr, pKCS12StoreParameter.isForDEREncoding());
                return;
            }
            throw new IllegalArgumentException("No support for 'param' of type " + loadStoreParameter.getClass().getName());
        }
        throw new IllegalArgumentException("'param' arg cannot be null");
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) {
        doStore(outputStream, cArr, false);
    }

    private void doStore(OutputStream outputStream, char[] cArr, boolean z) {
        DEROutputStream dEROutputStream;
        DEROutputStream dEROutputStream2;
        Enumeration enumeration;
        boolean z2;
        Enumeration enumeration2;
        boolean z3;
        boolean z4;
        if (cArr != null) {
            ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
            Enumeration keys2 = this.keys.keys();
            while (keys2.hasMoreElements()) {
                byte[] bArr = new byte[20];
                this.random.nextBytes(bArr);
                String str = (String) keys2.nextElement();
                PrivateKey privateKey = (PrivateKey) this.keys.get(str);
                PKCS12PBEParams pKCS12PBEParams = new PKCS12PBEParams(bArr, 1024);
                EncryptedPrivateKeyInfo encryptedPrivateKeyInfo = new EncryptedPrivateKeyInfo(new AlgorithmIdentifier(this.keyAlgorithm, pKCS12PBEParams.toASN1Primitive()), wrapKey(this.keyAlgorithm.getId(), privateKey, pKCS12PBEParams, cArr));
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                if (privateKey instanceof PKCS12BagAttributeCarrier) {
                    PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier = (PKCS12BagAttributeCarrier) privateKey;
                    DERBMPString dERBMPString = (DERBMPString) pKCS12BagAttributeCarrier.getBagAttribute(pkcs_9_at_friendlyName);
                    if (dERBMPString == null || !dERBMPString.getString().equals(str)) {
                        pKCS12BagAttributeCarrier.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(str));
                    }
                    if (pKCS12BagAttributeCarrier.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                        pKCS12BagAttributeCarrier.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(engineGetCertificate(str).getPublicKey()));
                    }
                    Enumeration bagAttributeKeys = pKCS12BagAttributeCarrier.getBagAttributeKeys();
                    z4 = false;
                    while (bagAttributeKeys.hasMoreElements()) {
                        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) bagAttributeKeys.nextElement();
                        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
                        aSN1EncodableVector3.add(aSN1ObjectIdentifier);
                        aSN1EncodableVector3.add(new DERSet(pKCS12BagAttributeCarrier.getBagAttribute(aSN1ObjectIdentifier)));
                        aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector3));
                        z4 = true;
                    }
                } else {
                    z4 = false;
                }
                if (!z4) {
                    ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
                    Certificate engineGetCertificate = engineGetCertificate(str);
                    aSN1EncodableVector4.add(pkcs_9_at_localKeyId);
                    aSN1EncodableVector4.add(new DERSet(createSubjectKeyId(engineGetCertificate.getPublicKey())));
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector4));
                    ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
                    aSN1EncodableVector5.add(pkcs_9_at_friendlyName);
                    aSN1EncodableVector5.add(new DERSet(new DERBMPString(str)));
                    aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector5));
                }
                aSN1EncodableVector.add(new SafeBag(pkcs8ShroudedKeyBag, encryptedPrivateKeyInfo.toASN1Primitive(), new DERSet(aSN1EncodableVector2)));
            }
            BEROctetString bEROctetString = new BEROctetString(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
            byte[] bArr2 = new byte[20];
            this.random.nextBytes(bArr2);
            ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
            AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(this.certAlgorithm, new PKCS12PBEParams(bArr2, 1024).toASN1Primitive());
            Hashtable hashtable = new Hashtable();
            Enumeration keys3 = this.keys.keys();
            while (keys3.hasMoreElements()) {
                try {
                    String str2 = (String) keys3.nextElement();
                    Certificate engineGetCertificate2 = engineGetCertificate(str2);
                    CertBag certBag = new CertBag(x509Certificate, new DEROctetString(engineGetCertificate2.getEncoded()));
                    ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
                    if (engineGetCertificate2 instanceof PKCS12BagAttributeCarrier) {
                        PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier2 = (PKCS12BagAttributeCarrier) engineGetCertificate2;
                        DERBMPString dERBMPString2 = (DERBMPString) pKCS12BagAttributeCarrier2.getBagAttribute(pkcs_9_at_friendlyName);
                        if (dERBMPString2 == null || !dERBMPString2.getString().equals(str2)) {
                            pKCS12BagAttributeCarrier2.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(str2));
                        }
                        if (pKCS12BagAttributeCarrier2.getBagAttribute(pkcs_9_at_localKeyId) == null) {
                            pKCS12BagAttributeCarrier2.setBagAttribute(pkcs_9_at_localKeyId, createSubjectKeyId(engineGetCertificate2.getPublicKey()));
                        }
                        Enumeration bagAttributeKeys2 = pKCS12BagAttributeCarrier2.getBagAttributeKeys();
                        z3 = false;
                        while (bagAttributeKeys2.hasMoreElements()) {
                            ASN1ObjectIdentifier aSN1ObjectIdentifier2 = (ASN1ObjectIdentifier) bagAttributeKeys2.nextElement();
                            ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
                            aSN1EncodableVector8.add(aSN1ObjectIdentifier2);
                            aSN1EncodableVector8.add(new DERSet(pKCS12BagAttributeCarrier2.getBagAttribute(aSN1ObjectIdentifier2)));
                            aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector8));
                            keys3 = keys3;
                            z3 = true;
                        }
                        enumeration2 = keys3;
                    } else {
                        enumeration2 = keys3;
                        z3 = false;
                    }
                    if (!z3) {
                        ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
                        aSN1EncodableVector9.add(pkcs_9_at_localKeyId);
                        aSN1EncodableVector9.add(new DERSet(createSubjectKeyId(engineGetCertificate2.getPublicKey())));
                        aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector9));
                        ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
                        aSN1EncodableVector10.add(pkcs_9_at_friendlyName);
                        aSN1EncodableVector10.add(new DERSet(new DERBMPString(str2)));
                        aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector10));
                    }
                    aSN1EncodableVector6.add(new SafeBag(certBag, certBag.toASN1Primitive(), new DERSet(aSN1EncodableVector7)));
                    hashtable.put(engineGetCertificate2, engineGetCertificate2);
                    keys3 = enumeration2;
                } catch (CertificateEncodingException e) {
                    throw new IOException("Error encoding certificate: " + e.toString());
                }
            }
            Enumeration keys4 = this.certs.keys();
            while (keys4.hasMoreElements()) {
                try {
                    String str3 = (String) keys4.nextElement();
                    Certificate certificate = (Certificate) this.certs.get(str3);
                    if (this.keys.get(str3) == null) {
                        CertBag certBag2 = new CertBag(x509Certificate, new DEROctetString(certificate.getEncoded()));
                        ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
                        if (certificate instanceof PKCS12BagAttributeCarrier) {
                            PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier3 = (PKCS12BagAttributeCarrier) certificate;
                            DERBMPString dERBMPString3 = (DERBMPString) pKCS12BagAttributeCarrier3.getBagAttribute(pkcs_9_at_friendlyName);
                            if (dERBMPString3 == null || !dERBMPString3.getString().equals(str3)) {
                                pKCS12BagAttributeCarrier3.setBagAttribute(pkcs_9_at_friendlyName, new DERBMPString(str3));
                            }
                            Enumeration bagAttributeKeys3 = pKCS12BagAttributeCarrier3.getBagAttributeKeys();
                            z2 = false;
                            while (bagAttributeKeys3.hasMoreElements()) {
                                ASN1ObjectIdentifier aSN1ObjectIdentifier3 = (ASN1ObjectIdentifier) bagAttributeKeys3.nextElement();
                                if (aSN1ObjectIdentifier3.equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId)) {
                                    keys4 = keys4;
                                } else {
                                    ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
                                    aSN1EncodableVector12.add(aSN1ObjectIdentifier3);
                                    aSN1EncodableVector12.add(new DERSet(pKCS12BagAttributeCarrier3.getBagAttribute(aSN1ObjectIdentifier3)));
                                    aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                                    keys4 = keys4;
                                    z2 = true;
                                }
                            }
                            enumeration = keys4;
                        } else {
                            enumeration = keys4;
                            z2 = false;
                        }
                        if (!z2) {
                            ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
                            aSN1EncodableVector13.add(pkcs_9_at_friendlyName);
                            aSN1EncodableVector13.add(new DERSet(new DERBMPString(str3)));
                            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                        }
                        aSN1EncodableVector6.add(new SafeBag(certBag, certBag2.toASN1Primitive(), new DERSet(aSN1EncodableVector11)));
                        hashtable.put(certificate, certificate);
                        keys4 = enumeration;
                    }
                } catch (CertificateEncodingException e2) {
                    throw new IOException("Error encoding certificate: " + e2.toString());
                }
            }
            Set usedCertificateSet = getUsedCertificateSet();
            Enumeration keys5 = this.chainCerts.keys();
            while (keys5.hasMoreElements()) {
                try {
                    Certificate certificate2 = (Certificate) this.chainCerts.get((CertId) keys5.nextElement());
                    if (usedCertificateSet.contains(certificate2)) {
                        if (hashtable.get(certificate2) == null) {
                            CertBag certBag3 = new CertBag(x509Certificate, new DEROctetString(certificate2.getEncoded()));
                            ASN1EncodableVector aSN1EncodableVector14 = new ASN1EncodableVector();
                            if (certificate2 instanceof PKCS12BagAttributeCarrier) {
                                PKCS12BagAttributeCarrier pKCS12BagAttributeCarrier4 = (PKCS12BagAttributeCarrier) certificate2;
                                Enumeration bagAttributeKeys4 = pKCS12BagAttributeCarrier4.getBagAttributeKeys();
                                while (bagAttributeKeys4.hasMoreElements()) {
                                    ASN1ObjectIdentifier aSN1ObjectIdentifier4 = (ASN1ObjectIdentifier) bagAttributeKeys4.nextElement();
                                    if (!aSN1ObjectIdentifier4.equals(PKCSObjectIdentifiers.pkcs_9_at_localKeyId)) {
                                        ASN1EncodableVector aSN1EncodableVector15 = new ASN1EncodableVector();
                                        aSN1EncodableVector15.add(aSN1ObjectIdentifier4);
                                        aSN1EncodableVector15.add(new DERSet(pKCS12BagAttributeCarrier4.getBagAttribute(aSN1ObjectIdentifier4)));
                                        aSN1EncodableVector14.add(new DERSequence(aSN1EncodableVector15));
                                    }
                                }
                            }
                            aSN1EncodableVector6.add(new SafeBag(certBag, certBag3.toASN1Primitive(), new DERSet(aSN1EncodableVector14)));
                        }
                    }
                } catch (CertificateEncodingException e3) {
                    throw new IOException("Error encoding certificate: " + e3.toString());
                }
            }
            AuthenticatedSafe authenticatedSafe = new AuthenticatedSafe(new ContentInfo[]{new ContentInfo(data, bEROctetString), new ContentInfo(encryptedData, new EncryptedData(data, algorithmIdentifier, new BEROctetString(cryptData(true, algorithmIdentifier, cArr, false, new DERSequence(aSN1EncodableVector6).getEncoded(ASN1Encoding.DER)))).toASN1Primitive())});
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (z) {
                dEROutputStream = new DEROutputStream(byteArrayOutputStream);
            } else {
                dEROutputStream = new BEROutputStream(byteArrayOutputStream);
            }
            dEROutputStream.writeObject(authenticatedSafe);
            ContentInfo contentInfo = new ContentInfo(data, new BEROctetString(byteArrayOutputStream.toByteArray()));
            byte[] bArr3 = new byte[20];
            this.random.nextBytes(bArr3);
            try {
                Pfx pfx = new Pfx(contentInfo, new MacData(new DigestInfo(new AlgorithmIdentifier(id_SHA1, DERNull.INSTANCE), calculatePbeMac(id_SHA1, bArr3, 1024, cArr, false, ((ASN1OctetString) contentInfo.getContent()).getOctets())), bArr3, 1024));
                if (z) {
                    dEROutputStream2 = new DEROutputStream(outputStream);
                } else {
                    dEROutputStream2 = new BEROutputStream(outputStream);
                }
                dEROutputStream2.writeObject(pfx);
            } catch (Exception e4) {
                throw new IOException("error constructing MAC: " + e4.toString());
            }
        } else {
            throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
        }
    }

    private Set getUsedCertificateSet() {
        HashSet hashSet = new HashSet();
        Enumeration keys2 = this.keys.keys();
        while (keys2.hasMoreElements()) {
            Certificate[] engineGetCertificateChain = engineGetCertificateChain((String) keys2.nextElement());
            for (int i = 0; i != engineGetCertificateChain.length; i++) {
                hashSet.add(engineGetCertificateChain[i]);
            }
        }
        Enumeration keys3 = this.certs.keys();
        while (keys3.hasMoreElements()) {
            hashSet.add(engineGetCertificate((String) keys3.nextElement()));
        }
        return hashSet;
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier aSN1ObjectIdentifier, byte[] bArr, int i, char[] cArr, boolean z, byte[] bArr2) {
        PBEParameterSpec pBEParameterSpec = new PBEParameterSpec(bArr, i);
        Mac createMac = this.helper.createMac(aSN1ObjectIdentifier.getId());
        createMac.init(new PKCS12Key(cArr, z), pBEParameterSpec);
        createMac.update(bArr2);
        return createMac.doFinal();
    }

    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class BCPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    public static class DefPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class DefPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    /* access modifiers changed from: private */
    public static class IgnoresCaseHashtable {
        private Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public void put(String str, Object obj) {
            String lowerCase = str == null ? null : Strings.toLowerCase(str);
            String str2 = (String) this.keys.get(lowerCase);
            if (str2 != null) {
                this.orig.remove(str2);
            }
            this.keys.put(lowerCase, str);
            this.orig.put(str, obj);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public Object remove(String str) {
            String str2 = (String) this.keys.remove(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.remove(str2);
        }

        public Object get(String str) {
            String str2 = (String) this.keys.get(str == null ? null : Strings.toLowerCase(str));
            if (str2 == null) {
                return null;
            }
            return this.orig.get(str2);
        }

        public Enumeration elements() {
            return this.orig.elements();
        }
    }

    /* access modifiers changed from: private */
    public static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            HashMap hashMap = new HashMap();
            hashMap.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            hashMap.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            hashMap.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            hashMap.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            hashMap.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(hashMap);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer num = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (num != null) {
                return num.intValue();
            }
            return -1;
        }
    }
}
