package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x509.AttributeCertificate;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.util.Arrays;

public class X509V2AttributeCertificate implements X509AttributeCertificate {
    private AttributeCertificate cert;
    private Date notAfter;
    private Date notBefore;

    private static AttributeCertificate getObject(InputStream inputStream) {
        try {
            return AttributeCertificate.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new IOException("exception decoding certificate structure: " + e2.toString());
        }
    }

    public X509V2AttributeCertificate(InputStream inputStream) {
        this(getObject(inputStream));
    }

    public X509V2AttributeCertificate(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    X509V2AttributeCertificate(AttributeCertificate attributeCertificate) {
        this.cert = attributeCertificate;
        try {
            this.notAfter = attributeCertificate.getAcinfo().getAttrCertValidityPeriod().getNotAfterTime().getDate();
            this.notBefore = attributeCertificate.getAcinfo().getAttrCertValidityPeriod().getNotBeforeTime().getDate();
        } catch (ParseException unused) {
            throw new IOException("invalid data structure in certificate!");
        }
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public int getVersion() {
        return this.cert.getAcinfo().getVersion().getValue().intValue() + 1;
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public BigInteger getSerialNumber() {
        return this.cert.getAcinfo().getSerialNumber().getValue();
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public AttributeCertificateHolder getHolder() {
        return new AttributeCertificateHolder((ASN1Sequence) this.cert.getAcinfo().getHolder().toASN1Primitive());
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public AttributeCertificateIssuer getIssuer() {
        return new AttributeCertificateIssuer(this.cert.getAcinfo().getIssuer());
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public Date getNotBefore() {
        return this.notBefore;
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public Date getNotAfter() {
        return this.notAfter;
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public boolean[] getIssuerUniqueID() {
        DERBitString issuerUniqueID = this.cert.getAcinfo().getIssuerUniqueID();
        if (issuerUniqueID == null) {
            return null;
        }
        byte[] bytes = issuerUniqueID.getBytes();
        boolean[] zArr = new boolean[((bytes.length * 8) - issuerUniqueID.getPadBits())];
        for (int i = 0; i != zArr.length; i++) {
            zArr[i] = (bytes[i / 8] & (128 >>> (i % 8))) != 0;
        }
        return zArr;
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public void checkValidity() {
        checkValidity(new Date());
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public void checkValidity(Date date) {
        if (date.after(getNotAfter())) {
            throw new CertificateExpiredException("certificate expired on " + getNotAfter());
        } else if (date.before(getNotBefore())) {
            throw new CertificateNotYetValidException("certificate not valid till " + getNotBefore());
        }
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public byte[] getSignature() {
        return this.cert.getSignatureValue().getOctets();
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public final void verify(PublicKey publicKey, String str) {
        if (this.cert.getSignatureAlgorithm().equals(this.cert.getAcinfo().getSignature())) {
            Signature instance = Signature.getInstance(this.cert.getSignatureAlgorithm().getAlgorithm().getId(), str);
            instance.initVerify(publicKey);
            try {
                instance.update(this.cert.getAcinfo().getEncoded());
                if (!instance.verify(getSignature())) {
                    throw new InvalidKeyException("Public key presented not for certificate signature");
                }
            } catch (IOException unused) {
                throw new SignatureException("Exception encoding certificate info object");
            }
        } else {
            throw new CertificateException("Signature algorithm in certificate info not same as outer certificate");
        }
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public byte[] getEncoded() {
        return this.cert.getEncoded();
    }

    public byte[] getExtensionValue(String str) {
        Extension extension;
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions == null || (extension = extensions.getExtension(new ASN1ObjectIdentifier(str))) == null) {
            return null;
        }
        try {
            return extension.getExtnValue().getEncoded(ASN1Encoding.DER);
        } catch (Exception e) {
            throw new RuntimeException("error encoding " + e.toString());
        }
    }

    private Set getExtensionOIDs(boolean z) {
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration oids = extensions.oids();
        while (oids.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
            if (extensions.getExtension(aSN1ObjectIdentifier).isCritical() == z) {
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty();
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public X509Attribute[] getAttributes() {
        ASN1Sequence attributes = this.cert.getAcinfo().getAttributes();
        X509Attribute[] x509AttributeArr = new X509Attribute[attributes.size()];
        for (int i = 0; i != attributes.size(); i++) {
            x509AttributeArr[i] = new X509Attribute(attributes.getObjectAt(i));
        }
        return x509AttributeArr;
    }

    @Override // org.spongycastle.x509.X509AttributeCertificate
    public X509Attribute[] getAttributes(String str) {
        ASN1Sequence attributes = this.cert.getAcinfo().getAttributes();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i != attributes.size(); i++) {
            X509Attribute x509Attribute = new X509Attribute(attributes.getObjectAt(i));
            if (x509Attribute.getOID().equals(str)) {
                arrayList.add(x509Attribute);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (X509Attribute[]) arrayList.toArray(new X509Attribute[arrayList.size()]);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof X509AttributeCertificate)) {
            return false;
        }
        try {
            return Arrays.areEqual(getEncoded(), ((X509AttributeCertificate) obj).getEncoded());
        } catch (IOException unused) {
            return false;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(getEncoded());
        } catch (IOException unused) {
            return 0;
        }
    }
}
