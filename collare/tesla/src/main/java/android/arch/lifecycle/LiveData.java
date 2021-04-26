package android.arch.lifecycle;

import android.arch.a.b.b;
import android.arch.lifecycle.c;
import java.util.Map;

public abstract class LiveData<T> {

    /* renamed from: b  reason: collision with root package name */
    private static final Object f60b = new Object();

    /* renamed from: a  reason: collision with root package name */
    private final Object f61a = new Object();

    /* renamed from: c  reason: collision with root package name */
    private b<k<T>, LiveData<T>.a> f62c = new b<>();

    /* renamed from: d  reason: collision with root package name */
    private int f63d = 0;
    private volatile Object e;
    private volatile Object f;
    private int g;
    private boolean h;
    private boolean i;
    private final Runnable j;

    /* access modifiers changed from: protected */
    public void b() {
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    public LiveData() {
        Object obj = f60b;
        this.e = obj;
        this.f = obj;
        this.g = -1;
        this.j = new Runnable() {
            /* class android.arch.lifecycle.LiveData.AnonymousClass1 */

            /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: android.arch.lifecycle.LiveData */
            /* JADX WARN: Multi-variable type inference failed */
            public void run() {
                Object obj;
                synchronized (LiveData.this.f61a) {
                    obj = LiveData.this.f;
                    LiveData.this.f = LiveData.f60b;
                }
                LiveData.this.b(obj);
            }
        };
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: android.arch.lifecycle.k<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private void a(LiveData<T>.a aVar) {
        if (aVar.f68d) {
            if (!aVar.a()) {
                aVar.a(false);
                return;
            }
            int i2 = aVar.e;
            int i3 = this.g;
            if (i2 < i3) {
                aVar.e = i3;
                aVar.f67c.a(this.e);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(LiveData<T>.a aVar) {
        if (this.h) {
            this.i = true;
            return;
        }
        this.h = true;
        do {
            this.i = false;
            if (aVar == null) {
                b<K, V>.d c2 = this.f62c.c();
                while (c2.hasNext()) {
                    a((LiveData<T>.a) ((a) ((Map.Entry) c2.next()).getValue()));
                    if (this.i) {
                        break;
                    }
                }
            } else {
                a(aVar);
                aVar = null;
            }
        } while (this.i);
        this.h = false;
    }

    public void a(e eVar, k<T> kVar) {
        if (eVar.getLifecycle().a() != c.b.DESTROYED) {
            LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(eVar, kVar);
            LiveData<T>.a a2 = this.f62c.a(kVar, lifecycleBoundObserver);
            if (a2 != null && !a2.a(eVar)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (a2 == null) {
                eVar.getLifecycle().a(lifecycleBoundObserver);
            }
        }
    }

    public void a(k<T> kVar) {
        a("removeObserver");
        LiveData<T>.a b2 = this.f62c.b(kVar);
        if (b2 != null) {
            b2.b();
            b2.a(false);
        }
    }

    /* access modifiers changed from: protected */
    public void a(T t) {
        boolean z;
        synchronized (this.f61a) {
            z = this.f == f60b;
            this.f = t;
        }
        if (z) {
            android.arch.a.a.a.a().b(this.j);
        }
    }

    /* access modifiers changed from: protected */
    public void b(T t) {
        a("setValue");
        this.g++;
        this.e = t;
        b((LiveData<T>.a) null);
    }

    public T a() {
        T t = (T) this.e;
        if (t != f60b) {
            return t;
        }
        return null;
    }

    public boolean d() {
        return this.f63d > 0;
    }

    class LifecycleBoundObserver extends LiveData<T>.a implements GenericLifecycleObserver {

        /* renamed from: a  reason: collision with root package name */
        final e f65a;

        LifecycleBoundObserver(e eVar, k<T> kVar) {
            super(kVar);
            this.f65a = eVar;
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.lifecycle.LiveData.a
        public boolean a() {
            return this.f65a.getLifecycle().a().a(c.b.STARTED);
        }

        @Override // android.arch.lifecycle.GenericLifecycleObserver
        public void a(e eVar, c.a aVar) {
            if (this.f65a.getLifecycle().a() == c.b.DESTROYED) {
                LiveData.this.a((k) this.f67c);
            } else {
                a(a());
            }
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.lifecycle.LiveData.a
        public boolean a(e eVar) {
            return this.f65a == eVar;
        }

        /* access modifiers changed from: package-private */
        @Override // android.arch.lifecycle.LiveData.a
        public void b() {
            this.f65a.getLifecycle().b(this);
        }
    }

    /* access modifiers changed from: private */
    public abstract class a {

        /* renamed from: c  reason: collision with root package name */
        final k<T> f67c;

        /* renamed from: d  reason: collision with root package name */
        boolean f68d;
        int e = -1;

        /* access modifiers changed from: package-private */
        public abstract boolean a();

        /* access modifiers changed from: package-private */
        public boolean a(e eVar) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public void b() {
        }

        a(k<T> kVar) {
            this.f67c = kVar;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            if (z != this.f68d) {
                this.f68d = z;
                int i = 1;
                boolean z2 = LiveData.this.f63d == 0;
                LiveData liveData = LiveData.this;
                int i2 = liveData.f63d;
                if (!this.f68d) {
                    i = -1;
                }
                liveData.f63d = i2 + i;
                if (z2 && this.f68d) {
                    LiveData.this.b();
                }
                if (LiveData.this.f63d == 0 && !this.f68d) {
                    LiveData.this.c();
                }
                if (this.f68d) {
                    LiveData.this.b((LiveData) this);
                }
            }
        }
    }

    private static void a(String str) {
        if (!android.arch.a.a.a.a().b()) {
            throw new IllegalStateException("Cannot invoke " + str + " on a background" + " thread");
        }
    }
}
