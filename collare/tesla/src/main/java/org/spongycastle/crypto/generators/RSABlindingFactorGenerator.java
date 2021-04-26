package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.params.RSAPrivateCrtKeyParameters;

public class RSABlindingFactorGenerator {
    private static BigInteger ONE = BigInteger.valueOf(1);
    private static BigInteger ZERO = BigInteger.valueOf(0);
    private RSAKeyParameters key;
    private SecureRandom random;

    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.key = (RSAKeyParameters) parametersWithRandom.getParameters();
            this.random = parametersWithRandom.getRandom();
        } else {
            this.key = (RSAKeyParameters) cipherParameters;
            this.random = new SecureRandom();
        }
        if (this.key instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("generator requires RSA public key");
        }
    }

    public BigInteger generateBlindingFactor() {
        RSAKeyParameters rSAKeyParameters = this.key;
        if (rSAKeyParameters != null) {
            BigInteger modulus = rSAKeyParameters.getModulus();
            int bitLength = modulus.bitLength() - 1;
            while (true) {
                BigInteger bigInteger = new BigInteger(bitLength, this.random);
                BigInteger gcd = bigInteger.gcd(modulus);
                if (!bigInteger.equals(ZERO) && !bigInteger.equals(ONE) && gcd.equals(ONE)) {
                    return bigInteger;
                }
            }
        } else {
            throw new IllegalStateException("generator not initialised");
        }
    }
}
