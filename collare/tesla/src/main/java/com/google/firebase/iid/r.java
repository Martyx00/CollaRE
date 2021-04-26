package com.google.firebase.iid;

import com.google.firebase.FirebaseApp;
import com.google.firebase.a.d;
import com.google.firebase.components.b;
import com.google.firebase.components.c;

final /* synthetic */ class r implements c {

    /* renamed from: a  reason: collision with root package name */
    static final c f3944a = new r();

    private r() {
    }

    @Override // com.google.firebase.components.c
    public final Object a(b bVar) {
        return new FirebaseInstanceId((FirebaseApp) bVar.a(FirebaseApp.class), (d) bVar.a(d.class));
    }
}
