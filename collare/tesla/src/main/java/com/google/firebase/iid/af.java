package com.google.firebase.iid;

import android.content.Intent;

final class af implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final /* synthetic */ Intent f3873a;

    /* renamed from: b  reason: collision with root package name */
    private final /* synthetic */ Intent f3874b;

    /* renamed from: c  reason: collision with root package name */
    private final /* synthetic */ ad f3875c;

    af(ad adVar, Intent intent, Intent intent2) {
        this.f3875c = adVar;
        this.f3873a = intent;
        this.f3874b = intent2;
    }

    public final void run() {
        this.f3875c.b(this.f3873a);
        this.f3875c.d(this.f3874b);
    }
}
