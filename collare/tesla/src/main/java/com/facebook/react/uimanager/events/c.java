package com.facebook.react.uimanager.events;

import com.facebook.react.common.i;
import com.facebook.react.uimanager.events.c;

/* compiled from: Event */
public abstract class c<T extends c> {

    /* renamed from: a  reason: collision with root package name */
    private static int f3230a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3231b;

    /* renamed from: c  reason: collision with root package name */
    private int f3232c;

    /* renamed from: d  reason: collision with root package name */
    private long f3233d;
    private int e;

    public abstract String a();

    public abstract void a(RCTEventEmitter rCTEventEmitter);

    public boolean b() {
        return true;
    }

    public void c() {
    }

    public short f() {
        return 0;
    }

    protected c() {
        int i = f3230a;
        f3230a = i + 1;
        this.e = i;
    }

    protected c(int i) {
        int i2 = f3230a;
        f3230a = i2 + 1;
        this.e = i2;
        a(i);
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        this.f3232c = i;
        this.f3233d = i.c();
        this.f3231b = true;
    }

    public final int d() {
        return this.f3232c;
    }

    public final long e() {
        return this.f3233d;
    }

    public T a(T t) {
        return e() >= t.e() ? this : t;
    }

    public int g() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public boolean h() {
        return this.f3231b;
    }

    /* access modifiers changed from: package-private */
    public final void i() {
        this.f3231b = false;
        c();
    }
}
