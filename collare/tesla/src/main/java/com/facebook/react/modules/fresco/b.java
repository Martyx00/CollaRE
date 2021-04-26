package com.facebook.react.modules.fresco;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.b.a.b;
import com.facebook.imagepipeline.n.ag;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* access modifiers changed from: package-private */
/* compiled from: ReactOkHttpNetworkFetcher */
public class b extends com.facebook.imagepipeline.b.a.b {

    /* renamed from: a  reason: collision with root package name */
    private final OkHttpClient f2880a;

    /* renamed from: b  reason: collision with root package name */
    private final Executor f2881b;

    public b(OkHttpClient okHttpClient) {
        super(okHttpClient);
        this.f2880a = okHttpClient;
        this.f2881b = okHttpClient.dispatcher().executorService();
    }

    private Map<String, String> a(ReadableMap readableMap) {
        if (readableMap == null) {
            return null;
        }
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        HashMap hashMap = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            hashMap.put(nextKey, readableMap.getString(nextKey));
        }
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.b.a.b
    public void a(b.a aVar, ag.a aVar2) {
        aVar.f2014a = SystemClock.elapsedRealtime();
        Uri e = aVar.e();
        Map<String, String> a2 = aVar.b().a() instanceof a ? a(((a) aVar.b().a()).s()) : null;
        if (a2 == null) {
            a2 = Collections.emptyMap();
        }
        a(aVar, aVar2, new Request.Builder().cacheControl(new CacheControl.Builder().noStore().build()).url(e.toString()).headers(Headers.of(a2)).get().build());
    }
}
