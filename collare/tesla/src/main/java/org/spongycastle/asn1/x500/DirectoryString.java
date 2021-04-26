package org.spongycastle.asn1.x500;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERUniversalString;

public class DirectoryString extends ASN1Object implements ASN1Choice, ASN1String {
    private ASN1String string;

    public static DirectoryString getInstance(Object obj) {
        if (obj == null || (obj instanceof DirectoryString)) {
            return (DirectoryString) obj;
        }
        if (obj instanceof DERT61String) {
            return new DirectoryString((DERT61String) obj);
        }
        if (obj instanceof DERPrintableString) {
            return new DirectoryString((DERPrintableString) obj);
        }
        if (obj instanceof DERUniversalString) {
            return new DirectoryString((DERUniversalString) obj);
        }
        if (obj instanceof DERUTF8String) {
            return new DirectoryString((DERUTF8String) obj);
        }
        if (obj instanceof DERBMPString) {
            return new DirectoryString((DERBMPString) obj);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static DirectoryString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        if (z) {
            return getInstance(aSN1TaggedObject.getObject());
        }
        throw new IllegalArgumentException("choice item must be explicitly tagged");
    }

    private DirectoryString(DERT61String dERT61String) {
        this.string = dERT61String;
    }

    private DirectoryString(DERPrintableString dERPrintableString) {
        this.string = dERPrintableString;
    }

    private DirectoryString(DERUniversalString dERUniversalString) {
        this.string = dERUniversalString;
    }

    private DirectoryString(DERUTF8String dERUTF8String) {
        this.string = dERUTF8String;
    }

    private DirectoryString(DERBMPString dERBMPString) {
        this.string = dERBMPString;
    }

    public DirectoryString(String str) {
        this.string = new DERUTF8String(str);
    }

    @Override // org.spongycastle.asn1.ASN1String
    public String getString() {
        return this.string.getString();
    }

    public String toString() {
        return this.string.getString();
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return ((ASN1Encodable) this.string).toASN1Primitive();
    }
}
