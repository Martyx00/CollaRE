package org.spongycastle.pqc.jcajce.provider.mceliece;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.util.DigestFactory;
import org.spongycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.spongycastle.pqc.crypto.mceliece.McElieceFujisakiCipher;
import org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher;

public class McElieceFujisakiCipherSpi extends AsymmetricHybridCipher implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private ByteArrayOutputStream buf = new ByteArrayOutputStream();
    private McElieceFujisakiCipher cipher;
    private Digest digest;

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public int decryptOutputSize(int i) {
        return 0;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public int encryptOutputSize(int i) {
        return 0;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public String getName() {
        return "McElieceFujisakiCipher";
    }

    protected McElieceFujisakiCipherSpi(Digest digest2, McElieceFujisakiCipher mcElieceFujisakiCipher) {
        this.digest = digest2;
        this.cipher = mcElieceFujisakiCipher;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt, org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public byte[] update(byte[] bArr, int i, int i2) {
        this.buf.write(bArr, i, i2);
        return new byte[0];
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt, org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public byte[] doFinal(byte[] bArr, int i, int i2) {
        update(bArr, i, i2);
        byte[] byteArray = this.buf.toByteArray();
        this.buf.reset();
        if (this.opMode == 1) {
            try {
                return this.cipher.messageEncrypt(byteArray);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else if (this.opMode != 2) {
            return null;
        } else {
            try {
                return this.cipher.messageDecrypt(byteArray);
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        ParametersWithRandom parametersWithRandom = new ParametersWithRandom(McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey) key), secureRandom);
        this.digest.reset();
        this.cipher.init(true, parametersWithRandom);
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.AsymmetricHybridCipher
    public void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        AsymmetricKeyParameter generatePrivateKeyParameter = McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey) key);
        this.digest.reset();
        this.cipher.init(false, generatePrivateKeyParameter);
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public int getKeySize(Key key) {
        McElieceCCA2KeyParameters mcElieceCCA2KeyParameters;
        if (key instanceof PublicKey) {
            mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters) McElieceCCA2KeysToParams.generatePublicKeyParameter((PublicKey) key);
        } else {
            mcElieceCCA2KeyParameters = (McElieceCCA2KeyParameters) McElieceCCA2KeysToParams.generatePrivateKeyParameter((PrivateKey) key);
        }
        return this.cipher.getKeySize(mcElieceCCA2KeyParameters);
    }

    public byte[] messageEncrypt(byte[] bArr) {
        try {
            return this.cipher.messageEncrypt(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] messageDecrypt(byte[] bArr) {
        try {
            return this.cipher.messageDecrypt(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class McElieceFujisaki extends McElieceFujisakiCipherSpi {
        public McElieceFujisaki() {
            super(DigestFactory.createSHA1(), new McElieceFujisakiCipher());
        }
    }
}
