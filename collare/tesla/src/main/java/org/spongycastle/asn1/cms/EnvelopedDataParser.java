package org.spongycastle.asn1.cms;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1SequenceParser;
import org.spongycastle.asn1.ASN1SetParser;
import org.spongycastle.asn1.ASN1TaggedObjectParser;

public class EnvelopedDataParser {
    private ASN1Encodable _nextObject;
    private boolean _originatorInfoCalled;
    private ASN1SequenceParser _seq;
    private ASN1Integer _version;

    public EnvelopedDataParser(ASN1SequenceParser aSN1SequenceParser) {
        this._seq = aSN1SequenceParser;
        this._version = ASN1Integer.getInstance(aSN1SequenceParser.readObject());
    }

    public ASN1Integer getVersion() {
        return this._version;
    }

    public OriginatorInfo getOriginatorInfo() {
        this._originatorInfoCalled = true;
        if (this._nextObject == null) {
            this._nextObject = this._seq.readObject();
        }
        ASN1Encodable aSN1Encodable = this._nextObject;
        if (!(aSN1Encodable instanceof ASN1TaggedObjectParser) || ((ASN1TaggedObjectParser) aSN1Encodable).getTagNo() != 0) {
            return null;
        }
        this._nextObject = null;
        return OriginatorInfo.getInstance(((ASN1SequenceParser) ((ASN1TaggedObjectParser) this._nextObject).getObjectParser(16, false)).toASN1Primitive());
    }

    public ASN1SetParser getRecipientInfos() {
        if (!this._originatorInfoCalled) {
            getOriginatorInfo();
        }
        if (this._nextObject == null) {
            this._nextObject = this._seq.readObject();
        }
        ASN1SetParser aSN1SetParser = (ASN1SetParser) this._nextObject;
        this._nextObject = null;
        return aSN1SetParser;
    }

    public EncryptedContentInfoParser getEncryptedContentInfo() {
        if (this._nextObject == null) {
            this._nextObject = this._seq.readObject();
        }
        ASN1Encodable aSN1Encodable = this._nextObject;
        if (aSN1Encodable == null) {
            return null;
        }
        this._nextObject = null;
        return new EncryptedContentInfoParser((ASN1SequenceParser) aSN1Encodable);
    }

    public ASN1SetParser getUnprotectedAttrs() {
        if (this._nextObject == null) {
            this._nextObject = this._seq.readObject();
        }
        ASN1Encodable aSN1Encodable = this._nextObject;
        if (aSN1Encodable == null) {
            return null;
        }
        this._nextObject = null;
        return (ASN1SetParser) ((ASN1TaggedObjectParser) aSN1Encodable).getObjectParser(17, false);
    }
}
