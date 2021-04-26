package android.arch.lifecycle;

import android.arch.lifecycle.a;
import android.arch.lifecycle.c;

class ReflectiveGenericLifecycleObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final Object f69a;

    /* renamed from: b  reason: collision with root package name */
    private final a.C0002a f70b = a.f72a.b(this.f69a.getClass());

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f69a = obj;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(e eVar, c.a aVar) {
        this.f70b.a(eVar, aVar, this.f69a);
    }
}
