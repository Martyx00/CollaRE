package com.facebook.imagepipeline.n;

import com.facebook.imagepipeline.j.d;
import com.facebook.imagepipeline.n.t;
import java.io.InputStream;
import java.util.Map;

/* compiled from: NetworkFetcher */
public interface ag<FETCH_STATE extends t> {

    /* compiled from: NetworkFetcher */
    public interface a {
        void a();

        void a(InputStream inputStream, int i);

        void a(Throwable th);
    }

    Map<String, String> a(FETCH_STATE fetch_state, int i);

    void a(FETCH_STATE fetch_state, a aVar);

    boolean a(FETCH_STATE fetch_state);

    FETCH_STATE b(k<d> kVar, al alVar);

    void b(FETCH_STATE fetch_state, int i);
}
