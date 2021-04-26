package com.facebook.imagepipeline.e;

import android.graphics.Bitmap;
import com.facebook.imagepipeline.h.c;

/* compiled from: ImageDecodeOptions */
public class b {
    private static final b h = b().h();

    /* renamed from: a  reason: collision with root package name */
    public final int f2078a;

    /* renamed from: b  reason: collision with root package name */
    public final boolean f2079b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f2080c;

    /* renamed from: d  reason: collision with root package name */
    public final boolean f2081d;
    public final boolean e;
    public final Bitmap.Config f;
    public final c g;

    public b(c cVar) {
        this.f2078a = cVar.a();
        this.f2079b = cVar.b();
        this.f2080c = cVar.c();
        this.f2081d = cVar.d();
        this.e = cVar.f();
        this.f = cVar.g();
        this.g = cVar.e();
    }

    public static b a() {
        return h;
    }

    public static c b() {
        return new c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f2079b == bVar.f2079b && this.f2080c == bVar.f2080c && this.f2081d == bVar.f2081d && this.e == bVar.e && this.f == bVar.f && this.g == bVar.g;
    }

    public int hashCode() {
        int ordinal = ((((((((((this.f2078a * 31) + (this.f2079b ? 1 : 0)) * 31) + (this.f2080c ? 1 : 0)) * 31) + (this.f2081d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + this.f.ordinal()) * 31;
        c cVar = this.g;
        return ordinal + (cVar != null ? cVar.hashCode() : 0);
    }

    public String toString() {
        return String.format(null, "%d-%b-%b-%b-%b-%s-%s", Integer.valueOf(this.f2078a), Boolean.valueOf(this.f2079b), Boolean.valueOf(this.f2080c), Boolean.valueOf(this.f2081d), Boolean.valueOf(this.e), this.f.name(), this.g);
    }
}
