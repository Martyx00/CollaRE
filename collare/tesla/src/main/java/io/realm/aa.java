package io.realm;

import io.realm.internal.a.c;

/* compiled from: SchemaConnector */
class aa implements c.a {

    /* renamed from: a  reason: collision with root package name */
    private final z f6131a;

    public aa(z zVar) {
        this.f6131a = zVar;
    }

    @Override // io.realm.internal.a.c.a
    public boolean a() {
        return this.f6131a.a();
    }

    @Override // io.realm.internal.a.c.a
    public io.realm.internal.c a(String str) {
        return this.f6131a.c(str);
    }

    @Override // io.realm.internal.a.c.a
    public long b(String str) {
        return this.f6131a.a(str).getNativePtr();
    }
}
