package io.a.a.a.a.e;

import io.a.a.a.c;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* access modifiers changed from: package-private */
/* compiled from: PinningTrustManager */
public class h implements X509TrustManager {

    /* renamed from: a  reason: collision with root package name */
    private static final X509Certificate[] f6010a = new X509Certificate[0];

    /* renamed from: b  reason: collision with root package name */
    private final TrustManager[] f6011b;

    /* renamed from: c  reason: collision with root package name */
    private final i f6012c;

    /* renamed from: d  reason: collision with root package name */
    private final long f6013d;
    private final List<byte[]> e = new LinkedList();
    private final Set<X509Certificate> f = Collections.synchronizedSet(new HashSet());

    public h(i iVar, g gVar) {
        this.f6011b = a(iVar);
        this.f6012c = iVar;
        this.f6013d = gVar.getPinCreationTimeInMillis();
        for (String str : gVar.getPins()) {
            this.e.add(a(str));
        }
    }

    private TrustManager[] a(i iVar) {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init(iVar.f6014a);
            return instance.getTrustManagers();
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError(e2);
        } catch (KeyStoreException e3) {
            throw new AssertionError(e3);
        }
    }

    private boolean a(X509Certificate x509Certificate) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA1").digest(x509Certificate.getPublicKey().getEncoded());
            for (byte[] bArr : this.e) {
                if (Arrays.equals(bArr, digest)) {
                    return true;
                }
            }
            return false;
        } catch (NoSuchAlgorithmException e2) {
            throw new CertificateException(e2);
        }
    }

    private void a(X509Certificate[] x509CertificateArr, String str) {
        for (TrustManager trustManager : this.f6011b) {
            ((X509TrustManager) trustManager).checkServerTrusted(x509CertificateArr, str);
        }
    }

    private void a(X509Certificate[] x509CertificateArr) {
        if (this.f6013d == -1 || System.currentTimeMillis() - this.f6013d <= 15552000000L) {
            for (X509Certificate x509Certificate : a.a(x509CertificateArr, this.f6012c)) {
                if (a(x509Certificate)) {
                    return;
                }
            }
            throw new CertificateException("No valid pins found in chain!");
        }
        c.g().d("Fabric", "Certificate pins are stale, (" + (System.currentTimeMillis() - this.f6013d) + " millis vs " + 15552000000L + " millis) falling back to system trust.");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        if (!this.f.contains(x509CertificateArr[0])) {
            a(x509CertificateArr, str);
            a(x509CertificateArr);
            this.f.add(x509CertificateArr[0]);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return f6010a;
    }

    private byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
