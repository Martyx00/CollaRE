package com.facebook.react.common;

/* compiled from: ClearableSynchronizedPool */
public class a<T> {

    /* renamed from: a  reason: collision with root package name */
    private final Object[] f2603a;

    /* renamed from: b  reason: collision with root package name */
    private int f2604b = 0;

    public a(int i) {
        this.f2603a = new Object[i];
    }

    public synchronized T a() {
        if (this.f2604b == 0) {
            return null;
        }
        this.f2604b--;
        int i = this.f2604b;
        T t = (T) this.f2603a[i];
        this.f2603a[i] = null;
        return t;
    }

    public synchronized boolean a(T t) {
        if (this.f2604b == this.f2603a.length) {
            return false;
        }
        this.f2603a[this.f2604b] = t;
        this.f2604b++;
        return true;
    }

    public synchronized void b() {
        for (int i = 0; i < this.f2604b; i++) {
            this.f2603a[i] = null;
        }
        this.f2604b = 0;
    }
}
