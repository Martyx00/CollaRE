package com.google.firebase.iid;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public final class al {

    /* renamed from: a  reason: collision with root package name */
    private static final Executor f3889a = an.f3891a;

    static Executor a() {
        return f3889a;
    }

    static Executor b() {
        return new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue(), am.f3890a);
    }

    static final /* synthetic */ Thread a(Runnable runnable) {
        return new Thread(runnable, "firebase-iid-executor");
    }
}
