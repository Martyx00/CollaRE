package org.spongycastle.asn1.ess;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERUTF8String;

public class ContentHints extends ASN1Object {
    private DERUTF8String contentDescription;
    private ASN1ObjectIdentifier contentType;

    public static ContentHints getInstance(Object obj) {
        if (obj instanceof ContentHints) {
            return (ContentHints) obj;
        }
        if (obj != null) {
            return new ContentHints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private ContentHints(ASN1Sequence aSN1Sequence) {
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(0);
        if (objectAt.toASN1Primitive() instanceof DERUTF8String) {
            this.contentDescription = DERUTF8String.getInstance(objectAt);
            this.contentType = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            return;
        }
        this.contentType = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
    }

    public ContentHints(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.contentType = aSN1ObjectIdentifier;
        this.contentDescription = null;
    }

    public ContentHints(ASN1ObjectIdentifier aSN1ObjectIdentifier, DERUTF8String dERUTF8String) {
        this.contentType = aSN1ObjectIdentifier;
        this.contentDescription = dERUTF8String;
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public DERUTF8String getContentDescription() {
        return this.contentDescription;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        DERUTF8String dERUTF8String = this.contentDescription;
        if (dERUTF8String != null) {
            aSN1EncodableVector.add(dERUTF8String);
        }
        aSN1EncodableVector.add(this.contentType);
        return new DERSequence(aSN1EncodableVector);
    }
}
