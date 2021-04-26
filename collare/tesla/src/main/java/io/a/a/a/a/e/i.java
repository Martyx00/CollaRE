package io.a.a.a.a.e;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;

/* access modifiers changed from: package-private */
/* compiled from: SystemKeyStore */
public class i {

    /* renamed from: a  reason: collision with root package name */
    final KeyStore f6014a;

    /* renamed from: b  reason: collision with root package name */
    private final HashMap<Principal, X509Certificate> f6015b;

    public i(InputStream inputStream, String str) {
        KeyStore a2 = a(inputStream, str);
        this.f6015b = a(a2);
        this.f6014a = a2;
    }

    public boolean a(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.f6015b.get(x509Certificate.getSubjectX500Principal());
        return x509Certificate2 != null && x509Certificate2.getPublicKey().equals(x509Certificate.getPublicKey());
    }

    public X509Certificate b(X509Certificate x509Certificate) {
        X509Certificate x509Certificate2 = this.f6015b.get(x509Certificate.getIssuerX500Principal());
        if (x509Certificate2 == null || x509Certificate2.getSubjectX500Principal().equals(x509Certificate.getSubjectX500Principal())) {
            return null;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return x509Certificate2;
        } catch (GeneralSecurityException unused) {
            return null;
        }
    }

    private HashMap<Principal, X509Certificate> a(KeyStore keyStore) {
        try {
            HashMap<Principal, X509Certificate> hashMap = new HashMap<>();
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(aliases.nextElement());
                if (x509Certificate != null) {
                    hashMap.put(x509Certificate.getSubjectX500Principal(), x509Certificate);
                }
            }
            return hashMap;
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        }
    }

    private KeyStore a(InputStream inputStream, String str) {
        try {
            KeyStore instance = KeyStore.getInstance("BKS");
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try {
                instance.load(bufferedInputStream, str.toCharArray());
                return instance;
            } finally {
                bufferedInputStream.close();
            }
        } catch (KeyStoreException e) {
            throw new AssertionError(e);
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        } catch (CertificateException e3) {
            throw new AssertionError(e3);
        } catch (IOException e4) {
            throw new AssertionError(e4);
        }
    }
}
