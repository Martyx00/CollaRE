package okhttp3.internal.huc;

import c.c;
import io.a.a.a.a.b.a;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketPermission;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.Permission;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Internal;
import okhttp3.internal.JavaNetHeaders;
import okhttp3.internal.URLFilter;
import okhttp3.internal.Version;
import okhttp3.internal.http.HttpDate;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.StatusLine;
import okhttp3.internal.platform.Platform;

public final class OkHttpURLConnection extends HttpURLConnection implements Callback {
    private static final Set<String> METHODS = new LinkedHashSet(Arrays.asList("OPTIONS", "GET", "HEAD", "POST", "PUT", "DELETE", "TRACE", "PATCH"));
    public static final String RESPONSE_SOURCE = (Platform.get().getPrefix() + "-Response-Source");
    public static final String SELECTED_PROTOCOL = (Platform.get().getPrefix() + "-Selected-Protocol");
    Call call;
    private Throwable callFailure;
    OkHttpClient client;
    boolean connectPending;
    private boolean executed;
    private long fixedContentLength;
    Handshake handshake;
    private final Object lock;
    private final NetworkInterceptor networkInterceptor;
    Response networkResponse;
    Proxy proxy;
    private Headers.Builder requestHeaders;
    private Response response;
    private Headers responseHeaders;
    URLFilter urlFilter;

    public OkHttpURLConnection(URL url, OkHttpClient okHttpClient) {
        super(url);
        this.networkInterceptor = new NetworkInterceptor();
        this.requestHeaders = new Headers.Builder();
        this.fixedContentLength = -1;
        this.lock = new Object();
        this.connectPending = true;
        this.client = okHttpClient;
    }

    public OkHttpURLConnection(URL url, OkHttpClient okHttpClient, URLFilter uRLFilter) {
        this(url, okHttpClient);
        this.urlFilter = uRLFilter;
    }

    @Override // java.net.URLConnection
    public void connect() {
        if (!this.executed) {
            Call buildCall = buildCall();
            this.executed = true;
            buildCall.enqueue(this);
            synchronized (this.lock) {
                while (this.connectPending && this.response == null && this.callFailure == null) {
                    try {
                        this.lock.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                if (this.callFailure != null) {
                    throw propagate(this.callFailure);
                }
            }
        }
    }

    public void disconnect() {
        if (this.call != null) {
            this.networkInterceptor.proceed();
            this.call.cancel();
        }
    }

    public InputStream getErrorStream() {
        try {
            Response response2 = getResponse(true);
            if (!HttpHeaders.hasBody(response2) || response2.code() < 400) {
                return null;
            }
            return response2.body().byteStream();
        } catch (IOException unused) {
            return null;
        }
    }

    private Headers getHeaders() {
        if (this.responseHeaders == null) {
            Response response2 = getResponse(true);
            this.responseHeaders = response2.headers().newBuilder().add(SELECTED_PROTOCOL, response2.protocol().toString()).add(RESPONSE_SOURCE, responseSourceHeader(response2)).build();
        }
        return this.responseHeaders;
    }

    private static String responseSourceHeader(Response response2) {
        if (response2.networkResponse() == null) {
            if (response2.cacheResponse() == null) {
                return "NONE";
            }
            return "CACHE " + response2.code();
        } else if (response2.cacheResponse() == null) {
            return "NETWORK " + response2.code();
        } else {
            return "CONDITIONAL_CACHE " + response2.networkResponse().code();
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        try {
            Headers headers = getHeaders();
            if (i >= 0) {
                if (i < headers.size()) {
                    return headers.value(i);
                }
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        if (str != null) {
            return getHeaders().get(str);
        }
        try {
            return StatusLine.get(getResponse(true)).toString();
        } catch (IOException unused) {
            return null;
        }
    }

    public String getHeaderFieldKey(int i) {
        try {
            Headers headers = getHeaders();
            if (i >= 0) {
                if (i < headers.size()) {
                    return headers.name(i);
                }
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        try {
            return JavaNetHeaders.toMultimap(getHeaders(), StatusLine.get(getResponse(true)).toString());
        } catch (IOException unused) {
            return Collections.emptyMap();
        }
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return JavaNetHeaders.toMultimap(this.requestHeaders.build(), null);
        }
        throw new IllegalStateException("Cannot access request header fields after connection is set");
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() {
        if (this.doInput) {
            Response response2 = getResponse(false);
            if (response2.code() < 400) {
                return response2.body().byteStream();
            }
            throw new FileNotFoundException(this.url.toString());
        }
        throw new ProtocolException("This protocol does not support input");
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() {
        OutputStreamRequestBody outputStreamRequestBody = (OutputStreamRequestBody) buildCall().request().body();
        if (outputStreamRequestBody != null) {
            if (outputStreamRequestBody instanceof StreamedRequestBody) {
                connect();
                this.networkInterceptor.proceed();
            }
            if (!outputStreamRequestBody.isClosed()) {
                return outputStreamRequestBody.outputStream();
            }
            throw new ProtocolException("cannot write request body after response has been read");
        }
        throw new ProtocolException("method does not support a request body: " + this.method);
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() {
        int i;
        URL url = getURL();
        String host = url.getHost();
        if (url.getPort() != -1) {
            i = url.getPort();
        } else {
            i = HttpUrl.defaultPort(url.getProtocol());
        }
        if (usingProxy()) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.client.proxy().address();
            host = inetSocketAddress.getHostName();
            i = inetSocketAddress.getPort();
        }
        return new SocketPermission(host + ":" + i, "connect, resolve");
    }

    public String getRequestProperty(String str) {
        if (str == null) {
            return null;
        }
        return this.requestHeaders.get(str);
    }

    public void setConnectTimeout(int i) {
        this.client = this.client.newBuilder().connectTimeout((long) i, TimeUnit.MILLISECONDS).build();
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.client = this.client.newBuilder().followRedirects(z).build();
    }

    public boolean getInstanceFollowRedirects() {
        return this.client.followRedirects();
    }

    public int getConnectTimeout() {
        return this.client.connectTimeoutMillis();
    }

    public void setReadTimeout(int i) {
        this.client = this.client.newBuilder().readTimeout((long) i, TimeUnit.MILLISECONDS).build();
    }

    public int getReadTimeout() {
        return this.client.readTimeoutMillis();
    }

    private Call buildCall() {
        OutputStreamRequestBody outputStreamRequestBody;
        Call call2 = this.call;
        if (call2 != null) {
            return call2;
        }
        boolean z = true;
        this.connected = true;
        if (this.doOutput) {
            if (this.method.equals("GET")) {
                this.method = "POST";
            } else if (!HttpMethod.permitsRequestBody(this.method)) {
                throw new ProtocolException(this.method + " does not support writing");
            }
        }
        if (this.requestHeaders.get(a.HEADER_USER_AGENT) == null) {
            this.requestHeaders.add(a.HEADER_USER_AGENT, defaultUserAgent());
        }
        if (HttpMethod.permitsRequestBody(this.method)) {
            if (this.requestHeaders.get("Content-Type") == null) {
                this.requestHeaders.add("Content-Type", "application/x-www-form-urlencoded");
            }
            long j = -1;
            if (this.fixedContentLength == -1 && this.chunkLength <= 0) {
                z = false;
            }
            String str = this.requestHeaders.get("Content-Length");
            long j2 = this.fixedContentLength;
            if (j2 != -1) {
                j = j2;
            } else if (str != null) {
                j = Long.parseLong(str);
            }
            if (z) {
                outputStreamRequestBody = new StreamedRequestBody(j);
            } else {
                outputStreamRequestBody = new BufferedRequestBody(j);
            }
            outputStreamRequestBody.timeout().timeout((long) this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        } else {
            outputStreamRequestBody = null;
        }
        try {
            Request build = new Request.Builder().url(HttpUrl.get(getURL().toString())).headers(this.requestHeaders.build()).method(this.method, outputStreamRequestBody).build();
            URLFilter uRLFilter = this.urlFilter;
            if (uRLFilter != null) {
                uRLFilter.checkURLPermitted(build.url().url());
            }
            OkHttpClient.Builder newBuilder = this.client.newBuilder();
            newBuilder.interceptors().clear();
            newBuilder.interceptors().add(UnexpectedException.INTERCEPTOR);
            newBuilder.networkInterceptors().clear();
            newBuilder.networkInterceptors().add(this.networkInterceptor);
            newBuilder.dispatcher(new Dispatcher(this.client.dispatcher().executorService()));
            if (!getUseCaches()) {
                newBuilder.cache(null);
            }
            Call newCall = newBuilder.build().newCall(build);
            this.call = newCall;
            return newCall;
        } catch (IllegalArgumentException e) {
            if (Internal.instance.isInvalidHttpUrlHost(e)) {
                UnknownHostException unknownHostException = new UnknownHostException();
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
            MalformedURLException malformedURLException = new MalformedURLException();
            malformedURLException.initCause(e);
            throw malformedURLException;
        }
    }

    private String defaultUserAgent() {
        String property = System.getProperty("http.agent");
        return property != null ? toHumanReadableAscii(property) : Version.userAgent();
    }

    private static String toHumanReadableAscii(String str) {
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt <= 31 || codePointAt >= 127) {
                c cVar = new c();
                cVar.a(str, 0, i);
                cVar.a(63);
                while (true) {
                    i += Character.charCount(codePointAt);
                    if (i >= length) {
                        return cVar.r();
                    }
                    codePointAt = str.codePointAt(i);
                    cVar.a((codePointAt <= 31 || codePointAt >= 127) ? 63 : codePointAt);
                }
            } else {
                i += Character.charCount(codePointAt);
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0021, code lost:
        r3 = buildCall();
        r2.networkInterceptor.proceed();
        r0 = (okhttp3.internal.huc.OutputStreamRequestBody) r3.request().body();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0034, code lost:
        if (r0 == null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0036, code lost:
        r0.outputStream().close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
        if (r2.executed == false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        r0 = r2.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0046, code lost:
        if (r2.response != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x004a, code lost:
        if (r2.callFailure != null) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x004c, code lost:
        r2.lock.wait();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0054, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0056, code lost:
        java.lang.Thread.currentThread().interrupt();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0062, code lost:
        throw new java.io.InterruptedIOException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0064, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0065, code lost:
        r2.executed = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        onResponse(r3, r3.execute());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0070, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0071, code lost:
        onFailure(r3, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private okhttp3.Response getResponse(boolean r3) {
        /*
        // Method dump skipped, instructions count: 151
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.huc.OkHttpURLConnection.getResponse(boolean):okhttp3.Response");
    }

    public boolean usingProxy() {
        if (this.proxy != null) {
            return true;
        }
        Proxy proxy2 = this.client.proxy();
        if (proxy2 == null || proxy2.type() == Proxy.Type.DIRECT) {
            return false;
        }
        return true;
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() {
        return getResponse(true).message();
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() {
        return getResponse(true).code();
    }

    public void setRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot set request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform platform = Platform.get();
            platform.log(5, "Ignoring header " + str + " because its value was null.", null);
        } else {
            this.requestHeaders.set(str, str2);
        }
    }

    public void setIfModifiedSince(long j) {
        super.setIfModifiedSince(j);
        if (this.ifModifiedSince != 0) {
            this.requestHeaders.set("If-Modified-Since", HttpDate.format(new Date(this.ifModifiedSince)));
        } else {
            this.requestHeaders.removeAll("If-Modified-Since");
        }
    }

    public void addRequestProperty(String str, String str2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot add request property after connection is made");
        } else if (str == null) {
            throw new NullPointerException("field == null");
        } else if (str2 == null) {
            Platform platform = Platform.get();
            platform.log(5, "Ignoring header " + str + " because its value was null.", null);
        } else {
            this.requestHeaders.add(str, str2);
        }
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) {
        if (METHODS.contains(str)) {
            this.method = str;
            return;
        }
        throw new ProtocolException("Expected one of " + METHODS + " but was " + str);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        setFixedLengthStreamingMode((long) i);
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(long j) {
        if (((HttpURLConnection) this).connected) {
            throw new IllegalStateException("Already connected");
        } else if (this.chunkLength > 0) {
            throw new IllegalStateException("Already in chunked mode");
        } else if (j >= 0) {
            this.fixedContentLength = j;
            ((HttpURLConnection) this).fixedContentLength = (int) Math.min(j, 2147483647L);
        } else {
            throw new IllegalArgumentException("contentLength < 0");
        }
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call2, IOException iOException) {
        synchronized (this.lock) {
            boolean z = iOException instanceof UnexpectedException;
            Throwable th = iOException;
            if (z) {
                th = iOException.getCause();
            }
            this.callFailure = th;
            this.lock.notifyAll();
        }
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call2, Response response2) {
        synchronized (this.lock) {
            this.response = response2;
            this.handshake = response2.handshake();
            this.url = response2.request().url().url();
            this.lock.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public static final class UnexpectedException extends IOException {
        static final Interceptor INTERCEPTOR = new Interceptor() {
            /* class okhttp3.internal.huc.OkHttpURLConnection.UnexpectedException.AnonymousClass1 */

            @Override // okhttp3.Interceptor
            public Response intercept(Interceptor.Chain chain) {
                try {
                    return chain.proceed(chain.request());
                } catch (Error | RuntimeException e) {
                    throw new UnexpectedException(e);
                }
            }
        };

        UnexpectedException(Throwable th) {
            super(th);
        }
    }

    private static IOException propagate(Throwable th) {
        if (th instanceof IOException) {
            throw ((IOException) th);
        } else if (th instanceof Error) {
            throw ((Error) th);
        } else if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        } else {
            throw new AssertionError();
        }
    }

    /* access modifiers changed from: package-private */
    public final class NetworkInterceptor implements Interceptor {
        private boolean proceed;

        NetworkInterceptor() {
        }

        public void proceed() {
            synchronized (OkHttpURLConnection.this.lock) {
                this.proceed = true;
                OkHttpURLConnection.this.lock.notifyAll();
            }
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) {
            Request request = chain.request();
            if (OkHttpURLConnection.this.urlFilter != null) {
                OkHttpURLConnection.this.urlFilter.checkURLPermitted(request.url().url());
            }
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.connectPending = false;
                OkHttpURLConnection.this.proxy = chain.connection().route().proxy();
                OkHttpURLConnection.this.handshake = chain.connection().handshake();
                OkHttpURLConnection.this.lock.notifyAll();
                while (!this.proceed) {
                    try {
                        OkHttpURLConnection.this.lock.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
            }
            if (request.body() instanceof OutputStreamRequestBody) {
                request = ((OutputStreamRequestBody) request.body()).prepareToSendRequest(request);
            }
            Response proceed2 = chain.proceed(request);
            synchronized (OkHttpURLConnection.this.lock) {
                OkHttpURLConnection.this.networkResponse = proceed2;
                OkHttpURLConnection.this.url = proceed2.request().url().url();
            }
            return proceed2;
        }
    }
}
