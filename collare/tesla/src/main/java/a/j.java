package a;

import a.h;

/* access modifiers changed from: package-private */
/* compiled from: UnobservedErrorNotifier */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private h<?> f35a;

    public j(h<?> hVar) {
        this.f35a = hVar;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        h.a a2;
        try {
            h<?> hVar = this.f35a;
            if (!(hVar == null || (a2 = h.a()) == null)) {
                a2.a(hVar, new k(hVar.f()));
            }
        } finally {
            super.finalize();
        }
    }

    public void a() {
        this.f35a = null;
    }
}
