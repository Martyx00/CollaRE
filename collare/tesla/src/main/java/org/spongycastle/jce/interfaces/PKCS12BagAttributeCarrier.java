package org.spongycastle.jce.interfaces;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface PKCS12BagAttributeCarrier {
    ASN1Encodable getBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    Enumeration getBagAttributeKeys();

    void setBagAttribute(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable);
}
