package com.facebook.react.common.futures;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SimpleSettableFuture<T> implements Future<T> {

    /* renamed from: a  reason: collision with root package name */
    private final CountDownLatch f2613a = new CountDownLatch(1);

    /* renamed from: b  reason: collision with root package name */
    private T f2614b;

    /* renamed from: c  reason: collision with root package name */
    private Exception f2615c;

    public boolean isCancelled() {
        return false;
    }

    public void a(T t) {
        b();
        this.f2614b = t;
        this.f2613a.countDown();
    }

    public void a(Exception exc) {
        b();
        this.f2615c = exc;
        this.f2613a.countDown();
    }

    public boolean cancel(boolean z) {
        throw new UnsupportedOperationException();
    }

    public boolean isDone() {
        return this.f2613a.getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    public T get() {
        this.f2613a.await();
        Exception exc = this.f2615c;
        if (exc == null) {
            return this.f2614b;
        }
        throw new ExecutionException(exc);
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) {
        if (this.f2613a.await(j, timeUnit)) {
            Exception exc = this.f2615c;
            if (exc == null) {
                return this.f2614b;
            }
            throw new ExecutionException(exc);
        }
        throw new TimeoutException("Timed out waiting for result");
    }

    public T a() {
        try {
            return get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void b() {
        if (this.f2613a.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
