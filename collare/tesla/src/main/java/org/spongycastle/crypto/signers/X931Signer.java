package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class X931Signer implements Signer {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA224 = 14540;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private RSAKeyParameters kParam;
    private int keyBits;
    private int trailer;

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest2, boolean z) {
        this.cipher = asymmetricBlockCipher;
        this.digest = digest2;
        if (z) {
            this.trailer = 188;
            return;
        }
        Integer trailer2 = ISOTrailers.getTrailer(digest2);
        if (trailer2 != null) {
            this.trailer = trailer2.intValue();
            return;
        }
        throw new IllegalArgumentException("no valid trailer for digest: " + digest2.getAlgorithmName());
    }

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest2) {
        this(asymmetricBlockCipher, digest2, false);
    }

    @Override // org.spongycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.kParam = (RSAKeyParameters) cipherParameters;
        this.cipher.init(z, this.kParam);
        this.keyBits = this.kParam.getModulus().bitLength();
        this.block = new byte[((this.keyBits + 7) / 8)];
        reset();
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte b2) {
        this.digest.update(b2);
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    @Override // org.spongycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
    }

    @Override // org.spongycastle.crypto.Signer
    public byte[] generateSignature() {
        createSignatureBlock();
        AsymmetricBlockCipher asymmetricBlockCipher = this.cipher;
        byte[] bArr = this.block;
        BigInteger bigInteger = new BigInteger(1, asymmetricBlockCipher.processBlock(bArr, 0, bArr.length));
        clearBlock(this.block);
        return BigIntegers.asUnsignedByteArray((this.kParam.getModulus().bitLength() + 7) / 8, bigInteger.min(this.kParam.getModulus().subtract(bigInteger)));
    }

    private void createSignatureBlock() {
        int i;
        int digestSize = this.digest.getDigestSize();
        if (this.trailer == 188) {
            byte[] bArr = this.block;
            i = (bArr.length - digestSize) - 1;
            this.digest.doFinal(bArr, i);
            byte[] bArr2 = this.block;
            bArr2[bArr2.length - 1] = PSSSigner.TRAILER_IMPLICIT;
        } else {
            byte[] bArr3 = this.block;
            i = (bArr3.length - digestSize) - 2;
            this.digest.doFinal(bArr3, i);
            byte[] bArr4 = this.block;
            int i2 = this.trailer;
            bArr4[bArr4.length - 2] = (byte) (i2 >>> 8);
            bArr4[bArr4.length - 1] = (byte) i2;
        }
        this.block[0] = 107;
        for (int i3 = i - 2; i3 != 0; i3--) {
            this.block[i3] = -69;
        }
        this.block[i - 1] = -70;
    }

    @Override // org.spongycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        try {
            this.block = this.cipher.processBlock(bArr, 0, bArr.length);
            BigInteger bigInteger = new BigInteger(1, this.block);
            if ((bigInteger.intValue() & 15) != 12) {
                bigInteger = this.kParam.getModulus().subtract(bigInteger);
                if ((bigInteger.intValue() & 15) != 12) {
                    return false;
                }
            }
            createSignatureBlock();
            byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.block.length, bigInteger);
            boolean constantTimeAreEqual = Arrays.constantTimeAreEqual(this.block, asUnsignedByteArray);
            clearBlock(this.block);
            clearBlock(asUnsignedByteArray);
            return constantTimeAreEqual;
        } catch (Exception unused) {
            return false;
        }
    }
}
