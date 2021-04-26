package io.a.a.a.a.c;

import android.annotation.TargetApi;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PriorityThreadPoolExecutor */
public class k extends ThreadPoolExecutor {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5977a = Runtime.getRuntime().availableProcessors();

    /* renamed from: b  reason: collision with root package name */
    private static final int f5978b;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5979c;

    static {
        int i = f5977a;
        f5978b = i + 1;
        f5979c = (i * 2) + 1;
    }

    <T extends Runnable & b & l & i> k(int i, int i2, long j, TimeUnit timeUnit, c<T> cVar, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, cVar, threadFactory);
        prestartAllCoreThreads();
    }

    public static <T extends Runnable & b & l & i> k a(int i, int i2) {
        return new k(i, i2, 1, TimeUnit.SECONDS, new c(), new a(10));
    }

    public static k a() {
        return a(f5978b, f5979c);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new h(runnable, t);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.AbstractExecutorService
    public <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new h(callable);
    }

    @TargetApi(9)
    public void execute(Runnable runnable) {
        if (j.isProperDelegate(runnable)) {
            super.execute(runnable);
        } else {
            super.execute(newTaskFor(runnable, null));
        }
    }

    /* access modifiers changed from: protected */
    public void afterExecute(Runnable runnable, Throwable th) {
        l lVar = (l) runnable;
        lVar.setFinished(true);
        lVar.setError(th);
        getQueue().d();
        super.afterExecute(runnable, th);
    }

    /* renamed from: b */
    public c getQueue() {
        return (c) super.getQueue();
    }

    /* access modifiers changed from: protected */
    /* compiled from: PriorityThreadPoolExecutor */
    public static final class a implements ThreadFactory {

        /* renamed from: a  reason: collision with root package name */
        private final int f5980a;

        public a(int i) {
            this.f5980a = i;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setPriority(this.f5980a);
            thread.setName("Queue");
            return thread;
        }
    }
}
