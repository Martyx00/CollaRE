package org.spongycastle.crypto.generators;

import com.google.android.gms.common.api.Api;
import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFCounterParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFCounterBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputDataCtrPrefix;
    private byte[] fixedInputData_afterCtr;
    private int generatedBytes;
    private final int h;
    private byte[] ios;
    private byte[] k = new byte[this.h];
    private int maxSizeExcl;
    private final Mac prf;

    public KDFCounterBytesGenerator(Mac mac) {
        this.prf = mac;
        this.h = mac.getMacSize();
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        int i;
        if (derivationParameters instanceof KDFCounterParameters) {
            KDFCounterParameters kDFCounterParameters = (KDFCounterParameters) derivationParameters;
            this.prf.init(new KeyParameter(kDFCounterParameters.getKI()));
            this.fixedInputDataCtrPrefix = kDFCounterParameters.getFixedInputDataCounterPrefix();
            this.fixedInputData_afterCtr = kDFCounterParameters.getFixedInputDataCounterSuffix();
            int r = kDFCounterParameters.getR();
            this.ios = new byte[(r / 8)];
            BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf((long) this.h));
            if (multiply.compareTo(INTEGER_MAX) == 1) {
                i = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            } else {
                i = multiply.intValue();
            }
            this.maxSizeExcl = i;
            this.generatedBytes = 0;
            return;
        }
        throw new IllegalArgumentException("Wrong type of arguments given");
    }

    @Override // org.spongycastle.crypto.MacDerivationFunction
    public Mac getMac() {
        return this.prf;
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) {
        int i3 = this.generatedBytes;
        int i4 = i3 + i2;
        if (i4 < 0 || i4 >= this.maxSizeExcl) {
            throw new DataLengthException("Current KDFCTR may only be used for " + this.maxSizeExcl + " bytes");
        }
        if (i3 % this.h == 0) {
            generateNext();
        }
        int i5 = this.generatedBytes;
        int i6 = this.h;
        int i7 = i5 % i6;
        int min = Math.min(i6 - (i5 % i6), i2);
        System.arraycopy(this.k, i7, bArr, i, min);
        this.generatedBytes += min;
        int i8 = i2 - min;
        int i9 = i + min;
        while (i8 > 0) {
            generateNext();
            int min2 = Math.min(this.h, i8);
            System.arraycopy(this.k, 0, bArr, i9, min2);
            this.generatedBytes += min2;
            i8 -= min2;
            i9 += min2;
        }
        return i2;
    }

    private void generateNext() {
        int i = (this.generatedBytes / this.h) + 1;
        byte[] bArr = this.ios;
        switch (bArr.length) {
            case 1:
                break;
            case 4:
                bArr[0] = (byte) (i >>> 24);
            case 3:
                byte[] bArr2 = this.ios;
                bArr2[bArr2.length - 3] = (byte) (i >>> 16);
            case 2:
                byte[] bArr3 = this.ios;
                bArr3[bArr3.length - 2] = (byte) (i >>> 8);
                break;
            default:
                throw new IllegalStateException("Unsupported size of counter i");
        }
        byte[] bArr4 = this.ios;
        bArr4[bArr4.length - 1] = (byte) i;
        Mac mac = this.prf;
        byte[] bArr5 = this.fixedInputDataCtrPrefix;
        mac.update(bArr5, 0, bArr5.length);
        Mac mac2 = this.prf;
        byte[] bArr6 = this.ios;
        mac2.update(bArr6, 0, bArr6.length);
        Mac mac3 = this.prf;
        byte[] bArr7 = this.fixedInputData_afterCtr;
        mac3.update(bArr7, 0, bArr7.length);
        this.prf.doFinal(this.k, 0);
    }
}
