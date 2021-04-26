package io.realm;

import com.teslamotors.plugins.client.b.c;
import io.realm.a;
import io.realm.ac;
import io.realm.annotations.RealmModule;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.n;
import io.realm.internal.o;
import io.realm.internal.p;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RealmModule
class DefaultRealmModuleMediator extends o {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<Class<? extends u>> f6104a;

    @Override // io.realm.internal.o
    public boolean c() {
        return true;
    }

    DefaultRealmModuleMediator() {
    }

    static {
        HashSet hashSet = new HashSet(1);
        hashSet.add(c.class);
        f6104a = Collections.unmodifiableSet(hashSet);
    }

    @Override // io.realm.internal.o
    public Map<Class<? extends u>, OsObjectSchemaInfo> a() {
        HashMap hashMap = new HashMap(1);
        hashMap.put(c.class, ac.e());
        return hashMap;
    }

    @Override // io.realm.internal.o
    public io.realm.internal.c a(Class<? extends u> cls, OsSchemaInfo osSchemaInfo) {
        c(cls);
        if (cls.equals(c.class)) {
            return ac.a(osSchemaInfo);
        }
        throw d(cls);
    }

    @Override // io.realm.internal.o
    public String a(Class<? extends u> cls) {
        c(cls);
        if (cls.equals(c.class)) {
            return "RealmItem";
        }
        throw d(cls);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(Class<E> cls, Object obj, p pVar, io.realm.internal.c cVar, boolean z, List<String> list) {
        a.C0164a aVar = (a.C0164a) a.f.get();
        try {
            aVar.a((a) obj, pVar, cVar, z, list);
            c(cls);
            if (cls.equals(c.class)) {
                return cls.cast(new ac());
            }
            throw d(cls);
        } finally {
            aVar.f();
        }
    }

    @Override // io.realm.internal.o
    public Set<Class<? extends u>> b() {
        return f6104a;
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(o oVar, E e, boolean z, Map<u, n> map, Set<g> set) {
        Class<?> superclass = e instanceof n ? e.getClass().getSuperclass() : e.getClass();
        if (superclass.equals(c.class)) {
            return (E) ((u) superclass.cast(ac.a(oVar, (ac.a) oVar.h().c(c.class), (c) e, z, map, set)));
        }
        throw d(superclass);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(E e, int i, Map<u, n.a<u>> map) {
        Class<? super Object> superclass = e.getClass().getSuperclass();
        if (superclass.equals(c.class)) {
            return (E) ((u) superclass.cast(ac.a((c) e, 0, i, map)));
        }
        throw d(superclass);
    }
}
