package com.google.firebase.iid;

import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public final /* synthetic */ class aq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final FirebaseInstanceId f3894a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3895b;

    /* renamed from: c  reason: collision with root package name */
    private final String f3896c;

    /* renamed from: d  reason: collision with root package name */
    private final TaskCompletionSource f3897d;
    private final String e;

    aq(FirebaseInstanceId firebaseInstanceId, String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        this.f3894a = firebaseInstanceId;
        this.f3895b = str;
        this.f3896c = str2;
        this.f3897d = taskCompletionSource;
        this.e = str3;
    }

    public final void run() {
        this.f3894a.a(this.f3895b, this.f3896c, this.f3897d, this.e);
    }
}
