package com.teslamotors.plugins.client.d.a;

/* compiled from: ExponentialBackoffStrategy */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private int f5526a = 100;

    /* renamed from: b  reason: collision with root package name */
    private int f5527b = 1000;

    public int a() {
        return this.f5526a;
    }

    public int b() {
        return this.f5527b;
    }

    public void a(int i) {
        if (i > 0) {
            this.f5526a = i;
            return;
        }
        throw new IllegalArgumentException("Cannot have an initial delay less than 0");
    }

    public void b(int i) {
        if (i > 0) {
            this.f5527b = i;
            return;
        }
        throw new IllegalArgumentException("Cannot have an maximum delay less than 0");
    }
}
