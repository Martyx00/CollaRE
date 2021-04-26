package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public abstract class BaseCipherSpi extends CipherSpi {
    private Class[] availableSpecs = {IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
    protected AlgorithmParameters engineParams = null;
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private byte[] iv;
    private int ivSize;
    protected Wrapper wrapEngine = null;

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int i) {
        return -1;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    protected BaseCipherSpi() {
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length;
    }

    /* access modifiers changed from: protected */
    public final AlgorithmParameters createParametersInstance(String str) {
        return this.helper.createAlgorithmParameters(str);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineSetMode(String str) {
        throw new NoSuchAlgorithmException("can't support mode " + str);
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public void engineSetPadding(String str) {
        throw new NoSuchPaddingException("Padding " + str + " unknown.");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public byte[] engineWrap(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded != null) {
            try {
                if (this.wrapEngine == null) {
                    return engineDoFinal(encoded, 0, encoded.length);
                }
                return this.wrapEngine.wrap(encoded, 0, encoded.length);
            } catch (BadPaddingException e) {
                throw new IllegalBlockSizeException(e.getMessage());
            }
        } else {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.CipherSpi
    public Key engineUnwrap(byte[] bArr, String str, int i) {
        byte[] bArr2;
        try {
            if (this.wrapEngine == null) {
                bArr2 = engineDoFinal(bArr, 0, bArr.length);
            } else {
                bArr2 = this.wrapEngine.unwrap(bArr, 0, bArr.length);
            }
            if (i == 3) {
                return new SecretKeySpec(bArr2, str);
            }
            if (!str.equals("") || i != 2) {
                try {
                    KeyFactory createKeyFactory = this.helper.createKeyFactory(str);
                    if (i == 1) {
                        return createKeyFactory.generatePublic(new X509EncodedKeySpec(bArr2));
                    }
                    if (i == 2) {
                        return createKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(bArr2));
                    }
                    throw new InvalidKeyException("Unknown key type " + i);
                } catch (NoSuchAlgorithmException e) {
                    throw new InvalidKeyException("Unknown key type " + e.getMessage());
                } catch (InvalidKeySpecException e2) {
                    throw new InvalidKeyException("Unknown key type " + e2.getMessage());
                } catch (NoSuchProviderException e3) {
                    throw new InvalidKeyException("Unknown key type " + e3.getMessage());
                }
            } else {
                try {
                    PrivateKeyInfo instance = PrivateKeyInfo.getInstance(bArr2);
                    PrivateKey privateKey = BouncyCastleProvider.getPrivateKey(instance);
                    if (privateKey != null) {
                        return privateKey;
                    }
                    throw new InvalidKeyException("algorithm " + instance.getPrivateKeyAlgorithm().getAlgorithm() + " not supported");
                } catch (Exception unused) {
                    throw new InvalidKeyException("Invalid key encoding.");
                }
            }
        } catch (InvalidCipherTextException e4) {
            throw new InvalidKeyException(e4.getMessage());
        } catch (BadPaddingException e5) {
            throw new InvalidKeyException("unable to unwrap") {
                /* class org.spongycastle.jcajce.provider.asymmetric.util.BaseCipherSpi.AnonymousClass1 */

                public synchronized Throwable getCause() {
                    return e5;
                }
            };
        } catch (IllegalBlockSizeException e6) {
            throw new InvalidKeyException(e6.getMessage());
        }
    }
}
