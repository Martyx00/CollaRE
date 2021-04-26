package android.arch.lifecycle;

import android.arch.lifecycle.c;

public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private final b[] f57a;

    CompositeGeneratedAdaptersObserver(b[] bVarArr) {
        this.f57a = bVarArr;
    }

    @Override // android.arch.lifecycle.GenericLifecycleObserver
    public void a(e eVar, c.a aVar) {
        i iVar = new i();
        for (b bVar : this.f57a) {
            bVar.a(eVar, aVar, false, iVar);
        }
        for (b bVar2 : this.f57a) {
            bVar2.a(eVar, aVar, true, iVar);
        }
    }
}
