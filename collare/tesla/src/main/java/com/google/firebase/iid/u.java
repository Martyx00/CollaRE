package com.google.firebase.iid;

import android.util.Pair;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* access modifiers changed from: package-private */
public final /* synthetic */ class u implements Continuation {

    /* renamed from: a  reason: collision with root package name */
    private final t f3948a;

    /* renamed from: b  reason: collision with root package name */
    private final Pair f3949b;

    u(t tVar, Pair pair) {
        this.f3948a = tVar;
        this.f3949b = pair;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return this.f3948a.a(this.f3949b, task);
    }
}
