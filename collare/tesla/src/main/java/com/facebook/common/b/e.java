package com.facebook.common.b;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StatefulRunnable */
public abstract class e<T> implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    protected final AtomicInteger f1735a = new AtomicInteger(0);

    /* access modifiers changed from: protected */
    public void a(Exception exc) {
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void b(T t) {
    }

    /* access modifiers changed from: protected */
    public abstract T c();

    public final void run() {
        if (this.f1735a.compareAndSet(0, 1)) {
            try {
                T c2 = c();
                this.f1735a.set(3);
                try {
                    a((Object) c2);
                } finally {
                    b(c2);
                }
            } catch (Exception e) {
                this.f1735a.set(4);
                a(e);
            }
        }
    }

    public void a() {
        if (this.f1735a.compareAndSet(0, 2)) {
            b();
        }
    }
}
