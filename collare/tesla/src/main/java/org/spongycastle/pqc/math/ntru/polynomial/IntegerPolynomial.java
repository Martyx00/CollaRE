package org.spongycastle.pqc.math.ntru.polynomial;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.util.ArrayEncoder;
import org.spongycastle.pqc.math.ntru.util.Util;
import org.spongycastle.util.Arrays;

public class IntegerPolynomial implements Polynomial {
    private static final List BIGINT_PRIMES = new ArrayList();
    private static final int NUM_EQUAL_RESULTANTS = 3;
    private static final int[] PRIMES = {4507, 4513, 4517, 4519, 4523, 4547, 4549, 4561, 4567, 4583, 4591, 4597, 4603, 4621, 4637, 4639, 4643, 4649, 4651, 4657, 4663, 4673, 4679, 4691, 4703, 4721, 4723, 4729, 4733, 4751, 4759, 4783, 4787, 4789, 4793, 4799, 4801, 4813, 4817, 4831, 4861, 4871, 4877, 4889, 4903, 4909, 4919, 4931, 4933, 4937, 4943, 4951, 4957, 4967, 4969, 4973, 4987, 4993, 4999, 5003, 5009, 5011, 5021, 5023, 5039, 5051, 5059, 5077, 5081, 5087, 5099, 5101, 5107, 5113, 5119, 5147, 5153, 5167, 5171, 5179, 5189, 5197, 5209, 5227, 5231, 5233, 5237, 5261, 5273, 5279, 5281, 5297, 5303, 5309, 5323, 5333, 5347, 5351, 5381, 5387, 5393, 5399, 5407, 5413, 5417, 5419, 5431, 5437, 5441, 5443, 5449, 5471, 5477, 5479, 5483, 5501, 5503, 5507, 5519, 5521, 5527, 5531, 5557, 5563, 5569, 5573, 5581, 5591, 5623, 5639, 5641, 5647, 5651, 5653, 5657, 5659, 5669, 5683, 5689, 5693, 5701, 5711, 5717, 5737, 5741, 5743, 5749, 5779, 5783, 5791, 5801, 5807, 5813, 5821, 5827, 5839, 5843, 5849, 5851, 5857, 5861, 5867, 5869, 5879, 5881, 5897, 5903, 5923, 5927, 5939, 5953, 5981, 5987, 6007, 6011, 6029, 6037, 6043, 6047, 6053, 6067, 6073, 6079, 6089, 6091, 6101, 6113, 6121, 6131, 6133, 6143, 6151, 6163, 6173, 6197, 6199, 6203, 6211, 6217, 6221, 6229, 6247, 6257, 6263, 6269, 6271, 6277, 6287, 6299, 6301, 6311, 6317, 6323, 6329, 6337, 6343, 6353, 6359, 6361, 6367, 6373, 6379, 6389, 6397, 6421, 6427, 6449, 6451, 6469, 6473, 6481, 6491, 6521, 6529, 6547, 6551, 6553, 6563, 6569, 6571, 6577, 6581, 6599, 6607, 6619, 6637, 6653, 6659, 6661, 6673, 6679, 6689, 6691, 6701, 6703, 6709, 6719, 6733, 6737, 6761, 6763, 6779, 6781, 6791, 6793, 6803, 6823, 6827, 6829, 6833, 6841, 6857, 6863, 6869, 6871, 6883, 6899, 6907, 6911, 6917, 6947, 6949, 6959, 6961, 6967, 6971, 6977, 6983, 6991, 6997, 7001, 7013, 7019, 7027, 7039, 7043, 7057, 7069, 7079, 7103, 7109, 7121, 7127, 7129, 7151, 7159, 7177, 7187, 7193, 7207, 7211, 7213, 7219, 7229, 7237, 7243, 7247, 7253, 7283, 7297, 7307, 7309, 7321, 7331, 7333, 7349, 7351, 7369, 7393, 7411, 7417, 7433, 7451, 7457, 7459, 7477, 7481, 7487, 7489, 7499, 7507, 7517, 7523, 7529, 7537, 7541, 7547, 7549, 7559, 7561, 7573, 7577, 7583, 7589, 7591, 7603, 7607, 7621, 7639, 7643, 7649, 7669, 7673, 7681, 7687, 7691, 7699, 7703, 7717, 7723, 7727, 7741, 7753, 7757, 7759, 7789, 7793, 7817, 7823, 7829, 7841, 7853, 7867, 7873, 7877, 7879, 7883, 7901, 7907, 7919, 7927, 7933, 7937, 7949, 7951, 7963, 7993, 8009, 8011, 8017, 8039, 8053, 8059, 8069, 8081, 8087, 8089, 8093, 8101, 8111, 8117, 8123, 8147, 8161, 8167, 8171, 8179, 8191, 8209, 8219, 8221, 8231, 8233, 8237, 8243, 8263, 8269, 8273, 8287, 8291, 8293, 8297, 8311, 8317, 8329, 8353, 8363, 8369, 8377, 8387, 8389, 8419, 8423, 8429, 8431, 8443, 8447, 8461, 8467, 8501, 8513, 8521, 8527, 8537, 8539, 8543, 8563, 8573, 8581, 8597, 8599, 8609, 8623, 8627, 8629, 8641, 8647, 8663, 8669, 8677, 8681, 8689, 8693, 8699, 8707, 8713, 8719, 8731, 8737, 8741, 8747, 8753, 8761, 8779, 8783, 8803, 8807, 8819, 8821, 8831, 8837, 8839, 8849, 8861, 8863, 8867, 8887, 8893, 8923, 8929, 8933, 8941, 8951, 8963, 8969, 8971, 8999, 9001, 9007, 9011, 9013, 9029, 9041, 9043, 9049, 9059, 9067, 9091, 9103, 9109, 9127, 9133, 9137, 9151, 9157, 9161, 9173, 9181, 9187, 9199, 9203, 9209, 9221, 9227, 9239, 9241, 9257, 9277, 9281, 9283, 9293, 9311, 9319, 9323, 9337, 9341, 9343, 9349, 9371, 9377, 9391, 9397, 9403, 9413, 9419, 9421, 9431, 9433, 9437, 9439, 9461, 9463, 9467, 9473, 9479, 9491, 9497, 9511, 9521, 9533, 9539, 9547, 9551, 9587, 9601, 9613, 9619, 9623, 9629, 9631, 9643, 9649, 9661, 9677, 9679, 9689, 9697, 9719, 9721, 9733, 9739, 9743, 9749, 9767, 9769, 9781, 9787, 9791, 9803, 9811, 9817, 9829, 9833, 9839, 9851, 9857, 9859, 9871, 9883, 9887, 9901, 9907, 9923, 9929, 9931, 9941, 9949, 9967, 9973};
    public int[] coeffs;

    static {
        int i = 0;
        while (true) {
            int[] iArr = PRIMES;
            if (i != iArr.length) {
                BIGINT_PRIMES.add(BigInteger.valueOf((long) iArr[i]));
                i++;
            } else {
                return;
            }
        }
    }

    public IntegerPolynomial(int i) {
        this.coeffs = new int[i];
    }

    public IntegerPolynomial(int[] iArr) {
        this.coeffs = iArr;
    }

    public IntegerPolynomial(BigIntPolynomial bigIntPolynomial) {
        this.coeffs = new int[bigIntPolynomial.coeffs.length];
        for (int i = 0; i < bigIntPolynomial.coeffs.length; i++) {
            this.coeffs[i] = bigIntPolynomial.coeffs[i].intValue();
        }
    }

    public static IntegerPolynomial fromBinary3Sves(byte[] bArr, int i) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Sves(bArr, i));
    }

    public static IntegerPolynomial fromBinary3Tight(byte[] bArr, int i) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(bArr, i));
    }

    public static IntegerPolynomial fromBinary3Tight(InputStream inputStream, int i) {
        return new IntegerPolynomial(ArrayEncoder.decodeMod3Tight(inputStream, i));
    }

    public static IntegerPolynomial fromBinary(byte[] bArr, int i, int i2) {
        return new IntegerPolynomial(ArrayEncoder.decodeModQ(bArr, i, i2));
    }

    public static IntegerPolynomial fromBinary(InputStream inputStream, int i, int i2) {
        return new IntegerPolynomial(ArrayEncoder.decodeModQ(inputStream, i, i2));
    }

    public byte[] toBinary3Sves() {
        return ArrayEncoder.encodeMod3Sves(this.coeffs);
    }

    public byte[] toBinary3Tight() {
        BigInteger bigInteger = Constants.BIGINT_ZERO;
        for (int length = this.coeffs.length - 1; length >= 0; length--) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(3)).add(BigInteger.valueOf((long) (this.coeffs[length] + 1)));
        }
        int bitLength = (BigInteger.valueOf(3).pow(this.coeffs.length).bitLength() + 7) / 8;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length >= bitLength) {
            return byteArray.length > bitLength ? Arrays.copyOfRange(byteArray, 1, byteArray.length) : byteArray;
        }
        byte[] bArr = new byte[bitLength];
        System.arraycopy(byteArray, 0, bArr, bitLength - byteArray.length, byteArray.length);
        return bArr;
    }

    public byte[] toBinary(int i) {
        return ArrayEncoder.encodeModQ(this.coeffs, i);
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial, int i) {
        IntegerPolynomial mult = mult(integerPolynomial);
        mult.mod(i);
        return mult;
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial mult(IntegerPolynomial integerPolynomial) {
        int[] iArr;
        int length = this.coeffs.length;
        if (integerPolynomial.coeffs.length == length) {
            IntegerPolynomial multRecursive = multRecursive(integerPolynomial);
            if (multRecursive.coeffs.length > length) {
                int i = length;
                while (true) {
                    iArr = multRecursive.coeffs;
                    if (i >= iArr.length) {
                        break;
                    }
                    int i2 = i - length;
                    iArr[i2] = iArr[i2] + iArr[i];
                    i++;
                }
                multRecursive.coeffs = Arrays.copyOf(iArr, length);
            }
            return multRecursive;
        }
        throw new IllegalArgumentException("Number of coefficients must be the same");
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public BigIntPolynomial mult(BigIntPolynomial bigIntPolynomial) {
        return new BigIntPolynomial(this).mult(bigIntPolynomial);
    }

    private IntegerPolynomial multRecursive(IntegerPolynomial integerPolynomial) {
        int[] iArr = this.coeffs;
        int[] iArr2 = integerPolynomial.coeffs;
        int length = iArr2.length;
        int i = 0;
        if (length <= 32) {
            int i2 = (length * 2) - 1;
            IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(new int[i2]);
            for (int i3 = 0; i3 < i2; i3++) {
                for (int max = Math.max(0, (i3 - length) + 1); max <= Math.min(i3, length - 1); max++) {
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i3] = iArr3[i3] + (iArr2[max] * iArr[i3 - max]);
                }
            }
            return integerPolynomial2;
        }
        int i4 = length / 2;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(Arrays.copyOf(iArr, i4));
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(Arrays.copyOfRange(iArr, i4, length));
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(Arrays.copyOf(iArr2, i4));
        IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(Arrays.copyOfRange(iArr2, i4, length));
        IntegerPolynomial integerPolynomial7 = (IntegerPolynomial) integerPolynomial3.clone();
        integerPolynomial7.add(integerPolynomial4);
        IntegerPolynomial integerPolynomial8 = (IntegerPolynomial) integerPolynomial5.clone();
        integerPolynomial8.add(integerPolynomial6);
        IntegerPolynomial multRecursive = integerPolynomial3.multRecursive(integerPolynomial5);
        IntegerPolynomial multRecursive2 = integerPolynomial4.multRecursive(integerPolynomial6);
        IntegerPolynomial multRecursive3 = integerPolynomial7.multRecursive(integerPolynomial8);
        multRecursive3.sub(multRecursive);
        multRecursive3.sub(multRecursive2);
        IntegerPolynomial integerPolynomial9 = new IntegerPolynomial((length * 2) - 1);
        int i5 = 0;
        while (true) {
            int[] iArr4 = multRecursive.coeffs;
            if (i5 >= iArr4.length) {
                break;
            }
            integerPolynomial9.coeffs[i5] = iArr4[i5];
            i5++;
        }
        int i6 = 0;
        while (true) {
            int[] iArr5 = multRecursive3.coeffs;
            if (i6 >= iArr5.length) {
                break;
            }
            int[] iArr6 = integerPolynomial9.coeffs;
            int i7 = i4 + i6;
            iArr6[i7] = iArr6[i7] + iArr5[i6];
            i6++;
        }
        while (true) {
            int[] iArr7 = multRecursive2.coeffs;
            if (i >= iArr7.length) {
                return integerPolynomial9;
            }
            int[] iArr8 = integerPolynomial9.coeffs;
            int i8 = (i4 * 2) + i;
            iArr8[i8] = iArr8[i8] + iArr7[i];
            i++;
        }
    }

    public IntegerPolynomial invertFq(int i) {
        int length = this.coeffs.length;
        int i2 = length + 1;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i2);
        integerPolynomial.coeffs[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i2);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(i2);
        integerPolynomial3.coeffs = Arrays.copyOf(this.coeffs, i2);
        integerPolynomial3.modPositive(2);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(i2);
        int[] iArr = integerPolynomial4.coeffs;
        iArr[0] = 1;
        iArr[length] = 1;
        int i3 = 0;
        while (true) {
            if (integerPolynomial3.coeffs[0] == 0) {
                for (int i4 = 1; i4 <= length; i4++) {
                    int[] iArr2 = integerPolynomial3.coeffs;
                    iArr2[i4 - 1] = iArr2[i4];
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i2 - i4] = iArr3[length - i4];
                }
                integerPolynomial3.coeffs[length] = 0;
                integerPolynomial2.coeffs[0] = 0;
                i3++;
                if (integerPolynomial3.equalsZero()) {
                    return null;
                }
            } else if (!integerPolynomial3.equalsOne()) {
                if (integerPolynomial3.degree() < integerPolynomial4.degree()) {
                    integerPolynomial2 = integerPolynomial;
                    integerPolynomial = integerPolynomial2;
                    integerPolynomial4 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial4;
                }
                integerPolynomial3.add(integerPolynomial4, 2);
                integerPolynomial.add(integerPolynomial2, 2);
            } else if (integerPolynomial.coeffs[length] != 0) {
                return null;
            } else {
                IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
                int i5 = i3 % length;
                for (int i6 = length - 1; i6 >= 0; i6--) {
                    int i7 = i6 - i5;
                    if (i7 < 0) {
                        i7 += length;
                    }
                    integerPolynomial5.coeffs[i7] = integerPolynomial.coeffs[i6];
                }
                return mod2ToModq(integerPolynomial5, i);
            }
        }
    }

    private IntegerPolynomial mod2ToModq(IntegerPolynomial integerPolynomial, int i) {
        int i2 = 2;
        if (!Util.is64BitJVM() || i != 2048) {
            while (i2 < i) {
                i2 *= 2;
                int[] iArr = integerPolynomial.coeffs;
                IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(Arrays.copyOf(iArr, iArr.length));
                integerPolynomial2.mult2(i2);
                integerPolynomial2.sub(mult(integerPolynomial, i2).mult(integerPolynomial, i2), i2);
                integerPolynomial = integerPolynomial2;
            }
            return integerPolynomial;
        }
        LongPolynomial2 longPolynomial2 = new LongPolynomial2(this);
        LongPolynomial2 longPolynomial22 = new LongPolynomial2(integerPolynomial);
        while (i2 < i) {
            i2 *= 2;
            LongPolynomial2 longPolynomial23 = (LongPolynomial2) longPolynomial22.clone();
            int i3 = i2 - 1;
            longPolynomial23.mult2And(i3);
            longPolynomial23.subAnd(longPolynomial2.mult(longPolynomial22).mult(longPolynomial22), i3);
            longPolynomial22 = longPolynomial23;
        }
        return longPolynomial22.toIntegerPolynomial();
    }

    public IntegerPolynomial invertF3() {
        int length = this.coeffs.length;
        int i = length + 1;
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(i);
        integerPolynomial.coeffs[0] = 1;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(i);
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(i);
        integerPolynomial3.coeffs = Arrays.copyOf(this.coeffs, i);
        integerPolynomial3.modPositive(3);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(i);
        int[] iArr = integerPolynomial4.coeffs;
        iArr[0] = -1;
        iArr[length] = 1;
        int i2 = 0;
        while (true) {
            if (integerPolynomial3.coeffs[0] == 0) {
                for (int i3 = 1; i3 <= length; i3++) {
                    int[] iArr2 = integerPolynomial3.coeffs;
                    iArr2[i3 - 1] = iArr2[i3];
                    int[] iArr3 = integerPolynomial2.coeffs;
                    iArr3[i - i3] = iArr3[length - i3];
                }
                integerPolynomial3.coeffs[length] = 0;
                integerPolynomial2.coeffs[0] = 0;
                i2++;
                if (integerPolynomial3.equalsZero()) {
                    return null;
                }
            } else if (!integerPolynomial3.equalsAbsOne()) {
                if (integerPolynomial3.degree() < integerPolynomial4.degree()) {
                    integerPolynomial2 = integerPolynomial;
                    integerPolynomial = integerPolynomial2;
                    integerPolynomial4 = integerPolynomial3;
                    integerPolynomial3 = integerPolynomial4;
                }
                if (integerPolynomial3.coeffs[0] == integerPolynomial4.coeffs[0]) {
                    integerPolynomial3.sub(integerPolynomial4, 3);
                    integerPolynomial.sub(integerPolynomial2, 3);
                } else {
                    integerPolynomial3.add(integerPolynomial4, 3);
                    integerPolynomial.add(integerPolynomial2, 3);
                }
            } else if (integerPolynomial.coeffs[length] != 0) {
                return null;
            } else {
                IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
                int i4 = i2 % length;
                for (int i5 = length - 1; i5 >= 0; i5--) {
                    int i6 = i5 - i4;
                    if (i6 < 0) {
                        i6 += length;
                    }
                    integerPolynomial5.coeffs[i6] = integerPolynomial3.coeffs[0] * integerPolynomial.coeffs[i5];
                }
                integerPolynomial5.ensurePositive(3);
                return integerPolynomial5;
            }
        }
    }

    public Resultant resultant() {
        BigInteger multiply;
        BigInteger mod;
        int length = this.coeffs.length;
        LinkedList linkedList = new LinkedList();
        BigInteger bigInteger = Constants.BIGINT_ONE;
        BigInteger bigInteger2 = Constants.BIGINT_ONE;
        PrimeGenerator primeGenerator = new PrimeGenerator();
        int i = 1;
        while (true) {
            BigInteger nextPrime = primeGenerator.nextPrime();
            ModularResultant resultant = resultant(nextPrime.intValue());
            linkedList.add(resultant);
            multiply = bigInteger.multiply(nextPrime);
            BigIntEuclidean calculate = BigIntEuclidean.calculate(nextPrime, bigInteger);
            mod = bigInteger2.multiply(calculate.x.multiply(nextPrime)).add(resultant.res.multiply(calculate.y.multiply(bigInteger))).mod(multiply);
            BigInteger divide = multiply.divide(BigInteger.valueOf(2));
            BigInteger negate = divide.negate();
            if (mod.compareTo(divide) > 0) {
                mod = mod.subtract(multiply);
            } else if (mod.compareTo(negate) < 0) {
                mod = mod.add(multiply);
            }
            if (mod.equals(bigInteger2)) {
                i++;
                if (i >= 3) {
                    break;
                }
            } else {
                i = 1;
            }
            bigInteger2 = mod;
            bigInteger = multiply;
        }
        while (linkedList.size() > 1) {
            linkedList.addLast(ModularResultant.combineRho((ModularResultant) linkedList.removeFirst(), (ModularResultant) linkedList.removeFirst()));
        }
        BigIntPolynomial bigIntPolynomial = ((ModularResultant) linkedList.getFirst()).rho;
        BigInteger divide2 = multiply.divide(BigInteger.valueOf(2));
        BigInteger negate2 = divide2.negate();
        if (mod.compareTo(divide2) > 0) {
            mod = mod.subtract(multiply);
        }
        if (mod.compareTo(negate2) < 0) {
            mod = mod.add(multiply);
        }
        for (int i2 = 0; i2 < length; i2++) {
            BigInteger bigInteger3 = bigIntPolynomial.coeffs[i2];
            if (bigInteger3.compareTo(divide2) > 0) {
                bigIntPolynomial.coeffs[i2] = bigInteger3.subtract(multiply);
            }
            if (bigInteger3.compareTo(negate2) < 0) {
                bigIntPolynomial.coeffs[i2] = bigInteger3.add(multiply);
            }
        }
        return new Resultant(bigIntPolynomial, mod);
    }

    public Resultant resultantMultiThread() {
        ModularResultant modularResultant;
        int length = this.coeffs.length;
        BigInteger multiply = squareSum().pow((length + 1) / 2).multiply(BigInteger.valueOf(2).pow((degree() + 1) / 2)).multiply(BigInteger.valueOf(2));
        BigInteger valueOf = BigInteger.valueOf(10000);
        BigInteger bigInteger = Constants.BIGINT_ONE;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        Iterator it = BIGINT_PRIMES.iterator();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        while (true) {
            modularResultant = null;
            if (bigInteger.compareTo(multiply) >= 0) {
                break;
            }
            if (it.hasNext()) {
                valueOf = (BigInteger) it.next();
            } else {
                valueOf = valueOf.nextProbablePrime();
            }
            linkedBlockingQueue.add(newFixedThreadPool.submit(new ModResultantTask(valueOf.intValue())));
            bigInteger = bigInteger.multiply(valueOf);
        }
        while (true) {
            if (linkedBlockingQueue.isEmpty()) {
                break;
            }
            try {
                Future future = (Future) linkedBlockingQueue.take();
                Future future2 = (Future) linkedBlockingQueue.poll();
                if (future2 == null) {
                    modularResultant = (ModularResultant) future.get();
                    break;
                }
                linkedBlockingQueue.add(newFixedThreadPool.submit(new CombineTask((ModularResultant) future.get(), (ModularResultant) future2.get())));
            } catch (Exception e) {
                throw new IllegalStateException(e.toString());
            }
        }
        newFixedThreadPool.shutdown();
        BigInteger bigInteger2 = modularResultant.res;
        BigIntPolynomial bigIntPolynomial = modularResultant.rho;
        BigInteger divide = bigInteger.divide(BigInteger.valueOf(2));
        BigInteger negate = divide.negate();
        if (bigInteger2.compareTo(divide) > 0) {
            bigInteger2 = bigInteger2.subtract(bigInteger);
        }
        if (bigInteger2.compareTo(negate) < 0) {
            bigInteger2 = bigInteger2.add(bigInteger);
        }
        for (int i = 0; i < length; i++) {
            BigInteger bigInteger3 = bigIntPolynomial.coeffs[i];
            if (bigInteger3.compareTo(divide) > 0) {
                bigIntPolynomial.coeffs[i] = bigInteger3.subtract(bigInteger);
            }
            if (bigInteger3.compareTo(negate) < 0) {
                bigIntPolynomial.coeffs[i] = bigInteger3.add(bigInteger);
            }
        }
        return new Resultant(bigIntPolynomial, bigInteger2);
    }

    public ModularResultant resultant(int i) {
        int[] iArr = this.coeffs;
        int[] copyOf = Arrays.copyOf(iArr, iArr.length + 1);
        IntegerPolynomial integerPolynomial = new IntegerPolynomial(copyOf);
        int length = copyOf.length;
        IntegerPolynomial integerPolynomial2 = new IntegerPolynomial(length);
        int[] iArr2 = integerPolynomial2.coeffs;
        iArr2[0] = -1;
        int i2 = length - 1;
        iArr2[i2] = 1;
        IntegerPolynomial integerPolynomial3 = new IntegerPolynomial(integerPolynomial.coeffs);
        IntegerPolynomial integerPolynomial4 = new IntegerPolynomial(length);
        IntegerPolynomial integerPolynomial5 = new IntegerPolynomial(length);
        integerPolynomial5.coeffs[0] = 1;
        IntegerPolynomial integerPolynomial6 = integerPolynomial4;
        int i3 = i2;
        int i4 = i3;
        int degree = integerPolynomial3.degree();
        int i5 = 1;
        IntegerPolynomial integerPolynomial7 = integerPolynomial2;
        IntegerPolynomial integerPolynomial8 = integerPolynomial3;
        while (degree > 0) {
            int invert = (Util.invert(integerPolynomial8.coeffs[degree], i) * integerPolynomial7.coeffs[i3]) % i;
            int i6 = i3 - degree;
            integerPolynomial7.multShiftSub(integerPolynomial8, invert, i6, i);
            integerPolynomial6.multShiftSub(integerPolynomial5, invert, i6, i);
            i3 = integerPolynomial7.degree();
            if (i3 < degree) {
                i5 = (i5 * Util.pow(integerPolynomial8.coeffs[degree], i4 - i3, i)) % i;
                if (i4 % 2 == 1 && degree % 2 == 1) {
                    i5 = (-i5) % i;
                }
                i4 = degree;
                degree = i3;
                i3 = i4;
                integerPolynomial7 = integerPolynomial8;
                integerPolynomial8 = integerPolynomial7;
                integerPolynomial6 = integerPolynomial5;
                integerPolynomial5 = integerPolynomial6;
            }
        }
        int pow = (i5 * Util.pow(integerPolynomial8.coeffs[0], i3, i)) % i;
        integerPolynomial5.mult(Util.invert(integerPolynomial8.coeffs[0], i));
        integerPolynomial5.mod(i);
        integerPolynomial5.mult(pow);
        integerPolynomial5.mod(i);
        int[] iArr3 = integerPolynomial5.coeffs;
        integerPolynomial5.coeffs = Arrays.copyOf(iArr3, iArr3.length - 1);
        return new ModularResultant(new BigIntPolynomial(integerPolynomial5), BigInteger.valueOf((long) pow), BigInteger.valueOf((long) i));
    }

    private void multShiftSub(IntegerPolynomial integerPolynomial, int i, int i2, int i3) {
        int length = this.coeffs.length;
        for (int i4 = i2; i4 < length; i4++) {
            int[] iArr = this.coeffs;
            iArr[i4] = (iArr[i4] - (integerPolynomial.coeffs[i4 - i2] * i)) % i3;
        }
    }

    private BigInteger squareSum() {
        BigInteger bigInteger = Constants.BIGINT_ZERO;
        int i = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i >= iArr.length) {
                return bigInteger;
            }
            bigInteger = bigInteger.add(BigInteger.valueOf((long) (iArr[i] * iArr[i])));
            i++;
        }
    }

    /* access modifiers changed from: package-private */
    public int degree() {
        int length = this.coeffs.length - 1;
        while (length > 0 && this.coeffs[length] == 0) {
            length--;
        }
        return length;
    }

    public void add(IntegerPolynomial integerPolynomial, int i) {
        add(integerPolynomial);
        mod(i);
    }

    public void add(IntegerPolynomial integerPolynomial) {
        int[] iArr = integerPolynomial.coeffs;
        int length = iArr.length;
        int[] iArr2 = this.coeffs;
        if (length > iArr2.length) {
            this.coeffs = Arrays.copyOf(iArr2, iArr.length);
        }
        int i = 0;
        while (true) {
            int[] iArr3 = integerPolynomial.coeffs;
            if (i < iArr3.length) {
                int[] iArr4 = this.coeffs;
                iArr4[i] = iArr4[i] + iArr3[i];
                i++;
            } else {
                return;
            }
        }
    }

    public void sub(IntegerPolynomial integerPolynomial, int i) {
        sub(integerPolynomial);
        mod(i);
    }

    public void sub(IntegerPolynomial integerPolynomial) {
        int[] iArr = integerPolynomial.coeffs;
        int length = iArr.length;
        int[] iArr2 = this.coeffs;
        if (length > iArr2.length) {
            this.coeffs = Arrays.copyOf(iArr2, iArr.length);
        }
        int i = 0;
        while (true) {
            int[] iArr3 = integerPolynomial.coeffs;
            if (i < iArr3.length) {
                int[] iArr4 = this.coeffs;
                iArr4[i] = iArr4[i] - iArr3[i];
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void sub(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 < iArr.length) {
                iArr[i2] = iArr[i2] - i;
                i2++;
            } else {
                return;
            }
        }
    }

    public void mult(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 < iArr.length) {
                iArr[i2] = iArr[i2] * i;
                i2++;
            } else {
                return;
            }
        }
    }

    private void mult2(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 < iArr.length) {
                iArr[i2] = iArr[i2] * 2;
                iArr[i2] = iArr[i2] % i;
                i2++;
            } else {
                return;
            }
        }
    }

    public void mult3(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 < iArr.length) {
                iArr[i2] = iArr[i2] * 3;
                iArr[i2] = iArr[i2] % i;
                i2++;
            } else {
                return;
            }
        }
    }

    public void div(int i) {
        int i2 = (i + 1) / 2;
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i3 < iArr.length) {
                iArr[i3] = iArr[i3] + (iArr[i3] > 0 ? i2 : -i2);
                int[] iArr2 = this.coeffs;
                iArr2[i3] = iArr2[i3] / i;
                i3++;
            } else {
                return;
            }
        }
    }

    public void mod3() {
        int i = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i < iArr.length) {
                iArr[i] = iArr[i] % 3;
                if (iArr[i] > 1) {
                    iArr[i] = iArr[i] - 3;
                }
                int[] iArr2 = this.coeffs;
                if (iArr2[i] < -1) {
                    iArr2[i] = iArr2[i] + 3;
                }
                i++;
            } else {
                return;
            }
        }
    }

    public void modPositive(int i) {
        mod(i);
        ensurePositive(i);
    }

    /* access modifiers changed from: package-private */
    public void modCenter(int i) {
        int i2;
        mod(i);
        for (int i3 = 0; i3 < this.coeffs.length; i3++) {
            while (true) {
                int[] iArr = this.coeffs;
                i2 = i / 2;
                if (iArr[i3] >= i2) {
                    break;
                }
                iArr[i3] = iArr[i3] + i;
            }
            while (true) {
                int[] iArr2 = this.coeffs;
                if (iArr2[i3] < i2) {
                    break;
                }
                iArr2[i3] = iArr2[i3] - i;
            }
        }
    }

    public void mod(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 < iArr.length) {
                iArr[i2] = iArr[i2] % i;
                i2++;
            } else {
                return;
            }
        }
    }

    public void ensurePositive(int i) {
        for (int i2 = 0; i2 < this.coeffs.length; i2++) {
            while (true) {
                int[] iArr = this.coeffs;
                if (iArr[i2] >= 0) {
                    break;
                }
                iArr[i2] = iArr[i2] + i;
            }
        }
    }

    public long centeredNormSq(int i) {
        int length = this.coeffs.length;
        IntegerPolynomial integerPolynomial = (IntegerPolynomial) clone();
        integerPolynomial.shiftGap(i);
        long j = 0;
        int i2 = 0;
        long j2 = 0;
        while (true) {
            int[] iArr = integerPolynomial.coeffs;
            if (i2 == iArr.length) {
                return j - ((j2 * j2) / ((long) length));
            }
            int i3 = iArr[i2];
            j2 += (long) i3;
            j += (long) (i3 * i3);
            i2++;
        }
    }

    /* access modifiers changed from: package-private */
    public void shiftGap(int i) {
        int i2;
        modCenter(i);
        int[] clone = Arrays.clone(this.coeffs);
        sort(clone);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i3 < clone.length - 1) {
            int i6 = i3 + 1;
            int i7 = clone[i6] - clone[i3];
            if (i7 > i4) {
                i5 = clone[i3];
                i4 = i7;
            }
            i3 = i6;
        }
        int i8 = clone[0];
        int i9 = clone[clone.length - 1];
        if ((i - i9) + i8 > i4) {
            i2 = (i9 + i8) / 2;
        } else {
            i2 = i5 + (i4 / 2) + (i / 2);
        }
        sub(i2);
    }

    private void sort(int[] iArr) {
        boolean z;
        for (boolean z2 = true; z2; z2 = z) {
            int i = 0;
            z = false;
            while (i != iArr.length - 1) {
                int i2 = i + 1;
                if (iArr[i] > iArr[i2]) {
                    int i3 = iArr[i];
                    iArr[i] = iArr[i2];
                    iArr[i2] = i3;
                    z = true;
                }
                i = i2;
            }
        }
    }

    public void center0(int i) {
        for (int i2 = 0; i2 < this.coeffs.length; i2++) {
            while (true) {
                int[] iArr = this.coeffs;
                if (iArr[i2] >= (-i) / 2) {
                    break;
                }
                iArr[i2] = iArr[i2] + i;
            }
            while (true) {
                int[] iArr2 = this.coeffs;
                if (iArr2[i2] <= i / 2) {
                    break;
                }
                iArr2[i2] = iArr2[i2] - i;
            }
        }
    }

    public int sumCoeffs() {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i >= iArr.length) {
                return i2;
            }
            i2 += iArr[i];
            i++;
        }
    }

    private boolean equalsZero() {
        int i = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i >= iArr.length) {
                return true;
            }
            if (iArr[i] != 0) {
                return false;
            }
            i++;
        }
    }

    public boolean equalsOne() {
        int i = 1;
        while (true) {
            int[] iArr = this.coeffs;
            if (i < iArr.length) {
                if (iArr[i] != 0) {
                    return false;
                }
                i++;
            } else if (iArr[0] == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean equalsAbsOne() {
        int i = 1;
        while (true) {
            int[] iArr = this.coeffs;
            if (i < iArr.length) {
                if (iArr[i] != 0) {
                    return false;
                }
                i++;
            } else if (Math.abs(iArr[0]) == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public int count(int i) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i2 == iArr.length) {
                return i3;
            }
            if (iArr[i2] == i) {
                i3++;
            }
            i2++;
        }
    }

    public void rotate1() {
        int[] iArr = this.coeffs;
        int i = iArr[iArr.length - 1];
        for (int length = iArr.length - 1; length > 0; length--) {
            int[] iArr2 = this.coeffs;
            iArr2[length] = iArr2[length - 1];
        }
        this.coeffs[0] = i;
    }

    public void clear() {
        int i = 0;
        while (true) {
            int[] iArr = this.coeffs;
            if (i < iArr.length) {
                iArr[i] = 0;
                i++;
            } else {
                return;
            }
        }
    }

    @Override // org.spongycastle.pqc.math.ntru.polynomial.Polynomial
    public IntegerPolynomial toIntegerPolynomial() {
        return (IntegerPolynomial) clone();
    }

    public Object clone() {
        return new IntegerPolynomial((int[]) this.coeffs.clone());
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntegerPolynomial) {
            return Arrays.areEqual(this.coeffs, ((IntegerPolynomial) obj).coeffs);
        }
        return false;
    }

    private class ModResultantTask implements Callable<ModularResultant> {
        private int modulus;

        private ModResultantTask(int i) {
            this.modulus = i;
        }

        @Override // java.util.concurrent.Callable
        public ModularResultant call() {
            return IntegerPolynomial.this.resultant(this.modulus);
        }
    }

    private class CombineTask implements Callable<ModularResultant> {
        private ModularResultant modRes1;
        private ModularResultant modRes2;

        private CombineTask(ModularResultant modularResultant, ModularResultant modularResultant2) {
            this.modRes1 = modularResultant;
            this.modRes2 = modularResultant2;
        }

        @Override // java.util.concurrent.Callable
        public ModularResultant call() {
            return ModularResultant.combineRho(this.modRes1, this.modRes2);
        }
    }

    private class PrimeGenerator {
        private int index;
        private BigInteger prime;

        private PrimeGenerator() {
            this.index = 0;
        }

        public BigInteger nextPrime() {
            if (this.index < IntegerPolynomial.BIGINT_PRIMES.size()) {
                List list = IntegerPolynomial.BIGINT_PRIMES;
                int i = this.index;
                this.index = i + 1;
                this.prime = (BigInteger) list.get(i);
            } else {
                this.prime = this.prime.nextProbablePrime();
            }
            return this.prime;
        }
    }
}
