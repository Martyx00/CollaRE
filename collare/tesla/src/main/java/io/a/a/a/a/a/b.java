package io.a.a.a.a.a;

import android.content.Context;

/* compiled from: MemoryValueCache */
public class b<T> extends a<T> {

    /* renamed from: a  reason: collision with root package name */
    private T f5877a;

    public b() {
        this(null);
    }

    public b(c<T> cVar) {
        super(cVar);
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.a.a
    public T a(Context context) {
        return this.f5877a;
    }

    /* access modifiers changed from: protected */
    @Override // io.a.a.a.a.a.a
    public void a(Context context, T t) {
        this.f5877a = t;
    }
}
