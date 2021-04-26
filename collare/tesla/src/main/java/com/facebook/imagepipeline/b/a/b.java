package com.facebook.imagepipeline.b.a;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.ag;
import com.facebook.imagepipeline.n.al;
import com.facebook.imagepipeline.n.c;
import com.facebook.imagepipeline.n.e;
import com.facebook.imagepipeline.n.k;
import com.facebook.imagepipeline.n.t;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: OkHttpNetworkFetcher */
public class b extends c<a> {

    /* renamed from: a  reason: collision with root package name */
    private final Call.Factory f2006a;

    /* renamed from: b  reason: collision with root package name */
    private Executor f2007b;

    /* compiled from: OkHttpNetworkFetcher */
    public static class a extends t {

        /* renamed from: a  reason: collision with root package name */
        public long f2014a;

        /* renamed from: b  reason: collision with root package name */
        public long f2015b;

        /* renamed from: c  reason: collision with root package name */
        public long f2016c;

        public a(k<d> kVar, al alVar) {
            super(kVar, alVar);
        }
    }

    public b(OkHttpClient okHttpClient) {
        this(okHttpClient, okHttpClient.dispatcher().executorService());
    }

    public b(Call.Factory factory, Executor executor) {
        this.f2006a = factory;
        this.f2007b = executor;
    }

    /* renamed from: a */
    public a b(k<d> kVar, al alVar) {
        return new a(kVar, alVar);
    }

    public void a(a aVar, ag.a aVar2) {
        aVar.f2014a = SystemClock.elapsedRealtime();
        try {
            Request.Builder builder = new Request.Builder().cacheControl(new CacheControl.Builder().noStore().build()).url(aVar.e().toString()).get();
            com.facebook.imagepipeline.e.a h = aVar.b().a().h();
            if (h != null) {
                builder.addHeader("Range", h.a());
            }
            a(aVar, aVar2, builder.build());
        } catch (Exception e) {
            aVar2.a(e);
        }
    }

    /* renamed from: a */
    public void b(a aVar, int i) {
        aVar.f2016c = SystemClock.elapsedRealtime();
    }

    /* renamed from: b */
    public Map<String, String> a(a aVar, int i) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(aVar.f2015b - aVar.f2014a));
        hashMap.put("fetch_time", Long.toString(aVar.f2016c - aVar.f2015b));
        hashMap.put("total_time", Long.toString(aVar.f2016c - aVar.f2014a));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void a(final a aVar, final ag.a aVar2, Request request) {
        final Call newCall = this.f2006a.newCall(request);
        aVar.b().a(new e() {
            /* class com.facebook.imagepipeline.b.a.b.AnonymousClass1 */

            @Override // com.facebook.imagepipeline.n.am, com.facebook.imagepipeline.n.e
            public void a() {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    newCall.cancel();
                } else {
                    b.this.f2007b.execute(new Runnable() {
                        /* class com.facebook.imagepipeline.b.a.b.AnonymousClass1.AnonymousClass1 */

                        public void run() {
                            newCall.cancel();
                        }
                    });
                }
            }
        });
        newCall.enqueue(new Callback() {
            /* class com.facebook.imagepipeline.b.a.b.AnonymousClass2 */

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) {
                aVar.f2015b = SystemClock.elapsedRealtime();
                ResponseBody body = response.body();
                try {
                    if (!response.isSuccessful()) {
                        b.this.a((b) call, (Call) new IOException("Unexpected HTTP code " + response), (Exception) aVar2);
                        body.close();
                        return;
                    }
                    com.facebook.imagepipeline.e.a a2 = com.facebook.imagepipeline.e.a.a(response.header("Content-Range"));
                    if (!(a2 == null || (a2.f2076a == 0 && a2.f2077b == Integer.MAX_VALUE))) {
                        aVar.a(a2);
                        aVar.a(8);
                    }
                    long contentLength = body.contentLength();
                    if (contentLength < 0) {
                        contentLength = 0;
                    }
                    aVar2.a(body.byteStream(), (int) contentLength);
                    body.close();
                } catch (Exception e) {
                    b.this.a((b) call, (Call) e, (Exception) aVar2);
                } catch (Throwable th) {
                    body.close();
                    throw th;
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                b.this.a((b) call, (Call) iOException, (Exception) aVar2);
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Call call, Exception exc, ag.a aVar) {
        if (call.isCanceled()) {
            aVar.a();
        } else {
            aVar.a(exc);
        }
    }
}
