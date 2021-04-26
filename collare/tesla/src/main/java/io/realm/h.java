package io.realm;

import com.google.android.gms.common.api.Api;
import io.realm.internal.OsList;

/* access modifiers changed from: package-private */
/* compiled from: RealmList */
public abstract class h<T> {

    /* renamed from: a  reason: collision with root package name */
    final OsList f6172a;

    public abstract T a(int i);

    /* access modifiers changed from: protected */
    public abstract void a(Object obj);

    /* access modifiers changed from: protected */
    public abstract void b(int i, Object obj);

    /* access modifiers changed from: protected */
    public abstract void c(Object obj);

    /* access modifiers changed from: protected */
    public abstract void d(int i, Object obj);

    public final boolean a() {
        return this.f6172a.d();
    }

    public final int b() {
        long c2 = this.f6172a.c();
        return c2 < 2147483647L ? (int) c2 : Api.BaseClientBuilder.API_PRIORITY_OTHER;
    }

    public final void b(Object obj) {
        a(obj);
        if (obj == null) {
            d();
        } else {
            c(obj);
        }
    }

    private void d() {
        this.f6172a.a();
    }

    public final void a(int i, Object obj) {
        a(obj);
        if (obj == null) {
            b(i);
        } else {
            b(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i) {
        this.f6172a.a((long) i);
    }

    public final T c(int i, Object obj) {
        a(obj);
        T a2 = a(i);
        if (obj == null) {
            c(i);
        } else {
            d(i, obj);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public void c(int i) {
        this.f6172a.b((long) i);
    }

    /* access modifiers changed from: package-private */
    public final void d(int i) {
        this.f6172a.c((long) i);
    }

    /* access modifiers changed from: package-private */
    public final void c() {
        this.f6172a.b();
    }
}
