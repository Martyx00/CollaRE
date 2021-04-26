package org.spongycastle.jcajce.provider.asymmetric.dh;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.jcajce.spec.UserKeyingMaterialSpec;

public class KeyAgreementSpi extends BaseAgreementSpi {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private BigInteger g;
    private BigInteger p;
    private BigInteger result;
    private BigInteger x;

    public KeyAgreementSpi() {
        super("Diffie-Hellman", null);
    }

    public KeyAgreementSpi(String str, DerivationFunction derivationFunction) {
        super(str, derivationFunction);
    }

    /* access modifiers changed from: protected */
    public byte[] bigIntToBytes(BigInteger bigInteger) {
        int bitLength = (this.p.bitLength() + 7) / 8;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length == bitLength) {
            return byteArray;
        }
        if (byteArray[0] == 0 && byteArray.length == bitLength + 1) {
            byte[] bArr = new byte[(byteArray.length - 1)];
            System.arraycopy(byteArray, 1, bArr, 0, bArr.length);
            return bArr;
        }
        byte[] bArr2 = new byte[bitLength];
        System.arraycopy(byteArray, 0, bArr2, bArr2.length - byteArray.length, byteArray.length);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public Key engineDoPhase(Key key, boolean z) {
        if (this.x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        } else if (key instanceof DHPublicKey) {
            DHPublicKey dHPublicKey = (DHPublicKey) key;
            if (!dHPublicKey.getParams().getG().equals(this.g) || !dHPublicKey.getParams().getP().equals(this.p)) {
                throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
            }
            BigInteger y = dHPublicKey.getY();
            if (y == null || y.compareTo(TWO) < 0 || y.compareTo(this.p.subtract(ONE)) >= 0) {
                throw new InvalidKeyException("Invalid DH PublicKey");
            }
            this.result = y.modPow(this.x, this.p);
            if (this.result.compareTo(ONE) == 0) {
                throw new InvalidKeyException("Shared key can't be 1");
            } else if (z) {
                return null;
            } else {
                return new BCDHPublicKey(this.result, dHPublicKey.getParams());
            }
        } else {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] engineGenerateSecret() {
        if (this.x != null) {
            return super.engineGenerateSecret();
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i) {
        if (this.x != null) {
            return super.engineGenerateSecret(bArr, i);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi, org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public SecretKey engineGenerateSecret(String str) {
        if (this.x != null) {
            byte[] bigIntToBytes = bigIntToBytes(this.result);
            if (str.equals("TlsPremasterSecret")) {
                return new SecretKeySpec(trimZeroes(bigIntToBytes), str);
            }
            return super.engineGenerateSecret(str);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            if (algorithmParameterSpec == null) {
                this.p = dHPrivateKey.getParams().getP();
                this.g = dHPrivateKey.getParams().getG();
            } else if (algorithmParameterSpec instanceof DHParameterSpec) {
                DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
                this.p = dHParameterSpec.getP();
                this.g = dHParameterSpec.getG();
            } else if (algorithmParameterSpec instanceof UserKeyingMaterialSpec) {
                this.p = dHPrivateKey.getParams().getP();
                this.g = dHPrivateKey.getParams().getG();
                this.ukmParameters = ((UserKeyingMaterialSpec) algorithmParameterSpec).getUserKeyingMaterial();
            } else {
                throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
            }
            BigInteger x2 = dHPrivateKey.getX();
            this.result = x2;
            this.x = x2;
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public void engineInit(Key key, SecureRandom secureRandom) {
        if (key instanceof DHPrivateKey) {
            DHPrivateKey dHPrivateKey = (DHPrivateKey) key;
            this.p = dHPrivateKey.getParams().getP();
            this.g = dHPrivateKey.getParams().getG();
            BigInteger x2 = dHPrivateKey.getX();
            this.result = x2;
            this.x = x2;
            return;
        }
        throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi
    public byte[] calcSecret() {
        return bigIntToBytes(this.result);
    }

    public static class DHwithRFC2631KDF extends KeyAgreementSpi {
        public DHwithRFC2631KDF() {
            super("DHwithRFC2631KDF", new DHKEKGenerator(DigestFactory.createSHA1()));
        }
    }
}
