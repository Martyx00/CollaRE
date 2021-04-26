package com.google.firebase.iid;

import java.util.concurrent.ThreadFactory;

/* access modifiers changed from: package-private */
public final /* synthetic */ class am implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    static final ThreadFactory f3890a = new am();

    private am() {
    }

    public final Thread newThread(Runnable runnable) {
        return al.a(runnable);
    }
}
