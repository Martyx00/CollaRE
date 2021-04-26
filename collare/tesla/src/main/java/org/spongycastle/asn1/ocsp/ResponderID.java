package org.spongycastle.asn1.ocsp;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x500.X500Name;

public class ResponderID extends ASN1Object implements ASN1Choice {
    private ASN1Encodable value;

    public ResponderID(ASN1OctetString aSN1OctetString) {
        this.value = aSN1OctetString;
    }

    public ResponderID(X500Name x500Name) {
        this.value = x500Name;
    }

    public static ResponderID getInstance(Object obj) {
        if (obj instanceof ResponderID) {
            return (ResponderID) obj;
        }
        if (obj instanceof DEROctetString) {
            return new ResponderID((DEROctetString) obj);
        }
        if (!(obj instanceof ASN1TaggedObject)) {
            return new ResponderID(X500Name.getInstance(obj));
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) obj;
        if (aSN1TaggedObject.getTagNo() == 1) {
            return new ResponderID(X500Name.getInstance(aSN1TaggedObject, true));
        }
        return new ResponderID(ASN1OctetString.getInstance(aSN1TaggedObject, true));
    }

    public static ResponderID getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(aSN1TaggedObject.getObject());
    }

    public byte[] getKeyHash() {
        ASN1Encodable aSN1Encodable = this.value;
        if (aSN1Encodable instanceof ASN1OctetString) {
            return ((ASN1OctetString) aSN1Encodable).getOctets();
        }
        return null;
    }

    public X500Name getName() {
        ASN1Encodable aSN1Encodable = this.value;
        if (aSN1Encodable instanceof ASN1OctetString) {
            return null;
        }
        return X500Name.getInstance(aSN1Encodable);
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1Encodable aSN1Encodable = this.value;
        if (aSN1Encodable instanceof ASN1OctetString) {
            return new DERTaggedObject(true, 2, aSN1Encodable);
        }
        return new DERTaggedObject(true, 1, aSN1Encodable);
    }
}
