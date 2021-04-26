package com.google.firebase.iid;

import android.support.annotation.Keep;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.d;
import com.google.firebase.components.e;
import java.util.Arrays;
import java.util.List;

@Keep
@KeepForSdk
public final class Registrar implements d {

    /* access modifiers changed from: private */
    public static class a implements com.google.firebase.iid.a.a {

        /* renamed from: a  reason: collision with root package name */
        private final FirebaseInstanceId f3856a;

        public a(FirebaseInstanceId firebaseInstanceId) {
            this.f3856a = firebaseInstanceId;
        }
    }

    @Override // com.google.firebase.components.d
    @Keep
    public final List<com.google.firebase.components.a<?>> getComponents() {
        return Arrays.asList(com.google.firebase.components.a.a(FirebaseInstanceId.class).a(e.a(FirebaseApp.class)).a(e.a(com.google.firebase.a.d.class)).a(r.f3944a).a().c(), com.google.firebase.components.a.a(com.google.firebase.iid.a.a.class).a(e.a(FirebaseInstanceId.class)).a(s.f3945a).c());
    }
}
