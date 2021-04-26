package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class AuthorityInformationAccess extends ASN1Object {
    private AccessDescription[] descriptions;

    public static AuthorityInformationAccess getInstance(Object obj) {
        if (obj instanceof AuthorityInformationAccess) {
            return (AuthorityInformationAccess) obj;
        }
        if (obj != null) {
            return new AuthorityInformationAccess(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static AuthorityInformationAccess fromExtensions(Extensions extensions) {
        return getInstance(extensions.getExtensionParsedValue(Extension.authorityInfoAccess));
    }

    private AuthorityInformationAccess(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() >= 1) {
            this.descriptions = new AccessDescription[aSN1Sequence.size()];
            for (int i = 0; i != aSN1Sequence.size(); i++) {
                this.descriptions[i] = AccessDescription.getInstance(aSN1Sequence.getObjectAt(i));
            }
            return;
        }
        throw new IllegalArgumentException("sequence may not be empty");
    }

    public AuthorityInformationAccess(AccessDescription accessDescription) {
        this(new AccessDescription[]{accessDescription});
    }

    public AuthorityInformationAccess(AccessDescription[] accessDescriptionArr) {
        this.descriptions = new AccessDescription[accessDescriptionArr.length];
        System.arraycopy(accessDescriptionArr, 0, this.descriptions, 0, accessDescriptionArr.length);
    }

    public AuthorityInformationAccess(ASN1ObjectIdentifier aSN1ObjectIdentifier, GeneralName generalName) {
        this(new AccessDescription(aSN1ObjectIdentifier, generalName));
    }

    public AccessDescription[] getAccessDescriptions() {
        return this.descriptions;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        int i = 0;
        while (true) {
            AccessDescription[] accessDescriptionArr = this.descriptions;
            if (i == accessDescriptionArr.length) {
                return new DERSequence(aSN1EncodableVector);
            }
            aSN1EncodableVector.add(accessDescriptionArr[i]);
            i++;
        }
    }

    public String toString() {
        return "AuthorityInformationAccess: Oid(" + this.descriptions[0].getAccessMethod().getId() + ")";
    }
}
