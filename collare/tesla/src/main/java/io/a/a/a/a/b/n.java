package io.a.a.a.a.b;

import io.a.a.a.c;
import io.a.a.a.l;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: ExecutorUtils */
public final class n {
    public static ExecutorService a(String str) {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor(c(str));
        a(str, newSingleThreadExecutor);
        return newSingleThreadExecutor;
    }

    public static ScheduledExecutorService b(String str) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(c(str));
        a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static final ThreadFactory c(final String str) {
        final AtomicLong atomicLong = new AtomicLong(1);
        return new ThreadFactory() {
            /* class io.a.a.a.a.b.n.AnonymousClass1 */

            public Thread newThread(final Runnable runnable) {
                Thread newThread = Executors.defaultThreadFactory().newThread(new h() {
                    /* class io.a.a.a.a.b.n.AnonymousClass1.AnonymousClass1 */

                    @Override // io.a.a.a.a.b.h
                    public void onRun() {
                        runnable.run();
                    }
                });
                newThread.setName(str + atomicLong.getAndIncrement());
                return newThread;
            }
        };
    }

    private static final void a(String str, ExecutorService executorService) {
        a(str, executorService, 2, TimeUnit.SECONDS);
    }

    public static final void a(final String str, final ExecutorService executorService, final long j, final TimeUnit timeUnit) {
        Runtime runtime = Runtime.getRuntime();
        AnonymousClass2 r8 = new h() {
            /* class io.a.a.a.a.b.n.AnonymousClass2 */

            @Override // io.a.a.a.a.b.h
            public void onRun() {
                try {
                    l g = c.g();
                    g.a("Fabric", "Executing shutdown hook for " + str);
                    executorService.shutdown();
                    if (!executorService.awaitTermination(j, timeUnit)) {
                        l g2 = c.g();
                        g2.a("Fabric", str + " did not shut down in the allocated time. Requesting immediate shutdown.");
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException unused) {
                    c.g().a("Fabric", String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", str));
                    executorService.shutdownNow();
                }
            }
        };
        runtime.addShutdownHook(new Thread(r8, "Crashlytics Shutdown Hook for " + str));
    }
}
