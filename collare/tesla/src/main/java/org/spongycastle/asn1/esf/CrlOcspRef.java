package org.spongycastle.asn1.esf;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class CrlOcspRef extends ASN1Object {
    private CrlListID crlids;
    private OcspListID ocspids;
    private OtherRevRefs otherRev;

    public static CrlOcspRef getInstance(Object obj) {
        if (obj instanceof CrlOcspRef) {
            return (CrlOcspRef) obj;
        }
        if (obj != null) {
            return new CrlOcspRef(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private CrlOcspRef(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objects.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.crlids = CrlListID.getInstance(aSN1TaggedObject.getObject());
                    break;
                case 1:
                    this.ocspids = OcspListID.getInstance(aSN1TaggedObject.getObject());
                    break;
                case 2:
                    this.otherRev = OtherRevRefs.getInstance(aSN1TaggedObject.getObject());
                    break;
                default:
                    throw new IllegalArgumentException("illegal tag");
            }
        }
    }

    public CrlOcspRef(CrlListID crlListID, OcspListID ocspListID, OtherRevRefs otherRevRefs) {
        this.crlids = crlListID;
        this.ocspids = ocspListID;
        this.otherRev = otherRevRefs;
    }

    public CrlListID getCrlids() {
        return this.crlids;
    }

    public OcspListID getOcspids() {
        return this.ocspids;
    }

    public OtherRevRefs getOtherRev() {
        return this.otherRev;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        CrlListID crlListID = this.crlids;
        if (crlListID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, crlListID.toASN1Primitive()));
        }
        OcspListID ocspListID = this.ocspids;
        if (ocspListID != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, ocspListID.toASN1Primitive()));
        }
        OtherRevRefs otherRevRefs = this.otherRev;
        if (otherRevRefs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, otherRevRefs.toASN1Primitive()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
