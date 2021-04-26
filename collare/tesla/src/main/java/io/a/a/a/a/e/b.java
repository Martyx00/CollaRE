package io.a.a.a.a.e;

import io.a.a.a.l;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: DefaultHttpRequestFactory */
public class b implements e {

    /* renamed from: a  reason: collision with root package name */
    private final l f5990a;

    /* renamed from: b  reason: collision with root package name */
    private g f5991b;

    /* renamed from: c  reason: collision with root package name */
    private SSLSocketFactory f5992c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5993d;

    public b() {
        this(new io.a.a.a.b());
    }

    public b(l lVar) {
        this.f5990a = lVar;
    }

    @Override // io.a.a.a.a.e.e
    public void a(g gVar) {
        if (this.f5991b != gVar) {
            this.f5991b = gVar;
            a();
        }
    }

    private synchronized void a() {
        this.f5993d = false;
        this.f5992c = null;
    }

    @Override // io.a.a.a.a.e.e
    public d a(c cVar, String str) {
        return a(cVar, str, Collections.emptyMap());
    }

    @Override // io.a.a.a.a.e.e
    public d a(c cVar, String str, Map<String, String> map) {
        d dVar;
        SSLSocketFactory b2;
        switch (cVar) {
            case GET:
                dVar = d.a((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case POST:
                dVar = d.b((CharSequence) str, (Map<?, ?>) map, true);
                break;
            case PUT:
                dVar = d.d((CharSequence) str);
                break;
            case DELETE:
                dVar = d.e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (!(!a(str) || this.f5991b == null || (b2 = b()) == null)) {
            ((HttpsURLConnection) dVar.a()).setSSLSocketFactory(b2);
        }
        return dVar;
    }

    private boolean a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith("https");
    }

    private synchronized SSLSocketFactory b() {
        if (this.f5992c == null && !this.f5993d) {
            this.f5992c = c();
        }
        return this.f5992c;
    }

    private synchronized SSLSocketFactory c() {
        SSLSocketFactory a2;
        this.f5993d = true;
        try {
            a2 = f.a(this.f5991b);
            this.f5990a.a("Fabric", "Custom SSL pinning enabled");
        } catch (Exception e) {
            this.f5990a.e("Fabric", "Exception while validating pinned certs", e);
            return null;
        }
        return a2;
    }
}
