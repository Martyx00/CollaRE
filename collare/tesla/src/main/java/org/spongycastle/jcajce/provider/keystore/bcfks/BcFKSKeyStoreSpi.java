package org.spongycastle.jcajce.provider.keystore.bcfks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERNull;
import org.spongycastle.asn1.bc.EncryptedObjectStoreData;
import org.spongycastle.asn1.bc.EncryptedPrivateKeyData;
import org.spongycastle.asn1.bc.EncryptedSecretKeyData;
import org.spongycastle.asn1.bc.ObjectData;
import org.spongycastle.asn1.bc.ObjectDataSequence;
import org.spongycastle.asn1.bc.ObjectStore;
import org.spongycastle.asn1.bc.ObjectStoreData;
import org.spongycastle.asn1.bc.ObjectStoreIntegrityCheck;
import org.spongycastle.asn1.bc.PbkdMacIntegrityCheck;
import org.spongycastle.asn1.bc.SecretKeyData;
import org.spongycastle.asn1.cms.CCMParameters;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.pkcs.EncryptionScheme;
import org.spongycastle.asn1.pkcs.KeyDerivationFunc;
import org.spongycastle.asn1.pkcs.PBES2Parameters;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x9.X9ObjectIdentifiers;
import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

class BcFKSKeyStoreSpi extends KeyStoreSpi {
    private static final BigInteger CERTIFICATE = BigInteger.valueOf(0);
    private static final BigInteger PRIVATE_KEY = BigInteger.valueOf(1);
    private static final BigInteger PROTECTED_PRIVATE_KEY = BigInteger.valueOf(3);
    private static final BigInteger PROTECTED_SECRET_KEY = BigInteger.valueOf(4);
    private static final BigInteger SECRET_KEY = BigInteger.valueOf(2);
    private static final Map<String, ASN1ObjectIdentifier> oidMap = new HashMap();
    private static final Map<ASN1ObjectIdentifier, String> publicAlgMap = new HashMap();
    private Date creationDate;
    private final Map<String, ObjectData> entries = new HashMap();
    private AlgorithmIdentifier hmacAlgorithm;
    private KeyDerivationFunc hmacPkbdAlgorithm;
    private Date lastModifiedDate;
    private final Map<String, PrivateKey> privateKeyCache = new HashMap();
    private final BouncyCastleProvider provider;

    static {
        oidMap.put("DESEDE", OIWObjectIdentifiers.desEDE);
        oidMap.put("TRIPLEDES", OIWObjectIdentifiers.desEDE);
        oidMap.put("TDEA", OIWObjectIdentifiers.desEDE);
        oidMap.put("HMACSHA1", PKCSObjectIdentifiers.id_hmacWithSHA1);
        oidMap.put("HMACSHA224", PKCSObjectIdentifiers.id_hmacWithSHA224);
        oidMap.put("HMACSHA256", PKCSObjectIdentifiers.id_hmacWithSHA256);
        oidMap.put("HMACSHA384", PKCSObjectIdentifiers.id_hmacWithSHA384);
        oidMap.put("HMACSHA512", PKCSObjectIdentifiers.id_hmacWithSHA512);
        publicAlgMap.put(PKCSObjectIdentifiers.rsaEncryption, "RSA");
        publicAlgMap.put(X9ObjectIdentifiers.id_ecPublicKey, "EC");
        publicAlgMap.put(OIWObjectIdentifiers.elGamalAlgorithm, "DH");
        publicAlgMap.put(PKCSObjectIdentifiers.dhKeyAgreement, "DH");
        publicAlgMap.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    private static String getPublicKeyAlg(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        String str = publicAlgMap.get(aSN1ObjectIdentifier);
        if (str != null) {
            return str;
        }
        return aSN1ObjectIdentifier.getId();
    }

    BcFKSKeyStoreSpi(BouncyCastleProvider bouncyCastleProvider) {
        this.provider = bouncyCastleProvider;
    }

    @Override // java.security.KeyStoreSpi
    public Key engineGetKey(String str, char[] cArr) {
        KeyFactory keyFactory;
        SecretKeyFactory secretKeyFactory;
        ObjectData objectData = this.entries.get(str);
        if (objectData == null) {
            return null;
        }
        if (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY)) {
            PrivateKey privateKey = this.privateKeyCache.get(str);
            if (privateKey != null) {
                return privateKey;
            }
            EncryptedPrivateKeyInfo instance = EncryptedPrivateKeyInfo.getInstance(EncryptedPrivateKeyData.getInstance(objectData.getData()).getEncryptedPrivateKeyInfo());
            try {
                PrivateKeyInfo instance2 = PrivateKeyInfo.getInstance(decryptData("PRIVATE_KEY_ENCRYPTION", instance.getEncryptionAlgorithm(), cArr, instance.getEncryptedData()));
                if (this.provider != null) {
                    keyFactory = KeyFactory.getInstance(instance2.getPrivateKeyAlgorithm().getAlgorithm().getId(), this.provider);
                } else {
                    keyFactory = KeyFactory.getInstance(getPublicKeyAlg(instance2.getPrivateKeyAlgorithm().getAlgorithm()));
                }
                PrivateKey generatePrivate = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(instance2.getEncoded()));
                this.privateKeyCache.put(str, generatePrivate);
                return generatePrivate;
            } catch (Exception e) {
                throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover private key (" + str + "): " + e.getMessage());
            }
        } else if (objectData.getType().equals(SECRET_KEY) || objectData.getType().equals(PROTECTED_SECRET_KEY)) {
            EncryptedSecretKeyData instance3 = EncryptedSecretKeyData.getInstance(objectData.getData());
            try {
                SecretKeyData instance4 = SecretKeyData.getInstance(decryptData("SECRET_KEY_ENCRYPTION", instance3.getKeyEncryptionAlgorithm(), cArr, instance3.getEncryptedKeyData()));
                if (this.provider != null) {
                    secretKeyFactory = SecretKeyFactory.getInstance(instance4.getKeyAlgorithm().getId(), this.provider);
                } else {
                    secretKeyFactory = SecretKeyFactory.getInstance(instance4.getKeyAlgorithm().getId());
                }
                return secretKeyFactory.generateSecret(new SecretKeySpec(instance4.getKeyBytes(), instance4.getKeyAlgorithm().getId()));
            } catch (Exception e2) {
                throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + str + "): " + e2.getMessage());
            }
        } else {
            throw new UnrecoverableKeyException("BCFKS KeyStore unable to recover secret key (" + str + "): type not recognized");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: java.security.cert.X509Certificate[] */
    /* JADX WARN: Multi-variable type inference failed */
    public Certificate[] engineGetCertificateChain(String str) {
        ObjectData objectData = this.entries.get(str);
        if (objectData == null) {
            return null;
        }
        if (!(objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY))) {
            return null;
        }
        org.spongycastle.asn1.x509.Certificate[] certificateChain = EncryptedPrivateKeyData.getInstance(objectData.getData()).getCertificateChain();
        X509Certificate[] x509CertificateArr = new X509Certificate[certificateChain.length];
        for (int i = 0; i != x509CertificateArr.length; i++) {
            x509CertificateArr[i] = decodeCertificate(certificateChain[i]);
        }
        return x509CertificateArr;
    }

    public Certificate engineGetCertificate(String str) {
        ObjectData objectData = this.entries.get(str);
        if (objectData == null) {
            return null;
        }
        if (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY)) {
            return decodeCertificate(EncryptedPrivateKeyData.getInstance(objectData.getData()).getCertificateChain()[0]);
        }
        if (objectData.getType().equals(CERTIFICATE)) {
            return decodeCertificate(objectData.getData());
        }
        return null;
    }

    private Certificate decodeCertificate(Object obj) {
        BouncyCastleProvider bouncyCastleProvider = this.provider;
        if (bouncyCastleProvider != null) {
            try {
                return CertificateFactory.getInstance("X.509", bouncyCastleProvider).generateCertificate(new ByteArrayInputStream(org.spongycastle.asn1.x509.Certificate.getInstance(obj).getEncoded()));
            } catch (Exception unused) {
                return null;
            }
        } else {
            try {
                return CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(org.spongycastle.asn1.x509.Certificate.getInstance(obj).getEncoded()));
            } catch (Exception unused2) {
                return null;
            }
        }
    }

    public Date engineGetCreationDate(String str) {
        ObjectData objectData = this.entries.get(str);
        if (objectData == null) {
            return null;
        }
        try {
            return objectData.getLastModifiedDate().getDate();
        } catch (ParseException unused) {
            return new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
        Cipher cipher;
        byte[] bArr;
        Cipher cipher2;
        Date date = new Date();
        ObjectData objectData = this.entries.get(str);
        Date extractCreationDate = objectData != null ? extractCreationDate(objectData, date) : date;
        this.privateKeyCache.remove(str);
        if (key instanceof PrivateKey) {
            if (certificateArr != null) {
                try {
                    byte[] encoded = key.getEncoded();
                    KeyDerivationFunc generatePkbdAlgorithmIdentifier = generatePkbdAlgorithmIdentifier(32);
                    if (cArr == null) {
                        cArr = new char[0];
                    }
                    byte[] generateKey = generateKey(generatePkbdAlgorithmIdentifier, "PRIVATE_KEY_ENCRYPTION", cArr);
                    if (this.provider == null) {
                        cipher2 = Cipher.getInstance("AES/CCM/NoPadding");
                    } else {
                        cipher2 = Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                    }
                    cipher2.init(1, new SecretKeySpec(generateKey, "AES"));
                    this.entries.put(str, new ObjectData(PRIVATE_KEY, str, extractCreationDate, date, createPrivateKeySequence(new EncryptedPrivateKeyInfo(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, new PBES2Parameters(generatePkbdAlgorithmIdentifier, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(cipher2.getParameters().getEncoded())))), cipher2.doFinal(encoded)), certificateArr).getEncoded(), null));
                } catch (Exception e) {
                    throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + e.toString(), e);
                }
            } else {
                throw new KeyStoreException("BCFKS KeyStore requires a certificate chain for private key storage.");
            }
        } else if (!(key instanceof SecretKey)) {
            throw new KeyStoreException("BCFKS KeyStore unable to recognize key.");
        } else if (certificateArr == null) {
            try {
                byte[] encoded2 = key.getEncoded();
                KeyDerivationFunc generatePkbdAlgorithmIdentifier2 = generatePkbdAlgorithmIdentifier(32);
                if (cArr == null) {
                    cArr = new char[0];
                }
                byte[] generateKey2 = generateKey(generatePkbdAlgorithmIdentifier2, "SECRET_KEY_ENCRYPTION", cArr);
                if (this.provider == null) {
                    cipher = Cipher.getInstance("AES/CCM/NoPadding");
                } else {
                    cipher = Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                }
                cipher.init(1, new SecretKeySpec(generateKey2, "AES"));
                String upperCase = Strings.toUpperCase(key.getAlgorithm());
                if (upperCase.indexOf("AES") > -1) {
                    bArr = cipher.doFinal(new SecretKeyData(NISTObjectIdentifiers.aes, encoded2).getEncoded());
                } else {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = oidMap.get(upperCase);
                    if (aSN1ObjectIdentifier != null) {
                        bArr = cipher.doFinal(new SecretKeyData(aSN1ObjectIdentifier, encoded2).getEncoded());
                    } else {
                        throw new KeyStoreException("BCFKS KeyStore cannot recognize secret key (" + upperCase + ") for storage.");
                    }
                }
                this.entries.put(str, new ObjectData(SECRET_KEY, str, extractCreationDate, date, new EncryptedSecretKeyData(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, new PBES2Parameters(generatePkbdAlgorithmIdentifier2, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(cipher.getParameters().getEncoded())))), bArr).getEncoded(), null));
            } catch (Exception e2) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing private key: " + e2.toString(), e2);
            }
        } else {
            throw new KeyStoreException("BCFKS KeyStore cannot store certificate chain with secret key.");
        }
        this.lastModifiedDate = date;
    }

    private SecureRandom getDefaultSecureRandom() {
        return new SecureRandom();
    }

    private EncryptedPrivateKeyData createPrivateKeySequence(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo, Certificate[] certificateArr) {
        org.spongycastle.asn1.x509.Certificate[] certificateArr2 = new org.spongycastle.asn1.x509.Certificate[certificateArr.length];
        for (int i = 0; i != certificateArr.length; i++) {
            certificateArr2[i] = org.spongycastle.asn1.x509.Certificate.getInstance(certificateArr[i].getEncoded());
        }
        return new EncryptedPrivateKeyData(encryptedPrivateKeyInfo, certificateArr2);
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
        Date date = new Date();
        ObjectData objectData = this.entries.get(str);
        Date extractCreationDate = objectData != null ? extractCreationDate(objectData, date) : date;
        if (certificateArr != null) {
            try {
                EncryptedPrivateKeyInfo instance = EncryptedPrivateKeyInfo.getInstance(bArr);
                try {
                    this.privateKeyCache.remove(str);
                    this.entries.put(str, new ObjectData(PROTECTED_PRIVATE_KEY, str, extractCreationDate, date, createPrivateKeySequence(instance, certificateArr).getEncoded(), null));
                } catch (Exception e) {
                    throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + e.toString(), e);
                }
            } catch (Exception e2) {
                throw new ExtKeyStoreException("BCFKS KeyStore private key encoding must be an EncryptedPrivateKeyInfo.", e2);
            }
        } else {
            try {
                this.entries.put(str, new ObjectData(PROTECTED_SECRET_KEY, str, extractCreationDate, date, bArr, null));
            } catch (Exception e3) {
                throw new ExtKeyStoreException("BCFKS KeyStore exception storing protected private key: " + e3.toString(), e3);
            }
        }
        this.lastModifiedDate = date;
    }

    @Override // java.security.KeyStoreSpi
    public void engineSetCertificateEntry(String str, Certificate certificate) {
        Date date;
        ObjectData objectData = this.entries.get(str);
        Date date2 = new Date();
        if (objectData == null) {
            date = date2;
        } else if (objectData.getType().equals(CERTIFICATE)) {
            date = extractCreationDate(objectData, date2);
        } else {
            throw new KeyStoreException("BCFKS KeyStore already has a key entry with alias " + str);
        }
        try {
            this.entries.put(str, new ObjectData(CERTIFICATE, str, date, date2, certificate.getEncoded(), null));
            this.lastModifiedDate = date2;
        } catch (CertificateEncodingException e) {
            throw new ExtKeyStoreException("BCFKS KeyStore unable to handle certificate: " + e.getMessage(), e);
        }
    }

    private Date extractCreationDate(ObjectData objectData, Date date) {
        try {
            return objectData.getCreationDate().getDate();
        } catch (ParseException unused) {
            return date;
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineDeleteEntry(String str) {
        if (this.entries.get(str) != null) {
            this.privateKeyCache.remove(str);
            this.entries.remove(str);
            this.lastModifiedDate = new Date();
        }
    }

    @Override // java.security.KeyStoreSpi
    public Enumeration<String> engineAliases() {
        final Iterator it = new HashSet(this.entries.keySet()).iterator();
        return new Enumeration() {
            /* class org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi.AnonymousClass1 */

            public boolean hasMoreElements() {
                return it.hasNext();
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                return it.next();
            }
        };
    }

    public boolean engineContainsAlias(String str) {
        if (str != null) {
            return this.entries.containsKey(str);
        }
        throw new NullPointerException("alias value is null");
    }

    public int engineSize() {
        return this.entries.size();
    }

    public boolean engineIsKeyEntry(String str) {
        ObjectData objectData = this.entries.get(str);
        if (objectData == null) {
            return false;
        }
        BigInteger type = objectData.getType();
        if (type.equals(PRIVATE_KEY) || type.equals(SECRET_KEY) || type.equals(PROTECTED_PRIVATE_KEY) || type.equals(PROTECTED_SECRET_KEY)) {
            return true;
        }
        return false;
    }

    public boolean engineIsCertificateEntry(String str) {
        ObjectData objectData = this.entries.get(str);
        if (objectData != null) {
            return objectData.getType().equals(CERTIFICATE);
        }
        return false;
    }

    public String engineGetCertificateAlias(Certificate certificate) {
        if (certificate == null) {
            return null;
        }
        try {
            byte[] encoded = certificate.getEncoded();
            for (String str : this.entries.keySet()) {
                ObjectData objectData = this.entries.get(str);
                if (objectData.getType().equals(CERTIFICATE)) {
                    if (Arrays.areEqual(objectData.getData(), encoded)) {
                        return str;
                    }
                } else if (objectData.getType().equals(PRIVATE_KEY) || objectData.getType().equals(PROTECTED_PRIVATE_KEY)) {
                    try {
                        if (Arrays.areEqual(EncryptedPrivateKeyData.getInstance(objectData.getData()).getCertificateChain()[0].toASN1Primitive().getEncoded(), encoded)) {
                            return str;
                        }
                    } catch (IOException unused) {
                        continue;
                    }
                }
            }
            return null;
        } catch (CertificateEncodingException unused2) {
            return null;
        }
    }

    private byte[] generateKey(KeyDerivationFunc keyDerivationFunc, String str, char[] cArr) {
        byte[] PKCS12PasswordToBytes = PBEParametersGenerator.PKCS12PasswordToBytes(cArr);
        byte[] PKCS12PasswordToBytes2 = PBEParametersGenerator.PKCS12PasswordToBytes(str.toCharArray());
        PKCS5S2ParametersGenerator pKCS5S2ParametersGenerator = new PKCS5S2ParametersGenerator(new SHA512Digest());
        if (keyDerivationFunc.getAlgorithm().equals(PKCSObjectIdentifiers.id_PBKDF2)) {
            PBKDF2Params instance = PBKDF2Params.getInstance(keyDerivationFunc.getParameters());
            if (instance.getPrf().getAlgorithm().equals(PKCSObjectIdentifiers.id_hmacWithSHA512)) {
                pKCS5S2ParametersGenerator.init(Arrays.concatenate(PKCS12PasswordToBytes, PKCS12PasswordToBytes2), instance.getSalt(), instance.getIterationCount().intValue());
                return ((KeyParameter) pKCS5S2ParametersGenerator.generateDerivedParameters(instance.getKeyLength().intValue() * 8)).getKey();
            }
            throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD PRF.");
        }
        throw new IOException("BCFKS KeyStore: unrecognized MAC PBKD.");
    }

    private void verifyMac(byte[] bArr, PbkdMacIntegrityCheck pbkdMacIntegrityCheck, char[] cArr) {
        if (!Arrays.constantTimeAreEqual(calculateMac(bArr, pbkdMacIntegrityCheck.getMacAlgorithm(), pbkdMacIntegrityCheck.getPbkdAlgorithm(), cArr), pbkdMacIntegrityCheck.getMac())) {
            throw new IOException("BCFKS KeyStore corrupted: MAC calculation failed.");
        }
    }

    private byte[] calculateMac(byte[] bArr, AlgorithmIdentifier algorithmIdentifier, KeyDerivationFunc keyDerivationFunc, char[] cArr) {
        Mac mac;
        String id = algorithmIdentifier.getAlgorithm().getId();
        BouncyCastleProvider bouncyCastleProvider = this.provider;
        if (bouncyCastleProvider != null) {
            mac = Mac.getInstance(id, bouncyCastleProvider);
        } else {
            mac = Mac.getInstance(id);
        }
        try {
            if (cArr == null) {
                cArr = new char[0];
            }
            mac.init(new SecretKeySpec(generateKey(keyDerivationFunc, "INTEGRITY_CHECK", cArr), id));
            return mac.doFinal(bArr);
        } catch (InvalidKeyException e) {
            throw new IOException("Cannot set up MAC calculation: " + e.getMessage());
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineStore(OutputStream outputStream, char[] cArr) {
        char[] cArr2;
        Cipher cipher;
        ObjectData[] objectDataArr = (ObjectData[]) this.entries.values().toArray(new ObjectData[this.entries.size()]);
        KeyDerivationFunc generatePkbdAlgorithmIdentifier = generatePkbdAlgorithmIdentifier(32);
        if (cArr != null) {
            cArr2 = cArr;
        } else {
            cArr2 = new char[0];
        }
        byte[] generateKey = generateKey(generatePkbdAlgorithmIdentifier, "STORE_ENCRYPTION", cArr2);
        ObjectStoreData objectStoreData = new ObjectStoreData(this.hmacAlgorithm, this.creationDate, this.lastModifiedDate, new ObjectDataSequence(objectDataArr), null);
        try {
            if (this.provider == null) {
                cipher = Cipher.getInstance("AES/CCM/NoPadding");
            } else {
                cipher = Cipher.getInstance("AES/CCM/NoPadding", this.provider);
            }
            cipher.init(1, new SecretKeySpec(generateKey, "AES"));
            EncryptedObjectStoreData encryptedObjectStoreData = new EncryptedObjectStoreData(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, new PBES2Parameters(generatePkbdAlgorithmIdentifier, new EncryptionScheme(NISTObjectIdentifiers.id_aes256_CCM, CCMParameters.getInstance(cipher.getParameters().getEncoded())))), cipher.doFinal(objectStoreData.getEncoded()));
            PBKDF2Params instance = PBKDF2Params.getInstance(this.hmacPkbdAlgorithm.getParameters());
            byte[] bArr = new byte[instance.getSalt().length];
            getDefaultSecureRandom().nextBytes(bArr);
            this.hmacPkbdAlgorithm = new KeyDerivationFunc(this.hmacPkbdAlgorithm.getAlgorithm(), new PBKDF2Params(bArr, instance.getIterationCount().intValue(), instance.getKeyLength().intValue(), instance.getPrf()));
            outputStream.write(new ObjectStore(encryptedObjectStoreData, new ObjectStoreIntegrityCheck(new PbkdMacIntegrityCheck(this.hmacAlgorithm, this.hmacPkbdAlgorithm, calculateMac(encryptedObjectStoreData.getEncoded(), this.hmacAlgorithm, this.hmacPkbdAlgorithm, cArr)))).getEncoded());
            outputStream.flush();
        } catch (NoSuchPaddingException e) {
            throw new NoSuchAlgorithmException(e.toString());
        } catch (BadPaddingException e2) {
            throw new IOException(e2.toString());
        } catch (IllegalBlockSizeException e3) {
            throw new IOException(e3.toString());
        } catch (InvalidKeyException e4) {
            throw new IOException(e4.toString());
        }
    }

    @Override // java.security.KeyStoreSpi
    public void engineLoad(InputStream inputStream, char[] cArr) {
        ObjectStoreData objectStoreData;
        this.entries.clear();
        this.privateKeyCache.clear();
        this.creationDate = null;
        this.lastModifiedDate = null;
        this.hmacAlgorithm = null;
        if (inputStream == null) {
            Date date = new Date();
            this.creationDate = date;
            this.lastModifiedDate = date;
            this.hmacAlgorithm = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, DERNull.INSTANCE);
            this.hmacPkbdAlgorithm = generatePkbdAlgorithmIdentifier(64);
            return;
        }
        ObjectStore instance = ObjectStore.getInstance(new ASN1InputStream(inputStream).readObject());
        ObjectStoreIntegrityCheck integrityCheck = instance.getIntegrityCheck();
        if (integrityCheck.getType() == 0) {
            PbkdMacIntegrityCheck instance2 = PbkdMacIntegrityCheck.getInstance(integrityCheck.getIntegrityCheck());
            this.hmacAlgorithm = instance2.getMacAlgorithm();
            this.hmacPkbdAlgorithm = instance2.getPbkdAlgorithm();
            verifyMac(instance.getStoreData().toASN1Primitive().getEncoded(), instance2, cArr);
            ASN1Encodable storeData = instance.getStoreData();
            if (storeData instanceof EncryptedObjectStoreData) {
                EncryptedObjectStoreData encryptedObjectStoreData = (EncryptedObjectStoreData) storeData;
                objectStoreData = ObjectStoreData.getInstance(decryptData("STORE_ENCRYPTION", encryptedObjectStoreData.getEncryptionAlgorithm(), cArr, encryptedObjectStoreData.getEncryptedContent().getOctets()));
            } else {
                objectStoreData = ObjectStoreData.getInstance(storeData);
            }
            try {
                this.creationDate = objectStoreData.getCreationDate().getDate();
                this.lastModifiedDate = objectStoreData.getLastModifiedDate().getDate();
                if (objectStoreData.getIntegrityAlgorithm().equals(this.hmacAlgorithm)) {
                    Iterator<ASN1Encodable> it = objectStoreData.getObjectDataSequence().iterator();
                    while (it.hasNext()) {
                        ObjectData instance3 = ObjectData.getInstance(it.next());
                        this.entries.put(instance3.getIdentifier(), instance3);
                    }
                    return;
                }
                throw new IOException("BCFKS KeyStore storeData integrity algorithm does not match store integrity algorithm.");
            } catch (ParseException unused) {
                throw new IOException("BCFKS KeyStore unable to parse store data information.");
            }
        } else {
            throw new IOException("BCFKS KeyStore unable to recognize integrity check.");
        }
    }

    private byte[] decryptData(String str, AlgorithmIdentifier algorithmIdentifier, char[] cArr, byte[] bArr) {
        AlgorithmParameters algorithmParameters;
        Cipher cipher;
        if (algorithmIdentifier.getAlgorithm().equals(PKCSObjectIdentifiers.id_PBES2)) {
            PBES2Parameters instance = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
            EncryptionScheme encryptionScheme = instance.getEncryptionScheme();
            if (encryptionScheme.getAlgorithm().equals(NISTObjectIdentifiers.id_aes256_CCM)) {
                try {
                    CCMParameters instance2 = CCMParameters.getInstance(encryptionScheme.getParameters());
                    if (this.provider == null) {
                        cipher = Cipher.getInstance("AES/CCM/NoPadding");
                        algorithmParameters = AlgorithmParameters.getInstance("CCM");
                    } else {
                        cipher = Cipher.getInstance("AES/CCM/NoPadding", this.provider);
                        algorithmParameters = AlgorithmParameters.getInstance("CCM", this.provider);
                    }
                    algorithmParameters.init(instance2.getEncoded());
                    KeyDerivationFunc keyDerivationFunc = instance.getKeyDerivationFunc();
                    if (cArr == null) {
                        cArr = new char[0];
                    }
                    cipher.init(2, new SecretKeySpec(generateKey(keyDerivationFunc, str, cArr), "AES"), algorithmParameters);
                    return cipher.doFinal(bArr);
                } catch (Exception e) {
                    throw new IOException(e.toString());
                }
            } else {
                throw new IOException("BCFKS KeyStore cannot recognize protection encryption algorithm.");
            }
        } else {
            throw new IOException("BCFKS KeyStore cannot recognize protection algorithm.");
        }
    }

    private KeyDerivationFunc generatePkbdAlgorithmIdentifier(int i) {
        byte[] bArr = new byte[64];
        getDefaultSecureRandom().nextBytes(bArr);
        return new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(bArr, 1024, i, new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA512, DERNull.INSTANCE)));
    }

    public static class Std extends BcFKSKeyStoreSpi {
        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return BcFKSKeyStoreSpi.super.engineAliases();
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return BcFKSKeyStoreSpi.super.engineContainsAlias(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) {
            BcFKSKeyStoreSpi.super.engineDeleteEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCertificate(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return BcFKSKeyStoreSpi.super.engineGetCertificateAlias(certificate);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCertificateChain(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCreationDate(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) {
            return BcFKSKeyStoreSpi.super.engineGetKey(str, cArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return BcFKSKeyStoreSpi.super.engineIsCertificateEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return BcFKSKeyStoreSpi.super.engineIsKeyEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) {
            BcFKSKeyStoreSpi.super.engineLoad(inputStream, cArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) {
            BcFKSKeyStoreSpi.super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
            BcFKSKeyStoreSpi.super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
            BcFKSKeyStoreSpi.super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return BcFKSKeyStoreSpi.super.engineSize();
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) {
            BcFKSKeyStoreSpi.super.engineStore(outputStream, cArr);
        }

        public Std() {
            super(new BouncyCastleProvider());
        }
    }

    public static class Def extends BcFKSKeyStoreSpi {
        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Enumeration engineAliases() {
            return BcFKSKeyStoreSpi.super.engineAliases();
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineContainsAlias(String str) {
            return BcFKSKeyStoreSpi.super.engineContainsAlias(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineDeleteEntry(String str) {
            BcFKSKeyStoreSpi.super.engineDeleteEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate engineGetCertificate(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCertificate(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ String engineGetCertificateAlias(Certificate certificate) {
            return BcFKSKeyStoreSpi.super.engineGetCertificateAlias(certificate);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Certificate[] engineGetCertificateChain(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCertificateChain(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ Date engineGetCreationDate(String str) {
            return BcFKSKeyStoreSpi.super.engineGetCreationDate(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ Key engineGetKey(String str, char[] cArr) {
            return BcFKSKeyStoreSpi.super.engineGetKey(str, cArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsCertificateEntry(String str) {
            return BcFKSKeyStoreSpi.super.engineIsCertificateEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ boolean engineIsKeyEntry(String str) {
            return BcFKSKeyStoreSpi.super.engineIsKeyEntry(str);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineLoad(InputStream inputStream, char[] cArr) {
            BcFKSKeyStoreSpi.super.engineLoad(inputStream, cArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetCertificateEntry(String str, Certificate certificate) {
            BcFKSKeyStoreSpi.super.engineSetCertificateEntry(str, certificate);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) {
            BcFKSKeyStoreSpi.super.engineSetKeyEntry(str, key, cArr, certificateArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) {
            BcFKSKeyStoreSpi.super.engineSetKeyEntry(str, bArr, certificateArr);
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi
        public /* bridge */ /* synthetic */ int engineSize() {
            return BcFKSKeyStoreSpi.super.engineSize();
        }

        @Override // org.spongycastle.jcajce.provider.keystore.bcfks.BcFKSKeyStoreSpi, java.security.KeyStoreSpi
        public /* bridge */ /* synthetic */ void engineStore(OutputStream outputStream, char[] cArr) {
            BcFKSKeyStoreSpi.super.engineStore(outputStream, cArr);
        }

        public Def() {
            super(null);
        }
    }

    /* access modifiers changed from: private */
    public static class ExtKeyStoreException extends KeyStoreException {
        private final Throwable cause;

        ExtKeyStoreException(String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }
}
