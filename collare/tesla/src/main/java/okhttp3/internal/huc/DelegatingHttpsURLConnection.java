package okhttp3.internal.huc;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.Handshake;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* access modifiers changed from: package-private */
public abstract class DelegatingHttpsURLConnection extends HttpsURLConnection {
    private final HttpURLConnection delegate;

    public abstract HostnameVerifier getHostnameVerifier();

    public abstract SSLSocketFactory getSSLSocketFactory();

    /* access modifiers changed from: protected */
    public abstract Handshake handshake();

    public abstract void setHostnameVerifier(HostnameVerifier hostnameVerifier);

    public abstract void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory);

    DelegatingHttpsURLConnection(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.delegate = httpURLConnection;
    }

    public String getCipherSuite() {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.cipherSuite().javaName();
        }
        return null;
    }

    public Certificate[] getLocalCertificates() {
        Handshake handshake = handshake();
        if (handshake == null) {
            return null;
        }
        List<Certificate> localCertificates = handshake.localCertificates();
        if (!localCertificates.isEmpty()) {
            return (Certificate[]) localCertificates.toArray(new Certificate[localCertificates.size()]);
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Certificate[] getServerCertificates() {
        Handshake handshake = handshake();
        if (handshake == null) {
            return null;
        }
        List<Certificate> peerCertificates = handshake.peerCertificates();
        if (!peerCertificates.isEmpty()) {
            return (Certificate[]) peerCertificates.toArray(new Certificate[peerCertificates.size()]);
        }
        return null;
    }

    @Override // javax.net.ssl.HttpsURLConnection
    public Principal getPeerPrincipal() {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.peerPrincipal();
        }
        return null;
    }

    public Principal getLocalPrincipal() {
        Handshake handshake = handshake();
        if (handshake != null) {
            return handshake.localPrincipal();
        }
        return null;
    }

    @Override // java.net.URLConnection
    public void connect() {
        this.connected = true;
        this.delegate.connect();
    }

    public void disconnect() {
        this.delegate.disconnect();
    }

    public InputStream getErrorStream() {
        return this.delegate.getErrorStream();
    }

    public String getRequestMethod() {
        return this.delegate.getRequestMethod();
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() {
        return this.delegate.getResponseCode();
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() {
        return this.delegate.getResponseMessage();
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) {
        this.delegate.setRequestMethod(str);
    }

    public boolean usingProxy() {
        return this.delegate.usingProxy();
    }

    public boolean getInstanceFollowRedirects() {
        return this.delegate.getInstanceFollowRedirects();
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.delegate.setInstanceFollowRedirects(z);
    }

    public boolean getAllowUserInteraction() {
        return this.delegate.getAllowUserInteraction();
    }

    @Override // java.net.URLConnection
    public Object getContent() {
        return this.delegate.getContent();
    }

    @Override // java.net.URLConnection
    public Object getContent(Class[] clsArr) {
        return this.delegate.getContent(clsArr);
    }

    public String getContentEncoding() {
        return this.delegate.getContentEncoding();
    }

    public int getContentLength() {
        return this.delegate.getContentLength();
    }

    @IgnoreJRERequirement
    public long getContentLengthLong() {
        return this.delegate.getContentLengthLong();
    }

    public String getContentType() {
        return this.delegate.getContentType();
    }

    public long getDate() {
        return this.delegate.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.delegate.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.delegate.getDoInput();
    }

    public boolean getDoOutput() {
        return this.delegate.getDoOutput();
    }

    public long getExpiration() {
        return this.delegate.getExpiration();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public String getHeaderField(int i) {
        return this.delegate.getHeaderField(i);
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        return this.delegate.getHeaderFields();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        return this.delegate.getRequestProperties();
    }

    public void addRequestProperty(String str, String str2) {
        this.delegate.addRequestProperty(str, str2);
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        return this.delegate.getHeaderField(str);
    }

    @IgnoreJRERequirement
    public long getHeaderFieldLong(String str, long j) {
        return this.delegate.getHeaderFieldLong(str, j);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.delegate.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.delegate.getHeaderFieldInt(str, i);
    }

    public String getHeaderFieldKey(int i) {
        return this.delegate.getHeaderFieldKey(i);
    }

    public long getIfModifiedSince() {
        return this.delegate.getIfModifiedSince();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() {
        return this.delegate.getInputStream();
    }

    public long getLastModified() {
        return this.delegate.getLastModified();
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() {
        return this.delegate.getOutputStream();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public Permission getPermission() {
        return this.delegate.getPermission();
    }

    public String getRequestProperty(String str) {
        return this.delegate.getRequestProperty(str);
    }

    public URL getURL() {
        return this.delegate.getURL();
    }

    public boolean getUseCaches() {
        return this.delegate.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.delegate.setAllowUserInteraction(z);
    }

    public void setDefaultUseCaches(boolean z) {
        this.delegate.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.delegate.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.delegate.setDoOutput(z);
    }

    @Override // java.net.HttpURLConnection
    @IgnoreJRERequirement
    public void setFixedLengthStreamingMode(long j) {
        this.delegate.setFixedLengthStreamingMode(j);
    }

    public void setIfModifiedSince(long j) {
        this.delegate.setIfModifiedSince(j);
    }

    public void setRequestProperty(String str, String str2) {
        this.delegate.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.delegate.setUseCaches(z);
    }

    public void setConnectTimeout(int i) {
        this.delegate.setConnectTimeout(i);
    }

    public int getConnectTimeout() {
        return this.delegate.getConnectTimeout();
    }

    public void setReadTimeout(int i) {
        this.delegate.setReadTimeout(i);
    }

    public int getReadTimeout() {
        return this.delegate.getReadTimeout();
    }

    public String toString() {
        return this.delegate.toString();
    }

    @Override // java.net.HttpURLConnection
    public void setFixedLengthStreamingMode(int i) {
        this.delegate.setFixedLengthStreamingMode(i);
    }

    public void setChunkedStreamingMode(int i) {
        this.delegate.setChunkedStreamingMode(i);
    }
}
