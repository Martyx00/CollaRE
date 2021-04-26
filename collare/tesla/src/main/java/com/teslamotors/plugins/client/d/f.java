package com.teslamotors.plugins.client.d;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: StringEncryptionHelper */
public class f {

    /* renamed from: c  reason: collision with root package name */
    private static f f5547c;

    /* renamed from: a  reason: collision with root package name */
    private final Context f5548a;

    /* renamed from: b  reason: collision with root package name */
    private final b f5549b;

    /* access modifiers changed from: package-private */
    /* compiled from: StringEncryptionHelper */
    public interface b {
        String a(String str);

        String b(String str);
    }

    private f(Context context) {
        this.f5548a = context;
        this.f5549b = Build.VERSION.SDK_INT >= 23 ? new a() : new c();
    }

    public String a(String str) {
        return this.f5549b.b(str);
    }

    public String b(String str) {
        return this.f5549b.a(str);
    }

    public static f a(Context context) {
        if (f5547c == null) {
            f5547c = new f(context);
        }
        return f5547c;
    }

    /* compiled from: StringEncryptionHelper */
    private class c implements b {
        @Override // com.teslamotors.plugins.client.d.f.b
        public String a(String str) {
            return str;
        }

        @Override // com.teslamotors.plugins.client.d.f.b
        public String b(String str) {
            return str;
        }

        private c() {
        }
    }

    /* compiled from: StringEncryptionHelper */
    private class a implements b {

        /* renamed from: b  reason: collision with root package name */
        private final Cipher f5551b;

        /* renamed from: c  reason: collision with root package name */
        private SecretKey f5552c;

        private a() {
            this.f5552c = null;
            try {
                this.f5551b = Cipher.getInstance("AES/CBC/PKCS7Padding");
                a();
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new RuntimeException("[Realm] unable to generate Cipher", e);
            }
        }

        @TargetApi(23)
        private SecretKey a() {
            try {
                if (this.f5552c == null) {
                    KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                    instance.load(null);
                    this.f5552c = (SecretKey) instance.getKey("tm_master_key", null);
                    if (this.f5552c == null) {
                        b();
                    }
                }
                return this.f5552c;
            } catch (IOException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e) {
                throw new RuntimeException("[Realm] error retrieving master key", e);
            }
        }

        @TargetApi(23)
        private synchronized SecretKey b() {
            try {
                KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
                instance.init(new KeyGenParameterSpec.Builder("tm_master_key", 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").setUserAuthenticationRequired(false).build());
                this.f5552c = instance.generateKey();
            } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchProviderException e) {
                throw new RuntimeException("[Realm] Unable to create master key", e);
            }
            return this.f5552c;
        }

        @Override // com.teslamotors.plugins.client.d.f.b
        @TargetApi(23)
        public synchronized String a(String str) {
            byte[] doFinal;
            try {
                this.f5551b.init(1, this.f5552c);
                doFinal = this.f5551b.doFinal(str.getBytes());
            } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                throw new RuntimeException("[Realm] Unable to encrypt", e);
            }
            return Base64.encodeToString(this.f5551b.getIV(), 0) + ":" + Base64.encodeToString(doFinal, 0);
        }

        @Override // com.teslamotors.plugins.client.d.f.b
        @TargetApi(23)
        public synchronized String b(String str) {
            String[] split;
            try {
                split = str.split(":");
                this.f5551b.init(2, this.f5552c, new IvParameterSpec(Base64.decode(split[0], 0)));
            } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                throw new RuntimeException("[Realm] Unable to decrypt", e);
            }
            return new String(this.f5551b.doFinal(Base64.decode(split[1], 0)));
        }
    }
}
