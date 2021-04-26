package com.RNFetchBlob;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.gms.gcm.Task;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.ConnectionSpec;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import org.spongycastle.i18n.TextBundle;

/* compiled from: RNFetchBlobReq */
public class g extends BroadcastReceiver implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public static HashMap<String, Call> f1478a = new HashMap<>();

    /* renamed from: b  reason: collision with root package name */
    public static HashMap<String, Long> f1479b = new HashMap<>();

    /* renamed from: c  reason: collision with root package name */
    static HashMap<String, f> f1480c = new HashMap<>();

    /* renamed from: d  reason: collision with root package name */
    static HashMap<String, f> f1481d = new HashMap<>();
    static ConnectionPool e = new ConnectionPool();
    b f;
    String g;
    String h;
    String i;
    String j;
    String k;
    ReadableArray l;
    ReadableMap m;
    Callback n;
    long o;
    long p;
    a q;
    a r;
    c s;
    b t = b.Auto;
    WritableMap u;
    boolean v = false;
    ArrayList<String> w = new ArrayList<>();
    OkHttpClient x;

    /* access modifiers changed from: package-private */
    /* compiled from: RNFetchBlobReq */
    public enum a {
        Form,
        SingleFile,
        AsIs,
        WithoutBody,
        Others
    }

    /* access modifiers changed from: package-private */
    /* compiled from: RNFetchBlobReq */
    public enum b {
        Auto,
        UTF8,
        BASE64
    }

    /* access modifiers changed from: package-private */
    /* compiled from: RNFetchBlobReq */
    public enum c {
        KeepInMemory,
        FileStorage
    }

    public g(ReadableMap readableMap, String str, String str2, String str3, ReadableMap readableMap2, String str4, ReadableArray readableArray, OkHttpClient okHttpClient, Callback callback) {
        this.h = str2.toUpperCase();
        this.f = new b(readableMap);
        this.g = str;
        this.i = str3;
        this.m = readableMap2;
        this.n = callback;
        this.j = str4;
        this.l = readableArray;
        this.x = okHttpClient;
        if (this.f.f1458a.booleanValue() || this.f.f1459b != null) {
            this.s = c.FileStorage;
        } else {
            this.s = c.KeepInMemory;
        }
        if (str4 != null) {
            this.r = a.SingleFile;
        } else if (readableArray != null) {
            this.r = a.Form;
        } else {
            this.r = a.WithoutBody;
        }
    }

    public static void a(String str) {
        if (f1478a.containsKey(str)) {
            f1478a.get(str).cancel();
            f1478a.remove(str);
        }
        if (f1479b.containsKey(str)) {
            ((DownloadManager) RNFetchBlob.RCTContext.getApplicationContext().getSystemService("download")).remove(f1479b.get(str).longValue());
        }
    }

    public void run() {
        String str;
        OkHttpClient.Builder builder;
        if (this.f.f1461d == null || !this.f.f1461d.hasKey("useDownloadManager") || !this.f.f1461d.getBoolean("useDownloadManager")) {
            String str2 = this.g;
            if (this.f.f1460c.isEmpty()) {
                str = "";
            } else {
                str = "." + this.f.f1460c;
            }
            if (this.f.f != null) {
                str2 = h.a(this.f.f);
                if (str2 == null) {
                    str2 = this.g;
                }
                File file = new File(d.a(str2) + str);
                if (file.exists()) {
                    this.n.invoke(null, "path", file.getAbsolutePath());
                    return;
                }
            }
            if (this.f.f1459b != null) {
                this.k = this.f.f1459b;
            } else if (this.f.f1458a.booleanValue()) {
                this.k = d.a(str2) + str;
            }
            try {
                if (this.f.e.booleanValue()) {
                    builder = h.a(this.x);
                } else {
                    builder = this.x.newBuilder();
                }
                Request.Builder builder2 = new Request.Builder();
                try {
                    builder2.url(new URL(this.i));
                } catch (MalformedURLException e2) {
                    e2.printStackTrace();
                }
                HashMap<String, String> hashMap = new HashMap<>();
                if (this.m != null) {
                    ReadableMapKeySetIterator keySetIterator = this.m.keySetIterator();
                    while (keySetIterator.hasNextKey()) {
                        String nextKey = keySetIterator.nextKey();
                        String string = this.m.getString(nextKey);
                        if (!nextKey.equalsIgnoreCase("RNFB-Response")) {
                            builder2.header(nextKey.toLowerCase(), string);
                            hashMap.put(nextKey.toLowerCase(), string);
                        } else if (string.equalsIgnoreCase("base64")) {
                            this.t = b.BASE64;
                        } else if (string.equalsIgnoreCase("utf8")) {
                            this.t = b.UTF8;
                        }
                    }
                }
                if (this.h.equalsIgnoreCase("post") || this.h.equalsIgnoreCase("put") || this.h.equalsIgnoreCase("patch")) {
                    String lowerCase = a(hashMap, "Content-Type").toLowerCase();
                    if (this.l != null) {
                        this.r = a.Form;
                    } else if (lowerCase.isEmpty()) {
                        if (!lowerCase.equalsIgnoreCase("")) {
                            builder2.header("Content-Type", "application/octet-stream");
                        }
                        this.r = a.SingleFile;
                    }
                    if (this.j != null) {
                        if (this.j.startsWith("RNFetchBlob-file://") || this.j.startsWith("RNFetchBlob-content://")) {
                            this.r = a.SingleFile;
                        } else if (lowerCase.toLowerCase().contains(";base64") || lowerCase.toLowerCase().startsWith("application/octet")) {
                            String replace = lowerCase.replace(";base64", "").replace(";BASE64", "");
                            if (hashMap.containsKey("content-type")) {
                                hashMap.put("content-type", replace);
                            }
                            if (hashMap.containsKey("Content-Type")) {
                                hashMap.put("Content-Type", replace);
                            }
                            this.r = a.SingleFile;
                        } else {
                            this.r = a.AsIs;
                        }
                    }
                } else {
                    this.r = a.WithoutBody;
                }
                boolean equalsIgnoreCase = a(hashMap, "Transfer-Encoding").equalsIgnoreCase("chunked");
                switch (this.r) {
                    case SingleFile:
                        this.q = new a(this.g).a(equalsIgnoreCase).a(this.r).a(this.j).a(MediaType.parse(a(hashMap, "content-type")));
                        builder2.method(this.h, this.q);
                        break;
                    case AsIs:
                        this.q = new a(this.g).a(equalsIgnoreCase).a(this.r).a(this.j).a(MediaType.parse(a(hashMap, "content-type")));
                        builder2.method(this.h, this.q);
                        break;
                    case Form:
                        a a2 = new a(this.g).a(equalsIgnoreCase).a(this.r).a(this.l);
                        this.q = a2.a(MediaType.parse("multipart/form-data; boundary=" + ("RNFetchBlob-" + this.g)));
                        builder2.method(this.h, this.q);
                        break;
                    case WithoutBody:
                        if (!this.h.equalsIgnoreCase("post") && !this.h.equalsIgnoreCase("put") && !this.h.equalsIgnoreCase("patch")) {
                            builder2.method(this.h, null);
                            break;
                        } else {
                            builder2.method(this.h, RequestBody.create((MediaType) null, new byte[0]));
                            break;
                        }
                        break;
                }
                final Request build = builder2.build();
                builder.addNetworkInterceptor(new Interceptor() {
                    /* class com.RNFetchBlob.g.AnonymousClass1 */

                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) {
                        g.this.w.add(chain.request().url().toString());
                        return chain.proceed(chain.request());
                    }
                });
                builder.addInterceptor(new Interceptor() {
                    /* class com.RNFetchBlob.g.AnonymousClass2 */

                    @Override // okhttp3.Interceptor
                    public Response intercept(Interceptor.Chain chain) {
                        ResponseBody responseBody;
                        try {
                            Response proceed = chain.proceed(build);
                            switch (AnonymousClass4.f1487b[g.this.s.ordinal()]) {
                                case 1:
                                    responseBody = new com.RNFetchBlob.a.a(RNFetchBlob.RCTContext, g.this.g, proceed.body(), g.this.f.k.booleanValue());
                                    break;
                                case 2:
                                    responseBody = new com.RNFetchBlob.a.b(RNFetchBlob.RCTContext, g.this.g, proceed.body(), g.this.k, g.this.f.i.booleanValue());
                                    break;
                                default:
                                    responseBody = new com.RNFetchBlob.a.a(RNFetchBlob.RCTContext, g.this.g, proceed.body(), g.this.f.k.booleanValue());
                                    break;
                            }
                            return proceed.newBuilder().body(responseBody).build();
                        } catch (SocketException unused) {
                            g.this.v = true;
                            return chain.proceed(chain.request());
                        } catch (SocketTimeoutException e) {
                            g.this.v = true;
                            h.b("RNFetchBlob error when sending request : " + e.getLocalizedMessage());
                            return chain.proceed(chain.request());
                        } catch (Exception unused2) {
                            return chain.proceed(chain.request());
                        }
                    }
                });
                if (this.f.j >= 0) {
                    builder.connectTimeout(this.f.j, TimeUnit.MILLISECONDS);
                    builder.readTimeout(this.f.j, TimeUnit.MILLISECONDS);
                }
                builder.connectionPool(e);
                builder.retryOnConnectionFailure(false);
                builder.followRedirects(this.f.l.booleanValue());
                builder.followSslRedirects(this.f.l.booleanValue());
                builder.retryOnConnectionFailure(true);
                Call newCall = a(builder).build().newCall(build);
                f1478a.put(this.g, newCall);
                newCall.enqueue(new okhttp3.Callback() {
                    /* class com.RNFetchBlob.g.AnonymousClass3 */

                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        g.a(g.this.g);
                        if (g.this.u == null) {
                            g.this.u = Arguments.createMap();
                        }
                        if (iOException.getClass().equals(SocketTimeoutException.class)) {
                            g.this.u.putBoolean("timeout", true);
                            g.this.n.invoke("request timed out.", null, null);
                        } else {
                            g.this.n.invoke(iOException.getLocalizedMessage(), null, null);
                        }
                        g.this.a();
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        String str;
                        String str2;
                        String str3;
                        ReadableMap readableMap = g.this.f.f1461d;
                        if (readableMap != null) {
                            if (readableMap.hasKey("title")) {
                                str = g.this.f.f1461d.getString("title");
                            } else {
                                str = "";
                            }
                            if (readableMap.hasKey("description")) {
                                str2 = readableMap.getString("description");
                            } else {
                                str2 = "";
                            }
                            if (readableMap.hasKey("mime")) {
                                str3 = readableMap.getString("mime");
                            } else {
                                str3 = "text/plain";
                            }
                            boolean z = readableMap.hasKey("mediaScannable") ? readableMap.getBoolean("mediaScannable") : false;
                            boolean z2 = readableMap.hasKey("notification") ? readableMap.getBoolean("notification") : false;
                            ReactApplicationContext reactApplicationContext = RNFetchBlob.RCTContext;
                            ReactApplicationContext reactApplicationContext2 = RNFetchBlob.RCTContext;
                            ((DownloadManager) reactApplicationContext.getSystemService("download")).addCompletedDownload(str, str2, z, str3, g.this.k, g.this.o, z2);
                        }
                        g.this.a((g) response);
                    }
                });
            } catch (Exception e3) {
                e3.printStackTrace();
                a();
                this.n.invoke("RNFetchBlob request error: " + e3.getMessage() + e3.getCause());
            }
        } else {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.i));
            if (this.f.f1461d.getBoolean("notification")) {
                request.setNotificationVisibility(1);
            } else {
                request.setNotificationVisibility(2);
            }
            if (this.f.f1461d.hasKey("title")) {
                request.setTitle(this.f.f1461d.getString("title"));
            }
            if (this.f.f1461d.hasKey("description")) {
                request.setDescription(this.f.f1461d.getString("description"));
            }
            if (this.f.f1461d.hasKey("path")) {
                request.setDestinationUri(Uri.parse("file://" + this.f.f1461d.getString("path")));
            }
            if (this.f.f1461d.hasKey("mime")) {
                request.setMimeType(this.f.f1461d.getString("mime"));
            }
            ReadableMapKeySetIterator keySetIterator2 = this.m.keySetIterator();
            if (this.f.f1461d.hasKey("mediaScannable") && this.f.f1461d.hasKey("mediaScannable")) {
                request.allowScanningByMediaScanner();
            }
            while (keySetIterator2.hasNextKey()) {
                String nextKey2 = keySetIterator2.nextKey();
                request.addRequestHeader(nextKey2, this.m.getString(nextKey2));
            }
            Context applicationContext = RNFetchBlob.RCTContext.getApplicationContext();
            this.p = ((DownloadManager) applicationContext.getSystemService("download")).enqueue(request);
            f1479b.put(this.g, Long.valueOf(this.p));
            applicationContext.registerReceiver(this, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a() {
        if (f1478a.containsKey(this.g)) {
            f1478a.remove(this.g);
        }
        if (f1479b.containsKey(this.g)) {
            f1479b.remove(this.g);
        }
        if (f1481d.containsKey(this.g)) {
            f1481d.remove(this.g);
        }
        if (f1480c.containsKey(this.g)) {
            f1480c.remove(this.g);
        }
        a aVar = this.q;
        if (aVar != null) {
            aVar.a();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Response response) {
        boolean b2 = b(response);
        a(a(response, b2));
        switch (this.s) {
            case KeepInMemory:
                if (b2) {
                    try {
                        if (this.f.h.booleanValue()) {
                            String a2 = d.a(this.g);
                            InputStream byteStream = response.body().byteStream();
                            FileOutputStream fileOutputStream = new FileOutputStream(new File(a2));
                            byte[] bArr = new byte[Task.EXTRAS_LIMIT_BYTES];
                            while (true) {
                                int read = byteStream.read(bArr);
                                if (read == -1) {
                                    byteStream.close();
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    this.n.invoke(null, "path", a2);
                                    break;
                                } else {
                                    fileOutputStream.write(bArr, 0, read);
                                }
                            }
                        }
                    } catch (IOException unused) {
                        this.n.invoke("RNFetchBlob failed to encode response data to BASE64 string.", null);
                        break;
                    }
                }
                byte[] bytes = response.body().bytes();
                CharsetEncoder newEncoder = Charset.forName("UTF-8").newEncoder();
                if (this.t != b.BASE64) {
                    try {
                        newEncoder.encode(ByteBuffer.wrap(bytes).asCharBuffer());
                        String str = new String(bytes);
                        this.n.invoke(null, "utf8", str);
                        break;
                    } catch (CharacterCodingException unused2) {
                        if (this.t != b.UTF8) {
                            this.n.invoke(null, "base64", Base64.encodeToString(bytes, 2));
                            break;
                        } else {
                            this.n.invoke(null, "utf8", "");
                            break;
                        }
                    }
                } else {
                    this.n.invoke(null, "base64", Base64.encodeToString(bytes, 2));
                    return;
                }
            case FileStorage:
                try {
                    response.body().bytes();
                } catch (Exception unused3) {
                }
                this.k = this.k.replace("?append=true", "");
                this.n.invoke(null, "path", this.k);
                break;
            default:
                try {
                    Callback callback = this.n;
                    callback.invoke(null, "utf8", new String(response.body().bytes(), "UTF-8"));
                    break;
                } catch (IOException unused4) {
                    this.n.invoke("RNFetchBlob failed to encode response data to UTF8 string.", null);
                    break;
                }
        }
        response.body().close();
        a();
    }

    public static f b(String str) {
        if (!f1480c.containsKey(str)) {
            return null;
        }
        return f1480c.get(str);
    }

    public static f c(String str) {
        if (!f1481d.containsKey(str)) {
            return null;
        }
        return f1481d.get(str);
    }

    private WritableMap a(Response response, boolean z) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("status", response.code());
        createMap.putString("state", "2");
        createMap.putString("taskId", this.g);
        createMap.putBoolean("timeout", this.v);
        WritableMap createMap2 = Arguments.createMap();
        for (int i2 = 0; i2 < response.headers().size(); i2++) {
            createMap2.putString(response.headers().name(i2), response.headers().value(i2));
        }
        WritableArray createArray = Arguments.createArray();
        Iterator<String> it = this.w.iterator();
        while (it.hasNext()) {
            createArray.pushString(it.next());
        }
        createMap.putArray("redirects", createArray);
        createMap.putMap("headers", createMap2);
        Headers headers = response.headers();
        if (z) {
            createMap.putString("respType", "blob");
        } else if (a(headers, "content-type").equalsIgnoreCase("text/")) {
            createMap.putString("respType", TextBundle.TEXT_ENTRY);
        } else if (a(headers, "content-type").contains(io.a.a.a.a.b.a.ACCEPT_JSON_VALUE)) {
            createMap.putString("respType", "json");
        } else {
            createMap.putString("respType", "");
        }
        return createMap;
    }

    private boolean b(Response response) {
        boolean z;
        String a2 = a(response.headers(), "Content-Type");
        boolean z2 = !a2.equalsIgnoreCase("text/");
        boolean z3 = !a2.equalsIgnoreCase(io.a.a.a.a.b.a.ACCEPT_JSON_VALUE);
        if (this.f.m != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.f.m.size()) {
                    break;
                } else if (a2.toLowerCase().contains(this.f.m.getString(i2).toLowerCase())) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if ((!z3 || z2) && !z) {
                return false;
            }
            return true;
        }
        z = false;
        if (!z3) {
        }
        return false;
    }

    private String a(Headers headers, String str) {
        String str2 = headers.get(str);
        if (str2 != null) {
            return str2;
        }
        return headers.get(str.toLowerCase()) == null ? "" : headers.get(str.toLowerCase());
    }

    private String a(HashMap<String, String> hashMap, String str) {
        String str2 = hashMap.get(str);
        if (str2 != null) {
            return str2;
        }
        String str3 = hashMap.get(str.toLowerCase());
        return str3 == null ? "" : str3;
    }

    private void a(WritableMap writableMap) {
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) RNFetchBlob.RCTContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("RNFetchBlobState", writableMap);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00dd A[SYNTHETIC, Splitter:B:22:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x011e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r13, android.content.Intent r14) {
        /*
        // Method dump skipped, instructions count: 322
        */
        throw new UnsupportedOperationException("Method not decompiled: com.RNFetchBlob.g.onReceive(android.content.Context, android.content.Intent):void");
    }

    public static OkHttpClient.Builder a(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19) {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
                    throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
                }
                X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                SSLContext instance2 = SSLContext.getInstance("SSL");
                instance2.init(null, new TrustManager[]{x509TrustManager}, null);
                builder.sslSocketFactory(instance2.getSocketFactory(), x509TrustManager);
                ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_2).build();
                ArrayList arrayList = new ArrayList();
                arrayList.add(build);
                arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                arrayList.add(ConnectionSpec.CLEARTEXT);
                builder.connectionSpecs(arrayList);
            } catch (Exception e2) {
                com.facebook.common.e.a.c("OkHttpClientProvider", "Error while enabling TLS 1.2", e2);
            }
        }
        return builder;
    }
}
