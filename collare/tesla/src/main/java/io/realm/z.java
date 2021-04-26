package io.realm;

import io.realm.internal.Table;
import io.realm.internal.Util;
import io.realm.internal.b;
import io.realm.internal.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: RealmSchema */
public abstract class z {

    /* renamed from: a  reason: collision with root package name */
    final a f6380a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, Table> f6381b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    private final Map<Class<? extends u>, Table> f6382c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    private final Map<Class<? extends u>, x> f6383d = new HashMap();
    private final Map<String, x> e = new HashMap();
    private final b f;

    z(a aVar, b bVar) {
        this.f6380a = aVar;
        this.f = bVar;
    }

    /* access modifiers changed from: package-private */
    public Table a(String str) {
        String c2 = Table.c(str);
        Table table = this.f6381b.get(c2);
        if (table != null) {
            return table;
        }
        Table table2 = this.f6380a.i().getTable(c2);
        this.f6381b.put(c2, table2);
        return table2;
    }

    /* access modifiers changed from: package-private */
    public Table a(Class<? extends u> cls) {
        Table table = this.f6382c.get(cls);
        if (table != null) {
            return table;
        }
        Class<? extends u> a2 = Util.a(cls);
        if (a(a2, cls)) {
            table = this.f6382c.get(a2);
        }
        if (table == null) {
            table = this.f6380a.i().getTable(Table.c(this.f6380a.f().h().b(a2)));
            this.f6382c.put(a2, table);
        }
        if (a(a2, cls)) {
            this.f6382c.put(cls, table);
        }
        return table;
    }

    /* access modifiers changed from: package-private */
    public x b(Class<? extends u> cls) {
        x xVar = this.f6383d.get(cls);
        if (xVar != null) {
            return xVar;
        }
        Class<? extends u> a2 = Util.a(cls);
        if (a(a2, cls)) {
            xVar = this.f6383d.get(a2);
        }
        if (xVar == null) {
            e eVar = new e(this.f6380a, this, a(cls), c(a2));
            this.f6383d.put(a2, eVar);
            xVar = eVar;
        }
        if (a(a2, cls)) {
            this.f6383d.put(cls, xVar);
        }
        return xVar;
    }

    /* access modifiers changed from: package-private */
    public x b(String str) {
        String c2 = Table.c(str);
        x xVar = this.e.get(c2);
        if (xVar != null && xVar.b().a() && xVar.a().equals(str)) {
            return xVar;
        }
        if (this.f6380a.i().hasTable(c2)) {
            a aVar = this.f6380a;
            e eVar = new e(aVar, this, aVar.i().getTable(c2));
            this.e.put(c2, eVar);
            return eVar;
        }
        throw new IllegalArgumentException("The class " + str + " doesn't exist in this Realm.");
    }

    private boolean a(Class<? extends u> cls, Class<? extends u> cls2) {
        return cls.equals(cls2);
    }

    /* access modifiers changed from: package-private */
    public final boolean a() {
        return this.f != null;
    }

    /* access modifiers changed from: package-private */
    public final c c(Class<? extends u> cls) {
        c();
        return this.f.a(cls);
    }

    /* access modifiers changed from: protected */
    public final c c(String str) {
        c();
        return this.f.a(str);
    }

    private void c() {
        if (!a()) {
            throw new IllegalStateException("Attempt to use column index before set.");
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        b bVar = this.f;
        if (bVar != null) {
            bVar.a();
        }
        this.f6381b.clear();
        this.f6382c.clear();
        this.f6383d.clear();
        this.e.clear();
    }
}
