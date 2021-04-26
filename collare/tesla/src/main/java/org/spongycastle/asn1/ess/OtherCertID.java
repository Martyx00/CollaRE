package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.IssuerSerial;

public class OtherCertID extends ASN1Object {
    private IssuerSerial issuerSerial;
    private ASN1Encodable otherCertHash;

    public static OtherCertID getInstance(Object obj) {
        if (obj instanceof OtherCertID) {
            return (OtherCertID) obj;
        }
        if (obj != null) {
            return new OtherCertID(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherCertID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 1 || aSN1Sequence.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        if (aSN1Sequence.getObjectAt(0).toASN1Primitive() instanceof ASN1OctetString) {
            this.otherCertHash = ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.otherCertHash = DigestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        }
        if (aSN1Sequence.size() > 1) {
            this.issuerSerial = IssuerSerial.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) {
        this.otherCertHash = new DigestInfo(algorithmIdentifier, bArr);
    }

    public OtherCertID(AlgorithmIdentifier algorithmIdentifier, byte[] bArr, IssuerSerial issuerSerial2) {
        this.otherCertHash = new DigestInfo(algorithmIdentifier, bArr);
        this.issuerSerial = issuerSerial2;
    }

    public AlgorithmIdentifier getAlgorithmHash() {
        if (this.otherCertHash.toASN1Primitive() instanceof ASN1OctetString) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }
        return DigestInfo.getInstance(this.otherCertHash).getAlgorithmId();
    }

    public byte[] getCertHash() {
        if (this.otherCertHash.toASN1Primitive() instanceof ASN1OctetString) {
            return ((ASN1OctetString) this.otherCertHash.toASN1Primitive()).getOctets();
        }
        return DigestInfo.getInstance(this.otherCertHash).getDigest();
    }

    public IssuerSerial getIssuerSerial() {
        return this.issuerSerial;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.otherCertHash);
        IssuerSerial issuerSerial2 = this.issuerSerial;
        if (issuerSerial2 != null) {
            aSN1EncodableVector.add(issuerSerial2);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
