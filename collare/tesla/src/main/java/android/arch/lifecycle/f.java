package android.arch.lifecycle;

import android.arch.a.b.b;
import android.arch.lifecycle.c;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* compiled from: LifecycleRegistry */
public class f extends c {

    /* renamed from: a  reason: collision with root package name */
    private android.arch.a.b.a<d, a> f83a = new android.arch.a.b.a<>();

    /* renamed from: b  reason: collision with root package name */
    private c.b f84b;

    /* renamed from: c  reason: collision with root package name */
    private final WeakReference<e> f85c;

    /* renamed from: d  reason: collision with root package name */
    private int f86d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<c.b> g = new ArrayList<>();

    public f(e eVar) {
        this.f85c = new WeakReference<>(eVar);
        this.f84b = c.b.INITIALIZED;
    }

    public void a(c.b bVar) {
        b(bVar);
    }

    public void a(c.a aVar) {
        b(b(aVar));
    }

    private void b(c.b bVar) {
        if (this.f84b != bVar) {
            this.f84b = bVar;
            if (this.e || this.f86d != 0) {
                this.f = true;
                return;
            }
            this.e = true;
            d();
            this.e = false;
        }
    }

    private boolean b() {
        if (this.f83a.a() == 0) {
            return true;
        }
        c.b bVar = this.f83a.d().getValue().f89a;
        c.b bVar2 = this.f83a.e().getValue().f89a;
        if (bVar == bVar2 && this.f84b == bVar2) {
            return true;
        }
        return false;
    }

    private c.b c(d dVar) {
        Map.Entry<d, a> d2 = this.f83a.d(dVar);
        c.b bVar = null;
        c.b bVar2 = d2 != null ? d2.getValue().f89a : null;
        if (!this.g.isEmpty()) {
            ArrayList<c.b> arrayList = this.g;
            bVar = arrayList.get(arrayList.size() - 1);
        }
        return a(a(this.f84b, bVar2), bVar);
    }

    @Override // android.arch.lifecycle.c
    public void a(d dVar) {
        e eVar;
        a aVar = new a(dVar, this.f84b == c.b.DESTROYED ? c.b.DESTROYED : c.b.INITIALIZED);
        if (this.f83a.a(dVar, aVar) == null && (eVar = this.f85c.get()) != null) {
            boolean z = this.f86d != 0 || this.e;
            c.b c2 = c(dVar);
            this.f86d++;
            while (aVar.f89a.compareTo((Enum) c2) < 0 && this.f83a.c(dVar)) {
                c(aVar.f89a);
                aVar.a(eVar, e(aVar.f89a));
                c();
                c2 = c(dVar);
            }
            if (!z) {
                d();
            }
            this.f86d--;
        }
    }

    private void c() {
        ArrayList<c.b> arrayList = this.g;
        arrayList.remove(arrayList.size() - 1);
    }

    private void c(c.b bVar) {
        this.g.add(bVar);
    }

    @Override // android.arch.lifecycle.c
    public void b(d dVar) {
        this.f83a.b(dVar);
    }

    @Override // android.arch.lifecycle.c
    public c.b a() {
        return this.f84b;
    }

    static c.b b(c.a aVar) {
        switch (aVar) {
            case ON_CREATE:
            case ON_STOP:
                return c.b.CREATED;
            case ON_START:
            case ON_PAUSE:
                return c.b.STARTED;
            case ON_RESUME:
                return c.b.RESUMED;
            case ON_DESTROY:
                return c.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private static c.a d(c.b bVar) {
        switch (bVar) {
            case INITIALIZED:
                throw new IllegalArgumentException();
            case CREATED:
                return c.a.ON_DESTROY;
            case STARTED:
                return c.a.ON_STOP;
            case RESUMED:
                return c.a.ON_PAUSE;
            case DESTROYED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
    }

    private static c.a e(c.b bVar) {
        switch (bVar) {
            case INITIALIZED:
            case DESTROYED:
                return c.a.ON_CREATE;
            case CREATED:
                return c.a.ON_START;
            case STARTED:
                return c.a.ON_RESUME;
            case RESUMED:
                throw new IllegalArgumentException();
            default:
                throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v3, resolved type: android.arch.a.b.a<android.arch.lifecycle.d, android.arch.lifecycle.f$a> */
    /* JADX WARN: Multi-variable type inference failed */
    private void a(e eVar) {
        b<K, V>.d c2 = this.f83a.c();
        while (c2.hasNext() && !this.f) {
            Map.Entry entry = (Map.Entry) c2.next();
            a aVar = (a) entry.getValue();
            while (aVar.f89a.compareTo((Enum) this.f84b) < 0 && !this.f && this.f83a.c(entry.getKey())) {
                c(aVar.f89a);
                aVar.a(eVar, e(aVar.f89a));
                c();
            }
        }
    }

    private void b(e eVar) {
        Iterator<Map.Entry<d, a>> b2 = this.f83a.b();
        while (b2.hasNext() && !this.f) {
            Map.Entry<d, a> next = b2.next();
            a value = next.getValue();
            while (value.f89a.compareTo((Enum) this.f84b) > 0 && !this.f && this.f83a.c(next.getKey())) {
                c.a d2 = d(value.f89a);
                c(b(d2));
                value.a(eVar, d2);
                c();
            }
        }
    }

    private void d() {
        e eVar = this.f85c.get();
        if (eVar == null) {
            Log.w("LifecycleRegistry", "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!b()) {
            this.f = false;
            if (this.f84b.compareTo((Enum) this.f83a.d().getValue().f89a) < 0) {
                b(eVar);
            }
            Map.Entry<d, a> e2 = this.f83a.e();
            if (!this.f && e2 != null && this.f84b.compareTo((Enum) e2.getValue().f89a) > 0) {
                a(eVar);
            }
        }
        this.f = false;
    }

    static c.b a(c.b bVar, c.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    /* access modifiers changed from: package-private */
    /* compiled from: LifecycleRegistry */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        c.b f89a;

        /* renamed from: b  reason: collision with root package name */
        GenericLifecycleObserver f90b;

        a(d dVar, c.b bVar) {
            this.f90b = h.a(dVar);
            this.f89a = bVar;
        }

        /* access modifiers changed from: package-private */
        public void a(e eVar, c.a aVar) {
            c.b b2 = f.b(aVar);
            this.f89a = f.a(this.f89a, b2);
            this.f90b.a(eVar, aVar);
            this.f89a = b2;
        }
    }
}
