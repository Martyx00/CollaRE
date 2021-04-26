package com.facebook.imagepipeline.n;

import com.facebook.common.d.i;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;

/* compiled from: ThreadHandoffProducerQueue */
public class au {

    /* renamed from: a  reason: collision with root package name */
    private boolean f2332a = false;

    /* renamed from: b  reason: collision with root package name */
    private final Deque<Runnable> f2333b;

    /* renamed from: c  reason: collision with root package name */
    private final Executor f2334c;

    public au(Executor executor) {
        this.f2334c = (Executor) i.a(executor);
        this.f2333b = new ArrayDeque();
    }

    public synchronized void a(Runnable runnable) {
        if (this.f2332a) {
            this.f2333b.add(runnable);
        } else {
            this.f2334c.execute(runnable);
        }
    }

    public synchronized void b(Runnable runnable) {
        this.f2333b.remove(runnable);
    }
}
