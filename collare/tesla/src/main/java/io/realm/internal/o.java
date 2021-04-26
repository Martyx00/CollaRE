package io.realm.internal;

import io.realm.exceptions.RealmException;
import io.realm.g;
import io.realm.internal.n;
import io.realm.u;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: RealmProxyMediator */
public abstract class o {
    public abstract c a(Class<? extends u> cls, OsSchemaInfo osSchemaInfo);

    public abstract <E extends u> E a(io.realm.o oVar, E e, boolean z, Map<u, n> map, Set<g> set);

    public abstract <E extends u> E a(E e, int i, Map<u, n.a<u>> map);

    public abstract <E extends u> E a(Class<E> cls, Object obj, p pVar, c cVar, boolean z, List<String> list);

    /* access modifiers changed from: protected */
    public abstract String a(Class<? extends u> cls);

    public abstract Map<Class<? extends u>, OsObjectSchemaInfo> a();

    public abstract Set<Class<? extends u>> b();

    public boolean c() {
        return false;
    }

    public final String b(Class<? extends u> cls) {
        return a(Util.a(cls));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof o)) {
            return false;
        }
        return b().equals(((o) obj).b());
    }

    public int hashCode() {
        return b().hashCode();
    }

    protected static void c(Class<? extends u> cls) {
        if (cls == null) {
            throw new NullPointerException("A class extending RealmObject must be provided");
        }
    }

    protected static RealmException d(Class<? extends u> cls) {
        return new RealmException(String.format("'%s' is not part of the schema for this Realm.", cls.toString()));
    }
}
