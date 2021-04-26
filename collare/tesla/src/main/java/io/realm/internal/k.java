package io.realm.internal;

import io.realm.internal.k.b;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: ObserverPairList */
public class k<T extends b> {

    /* renamed from: a  reason: collision with root package name */
    private List<T> f6286a = new CopyOnWriteArrayList();

    /* renamed from: b  reason: collision with root package name */
    private boolean f6287b = false;

    /* compiled from: ObserverPairList */
    public interface a<T extends b> {
        void a(T t, Object obj);
    }

    /* compiled from: ObserverPairList */
    public static abstract class b<T, S> {

        /* renamed from: a  reason: collision with root package name */
        final WeakReference<T> f6288a;

        /* renamed from: b  reason: collision with root package name */
        protected final S f6289b;

        /* renamed from: c  reason: collision with root package name */
        boolean f6290c = false;

        public b(T t, S s) {
            this.f6289b = s;
            this.f6288a = new WeakReference<>(t);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            if (!this.f6289b.equals(bVar.f6289b) || this.f6288a.get() != bVar.f6288a.get()) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            T t = this.f6288a.get();
            int i = 0;
            int hashCode = (527 + (t != null ? t.hashCode() : 0)) * 31;
            S s = this.f6289b;
            if (s != null) {
                i = s.hashCode();
            }
            return hashCode + i;
        }
    }

    public void a(a<T> aVar) {
        for (T t : this.f6286a) {
            if (!this.f6287b) {
                T t2 = t.f6288a.get();
                if (t2 == null) {
                    this.f6286a.remove(t);
                } else if (!t.f6290c) {
                    aVar.a(t, t2);
                }
            } else {
                return;
            }
        }
    }

    public boolean a() {
        return this.f6286a.isEmpty();
    }

    public void b() {
        this.f6287b = true;
        this.f6286a.clear();
    }

    public void a(T t) {
        if (!this.f6286a.contains(t)) {
            this.f6286a.add(t);
            t.f6290c = false;
        }
        if (this.f6287b) {
            this.f6287b = false;
        }
    }

    public <S, U> void a(S s, U u) {
        for (T t : this.f6286a) {
            if (s == t.f6288a.get() && u.equals(t.f6289b)) {
                t.f6290c = true;
                this.f6286a.remove(t);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj) {
        for (T t : this.f6286a) {
            T t2 = t.f6288a.get();
            if (t2 == null || t2 == obj) {
                t.f6290c = true;
                this.f6286a.remove(t);
            }
        }
    }

    public int c() {
        return this.f6286a.size();
    }
}
