package com.google.firebase.components;

import com.google.firebase.b.a;

/* access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public final class q<T> implements a<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final Object f3834a = new Object();

    /* renamed from: b  reason: collision with root package name */
    private volatile Object f3835b = f3834a;

    /* renamed from: c  reason: collision with root package name */
    private volatile a<T> f3836c;

    q(c<T> cVar, b bVar) {
        this.f3836c = r.a(cVar, bVar);
    }

    @Override // com.google.firebase.b.a
    public final T a() {
        T t = (T) this.f3835b;
        if (t == f3834a) {
            synchronized (this) {
                t = this.f3835b;
                if (t == f3834a) {
                    t = this.f3836c.a();
                    this.f3835b = t;
                    this.f3836c = null;
                }
            }
        }
        return t;
    }
}
