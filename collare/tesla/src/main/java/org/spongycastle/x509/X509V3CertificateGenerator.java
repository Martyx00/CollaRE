package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.TBSCertificate;
import org.spongycastle.asn1.x509.Time;
import org.spongycastle.asn1.x509.V3TBSCertificateGenerator;
import org.spongycastle.asn1.x509.X509ExtensionsGenerator;
import org.spongycastle.asn1.x509.X509Name;
import org.spongycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.x509.extension.X509ExtensionUtil;

public class X509V3CertificateGenerator {
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private final CertificateFactory certificateFactory = new CertificateFactory();
    private X509ExtensionsGenerator extGenerator = new X509ExtensionsGenerator();
    private AlgorithmIdentifier sigAlgId;
    private ASN1ObjectIdentifier sigOID;
    private String signatureAlgorithm;
    private V3TBSCertificateGenerator tbsGen = new V3TBSCertificateGenerator();

    public void reset() {
        this.tbsGen = new V3TBSCertificateGenerator();
        this.extGenerator.reset();
    }

    public void setSerialNumber(BigInteger bigInteger) {
        if (bigInteger.compareTo(BigInteger.ZERO) > 0) {
            this.tbsGen.setSerialNumber(new ASN1Integer(bigInteger));
            return;
        }
        throw new IllegalArgumentException("serial number must be a positive integer");
    }

    public void setIssuerDN(X500Principal x500Principal) {
        try {
            this.tbsGen.setIssuer(new X509Principal(x500Principal.getEncoded()));
        } catch (IOException e) {
            throw new IllegalArgumentException("can't process principal: " + e);
        }
    }

    public void setIssuerDN(X509Name x509Name) {
        this.tbsGen.setIssuer(x509Name);
    }

    public void setNotBefore(Date date) {
        this.tbsGen.setStartDate(new Time(date));
    }

    public void setNotAfter(Date date) {
        this.tbsGen.setEndDate(new Time(date));
    }

    public void setSubjectDN(X500Principal x500Principal) {
        try {
            this.tbsGen.setSubject(new X509Principal(x500Principal.getEncoded()));
        } catch (IOException e) {
            throw new IllegalArgumentException("can't process principal: " + e);
        }
    }

    public void setSubjectDN(X509Name x509Name) {
        this.tbsGen.setSubject(x509Name);
    }

    public void setPublicKey(PublicKey publicKey) {
        try {
            this.tbsGen.setSubjectPublicKeyInfo(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(publicKey.getEncoded()).readObject()));
        } catch (Exception e) {
            throw new IllegalArgumentException("unable to process key - " + e.toString());
        }
    }

    public void setSignatureAlgorithm(String str) {
        this.signatureAlgorithm = str;
        try {
            this.sigOID = X509Util.getAlgorithmOID(str);
            this.sigAlgId = X509Util.getSigAlgID(this.sigOID, str);
            this.tbsGen.setSignature(this.sigAlgId);
        } catch (Exception unused) {
            throw new IllegalArgumentException("Unknown signature type requested: " + str);
        }
    }

    public void setSubjectUniqueID(boolean[] zArr) {
        this.tbsGen.setSubjectUniqueID(booleanToBitString(zArr));
    }

    public void setIssuerUniqueID(boolean[] zArr) {
        this.tbsGen.setIssuerUniqueID(booleanToBitString(zArr));
    }

    private DERBitString booleanToBitString(boolean[] zArr) {
        byte[] bArr = new byte[((zArr.length + 7) / 8)];
        for (int i = 0; i != zArr.length; i++) {
            int i2 = i / 8;
            bArr[i2] = (byte) (bArr[i2] | (zArr[i] ? 1 << (7 - (i % 8)) : 0));
        }
        int length = zArr.length % 8;
        if (length == 0) {
            return new DERBitString(bArr);
        }
        return new DERBitString(bArr, 8 - length);
    }

    public void addExtension(String str, boolean z, ASN1Encodable aSN1Encodable) {
        addExtension(new ASN1ObjectIdentifier(str), z, aSN1Encodable);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, ASN1Encodable aSN1Encodable) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), z, aSN1Encodable);
    }

    public void addExtension(String str, boolean z, byte[] bArr) {
        addExtension(new ASN1ObjectIdentifier(str), z, bArr);
    }

    public void addExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, byte[] bArr) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), z, bArr);
    }

    public void copyAndAddExtension(String str, boolean z, X509Certificate x509Certificate) {
        byte[] extensionValue = x509Certificate.getExtensionValue(str);
        if (extensionValue != null) {
            try {
                addExtension(str, z, X509ExtensionUtil.fromExtensionValue(extensionValue));
            } catch (IOException e) {
                throw new CertificateParsingException(e.toString());
            }
        } else {
            throw new CertificateParsingException("extension " + str + " not present");
        }
    }

    public void copyAndAddExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean z, X509Certificate x509Certificate) {
        copyAndAddExtension(aSN1ObjectIdentifier.getId(), z, x509Certificate);
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey) {
        try {
            return generateX509Certificate(privateKey, BouncyCastleProvider.PROVIDER_NAME, null);
        } catch (NoSuchProviderException unused) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, SecureRandom secureRandom) {
        try {
            return generateX509Certificate(privateKey, BouncyCastleProvider.PROVIDER_NAME, secureRandom);
        } catch (NoSuchProviderException unused) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, String str) {
        return generateX509Certificate(privateKey, str, null);
    }

    public X509Certificate generateX509Certificate(PrivateKey privateKey, String str, SecureRandom secureRandom) {
        try {
            return generate(privateKey, str, secureRandom);
        } catch (NoSuchProviderException e) {
            throw e;
        } catch (SignatureException e2) {
            throw e2;
        } catch (InvalidKeyException e3) {
            throw e3;
        } catch (GeneralSecurityException e4) {
            throw new SecurityException("exception: " + e4);
        }
    }

    public X509Certificate generate(PrivateKey privateKey) {
        return generate(privateKey, (SecureRandom) null);
    }

    public X509Certificate generate(PrivateKey privateKey, SecureRandom secureRandom) {
        TBSCertificate generateTbsCert = generateTbsCert();
        try {
            try {
                return generateJcaObject(generateTbsCert, X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, privateKey, secureRandom, generateTbsCert));
            } catch (Exception e) {
                throw new ExtCertificateEncodingException("exception producing certificate object", e);
            }
        } catch (IOException e2) {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", e2);
        }
    }

    public X509Certificate generate(PrivateKey privateKey, String str) {
        return generate(privateKey, str, null);
    }

    public X509Certificate generate(PrivateKey privateKey, String str, SecureRandom secureRandom) {
        TBSCertificate generateTbsCert = generateTbsCert();
        try {
            try {
                return generateJcaObject(generateTbsCert, X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, str, privateKey, secureRandom, generateTbsCert));
            } catch (Exception e) {
                throw new ExtCertificateEncodingException("exception producing certificate object", e);
            }
        } catch (IOException e2) {
            throw new ExtCertificateEncodingException("exception encoding TBS cert", e2);
        }
    }

    private TBSCertificate generateTbsCert() {
        if (!this.extGenerator.isEmpty()) {
            this.tbsGen.setExtensions(this.extGenerator.generate());
        }
        return this.tbsGen.generateTBSCertificate();
    }

    private X509Certificate generateJcaObject(TBSCertificate tBSCertificate, byte[] bArr) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(tBSCertificate);
        aSN1EncodableVector.add(this.sigAlgId);
        aSN1EncodableVector.add(new DERBitString(bArr));
        return (X509Certificate) this.certificateFactory.engineGenerateCertificate(new ByteArrayInputStream(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER)));
    }

    public Iterator getSignatureAlgNames() {
        return X509Util.getAlgNames();
    }
}
