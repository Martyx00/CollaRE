package io.realm;

import com.teslamotors.plugins.client.b.c;
import io.realm.a;
import io.realm.exceptions.RealmException;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Table;
import io.realm.internal.n;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.internal.p;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* compiled from: com_teslamotors_plugins_client_data_RealmItemRealmProxy */
public class ac extends c implements ad, n {

    /* renamed from: a  reason: collision with root package name */
    private static final OsObjectSchemaInfo f6136a = f();

    /* renamed from: b  reason: collision with root package name */
    private a f6137b;

    /* renamed from: c  reason: collision with root package name */
    private n<c> f6138c;

    /* access modifiers changed from: package-private */
    /* compiled from: com_teslamotors_plugins_client_data_RealmItemRealmProxy */
    public static final class a extends io.realm.internal.c {

        /* renamed from: a  reason: collision with root package name */
        long f6139a;

        /* renamed from: b  reason: collision with root package name */
        long f6140b;

        /* renamed from: c  reason: collision with root package name */
        long f6141c;

        a(OsSchemaInfo osSchemaInfo) {
            super(2);
            OsObjectSchemaInfo a2 = osSchemaInfo.a("RealmItem");
            this.f6140b = a("async_key", "async_key", a2);
            this.f6141c = a("async_value", "async_value", a2);
            this.f6139a = a2.b();
        }

        /* access modifiers changed from: protected */
        @Override // io.realm.internal.c
        public final void a(io.realm.internal.c cVar, io.realm.internal.c cVar2) {
            a aVar = (a) cVar;
            a aVar2 = (a) cVar2;
            aVar2.f6140b = aVar.f6140b;
            aVar2.f6141c = aVar.f6141c;
            aVar2.f6139a = aVar.f6139a;
        }
    }

    ac() {
        this.f6138c.e();
    }

    @Override // io.realm.internal.n
    public void b_() {
        if (this.f6138c == null) {
            a.C0164a aVar = (a.C0164a) a.f.get();
            this.f6137b = (a) aVar.c();
            this.f6138c = new n<>(this);
            this.f6138c.a(aVar.a());
            this.f6138c.a(aVar.b());
            this.f6138c.a(aVar.d());
            this.f6138c.a(aVar.e());
        }
    }

    @Override // io.realm.ad, com.teslamotors.plugins.client.b.c
    public String c() {
        this.f6138c.a().d();
        return this.f6138c.b().l(this.f6137b.f6140b);
    }

    @Override // io.realm.ad, com.teslamotors.plugins.client.b.c
    public void c(String str) {
        if (!this.f6138c.d()) {
            this.f6138c.a().d();
            throw new RealmException("Primary key field 'async_key' cannot be changed after object was created.");
        }
    }

    @Override // io.realm.ad, com.teslamotors.plugins.client.b.c
    public String d() {
        this.f6138c.a().d();
        return this.f6138c.b().l(this.f6137b.f6141c);
    }

    @Override // io.realm.ad, com.teslamotors.plugins.client.b.c
    public void d(String str) {
        if (!this.f6138c.d()) {
            this.f6138c.a().d();
            if (str == null) {
                this.f6138c.b().c(this.f6137b.f6141c);
            } else {
                this.f6138c.b().a(this.f6137b.f6141c, str);
            }
        } else if (this.f6138c.c()) {
            p b2 = this.f6138c.b();
            if (str == null) {
                b2.b().a(this.f6137b.f6141c, b2.c(), true);
            } else {
                b2.b().a(this.f6137b.f6141c, b2.c(), str, true);
            }
        }
    }

    private static OsObjectSchemaInfo f() {
        OsObjectSchemaInfo.a aVar = new OsObjectSchemaInfo.a("RealmItem", 2, 0);
        aVar.a("async_key", RealmFieldType.STRING, true, true, false);
        aVar.a("async_value", RealmFieldType.STRING, false, false, false);
        return aVar.a();
    }

    public static OsObjectSchemaInfo e() {
        return f6136a;
    }

    public static a a(OsSchemaInfo osSchemaInfo) {
        return new a(osSchemaInfo);
    }

    private static ac a(a aVar, p pVar) {
        a.C0164a aVar2 = (a.C0164a) a.f.get();
        aVar2.a(aVar, pVar, aVar.h().c(c.class), false, Collections.emptyList());
        ac acVar = new ac();
        aVar2.f();
        return acVar;
    }

    /* JADX INFO: finally extract failed */
    public static c a(o oVar, a aVar, c cVar, boolean z, Map<u, n> map, Set<g> set) {
        ac acVar;
        boolean z2;
        long j;
        if (cVar instanceof n) {
            n nVar = (n) cVar;
            if (nVar.c_().a() != null) {
                a a2 = nVar.c_().a();
                if (a2.f6111c != oVar.f6111c) {
                    throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
                } else if (a2.e().equals(oVar.e())) {
                    return cVar;
                }
            }
        }
        a.C0164a aVar2 = (a.C0164a) a.f.get();
        n nVar2 = map.get(cVar);
        if (nVar2 != null) {
            return (c) nVar2;
        }
        if (z) {
            Table b2 = oVar.b(c.class);
            long j2 = aVar.f6140b;
            String c2 = cVar.c();
            if (c2 == null) {
                j = b2.f(j2);
            } else {
                j = b2.a(j2, c2);
            }
            if (j == -1) {
                z2 = false;
                acVar = null;
            } else {
                try {
                    aVar2.a(oVar, b2.d(j), aVar, false, Collections.emptyList());
                    ac acVar2 = new ac();
                    map.put(cVar, acVar2);
                    aVar2.f();
                    z2 = z;
                    acVar = acVar2;
                } catch (Throwable th) {
                    aVar2.f();
                    throw th;
                }
            }
        } else {
            z2 = z;
            acVar = null;
        }
        return z2 ? a(oVar, aVar, acVar, cVar, map, set) : b(oVar, aVar, cVar, z, map, set);
    }

    public static c b(o oVar, a aVar, c cVar, boolean z, Map<u, n> map, Set<g> set) {
        n nVar = map.get(cVar);
        if (nVar != null) {
            return (c) nVar;
        }
        c cVar2 = cVar;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(oVar.b(c.class), aVar.f6139a, set);
        osObjectBuilder.a(aVar.f6140b, cVar2.c());
        osObjectBuilder.a(aVar.f6141c, cVar2.d());
        ac a2 = a(oVar, osObjectBuilder.b());
        map.put(cVar, a2);
        return a2;
    }

    public static c a(c cVar, int i, int i2, Map<u, n.a<u>> map) {
        c cVar2;
        if (i > i2 || cVar == null) {
            return null;
        }
        n.a<u> aVar = map.get(cVar);
        if (aVar == null) {
            cVar2 = new c();
            map.put(cVar, new n.a<>(i, cVar2));
        } else if (i >= aVar.f6299a) {
            return (c) aVar.f6300b;
        } else {
            aVar.f6299a = i;
            cVar2 = (c) aVar.f6300b;
        }
        c cVar3 = cVar2;
        c cVar4 = cVar;
        cVar3.c(cVar4.c());
        cVar3.d(cVar4.d());
        return cVar2;
    }

    static c a(o oVar, a aVar, c cVar, c cVar2, Map<u, n> map, Set<g> set) {
        c cVar3 = cVar2;
        OsObjectBuilder osObjectBuilder = new OsObjectBuilder(oVar.b(c.class), aVar.f6139a, set);
        osObjectBuilder.a(aVar.f6140b, cVar3.c());
        osObjectBuilder.a(aVar.f6141c, cVar3.d());
        osObjectBuilder.a();
        return cVar;
    }

    public String toString() {
        if (!v.a(this)) {
            return "Invalid object";
        }
        StringBuilder sb = new StringBuilder("RealmItem = proxy[");
        sb.append("{async_key:");
        sb.append(c() != null ? c() : "null");
        sb.append("}");
        sb.append(",");
        sb.append("{async_value:");
        sb.append(d() != null ? d() : "null");
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }

    @Override // io.realm.internal.n
    public n<?> c_() {
        return this.f6138c;
    }

    public int hashCode() {
        String e = this.f6138c.a().e();
        String h = this.f6138c.b().b().h();
        long c2 = this.f6138c.b().c();
        int i = 0;
        int hashCode = (527 + (e != null ? e.hashCode() : 0)) * 31;
        if (h != null) {
            i = h.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) ((c2 >>> 32) ^ c2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ac acVar = (ac) obj;
        String e = this.f6138c.a().e();
        String e2 = acVar.f6138c.a().e();
        if (e == null ? e2 != null : !e.equals(e2)) {
            return false;
        }
        String h = this.f6138c.b().b().h();
        String h2 = acVar.f6138c.b().b().h();
        if (h == null ? h2 == null : h.equals(h2)) {
            return this.f6138c.b().c() == acVar.f6138c.b().c();
        }
        return false;
    }
}
