package com.google.firebase.iid;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public final /* synthetic */ class as implements OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    private final FirebaseInstanceId f3902a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3903b;

    /* renamed from: c  reason: collision with root package name */
    private final String f3904c;

    /* renamed from: d  reason: collision with root package name */
    private final TaskCompletionSource f3905d;
    private final String e;

    as(FirebaseInstanceId firebaseInstanceId, String str, String str2, TaskCompletionSource taskCompletionSource, String str3) {
        this.f3902a = firebaseInstanceId;
        this.f3903b = str;
        this.f3904c = str2;
        this.f3905d = taskCompletionSource;
        this.e = str3;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task) {
        this.f3902a.a(this.f3903b, this.f3904c, this.f3905d, this.e, task);
    }
}
