package io.realm.internal.b;

import io.realm.g;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Util;
import io.realm.internal.c;
import io.realm.internal.n;
import io.realm.internal.o;
import io.realm.internal.p;
import io.realm.u;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: FilterableMediator */
public class b extends o {

    /* renamed from: a  reason: collision with root package name */
    private final o f6263a;

    /* renamed from: b  reason: collision with root package name */
    private final Set<Class<? extends u>> f6264b;

    public b(o oVar, Collection<Class<? extends u>> collection) {
        this.f6263a = oVar;
        HashSet hashSet = new HashSet();
        if (oVar != null) {
            Set<Class<? extends u>> b2 = oVar.b();
            for (Class<? extends u> cls : collection) {
                if (b2.contains(cls)) {
                    hashSet.add(cls);
                }
            }
        }
        this.f6264b = Collections.unmodifiableSet(hashSet);
    }

    @Override // io.realm.internal.o
    public Map<Class<? extends u>, OsObjectSchemaInfo> a() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Class<? extends u>, OsObjectSchemaInfo> entry : this.f6263a.a().entrySet()) {
            if (this.f6264b.contains(entry.getKey())) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    @Override // io.realm.internal.o
    public c a(Class<? extends u> cls, OsSchemaInfo osSchemaInfo) {
        e(cls);
        return this.f6263a.a(cls, osSchemaInfo);
    }

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.o
    public String a(Class<? extends u> cls) {
        e(cls);
        return this.f6263a.b(cls);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(Class<E> cls, Object obj, p pVar, c cVar, boolean z, List<String> list) {
        e(cls);
        return (E) this.f6263a.a(cls, obj, pVar, cVar, z, list);
    }

    @Override // io.realm.internal.o
    public Set<Class<? extends u>> b() {
        return this.f6264b;
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(io.realm.o oVar, E e, boolean z, Map<u, n> map, Set<g> set) {
        e(Util.a((Class<? extends u>) e.getClass()));
        return (E) this.f6263a.a(oVar, e, z, map, set);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(E e, int i, Map<u, n.a<u>> map) {
        e(Util.a((Class<? extends u>) e.getClass()));
        return (E) this.f6263a.a(e, i, map);
    }

    @Override // io.realm.internal.o
    public boolean c() {
        o oVar = this.f6263a;
        if (oVar == null) {
            return true;
        }
        return oVar.c();
    }

    private void e(Class<? extends u> cls) {
        if (!this.f6264b.contains(cls)) {
            throw new IllegalArgumentException(cls.getSimpleName() + " is not part of the schema for this Realm");
        }
    }
}
