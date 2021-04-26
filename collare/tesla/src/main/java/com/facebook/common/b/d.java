package com.facebook.common.b;

import android.os.Handler;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: ScheduledFutureImpl */
public class d<V> implements RunnableFuture<V>, ScheduledFuture<V> {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f1733a;

    /* renamed from: b  reason: collision with root package name */
    private final FutureTask<V> f1734b;

    public d(Handler handler, Callable<V> callable) {
        this.f1733a = handler;
        this.f1734b = new FutureTask<>(callable);
    }

    public d(Handler handler, Runnable runnable, V v) {
        this.f1733a = handler;
        this.f1734b = new FutureTask<>(runnable, v);
    }

    public long getDelay(TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    /* renamed from: a */
    public int compareTo(Delayed delayed) {
        throw new UnsupportedOperationException();
    }

    public void run() {
        this.f1734b.run();
    }

    public boolean cancel(boolean z) {
        return this.f1734b.cancel(z);
    }

    public boolean isCancelled() {
        return this.f1734b.isCancelled();
    }

    public boolean isDone() {
        return this.f1734b.isDone();
    }

    @Override // java.util.concurrent.Future
    public V get() {
        return this.f1734b.get();
    }

    @Override // java.util.concurrent.Future
    public V get(long j, TimeUnit timeUnit) {
        return this.f1734b.get(j, timeUnit);
    }
}
