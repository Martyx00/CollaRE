package org.spongycastle.asn1;

public interface ASN1TaggedObjectParser extends ASN1Encodable, InMemoryRepresentable {
    ASN1Encodable getObjectParser(int i, boolean z);

    int getTagNo();
}
