package io.a.a.a.a.e;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* compiled from: NetworkUtils */
public final class f {
    public static final SSLSocketFactory a(g gVar) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init(null, new TrustManager[]{new h(new i(gVar.getKeyStoreStream(), gVar.getKeyStorePassword()), gVar)}, null);
        return instance.getSocketFactory();
    }
}
