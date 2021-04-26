package org.spongycastle.asn1.esf;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class OtherHash extends ASN1Object implements ASN1Choice {
    private OtherHashAlgAndValue otherHash;
    private ASN1OctetString sha1Hash;

    public static OtherHash getInstance(Object obj) {
        if (obj instanceof OtherHash) {
            return (OtherHash) obj;
        }
        if (obj instanceof ASN1OctetString) {
            return new OtherHash((ASN1OctetString) obj);
        }
        return new OtherHash(OtherHashAlgAndValue.getInstance(obj));
    }

    private OtherHash(ASN1OctetString aSN1OctetString) {
        this.sha1Hash = aSN1OctetString;
    }

    public OtherHash(OtherHashAlgAndValue otherHashAlgAndValue) {
        this.otherHash = otherHashAlgAndValue;
    }

    public OtherHash(byte[] bArr) {
        this.sha1Hash = new DEROctetString(bArr);
    }

    public AlgorithmIdentifier getHashAlgorithm() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        if (otherHashAlgAndValue == null) {
            return new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1);
        }
        return otherHashAlgAndValue.getHashAlgorithm();
    }

    public byte[] getHashValue() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        if (otherHashAlgAndValue == null) {
            return this.sha1Hash.getOctets();
        }
        return otherHashAlgAndValue.getHashValue().getOctets();
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        OtherHashAlgAndValue otherHashAlgAndValue = this.otherHash;
        if (otherHashAlgAndValue == null) {
            return this.sha1Hash;
        }
        return otherHashAlgAndValue.toASN1Primitive();
    }
}
