package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1TaggedObjectParser;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;

public class EncryptedContentInfoParser {
    private AlgorithmIdentifier _contentEncryptionAlgorithm;
    private ASN1ObjectIdentifier _contentType;
    private ASN1TaggedObjectParser _encryptedContent;

    public EncryptedContentInfoParser(ASN1SequenceParser aSN1SequenceParser) {
        this._contentType = (ASN1ObjectIdentifier) aSN1SequenceParser.readObject();
        this._contentEncryptionAlgorithm = AlgorithmIdentifier.getInstance(aSN1SequenceParser.readObject().toASN1Primitive());
        this._encryptedContent = (ASN1TaggedObjectParser) aSN1SequenceParser.readObject();
    }

    public ASN1ObjectIdentifier getContentType() {
        return this._contentType;
    }

    public AlgorithmIdentifier getContentEncryptionAlgorithm() {
        return this._contentEncryptionAlgorithm;
    }

    public ASN1Encodable getEncryptedContent(int i) {
        return this._encryptedContent.getObjectParser(i, false);
    }
}
