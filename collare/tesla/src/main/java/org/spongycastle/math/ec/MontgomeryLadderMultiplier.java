package org.spongycastle.math.ec;

public class MontgomeryLadderMultiplier extends AbstractECMultiplier {
    /* JADX WARN: Type inference failed for: r3v0, types: [boolean] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // org.spongycastle.math.ec.AbstractECMultiplier
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.spongycastle.math.ec.ECPoint multiplyPositive(org.spongycastle.math.ec.ECPoint r8, java.math.BigInteger r9) {
        /*
            r7 = this;
            r0 = 2
            org.spongycastle.math.ec.ECPoint[] r0 = new org.spongycastle.math.ec.ECPoint[r0]
            org.spongycastle.math.ec.ECCurve r1 = r8.getCurve()
            org.spongycastle.math.ec.ECPoint r1 = r1.getInfinity()
            r2 = 0
            r0[r2] = r1
            r1 = 1
            r0[r1] = r8
            int r8 = r9.bitLength()
        L_0x0015:
            int r8 = r8 + -1
            if (r8 < 0) goto L_0x0032
            boolean r3 = r9.testBit(r8)
            int r4 = 1 - r3
            r5 = r0[r4]
            r6 = r0[r3]
            org.spongycastle.math.ec.ECPoint r5 = r5.add(r6)
            r0[r4] = r5
            r4 = r0[r3]
            org.spongycastle.math.ec.ECPoint r4 = r4.twice()
            r0[r3] = r4
            goto L_0x0015
        L_0x0032:
            r8 = r0[r2]
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.ec.MontgomeryLadderMultiplier.multiplyPositive(org.spongycastle.math.ec.ECPoint, java.math.BigInteger):org.spongycastle.math.ec.ECPoint");
    }
}
