package org.spongycastle.jce;

import java.util.Enumeration;

public class ECNamedCurveTable {
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0011, code lost:
        r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByName(r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.spongycastle.jce.spec.ECNamedCurveParameterSpec getParameterSpec(java.lang.String r8) {
        /*
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.crypto.ec.CustomNamedCurves.getByName(r8)
            if (r0 != 0) goto L_0x0022
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ IllegalArgumentException -> 0x000f }
            r1.<init>(r8)     // Catch:{ IllegalArgumentException -> 0x000f }
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.crypto.ec.CustomNamedCurves.getByOID(r1)     // Catch:{ IllegalArgumentException -> 0x000f }
        L_0x000f:
            if (r0 != 0) goto L_0x0022
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByName(r8)
            if (r0 != 0) goto L_0x0022
            org.spongycastle.asn1.ASN1ObjectIdentifier r1 = new org.spongycastle.asn1.ASN1ObjectIdentifier     // Catch:{ IllegalArgumentException -> 0x0021 }
            r1.<init>(r8)     // Catch:{ IllegalArgumentException -> 0x0021 }
            org.spongycastle.asn1.x9.X9ECParameters r0 = org.spongycastle.asn1.x9.ECNamedCurveTable.getByOID(r1)     // Catch:{ IllegalArgumentException -> 0x0021 }
            goto L_0x0022
        L_0x0021:
        L_0x0022:
            if (r0 != 0) goto L_0x0026
            r8 = 0
            return r8
        L_0x0026:
            org.spongycastle.jce.spec.ECNamedCurveParameterSpec r7 = new org.spongycastle.jce.spec.ECNamedCurveParameterSpec
            org.spongycastle.math.ec.ECCurve r2 = r0.getCurve()
            org.spongycastle.math.ec.ECPoint r3 = r0.getG()
            java.math.BigInteger r4 = r0.getN()
            java.math.BigInteger r5 = r0.getH()
            byte[] r6 = r0.getSeed()
            r0 = r7
            r1 = r8
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jce.ECNamedCurveTable.getParameterSpec(java.lang.String):org.spongycastle.jce.spec.ECNamedCurveParameterSpec");
    }

    public static Enumeration getNames() {
        return org.spongycastle.asn1.x9.ECNamedCurveTable.getNames();
    }
}
