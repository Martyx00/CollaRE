package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.gnu.GNUObjectIdentifiers;
import org.spongycastle.asn1.kisa.KISAObjectIdentifiers;
import org.spongycastle.asn1.misc.MiscObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKDFParameters;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

public abstract class BaseAgreementSpi extends KeyAgreementSpi {
    private static final Map<String, ASN1ObjectIdentifier> defaultOids = new HashMap();
    private static final Hashtable des = new Hashtable();
    private static final Map<String, Integer> keySizes = new HashMap();
    private static final Map<String, String> nameTable = new HashMap();
    private static final Hashtable oids = new Hashtable();
    private final String kaAlgorithm;
    private final DerivationFunction kdf;
    protected byte[] ukmParameters;

    /* access modifiers changed from: protected */
    public abstract byte[] calcSecret();

    static {
        Integer valueOf = Integers.valueOf(64);
        Integer valueOf2 = Integers.valueOf(128);
        Integer valueOf3 = Integers.valueOf(192);
        Integer valueOf4 = Integers.valueOf(256);
        keySizes.put("DES", valueOf);
        keySizes.put("DESEDE", valueOf3);
        keySizes.put("BLOWFISH", valueOf2);
        keySizes.put("AES", valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_ECB.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_ECB.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_ECB.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CFB.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CFB.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CFB.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_OFB.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_OFB.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_OFB.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_wrap.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_wrap.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CCM.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CCM.getId(), valueOf4);
        keySizes.put(NISTObjectIdentifiers.id_aes128_GCM.getId(), valueOf2);
        keySizes.put(NISTObjectIdentifiers.id_aes192_GCM.getId(), valueOf3);
        keySizes.put(NISTObjectIdentifiers.id_aes256_GCM.getId(), valueOf4);
        keySizes.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), valueOf2);
        keySizes.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), valueOf3);
        keySizes.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), valueOf4);
        keySizes.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), valueOf2);
        keySizes.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), valueOf3);
        keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), valueOf3);
        keySizes.put(OIWObjectIdentifiers.desCBC.getId(), valueOf);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), Integers.valueOf(CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), valueOf4);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), Integers.valueOf(384));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), Integers.valueOf(512));
        defaultOids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        defaultOids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        defaultOids.put("CAMELLIA", NTTObjectIdentifiers.id_camellia256_cbc);
        defaultOids.put("SEED", KISAObjectIdentifiers.id_seedCBC);
        defaultOids.put("DES", OIWObjectIdentifiers.desCBC);
        nameTable.put(MiscObjectIdentifiers.cast5CBC.getId(), "CAST5");
        nameTable.put(MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC.getId(), "IDEA");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_ECB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CBC.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CFB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_OFB.getId(), "Blowfish");
        nameTable.put(OIWObjectIdentifiers.desECB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desOFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desEDE.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap.getId(), "RC2");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), "HmacSHA1");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA224.getId(), "HmacSHA224");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), "HmacSHA256");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), "HmacSHA384");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), "HmacSHA512");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), "Camellia");
        nameTable.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedCBC.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedMAC.getId(), "SEED");
        nameTable.put(CryptoProObjectIdentifiers.gostR28147_gcfb.getId(), "GOST28147");
        nameTable.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        oids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        oids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        oids.put("DES", OIWObjectIdentifiers.desCBC);
        des.put("DES", "DES");
        des.put("DESEDE", "DES");
        des.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DES");
    }

    public BaseAgreementSpi(String str, DerivationFunction derivationFunction) {
        this.kaAlgorithm = str;
        this.kdf = derivationFunction;
    }

    protected static String getAlgorithm(String str) {
        if (str.indexOf(91) > 0) {
            return str.substring(0, str.indexOf(91));
        }
        if (str.startsWith(NISTObjectIdentifiers.aes.getId())) {
            return "AES";
        }
        if (str.startsWith(GNUObjectIdentifiers.Serpent.getId())) {
            return "Serpent";
        }
        String str2 = nameTable.get(Strings.toUpperCase(str));
        return str2 != null ? str2 : str;
    }

    protected static int getKeySize(String str) {
        if (str.indexOf(91) > 0) {
            return Integer.parseInt(str.substring(str.indexOf(91) + 1, str.indexOf(93)));
        }
        String upperCase = Strings.toUpperCase(str);
        if (!keySizes.containsKey(upperCase)) {
            return -1;
        }
        return keySizes.get(upperCase).intValue();
    }

    protected static byte[] trimZeroes(byte[] bArr) {
        if (bArr[0] != 0) {
            return bArr;
        }
        int i = 0;
        while (i < bArr.length && bArr[i] == 0) {
            i++;
        }
        byte[] bArr2 = new byte[(bArr.length - i)];
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public byte[] engineGenerateSecret() {
        if (this.kdf == null) {
            return calcSecret();
        }
        throw new UnsupportedOperationException("KDF can only be used when algorithm is known");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public int engineGenerateSecret(byte[] bArr, int i) {
        byte[] engineGenerateSecret = engineGenerateSecret();
        if (bArr.length - i >= engineGenerateSecret.length) {
            System.arraycopy(engineGenerateSecret, 0, bArr, i, engineGenerateSecret.length);
            return engineGenerateSecret.length;
        }
        throw new ShortBufferException(this.kaAlgorithm + " key agreement: need " + engineGenerateSecret.length + " bytes");
    }

    /* access modifiers changed from: protected */
    @Override // javax.crypto.KeyAgreementSpi
    public SecretKey engineGenerateSecret(String str) {
        byte[] calcSecret = calcSecret();
        String upperCase = Strings.toUpperCase(str);
        String id = oids.containsKey(upperCase) ? ((ASN1ObjectIdentifier) oids.get(upperCase)).getId() : str;
        int keySize = getKeySize(id);
        DerivationFunction derivationFunction = this.kdf;
        if (derivationFunction != null) {
            if (keySize >= 0) {
                byte[] bArr = new byte[(keySize / 8)];
                if (derivationFunction instanceof DHKEKGenerator) {
                    try {
                        this.kdf.init(new DHKDFParameters(new ASN1ObjectIdentifier(id), keySize, calcSecret, this.ukmParameters));
                    } catch (IllegalArgumentException unused) {
                        throw new NoSuchAlgorithmException("no OID for algorithm: " + id);
                    }
                } else {
                    this.kdf.init(new KDFParameters(calcSecret, this.ukmParameters));
                }
                this.kdf.generateBytes(bArr, 0, bArr.length);
                calcSecret = bArr;
            } else {
                throw new NoSuchAlgorithmException("unknown algorithm encountered: " + id);
            }
        } else if (keySize > 0) {
            byte[] bArr2 = new byte[(keySize / 8)];
            System.arraycopy(calcSecret, 0, bArr2, 0, bArr2.length);
            calcSecret = bArr2;
        }
        String algorithm = getAlgorithm(str);
        if (des.containsKey(algorithm)) {
            DESParameters.setOddParity(calcSecret);
        }
        return new SecretKeySpec(calcSecret, algorithm);
    }
}
