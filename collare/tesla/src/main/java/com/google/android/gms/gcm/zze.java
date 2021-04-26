package com.google.android.gms.gcm;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class zze implements ThreadFactory {
    private final AtomicInteger zzx = new AtomicInteger(1);

    zze(GcmTaskService gcmTaskService) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.zzx.getAndIncrement();
        StringBuilder sb = new StringBuilder(20);
        sb.append("gcm-task#");
        sb.append(andIncrement);
        Thread thread = new Thread(runnable, sb.toString());
        thread.setPriority(4);
        return thread;
    }
}
