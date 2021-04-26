package org.spongycastle.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.x509.Attribute;

public class X509Attribute extends ASN1Object {
    Attribute attr;

    X509Attribute(ASN1Encodable aSN1Encodable) {
        this.attr = Attribute.getInstance(aSN1Encodable);
    }

    public X509Attribute(String str, ASN1Encodable aSN1Encodable) {
        this.attr = new Attribute(new ASN1ObjectIdentifier(str), new DERSet(aSN1Encodable));
    }

    public X509Attribute(String str, ASN1EncodableVector aSN1EncodableVector) {
        this.attr = new Attribute(new ASN1ObjectIdentifier(str), new DERSet(aSN1EncodableVector));
    }

    public String getOID() {
        return this.attr.getAttrType().getId();
    }

    public ASN1Encodable[] getValues() {
        ASN1Set attrValues = this.attr.getAttrValues();
        ASN1Encodable[] aSN1EncodableArr = new ASN1Encodable[attrValues.size()];
        for (int i = 0; i != attrValues.size(); i++) {
            aSN1EncodableArr[i] = attrValues.getObjectAt(i);
        }
        return aSN1EncodableArr;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.attr.toASN1Primitive();
    }
}
