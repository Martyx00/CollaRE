package org.spongycastle.jcajce.provider.symmetric.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.MacSpi;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.RC2Parameters;
import org.spongycastle.crypto.params.SkeinParameters;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.provider.symmetric.util.PBE;
import org.spongycastle.jcajce.spec.AEADParameterSpec;
import org.spongycastle.jcajce.spec.SkeinParameterSpec;

public class BaseMac extends MacSpi implements PBE {
    private static final Class gcmSpecClass = lookup("javax.crypto.spec.GCMParameterSpec");
    private int keySize = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
    private Mac macEngine;
    private int pbeHash = 1;
    private int scheme = 2;

    protected BaseMac(Mac mac) {
        this.macEngine = mac;
    }

    protected BaseMac(Mac mac, int i, int i2, int i3) {
        this.macEngine = mac;
        this.scheme = i;
        this.pbeHash = i2;
        this.keySize = i3;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.MacSpi
    public void engineInit(Key key, AlgorithmParameterSpec algorithmParameterSpec) {
        CipherParameters cipherParameters;
        KeyParameter keyParameter;
        if (key != null) {
            if (key instanceof PKCS12Key) {
                try {
                    SecretKey secretKey = (SecretKey) key;
                    try {
                        PBEParameterSpec pBEParameterSpec = (PBEParameterSpec) algorithmParameterSpec;
                        if ((secretKey instanceof PBEKey) && pBEParameterSpec == null) {
                            PBEKey pBEKey = (PBEKey) secretKey;
                            pBEParameterSpec = new PBEParameterSpec(pBEKey.getSalt(), pBEKey.getIterationCount());
                        }
                        int i = 1;
                        int i2 = 256;
                        if (this.macEngine.getAlgorithmName().startsWith("GOST")) {
                            i = 6;
                        } else if (this.macEngine.getAlgorithmName().startsWith("SHA256")) {
                            i = 4;
                        } else {
                            i2 = CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
                        }
                        cipherParameters = PBE.Util.makePBEMacParameters(secretKey, 2, i, i2, pBEParameterSpec);
                    } catch (Exception unused) {
                        throw new InvalidAlgorithmParameterException("PKCS12 requires a PBEParameterSpec");
                    }
                } catch (Exception unused2) {
                    throw new InvalidKeyException("PKCS12 requires a SecretKey/PBEKey");
                }
            } else if (key instanceof BCPBEKey) {
                BCPBEKey bCPBEKey = (BCPBEKey) key;
                if (bCPBEKey.getParam() != null) {
                    cipherParameters = bCPBEKey.getParam();
                } else if (algorithmParameterSpec instanceof PBEParameterSpec) {
                    cipherParameters = PBE.Util.makePBEMacParameters(bCPBEKey, algorithmParameterSpec);
                } else {
                    throw new InvalidAlgorithmParameterException("PBE requires PBE parameters to be set.");
                }
            } else if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                cipherParameters = new KeyParameter(key.getEncoded());
            } else {
                throw new InvalidAlgorithmParameterException("inappropriate parameter type: " + algorithmParameterSpec.getClass().getName());
            }
            if (cipherParameters instanceof ParametersWithIV) {
                keyParameter = (KeyParameter) ((ParametersWithIV) cipherParameters).getParameters();
            } else {
                keyParameter = (KeyParameter) cipherParameters;
            }
            if (algorithmParameterSpec instanceof AEADParameterSpec) {
                AEADParameterSpec aEADParameterSpec = (AEADParameterSpec) algorithmParameterSpec;
                cipherParameters = new AEADParameters(keyParameter, aEADParameterSpec.getMacSizeInBits(), aEADParameterSpec.getNonce(), aEADParameterSpec.getAssociatedData());
            } else if (algorithmParameterSpec instanceof IvParameterSpec) {
                cipherParameters = new ParametersWithIV(keyParameter, ((IvParameterSpec) algorithmParameterSpec).getIV());
            } else if (algorithmParameterSpec instanceof RC2ParameterSpec) {
                RC2ParameterSpec rC2ParameterSpec = (RC2ParameterSpec) algorithmParameterSpec;
                cipherParameters = new ParametersWithIV(new RC2Parameters(keyParameter.getKey(), rC2ParameterSpec.getEffectiveKeyBits()), rC2ParameterSpec.getIV());
            } else if (algorithmParameterSpec instanceof SkeinParameterSpec) {
                cipherParameters = new SkeinParameters.Builder(copyMap(((SkeinParameterSpec) algorithmParameterSpec).getParameters())).setKey(keyParameter.getKey()).build();
            } else if (algorithmParameterSpec == null) {
                cipherParameters = new KeyParameter(key.getEncoded());
            } else {
                Class cls = gcmSpecClass;
                if (cls != null && cls.isAssignableFrom(algorithmParameterSpec.getClass())) {
                    try {
                        cipherParameters = new AEADParameters(keyParameter, ((Integer) gcmSpecClass.getDeclaredMethod("getTLen", new Class[0]).invoke(algorithmParameterSpec, new Object[0])).intValue(), (byte[]) gcmSpecClass.getDeclaredMethod("getIV", new Class[0]).invoke(algorithmParameterSpec, new Object[0]));
                    } catch (Exception unused3) {
                        throw new InvalidAlgorithmParameterException("Cannot process GCMParameterSpec.");
                    }
                } else if (!(algorithmParameterSpec instanceof PBEParameterSpec)) {
                    throw new InvalidAlgorithmParameterException("unknown parameter type: " + algorithmParameterSpec.getClass().getName());
                }
            }
            try {
                this.macEngine.init(cipherParameters);
            } catch (Exception e) {
                throw new InvalidAlgorithmParameterException("cannot initialize MAC: " + e.getMessage());
            }
        } else {
            throw new InvalidKeyException("key is null");
        }
    }

    /* access modifiers changed from: protected */
    public int engineGetMacLength() {
        return this.macEngine.getMacSize();
    }

    /* access modifiers changed from: protected */
    public void engineReset() {
        this.macEngine.reset();
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.MacSpi
    public void engineUpdate(byte b2) {
        this.macEngine.update(b2);
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte[] bArr, int i, int i2) {
        this.macEngine.update(bArr, i, i2);
    }

    /* access modifiers changed from: protected */
    public byte[] engineDoFinal() {
        byte[] bArr = new byte[engineGetMacLength()];
        this.macEngine.doFinal(bArr, 0);
        return bArr;
    }

    private static Hashtable copyMap(Map map) {
        Hashtable hashtable = new Hashtable();
        for (Object obj : map.keySet()) {
            hashtable.put(obj, map.get(obj));
        }
        return hashtable;
    }

    private static Class lookup(String str) {
        try {
            return BaseBlockCipher.class.getClassLoader().loadClass(str);
        } catch (Exception unused) {
            return null;
        }
    }
}
