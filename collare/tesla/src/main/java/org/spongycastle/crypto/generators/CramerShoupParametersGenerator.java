package org.spongycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.CramerShoupParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.util.BigIntegers;

public class CramerShoupParametersGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private int certainty;
    private SecureRandom random;
    private int size;

    public void init(int i, int i2, SecureRandom secureRandom) {
        this.size = i;
        this.certainty = i2;
        this.random = secureRandom;
    }

    public CramerShoupParameters generateParameters() {
        BigInteger bigInteger = ParametersHelper.generateSafePrimes(this.size, this.certainty, this.random)[1];
        BigInteger selectGenerator = ParametersHelper.selectGenerator(bigInteger, this.random);
        BigInteger selectGenerator2 = ParametersHelper.selectGenerator(bigInteger, this.random);
        while (selectGenerator.equals(selectGenerator2)) {
            selectGenerator2 = ParametersHelper.selectGenerator(bigInteger, this.random);
        }
        return new CramerShoupParameters(bigInteger, selectGenerator, selectGenerator2, new SHA256Digest());
    }

    public CramerShoupParameters generateParameters(DHParameters dHParameters) {
        BigInteger p = dHParameters.getP();
        BigInteger g = dHParameters.getG();
        BigInteger selectGenerator = ParametersHelper.selectGenerator(p, this.random);
        while (g.equals(selectGenerator)) {
            selectGenerator = ParametersHelper.selectGenerator(p, this.random);
        }
        return new CramerShoupParameters(p, g, selectGenerator, new SHA256Digest());
    }

    private static class ParametersHelper {
        private static final BigInteger TWO = BigInteger.valueOf(2);

        private ParametersHelper() {
        }

        static BigInteger[] generateSafePrimes(int i, int i2, SecureRandom secureRandom) {
            BigInteger bigInteger;
            BigInteger add;
            int i3 = i - 1;
            while (true) {
                bigInteger = new BigInteger(i3, 2, secureRandom);
                add = bigInteger.shiftLeft(1).add(CramerShoupParametersGenerator.ONE);
                if (!add.isProbablePrime(i2) || (i2 > 2 && !bigInteger.isProbablePrime(i2))) {
                }
            }
            return new BigInteger[]{add, bigInteger};
        }

        static BigInteger selectGenerator(BigInteger bigInteger, SecureRandom secureRandom) {
            BigInteger modPow;
            BigInteger subtract = bigInteger.subtract(TWO);
            do {
                modPow = BigIntegers.createRandomInRange(TWO, subtract, secureRandom).modPow(TWO, bigInteger);
            } while (modPow.equals(CramerShoupParametersGenerator.ONE));
            return modPow;
        }
    }
}
