package io.a.a.a;

import android.os.SystemClock;
import android.text.TextUtils;
import io.a.a.a.a.b.i;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* access modifiers changed from: package-private */
/* compiled from: FabricKitsFinder */
public class e implements Callable<Map<String, k>> {

    /* renamed from: a  reason: collision with root package name */
    final String f6093a;

    e(String str) {
        this.f6093a = str;
    }

    /* renamed from: a */
    public Map<String, k> call() {
        HashMap hashMap = new HashMap();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        hashMap.putAll(c());
        hashMap.putAll(d());
        l g = c.g();
        g.b("Fabric", "finish scanning in " + (SystemClock.elapsedRealtime() - elapsedRealtime));
        return hashMap;
    }

    private Map<String, k> c() {
        HashMap hashMap = new HashMap();
        try {
            Class.forName("com.google.android.gms.ads.AdView");
            k kVar = new k("com.google.firebase.firebase-ads", "0.0.0", "binary");
            hashMap.put(kVar.a(), kVar);
            c.g().b("Fabric", "Found kit: com.google.firebase.firebase-ads");
        } catch (Exception unused) {
        }
        return hashMap;
    }

    private Map<String, k> d() {
        k a2;
        HashMap hashMap = new HashMap();
        ZipFile b2 = b();
        Enumeration<? extends ZipEntry> entries = b2.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.getName().startsWith("fabric/") && zipEntry.getName().length() > 7 && (a2 = a(zipEntry, b2)) != null) {
                hashMap.put(a2.a(), a2);
                c.g().b("Fabric", String.format("Found kit:[%s] version:[%s]", a2.a(), a2.b()));
            }
        }
        if (b2 != null) {
            try {
                b2.close();
            } catch (IOException unused) {
            }
        }
        return hashMap;
    }

    private k a(ZipEntry zipEntry, ZipFile zipFile) {
        InputStream inputStream;
        Throwable th;
        IOException e;
        try {
            inputStream = zipFile.getInputStream(zipEntry);
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                String property = properties.getProperty("fabric-identifier");
                String property2 = properties.getProperty("fabric-version");
                String property3 = properties.getProperty("fabric-build-type");
                if (TextUtils.isEmpty(property) || TextUtils.isEmpty(property2)) {
                    throw new IllegalStateException("Invalid format of fabric file," + zipEntry.getName());
                }
                k kVar = new k(property, property2, property3);
                i.a((Closeable) inputStream);
                return kVar;
            } catch (IOException e2) {
                e = e2;
                try {
                    c.g().e("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
                    i.a((Closeable) inputStream);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    i.a((Closeable) inputStream);
                    throw th;
                }
            }
        } catch (IOException e3) {
            e = e3;
            inputStream = null;
            c.g().e("Fabric", "Error when parsing fabric properties " + zipEntry.getName(), e);
            i.a((Closeable) inputStream);
            return null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            i.a((Closeable) inputStream);
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public ZipFile b() {
        return new ZipFile(this.f6093a);
    }
}
