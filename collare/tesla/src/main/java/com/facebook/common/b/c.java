package com.facebook.common.b;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: HandlerExecutorServiceImpl */
public class c extends AbstractExecutorService implements b {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f1732a;

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public c(Handler handler) {
        this.f1732a = handler;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable runnable) {
        this.f1732a.post(runnable);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T> d<T> newTaskFor(Runnable runnable, T t) {
        return new d<>(this.f1732a, runnable, t);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <T> d<T> newTaskFor(Callable<T> callable) {
        return new d<>(this.f1732a, callable);
    }

    /* renamed from: a */
    public ScheduledFuture<?> submit(Runnable runnable) {
        return submit(runnable, null);
    }

    /* renamed from: b */
    public <T> ScheduledFuture<T> submit(Runnable runnable, T t) {
        if (runnable != null) {
            d<T> a2 = newTaskFor(runnable, t);
            execute(a2);
            return a2;
        }
        throw new NullPointerException();
    }

    /* renamed from: b */
    public <T> ScheduledFuture<T> submit(Callable<T> callable) {
        if (callable != null) {
            d<T> a2 = newTaskFor(callable);
            execute(a2);
            return a2;
        }
        throw new NullPointerException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        d a2 = newTaskFor(runnable, null);
        this.f1732a.postDelayed(a2, timeUnit.toMillis(j));
        return a2;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        d a2 = newTaskFor(callable);
        this.f1732a.postDelayed(a2, timeUnit.toMillis(j));
        return a2;
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ScheduledExecutorService
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public boolean a() {
        return Thread.currentThread() == this.f1732a.getLooper().getThread();
    }
}
