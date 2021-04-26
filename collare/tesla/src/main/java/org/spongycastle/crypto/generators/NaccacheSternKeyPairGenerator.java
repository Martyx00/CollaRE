package org.spongycastle.crypto.generators;

import com.google.android.gms.common.api.Api;
import com.teslamotors.TeslaApp.BuildConfig;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import okhttp3.internal.http.StatusLine;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.spongycastle.crypto.params.NaccacheSternKeyParameters;
import org.spongycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;

public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, CertificateBody.profileType, 131, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, StatusLine.HTTP_TEMP_REDIRECT, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, BuildConfig.VERSION_CODE, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private NaccacheSternKeyGenerationParameters param;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger generatePrime2;
        BigInteger add2;
        BigInteger bigInteger3;
        long j2;
        BigInteger bigInteger4;
        boolean z;
        BigInteger bigInteger5;
        long j3;
        BigInteger bigInteger6;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            PrintStream printStream = System.out;
            printStream.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger7 = ONE;
        BigInteger bigInteger8 = bigInteger7;
        for (int i = 0; i < permuteList.size() / 2; i++) {
            bigInteger8 = bigInteger8.multiply((BigInteger) permuteList.elementAt(i));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger7 = bigInteger7.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger8.multiply(bigInteger7);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger8).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger7).shiftLeft(1);
        long j4 = 0;
        while (true) {
            j = j4 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (!add.isProbablePrime(certainty)) {
                bigInteger2 = shiftLeft2;
                bigInteger = shiftLeft;
            } else {
                int i2 = 24;
                while (true) {
                    generatePrime2 = generatePrime(i2, certainty, random);
                    if (!generatePrime.equals(generatePrime2)) {
                        bigInteger2 = shiftLeft2;
                        add2 = generatePrime2.multiply(shiftLeft2).add(ONE);
                        if (add2.isProbablePrime(certainty)) {
                            break;
                        }
                        shiftLeft2 = bigInteger2;
                        i2 = 24;
                    }
                }
                bigInteger = shiftLeft;
                if (!multiply.gcd(generatePrime.multiply(generatePrime2)).equals(ONE)) {
                    continue;
                } else if (add.multiply(add2).bitLength() >= strength) {
                    break;
                } else if (isDebug) {
                    PrintStream printStream2 = System.out;
                    printStream2.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                }
            }
            j4 = j;
            shiftLeft2 = bigInteger2;
            shiftLeft = bigInteger;
        }
        if (isDebug) {
            PrintStream printStream3 = System.out;
            StringBuilder sb = new StringBuilder();
            bigInteger3 = generatePrime4;
            sb.append("needed ");
            sb.append(j);
            sb.append(" tries to generate p and q.");
            printStream3.println(sb.toString());
        } else {
            bigInteger3 = generatePrime4;
        }
        BigInteger multiply2 = add.multiply(add2);
        BigInteger multiply3 = add.subtract(ONE).multiply(add2.subtract(ONE));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j5 = 0;
        while (true) {
            Vector vector = new Vector();
            int i3 = 0;
            j2 = j5;
            while (i3 != permuteList.size()) {
                BigInteger divide = multiply3.divide((BigInteger) permuteList.elementAt(i3));
                while (true) {
                    j3 = j2 + 1;
                    bigInteger6 = new BigInteger(strength, certainty, random);
                    if (!bigInteger6.modPow(divide, multiply2).equals(ONE)) {
                        break;
                    }
                    j2 = j3;
                    strength = strength;
                }
                vector.addElement(bigInteger6);
                i3++;
                j2 = j3;
                strength = strength;
            }
            bigInteger4 = ONE;
            int i4 = 0;
            while (i4 < permuteList.size()) {
                bigInteger4 = bigInteger4.multiply(((BigInteger) vector.elementAt(i4)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i4)), multiply2)).mod(multiply2);
                i4++;
                random = random;
            }
            int i5 = 0;
            while (true) {
                if (i5 >= permuteList.size()) {
                    z = false;
                    break;
                } else if (bigInteger4.modPow(multiply3.divide((BigInteger) permuteList.elementAt(i5)), multiply2).equals(ONE)) {
                    if (isDebug) {
                        PrintStream printStream4 = System.out;
                        printStream4.println("g has order phi(n)/" + permuteList.elementAt(i5) + "\n g: " + bigInteger4);
                    }
                    z = true;
                } else {
                    i5++;
                }
            }
            if (z) {
                bigInteger5 = bigInteger3;
            } else if (!bigInteger4.modPow(multiply3.divide(BigInteger.valueOf(4)), multiply2).equals(ONE)) {
                if (!bigInteger4.modPow(multiply3.divide(generatePrime), multiply2).equals(ONE)) {
                    if (!bigInteger4.modPow(multiply3.divide(generatePrime2), multiply2).equals(ONE)) {
                        if (!bigInteger4.modPow(multiply3.divide(generatePrime3), multiply2).equals(ONE)) {
                            bigInteger5 = bigInteger3;
                            if (!bigInteger4.modPow(multiply3.divide(bigInteger5), multiply2).equals(ONE)) {
                                break;
                            } else if (isDebug) {
                                PrintStream printStream5 = System.out;
                                printStream5.println("g has order phi(n)/b\n g: " + bigInteger4);
                            }
                        } else if (isDebug) {
                            PrintStream printStream6 = System.out;
                            printStream6.println("g has order phi(n)/a\n g: " + bigInteger4);
                            bigInteger5 = bigInteger3;
                        } else {
                            bigInteger5 = bigInteger3;
                        }
                    } else if (isDebug) {
                        PrintStream printStream7 = System.out;
                        printStream7.println("g has order phi(n)/q'\n g: " + bigInteger4);
                        bigInteger5 = bigInteger3;
                    } else {
                        bigInteger5 = bigInteger3;
                    }
                } else if (isDebug) {
                    PrintStream printStream8 = System.out;
                    printStream8.println("g has order phi(n)/p'\n g: " + bigInteger4);
                    bigInteger5 = bigInteger3;
                } else {
                    bigInteger5 = bigInteger3;
                }
            } else if (isDebug) {
                PrintStream printStream9 = System.out;
                printStream9.println("g has order phi(n)/4\n g:" + bigInteger4);
                bigInteger5 = bigInteger3;
            } else {
                bigInteger5 = bigInteger3;
            }
            bigInteger3 = bigInteger5;
            strength = strength;
            random = random;
            add = add;
            j5 = j2;
            add2 = add2;
        }
        if (isDebug) {
            PrintStream printStream10 = System.out;
            printStream10.println("needed " + j2 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream11 = System.out;
            printStream11.println("smallPrimes: " + permuteList);
            PrintStream printStream12 = System.out;
            printStream12.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream13 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("a:.......... ");
            sb2.append(generatePrime3);
            printStream13.println(sb2.toString());
            PrintStream printStream14 = System.out;
            printStream14.println("b:.......... " + bigInteger5);
            PrintStream printStream15 = System.out;
            printStream15.println("p':......... " + generatePrime);
            PrintStream printStream16 = System.out;
            printStream16.println("q':......... " + generatePrime2);
            PrintStream printStream17 = System.out;
            printStream17.println("p:.......... " + add);
            PrintStream printStream18 = System.out;
            printStream18.println("q:.......... " + add2);
            PrintStream printStream19 = System.out;
            printStream19.println("n:.......... " + multiply2);
            PrintStream printStream20 = System.out;
            printStream20.println("phi(n):..... " + multiply3);
            PrintStream printStream21 = System.out;
            printStream21.println("g:.......... " + bigInteger4);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger4, multiply2, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger4, multiply2, multiply.bitLength(), permuteList, multiply3));
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i, i2, secureRandom);
        while (bigInteger.bitLength() != i) {
            bigInteger = new BigInteger(i, i2, secureRandom);
        }
        return bigInteger;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        vector3.removeElementAt(0);
        while (vector3.size() != 0) {
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
            vector3.removeElementAt(0);
        }
        return vector2;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) (secureRandom.nextInt() & Api.BaseClientBuilder.API_PRIORITY_OTHER))) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Api.BaseClientBuilder.API_PRIORITY_OTHER;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf((long) smallPrimes[i2]));
        }
        return vector;
    }
}
