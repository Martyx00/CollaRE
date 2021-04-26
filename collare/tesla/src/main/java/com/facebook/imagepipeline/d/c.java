package com.facebook.imagepipeline.d;

import android.net.Uri;
import com.facebook.b.a.d;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.e.b;
import com.facebook.imagepipeline.e.e;
import com.facebook.imagepipeline.e.f;

/* compiled from: BitmapMemoryCacheKey */
public class c implements d {

    /* renamed from: a  reason: collision with root package name */
    private final String f2029a;

    /* renamed from: b  reason: collision with root package name */
    private final e f2030b;

    /* renamed from: c  reason: collision with root package name */
    private final f f2031c;

    /* renamed from: d  reason: collision with root package name */
    private final b f2032d;
    private final d e;
    private final String f;
    private final int g;
    private final Object h;
    private final long i;

    public c(String str, e eVar, f fVar, b bVar, d dVar, String str2, Object obj) {
        this.f2029a = (String) i.a(str);
        this.f2030b = eVar;
        this.f2031c = fVar;
        this.f2032d = bVar;
        this.e = dVar;
        this.f = str2;
        this.g = com.facebook.common.k.b.a(Integer.valueOf(str.hashCode()), Integer.valueOf(eVar != null ? eVar.hashCode() : 0), Integer.valueOf(fVar.hashCode()), this.f2032d, this.e, str2);
        this.h = obj;
        this.i = RealtimeSinceBootClock.get().now();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.g != cVar.g || !this.f2029a.equals(cVar.f2029a) || !h.a(this.f2030b, cVar.f2030b) || !h.a(this.f2031c, cVar.f2031c) || !h.a(this.f2032d, cVar.f2032d) || !h.a(this.e, cVar.e) || !h.a(this.f, cVar.f)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.g;
    }

    @Override // com.facebook.b.a.d
    public boolean a(Uri uri) {
        return a().contains(uri.toString());
    }

    @Override // com.facebook.b.a.d
    public String a() {
        return this.f2029a;
    }

    public String toString() {
        return String.format(null, "%s_%s_%s_%s_%s_%s_%d", this.f2029a, this.f2030b, this.f2031c, this.f2032d, this.e, this.f, Integer.valueOf(this.g));
    }
}
