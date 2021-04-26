package org.spongycastle.asn1.x500.style;

import org.spongycastle.asn1.x500.RDN;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameStyle;

public class BCStrictStyle extends BCStyle {
    public static final X500NameStyle INSTANCE = new BCStrictStyle();

    @Override // org.spongycastle.asn1.x500.style.AbstractX500NameStyle, org.spongycastle.asn1.x500.X500NameStyle
    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        RDN[] rDNs = x500Name.getRDNs();
        RDN[] rDNs2 = x500Name2.getRDNs();
        if (rDNs.length != rDNs2.length) {
            return false;
        }
        for (int i = 0; i != rDNs.length; i++) {
            if (!rdnAreEqual(rDNs[i], rDNs2[i])) {
                return false;
            }
        }
        return true;
    }
}
