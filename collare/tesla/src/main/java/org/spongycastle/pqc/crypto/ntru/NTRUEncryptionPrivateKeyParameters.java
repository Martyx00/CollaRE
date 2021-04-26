package org.spongycastle.pqc.crypto.ntru;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.pqc.math.ntru.polynomial.DenseTernaryPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;
import org.spongycastle.pqc.math.ntru.polynomial.ProductFormPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.SparseTernaryPolynomial;

public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters {
    public IntegerPolynomial fp;
    public IntegerPolynomial h;
    public Polynomial t;

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial integerPolynomial, Polynomial polynomial, IntegerPolynomial integerPolynomial2, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        this.h = integerPolynomial;
        this.t = polynomial;
        this.fp = integerPolynomial2;
    }

    public NTRUEncryptionPrivateKeyParameters(byte[] bArr, NTRUEncryptionParameters nTRUEncryptionParameters) {
        this(new ByteArrayInputStream(bArr), nTRUEncryptionParameters);
    }

    public NTRUEncryptionPrivateKeyParameters(InputStream inputStream, NTRUEncryptionParameters nTRUEncryptionParameters) {
        super(true, nTRUEncryptionParameters);
        if (nTRUEncryptionParameters.polyType == 1) {
            int i = nTRUEncryptionParameters.N;
            int i2 = nTRUEncryptionParameters.df1;
            int i3 = nTRUEncryptionParameters.df2;
            int i4 = nTRUEncryptionParameters.df3;
            int i5 = nTRUEncryptionParameters.fastFp ? nTRUEncryptionParameters.df3 : nTRUEncryptionParameters.df3 - 1;
            this.h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
            this.t = ProductFormPolynomial.fromBinary(inputStream, i, i2, i3, i4, i5);
        } else {
            this.h = IntegerPolynomial.fromBinary(inputStream, nTRUEncryptionParameters.N, nTRUEncryptionParameters.q);
            IntegerPolynomial fromBinary3Tight = IntegerPolynomial.fromBinary3Tight(inputStream, nTRUEncryptionParameters.N);
            this.t = nTRUEncryptionParameters.sparse ? new SparseTernaryPolynomial(fromBinary3Tight) : new DenseTernaryPolynomial(fromBinary3Tight);
        }
        init();
    }

    private void init() {
        if (this.params.fastFp) {
            this.fp = new IntegerPolynomial(this.params.N);
            this.fp.coeffs[0] = 1;
            return;
        }
        this.fp = this.t.toIntegerPolynomial().invertF3();
    }

    public byte[] getEncoded() {
        byte[] bArr;
        byte[] binary = this.h.toBinary(this.params.q);
        Polynomial polynomial = this.t;
        if (polynomial instanceof ProductFormPolynomial) {
            bArr = ((ProductFormPolynomial) polynomial).toBinary();
        } else {
            bArr = polynomial.toIntegerPolynomial().toBinary3Tight();
        }
        byte[] bArr2 = new byte[(binary.length + bArr.length)];
        System.arraycopy(binary, 0, bArr2, 0, binary.length);
        System.arraycopy(bArr, 0, bArr2, binary.length, bArr.length);
        return bArr2;
    }

    public void writeTo(OutputStream outputStream) {
        outputStream.write(getEncoded());
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.params == null ? 0 : this.params.hashCode()) + 31) * 31;
        Polynomial polynomial = this.t;
        int hashCode2 = (hashCode + (polynomial == null ? 0 : polynomial.hashCode())) * 31;
        IntegerPolynomial integerPolynomial = this.h;
        if (integerPolynomial != null) {
            i = integerPolynomial.hashCode();
        }
        return hashCode2 + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof NTRUEncryptionPrivateKeyParameters)) {
            return false;
        }
        NTRUEncryptionPrivateKeyParameters nTRUEncryptionPrivateKeyParameters = (NTRUEncryptionPrivateKeyParameters) obj;
        if (this.params == null) {
            if (nTRUEncryptionPrivateKeyParameters.params != null) {
                return false;
            }
        } else if (!this.params.equals(nTRUEncryptionPrivateKeyParameters.params)) {
            return false;
        }
        Polynomial polynomial = this.t;
        if (polynomial == null) {
            if (nTRUEncryptionPrivateKeyParameters.t != null) {
                return false;
            }
        } else if (!polynomial.equals(nTRUEncryptionPrivateKeyParameters.t)) {
            return false;
        }
        return this.h.equals(nTRUEncryptionPrivateKeyParameters.h);
    }
}
