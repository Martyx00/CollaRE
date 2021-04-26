package org.webrtc;

import java.util.concurrent.atomic.AtomicInteger;

/* access modifiers changed from: package-private */
public class RefCountDelegate implements RefCounted {
    private final AtomicInteger refCount = new AtomicInteger(1);
    private final Runnable releaseCallback;

    public RefCountDelegate(Runnable runnable) {
        this.releaseCallback = runnable;
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        this.refCount.incrementAndGet();
    }

    @Override // org.webrtc.RefCounted
    public void release() {
        Runnable runnable;
        if (this.refCount.decrementAndGet() == 0 && (runnable = this.releaseCallback) != null) {
            runnable.run();
        }
    }
}
