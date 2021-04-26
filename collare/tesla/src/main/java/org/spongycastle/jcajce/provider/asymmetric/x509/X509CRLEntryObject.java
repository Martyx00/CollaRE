package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Enumerated;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.CRLReason;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.TBSCertList;
import org.spongycastle.util.Strings;

class X509CRLEntryObject extends X509CRLEntry {

    /* renamed from: c  reason: collision with root package name */
    private TBSCertList.CRLEntry f6414c;
    private X500Name certificateIssuer;
    private int hashValue;
    private boolean isHashValueSet;

    protected X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry) {
        this.f6414c = cRLEntry;
        this.certificateIssuer = null;
    }

    protected X509CRLEntryObject(TBSCertList.CRLEntry cRLEntry, boolean z, X500Name x500Name) {
        this.f6414c = cRLEntry;
        this.certificateIssuer = loadCertificateIssuer(z, x500Name);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set criticalExtensionOIDs = getCriticalExtensionOIDs();
        return criticalExtensionOIDs != null && !criticalExtensionOIDs.isEmpty();
    }

    private X500Name loadCertificateIssuer(boolean z, X500Name x500Name) {
        if (!z) {
            return null;
        }
        Extension extension = getExtension(Extension.certificateIssuer);
        if (extension == null) {
            return x500Name;
        }
        try {
            GeneralName[] names = GeneralNames.getInstance(extension.getParsedValue()).getNames();
            for (int i = 0; i < names.length; i++) {
                if (names[i].getTagNo() == 4) {
                    return X500Name.getInstance(names[i].getName());
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public X500Principal getCertificateIssuer() {
        X500Name x500Name = this.certificateIssuer;
        if (x500Name == null) {
            return null;
        }
        try {
            return new X500Principal(x500Name.getEncoded());
        } catch (IOException unused) {
            return null;
        }
    }

    private Set getExtensionOIDs(boolean z) {
        Extensions extensions = this.f6414c.getExtensions();
        if (extensions == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        Enumeration oids = extensions.oids();
        while (oids.hasMoreElements()) {
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
            if (z == extensions.getExtension(aSN1ObjectIdentifier).isCritical()) {
                hashSet.add(aSN1ObjectIdentifier.getId());
            }
        }
        return hashSet;
    }

    @Override // java.security.cert.X509Extension
    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    @Override // java.security.cert.X509Extension
    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    private Extension getExtension(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Extensions extensions = this.f6414c.getExtensions();
        if (extensions != null) {
            return extensions.getExtension(aSN1ObjectIdentifier);
        }
        return null;
    }

    public byte[] getExtensionValue(String str) {
        Extension extension = getExtension(new ASN1ObjectIdentifier(str));
        if (extension == null) {
            return null;
        }
        try {
            return extension.getExtnValue().getEncoded();
        } catch (Exception e) {
            throw new IllegalStateException("Exception encoding: " + e.toString());
        }
    }

    public int hashCode() {
        if (!this.isHashValueSet) {
            this.hashValue = super.hashCode();
            this.isHashValueSet = true;
        }
        return this.hashValue;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof X509CRLEntryObject) {
            return this.f6414c.equals(((X509CRLEntryObject) obj).f6414c);
        }
        return super.equals(this);
    }

    @Override // java.security.cert.X509CRLEntry
    public byte[] getEncoded() {
        try {
            return this.f6414c.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public BigInteger getSerialNumber() {
        return this.f6414c.getUserCertificate().getValue();
    }

    public Date getRevocationDate() {
        return this.f6414c.getRevocationDate().getDate();
    }

    public boolean hasExtensions() {
        return this.f6414c.getExtensions() != null;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("      userCertificate: ");
        stringBuffer.append(getSerialNumber());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("       revocationDate: ");
        stringBuffer.append(getRevocationDate());
        stringBuffer.append(lineSeparator);
        stringBuffer.append("       certificateIssuer: ");
        stringBuffer.append(getCertificateIssuer());
        stringBuffer.append(lineSeparator);
        Extensions extensions = this.f6414c.getExtensions();
        if (extensions != null) {
            Enumeration oids = extensions.oids();
            if (oids.hasMoreElements()) {
                stringBuffer.append("   crlEntryExtensions:");
                stringBuffer.append(lineSeparator);
                while (oids.hasMoreElements()) {
                    ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) oids.nextElement();
                    Extension extension = extensions.getExtension(aSN1ObjectIdentifier);
                    if (extension.getExtnValue() != null) {
                        ASN1InputStream aSN1InputStream = new ASN1InputStream(extension.getExtnValue().getOctets());
                        stringBuffer.append("                       critical(");
                        stringBuffer.append(extension.isCritical());
                        stringBuffer.append(") ");
                        try {
                            if (aSN1ObjectIdentifier.equals(Extension.reasonCode)) {
                                stringBuffer.append(CRLReason.getInstance(ASN1Enumerated.getInstance(aSN1InputStream.readObject())));
                                stringBuffer.append(lineSeparator);
                            } else if (aSN1ObjectIdentifier.equals(Extension.certificateIssuer)) {
                                stringBuffer.append("Certificate issuer: ");
                                stringBuffer.append(GeneralNames.getInstance(aSN1InputStream.readObject()));
                                stringBuffer.append(lineSeparator);
                            } else {
                                stringBuffer.append(aSN1ObjectIdentifier.getId());
                                stringBuffer.append(" value = ");
                                stringBuffer.append(ASN1Dump.dumpAsString(aSN1InputStream.readObject()));
                                stringBuffer.append(lineSeparator);
                            }
                        } catch (Exception unused) {
                            stringBuffer.append(aSN1ObjectIdentifier.getId());
                            stringBuffer.append(" value = ");
                            stringBuffer.append("*****");
                            stringBuffer.append(lineSeparator);
                        }
                    } else {
                        stringBuffer.append(lineSeparator);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }
}
