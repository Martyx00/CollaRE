package com.facebook.react.modules.network;

import android.net.Uri;
import android.util.Base64;
import c.f;
import c.j;
import c.l;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.h;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.spongycastle.i18n.TextBundle;

@com.facebook.react.module.a.a(a = NetworkingModule.NAME)
public final class NetworkingModule extends ReactContextBaseJavaModule {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    protected static final String NAME = "Networking";
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "NetworkingModule";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private final OkHttpClient mClient;
    private final c mCookieHandler;
    private final a mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final List<a> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    private final List<b> mResponseHandlers;
    private boolean mShuttingDown;
    private final List<c> mUriHandlers;

    public interface a {
        RequestBody a(ReadableMap readableMap, String str);

        boolean a(ReadableMap readableMap);
    }

    public interface b {
        WritableMap a(ResponseBody responseBody);

        boolean a(String str);
    }

    public interface c {
        WritableMap a(Uri uri);

        boolean a(Uri uri, String str);
    }

    /* access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient, List<e> list) {
        super(reactApplicationContext);
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            for (e eVar : list) {
                newBuilder.addNetworkInterceptor(eVar.a());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        this.mCookieHandler = new c(reactApplicationContext);
        this.mCookieJarContainer = (a) this.mClient.cookieJar();
        this.mShuttingDown = false;
        this.mDefaultUserAgent = str;
        this.mRequestIds = new HashSet();
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, null, h.a(reactApplicationContext), null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<e> list) {
        this(reactApplicationContext, null, h.a(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, h.a(reactApplicationContext), null);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        this.mCookieJarContainer.a(new JavaNetCookieJar(this.mCookieHandler));
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.a();
        this.mCookieJarContainer.a();
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    public void addUriHandler(c cVar) {
        this.mUriHandlers.add(cVar);
    }

    public void addRequestBodyHandler(a aVar) {
        this.mRequestBodyHandlers.add(aVar);
    }

    public void addResponseHandler(b bVar) {
        this.mResponseHandlers.add(bVar);
    }

    public void removeUriHandler(c cVar) {
        this.mUriHandlers.remove(cVar);
    }

    public void removeRequestBodyHandler(a aVar) {
        this.mRequestBodyHandlers.remove(aVar);
    }

    public void removeResponseHandler(b bVar) {
        this.mResponseHandlers.remove(bVar);
    }

    @ReactMethod
    public void sendRequest(String str, String str2, int i, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, int i2, boolean z2) {
        try {
            sendRequestInternal(str, str2, i, readableArray, readableMap, str3, z, i2, z2);
        } catch (Throwable th) {
            com.facebook.common.e.a.c(TAG, "Failed to send url request: " + str2, th);
            o.a(getEventEmitter(), i, th.getMessage(), th);
        }
    }

    public void sendRequestInternal(String str, String str2, final int i, ReadableArray readableArray, ReadableMap readableMap, final String str3, final boolean z, int i2, boolean z2) {
        a aVar;
        RequestBody requestBody;
        Charset charset;
        final DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        try {
            Uri parse = Uri.parse(str2);
            for (c cVar : this.mUriHandlers) {
                if (cVar.a(parse, str3)) {
                    o.a(eventEmitter, i, cVar.a(parse));
                    o.a(eventEmitter, i);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (i != 0) {
                    url.tag(Integer.valueOf(i));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new Interceptor() {
                        /* class com.facebook.react.modules.network.NetworkingModule.AnonymousClass1 */

                        @Override // okhttp3.Interceptor
                        public Response intercept(Interceptor.Chain chain) {
                            Response proceed = chain.proceed(chain.request());
                            return proceed.newBuilder().body(new k(proceed.body(), new i() {
                                /* class com.facebook.react.modules.network.NetworkingModule.AnonymousClass1.AnonymousClass1 */

                                /* renamed from: a  reason: collision with root package name */
                                long f2915a = System.nanoTime();

                                @Override // com.facebook.react.modules.network.i
                                public void a(long j, long j2, boolean z) {
                                    long nanoTime = System.nanoTime();
                                    if ((z || NetworkingModule.shouldDispatch(nanoTime, this.f2915a)) && !str3.equals(TextBundle.TEXT_ENTRY)) {
                                        o.b(eventEmitter, i, j, j2);
                                        this.f2915a = nanoTime;
                                    }
                                }
                            })).build();
                        }
                    });
                }
                if (i2 != this.mClient.connectTimeoutMillis()) {
                    newBuilder.connectTimeout((long) i2, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    o.a(eventEmitter, i, "Unrecognized headers format", (Throwable) null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<a> it = this.mRequestBodyHandlers.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        aVar = it.next();
                        if (aVar.a(readableMap)) {
                            break;
                        }
                    }
                }
                aVar = null;
                if (readableMap == null || str.toLowerCase().equals("get") || str.toLowerCase().equals("head")) {
                    requestBody = n.b(str);
                } else if (aVar != null) {
                    requestBody = aVar.a(readableMap, str4);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                    if (str4 == null) {
                        o.a(eventEmitter, i, "Payload is set but no content-type header specified", (Throwable) null);
                        return;
                    }
                    String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                    MediaType parse2 = MediaType.parse(str4);
                    if (n.a(str5)) {
                        requestBody = n.a(parse2, string);
                        if (requestBody == null) {
                            o.a(eventEmitter, i, "Failed to gzip request body", (Throwable) null);
                            return;
                        }
                    } else {
                        if (parse2 == null) {
                            charset = h.f2617a;
                        } else {
                            charset = parse2.charset(h.f2617a);
                        }
                        requestBody = RequestBody.create(parse2, string.getBytes(charset));
                    }
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_BASE64)) {
                    if (str4 == null) {
                        o.a(eventEmitter, i, "Payload is set but no content-type header specified", (Throwable) null);
                        return;
                    }
                    requestBody = RequestBody.create(MediaType.parse(str4), f.b(readableMap.getString(REQUEST_BODY_KEY_BASE64)));
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_URI)) {
                    if (str4 == null) {
                        o.a(eventEmitter, i, "Payload is set but no content-type header specified", (Throwable) null);
                        return;
                    }
                    String string2 = readableMap.getString(REQUEST_BODY_KEY_URI);
                    InputStream a2 = n.a(getReactApplicationContext(), string2);
                    if (a2 == null) {
                        o.a(eventEmitter, i, "Could not retrieve file for uri " + string2, (Throwable) null);
                        return;
                    }
                    requestBody = n.a(MediaType.parse(str4), a2);
                } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                    if (str4 == null) {
                        str4 = "multipart/form-data";
                    }
                    MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, i);
                    if (constructMultipartBody != null) {
                        requestBody = constructMultipartBody.build();
                    } else {
                        return;
                    }
                } else {
                    requestBody = n.b(str);
                }
                url.method(str, wrapRequestBodyWithProgressEmitter(requestBody, eventEmitter, i));
                addRequest(i);
                build.newCall(url.build()).enqueue(new Callback() {
                    /* class com.facebook.react.modules.network.NetworkingModule.AnonymousClass2 */

                    @Override // okhttp3.Callback
                    public void onFailure(Call call, IOException iOException) {
                        String str;
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i);
                            if (iOException.getMessage() != null) {
                                str = iOException.getMessage();
                            } else {
                                str = "Error while executing request: " + iOException.getClass().getSimpleName();
                            }
                            o.a(eventEmitter, i, str, iOException);
                        }
                    }

                    @Override // okhttp3.Callback
                    public void onResponse(Call call, Response response) {
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i);
                            o.a(eventEmitter, i, response.code(), NetworkingModule.translateHeaders(response.headers()), response.request().url().toString());
                            try {
                                ResponseBody body = response.body();
                                if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                    j jVar = new j(body.source());
                                    String header = response.header("Content-Type");
                                    body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1, l.a(jVar));
                                }
                                for (b bVar : NetworkingModule.this.mResponseHandlers) {
                                    if (bVar.a(str3)) {
                                        o.a(eventEmitter, i, bVar.a(body));
                                        o.a(eventEmitter, i);
                                        return;
                                    }
                                }
                                if (!z || !str3.equals(TextBundle.TEXT_ENTRY)) {
                                    String str = "";
                                    if (str3.equals(TextBundle.TEXT_ENTRY)) {
                                        try {
                                            str = body.string();
                                        } catch (IOException e2) {
                                            if (!response.request().method().equalsIgnoreCase("HEAD")) {
                                                o.a(eventEmitter, i, e2.getMessage(), e2);
                                            }
                                        }
                                    } else if (str3.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                                        str = Base64.encodeToString(body.bytes(), 2);
                                    }
                                    o.a(eventEmitter, i, str);
                                    o.a(eventEmitter, i);
                                    return;
                                }
                                NetworkingModule.this.readWithProgress(eventEmitter, i, body);
                                o.a(eventEmitter, i);
                            } catch (IOException e3) {
                                o.a(eventEmitter, i, e3.getMessage(), e3);
                            }
                        }
                    }
                });
            } catch (Exception e) {
                o.a(eventEmitter, i, e.getMessage(), (Throwable) null);
            }
        } catch (IOException e2) {
            o.a(eventEmitter, i, e2.getMessage(), e2);
        }
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, final int i) {
        if (requestBody == null) {
            return null;
        }
        return n.a(requestBody, new i() {
            /* class com.facebook.react.modules.network.NetworkingModule.AnonymousClass3 */

            /* renamed from: a  reason: collision with root package name */
            long f2921a = System.nanoTime();

            @Override // com.facebook.react.modules.network.i
            public void a(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.f2921a)) {
                    o.a(rCTDeviceEventEmitter, i, j, j2);
                    this.f2921a = nanoTime;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void readWithProgress(DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, ResponseBody responseBody) {
        long j;
        Charset charset;
        long j2 = -1;
        try {
            k kVar = (k) responseBody;
            j = kVar.a();
            try {
                j2 = kVar.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        if (responseBody.contentType() == null) {
            charset = h.f2617a;
        } else {
            charset = responseBody.contentType().charset(h.f2617a);
        }
        l lVar = new l(charset);
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            while (true) {
                int read = byteStream.read(bArr);
                if (read != -1) {
                    o.a(rCTDeviceEventEmitter, i, lVar.a(bArr, read), j, j2);
                } else {
                    return;
                }
            }
        } finally {
            byteStream.close();
        }
    }

    private synchronized void addRequest(int i) {
        this.mRequestIds.add(Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void removeRequest(int i) {
        this.mRequestIds.remove(Integer.valueOf(i));
    }

    private synchronized void cancelAllRequests() {
        for (Integer num : this.mRequestIds) {
            cancelRequest(num.intValue());
        }
        this.mRequestIds.clear();
    }

    /* access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        WritableMap createMap = Arguments.createMap();
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            if (createMap.hasKey(name)) {
                createMap.putString(name, createMap.getString(name) + ", " + headers.value(i));
            } else {
                createMap.putString(name, headers.value(i));
            }
        }
        return createMap;
    }

    @ReactMethod
    public void abortRequest(int i) {
        cancelRequest(i);
        removeRequest(i);
    }

    private void cancelRequest(final int i) {
        new GuardedAsyncTask<Void, Void>(getReactApplicationContext()) {
            /* class com.facebook.react.modules.network.NetworkingModule.AnonymousClass4 */

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void doInBackgroundGuarded(Void... voidArr) {
                com.facebook.react.common.a.a.a(NetworkingModule.this.mClient, Integer.valueOf(i));
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void clearCookies(com.facebook.react.bridge.Callback callback) {
        this.mCookieHandler.a(callback);
    }

    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i) {
        MediaType mediaType;
        DeviceEventManagerModule.RCTDeviceEventEmitter eventEmitter = getEventEmitter();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            Headers extractHeaders = extractHeaders(map.getArray("headers"), null);
            if (extractHeaders == null) {
                o.a(eventEmitter, i, "Missing or invalid header format for FormData part.", (Throwable) null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!map.hasKey(REQUEST_BODY_KEY_URI)) {
                o.a(eventEmitter, i, "Unrecognized FormData part.", (Throwable) null);
            } else if (mediaType == null) {
                o.a(eventEmitter, i, "Binary FormData part needs a content-type header.", (Throwable) null);
                return null;
            } else {
                String string = map.getString(REQUEST_BODY_KEY_URI);
                InputStream a2 = n.a(getReactApplicationContext(), string);
                if (a2 == null) {
                    o.a(eventEmitter, i, "Could not retrieve file for uri " + string, (Throwable) null);
                    return null;
                }
                builder.addPart(extractHeaders, n.a(mediaType, a2));
            }
        }
        return builder;
    }

    private Headers extractHeaders(ReadableArray readableArray, ReadableMap readableMap) {
        String str;
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ReadableArray array = readableArray.getArray(i);
            if (array == null || array.size() != 2) {
                return null;
            }
            String a2 = d.a(array.getString(0));
            String b2 = d.b(array.getString(1));
            if (a2 == null || b2 == null) {
                return null;
            }
            builder.add(a2, b2);
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.mDefaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (readableMap != null && readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            z = true;
        }
        if (!z) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }

    private DeviceEventManagerModule.RCTDeviceEventEmitter getEventEmitter() {
        return (DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
    }
}
