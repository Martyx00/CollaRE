package org.spongycastle.pqc.jcajce.provider.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.CipherSpi;

public abstract class CipherSpiExt extends CipherSpi {
    public static final int DECRYPT_MODE = 2;
    public static final int ENCRYPT_MODE = 1;
    protected int opMode;

    public abstract int doFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public abstract byte[] doFinal(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public final AlgorithmParameters engineGetParameters() {
        return null;
    }

    public abstract int getBlockSize();

    public abstract byte[] getIV();

    public abstract int getKeySize(Key key);

    public abstract String getName();

    public abstract int getOutputSize(int i);

    public abstract AlgorithmParameterSpec getParameters();

    public abstract void initDecrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec);

    public abstract void initEncrypt(Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom);

    /* access modifiers changed from: protected */
    public abstract void setMode(String str);

    /* access modifiers changed from: protected */
    public abstract void setPadding(String str);

    public abstract int update(byte[] bArr, int i, int i2, byte[] bArr2, int i3);

    public abstract byte[] update(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidParameterException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        if (algorithmParameters == null) {
            engineInit(i, key, secureRandom);
        } else {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        if (algorithmParameterSpec != null && !(algorithmParameterSpec instanceof AlgorithmParameterSpec)) {
            throw new InvalidAlgorithmParameterException();
        } else if (key == null || !(key instanceof Key)) {
            throw new InvalidKeyException();
        } else {
            this.opMode = i;
            if (i == 1) {
                initEncrypt(key, algorithmParameterSpec, secureRandom);
            } else if (i == 2) {
                initDecrypt(key, algorithmParameterSpec);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        return doFinal(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return doFinal(bArr, i, i2, bArr2, i3);
    }

    /* access modifiers changed from: protected */
    public final int engineGetBlockSize() {
        return getBlockSize();
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final int engineGetKeySize(Key key) {
        if (key instanceof Key) {
            return getKeySize(key);
        }
        throw new InvalidKeyException("Unsupported key.");
    }

    /* access modifiers changed from: protected */
    public final byte[] engineGetIV() {
        return getIV();
    }

    /* access modifiers changed from: protected */
    public final int engineGetOutputSize(int i) {
        return getOutputSize(i);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final void engineSetMode(String str) {
        setMode(str);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final void engineSetPadding(String str) {
        setPadding(str);
    }

    /* access modifiers changed from: protected */
    public final byte[] engineUpdate(byte[] bArr, int i, int i2) {
        return update(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public final int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        return update(bArr, i, i2, bArr2, i3);
    }

    public final byte[] update(byte[] bArr) {
        return update(bArr, 0, bArr.length);
    }

    public final byte[] doFinal() {
        return doFinal(null, 0, 0);
    }

    public final byte[] doFinal(byte[] bArr) {
        return doFinal(bArr, 0, bArr.length);
    }
}
