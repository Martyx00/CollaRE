package org.spongycastle.asn1.x9;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class OtherInfo extends ASN1Object {
    private KeySpecificInfo keyInfo;
    private ASN1OctetString partyAInfo;
    private ASN1OctetString suppPubInfo;

    public OtherInfo(KeySpecificInfo keySpecificInfo, ASN1OctetString aSN1OctetString, ASN1OctetString aSN1OctetString2) {
        this.keyInfo = keySpecificInfo;
        this.partyAInfo = aSN1OctetString;
        this.suppPubInfo = aSN1OctetString2;
    }

    public static OtherInfo getInstance(Object obj) {
        if (obj instanceof OtherInfo) {
            return (OtherInfo) obj;
        }
        if (obj != null) {
            return new OtherInfo(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private OtherInfo(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.keyInfo = KeySpecificInfo.getInstance(objects.nextElement());
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.partyAInfo = (ASN1OctetString) aSN1TaggedObject.getObject();
            } else if (aSN1TaggedObject.getTagNo() == 2) {
                this.suppPubInfo = (ASN1OctetString) aSN1TaggedObject.getObject();
            }
        }
    }

    public KeySpecificInfo getKeyInfo() {
        return this.keyInfo;
    }

    public ASN1OctetString getPartyAInfo() {
        return this.partyAInfo;
    }

    public ASN1OctetString getSuppPubInfo() {
        return this.suppPubInfo;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.keyInfo);
        ASN1OctetString aSN1OctetString = this.partyAInfo;
        if (aSN1OctetString != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, aSN1OctetString));
        }
        aSN1EncodableVector.add(new DERTaggedObject(2, this.suppPubInfo));
        return new DERSequence(aSN1EncodableVector);
    }
}
