package com.facebook.imagepipeline.n;

import android.net.Uri;
import com.facebook.common.k.f;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.ag;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import okhttp3.internal.http.StatusLine;

/* compiled from: HttpUrlConnectionNetworkFetcher */
public class u extends c<t> {

    /* renamed from: a  reason: collision with root package name */
    private int f2425a;

    /* renamed from: b  reason: collision with root package name */
    private final ExecutorService f2426b;

    private static boolean a(int i) {
        return i >= 200 && i < 300;
    }

    private static boolean b(int i) {
        switch (i) {
            case 300:
            case 301:
            case 302:
            case 303:
            case StatusLine.HTTP_TEMP_REDIRECT /*{ENCODED_INT: 307}*/:
            case StatusLine.HTTP_PERM_REDIRECT /*{ENCODED_INT: 308}*/:
                return true;
            case 304:
            case 305:
            case 306:
            default:
                return false;
        }
    }

    public u() {
        this(Executors.newFixedThreadPool(3));
    }

    public u(int i) {
        this(Executors.newFixedThreadPool(3));
        this.f2425a = i;
    }

    u(ExecutorService executorService) {
        this.f2426b = executorService;
    }

    @Override // com.facebook.imagepipeline.n.ag
    public t b(k<d> kVar, al alVar) {
        return new t(kVar, alVar);
    }

    @Override // com.facebook.imagepipeline.n.ag
    public void a(final t tVar, final ag.a aVar) {
        final Future<?> submit = this.f2426b.submit(new Runnable() {
            /* class com.facebook.imagepipeline.n.u.AnonymousClass1 */

            public void run() {
                u.this.b(tVar, aVar);
            }
        });
        tVar.b().a(new e() {
            /* class com.facebook.imagepipeline.n.u.AnonymousClass2 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                if (submit.cancel(false)) {
                    aVar.a();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0039 A[SYNTHETIC, Splitter:B:26:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(com.facebook.imagepipeline.n.t r3, com.facebook.imagepipeline.n.ag.a r4) {
        /*
            r2 = this;
            r0 = 0
            android.net.Uri r3 = r3.e()     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            r1 = 5
            java.net.HttpURLConnection r3 = r2.a(r3, r1)     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            if (r3 == 0) goto L_0x0017
            java.io.InputStream r0 = r3.getInputStream()     // Catch:{ IOException -> 0x0015 }
            r1 = -1
            r4.a(r0, r1)     // Catch:{ IOException -> 0x0015 }
            goto L_0x0017
        L_0x0015:
            r1 = move-exception
            goto L_0x0026
        L_0x0017:
            if (r0 == 0) goto L_0x001e
            r0.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x001e
        L_0x001d:
        L_0x001e:
            if (r3 == 0) goto L_0x0035
            goto L_0x0032
        L_0x0021:
            r4 = move-exception
            r3 = r0
            goto L_0x0037
        L_0x0024:
            r1 = move-exception
            r3 = r0
        L_0x0026:
            r4.a(r1)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0030
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0030
        L_0x002f:
        L_0x0030:
            if (r3 == 0) goto L_0x0035
        L_0x0032:
            r3.disconnect()
        L_0x0035:
            return
        L_0x0036:
            r4 = move-exception
        L_0x0037:
            if (r0 == 0) goto L_0x003e
            r0.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x003e
        L_0x003d:
        L_0x003e:
            if (r3 == 0) goto L_0x0043
            r3.disconnect()
        L_0x0043:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.n.u.b(com.facebook.imagepipeline.n.t, com.facebook.imagepipeline.n.ag$a):void");
    }

    private HttpURLConnection a(Uri uri, int i) {
        Uri uri2;
        String str;
        HttpURLConnection a2 = a(uri);
        a2.setConnectTimeout(this.f2425a);
        int responseCode = a2.getResponseCode();
        if (a(responseCode)) {
            return a2;
        }
        if (b(responseCode)) {
            String headerField = a2.getHeaderField("Location");
            a2.disconnect();
            if (headerField == null) {
                uri2 = null;
            } else {
                uri2 = Uri.parse(headerField);
            }
            String scheme = uri.getScheme();
            if (i > 0 && uri2 != null && !uri2.getScheme().equals(scheme)) {
                return a(uri2, i - 1);
            }
            if (i == 0) {
                str = a("URL %s follows too many redirects", uri.toString());
            } else {
                str = a("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode));
            }
            throw new IOException(str);
        }
        a2.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), Integer.valueOf(responseCode)));
    }

    static HttpURLConnection a(Uri uri) {
        return (HttpURLConnection) f.a(uri).openConnection();
    }

    private static String a(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }
}
