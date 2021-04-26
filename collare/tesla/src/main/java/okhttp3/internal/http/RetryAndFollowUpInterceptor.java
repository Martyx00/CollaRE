package okhttp3.internal.http;

import com.google.android.gms.common.api.Api;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http2.ConnectionShutdownException;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private Object callStackTrace;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private final boolean forWebSocket;
    private volatile StreamAllocation streamAllocation;

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient, boolean z) {
        this.client = okHttpClient;
        this.forWebSocket = z;
    }

    public void cancel() {
        this.canceled = true;
        StreamAllocation streamAllocation2 = this.streamAllocation;
        if (streamAllocation2 != null) {
            streamAllocation2.cancel();
        }
    }

    public boolean isCanceled() {
        return this.canceled;
    }

    public void setCallStackTrace(Object obj) {
        this.callStackTrace = obj;
    }

    public StreamAllocation streamAllocation() {
        return this.streamAllocation;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Call call = realInterceptorChain.call();
        EventListener eventListener = realInterceptorChain.eventListener();
        StreamAllocation streamAllocation2 = new StreamAllocation(this.client.connectionPool(), createAddress(request.url()), call, eventListener, this.callStackTrace);
        this.streamAllocation = streamAllocation2;
        Response response = null;
        int i = 0;
        while (!this.canceled) {
            try {
                Response proceed = realInterceptorChain.proceed(request, streamAllocation2, null, null);
                if (response != null) {
                    proceed = proceed.newBuilder().priorResponse(response.newBuilder().body(null).build()).build();
                }
                try {
                    Request followUpRequest = followUpRequest(proceed, streamAllocation2.route());
                    if (followUpRequest == null) {
                        streamAllocation2.release();
                        return proceed;
                    }
                    Util.closeQuietly(proceed.body());
                    int i2 = i + 1;
                    if (i2 > 20) {
                        streamAllocation2.release();
                        throw new ProtocolException("Too many follow-up requests: " + i2);
                    } else if (!(followUpRequest.body() instanceof UnrepeatableRequestBody)) {
                        if (!sameConnection(proceed, followUpRequest.url())) {
                            streamAllocation2.release();
                            streamAllocation2 = new StreamAllocation(this.client.connectionPool(), createAddress(followUpRequest.url()), call, eventListener, this.callStackTrace);
                            this.streamAllocation = streamAllocation2;
                        } else if (streamAllocation2.codec() != null) {
                            throw new IllegalStateException("Closing the body of " + proceed + " didn't close its backing stream. Bad interceptor?");
                        }
                        response = proceed;
                        request = followUpRequest;
                        i = i2;
                    } else {
                        streamAllocation2.release();
                        throw new HttpRetryException("Cannot retry streamed HTTP body", proceed.code());
                    }
                } catch (IOException e) {
                    streamAllocation2.release();
                    throw e;
                }
            } catch (RouteException e2) {
                if (!recover(e2.getLastConnectException(), streamAllocation2, false, request)) {
                    throw e2.getFirstConnectException();
                }
            } catch (IOException e3) {
                if (!recover(e3, streamAllocation2, !(e3 instanceof ConnectionShutdownException), request)) {
                    throw e3;
                }
            } catch (Throwable th) {
                streamAllocation2.streamFailed(null);
                streamAllocation2.release();
                throw th;
            }
        }
        streamAllocation2.release();
        throw new IOException("Canceled");
    }

    private Address createAddress(HttpUrl httpUrl) {
        CertificatePinner certificatePinner;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        if (httpUrl.isHttps()) {
            SSLSocketFactory sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            sSLSocketFactory = sslSocketFactory;
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    private boolean recover(IOException iOException, StreamAllocation streamAllocation2, boolean z, Request request) {
        streamAllocation2.streamFailed(iOException);
        if (!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if ((!z || !(request.body() instanceof UnrepeatableRequestBody)) && isRecoverable(iOException, z) && streamAllocation2.hasMoreRoutes()) {
            return true;
        }
        return false;
    }

    private boolean isRecoverable(IOException iOException, boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!(iOException instanceof SocketTimeoutException) || z) {
                return false;
            }
            return true;
        } else if ((!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException)) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private Request followUpRequest(Response response, Route route) {
        String header;
        HttpUrl resolve;
        Proxy proxy;
        if (response != null) {
            int code = response.code();
            String method = response.request().method();
            RequestBody requestBody = null;
            switch (code) {
                case 300:
                case 301:
                case 302:
                case 303:
                    break;
                case StatusLine.HTTP_TEMP_REDIRECT /*{ENCODED_INT: 307}*/:
                case StatusLine.HTTP_PERM_REDIRECT /*{ENCODED_INT: 308}*/:
                    if (!method.equals("GET") && !method.equals("HEAD")) {
                        return null;
                    }
                case 401:
                    return this.client.authenticator().authenticate(route, response);
                case 407:
                    if (route != null) {
                        proxy = route.proxy();
                    } else {
                        proxy = this.client.proxy();
                    }
                    if (proxy.type() == Proxy.Type.HTTP) {
                        return this.client.proxyAuthenticator().authenticate(route, response);
                    }
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                case 408:
                    if (!this.client.retryOnConnectionFailure() || (response.request().body() instanceof UnrepeatableRequestBody)) {
                        return null;
                    }
                    if ((response.priorResponse() == null || response.priorResponse().code() != 408) && retryAfter(response, 0) <= 0) {
                        return response.request();
                    }
                    return null;
                case 503:
                    if ((response.priorResponse() == null || response.priorResponse().code() != 503) && retryAfter(response, Api.BaseClientBuilder.API_PRIORITY_OTHER) == 0) {
                        return response.request();
                    }
                    return null;
                default:
                    return null;
            }
            if (!this.client.followRedirects() || (header = response.header("Location")) == null || (resolve = response.request().url().resolve(header)) == null) {
                return null;
            }
            if (!resolve.scheme().equals(response.request().url().scheme()) && !this.client.followSslRedirects()) {
                return null;
            }
            Request.Builder newBuilder = response.request().newBuilder();
            if (HttpMethod.permitsRequestBody(method)) {
                boolean redirectsWithBody = HttpMethod.redirectsWithBody(method);
                if (HttpMethod.redirectsToGet(method)) {
                    newBuilder.method("GET", null);
                } else {
                    if (redirectsWithBody) {
                        requestBody = response.request().body();
                    }
                    newBuilder.method(method, requestBody);
                }
                if (!redirectsWithBody) {
                    newBuilder.removeHeader("Transfer-Encoding");
                    newBuilder.removeHeader("Content-Length");
                    newBuilder.removeHeader("Content-Type");
                }
            }
            if (!sameConnection(response, resolve)) {
                newBuilder.removeHeader("Authorization");
            }
            return newBuilder.url(resolve).build();
        }
        throw new IllegalStateException();
    }

    private int retryAfter(Response response, int i) {
        String header = response.header("Retry-After");
        if (header == null) {
            return i;
        }
        return header.matches("\\d+") ? Integer.valueOf(header).intValue() : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    private boolean sameConnection(Response response, HttpUrl httpUrl) {
        HttpUrl url = response.request().url();
        return url.host().equals(httpUrl.host()) && url.port() == httpUrl.port() && url.scheme().equals(httpUrl.scheme());
    }
}
