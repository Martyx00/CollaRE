package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.x500.DirectoryString;

public class SignerLocation extends ASN1Object {
    private DERUTF8String countryName;
    private DERUTF8String localityName;
    private ASN1Sequence postalAddress;

    private SignerLocation(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.countryName = new DERUTF8String(DirectoryString.getInstance(aSN1TaggedObject, true).getString());
                    break;
                case 1:
                    this.localityName = new DERUTF8String(DirectoryString.getInstance(aSN1TaggedObject, true).getString());
                    break;
                case 2:
                    if (aSN1TaggedObject.isExplicit()) {
                        this.postalAddress = ASN1Sequence.getInstance(aSN1TaggedObject, true);
                    } else {
                        this.postalAddress = ASN1Sequence.getInstance(aSN1TaggedObject, false);
                    }
                    ASN1Sequence aSN1Sequence2 = this.postalAddress;
                    if (aSN1Sequence2 != null && aSN1Sequence2.size() > 6) {
                        throw new IllegalArgumentException("postal address must contain less than 6 strings");
                    }
                default:
                    throw new IllegalArgumentException("illegal tag");
            }
        }
    }

    public SignerLocation(DERUTF8String dERUTF8String, DERUTF8String dERUTF8String2, ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null || aSN1Sequence.size() <= 6) {
            if (dERUTF8String != null) {
                this.countryName = DERUTF8String.getInstance(dERUTF8String.toASN1Primitive());
            }
            if (dERUTF8String2 != null) {
                this.localityName = DERUTF8String.getInstance(dERUTF8String2.toASN1Primitive());
            }
            if (aSN1Sequence != null) {
                this.postalAddress = ASN1Sequence.getInstance(aSN1Sequence.toASN1Primitive());
                return;
            }
            return;
        }
        throw new IllegalArgumentException("postal address must contain less than 6 strings");
    }

    public static SignerLocation getInstance(Object obj) {
        if (obj == null || (obj instanceof SignerLocation)) {
            return (SignerLocation) obj;
        }
        return new SignerLocation(ASN1Sequence.getInstance(obj));
    }

    public DERUTF8String getCountryName() {
        return this.countryName;
    }

    public DERUTF8String getLocalityName() {
        return this.localityName;
    }

    public ASN1Sequence getPostalAddress() {
        return this.postalAddress;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        DERUTF8String dERUTF8String = this.countryName;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, dERUTF8String));
        }
        DERUTF8String dERUTF8String2 = this.localityName;
        if (dERUTF8String2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, dERUTF8String2));
        }
        ASN1Sequence aSN1Sequence = this.postalAddress;
        if (aSN1Sequence != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, aSN1Sequence));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
