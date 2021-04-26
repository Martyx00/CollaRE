package org.spongycastle.asn1.isismtt.x509;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.x500.DirectoryString;

public class AdditionalInformationSyntax extends ASN1Object {
    private DirectoryString information;

    public static AdditionalInformationSyntax getInstance(Object obj) {
        if (obj instanceof AdditionalInformationSyntax) {
            return (AdditionalInformationSyntax) obj;
        }
        if (obj != null) {
            return new AdditionalInformationSyntax(DirectoryString.getInstance(obj));
        }
        return null;
    }

    private AdditionalInformationSyntax(DirectoryString directoryString) {
        this.information = directoryString;
    }

    public AdditionalInformationSyntax(String str) {
        this(new DirectoryString(str));
    }

    public DirectoryString getInformation() {
        return this.information;
    }

    @Override // org.spongycastle.asn1.ASN1Encodable, org.spongycastle.asn1.ASN1Object
    public ASN1Primitive toASN1Primitive() {
        return this.information.toASN1Primitive();
    }
}
