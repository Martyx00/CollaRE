package org.spongycastle.pqc.crypto.ntru;

import com.teslamotors.TeslaApp.BuildConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters implements Cloneable {
    public static final NTRUSigningKeyGenerationParameters APR2011_439 = new NTRUSigningKeyGenerationParameters(BuildConfig.VERSION_CODE, 2048, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_439_PROD = new NTRUSigningKeyGenerationParameters(BuildConfig.VERSION_CODE, 2048, 9, 8, 5, 1, 1, 0.165d, 490.0d, 280.0d, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743 = new NTRUSigningKeyGenerationParameters(743, 2048, 248, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743_PROD = new NTRUSigningKeyGenerationParameters(743, 2048, 11, 11, 15, 1, 1, 0.127d, 560.0d, 360.0d, true, false, 0, new SHA512Digest());
    public static final int BASIS_TYPE_STANDARD = 0;
    public static final int BASIS_TYPE_TRANSPOSE = 1;
    public static final int KEY_GEN_ALG_FLOAT = 1;
    public static final int KEY_GEN_ALG_RESULTANT = 0;
    public static final NTRUSigningKeyGenerationParameters TEST157 = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 29, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157_PROD = new NTRUSigningKeyGenerationParameters(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 256, 5, 5, 8, 1, 1, 0.38d, 200.0d, 80.0d, false, false, 0, new SHA256Digest());
    public int B;
    public int N;
    public int basisType;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d  reason: collision with root package name */
    public int f6430d;
    public int d1;
    public int d2;
    public int d3;
    public Digest hashAlg;
    public int keyGenAlg;
    double keyNormBound;
    public double keyNormBoundSq;
    double normBound;
    public double normBoundSq;
    public int polyType;
    public boolean primeCheck;
    public int q;
    public int signFailTolerance = 100;
    public boolean sparse;

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, double d4, double d5, double d6, boolean z, boolean z2, int i6, Digest digest) {
        super(new SecureRandom(), i);
        this.N = i;
        this.q = i2;
        this.f6430d = i3;
        this.B = i4;
        this.basisType = i5;
        this.beta = d4;
        this.normBound = d5;
        this.keyNormBound = d6;
        this.primeCheck = z;
        this.sparse = z2;
        this.keyGenAlg = i6;
        this.hashAlg = digest;
        this.polyType = 0;
        init();
    }

    public NTRUSigningKeyGenerationParameters(int i, int i2, int i3, int i4, int i5, int i6, int i7, double d4, double d5, double d6, boolean z, boolean z2, int i8, Digest digest) {
        super(new SecureRandom(), i);
        this.N = i;
        this.q = i2;
        this.d1 = i3;
        this.d2 = i4;
        this.d3 = i5;
        this.B = i6;
        this.basisType = i7;
        this.beta = d4;
        this.normBound = d5;
        this.keyNormBound = d6;
        this.primeCheck = z;
        this.sparse = z2;
        this.keyGenAlg = i8;
        this.hashAlg = digest;
        this.polyType = 1;
        init();
    }

    private void init() {
        double d4 = this.beta;
        this.betaSq = d4 * d4;
        double d5 = this.normBound;
        this.normBoundSq = d5 * d5;
        double d6 = this.keyNormBound;
        this.keyNormBoundSq = d6 * d6;
    }

    public NTRUSigningKeyGenerationParameters(InputStream inputStream) {
        super(new SecureRandom(), 0);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.N = dataInputStream.readInt();
        this.q = dataInputStream.readInt();
        this.f6430d = dataInputStream.readInt();
        this.d1 = dataInputStream.readInt();
        this.d2 = dataInputStream.readInt();
        this.d3 = dataInputStream.readInt();
        this.B = dataInputStream.readInt();
        this.basisType = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.keyNormBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.primeCheck = dataInputStream.readBoolean();
        this.sparse = dataInputStream.readBoolean();
        this.bitsF = dataInputStream.readInt();
        this.keyGenAlg = dataInputStream.read();
        String readUTF = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        this.polyType = dataInputStream.read();
        init();
    }

    public void writeTo(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.N);
        dataOutputStream.writeInt(this.q);
        dataOutputStream.writeInt(this.f6430d);
        dataOutputStream.writeInt(this.d1);
        dataOutputStream.writeInt(this.d2);
        dataOutputStream.writeInt(this.d3);
        dataOutputStream.writeInt(this.B);
        dataOutputStream.writeInt(this.basisType);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeDouble(this.keyNormBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeBoolean(this.primeCheck);
        dataOutputStream.writeBoolean(this.sparse);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.write(this.keyGenAlg);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
        dataOutputStream.write(this.polyType);
    }

    public NTRUSigningParameters getSigningParameters() {
        return new NTRUSigningParameters(this.N, this.q, this.f6430d, this.B, this.beta, this.normBound, this.hashAlg);
    }

    @Override // java.lang.Object
    public NTRUSigningKeyGenerationParameters clone() {
        if (this.polyType == 0) {
            return new NTRUSigningKeyGenerationParameters(this.N, this.q, this.f6430d, this.B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
        }
        return new NTRUSigningKeyGenerationParameters(this.N, this.q, this.d1, this.d2, this.d3, this.B, this.basisType, this.beta, this.normBound, this.keyNormBound, this.primeCheck, this.sparse, this.keyGenAlg, this.hashAlg);
    }

    public int hashCode() {
        int i = ((((this.B + 31) * 31) + this.N) * 31) + this.basisType;
        long doubleToLongBits = Double.doubleToLongBits(this.beta);
        long doubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i2 = ((((((((((((((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f6430d) * 31) + this.d1) * 31) + this.d2) * 31) + this.d3) * 31;
        Digest digest = this.hashAlg;
        int hashCode = ((i2 + (digest == null ? 0 : digest.getAlgorithmName().hashCode())) * 31) + this.keyGenAlg;
        long doubleToLongBits3 = Double.doubleToLongBits(this.keyNormBound);
        long doubleToLongBits4 = Double.doubleToLongBits(this.keyNormBoundSq);
        long doubleToLongBits5 = Double.doubleToLongBits(this.normBound);
        long doubleToLongBits6 = Double.doubleToLongBits(this.normBoundSq);
        int i3 = 1231;
        int i4 = ((((((((((((((((hashCode * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + this.polyType) * 31) + (this.primeCheck ? 1231 : 1237)) * 31) + this.q) * 31) + this.signFailTolerance) * 31;
        if (!this.sparse) {
            i3 = 1237;
        }
        return i4 + i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningKeyGenerationParameters)) {
            return false;
        }
        NTRUSigningKeyGenerationParameters nTRUSigningKeyGenerationParameters = (NTRUSigningKeyGenerationParameters) obj;
        if (this.B != nTRUSigningKeyGenerationParameters.B || this.N != nTRUSigningKeyGenerationParameters.N || this.basisType != nTRUSigningKeyGenerationParameters.basisType || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.betaSq) || this.bitsF != nTRUSigningKeyGenerationParameters.bitsF || this.f6430d != nTRUSigningKeyGenerationParameters.f6430d || this.d1 != nTRUSigningKeyGenerationParameters.d1 || this.d2 != nTRUSigningKeyGenerationParameters.d2 || this.d3 != nTRUSigningKeyGenerationParameters.d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningKeyGenerationParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningKeyGenerationParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return this.keyGenAlg == nTRUSigningKeyGenerationParameters.keyGenAlg && Double.doubleToLongBits(this.keyNormBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBound) && Double.doubleToLongBits(this.keyNormBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.keyNormBoundSq) && Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningKeyGenerationParameters.normBoundSq) && this.polyType == nTRUSigningKeyGenerationParameters.polyType && this.primeCheck == nTRUSigningKeyGenerationParameters.primeCheck && this.q == nTRUSigningKeyGenerationParameters.q && this.signFailTolerance == nTRUSigningKeyGenerationParameters.signFailTolerance && this.sparse == nTRUSigningKeyGenerationParameters.sparse;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.N + " q=" + this.q);
        if (this.polyType == 0) {
            sb.append(" polyType=SIMPLE d=" + this.f6430d);
        } else {
            sb.append(" polyType=PRODUCT d1=" + this.d1 + " d2=" + this.d2 + " d3=" + this.d3);
        }
        sb.append(" B=" + this.B + " basisType=" + this.basisType + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " keyNormBound=" + decimalFormat.format(this.keyNormBound) + " prime=" + this.primeCheck + " sparse=" + this.sparse + " keyGenAlg=" + this.keyGenAlg + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }
}
