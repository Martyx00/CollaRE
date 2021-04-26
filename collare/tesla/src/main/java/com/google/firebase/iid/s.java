package com.google.firebase.iid;

import com.google.firebase.components.b;
import com.google.firebase.components.c;
import com.google.firebase.iid.Registrar;

final /* synthetic */ class s implements c {

    /* renamed from: a  reason: collision with root package name */
    static final c f3945a = new s();

    private s() {
    }

    @Override // com.google.firebase.components.c
    public final Object a(b bVar) {
        return new Registrar.a((FirebaseInstanceId) bVar.a(FirebaseInstanceId.class));
    }
}
