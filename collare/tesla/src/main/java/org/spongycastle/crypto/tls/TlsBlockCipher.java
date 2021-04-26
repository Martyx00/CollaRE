package org.spongycastle.crypto.tls;

import java.security.SecureRandom;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;

public class TlsBlockCipher implements TlsCipher {
    protected TlsContext context;
    protected BlockCipher decryptCipher;
    protected BlockCipher encryptCipher;
    protected boolean encryptThenMAC;
    protected byte[] randomData = new byte[256];
    protected TlsMac readMac;
    protected boolean useExplicitIV;
    protected TlsMac writeMac;

    /* access modifiers changed from: protected */
    public int lowestBitSet(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 0;
        while ((i & 1) == 0) {
            i2++;
            i >>= 1;
        }
        return i2;
    }

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int i) {
        byte[] bArr;
        byte[] bArr2;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.context = tlsContext;
        tlsContext.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(tlsContext);
        this.encryptThenMAC = tlsContext.getSecurityParameters().encryptThenMAC;
        int digestSize = (i * 2) + digest.getDigestSize() + digest2.getDigestSize();
        int blockSize = !this.useExplicitIV ? digestSize + blockCipher.getBlockSize() + blockCipher2.getBlockSize() : digestSize;
        byte[] calculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, blockSize);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, calculateKeyBlock, 0, digest.getDigestSize());
        int digestSize2 = digest.getDigestSize() + 0;
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, calculateKeyBlock, digestSize2, digest2.getDigestSize());
        int digestSize3 = digestSize2 + digest2.getDigestSize();
        KeyParameter keyParameter = new KeyParameter(calculateKeyBlock, digestSize3, i);
        int i2 = digestSize3 + i;
        KeyParameter keyParameter2 = new KeyParameter(calculateKeyBlock, i2, i);
        int i3 = i2 + i;
        if (this.useExplicitIV) {
            bArr2 = new byte[blockCipher.getBlockSize()];
            bArr = new byte[blockCipher2.getBlockSize()];
        } else {
            bArr2 = Arrays.copyOfRange(calculateKeyBlock, i3, blockCipher.getBlockSize() + i3);
            int blockSize2 = i3 + blockCipher.getBlockSize();
            bArr = Arrays.copyOfRange(calculateKeyBlock, blockSize2, blockCipher2.getBlockSize() + blockSize2);
            i3 = blockSize2 + blockCipher2.getBlockSize();
        }
        if (i3 == blockSize) {
            if (tlsContext.isServer()) {
                this.writeMac = tlsMac2;
                this.readMac = tlsMac;
                this.encryptCipher = blockCipher2;
                this.decryptCipher = blockCipher;
                parametersWithIV = new ParametersWithIV(keyParameter2, bArr);
                parametersWithIV2 = new ParametersWithIV(keyParameter, bArr2);
            } else {
                this.writeMac = tlsMac;
                this.readMac = tlsMac2;
                this.encryptCipher = blockCipher;
                this.decryptCipher = blockCipher2;
                parametersWithIV = new ParametersWithIV(keyParameter, bArr2);
                parametersWithIV2 = new ParametersWithIV(keyParameter2, bArr);
            }
            this.encryptCipher.init(true, parametersWithIV);
            this.decryptCipher.init(false, parametersWithIV2);
            return;
        }
        throw new TlsFatalAlert(80);
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i) {
        int i2;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        if (this.useExplicitIV) {
            i -= blockSize;
        }
        if (this.encryptThenMAC) {
            int i3 = i - size;
            i2 = i3 - (i3 % blockSize);
        } else {
            i2 = (i - (i % blockSize)) - size;
        }
        return i2 - 1;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte[] bArr2;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        ProtocolVersion serverVersion = this.context.getServerVersion();
        int i5 = (blockSize - 1) - ((!this.encryptThenMAC ? i2 + size : i2) % blockSize);
        int chooseExtraPadBlocks = (serverVersion.isDTLS() || serverVersion.isSSL()) ? i5 : i5 + (chooseExtraPadBlocks(this.context.getSecureRandom(), (255 - i5) / blockSize) * blockSize);
        int i6 = size + i2 + chooseExtraPadBlocks + 1;
        if (this.useExplicitIV) {
            i6 += blockSize;
        }
        byte[] bArr3 = new byte[i6];
        if (this.useExplicitIV) {
            byte[] bArr4 = new byte[blockSize];
            this.context.getNonceRandomGenerator().nextBytes(bArr4);
            this.encryptCipher.init(true, new ParametersWithIV(null, bArr4));
            System.arraycopy(bArr4, 0, bArr3, 0, blockSize);
            bArr2 = bArr;
            i4 = i;
            i3 = blockSize + 0;
        } else {
            bArr2 = bArr;
            i4 = i;
            i3 = 0;
        }
        System.arraycopy(bArr2, i4, bArr3, i3, i2);
        int i7 = i3 + i2;
        if (!this.encryptThenMAC) {
            byte[] calculateMac = this.writeMac.calculateMac(j, s, bArr, i, i2);
            System.arraycopy(calculateMac, 0, bArr3, i7, calculateMac.length);
            i7 += calculateMac.length;
        }
        int i8 = i7;
        int i9 = 0;
        while (i9 <= chooseExtraPadBlocks) {
            bArr3[i8] = (byte) chooseExtraPadBlocks;
            i9++;
            i8++;
        }
        while (i3 < i8) {
            this.encryptCipher.processBlock(bArr3, i3, bArr3, i3);
            i3 += blockSize;
        }
        if (!this.encryptThenMAC) {
            return bArr3;
        }
        byte[] calculateMac2 = this.writeMac.calculateMac(j, s, bArr3, 0, i8);
        System.arraycopy(calculateMac2, 0, bArr3, i8, calculateMac2.length);
        int length = calculateMac2.length;
        return bArr3;
    }

    @Override // org.spongycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        byte[] bArr2;
        int blockSize = this.decryptCipher.getBlockSize();
        int size = this.readMac.getSize();
        if (this.encryptThenMAC) {
            i3 = blockSize + size;
        } else {
            i3 = Math.max(blockSize, size + 1);
        }
        if (this.useExplicitIV) {
            i3 += blockSize;
        }
        if (i2 >= i3) {
            int i7 = this.encryptThenMAC ? i2 - size : i2;
            if (i7 % blockSize == 0) {
                if (this.encryptThenMAC) {
                    int i8 = i + i2;
                    if (!Arrays.constantTimeAreEqual(this.readMac.calculateMac(j, s, bArr, i, i2 - size), Arrays.copyOfRange(bArr, i8 - size, i8))) {
                        throw new TlsFatalAlert(20);
                    }
                }
                if (this.useExplicitIV) {
                    this.decryptCipher.init(false, new ParametersWithIV(null, bArr, i, blockSize));
                    i4 = i + blockSize;
                    i5 = i7 - blockSize;
                } else {
                    i4 = i;
                    i5 = i7;
                }
                for (int i9 = 0; i9 < i5; i9 += blockSize) {
                    int i10 = i4 + i9;
                    this.decryptCipher.processBlock(bArr, i10, bArr, i10);
                }
                int checkPaddingConstantTime = checkPaddingConstantTime(bArr, i4, i5, blockSize, this.encryptThenMAC ? 0 : size);
                boolean z = checkPaddingConstantTime == 0;
                int i11 = i5 - checkPaddingConstantTime;
                if (!this.encryptThenMAC) {
                    i11 -= size;
                    int i12 = i4 + i11;
                    i6 = i4;
                    bArr2 = bArr;
                    z |= !Arrays.constantTimeAreEqual(this.readMac.calculateMacConstantTime(j, s, bArr, i4, i11, i5 - size, this.randomData), Arrays.copyOfRange(bArr, i12, i12 + size));
                } else {
                    i6 = i4;
                    bArr2 = bArr;
                }
                if (!z) {
                    return Arrays.copyOfRange(bArr2, i6, i6 + i11);
                }
                throw new TlsFatalAlert(20);
            }
            throw new TlsFatalAlert(21);
        }
        throw new TlsFatalAlert(50);
    }

    /* access modifiers changed from: protected */
    public int checkPaddingConstantTime(byte[] bArr, int i, int i2, int i3, int i4) {
        byte b2;
        int i5;
        int i6 = i + i2;
        byte b3 = bArr[i6 - 1];
        int i7 = (b3 & 255) + 1;
        if ((!TlsUtils.isSSL(this.context) || i7 <= i3) && i4 + i7 <= i2) {
            int i8 = i6 - i7;
            b2 = 0;
            while (true) {
                int i9 = i8 + 1;
                b2 = (byte) (b2 | (bArr[i8] ^ b3));
                if (i9 >= i6) {
                    break;
                }
                i8 = i9;
            }
            if (b2 != 0) {
                i5 = i7;
                i7 = 0;
            } else {
                i5 = i7;
            }
        } else {
            i5 = 0;
            b2 = 0;
            i7 = 0;
        }
        byte[] bArr2 = this.randomData;
        while (i5 < 256) {
            b2 = (byte) ((bArr2[i5] ^ b3) | b2);
            i5++;
        }
        bArr2[0] = (byte) (bArr2[0] ^ b2);
        return i7;
    }

    /* access modifiers changed from: protected */
    public int chooseExtraPadBlocks(SecureRandom secureRandom, int i) {
        return Math.min(lowestBitSet(secureRandom.nextInt()), i);
    }
}
