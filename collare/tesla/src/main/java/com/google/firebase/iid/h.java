package com.google.firebase.iid;

import android.os.Handler;
import android.os.Message;

/* access modifiers changed from: package-private */
public final /* synthetic */ class h implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    private final g f3928a;

    h(g gVar) {
        this.f3928a = gVar;
    }

    public final boolean handleMessage(Message message) {
        return this.f3928a.a(message);
    }
}
