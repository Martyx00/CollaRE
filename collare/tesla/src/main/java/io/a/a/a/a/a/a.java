package io.a.a.a.a.a;

import android.content.Context;

/* compiled from: AbstractValueCache */
public abstract class a<T> implements c<T> {

    /* renamed from: a  reason: collision with root package name */
    private final c<T> f5876a;

    /* access modifiers changed from: protected */
    public abstract T a(Context context);

    /* access modifiers changed from: protected */
    public abstract void a(Context context, T t);

    public a(c<T> cVar) {
        this.f5876a = cVar;
    }

    @Override // io.a.a.a.a.a.c
    public final synchronized T a(Context context, d<T> dVar) {
        T a2;
        a2 = a(context);
        if (a2 == null) {
            a2 = this.f5876a != null ? this.f5876a.a(context, dVar) : dVar.load(context);
            b(context, a2);
        }
        return a2;
    }

    private void b(Context context, T t) {
        if (t != null) {
            a(context, (Object) t);
            return;
        }
        throw new NullPointerException();
    }
}
