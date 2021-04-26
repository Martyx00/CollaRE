package com.facebook.imagepipeline.n;

import android.net.Uri;
import com.facebook.imagepipeline.e.a;
import com.facebook.imagepipeline.j.d;

/* compiled from: FetchState */
public class t {

    /* renamed from: a  reason: collision with root package name */
    private final k<d> f2421a;

    /* renamed from: b  reason: collision with root package name */
    private final al f2422b;

    /* renamed from: c  reason: collision with root package name */
    private long f2423c = 0;

    /* renamed from: d  reason: collision with root package name */
    private int f2424d;
    private a e;

    public t(k<d> kVar, al alVar) {
        this.f2421a = kVar;
        this.f2422b = alVar;
    }

    public k<d> a() {
        return this.f2421a;
    }

    public al b() {
        return this.f2422b;
    }

    public String c() {
        return this.f2422b.b();
    }

    public an d() {
        return this.f2422b.c();
    }

    public Uri e() {
        return this.f2422b.a().b();
    }

    public long f() {
        return this.f2423c;
    }

    public void a(long j) {
        this.f2423c = j;
    }

    public int g() {
        return this.f2424d;
    }

    public void a(int i) {
        this.f2424d = i;
    }

    public a h() {
        return this.e;
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
