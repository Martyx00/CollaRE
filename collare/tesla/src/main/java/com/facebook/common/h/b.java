package com.facebook.common.h;

import java.lang.ref.SoftReference;

/* compiled from: OOMSoftReference */
public class b<T> {

    /* renamed from: a  reason: collision with root package name */
    SoftReference<T> f1768a = null;

    /* renamed from: b  reason: collision with root package name */
    SoftReference<T> f1769b = null;

    /* renamed from: c  reason: collision with root package name */
    SoftReference<T> f1770c = null;

    public void a(T t) {
        this.f1768a = new SoftReference<>(t);
        this.f1769b = new SoftReference<>(t);
        this.f1770c = new SoftReference<>(t);
    }

    public T a() {
        SoftReference<T> softReference = this.f1768a;
        if (softReference == null) {
            return null;
        }
        return softReference.get();
    }

    public void b() {
        SoftReference<T> softReference = this.f1768a;
        if (softReference != null) {
            softReference.clear();
            this.f1768a = null;
        }
        SoftReference<T> softReference2 = this.f1769b;
        if (softReference2 != null) {
            softReference2.clear();
            this.f1769b = null;
        }
        SoftReference<T> softReference3 = this.f1770c;
        if (softReference3 != null) {
            softReference3.clear();
            this.f1770c = null;
        }
    }
}
