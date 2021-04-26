package org.spongycastle.pqc.crypto.ntru;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

public class NTRUSigningParameters implements Cloneable {
    public int B;
    public int N;
    double beta;
    public double betaSq;
    int bitsF = 6;

    /* renamed from: d  reason: collision with root package name */
    public int f6431d;
    public int d1;
    public int d2;
    public int d3;
    public Digest hashAlg;
    double normBound;
    public double normBoundSq;
    public int q;
    public int signFailTolerance = 100;

    public NTRUSigningParameters(int i, int i2, int i3, int i4, double d4, double d5, Digest digest) {
        this.N = i;
        this.q = i2;
        this.f6431d = i3;
        this.B = i4;
        this.beta = d4;
        this.normBound = d5;
        this.hashAlg = digest;
        init();
    }

    public NTRUSigningParameters(int i, int i2, int i3, int i4, int i5, int i6, double d4, double d5, double d6, Digest digest) {
        this.N = i;
        this.q = i2;
        this.d1 = i3;
        this.d2 = i4;
        this.d3 = i5;
        this.B = i6;
        this.beta = d4;
        this.normBound = d5;
        this.hashAlg = digest;
        init();
    }

    private void init() {
        double d4 = this.beta;
        this.betaSq = d4 * d4;
        double d5 = this.normBound;
        this.normBoundSq = d5 * d5;
    }

    public NTRUSigningParameters(InputStream inputStream) {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.N = dataInputStream.readInt();
        this.q = dataInputStream.readInt();
        this.f6431d = dataInputStream.readInt();
        this.d1 = dataInputStream.readInt();
        this.d2 = dataInputStream.readInt();
        this.d3 = dataInputStream.readInt();
        this.B = dataInputStream.readInt();
        this.beta = dataInputStream.readDouble();
        this.normBound = dataInputStream.readDouble();
        this.signFailTolerance = dataInputStream.readInt();
        this.bitsF = dataInputStream.readInt();
        String readUTF = dataInputStream.readUTF();
        if (McElieceCCA2KeyGenParameterSpec.SHA512.equals(readUTF)) {
            this.hashAlg = new SHA512Digest();
        } else if (McElieceCCA2KeyGenParameterSpec.SHA256.equals(readUTF)) {
            this.hashAlg = new SHA256Digest();
        }
        init();
    }

    public void writeTo(OutputStream outputStream) {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(this.N);
        dataOutputStream.writeInt(this.q);
        dataOutputStream.writeInt(this.f6431d);
        dataOutputStream.writeInt(this.d1);
        dataOutputStream.writeInt(this.d2);
        dataOutputStream.writeInt(this.d3);
        dataOutputStream.writeInt(this.B);
        dataOutputStream.writeDouble(this.beta);
        dataOutputStream.writeDouble(this.normBound);
        dataOutputStream.writeInt(this.signFailTolerance);
        dataOutputStream.writeInt(this.bitsF);
        dataOutputStream.writeUTF(this.hashAlg.getAlgorithmName());
    }

    @Override // java.lang.Object
    public NTRUSigningParameters clone() {
        return new NTRUSigningParameters(this.N, this.q, this.f6431d, this.B, this.beta, this.normBound, this.hashAlg);
    }

    public int hashCode() {
        int i = ((this.B + 31) * 31) + this.N;
        long doubleToLongBits = Double.doubleToLongBits(this.beta);
        long doubleToLongBits2 = Double.doubleToLongBits(this.betaSq);
        int i2 = ((((((((((((((i * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.bitsF) * 31) + this.f6431d) * 31) + this.d1) * 31) + this.d2) * 31) + this.d3) * 31;
        Digest digest = this.hashAlg;
        int hashCode = i2 + (digest == null ? 0 : digest.getAlgorithmName().hashCode());
        long doubleToLongBits3 = Double.doubleToLongBits(this.normBound);
        long doubleToLongBits4 = Double.doubleToLongBits(this.normBoundSq);
        return (((((((hashCode * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31) + this.q) * 31) + this.signFailTolerance;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUSigningParameters)) {
            return false;
        }
        NTRUSigningParameters nTRUSigningParameters = (NTRUSigningParameters) obj;
        if (this.B != nTRUSigningParameters.B || this.N != nTRUSigningParameters.N || Double.doubleToLongBits(this.beta) != Double.doubleToLongBits(nTRUSigningParameters.beta) || Double.doubleToLongBits(this.betaSq) != Double.doubleToLongBits(nTRUSigningParameters.betaSq) || this.bitsF != nTRUSigningParameters.bitsF || this.f6431d != nTRUSigningParameters.f6431d || this.d1 != nTRUSigningParameters.d1 || this.d2 != nTRUSigningParameters.d2 || this.d3 != nTRUSigningParameters.d3) {
            return false;
        }
        Digest digest = this.hashAlg;
        if (digest == null) {
            if (nTRUSigningParameters.hashAlg != null) {
                return false;
            }
        } else if (!digest.getAlgorithmName().equals(nTRUSigningParameters.hashAlg.getAlgorithmName())) {
            return false;
        }
        return Double.doubleToLongBits(this.normBound) == Double.doubleToLongBits(nTRUSigningParameters.normBound) && Double.doubleToLongBits(this.normBoundSq) == Double.doubleToLongBits(nTRUSigningParameters.normBoundSq) && this.q == nTRUSigningParameters.q && this.signFailTolerance == nTRUSigningParameters.signFailTolerance;
    }

    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringBuilder sb = new StringBuilder("SignatureParameters(N=" + this.N + " q=" + this.q);
        sb.append(" B=" + this.B + " beta=" + decimalFormat.format(this.beta) + " normBound=" + decimalFormat.format(this.normBound) + " hashAlg=" + this.hashAlg + ")");
        return sb.toString();
    }
}
