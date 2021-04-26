package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1TaggedObjectParser;

public class ContentInfoParser {
    private ASN1TaggedObjectParser content;
    private ASN1ObjectIdentifier contentType;

    public ContentInfoParser(ASN1SequenceParser aSN1SequenceParser) {
        this.contentType = (ASN1ObjectIdentifier) aSN1SequenceParser.readObject();
        this.content = (ASN1TaggedObjectParser) aSN1SequenceParser.readObject();
    }

    public ASN1ObjectIdentifier getContentType() {
        return this.contentType;
    }

    public ASN1Encodable getContent(int i) {
        ASN1TaggedObjectParser aSN1TaggedObjectParser = this.content;
        if (aSN1TaggedObjectParser != null) {
            return aSN1TaggedObjectParser.getObjectParser(i, true);
        }
        return null;
    }
}
