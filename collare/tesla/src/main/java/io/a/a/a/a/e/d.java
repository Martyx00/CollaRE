package io.a.a.a.a.e;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.zip.GZIPInputStream;
import org.spongycastle.asn1.cmp.PKIFailureInfo;

/* compiled from: HttpRequest */
public class d {

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f5999b = new String[0];

    /* renamed from: c  reason: collision with root package name */
    private static b f6000c = b.f6008a;

    /* renamed from: a  reason: collision with root package name */
    public final URL f6001a;

    /* renamed from: d  reason: collision with root package name */
    private HttpURLConnection f6002d = null;
    private final String e;
    private e f;
    private boolean g;
    private boolean h = true;
    private boolean i = false;
    private int j = PKIFailureInfo.certRevoked;
    private String k;
    private int l;

    /* compiled from: HttpRequest */
    public interface b {

        /* renamed from: a  reason: collision with root package name */
        public static final b f6008a = new b() {
            /* class io.a.a.a.a.e.d.b.AnonymousClass1 */

            @Override // io.a.a.a.a.e.d.b
            public HttpURLConnection a(URL url) {
                return (HttpURLConnection) url.openConnection();
            }

            @Override // io.a.a.a.a.e.d.b
            public HttpURLConnection a(URL url, Proxy proxy) {
                return (HttpURLConnection) url.openConnection(proxy);
            }
        };

        HttpURLConnection a(URL url);

        HttpURLConnection a(URL url, Proxy proxy);
    }

    /* access modifiers changed from: private */
    public static String f(String str) {
        return (str == null || str.length() <= 0) ? "UTF-8" : str;
    }

    private static StringBuilder a(String str, StringBuilder sb) {
        if (str.indexOf(58) + 2 == str.lastIndexOf(47)) {
            sb.append('/');
        }
        return sb;
    }

    private static StringBuilder b(String str, StringBuilder sb) {
        int indexOf = str.indexOf(63);
        int length = sb.length() - 1;
        if (indexOf == -1) {
            sb.append('?');
        } else if (indexOf < length && str.charAt(length) != '&') {
            sb.append('&');
        }
        return sb;
    }

    /* compiled from: HttpRequest */
    public static class c extends RuntimeException {
        protected c(IOException iOException) {
            super(iOException);
        }

        /* renamed from: a */
        public IOException getCause() {
            return (IOException) super.getCause();
        }
    }

    /* renamed from: io.a.a.a.a.e.d$d  reason: collision with other inner class name */
    /* compiled from: HttpRequest */
    protected static abstract class AbstractCallableC0163d<V> implements Callable<V> {
        /* access modifiers changed from: protected */
        public abstract V b();

        /* access modifiers changed from: protected */
        public abstract void c();

        protected AbstractCallableC0163d() {
        }

        @Override // java.util.concurrent.Callable
        public V call() {
            Throwable th;
            boolean z = true;
            try {
                V b2 = b();
                try {
                    c();
                    return b2;
                } catch (IOException e) {
                    throw new c(e);
                }
            } catch (c e2) {
                throw e2;
            } catch (IOException e3) {
                throw new c(e3);
            } catch (Throwable th2) {
                th = th2;
                c();
                throw th;
            }
        }
    }

    /* compiled from: HttpRequest */
    protected static abstract class a<V> extends AbstractCallableC0163d<V> {

        /* renamed from: a  reason: collision with root package name */
        private final Closeable f6006a;

        /* renamed from: b  reason: collision with root package name */
        private final boolean f6007b;

        protected a(Closeable closeable, boolean z) {
            this.f6006a = closeable;
            this.f6007b = z;
        }

        /* access modifiers changed from: protected */
        @Override // io.a.a.a.a.e.d.AbstractCallableC0163d
        public void c() {
            Closeable closeable = this.f6006a;
            if (closeable instanceof Flushable) {
                ((Flushable) closeable).flush();
            }
            if (this.f6007b) {
                try {
                    this.f6006a.close();
                } catch (IOException unused) {
                }
            } else {
                this.f6006a.close();
            }
        }
    }

    /* compiled from: HttpRequest */
    public static class e extends BufferedOutputStream {

        /* renamed from: a  reason: collision with root package name */
        private final CharsetEncoder f6009a;

        public e(OutputStream outputStream, String str, int i) {
            super(outputStream, i);
            this.f6009a = Charset.forName(d.f(str)).newEncoder();
        }

        public e a(String str) {
            ByteBuffer encode = this.f6009a.encode(CharBuffer.wrap(str));
            super.write(encode.array(), 0, encode.limit());
            return this;
        }
    }

    public static String a(CharSequence charSequence) {
        String str;
        int i2;
        try {
            URL url = new URL(charSequence.toString());
            String host = url.getHost();
            int port = url.getPort();
            if (port != -1) {
                str = host + ':' + Integer.toString(port);
            } else {
                str = host;
            }
            try {
                String aSCIIString = new URI(url.getProtocol(), str, url.getPath(), url.getQuery(), null).toASCIIString();
                int indexOf = aSCIIString.indexOf(63);
                if (indexOf <= 0 || (i2 = indexOf + 1) >= aSCIIString.length()) {
                    return aSCIIString;
                }
                return aSCIIString.substring(0, i2) + aSCIIString.substring(i2).replace("+", "%2B");
            } catch (URISyntaxException e2) {
                IOException iOException = new IOException("Parsing URI failed");
                iOException.initCause(e2);
                throw new c(iOException);
            }
        } catch (IOException e3) {
            throw new c(e3);
        }
    }

    public static String a(CharSequence charSequence, Map<?, ?> map) {
        String charSequence2 = charSequence.toString();
        if (map == null || map.isEmpty()) {
            return charSequence2;
        }
        StringBuilder sb = new StringBuilder(charSequence2);
        a(charSequence2, sb);
        b(charSequence2, sb);
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        Map.Entry<?, ?> next = it.next();
        sb.append(next.getKey().toString());
        sb.append('=');
        Object value = next.getValue();
        if (value != null) {
            sb.append(value);
        }
        while (it.hasNext()) {
            sb.append('&');
            Map.Entry<?, ?> next2 = it.next();
            sb.append(next2.getKey().toString());
            sb.append('=');
            Object value2 = next2.getValue();
            if (value2 != null) {
                sb.append(value2);
            }
        }
        return sb.toString();
    }

    public static d b(CharSequence charSequence) {
        return new d(charSequence, "GET");
    }

    public static d a(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String a2 = a(charSequence, map);
        if (z) {
            a2 = a((CharSequence) a2);
        }
        return b((CharSequence) a2);
    }

    public static d c(CharSequence charSequence) {
        return new d(charSequence, "POST");
    }

    public static d b(CharSequence charSequence, Map<?, ?> map, boolean z) {
        String a2 = a(charSequence, map);
        if (z) {
            a2 = a((CharSequence) a2);
        }
        return c((CharSequence) a2);
    }

    public static d d(CharSequence charSequence) {
        return new d(charSequence, "PUT");
    }

    public static d e(CharSequence charSequence) {
        return new d(charSequence, "DELETE");
    }

    public d(CharSequence charSequence, String str) {
        try {
            this.f6001a = new URL(charSequence.toString());
            this.e = str;
        } catch (MalformedURLException e2) {
            throw new c(e2);
        }
    }

    private Proxy p() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.k, this.l));
    }

    private HttpURLConnection q() {
        HttpURLConnection httpURLConnection;
        try {
            if (this.k != null) {
                httpURLConnection = f6000c.a(this.f6001a, p());
            } else {
                httpURLConnection = f6000c.a(this.f6001a);
            }
            httpURLConnection.setRequestMethod(this.e);
            return httpURLConnection;
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public String toString() {
        return o() + ' ' + n();
    }

    public HttpURLConnection a() {
        if (this.f6002d == null) {
            this.f6002d = q();
        }
        return this.f6002d;
    }

    public int b() {
        try {
            j();
            return a().getResponseCode();
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    /* access modifiers changed from: protected */
    public ByteArrayOutputStream c() {
        int i2 = i();
        if (i2 > 0) {
            return new ByteArrayOutputStream(i2);
        }
        return new ByteArrayOutputStream();
    }

    public String a(String str) {
        ByteArrayOutputStream c2 = c();
        try {
            a(e(), c2);
            return c2.toString(f(str));
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public String d() {
        return a(g());
    }

    public BufferedInputStream e() {
        return new BufferedInputStream(f(), this.j);
    }

    public InputStream f() {
        InputStream inputStream;
        if (b() < 400) {
            try {
                inputStream = a().getInputStream();
            } catch (IOException e2) {
                throw new c(e2);
            }
        } else {
            inputStream = a().getErrorStream();
            if (inputStream == null) {
                try {
                    inputStream = a().getInputStream();
                } catch (IOException e3) {
                    throw new c(e3);
                }
            }
        }
        if (!this.i || !"gzip".equals(h())) {
            return inputStream;
        }
        try {
            return new GZIPInputStream(inputStream);
        } catch (IOException e4) {
            throw new c(e4);
        }
    }

    public d a(int i2) {
        a().setConnectTimeout(i2);
        return this;
    }

    public d a(String str, String str2) {
        a().setRequestProperty(str, str2);
        return this;
    }

    public d a(Map.Entry<String, String> entry) {
        return a(entry.getKey(), entry.getValue());
    }

    public String b(String str) {
        k();
        return a().getHeaderField(str);
    }

    public int c(String str) {
        return a(str, -1);
    }

    public int a(String str, int i2) {
        k();
        return a().getHeaderFieldInt(str, i2);
    }

    public String b(String str, String str2) {
        return c(b(str), str2);
    }

    /* access modifiers changed from: protected */
    public String c(String str, String str2) {
        String trim;
        int length;
        if (str == null || str.length() == 0) {
            return null;
        }
        int length2 = str.length();
        int indexOf = str.indexOf(59) + 1;
        if (indexOf == 0 || indexOf == length2) {
            return null;
        }
        int indexOf2 = str.indexOf(59, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = length2;
        }
        while (indexOf < indexOf2) {
            int indexOf3 = str.indexOf(61, indexOf);
            if (indexOf3 == -1 || indexOf3 >= indexOf2 || !str2.equals(str.substring(indexOf, indexOf3).trim()) || (length = (trim = str.substring(indexOf3 + 1, indexOf2).trim()).length()) == 0) {
                indexOf = indexOf2 + 1;
                indexOf2 = str.indexOf(59, indexOf);
                if (indexOf2 == -1) {
                    indexOf2 = length2;
                }
            } else {
                if (length > 2 && '\"' == trim.charAt(0)) {
                    int i2 = length - 1;
                    if ('\"' == trim.charAt(i2)) {
                        return trim.substring(1, i2);
                    }
                }
                return trim;
            }
        }
        return null;
    }

    public String g() {
        return b("Content-Type", "charset");
    }

    public d a(boolean z) {
        a().setUseCaches(z);
        return this;
    }

    public String h() {
        return b("Content-Encoding");
    }

    public d d(String str) {
        return d(str, null);
    }

    public d d(String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return a("Content-Type", str);
        }
        return a("Content-Type", str + "; charset=" + str2);
    }

    public int i() {
        return c("Content-Length");
    }

    /* access modifiers changed from: protected */
    public d a(final InputStream inputStream, final OutputStream outputStream) {
        return (d) new a<d>(this.h, inputStream) {
            /* class io.a.a.a.a.e.d.AnonymousClass1 */

            /* renamed from: a */
            public d b() {
                byte[] bArr = new byte[d.this.j];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        return d.this;
                    }
                    outputStream.write(bArr, 0, read);
                }
            }
        }.call();
    }

    /* access modifiers changed from: protected */
    public d j() {
        e eVar = this.f;
        if (eVar == null) {
            return this;
        }
        if (this.g) {
            eVar.a("\r\n--00content0boundary00--\r\n");
        }
        if (this.h) {
            try {
                this.f.close();
            } catch (IOException unused) {
            }
        } else {
            this.f.close();
        }
        this.f = null;
        return this;
    }

    /* access modifiers changed from: protected */
    public d k() {
        try {
            return j();
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    /* access modifiers changed from: protected */
    public d l() {
        if (this.f != null) {
            return this;
        }
        a().setDoOutput(true);
        this.f = new e(a().getOutputStream(), c(a().getRequestProperty("Content-Type"), "charset"), this.j);
        return this;
    }

    /* access modifiers changed from: protected */
    public d m() {
        if (!this.g) {
            this.g = true;
            d("multipart/form-data; boundary=00content0boundary00").l();
            this.f.a("--00content0boundary00\r\n");
        } else {
            this.f.a("\r\n--00content0boundary00\r\n");
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public d a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("form-data; name=\"");
        sb.append(str);
        if (str2 != null) {
            sb.append("\"; filename=\"");
            sb.append(str2);
        }
        sb.append('\"');
        f("Content-Disposition", sb.toString());
        if (str3 != null) {
            f("Content-Type", str3);
        }
        return f("\r\n");
    }

    public d e(String str, String str2) {
        return b(str, (String) null, str2);
    }

    public d b(String str, String str2, String str3) {
        return a(str, str2, (String) null, str3);
    }

    public d a(String str, String str2, String str3, String str4) {
        try {
            m();
            a(str, str2, str3);
            this.f.a(str4);
            return this;
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public d a(String str, Number number) {
        return a(str, (String) null, number);
    }

    public d a(String str, String str2, Number number) {
        return b(str, str2, number != null ? number.toString() : null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0024 A[SYNTHETIC, Splitter:B:19:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public io.a.a.a.a.e.d a(java.lang.String r4, java.lang.String r5, java.lang.String r6, java.io.File r7) {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x001b }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x001b }
            r2.<init>(r7)     // Catch:{ IOException -> 0x001b }
            r1.<init>(r2)     // Catch:{ IOException -> 0x001b }
            io.a.a.a.a.e.d r4 = r3.a(r4, r5, r6, r1)     // Catch:{ IOException -> 0x0016, all -> 0x0013 }
            r1.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r4
        L_0x0013:
            r4 = move-exception
            r0 = r1
            goto L_0x0022
        L_0x0016:
            r4 = move-exception
            r0 = r1
            goto L_0x001c
        L_0x0019:
            r4 = move-exception
            goto L_0x0022
        L_0x001b:
            r4 = move-exception
        L_0x001c:
            io.a.a.a.a.e.d$c r5 = new io.a.a.a.a.e.d$c     // Catch:{ all -> 0x0019 }
            r5.<init>(r4)     // Catch:{ all -> 0x0019 }
            throw r5     // Catch:{ all -> 0x0019 }
        L_0x0022:
            if (r0 == 0) goto L_0x0027
            r0.close()     // Catch:{ IOException -> 0x0027 }
        L_0x0027:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.a.a.a.a.e.d.a(java.lang.String, java.lang.String, java.lang.String, java.io.File):io.a.a.a.a.e.d");
    }

    public d a(String str, String str2, String str3, InputStream inputStream) {
        try {
            m();
            a(str, str2, str3);
            a(inputStream, this.f);
            return this;
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public d f(String str, String str2) {
        return f((CharSequence) str).f(": ").f((CharSequence) str2).f("\r\n");
    }

    public d f(CharSequence charSequence) {
        try {
            l();
            this.f.a(charSequence.toString());
            return this;
        } catch (IOException e2) {
            throw new c(e2);
        }
    }

    public URL n() {
        return a().getURL();
    }

    public String o() {
        return a().getRequestMethod();
    }
}
