package io.realm.internal.b;

import io.realm.exceptions.RealmException;
import io.realm.g;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Util;
import io.realm.internal.c;
import io.realm.internal.n;
import io.realm.internal.o;
import io.realm.internal.p;
import io.realm.u;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: CompositeMediator */
public class a extends o {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<? extends u>, o> f6261a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, Class<? extends u>> f6262b = new HashMap();

    public a(o... oVarArr) {
        HashMap hashMap = new HashMap();
        if (oVarArr != null) {
            for (o oVar : oVarArr) {
                for (Class<? extends u> cls : oVar.b()) {
                    String b2 = oVar.b(cls);
                    Class<? extends u> cls2 = this.f6262b.get(b2);
                    if (cls2 == null || cls2.equals(cls)) {
                        hashMap.put(cls, oVar);
                        this.f6262b.put(b2, cls);
                    } else {
                        throw new IllegalStateException(String.format("It is not allowed for two different model classes to share the same internal name in Realm. The classes %s and %s are being included from the modules '%s' and '%s' and they share the same internal name '%s'.", cls2, cls, hashMap.get(cls2), oVar, b2));
                    }
                }
            }
        }
        this.f6261a = Collections.unmodifiableMap(hashMap);
    }

    @Override // io.realm.internal.o
    public Map<Class<? extends u>, OsObjectSchemaInfo> a() {
        HashMap hashMap = new HashMap();
        for (o oVar : this.f6261a.values()) {
            hashMap.putAll(oVar.a());
        }
        return hashMap;
    }

    @Override // io.realm.internal.o
    public c a(Class<? extends u> cls, OsSchemaInfo osSchemaInfo) {
        return e(cls).a(cls, osSchemaInfo);
    }

    /* access modifiers changed from: protected */
    @Override // io.realm.internal.o
    public String a(Class<? extends u> cls) {
        return e(cls).b(cls);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(Class<E> cls, Object obj, p pVar, c cVar, boolean z, List<String> list) {
        return (E) e(cls).a(cls, obj, pVar, cVar, z, list);
    }

    @Override // io.realm.internal.o
    public Set<Class<? extends u>> b() {
        return this.f6261a.keySet();
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(io.realm.o oVar, E e, boolean z, Map<u, n> map, Set<g> set) {
        return (E) e(Util.a((Class<? extends u>) e.getClass())).a(oVar, e, z, map, set);
    }

    @Override // io.realm.internal.o
    public <E extends u> E a(E e, int i, Map<u, n.a<u>> map) {
        return (E) e(Util.a((Class<? extends u>) e.getClass())).a(e, i, map);
    }

    @Override // io.realm.internal.o
    public boolean c() {
        for (Map.Entry<Class<? extends u>, o> entry : this.f6261a.entrySet()) {
            if (!entry.getValue().c()) {
                return false;
            }
        }
        return true;
    }

    private o e(Class<? extends u> cls) {
        o oVar = this.f6261a.get(cls);
        if (oVar != null) {
            return oVar;
        }
        throw new RealmException(cls.getSimpleName() + " is not part of the schema for this Realm");
    }
}
