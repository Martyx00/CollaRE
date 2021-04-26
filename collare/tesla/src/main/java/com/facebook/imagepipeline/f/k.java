package com.facebook.imagepipeline.f;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PriorityThreadFactory */
public class k implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    private final int f2133a;

    /* renamed from: b  reason: collision with root package name */
    private final String f2134b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f2135c;

    /* renamed from: d  reason: collision with root package name */
    private final AtomicInteger f2136d = new AtomicInteger(1);

    public k(int i, String str, boolean z) {
        this.f2133a = i;
        this.f2134b = str;
        this.f2135c = z;
    }

    public Thread newThread(final Runnable runnable) {
        String str;
        AnonymousClass1 r0 = new Runnable() {
            /* class com.facebook.imagepipeline.f.k.AnonymousClass1 */

            public void run() {
                try {
                    Process.setThreadPriority(k.this.f2133a);
                } catch (Throwable unused) {
                }
                runnable.run();
            }
        };
        if (this.f2135c) {
            str = this.f2134b + "-" + this.f2136d.getAndIncrement();
        } else {
            str = this.f2134b;
        }
        return new Thread(r0, str);
    }
}
