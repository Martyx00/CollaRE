package org.spongycastle.pqc.jcajce.provider.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.ShortBufferException;

public abstract class AsymmetricHybridCipher extends CipherSpiExt {
    protected AlgorithmParameterSpec paramSpec;

    /* access modifiers changed from: protected */
    public abstract int decryptOutputSize(int i);

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public abstract byte[] doFinal(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract int encryptOutputSize(int i);

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getBlockSize() {
        return 0;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final byte[] getIV() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void initCipherDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    /* access modifiers changed from: protected */
    public abstract void initCipherEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void setMode(String str) {
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void setPadding(String str) {
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public abstract byte[] update(byte[] bArr, int i, int i2);

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final AlgorithmParameterSpec getParameters() {
        return this.paramSpec;
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int getOutputSize(int i) {
        if (this.opMode == 1) {
            return encryptOutputSize(i);
        }
        return decryptOutputSize(i);
    }

    public final void initEncrypt(Key key) {
        try {
            initEncrypt(key, null, new SecureRandom());
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, SecureRandom secureRandom) {
        try {
            initEncrypt(key, null, secureRandom);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        initEncrypt(key, algorithmParameterSpec, new SecureRandom());
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        this.opMode = 1;
        initCipherEncrypt(key, algorithmParameterSpec, secureRandom);
    }

    public final void initDecrypt(Key key) {
        try {
            initDecrypt(key, null);
        } catch (InvalidAlgorithmParameterException unused) {
            throw new InvalidParameterException("This cipher needs algorithm parameters for initialization (cannot be null).");
        }
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        this.opMode = 2;
        initCipherDecrypt(key, algorithmParameterSpec);
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int update(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr2.length >= getOutputSize(i2)) {
            byte[] update = update(bArr, i, i2);
            System.arraycopy(update, 0, bArr2, i3, update.length);
            return update.length;
        }
        throw new ShortBufferException("output");
    }

    @Override // org.spongycastle.pqc.jcajce.provider.util.CipherSpiExt
    public final int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr2.length >= getOutputSize(i2)) {
            byte[] doFinal = doFinal(bArr, i, i2);
            System.arraycopy(doFinal, 0, bArr2, i3, doFinal.length);
            return doFinal.length;
        }
        throw new ShortBufferException("Output buffer too short.");
    }
}
