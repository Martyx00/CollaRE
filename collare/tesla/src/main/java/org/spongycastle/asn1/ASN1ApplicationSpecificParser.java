package org.spongycastle.asn1;

public interface ASN1ApplicationSpecificParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable readObject();
}
