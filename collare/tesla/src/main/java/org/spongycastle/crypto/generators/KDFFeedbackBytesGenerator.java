package org.spongycastle.crypto.generators;

import com.google.android.gms.common.api.Api;
import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFFeedbackParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFFeedbackBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private byte[] fixedInputData;
    private int generatedBytes;
    private final int h;
    private byte[] ios;
    private byte[] iv;
    private byte[] k = new byte[this.h];
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFFeedbackBytesGenerator(Mac mac) {
        this.prf = mac;
        this.h = mac.getMacSize();
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFFeedbackParameters) {
            KDFFeedbackParameters kDFFeedbackParameters = (KDFFeedbackParameters) derivationParameters;
            this.prf.init(new KeyParameter(kDFFeedbackParameters.getKI()));
            this.fixedInputData = kDFFeedbackParameters.getFixedInputData();
            int r = kDFFeedbackParameters.getR();
            this.ios = new byte[(r / 8)];
            boolean useCounter2 = kDFFeedbackParameters.useCounter();
            int i = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            if (useCounter2) {
                BigInteger multiply = TWO.pow(r).multiply(BigInteger.valueOf((long) this.h));
                if (multiply.compareTo(INTEGER_MAX) != 1) {
                    i = multiply.intValue();
                }
                this.maxSizeExcl = i;
            } else {
                this.maxSizeExcl = Api.BaseClientBuilder.API_PRIORITY_OTHER;
            }
            this.iv = kDFFeedbackParameters.getIV();
            this.useCounter = kDFFeedbackParameters.useCounter();
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
        if (this.generatedBytes == 0) {
            Mac mac = this.prf;
            byte[] bArr = this.iv;
            mac.update(bArr, 0, bArr.length);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.k;
            mac2.update(bArr2, 0, bArr2.length);
        }
        if (this.useCounter) {
            int i = (this.generatedBytes / this.h) + 1;
            byte[] bArr3 = this.ios;
            switch (bArr3.length) {
                case 1:
                    break;
                case 4:
                    bArr3[0] = (byte) (i >>> 24);
                case 3:
                    byte[] bArr4 = this.ios;
                    bArr4[bArr4.length - 3] = (byte) (i >>> 16);
                case 2:
                    byte[] bArr5 = this.ios;
                    bArr5[bArr5.length - 2] = (byte) (i >>> 8);
                    break;
                default:
                    throw new IllegalStateException("Unsupported size of counter i");
            }
            byte[] bArr6 = this.ios;
            bArr6[bArr6.length - 1] = (byte) i;
            this.prf.update(bArr6, 0, bArr6.length);
        }
        Mac mac3 = this.prf;
        byte[] bArr7 = this.fixedInputData;
        mac3.update(bArr7, 0, bArr7.length);
        this.prf.doFinal(this.k, 0);
    }
}
