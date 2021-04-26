package org.spongycastle.jce.provider;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collection;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.SignedData;
import org.spongycastle.x509.X509StreamParserSpi;
import org.spongycastle.x509.util.StreamParsingException;

public class X509CertParser extends X509StreamParserSpi {
    private static final PEMUtil PEM_PARSER = new PEMUtil("CERTIFICATE");
    private InputStream currentStream = null;
    private ASN1Set sData = null;
    private int sDataObjectCount = 0;

    private Certificate readDERCertificate(InputStream inputStream) {
        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(inputStream).readObject();
        if (aSN1Sequence.size() <= 1 || !(aSN1Sequence.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !aSN1Sequence.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            return new X509CertificateObject(org.spongycastle.asn1.x509.Certificate.getInstance(aSN1Sequence));
        }
        this.sData = new SignedData(ASN1Sequence.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true)).getCertificates();
        return getCertificate();
    }

    private Certificate getCertificate() {
        if (this.sData == null) {
            return null;
        }
        while (this.sDataObjectCount < this.sData.size()) {
            ASN1Set aSN1Set = this.sData;
            int i = this.sDataObjectCount;
            this.sDataObjectCount = i + 1;
            ASN1Encodable objectAt = aSN1Set.getObjectAt(i);
            if (objectAt instanceof ASN1Sequence) {
                return new X509CertificateObject(org.spongycastle.asn1.x509.Certificate.getInstance(objectAt));
            }
        }
        return null;
    }

    private Certificate readPEMCertificate(InputStream inputStream) {
        ASN1Sequence readPEMObject = PEM_PARSER.readPEMObject(inputStream);
        if (readPEMObject != null) {
            return new X509CertificateObject(org.spongycastle.asn1.x509.Certificate.getInstance(readPEMObject));
        }
        return null;
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public void engineInit(InputStream inputStream) {
        this.currentStream = inputStream;
        this.sData = null;
        this.sDataObjectCount = 0;
        if (!this.currentStream.markSupported()) {
            this.currentStream = new BufferedInputStream(this.currentStream);
        }
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Object engineRead() {
        try {
            if (this.sData == null) {
                this.currentStream.mark(10);
                int read = this.currentStream.read();
                if (read == -1) {
                    return null;
                }
                if (read != 48) {
                    this.currentStream.reset();
                    return readPEMCertificate(this.currentStream);
                }
                this.currentStream.reset();
                return readDERCertificate(this.currentStream);
            } else if (this.sDataObjectCount != this.sData.size()) {
                return getCertificate();
            } else {
                this.sData = null;
                this.sDataObjectCount = 0;
                return null;
            }
        } catch (Exception e) {
            throw new StreamParsingException(e.toString(), e);
        }
    }

    @Override // org.spongycastle.x509.X509StreamParserSpi
    public Collection engineReadAll() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Certificate certificate = (Certificate) engineRead();
            if (certificate == null) {
                return arrayList;
            }
            arrayList.add(certificate);
        }
    }
}
