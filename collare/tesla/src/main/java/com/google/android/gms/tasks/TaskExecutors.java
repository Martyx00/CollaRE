package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new zza();
    static final Executor zzw = new zzt();

    private TaskExecutors() {
    }

    private static final class zza implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        public final void execute(Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }
}
