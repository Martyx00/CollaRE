package com.oney.WebRTCModule;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* access modifiers changed from: package-private */
/* compiled from: ThreadUtils */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    private static final ExecutorService f4725a = Executors.newSingleThreadExecutor();

    public static void a(Runnable runnable) {
        f4725a.execute(runnable);
    }
}
