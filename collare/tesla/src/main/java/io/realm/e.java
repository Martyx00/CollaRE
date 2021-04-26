package io.realm;

import io.realm.internal.Table;
import io.realm.internal.c;
import io.realm.x;

/* access modifiers changed from: package-private */
/* compiled from: ImmutableRealmObjectSchema */
public class e extends x {
    e(a aVar, z zVar, Table table, c cVar) {
        super(aVar, zVar, table, cVar);
    }

    e(a aVar, z zVar, Table table) {
        super(aVar, zVar, table, new x.a(table));
    }

    /* access modifiers changed from: package-private */
    @Override // io.realm.x
    public io.realm.internal.a.c a(String str, RealmFieldType... realmFieldTypeArr) {
        return io.realm.internal.a.c.a(c(), b(), str, realmFieldTypeArr);
    }
}
