package io.realm.internal;

import io.realm.log.RealmLog;
import java.lang.ref.ReferenceQueue;

/* compiled from: FinalizerRunnable */
class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final ReferenceQueue<i> f6278a;

    e(ReferenceQueue<i> referenceQueue) {
        this.f6278a = referenceQueue;
    }

    public void run() {
        while (true) {
            try {
                ((NativeObjectReference) this.f6278a.remove()).a();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                RealmLog.c("The FinalizerRunnable thread has been interrupted. Native resources cannot be freed anymore", new Object[0]);
                return;
            }
        }
    }
}
