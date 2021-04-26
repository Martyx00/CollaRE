package com.google.firebase.iid;

import android.os.Looper;
import android.os.Message;
import com.google.android.gms.internal.firebase_messaging.zza;

final class x extends zza {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ w f3954a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    x(w wVar, Looper looper) {
        super(looper);
        this.f3954a = wVar;
    }

    public final void handleMessage(Message message) {
        this.f3954a.a((w) message);
    }
}
