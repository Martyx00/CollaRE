package io.realm;

import io.realm.internal.OsObject;
import io.realm.internal.UncheckedRow;
import io.realm.internal.k;
import io.realm.internal.l;
import io.realm.internal.p;
import io.realm.u;
import java.util.List;

/* compiled from: ProxyState */
public final class n<E extends u> implements l.a {
    private static a i = new a();

    /* renamed from: a  reason: collision with root package name */
    private E f6338a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f6339b = true;

    /* renamed from: c  reason: collision with root package name */
    private p f6340c;

    /* renamed from: d  reason: collision with root package name */
    private OsObject f6341d;
    private a e;
    private boolean f;
    private List<String> g;
    private k<OsObject.b> h = new k<>();

    /* access modifiers changed from: private */
    /* compiled from: ProxyState */
    public static class a implements k.a<OsObject.b> {
        private a() {
        }

        public void a(OsObject.b bVar, Object obj) {
            bVar.a((u) obj, null);
        }
    }

    public n() {
    }

    public n(E e2) {
        this.f6338a = e2;
    }

    public a a() {
        return this.e;
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public p b() {
        return this.f6340c;
    }

    public void a(p pVar) {
        this.f6340c = pVar;
    }

    public boolean c() {
        return this.f;
    }

    public void a(boolean z) {
        this.f = z;
    }

    public void a(List<String> list) {
        this.g = list;
    }

    private void f() {
        this.h.a((k.a<OsObject.b>) i);
    }

    public boolean d() {
        return this.f6339b;
    }

    public void e() {
        this.f6339b = false;
        this.g = null;
    }

    private void g() {
        if (this.e.e != null && !this.e.e.isClosed() && this.f6340c.d() && this.f6341d == null) {
            this.f6341d = new OsObject(this.e.e, (UncheckedRow) this.f6340c);
            this.f6341d.setObserverPairs(this.h);
            this.h = null;
        }
    }

    @Override // io.realm.internal.l.a
    public void b(p pVar) {
        this.f6340c = pVar;
        f();
        if (pVar.d()) {
            g();
        }
    }
}
