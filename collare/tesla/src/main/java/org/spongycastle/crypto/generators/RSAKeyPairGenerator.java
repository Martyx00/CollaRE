package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyGenerationParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.spongycastle.math.Primes;
import org.spongycastle.math.ec.WNafUtil;

public class RSAKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private int iterations;
    private RSAKeyGenerationParameters param;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (RSAKeyGenerationParameters) keyGenerationParameters;
        this.iterations = getNumberOfIterations(this.param.getStrength(), this.param.getCertainty());
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        BigInteger chooseRandomPrime;
        BigInteger multiply;
        BigInteger bigInteger;
        RSAKeyPairGenerator rSAKeyPairGenerator = this;
        int strength = rSAKeyPairGenerator.param.getStrength();
        int i = (strength + 1) / 2;
        int i2 = strength - i;
        int i3 = strength / 2;
        int i4 = i3 - 100;
        int i5 = strength / 3;
        if (i4 < i5) {
            i4 = i5;
        }
        int i6 = strength >> 2;
        BigInteger pow = BigInteger.valueOf(2).pow(i3);
        BigInteger shiftLeft = ONE.shiftLeft(strength - 1);
        BigInteger shiftLeft2 = ONE.shiftLeft(i4);
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = null;
        boolean z = false;
        while (!z) {
            BigInteger publicExponent = rSAKeyPairGenerator.param.getPublicExponent();
            BigInteger chooseRandomPrime2 = rSAKeyPairGenerator.chooseRandomPrime(i, publicExponent, shiftLeft);
            while (true) {
                chooseRandomPrime = rSAKeyPairGenerator.chooseRandomPrime(i2, publicExponent, shiftLeft);
                BigInteger abs = chooseRandomPrime.subtract(chooseRandomPrime2).abs();
                if (abs.bitLength() >= i4 && abs.compareTo(shiftLeft2) > 0) {
                    multiply = chooseRandomPrime2.multiply(chooseRandomPrime);
                    if (multiply.bitLength() == strength) {
                        if (WNafUtil.getNafWeight(multiply) >= i6) {
                            break;
                        }
                        chooseRandomPrime2 = rSAKeyPairGenerator.chooseRandomPrime(i, publicExponent, shiftLeft);
                    } else {
                        chooseRandomPrime2 = chooseRandomPrime2.max(chooseRandomPrime);
                    }
                } else {
                    strength = strength;
                    rSAKeyPairGenerator = this;
                }
            }
            if (chooseRandomPrime2.compareTo(chooseRandomPrime) < 0) {
                bigInteger = chooseRandomPrime2;
                chooseRandomPrime2 = chooseRandomPrime;
            } else {
                bigInteger = chooseRandomPrime;
            }
            BigInteger subtract = chooseRandomPrime2.subtract(ONE);
            BigInteger subtract2 = bigInteger.subtract(ONE);
            BigInteger modInverse = publicExponent.modInverse(subtract.divide(subtract.gcd(subtract2)).multiply(subtract2));
            if (modInverse.compareTo(pow) <= 0) {
                strength = strength;
                rSAKeyPairGenerator = this;
            } else {
                asymmetricCipherKeyPair = new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new RSAKeyParameters(false, multiply, publicExponent), (AsymmetricKeyParameter) new RSAPrivateCrtKeyParameters(multiply, publicExponent, modInverse, chooseRandomPrime2, bigInteger, modInverse.remainder(subtract), modInverse.remainder(subtract2), bigInteger.modInverse(chooseRandomPrime2)));
                strength = strength;
                rSAKeyPairGenerator = this;
                z = true;
            }
        }
        return asymmetricCipherKeyPair;
    }

    /* access modifiers changed from: protected */
    public BigInteger chooseRandomPrime(int i, BigInteger bigInteger, BigInteger bigInteger2) {
        for (int i2 = 0; i2 != i * 5; i2++) {
            BigInteger bigInteger3 = new BigInteger(i, 1, this.param.getRandom());
            if (!bigInteger3.mod(bigInteger).equals(ONE) && bigInteger3.multiply(bigInteger3).compareTo(bigInteger2) >= 0 && isProbablePrime(bigInteger3) && bigInteger.gcd(bigInteger3.subtract(ONE)).equals(ONE)) {
                return bigInteger3;
            }
        }
        throw new IllegalStateException("unable to generate prime number for RSA key");
    }

    /* access modifiers changed from: protected */
    public boolean isProbablePrime(BigInteger bigInteger) {
        return !Primes.hasAnySmallFactors(bigInteger) && Primes.isMRProbablePrime(bigInteger, this.param.getRandom(), this.iterations);
    }

    private static int getNumberOfIterations(int i, int i2) {
        if (i >= 1536) {
            if (i2 <= 100) {
                return 3;
            }
            if (i2 <= 128) {
                return 4;
            }
            return 4 + (((i2 - 128) + 1) / 2);
        } else if (i >= 1024) {
            if (i2 <= 100) {
                return 4;
            }
            if (i2 <= 112) {
                return 5;
            }
            return (((i2 - 112) + 1) / 2) + 5;
        } else if (i >= 512) {
            if (i2 <= 80) {
                return 5;
            }
            if (i2 <= 100) {
                return 7;
            }
            return (((i2 - 100) + 1) / 2) + 7;
        } else if (i2 <= 80) {
            return 40;
        } else {
            return 40 + (((i2 - 80) + 1) / 2);
        }
    }
}
