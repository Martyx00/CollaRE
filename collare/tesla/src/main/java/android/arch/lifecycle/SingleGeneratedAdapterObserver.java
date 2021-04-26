package android.arch.lifecycle;

import android.arch.lifecycle.c;

public class SingleGeneratedAdapterObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final b f71a;

    SingleGeneratedAdapterObserver(b bVar) {
        this.f71a = bVar;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(e eVar, c.a aVar) {
        this.f71a.a(eVar, aVar, false, null);
        this.f71a.a(eVar, aVar, true, null);
    }
}
