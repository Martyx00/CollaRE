package android.arch.lifecycle;

import android.arch.lifecycle.c;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final FullLifecycleObserver f58a;

    FullLifecycleObserverAdapter(FullLifecycleObserver fullLifecycleObserver) {
        this.f58a = fullLifecycleObserver;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(e eVar, c.a aVar) {
        switch (aVar) {
            case ON_CREATE:
                this.f58a.a(eVar);
                return;
            case ON_START:
                this.f58a.b(eVar);
                return;
            case ON_RESUME:
                this.f58a.c(eVar);
                return;
            case ON_PAUSE:
                this.f58a.d(eVar);
                return;
            case ON_STOP:
                this.f58a.e(eVar);
                return;
            case ON_DESTROY:
                this.f58a.f(eVar);
                return;
            case ON_ANY:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                return;
        }
    }
}
