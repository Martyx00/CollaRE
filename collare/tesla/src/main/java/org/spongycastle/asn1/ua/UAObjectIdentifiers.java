package org.spongycastle.asn1.ua;

import org.spongycastle.asn1.ASN1ObjectIdentifier;

public interface UAObjectIdentifiers {
    public static final ASN1ObjectIdentifier UaOid = new ASN1ObjectIdentifier("1.2.804.2.1.1.1");
    public static final ASN1ObjectIdentifier dstu4145be = UaOid.branch("1.3.1.1.1.1");
    public static final ASN1ObjectIdentifier dstu4145le = UaOid.branch("1.3.1.1");
}
