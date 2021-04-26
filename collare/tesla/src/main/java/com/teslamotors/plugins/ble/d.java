package com.teslamotors.plugins.ble;

import android.annotation.TargetApi;
import android.content.Context;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import com.teslamotors.plugins.client.f;
import com.teslamotors.plugins.crashlytics.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPublicKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.crypto.engines.AESEngine;
import org.spongycastle.crypto.modes.GCMBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.jce.ECNamedCurveTable;
import org.spongycastle.jce.ECPointUtil;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;
import org.spongycastle.x509.X509V3CertificateGenerator;

@TargetApi(21)
/* compiled from: BLECrypto */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5451a = "d";

    /* renamed from: b  reason: collision with root package name */
    private static String f5452b = "keys.store";

    /* renamed from: c  reason: collision with root package name */
    private KeyStore f5453c;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public d() {
        try {
            this.f5453c = KeyStore.getInstance("AndroidKeyStore");
            this.f5453c.load(null);
        } catch (Exception e) {
            b.a(new Throwable("Could not create key store", e));
        }
    }

    public static FileInputStream a(Context context) {
        try {
            return context.openFileInput(f5452b);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public static FileOutputStream b(Context context) {
        try {
            return context.openFileOutput(f5452b, 0);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    public static void c(Context context) {
        try {
            context.deleteFile(f5452b);
        } catch (Exception unused) {
        }
    }

    public static KeyPair a() {
        ECGenParameterSpec eCGenParameterSpec = new ECGenParameterSpec("prime256v1");
        KeyPairGenerator instance = KeyPairGenerator.getInstance("EC");
        instance.initialize(eCGenParameterSpec, new SecureRandom());
        return instance.generateKeyPair();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.security.KeyStore] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.io.Closeable, java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.security.KeyPair r9, java.lang.String r10, android.content.Context r11) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: com.teslamotors.plugins.ble.d.a(java.security.KeyPair, java.lang.String, android.content.Context):boolean");
    }

    public KeyPair a(String str, Context context) {
        Throwable th;
        FileInputStream fileInputStream;
        Exception e;
        String a2 = a(str);
        System.currentTimeMillis();
        KeyStore instance = KeyStore.getInstance("BKS", BouncyCastleProvider.PROVIDER_NAME);
        char[] charArray = d(context).toCharArray();
        try {
            fileInputStream = a(context);
            try {
                instance.load(fileInputStream, charArray);
                Key key = instance.getKey(a2, charArray);
                if (key != null) {
                    KeyPair keyPair = new KeyPair(instance.getCertificate(a2).getPublicKey(), (PrivateKey) key);
                    a(fileInputStream);
                    return keyPair;
                }
                a(fileInputStream);
                return null;
            } catch (Exception e2) {
                e = e2;
                try {
                    b.a(new Throwable("Failed to get local keypair - deleting", e));
                    c(context);
                    a(fileInputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    a(fileInputStream);
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            b.a(new Throwable("Failed to get local keypair - deleting", e));
            c(context);
            a(fileInputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            a(fileInputStream);
            throw th;
        }
    }

    public PublicKey a(byte[] bArr) {
        try {
            ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("prime256v1");
            KeyFactory instance = KeyFactory.getInstance("ECDSA", new BouncyCastleProvider());
            ECNamedCurveSpec eCNamedCurveSpec = new ECNamedCurveSpec("prime256v1", parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN());
            return (ECPublicKey) instance.generatePublic(new ECPublicKeySpec(ECPointUtil.decodePoint(eCNamedCurveSpec.getCurve(), bArr), eCNamedCurveSpec));
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] a(PublicKey publicKey, PrivateKey privateKey) {
        try {
            KeyAgreement instance = KeyAgreement.getInstance("ECDH");
            instance.init(privateKey);
            instance.doPhase(publicKey, true);
            byte[] encoded = instance.generateSecret("AES").getEncoded();
            if (encoded == null) {
                return null;
            }
            MessageDigest instance2 = MessageDigest.getInstance(McElieceCCA2KeyGenParameterSpec.SHA1);
            instance2.update(encoded);
            return Arrays.copyOfRange(instance2.digest(), 0, 16);
        } catch (Exception e) {
            b.a(new Throwable("Failed to do key exchange / generate shared secret", e));
            return null;
        }
    }

    public static byte[] a(PublicKey publicKey) {
        try {
            return Arrays.copyOf(MessageDigest.getInstance("SHA1").digest(b(publicKey)), 4);
        } catch (NoSuchAlgorithmException e) {
            b.a(new Throwable("Failed to compute SHA1 hash of public key", e));
            return null;
        }
    }

    public static byte[] b(PublicKey publicKey) {
        byte[] encoded = publicKey.getEncoded();
        return Arrays.copyOfRange(encoded, 26, encoded.length);
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, long j) {
        System.currentTimeMillis();
        try {
            AEADParameters aEADParameters = new AEADParameters(new KeyParameter(bArr2), 128, new byte[]{(byte) ((int) ((j >> 24) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) (j & 255))});
            GCMBlockCipher gCMBlockCipher = new GCMBlockCipher(new AESEngine());
            gCMBlockCipher.init(true, aEADParameters);
            byte[] bArr3 = new byte[gCMBlockCipher.getOutputSize(bArr.length)];
            gCMBlockCipher.doFinal(bArr3, gCMBlockCipher.processBytes(bArr, 0, bArr.length, bArr3, 0));
            return bArr3;
        } catch (Exception e) {
            b.a(new Throwable("Failed to encrypt message", e));
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bArr3 = new byte[4];
            new SecureRandom().nextBytes(bArr3);
            bArr2[0] = bArr3[0];
            bArr2[1] = bArr3[1];
            bArr2[2] = bArr3[2];
            bArr2[3] = bArr3[3];
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (Exception e) {
            b.a(new Throwable("encryptWithSharedSecret : Failed to encrypt message", e));
            return null;
        }
    }

    private static X509Certificate a(KeyPair keyPair) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        instance.add(1, 30);
        X509V3CertificateGenerator x509V3CertificateGenerator = new X509V3CertificateGenerator();
        x509V3CertificateGenerator.setSerialNumber(BigInteger.valueOf(1));
        x509V3CertificateGenerator.setSubjectDN(new X509Principal("CN=localhost"));
        x509V3CertificateGenerator.setIssuerDN(new X509Principal("CN=localhost"));
        x509V3CertificateGenerator.setPublicKey(keyPair.getPublic());
        x509V3CertificateGenerator.setNotBefore(new Date());
        x509V3CertificateGenerator.setNotAfter(instance.getTime());
        x509V3CertificateGenerator.setSignatureAlgorithm("ECDSAWITHSHA1");
        try {
            return x509V3CertificateGenerator.generate(keyPair.getPrivate(), BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception e) {
            b.a(new Throwable("Failed to generate certificate for key", e));
            return null;
        }
    }

    private String a(String str) {
        return String.format("PHONE_AUTH_%s_KEY_PAIR", str).toLowerCase(Locale.US);
    }

    private String d(Context context) {
        e(context);
        String B = f.a(context).B();
        if (B != null) {
            return c(B);
        }
        String bigInteger = new BigInteger(130, new SecureRandom()).toString();
        f.a(context).g(b(bigInteger));
        return bigInteger;
    }

    private void e(Context context) {
        if (!this.f5453c.containsAlias("KeyStorePassKey")) {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.add(1, 30);
            KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias("KeyStorePassKey").setSubject(new X500Principal("CN=localhost")).setSerialNumber(BigInteger.ONE).setStartDate(instance.getTime()).setEndDate(instance2.getTime()).setKeySize(2048).build();
            KeyPairGenerator instance3 = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            instance3.initialize(build);
            instance3.generateKeyPair();
        }
    }

    private String b(String str) {
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, (RSAPublicKey) ((KeyStore.PrivateKeyEntry) this.f5453c.getEntry("KeyStorePassKey", null)).getCertificate().getPublicKey());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(byteArrayOutputStream, instance);
            cipherOutputStream.write(str.getBytes("UTF-8"));
            cipherOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception e) {
            b.a(new Throwable("Failed to encrypt text", e));
            return null;
        }
    }

    private String c(String str) {
        try {
            PrivateKey privateKey = ((KeyStore.PrivateKeyEntry) this.f5453c.getEntry("KeyStorePassKey", null)).getPrivateKey();
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, privateKey);
            CipherInputStream cipherInputStream = new CipherInputStream(new ByteArrayInputStream(Base64.decode(str, 2)), instance);
            ArrayList arrayList = new ArrayList();
            while (true) {
                int read = cipherInputStream.read();
                if (read == -1) {
                    break;
                }
                arrayList.add(Byte.valueOf((byte) read));
            }
            byte[] bArr = new byte[arrayList.size()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = ((Byte) arrayList.get(i)).byteValue();
            }
            return new String(bArr, 0, bArr.length, "UTF-8");
        } catch (Exception e) {
            b.a(new Throwable("Failed to decrypt text", e));
            return null;
        }
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }
}
