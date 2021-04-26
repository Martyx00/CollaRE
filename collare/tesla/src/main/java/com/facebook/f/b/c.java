package com.facebook.f.b;

/* compiled from: RetryManager */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1867a;

    /* renamed from: b  reason: collision with root package name */
    private int f1868b;

    /* renamed from: c  reason: collision with root package name */
    private int f1869c;

    public c() {
        a();
    }

    public void a() {
        this.f1867a = false;
        this.f1868b = 4;
        b();
    }

    public void b() {
        this.f1869c = 0;
    }

    public void a(boolean z) {
        this.f1867a = z;
    }

    public boolean c() {
        return this.f1867a && this.f1869c < this.f1868b;
    }

    public void d() {
        this.f1869c++;
    }
}
