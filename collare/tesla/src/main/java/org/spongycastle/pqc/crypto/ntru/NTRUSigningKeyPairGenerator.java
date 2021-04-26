package org.spongycastle.pqc.crypto.ntru;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.crypto.ntru.NTRUSigningPrivateKeyParameters;
import org.spongycastle.pqc.math.ntru.euclid.BigIntEuclidean;
import org.spongycastle.pqc.math.ntru.polynomial.BigDecimalPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.BigIntPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Resultant;

public class NTRUSigningKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NTRUSigningKeyGenerationParameters params;

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.params = (NTRUSigningKeyGenerationParameters) keyGenerationParameters;
    }

    @Override // org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList = new ArrayList();
        int i = this.params.B;
        while (true) {
            nTRUSigningPublicKeyParameters = null;
            if (i < 0) {
                break;
            }
            arrayList.add(newCachedThreadPool.submit(new BasisGenerationTask()));
            i--;
        }
        newCachedThreadPool.shutdown();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = this.params.B; i2 >= 0; i2--) {
            Future future = (Future) arrayList.get(i2);
            try {
                arrayList2.add(future.get());
                if (i2 == this.params.B) {
                    nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis) future.get()).h, this.params.getSigningParameters());
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList2, nTRUSigningPublicKeyParameters));
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread() {
        ArrayList arrayList = new ArrayList();
        NTRUSigningPublicKeyParameters nTRUSigningPublicKeyParameters = null;
        for (int i = this.params.B; i >= 0; i--) {
            NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis = generateBoundedBasis();
            arrayList.add(generateBoundedBasis);
            if (i == 0) {
                nTRUSigningPublicKeyParameters = new NTRUSigningPublicKeyParameters(generateBoundedBasis.h, this.params.getSigningParameters());
            }
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) nTRUSigningPublicKeyParameters, (AsymmetricKeyParameter) new NTRUSigningPrivateKeyParameters(arrayList, nTRUSigningPublicKeyParameters));
    }

    private void minimizeFG(IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, IntegerPolynomial integerPolynomial4, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += i * 2 * ((integerPolynomial.coeffs[i3] * integerPolynomial.coeffs[i3]) + (integerPolynomial2.coeffs[i3] * integerPolynomial2.coeffs[i3]));
        }
        int i4 = i2 - 4;
        IntegerPolynomial integerPolynomial5 = (IntegerPolynomial) integerPolynomial.clone();
        IntegerPolynomial integerPolynomial6 = (IntegerPolynomial) integerPolynomial2.clone();
        int i5 = 0;
        int i6 = 0;
        while (i5 < i && i6 < i) {
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                i7 += i * 4 * ((integerPolynomial3.coeffs[i8] * integerPolynomial.coeffs[i8]) + (integerPolynomial4.coeffs[i8] * integerPolynomial2.coeffs[i8]));
            }
            int sumCoeffs = i7 - ((integerPolynomial3.sumCoeffs() + integerPolynomial4.sumCoeffs()) * 4);
            if (sumCoeffs > i4) {
                integerPolynomial3.sub(integerPolynomial5);
                integerPolynomial4.sub(integerPolynomial6);
                i5++;
                i6 = 0;
            } else if (sumCoeffs < (-i4)) {
                integerPolynomial3.add(integerPolynomial5);
                integerPolynomial4.add(integerPolynomial6);
                i5++;
                i6 = 0;
            }
            i6++;
            integerPolynomial5.rotate1();
            integerPolynomial6.rotate1();
        }
    }

    private FGBasis generateBasis() {
        boolean z;
        Polynomial polynomial;
        Polynomial polynomial2;
        IntegerPolynomial integerPolynomial;
        IntegerPolynomial invertFq;
        int i;
        int i2;
        int i3;
        Resultant resultant;
        Polynomial polynomial3;
        IntegerPolynomial integerPolynomial2;
        IntegerPolynomial integerPolynomial3;
        Polynomial polynomial4;
        IntegerPolynomial integerPolynomial4;
        Resultant resultant2;
        BigIntEuclidean calculate;
        BigIntPolynomial bigIntPolynomial;
        IntegerPolynomial integerPolynomial5;
        Polynomial polynomial5;
        int i4 = this.params.N;
        int i5 = this.params.q;
        int i6 = this.params.f6430d;
        int i7 = this.params.d1;
        int i8 = this.params.d2;
        int i9 = this.params.d3;
        int i10 = this.params.basisType;
        int i11 = (i4 * 2) + 1;
        boolean z2 = this.params.primeCheck;
        while (true) {
            if (this.params.polyType == 0) {
                polynomial = DenseTernaryPolynomial.generateRandom(i4, i6 + 1, i6, new SecureRandom());
                z = z2;
            } else {
                z = z2;
                polynomial = ProductFormPolynomial.generateRandom(i4, i7, i8, i9 + 1, i9, new SecureRandom());
            }
            polynomial2 = polynomial;
            integerPolynomial = polynomial2.toIntegerPolynomial();
            if (!z || !integerPolynomial.resultant(i11).res.equals(BigInteger.ZERO)) {
                invertFq = integerPolynomial.invertFq(i5);
                if (invertFq != null) {
                    break;
                }
                z2 = z;
            } else {
                z2 = z;
            }
        }
        Resultant resultant3 = integerPolynomial.resultant();
        while (true) {
            if (this.params.polyType == 0) {
                polynomial4 = DenseTernaryPolynomial.generateRandom(i4, i6 + 1, i6, new SecureRandom());
                resultant = resultant3;
                i2 = i7;
                i3 = i8;
                i = i9;
                integerPolynomial3 = invertFq;
                integerPolynomial2 = integerPolynomial;
                polynomial3 = polynomial2;
            } else {
                resultant = resultant3;
                i2 = i7;
                integerPolynomial3 = invertFq;
                i3 = i8;
                integerPolynomial2 = integerPolynomial;
                i = i9;
                polynomial3 = polynomial2;
                polynomial4 = ProductFormPolynomial.generateRandom(i4, i7, i8, i9 + 1, i9, new SecureRandom());
            }
            integerPolynomial4 = polynomial4.toIntegerPolynomial();
            if (z && integerPolynomial4.resultant(i11).res.equals(BigInteger.ZERO)) {
                invertFq = integerPolynomial3;
                integerPolynomial = integerPolynomial2;
                polynomial2 = polynomial3;
                resultant3 = resultant;
                i8 = i3;
                i7 = i2;
                i9 = i;
            } else if (integerPolynomial4.invertFq(i5) != null) {
                resultant2 = integerPolynomial4.resultant();
                calculate = BigIntEuclidean.calculate(resultant.res, resultant2.res);
                if (calculate.gcd.equals(BigInteger.ONE)) {
                    break;
                }
                invertFq = integerPolynomial3;
                integerPolynomial = integerPolynomial2;
                polynomial2 = polynomial3;
                resultant3 = resultant;
                i8 = i3;
                i7 = i2;
                i9 = i;
            } else {
                invertFq = integerPolynomial3;
                integerPolynomial = integerPolynomial2;
                polynomial2 = polynomial3;
                resultant3 = resultant;
                i8 = i3;
                i7 = i2;
                i9 = i;
            }
        }
        BigIntPolynomial bigIntPolynomial2 = (BigIntPolynomial) resultant.rho.clone();
        bigIntPolynomial2.mult(calculate.x.multiply(BigInteger.valueOf((long) i5)));
        BigIntPolynomial bigIntPolynomial3 = (BigIntPolynomial) resultant2.rho.clone();
        bigIntPolynomial3.mult(calculate.y.multiply(BigInteger.valueOf((long) (-i5))));
        int i12 = 0;
        if (this.params.keyGenAlg == 0) {
            int[] iArr = new int[i4];
            int[] iArr2 = new int[i4];
            iArr[0] = integerPolynomial2.coeffs[0];
            iArr2[0] = integerPolynomial4.coeffs[0];
            for (int i13 = 1; i13 < i4; i13++) {
                int i14 = i4 - i13;
                iArr[i13] = integerPolynomial2.coeffs[i14];
                iArr2[i13] = integerPolynomial4.coeffs[i14];
            }
            IntegerPolynomial integerPolynomial6 = new IntegerPolynomial(iArr);
            IntegerPolynomial integerPolynomial7 = new IntegerPolynomial(iArr2);
            IntegerPolynomial mult = polynomial3.mult(integerPolynomial6);
            mult.add(polynomial4.mult(integerPolynomial7));
            Resultant resultant4 = mult.resultant();
            BigIntPolynomial mult2 = integerPolynomial6.mult(bigIntPolynomial3);
            mult2.add(integerPolynomial7.mult(bigIntPolynomial2));
            bigIntPolynomial = mult2.mult(resultant4.rho);
            bigIntPolynomial.div(resultant4.res);
        } else {
            for (int i15 = 1; i15 < i4; i15 *= 10) {
                i12++;
            }
            BigDecimalPolynomial div = resultant.rho.div(new BigDecimal(resultant.res), bigIntPolynomial3.getMaxCoeffLength() + 1 + i12);
            BigDecimalPolynomial div2 = resultant2.rho.div(new BigDecimal(resultant2.res), bigIntPolynomial2.getMaxCoeffLength() + 1 + i12);
            BigDecimalPolynomial mult3 = div.mult(bigIntPolynomial3);
            mult3.add(div2.mult(bigIntPolynomial2));
            mult3.halve();
            bigIntPolynomial = mult3.round();
        }
        BigIntPolynomial bigIntPolynomial4 = (BigIntPolynomial) bigIntPolynomial3.clone();
        bigIntPolynomial4.sub(polynomial3.mult(bigIntPolynomial));
        BigIntPolynomial bigIntPolynomial5 = (BigIntPolynomial) bigIntPolynomial2.clone();
        bigIntPolynomial5.sub(polynomial4.mult(bigIntPolynomial));
        IntegerPolynomial integerPolynomial8 = new IntegerPolynomial(bigIntPolynomial4);
        IntegerPolynomial integerPolynomial9 = new IntegerPolynomial(bigIntPolynomial5);
        minimizeFG(integerPolynomial2, integerPolynomial4, integerPolynomial8, integerPolynomial9, i4);
        if (i10 == 0) {
            integerPolynomial5 = polynomial4.mult(integerPolynomial3, i5);
            polynomial5 = integerPolynomial8;
        } else {
            integerPolynomial5 = integerPolynomial8.mult(integerPolynomial3, i5);
            polynomial5 = polynomial4;
        }
        integerPolynomial5.modPositive(i5);
        return new FGBasis(polynomial3, polynomial5, integerPolynomial5, integerPolynomial8, integerPolynomial9, this.params);
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis() {
        FGBasis generateBasis;
        do {
            generateBasis = generateBasis();
        } while (!generateBasis.isNormOk());
        return generateBasis;
    }

    private class BasisGenerationTask implements Callable<NTRUSigningPrivateKeyParameters.Basis> {
        private BasisGenerationTask() {
        }

        @Override // java.util.concurrent.Callable
        public NTRUSigningPrivateKeyParameters.Basis call() {
            return NTRUSigningKeyPairGenerator.this.generateBoundedBasis();
        }
    }

    public class FGBasis extends NTRUSigningPrivateKeyParameters.Basis {
        public IntegerPolynomial F;
        public IntegerPolynomial G;

        FGBasis(Polynomial polynomial, Polynomial polynomial2, IntegerPolynomial integerPolynomial, IntegerPolynomial integerPolynomial2, IntegerPolynomial integerPolynomial3, NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters) {
            super(polynomial, polynomial2, integerPolynomial, nTRUSigningKeyGenerationParameters);
            this.F = integerPolynomial2;
            this.G = integerPolynomial3;
        }

        /* access modifiers changed from: package-private */
        public boolean isNormOk() {
            double d2 = this.params.keyNormBoundSq;
            int i = this.params.q;
            return ((double) this.F.centeredNormSq(i)) < d2 && ((double) this.G.centeredNormSq(i)) < d2;
        }
    }
}
