package org.spongycastle.crypto.generators;

import com.google.android.gms.common.api.Api;
import java.math.BigInteger;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.DerivationParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.MacDerivationFunction;
import org.spongycastle.crypto.params.KDFDoublePipelineIterationParameters;
import org.spongycastle.crypto.params.KeyParameter;

public class KDFDoublePipelineIterationBytesGenerator implements MacDerivationFunction {
    private static final BigInteger INTEGER_MAX = BigInteger.valueOf(2147483647L);
    private static final BigInteger TWO = BigInteger.valueOf(2);

    /* renamed from: a  reason: collision with root package name */
    private byte[] f6403a;
    private byte[] fixedInputData;
    private int generatedBytes;
    private final int h;
    private byte[] ios;
    private byte[] k;
    private int maxSizeExcl;
    private final Mac prf;
    private boolean useCounter;

    public KDFDoublePipelineIterationBytesGenerator(Mac mac) {
        this.prf = mac;
        this.h = mac.getMacSize();
        int i = this.h;
        this.f6403a = new byte[i];
        this.k = new byte[i];
    }

    @Override // org.spongycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFDoublePipelineIterationParameters) {
            KDFDoublePipelineIterationParameters kDFDoublePipelineIterationParameters = (KDFDoublePipelineIterationParameters) derivationParameters;
            this.prf.init(new KeyParameter(kDFDoublePipelineIterationParameters.getKI()));
            this.fixedInputData = kDFDoublePipelineIterationParameters.getFixedInputData();
            int r = kDFDoublePipelineIterationParameters.getR();
            this.ios = new byte[(r / 8)];
            boolean useCounter2 = kDFDoublePipelineIterationParameters.useCounter();
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
            this.useCounter = kDFDoublePipelineIterationParameters.useCounter();
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
            byte[] bArr = this.fixedInputData;
            mac.update(bArr, 0, bArr.length);
            this.prf.doFinal(this.f6403a, 0);
        } else {
            Mac mac2 = this.prf;
            byte[] bArr2 = this.f6403a;
            mac2.update(bArr2, 0, bArr2.length);
            this.prf.doFinal(this.f6403a, 0);
        }
        Mac mac3 = this.prf;
        byte[] bArr3 = this.f6403a;
        mac3.update(bArr3, 0, bArr3.length);
        if (this.useCounter) {
            int i = (this.generatedBytes / this.h) + 1;
            byte[] bArr4 = this.ios;
            switch (bArr4.length) {
                case 1:
                    break;
                case 4:
                    bArr4[0] = (byte) (i >>> 24);
                case 3:
                    byte[] bArr5 = this.ios;
                    bArr5[bArr5.length - 3] = (byte) (i >>> 16);
                case 2:
                    byte[] bArr6 = this.ios;
                    bArr6[bArr6.length - 2] = (byte) (i >>> 8);
                    break;
                default:
                    throw new IllegalStateException("Unsupported size of counter i");
            }
            byte[] bArr7 = this.ios;
            bArr7[bArr7.length - 1] = (byte) i;
            this.prf.update(bArr7, 0, bArr7.length);
        }
        Mac mac4 = this.prf;
        byte[] bArr8 = this.fixedInputData;
        mac4.update(bArr8, 0, bArr8.length);
        this.prf.doFinal(this.k, 0);
    }
}
