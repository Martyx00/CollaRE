package com.facebook.c;

import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.common.d.l;
import java.util.List;

/* compiled from: FirstAvailableDataSourceSupplier */
public class f<T> implements l<c<T>> {

    /* renamed from: a  reason: collision with root package name */
    private final List<l<c<T>>> f1716a;

    private f(List<l<c<T>>> list) {
        i.a(!list.isEmpty(), "List of suppliers is empty!");
        this.f1716a = list;
    }

    public static <T> f<T> a(List<l<c<T>>> list) {
        return new f<>(list);
    }

    /* renamed from: a */
    public c<T> b() {
        return new a();
    }

    public int hashCode() {
        return this.f1716a.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        return h.a(this.f1716a, ((f) obj).f1716a);
    }

    public String toString() {
        return h.a(this).a("list", this.f1716a).toString();
    }

    /* access modifiers changed from: private */
    /* compiled from: FirstAvailableDataSourceSupplier */
    public class a extends a<T> {

        /* renamed from: b  reason: collision with root package name */
        private int f1718b = 0;

        /* renamed from: c  reason: collision with root package name */
        private c<T> f1719c = null;

        /* renamed from: d  reason: collision with root package name */
        private c<T> f1720d = null;

        public a() {
            if (!j()) {
                a((Throwable) new RuntimeException("No data source supplier or supplier returned null."));
            }
        }

        @Override // com.facebook.c.c, com.facebook.c.a
        public synchronized T d() {
            c<T> l;
            l = l();
            return l != null ? l.d() : null;
        }

        @Override // com.facebook.c.c, com.facebook.c.a
        public synchronized boolean c() {
            c<T> l;
            l = l();
            return l != null && l.c();
        }

        @Override // com.facebook.c.c, com.facebook.c.a
        public boolean h() {
            synchronized (this) {
                if (!super.h()) {
                    return false;
                }
                c<T> cVar = this.f1719c;
                this.f1719c = null;
                c<T> cVar2 = this.f1720d;
                this.f1720d = null;
                e(cVar2);
                e(cVar);
                return true;
            }
        }

        private boolean j() {
            l<c<T>> k = k();
            c<T> b2 = k != null ? k.b() : null;
            if (!a((c) b2) || b2 == null) {
                e(b2);
                return false;
            }
            b2.a(new C0039a(), com.facebook.common.b.a.a());
            return true;
        }

        private synchronized l<c<T>> k() {
            if (a() || this.f1718b >= f.this.f1716a.size()) {
                return null;
            }
            List list = f.this.f1716a;
            int i = this.f1718b;
            this.f1718b = i + 1;
            return (l) list.get(i);
        }

        private synchronized boolean a(c<T> cVar) {
            if (a()) {
                return false;
            }
            this.f1719c = cVar;
            return true;
        }

        private synchronized boolean b(c<T> cVar) {
            if (!a()) {
                if (cVar == this.f1719c) {
                    this.f1719c = null;
                    return true;
                }
            }
            return false;
        }

        private synchronized c<T> l() {
            return this.f1720d;
        }

        private void a(c<T> cVar, boolean z) {
            c<T> cVar2;
            synchronized (this) {
                if (cVar == this.f1719c) {
                    if (cVar != this.f1720d) {
                        if (this.f1720d != null) {
                            if (!z) {
                                cVar2 = null;
                                e(cVar2);
                            }
                        }
                        c<T> cVar3 = this.f1720d;
                        this.f1720d = cVar;
                        cVar2 = cVar3;
                        e(cVar2);
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void c(c<T> cVar) {
            if (b(cVar)) {
                if (cVar != l()) {
                    e(cVar);
                }
                if (!j()) {
                    a(cVar.f());
                }
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void d(c<T> cVar) {
            a((c) cVar, cVar.b());
            if (cVar == l()) {
                a((Object) null, cVar.b());
            }
        }

        private void e(c<T> cVar) {
            if (cVar != null) {
                cVar.h();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: com.facebook.c.f$a$a  reason: collision with other inner class name */
        /* compiled from: FirstAvailableDataSourceSupplier */
        public class C0039a implements e<T> {
            @Override // com.facebook.c.e
            public void c(c<T> cVar) {
            }

            private C0039a() {
            }

            @Override // com.facebook.c.e
            public void b(c<T> cVar) {
                a.this.c(cVar);
            }

            @Override // com.facebook.c.e
            public void a(c<T> cVar) {
                if (cVar.c()) {
                    a.this.d(cVar);
                } else if (cVar.b()) {
                    a.this.c(cVar);
                }
            }

            @Override // com.facebook.c.e
            public void d(c<T> cVar) {
                a.this.a(Math.max(a.this.g(), cVar.g()));
            }
        }
    }
}
