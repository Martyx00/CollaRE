package org.spongycastle.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public abstract class Primes {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    public static final int SMALL_FACTOR_LIMIT = 211;
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    public static class MROutput {
        private BigInteger factor;
        private boolean provablyComposite;

        /* access modifiers changed from: private */
        public static MROutput probablyPrime() {
            return new MROutput(false, null);
        }

        /* access modifiers changed from: private */
        public static MROutput provablyCompositeWithFactor(BigInteger bigInteger) {
            return new MROutput(true, bigInteger);
        }

        /* access modifiers changed from: private */
        public static MROutput provablyCompositeNotPrimePower() {
            return new MROutput(true, null);
        }

        private MROutput(boolean z, BigInteger bigInteger) {
            this.provablyComposite = z;
            this.factor = bigInteger;
        }

        public BigInteger getFactor() {
            return this.factor;
        }

        public boolean isProvablyComposite() {
            return this.provablyComposite;
        }

        public boolean isNotPrimePower() {
            return this.provablyComposite && this.factor == null;
        }
    }

    public static class STOutput {
        private BigInteger prime;
        private int primeGenCounter;
        private byte[] primeSeed;

        private STOutput(BigInteger bigInteger, byte[] bArr, int i) {
            this.prime = bigInteger;
            this.primeSeed = bArr;
            this.primeGenCounter = i;
        }

        public BigInteger getPrime() {
            return this.prime;
        }

        public byte[] getPrimeSeed() {
            return this.primeSeed;
        }

        public int getPrimeGenCounter() {
            return this.primeGenCounter;
        }
    }

    public static STOutput generateSTRandomPrime(Digest digest, int i, byte[] bArr) {
        if (digest == null) {
            throw new IllegalArgumentException("'hash' cannot be null");
        } else if (i < 2) {
            throw new IllegalArgumentException("'length' must be >= 2");
        } else if (bArr != null && bArr.length != 0) {
            return implSTRandomPrime(digest, i, Arrays.clone(bArr));
        } else {
            throw new IllegalArgumentException("'inputSeed' cannot be null or empty");
        }
    }

    public static MROutput enhancedMRProbablePrimeTest(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        BigInteger bigInteger2;
        boolean z;
        checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        } else if (i < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        } else if (bigInteger.bitLength() == 2) {
            return MROutput.probablyPrime();
        } else {
            if (!bigInteger.testBit(0)) {
                return MROutput.provablyCompositeWithFactor(TWO);
            }
            BigInteger subtract = bigInteger.subtract(ONE);
            BigInteger subtract2 = bigInteger.subtract(TWO);
            int lowestSetBit = subtract.getLowestSetBit();
            BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
            for (int i2 = 0; i2 < i; i2++) {
                BigInteger createRandomInRange = BigIntegers.createRandomInRange(TWO, subtract2, secureRandom);
                BigInteger gcd = createRandomInRange.gcd(bigInteger);
                if (gcd.compareTo(ONE) > 0) {
                    return MROutput.provablyCompositeWithFactor(gcd);
                }
                BigInteger modPow = createRandomInRange.modPow(shiftRight, bigInteger);
                if (!modPow.equals(ONE) && !modPow.equals(subtract)) {
                    BigInteger bigInteger3 = modPow;
                    int i3 = 1;
                    while (true) {
                        if (i3 >= lowestSetBit) {
                            bigInteger2 = bigInteger3;
                            z = false;
                            break;
                        }
                        bigInteger2 = bigInteger3.modPow(TWO, bigInteger);
                        if (bigInteger2.equals(subtract)) {
                            z = true;
                            break;
                        } else if (bigInteger2.equals(ONE)) {
                            z = false;
                            break;
                        } else {
                            i3++;
                            bigInteger3 = bigInteger2;
                        }
                    }
                    if (!z) {
                        if (!bigInteger2.equals(ONE)) {
                            bigInteger3 = bigInteger2.modPow(TWO, bigInteger);
                            if (bigInteger3.equals(ONE)) {
                                bigInteger3 = bigInteger2;
                            }
                        }
                        BigInteger gcd2 = bigInteger3.subtract(ONE).gcd(bigInteger);
                        if (gcd2.compareTo(ONE) > 0) {
                            return MROutput.provablyCompositeWithFactor(gcd2);
                        }
                        return MROutput.provablyCompositeNotPrimePower();
                    }
                }
            }
            return MROutput.probablyPrime();
        }
    }

    public static boolean hasAnySmallFactors(BigInteger bigInteger) {
        checkCandidate(bigInteger, "candidate");
        return implHasAnySmallFactors(bigInteger);
    }

    public static boolean isMRProbablePrime(BigInteger bigInteger, SecureRandom secureRandom, int i) {
        checkCandidate(bigInteger, "candidate");
        if (secureRandom == null) {
            throw new IllegalArgumentException("'random' cannot be null");
        } else if (i < 1) {
            throw new IllegalArgumentException("'iterations' must be > 0");
        } else if (bigInteger.bitLength() == 2) {
            return true;
        } else {
            if (!bigInteger.testBit(0)) {
                return false;
            }
            BigInteger subtract = bigInteger.subtract(ONE);
            BigInteger subtract2 = bigInteger.subtract(TWO);
            int lowestSetBit = subtract.getLowestSetBit();
            BigInteger shiftRight = subtract.shiftRight(lowestSetBit);
            for (int i2 = 0; i2 < i; i2++) {
                if (!implMRProbablePrimeToBase(bigInteger, subtract, shiftRight, lowestSetBit, BigIntegers.createRandomInRange(TWO, subtract2, secureRandom))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean isMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2) {
        checkCandidate(bigInteger, "candidate");
        checkCandidate(bigInteger2, "base");
        if (bigInteger2.compareTo(bigInteger.subtract(ONE)) >= 0) {
            throw new IllegalArgumentException("'base' must be < ('candidate' - 1)");
        } else if (bigInteger.bitLength() == 2) {
            return true;
        } else {
            BigInteger subtract = bigInteger.subtract(ONE);
            int lowestSetBit = subtract.getLowestSetBit();
            return implMRProbablePrimeToBase(bigInteger, subtract, subtract.shiftRight(lowestSetBit), lowestSetBit, bigInteger2);
        }
    }

    private static void checkCandidate(BigInteger bigInteger, String str) {
        if (bigInteger == null || bigInteger.signum() < 1 || bigInteger.bitLength() < 2) {
            throw new IllegalArgumentException("'" + str + "' must be non-null and >= 2");
        }
    }

    private static boolean implHasAnySmallFactors(BigInteger bigInteger) {
        int intValue = bigInteger.mod(BigInteger.valueOf((long) 223092870)).intValue();
        if (intValue % 2 == 0 || intValue % 3 == 0 || intValue % 5 == 0 || intValue % 7 == 0 || intValue % 11 == 0 || intValue % 13 == 0 || intValue % 17 == 0 || intValue % 19 == 0 || intValue % 23 == 0) {
            return true;
        }
        int intValue2 = bigInteger.mod(BigInteger.valueOf((long) 58642669)).intValue();
        if (intValue2 % 29 == 0 || intValue2 % 31 == 0 || intValue2 % 37 == 0 || intValue2 % 41 == 0 || intValue2 % 43 == 0) {
            return true;
        }
        int intValue3 = bigInteger.mod(BigInteger.valueOf((long) 600662303)).intValue();
        if (intValue3 % 47 == 0 || intValue3 % 53 == 0 || intValue3 % 59 == 0 || intValue3 % 61 == 0 || intValue3 % 67 == 0) {
            return true;
        }
        int intValue4 = bigInteger.mod(BigInteger.valueOf((long) 33984931)).intValue();
        if (intValue4 % 71 == 0 || intValue4 % 73 == 0 || intValue4 % 79 == 0 || intValue4 % 83 == 0) {
            return true;
        }
        int intValue5 = bigInteger.mod(BigInteger.valueOf((long) 89809099)).intValue();
        if (intValue5 % 89 == 0 || intValue5 % 97 == 0 || intValue5 % 101 == 0 || intValue5 % 103 == 0) {
            return true;
        }
        int intValue6 = bigInteger.mod(BigInteger.valueOf((long) 167375713)).intValue();
        if (intValue6 % 107 == 0 || intValue6 % 109 == 0 || intValue6 % 113 == 0 || intValue6 % CertificateBody.profileType == 0) {
            return true;
        }
        int intValue7 = bigInteger.mod(BigInteger.valueOf((long) 371700317)).intValue();
        if (intValue7 % 131 == 0 || intValue7 % CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA == 0 || intValue7 % CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA == 0 || intValue7 % CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA == 0) {
            return true;
        }
        int intValue8 = bigInteger.mod(BigInteger.valueOf((long) 645328247)).intValue();
        if (intValue8 % CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA == 0 || intValue8 % CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 == 0 || intValue8 % CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 == 0 || intValue8 % CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384 == 0) {
            return true;
        }
        int intValue9 = bigInteger.mod(BigInteger.valueOf((long) 1070560157)).intValue();
        if (intValue9 % CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 == 0 || intValue9 % CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 == 0 || intValue9 % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 == 0 || intValue9 % CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 == 0) {
            return true;
        }
        int intValue10 = bigInteger.mod(BigInteger.valueOf((long) 1596463769)).intValue();
        if (intValue10 % CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 == 0 || intValue10 % CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 == 0 || intValue10 % 199 == 0 || intValue10 % SMALL_FACTOR_LIMIT == 0) {
            return true;
        }
        return false;
    }

    private static boolean implMRProbablePrimeToBase(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, int i, BigInteger bigInteger4) {
        BigInteger modPow = bigInteger4.modPow(bigInteger3, bigInteger);
        if (modPow.equals(ONE) || modPow.equals(bigInteger2)) {
            return true;
        }
        BigInteger bigInteger5 = modPow;
        for (int i2 = 1; i2 < i; i2++) {
            bigInteger5 = bigInteger5.modPow(TWO, bigInteger);
            if (bigInteger5.equals(bigInteger2)) {
                return true;
            }
            if (bigInteger5.equals(ONE)) {
                return false;
            }
        }
        return false;
    }

    private static STOutput implSTRandomPrime(Digest digest, int i, byte[] bArr) {
        int digestSize = digest.getDigestSize();
        if (i < 33) {
            byte[] bArr2 = new byte[digestSize];
            byte[] bArr3 = new byte[digestSize];
            int i2 = 0;
            do {
                hash(digest, bArr, bArr2, 0);
                inc(bArr, 1);
                hash(digest, bArr, bArr3, 0);
                inc(bArr, 1);
                i2++;
                long extract32 = ((long) (((extract32(bArr2) ^ extract32(bArr3)) & (-1 >>> (32 - i))) | (1 << (i - 1)) | 1)) & 4294967295L;
                if (isPrime32(extract32)) {
                    return new STOutput(BigInteger.valueOf(extract32), bArr, i2);
                }
            } while (i2 <= i * 4);
            throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
        }
        STOutput implSTRandomPrime = implSTRandomPrime(digest, (i + 3) / 2, bArr);
        BigInteger prime = implSTRandomPrime.getPrime();
        byte[] primeSeed = implSTRandomPrime.getPrimeSeed();
        int primeGenCounter = implSTRandomPrime.getPrimeGenCounter();
        int i3 = i - 1;
        int i4 = (i3 / (digestSize * 8)) + 1;
        BigInteger bit = hashGen(digest, primeSeed, i4).mod(ONE.shiftLeft(i3)).setBit(i3);
        BigInteger shiftLeft = prime.shiftLeft(1);
        BigInteger shiftLeft2 = bit.subtract(ONE).divide(shiftLeft).add(ONE).shiftLeft(1);
        BigInteger add = shiftLeft2.multiply(prime).add(ONE);
        BigInteger bigInteger = shiftLeft2;
        int i5 = 0;
        int i6 = primeGenCounter;
        while (true) {
            if (add.bitLength() > i) {
                bigInteger = ONE.shiftLeft(i3).subtract(ONE).divide(shiftLeft).add(ONE).shiftLeft(1);
                add = bigInteger.multiply(prime).add(ONE);
            }
            i6++;
            if (!implHasAnySmallFactors(add)) {
                BigInteger add2 = hashGen(digest, primeSeed, i4).mod(add.subtract(THREE)).add(TWO);
                bigInteger = bigInteger.add(BigInteger.valueOf((long) i5));
                BigInteger modPow = add2.modPow(bigInteger, add);
                if (add.gcd(modPow.subtract(ONE)).equals(ONE) && modPow.modPow(prime, add).equals(ONE)) {
                    return new STOutput(add, primeSeed, i6);
                }
                i5 = 0;
            } else {
                inc(primeSeed, i4);
            }
            if (i6 < (i * 4) + primeGenCounter) {
                i5 += 2;
                add = add.add(shiftLeft);
            } else {
                throw new IllegalStateException("Too many iterations in Shawe-Taylor Random_Prime Routine");
            }
        }
    }

    private static int extract32(byte[] bArr) {
        int min = Math.min(4, bArr.length);
        int i = 0;
        int i2 = 0;
        while (i < min) {
            int i3 = i + 1;
            i2 |= (bArr[bArr.length - i3] & 255) << (i * 8);
            i = i3;
        }
        return i2;
    }

    private static void hash(Digest digest, byte[] bArr, byte[] bArr2, int i) {
        digest.update(bArr, 0, bArr.length);
        digest.doFinal(bArr2, i);
    }

    private static BigInteger hashGen(Digest digest, byte[] bArr, int i) {
        int digestSize = digest.getDigestSize();
        int i2 = i * digestSize;
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i; i3++) {
            i2 -= digestSize;
            hash(digest, bArr, bArr2, i2);
            inc(bArr, 1);
        }
        return new BigInteger(1, bArr2);
    }

    private static void inc(byte[] bArr, int i) {
        int length = bArr.length;
        while (i > 0) {
            length--;
            if (length >= 0) {
                int i2 = i + (bArr[length] & 255);
                bArr[length] = (byte) i2;
                i = i2 >>> 8;
            } else {
                return;
            }
        }
    }

    private static boolean isPrime32(long j) {
        if ((j >>> 32) == 0) {
            int i = (j > 5 ? 1 : (j == 5 ? 0 : -1));
            if (i <= 0) {
                return j == 2 || j == 3 || i == 0;
            }
            if ((1 & j) == 0 || j % 3 == 0 || j % 5 == 0) {
                return false;
            }
            long[] jArr = {1, 7, 11, 13, 17, 19, 23, 29};
            long j2 = 0;
            int i2 = 1;
            while (true) {
                if (i2 >= jArr.length) {
                    j2 += 30;
                    if (j2 * j2 >= j) {
                        return true;
                    }
                    i2 = 0;
                } else if (j % (jArr[i2] + j2) != 0) {
                    i2++;
                } else if (j < 30) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            throw new IllegalArgumentException("Size limit exceeded");
        }
    }
}
