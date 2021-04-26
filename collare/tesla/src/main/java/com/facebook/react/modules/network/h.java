package com.facebook.react.modules.network;

import android.content.Context;
import android.os.Build;
import com.facebook.common.e.a;
import java.io.File;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

/* compiled from: OkHttpClientProvider */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static OkHttpClient f2947a;

    /* renamed from: b  reason: collision with root package name */
    private static g f2948b;

    public static OkHttpClient a() {
        if (f2947a == null) {
            f2947a = b();
        }
        return f2947a;
    }

    public static OkHttpClient b() {
        g gVar = f2948b;
        if (gVar != null) {
            return gVar.a();
        }
        return c().build();
    }

    public static OkHttpClient a(Context context) {
        g gVar = f2948b;
        if (gVar != null) {
            return gVar.a();
        }
        return b(context).build();
    }

    public static OkHttpClient.Builder c() {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).cookieJar(new m());
        try {
            Security.insertProviderAt((Provider) Class.forName("org.conscrypt.OpenSSLProvider").newInstance(), 1);
            return cookieJar;
        } catch (Exception unused) {
            return a(cookieJar);
        }
    }

    public static OkHttpClient.Builder b(Context context) {
        return a(context, 10485760);
    }

    public static OkHttpClient.Builder a(Context context, int i) {
        OkHttpClient.Builder c2 = c();
        if (i == 0) {
            return c2;
        }
        return c2.cache(new Cache(new File(context.getCacheDir(), "http-cache"), (long) i));
    }

    public static OkHttpClient.Builder a(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT <= 19) {
            try {
                builder.sslSocketFactory(new p());
                ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                ArrayList arrayList = new ArrayList();
                arrayList.add(build);
                arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                arrayList.add(ConnectionSpec.CLEARTEXT);
                builder.connectionSpecs(arrayList);
            } catch (Exception e) {
                a.c("OkHttpClientProvider", "Error while enabling TLS 1.2", e);
            }
        }
        return builder;
    }
}
