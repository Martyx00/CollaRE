package io.realm.internal;

import io.realm.internal.k;
import io.realm.l;
import io.realm.q;

@Keep
interface ObservableCollection {
    void notifyChangeListeners(long j);

    public static class b<T> extends k.b<T, Object> {
        public void a(T t, OsCollectionChangeSet osCollectionChangeSet) {
            if (this.f6289b instanceof l) {
                ((l) this.f6289b).a(t, new q(osCollectionChangeSet));
            } else if (this.f6289b instanceof q) {
                ((q) this.f6289b).a(t);
            } else {
                throw new RuntimeException("Unsupported listener type: " + this.f6289b);
            }
        }
    }

    public static class c<T> implements l<T> {

        /* renamed from: a  reason: collision with root package name */
        private final q<T> f6180a;

        c(q<T> qVar) {
            this.f6180a = qVar;
        }

        @Override // io.realm.l
        public void a(T t, io.realm.k kVar) {
            this.f6180a.a(t);
        }

        public boolean equals(Object obj) {
            return (obj instanceof c) && this.f6180a == ((c) obj).f6180a;
        }

        public int hashCode() {
            return this.f6180a.hashCode();
        }
    }

    public static class a implements k.a<b> {

        /* renamed from: a  reason: collision with root package name */
        private final OsCollectionChangeSet f6179a;

        a(OsCollectionChangeSet osCollectionChangeSet) {
            this.f6179a = osCollectionChangeSet;
        }

        public void a(b bVar, Object obj) {
            bVar.a(obj, this.f6179a);
        }
    }
}
