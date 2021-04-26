package io.realm;

import io.realm.internal.OsObjectStore;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.OsSharedRealm;
import io.realm.p;

/* compiled from: DynamicRealm */
public class c extends a {
    private final z g = new i(this);

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ void b() {
        super.b();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ void c() {
        super.c();
    }

    @Override // io.realm.a, java.io.Closeable, java.lang.AutoCloseable
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ String e() {
        return super.e();
    }

    @Override // io.realm.a
    public /* bridge */ /* synthetic */ r f() {
        return super.f();
    }

    private c(final p pVar) {
        super(pVar, (OsSchemaInfo) null);
        p.a(pVar.a(), new p.a() {
            /* class io.realm.c.AnonymousClass1 */

            @Override // io.realm.p.a
            public void a(int i) {
                if (i <= 0 && !pVar.a().o() && OsObjectStore.a(c.this.e) == -1) {
                    c.this.e.beginTransaction();
                    if (OsObjectStore.a(c.this.e) == -1) {
                        OsObjectStore.a(c.this.e, -1);
                    }
                    c.this.e.commitTransaction();
                }
            }
        });
    }

    private c(OsSharedRealm osSharedRealm) {
        super(osSharedRealm);
    }

    static c a(p pVar) {
        return new c(pVar);
    }

    static c a(OsSharedRealm osSharedRealm) {
        return new c(osSharedRealm);
    }

    @Override // io.realm.a
    public z h() {
        return this.g;
    }
}
