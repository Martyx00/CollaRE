package com.facebook.imagepipeline.n;

import com.facebook.common.e.a;

/* compiled from: BaseConsumer */
public abstract class b<T> implements k<T> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2355a = false;

    public static int a(int i, int i2) {
        return i & (~i2);
    }

    public static int a(boolean z) {
        return z ? 1 : 0;
    }

    public static boolean a(int i) {
        return (i & 1) == 1;
    }

    public static boolean b(int i, int i2) {
        return (i & i2) == i2;
    }

    public static boolean c(int i, int i2) {
        return (i & i2) != 0;
    }

    /* access modifiers changed from: protected */
    public abstract void a();

    /* access modifiers changed from: protected */
    public void a(float f) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(T t, int i);

    /* access modifiers changed from: protected */
    public abstract void a(Throwable th);

    public static boolean b(int i) {
        return !a(i);
    }

    @Override // com.facebook.imagepipeline.n.k
    public synchronized void b(T t, int i) {
        if (!this.f2355a) {
            this.f2355a = a(i);
            try {
                a(t, i);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.k
    public synchronized void b(Throwable th) {
        if (!this.f2355a) {
            this.f2355a = true;
            try {
                a(th);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.k
    public synchronized void b() {
        if (!this.f2355a) {
            this.f2355a = true;
            try {
                a();
            } catch (Exception e) {
                a(e);
            }
        }
    }

    @Override // com.facebook.imagepipeline.n.k
    public synchronized void b(float f) {
        if (!this.f2355a) {
            try {
                a(f);
            } catch (Exception e) {
                a(e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(Exception exc) {
        a.c(getClass(), "unhandled exception", exc);
    }
}
