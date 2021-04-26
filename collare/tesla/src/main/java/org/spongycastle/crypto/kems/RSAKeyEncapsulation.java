package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.BigIntegers;

public class RSAKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private DerivationFunction kdf;
    private RSAKeyParameters key;
    private SecureRandom rnd;

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.kdf = derivationFunction;
        this.rnd = secureRandom;
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) {
        if (cipherParameters instanceof RSAKeyParameters) {
            this.key = (RSAKeyParameters) cipherParameters;
            return;
        }
        throw new IllegalArgumentException("RSA key required");
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int i, int i2) {
        if (!this.key.isPrivate()) {
            BigInteger modulus = this.key.getModulus();
            BigInteger exponent = this.key.getExponent();
            BigInteger createRandomInRange = BigIntegers.createRandomInRange(ZERO, modulus.subtract(ONE), this.rnd);
            byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray((modulus.bitLength() + 7) / 8, createRandomInRange.modPow(exponent, modulus));
            System.arraycopy(asUnsignedByteArray, 0, bArr, i, asUnsignedByteArray.length);
            return generateKey(modulus, createRandomInRange, i2);
        }
        throw new IllegalArgumentException("Public key required for encryption");
    }

    public CipherParameters encrypt(byte[] bArr, int i) {
        return encrypt(bArr, 0, i);
    }

    @Override // org.spongycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int i, int i2, int i3) {
        if (this.key.isPrivate()) {
            BigInteger modulus = this.key.getModulus();
            BigInteger exponent = this.key.getExponent();
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
            return generateKey(modulus, new BigInteger(1, bArr2).modPow(exponent, modulus), i3);
        }
        throw new IllegalArgumentException("Private key required for decryption");
    }

    public CipherParameters decrypt(byte[] bArr, int i) {
        return decrypt(bArr, 0, bArr.length, i);
    }

    /* access modifiers changed from: protected */
    public KeyParameter generateKey(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.kdf.init(new KDFParameters(BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2), null));
        byte[] bArr = new byte[i];
        this.kdf.generateBytes(bArr, 0, bArr.length);
        return new KeyParameter(bArr);
    }
}
