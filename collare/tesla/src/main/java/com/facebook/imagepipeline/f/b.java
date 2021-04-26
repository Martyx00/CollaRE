package com.facebook.imagepipeline.f;

import com.facebook.b.b.c;
import com.facebook.b.b.d;
import com.facebook.b.b.e;
import com.facebook.b.b.i;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: DiskStorageCacheFactory */
public class b implements f {

    /* renamed from: a  reason: collision with root package name */
    private c f2102a;

    public b(c cVar) {
        this.f2102a = cVar;
    }

    public static e a(c cVar, d dVar) {
        return a(cVar, dVar, Executors.newSingleThreadExecutor());
    }

    public static e a(c cVar, d dVar, Executor executor) {
        return new e(dVar, cVar.g(), new e.b(cVar.f(), cVar.e(), cVar.d()), cVar.i(), cVar.h(), cVar.j(), cVar.k(), executor, cVar.l());
    }

    @Override // com.facebook.imagepipeline.f.f
    public i a(c cVar) {
        return a(cVar, this.f2102a.a(cVar));
    }
}
