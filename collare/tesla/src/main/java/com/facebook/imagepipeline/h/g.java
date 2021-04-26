package com.facebook.imagepipeline.h;

import com.facebook.common.d.i;
import com.facebook.imagepipeline.j.f;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.List;

/* compiled from: SimpleProgressiveJpegConfig */
public class g implements e {

    /* renamed from: a  reason: collision with root package name */
    private final b f2162a;

    /* compiled from: SimpleProgressiveJpegConfig */
    public interface b {
        List<Integer> a();

        int b();
    }

    /* compiled from: SimpleProgressiveJpegConfig */
    private static class a implements b {
        @Override // com.facebook.imagepipeline.h.g.b
        public int b() {
            return 0;
        }

        private a() {
        }

        @Override // com.facebook.imagepipeline.h.g.b
        public List<Integer> a() {
            return Collections.EMPTY_LIST;
        }
    }

    public g() {
        this(new a());
    }

    public g(b bVar) {
        this.f2162a = (b) i.a(bVar);
    }

    @Override // com.facebook.imagepipeline.h.e
    public int a(int i) {
        List<Integer> a2 = this.f2162a.a();
        if (a2 == null || a2.isEmpty()) {
            return i + 1;
        }
        for (int i2 = 0; i2 < a2.size(); i2++) {
            if (a2.get(i2).intValue() > i) {
                return a2.get(i2).intValue();
            }
        }
        return Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    @Override // com.facebook.imagepipeline.h.e
    public com.facebook.imagepipeline.j.g b(int i) {
        return f.a(i, i >= this.f2162a.b(), false);
    }
}
