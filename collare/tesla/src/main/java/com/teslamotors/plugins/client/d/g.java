package com.teslamotors.plugins.client.d;

import android.content.Context;
import android.util.Log;
import com.facebook.react.modules.network.m;
import com.teslamotors.plugins.client.a.e;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

/* compiled from: WebServicesHelper */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5554a = "g";

    /* renamed from: b  reason: collision with root package name */
    private static g f5555b;

    /* renamed from: c  reason: collision with root package name */
    private OkHttpClient f5556c;

    /* renamed from: d  reason: collision with root package name */
    private ArrayList<Call> f5557d;

    /* access modifiers changed from: private */
    /* compiled from: WebServicesHelper */
    public static class a implements Interceptor {
        private a() {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Response proceed = chain.proceed(chain.request());
            return proceed.code() == 408 ? proceed.newBuilder().code(504).build() : proceed;
        }
    }

    public static OkHttpClient a(Context context) {
        OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().connectTimeout(0, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).cookieJar(new m()).addNetworkInterceptor(new a());
        addNetworkInterceptor.retryOnConnectionFailure(false);
        addNetworkInterceptor.certificatePinner(new CertificatePinner.Builder().add("owner-api.teslamotors.com", "sha256/rDkm3ia02Yy7L+so+PO05jbxoYw5P8cm+6PmjOPOu4Q=").add("owner-api.teslamotors.com", "sha256/8hfllLQej0BvAAuR3z0NFTU7UzEu0oypl8orsPIDgoQ=").add("streaming.vn.teslamotors.com", "sha256/5vhETfxv+CttOmWIxw50Jo3t78BK6ZkR0WSFqROevuQ=").add("streaming.vn.teslamotors.com", "sha256/fnAiEr7uwimU62NoiaHkrBFu3If6SAarVTP0PPT+rEE=").add("streaming.vn.teslamotors.com", "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=").build());
        return addNetworkInterceptor.build();
    }

    public static synchronized g b(Context context) {
        g gVar;
        synchronized (g.class) {
            if (f5555b == null) {
                f5555b = new g(context);
                f5555b.f5557d = new ArrayList<>();
            }
            gVar = f5555b;
        }
        return gVar;
    }

    private g(Context context) {
        this.f5556c = a(context);
    }

    public Response a(String str, String str2, HashMap<String, String> hashMap, JSONObject jSONObject, e eVar) {
        try {
            return a(str, str2, hashMap, jSONObject, true, eVar);
        } catch (Exception e) {
            if (eVar == null) {
                return null;
            }
            eVar.a(e);
            return null;
        }
    }

    public Response a(String str, String str2, HashMap<String, String> hashMap, JSONObject jSONObject, boolean z, final e eVar) {
        byte[] bArr;
        if (str.equals("POST")) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            bArr = z ? a(jSONObject.toString()) : jSONObject.toString().getBytes();
        } else {
            if (str.equals("GET") && jSONObject != null) {
                String str3 = null;
                while (jSONObject.keys().hasNext()) {
                    if (str3 == null) {
                        str3 = "?" + jSONObject.get(jSONObject.keys().next());
                        if (!jSONObject.keys().hasNext()) {
                            str3 = str3 + "&";
                        }
                    }
                }
                if (str3 != null) {
                    str2 = str2 + str3;
                    bArr = null;
                }
            }
            bArr = null;
        }
        Request.Builder header = new Request.Builder().url(str2).header("Content-Encoding", "gzip");
        if (str.equals("GET")) {
            header.get();
        } else if (str.equals("POST") && bArr != null) {
            header.post(RequestBody.create(MediaType.parse(io.a.a.a.a.b.a.ACCEPT_JSON_VALUE), bArr));
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            header.addHeader(entry.getKey(), entry.getValue());
        }
        Call newCall = this.f5556c.newCall(header.build());
        this.f5557d.add(newCall);
        if (eVar == null) {
            return newCall.execute();
        }
        newCall.enqueue(new Callback() {
            /* class com.teslamotors.plugins.client.d.g.AnonymousClass1 */

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                g.this.f5557d.remove(call);
                Log.e(g.f5554a, "Request failure", iOException);
                eVar.a(iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                g.this.f5557d.remove(call);
                eVar.a(response);
            }
        });
        return null;
    }

    private byte[] a(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bytes);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
