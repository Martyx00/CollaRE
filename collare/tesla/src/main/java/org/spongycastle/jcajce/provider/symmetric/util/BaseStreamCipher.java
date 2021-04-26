package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12KeyWithParameters;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;

public class BaseStreamCipher extends BaseWrapCipher implements PBE {
    private Class[] availableSpecs;
    private StreamCipher cipher;
    private int digest;
    private int ivLength;
    private ParametersWithIV ivParam;
    private int keySizeInBits;
    private String pbeAlgorithm;
    private PBEParameterSpec pbeSpec;

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public int engineGetOutputSize(int i) {
        return i;
    }

    protected BaseStreamCipher(StreamCipher streamCipher, int i) {
        this(streamCipher, i, -1, -1);
    }

    protected BaseStreamCipher(StreamCipher streamCipher, int i, int i2, int i3) {
        this.availableSpecs = new Class[]{RC2ParameterSpec.class, RC5ParameterSpec.class, IvParameterSpec.class, PBEParameterSpec.class};
        this.ivLength = 0;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.cipher = streamCipher;
        this.ivLength = i;
        this.keySizeInBits = i2;
        this.digest = i3;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public byte[] engineGetIV() {
        ParametersWithIV parametersWithIV = this.ivParam;
        if (parametersWithIV != null) {
            return parametersWithIV.getIV();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length * 8;
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public AlgorithmParameters engineGetParameters() {
        if (this.engineParams != null || this.pbeSpec == null) {
            return this.engineParams;
        }
        try {
            AlgorithmParameters createParametersInstance = createParametersInstance(this.pbeAlgorithm);
            createParametersInstance.init(this.pbeSpec);
            return createParametersInstance;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public void engineSetMode(String str) {
        if (!str.equalsIgnoreCase("ECB")) {
            throw new IllegalArgumentException("can't support mode " + str);
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public void engineSetPadding(String str) {
        if (!str.equalsIgnoreCase("NoPadding")) {
            throw new NoSuchPaddingException("Padding " + str + " unknown.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public void engineInit(int i, Key key, AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
        ParametersWithIV parametersWithIV;
        this.pbeSpec = null;
        this.pbeAlgorithm = null;
        this.engineParams = null;
        if (key instanceof SecretKey) {
            if (key instanceof PKCS12Key) {
                PKCS12Key pKCS12Key = (PKCS12Key) key;
                this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                if ((pKCS12Key instanceof PKCS12KeyWithParameters) && this.pbeSpec == null) {
                    PKCS12KeyWithParameters pKCS12KeyWithParameters = (PKCS12KeyWithParameters) pKCS12Key;
                    this.pbeSpec = new PBEParameterSpec(pKCS12KeyWithParameters.getSalt(), pKCS12KeyWithParameters.getIterationCount());
                }
                parametersWithIV = PBE.Util.makePBEParameters(pKCS12Key.getEncoded(), 2, this.digest, this.keySizeInBits, this.ivLength * 8, this.pbeSpec, this.cipher.getAlgorithmName());
            } else if (key instanceof BCPBEKey) {
                BCPBEKey bCPBEKey = (BCPBEKey) key;
                if (bCPBEKey.getOID() != null) {
                    this.pbeAlgorithm = bCPBEKey.getOID().getId();
                } else {
                    this.pbeAlgorithm = bCPBEKey.getAlgorithm();
                }
                if (bCPBEKey.getParam() != null) {
                    parametersWithIV = bCPBEKey.getParam();
                    this.pbeSpec = new PBEParameterSpec(bCPBEKey.getSalt(), bCPBEKey.getIterationCount());
                } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    CipherParameters makePBEParameters = PBE.Util.makePBEParameters(bCPBEKey, algorithmParameterSpec, this.cipher.getAlgorithmName());
                    this.pbeSpec = (PBEParameterSpec) algorithmParameterSpec;
                    parametersWithIV = makePBEParameters;
                } else {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
                if (bCPBEKey.getIvSize() != 0) {
                    this.ivParam = (ParametersWithIV) parametersWithIV;
                }
            } else if (algorithmParameterSpec == null) {
                if (this.digest <= 0) {
                    parametersWithIV = new KeyParameter(key.getEncoded());
                } else {
                    throw new InvalidKeyException("Algorithm requires a PBE key");
                }
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                ParametersWithIV parametersWithIV2 = new ParametersWithIV(new KeyParameter(key.getEncoded()), ((IvParameterSpec) algorithmParameterSpec).getIV());
                this.ivParam = parametersWithIV2;
                parametersWithIV = parametersWithIV2;
            } else {
                throw new InvalidAlgorithmParameterException("unknown parameter type.");
            }
            if (this.ivLength != 0 && !(parametersWithIV instanceof ParametersWithIV)) {
                if (secureRandom == null) {
                    secureRandom = new SecureRandom();
                }
                if (i == 1 || i == 3) {
                    byte[] bArr = new byte[this.ivLength];
                    secureRandom.nextBytes(bArr);
                    ParametersWithIV parametersWithIV3 = new ParametersWithIV(parametersWithIV, bArr);
                    this.ivParam = parametersWithIV3;
                    parametersWithIV = parametersWithIV3;
                } else {
                    throw new InvalidAlgorithmParameterException("no IV set when one expected");
                }
            }
            switch (i) {
                case 1:
                case 3:
                    this.cipher.init(true, parametersWithIV);
                    return;
                case 2:
                case 4:
                    this.cipher.init(false, parametersWithIV);
                    return;
                default:
                    try {
                        throw new InvalidParameterException("unknown opmode " + i + " passed");
                    } catch (Exception e) {
                        throw new InvalidKeyException(e.getMessage());
                    }
            }
        } else {
            throw new InvalidKeyException("Key for algorithm " + key.getAlgorithm() + " not suitable for symmetric enryption.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public void engineInit(int i, Key key, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) {
        AlgorithmParameterSpec algorithmParameterSpec = null;
        if (algorithmParameters != null) {
            int i2 = 0;
            while (true) {
                Class[] clsArr = this.availableSpecs;
                if (i2 == clsArr.length) {
                    break;
                }
                try {
                    algorithmParameterSpec = algorithmParameters.getParameterSpec(clsArr[i2]);
                    break;
                } catch (Exception unused) {
                    i2++;
                }
            }
            if (algorithmParameterSpec == null) {
                throw new InvalidAlgorithmParameterException("can't handle parameter " + algorithmParameters.toString());
            }
        }
        engineInit(i, key, algorithmParameterSpec, secureRandom);
        this.engineParams = algorithmParameters;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public void engineInit(int i, Key key, SecureRandom secureRandom) {
        try {
            engineInit(i, key, (AlgorithmParameterSpec) null, secureRandom);
        } catch (InvalidAlgorithmParameterException e) {
            throw new InvalidKeyException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public byte[] engineUpdate(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        this.cipher.processBytes(bArr, i, i2, bArr2, 0);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public int engineUpdate(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i3 + i2 <= bArr2.length) {
            try {
                this.cipher.processBytes(bArr, i, i2, bArr2, i3);
                return i2;
            } catch (DataLengthException e) {
                throw new IllegalStateException(e.getMessage());
            }
        } else {
            throw new ShortBufferException("output buffer too short for input.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public byte[] engineDoFinal(byte[] bArr, int i, int i2) {
        if (i2 != 0) {
            byte[] engineUpdate = engineUpdate(bArr, i, i2);
            this.cipher.reset();
            return engineUpdate;
        }
        this.cipher.reset();
        return new byte[0];
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi, org.spongycastle.jcajce.provider.symmetric.util.BaseWrapCipher
    public int engineDoFinal(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i3 + i2 <= bArr2.length) {
            if (i2 != 0) {
                this.cipher.processBytes(bArr, i, i2, bArr2, i3);
            }
            this.cipher.reset();
            return i2;
        }
        throw new ShortBufferException("output buffer too short for input.");
    }
}
