package org.spongycastle.crypto.signers;

import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.SignerWithRecovery;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.Arrays;

public class ISO9796d2Signer implements SignerWithRecovery {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private boolean fullMessage;
    private int keyBits;
    private byte[] mBuf;
    private int messageLength;
    private byte[] preBlock;
    private byte[] preSig;
    private byte[] recoveredMessage;
    private int trailer;

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest2, boolean z) {
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

    public ISO9796d2Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest2) {
        this(asymmetricBlockCipher, digest2, false);
    }

    @Override // org.spongycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = (RSAKeyParameters) cipherParameters;
        this.cipher.init(z, rSAKeyParameters);
        this.keyBits = rSAKeyParameters.getModulus().bitLength();
        this.block = new byte[((this.keyBits + 7) / 8)];
        if (this.trailer == 188) {
            this.mBuf = new byte[((this.block.length - this.digest.getDigestSize()) - 2)];
        } else {
            this.mBuf = new byte[((this.block.length - this.digest.getDigestSize()) - 3)];
        }
        reset();
    }

    private boolean isSameAs(byte[] bArr, byte[] bArr2) {
        int i = this.messageLength;
        byte[] bArr3 = this.mBuf;
        boolean z = true;
        if (i > bArr3.length) {
            if (bArr3.length > bArr2.length) {
                z = false;
            }
            for (int i2 = 0; i2 != this.mBuf.length; i2++) {
                if (bArr[i2] != bArr2[i2]) {
                    z = false;
                }
            }
        } else {
            if (i != bArr2.length) {
                z = false;
            }
            for (int i3 = 0; i3 != bArr2.length; i3++) {
                if (bArr[i3] != bArr2[i3]) {
                    z = false;
                }
            }
        }
        return z;
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    @Override // org.spongycastle.crypto.SignerWithRecovery
    public void updateWithRecoveredMessage(byte[] bArr) {
        byte[] processBlock = this.cipher.processBlock(bArr, 0, bArr.length);
        if (((processBlock[0] & 192) ^ 64) != 0) {
            throw new InvalidCipherTextException("malformed signature");
        } else if (((processBlock[processBlock.length - 1] & 15) ^ 12) == 0) {
            int i = 2;
            if (((processBlock[processBlock.length - 1] & 255) ^ 188) == 0) {
                i = 1;
            } else {
                int i2 = ((processBlock[processBlock.length - 2] & 255) << 8) | (processBlock[processBlock.length - 1] & 255);
                Integer trailer2 = ISOTrailers.getTrailer(this.digest);
                if (trailer2 == null) {
                    throw new IllegalArgumentException("unrecognised hash in signature");
                } else if (i2 != trailer2.intValue()) {
                    throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
                }
            }
            int i3 = 0;
            while (i3 != processBlock.length && ((processBlock[i3] & 15) ^ 10) != 0) {
                i3++;
            }
            int i4 = i3 + 1;
            int length = ((processBlock.length - i) - this.digest.getDigestSize()) - i4;
            if (length > 0) {
                if ((processBlock[0] & 32) == 0) {
                    this.fullMessage = true;
                    this.recoveredMessage = new byte[length];
                    byte[] bArr2 = this.recoveredMessage;
                    System.arraycopy(processBlock, i4, bArr2, 0, bArr2.length);
                } else {
                    this.fullMessage = false;
                    this.recoveredMessage = new byte[length];
                    byte[] bArr3 = this.recoveredMessage;
                    System.arraycopy(processBlock, i4, bArr3, 0, bArr3.length);
                }
                this.preSig = bArr;
                this.preBlock = processBlock;
                Digest digest2 = this.digest;
                byte[] bArr4 = this.recoveredMessage;
                digest2.update(bArr4, 0, bArr4.length);
                byte[] bArr5 = this.recoveredMessage;
                this.messageLength = bArr5.length;
                System.arraycopy(bArr5, 0, this.mBuf, 0, bArr5.length);
                return;
            }
            throw new InvalidCipherTextException("malformed block");
        } else {
            throw new InvalidCipherTextException("malformed signature");
        }
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte b2) {
        this.digest.update(b2);
        int i = this.messageLength;
        byte[] bArr = this.mBuf;
        if (i < bArr.length) {
            bArr[i] = b2;
        }
        this.messageLength++;
    }

    @Override // org.spongycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        while (i2 > 0 && this.messageLength < this.mBuf.length) {
            update(bArr[i]);
            i++;
            i2--;
        }
        this.digest.update(bArr, i, i2);
        this.messageLength += i2;
    }

    @Override // org.spongycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
        this.messageLength = 0;
        clearBlock(this.mBuf);
        byte[] bArr = this.recoveredMessage;
        if (bArr != null) {
            clearBlock(bArr);
        }
        this.recoveredMessage = null;
        this.fullMessage = false;
        if (this.preSig != null) {
            this.preSig = null;
            clearBlock(this.preBlock);
            this.preBlock = null;
        }
    }

    @Override // org.spongycastle.crypto.Signer
    public byte[] generateSignature() {
        int i;
        int i2;
        int i3;
        byte b2;
        int digestSize = this.digest.getDigestSize();
        boolean z = true;
        if (this.trailer == 188) {
            byte[] bArr = this.block;
            i = (bArr.length - digestSize) - 1;
            this.digest.doFinal(bArr, i);
            byte[] bArr2 = this.block;
            bArr2[bArr2.length - 1] = PSSSigner.TRAILER_IMPLICIT;
            i2 = 8;
        } else {
            i2 = 16;
            byte[] bArr3 = this.block;
            int length = (bArr3.length - digestSize) - 2;
            this.digest.doFinal(bArr3, length);
            byte[] bArr4 = this.block;
            int i4 = this.trailer;
            bArr4[bArr4.length - 2] = (byte) (i4 >>> 8);
            bArr4[bArr4.length - 1] = (byte) i4;
            i = length;
        }
        int i5 = this.messageLength;
        int i6 = ((((digestSize + i5) * 8) + i2) + 4) - this.keyBits;
        if (i6 > 0) {
            int i7 = i5 - ((i6 + 7) / 8);
            b2 = 96;
            i3 = i - i7;
            System.arraycopy(this.mBuf, 0, this.block, i3, i7);
            this.recoveredMessage = new byte[i7];
        } else {
            b2 = 64;
            i3 = i - i5;
            System.arraycopy(this.mBuf, 0, this.block, i3, i5);
            this.recoveredMessage = new byte[this.messageLength];
        }
        int i8 = i3 - 1;
        if (i8 > 0) {
            for (int i9 = i8; i9 != 0; i9--) {
                this.block[i9] = -69;
            }
            byte[] bArr5 = this.block;
            bArr5[i8] = (byte) (bArr5[i8] ^ 1);
            bArr5[0] = 11;
            bArr5[0] = (byte) (bArr5[0] | b2);
        } else {
            byte[] bArr6 = this.block;
            bArr6[0] = 10;
            bArr6[0] = (byte) (bArr6[0] | b2);
        }
        AsymmetricBlockCipher asymmetricBlockCipher = this.cipher;
        byte[] bArr7 = this.block;
        byte[] processBlock = asymmetricBlockCipher.processBlock(bArr7, 0, bArr7.length);
        if ((b2 & 32) != 0) {
            z = false;
        }
        this.fullMessage = z;
        byte[] bArr8 = this.mBuf;
        byte[] bArr9 = this.recoveredMessage;
        System.arraycopy(bArr8, 0, bArr9, 0, bArr9.length);
        clearBlock(this.mBuf);
        clearBlock(this.block);
        return processBlock;
    }

    @Override // org.spongycastle.crypto.Signer
    public boolean verifySignature(byte[] bArr) {
        byte[] bArr2;
        byte[] bArr3 = this.preSig;
        if (bArr3 == null) {
            try {
                bArr2 = this.cipher.processBlock(bArr, 0, bArr.length);
            } catch (Exception unused) {
                return false;
            }
        } else if (Arrays.areEqual(bArr3, bArr)) {
            bArr2 = this.preBlock;
            this.preSig = null;
            this.preBlock = null;
        } else {
            throw new IllegalStateException("updateWithRecoveredMessage called on different signature");
        }
        if (((bArr2[0] & 192) ^ 64) != 0) {
            return returnFalse(bArr2);
        }
        if (((bArr2[bArr2.length - 1] & 15) ^ 12) != 0) {
            return returnFalse(bArr2);
        }
        int i = 2;
        if (((bArr2[bArr2.length - 1] & 255) ^ 188) == 0) {
            i = 1;
        } else {
            int i2 = ((bArr2[bArr2.length - 2] & 255) << 8) | (bArr2[bArr2.length - 1] & 255);
            Integer trailer2 = ISOTrailers.getTrailer(this.digest);
            if (trailer2 == null) {
                throw new IllegalArgumentException("unrecognised hash in signature");
            } else if (i2 != trailer2.intValue()) {
                throw new IllegalStateException("signer initialised with wrong digest for trailer " + i2);
            }
        }
        int i3 = 0;
        while (i3 != bArr2.length && ((bArr2[i3] & 15) ^ 10) != 0) {
            i3++;
        }
        int i4 = i3 + 1;
        byte[] bArr4 = new byte[this.digest.getDigestSize()];
        int length = (bArr2.length - i) - bArr4.length;
        int i5 = length - i4;
        if (i5 <= 0) {
            return returnFalse(bArr2);
        }
        if ((bArr2[0] & 32) == 0) {
            this.fullMessage = true;
            if (this.messageLength > i5) {
                return returnFalse(bArr2);
            }
            this.digest.reset();
            this.digest.update(bArr2, i4, i5);
            this.digest.doFinal(bArr4, 0);
            boolean z = true;
            for (int i6 = 0; i6 != bArr4.length; i6++) {
                int i7 = length + i6;
                bArr2[i7] = (byte) (bArr2[i7] ^ bArr4[i6]);
                if (bArr2[i7] != 0) {
                    z = false;
                }
            }
            if (!z) {
                return returnFalse(bArr2);
            }
            this.recoveredMessage = new byte[i5];
            byte[] bArr5 = this.recoveredMessage;
            System.arraycopy(bArr2, i4, bArr5, 0, bArr5.length);
        } else {
            this.fullMessage = false;
            this.digest.doFinal(bArr4, 0);
            boolean z2 = true;
            for (int i8 = 0; i8 != bArr4.length; i8++) {
                int i9 = length + i8;
                bArr2[i9] = (byte) (bArr2[i9] ^ bArr4[i8]);
                if (bArr2[i9] != 0) {
                    z2 = false;
                }
            }
            if (!z2) {
                return returnFalse(bArr2);
            }
            this.recoveredMessage = new byte[i5];
            byte[] bArr6 = this.recoveredMessage;
            System.arraycopy(bArr2, i4, bArr6, 0, bArr6.length);
        }
        if (!(this.messageLength == 0 || isSameAs(this.mBuf, this.recoveredMessage))) {
            return returnFalse(bArr2);
        }
        clearBlock(this.mBuf);
        clearBlock(bArr2);
        return true;
    }

    private boolean returnFalse(byte[] bArr) {
        clearBlock(this.mBuf);
        clearBlock(bArr);
        return false;
    }

    @Override // org.spongycastle.crypto.SignerWithRecovery
    public boolean hasFullMessage() {
        return this.fullMessage;
    }

    @Override // org.spongycastle.crypto.SignerWithRecovery
    public byte[] getRecoveredMessage() {
        return this.recoveredMessage;
    }
}
