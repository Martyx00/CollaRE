package org.spongycastle.crypto.encodings;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricBlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.util.Arrays;

public class PKCS1Encoding implements AsymmetricBlockCipher {
    private static final int HEADER_LENGTH = 10;
    public static final String NOT_STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.not_strict";
    public static final String STRICT_LENGTH_ENABLED_PROPERTY = "org.spongycastle.pkcs1.strict";
    private byte[] blockBuffer;
    private AsymmetricBlockCipher engine;
    private byte[] fallback = null;
    private boolean forEncryption;
    private boolean forPrivateKey;
    private int pLen = -1;
    private SecureRandom random;
    private boolean useStrictLength;

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, int i) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.pLen = i;
    }

    public PKCS1Encoding(AsymmetricBlockCipher asymmetricBlockCipher, byte[] bArr) {
        this.engine = asymmetricBlockCipher;
        this.useStrictLength = useStrict();
        this.fallback = bArr;
        this.pLen = bArr.length;
    }

    private boolean useStrict() {
        String str = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class org.spongycastle.crypto.encodings.PKCS1Encoding.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        String str2 = (String) AccessController.doPrivileged(new PrivilegedAction() {
            /* class org.spongycastle.crypto.encodings.PKCS1Encoding.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Object run() {
                return System.getProperty(PKCS1Encoding.NOT_STRICT_LENGTH_ENABLED_PROPERTY);
            }
        });
        if (str2 != null) {
            return !str2.equals("true");
        }
        if (str == null || str.equals("true")) {
            return true;
        }
        return false;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.engine;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.random = parametersWithRandom.getRandom();
            asymmetricKeyParameter = (AsymmetricKeyParameter) parametersWithRandom.getParameters();
        } else {
            asymmetricKeyParameter = (AsymmetricKeyParameter) cipherParameters;
            if (!asymmetricKeyParameter.isPrivate() && z) {
                this.random = new SecureRandom();
            }
        }
        this.engine.init(z, cipherParameters);
        this.forPrivateKey = asymmetricKeyParameter.isPrivate();
        this.forEncryption = z;
        this.blockBuffer = new byte[this.engine.getOutputBlockSize()];
        if (this.pLen > 0 && this.fallback == null && this.random == null) {
            throw new IllegalArgumentException("encoder requires random");
        }
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.engine.getInputBlockSize();
        return this.forEncryption ? inputBlockSize - 10 : inputBlockSize;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.engine.getOutputBlockSize();
        return this.forEncryption ? outputBlockSize : outputBlockSize - 10;
    }

    @Override // org.spongycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        if (this.forEncryption) {
            return encodeBlock(bArr, i, i2);
        }
        return decodeBlock(bArr, i, i2);
    }

    private byte[] encodeBlock(byte[] bArr, int i, int i2) {
        if (i2 <= getInputBlockSize()) {
            byte[] bArr2 = new byte[this.engine.getInputBlockSize()];
            if (this.forPrivateKey) {
                bArr2[0] = 1;
                for (int i3 = 1; i3 != (bArr2.length - i2) - 1; i3++) {
                    bArr2[i3] = -1;
                }
            } else {
                this.random.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int i4 = 1; i4 != (bArr2.length - i2) - 1; i4++) {
                    while (bArr2[i4] == 0) {
                        bArr2[i4] = (byte) this.random.nextInt();
                    }
                }
            }
            bArr2[(bArr2.length - i2) - 1] = 0;
            System.arraycopy(bArr, i, bArr2, bArr2.length - i2, i2);
            return this.engine.processBlock(bArr2, 0, bArr2.length);
        }
        throw new IllegalArgumentException("input data too large");
    }

    private static int checkPkcs1Encoding(byte[] bArr, int i) {
        int i2 = 0 | (bArr[0] ^ 2);
        int i3 = i + 1;
        int length = bArr.length - i3;
        int i4 = i2;
        for (int i5 = 1; i5 < length; i5++) {
            byte b2 = bArr[i5];
            int i6 = b2 | (b2 >> 1);
            int i7 = i6 | (i6 >> 2);
            i4 |= ((i7 | (i7 >> 4)) & 1) - 1;
        }
        int i8 = bArr[bArr.length - i3] | i4;
        int i9 = i8 | (i8 >> 1);
        int i10 = i9 | (i9 >> 2);
        return ~(((i10 | (i10 >> 4)) & 1) - 1);
    }

    private byte[] decodeBlockOrRandom(byte[] bArr, int i, int i2) {
        if (this.forPrivateKey) {
            byte[] processBlock = this.engine.processBlock(bArr, i, i2);
            byte[] bArr2 = this.fallback;
            if (bArr2 == null) {
                bArr2 = new byte[this.pLen];
                this.random.nextBytes(bArr2);
            }
            if (this.useStrictLength && (processBlock.length != this.engine.getOutputBlockSize())) {
                processBlock = this.blockBuffer;
            }
            int checkPkcs1Encoding = checkPkcs1Encoding(processBlock, this.pLen);
            byte[] bArr3 = new byte[this.pLen];
            int i3 = 0;
            while (true) {
                int i4 = this.pLen;
                if (i3 < i4) {
                    bArr3[i3] = (byte) ((processBlock[(processBlock.length - i4) + i3] & (~checkPkcs1Encoding)) | (bArr2[i3] & checkPkcs1Encoding));
                    i3++;
                } else {
                    Arrays.fill(processBlock, (byte) 0);
                    return bArr3;
                }
            }
        } else {
            throw new InvalidCipherTextException("sorry, this method is only for decryption, not for signing");
        }
    }

    private byte[] decodeBlock(byte[] bArr, int i, int i2) {
        if (this.pLen != -1) {
            return decodeBlockOrRandom(bArr, i, i2);
        }
        byte[] processBlock = this.engine.processBlock(bArr, i, i2);
        boolean z = true;
        boolean z2 = this.useStrictLength & (processBlock.length != this.engine.getOutputBlockSize());
        if (processBlock.length < getOutputBlockSize()) {
            processBlock = this.blockBuffer;
        }
        byte b2 = processBlock[0];
        boolean z3 = this.forPrivateKey ? b2 != 2 : b2 != 1;
        int findStart = findStart(b2, processBlock) + 1;
        if (findStart >= 10) {
            z = false;
        }
        if (z3 || z) {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect");
        } else if (!z2) {
            byte[] bArr2 = new byte[(processBlock.length - findStart)];
            System.arraycopy(processBlock, findStart, bArr2, 0, bArr2.length);
            return bArr2;
        } else {
            Arrays.fill(processBlock, (byte) 0);
            throw new InvalidCipherTextException("block incorrect size");
        }
    }

    private int findStart(byte b2, byte[] bArr) {
        boolean z = false;
        int i = -1;
        for (int i2 = 1; i2 != bArr.length; i2++) {
            byte b3 = bArr[i2];
            if ((b3 == 0) && (i < 0)) {
                i = i2;
            }
            z |= (b3 != -1) & (b2 == 1) & (i < 0);
        }
        if (z) {
            return -1;
        }
        return i;
    }
}
