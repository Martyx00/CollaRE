package com.google.firebase.iid;

import com.google.firebase.a.a;
import com.google.firebase.a.b;
import com.google.firebase.iid.FirebaseInstanceId;

/* access modifiers changed from: package-private */
public final /* synthetic */ class at implements b {

    /* renamed from: a  reason: collision with root package name */
    private final FirebaseInstanceId.a f3906a;

    at(FirebaseInstanceId.a aVar) {
        this.f3906a = aVar;
    }

    @Override // com.google.firebase.a.b
    public final void a(a aVar) {
        FirebaseInstanceId.a aVar2 = this.f3906a;
        synchronized (aVar2) {
            if (aVar2.a()) {
                FirebaseInstanceId.this.n();
            }
        }
    }
}
