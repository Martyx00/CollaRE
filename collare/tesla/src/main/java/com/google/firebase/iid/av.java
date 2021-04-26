package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public final /* synthetic */ class av implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final au f3911a;

    /* renamed from: b  reason: collision with root package name */
    private final Bundle f3912b;

    /* renamed from: c  reason: collision with root package name */
    private final TaskCompletionSource f3913c;

    av(au auVar, Bundle bundle, TaskCompletionSource taskCompletionSource) {
        this.f3911a = auVar;
        this.f3912b = bundle;
        this.f3913c = taskCompletionSource;
    }

    public final void run() {
        this.f3911a.a(this.f3912b, this.f3913c);
    }
}
