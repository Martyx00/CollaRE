package com.crashlytics.android.core;

import android.os.Looper;
import io.a.a.a.c;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public class CrashlyticsBackgroundWorker {
    private final ExecutorService executorService;

    public CrashlyticsBackgroundWorker(ExecutorService executorService2) {
        this.executorService = executorService2;
    }

    /* access modifiers changed from: package-private */
    public <T> T submitAndWait(Callable<T> callable) {
        try {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                return this.executorService.submit(callable).get(4, TimeUnit.SECONDS);
            }
            return this.executorService.submit(callable).get();
        } catch (RejectedExecutionException unused) {
            c.g().a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        } catch (Exception e) {
            c.g().e(CrashlyticsCore.TAG, "Failed to execute task.", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Future<?> submit(final Runnable runnable) {
        try {
            return this.executorService.submit(new Runnable() {
                /* class com.crashlytics.android.core.CrashlyticsBackgroundWorker.AnonymousClass1 */

                public void run() {
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        c.g().e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            c.g().a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public <T> Future<T> submit(final Callable<T> callable) {
        try {
            return this.executorService.submit(new Callable<T>() {
                /* class com.crashlytics.android.core.CrashlyticsBackgroundWorker.AnonymousClass2 */

                @Override // java.util.concurrent.Callable
                public T call() {
                    try {
                        return (T) callable.call();
                    } catch (Exception e) {
                        c.g().e(CrashlyticsCore.TAG, "Failed to execute task.", e);
                        return null;
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            c.g().a(CrashlyticsCore.TAG, "Executor is shut down because we're handling a fatal crash.");
            return null;
        }
    }
}
