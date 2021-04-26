package io.a.a.a.a.b;

import android.os.Process;

/* compiled from: BackgroundPriorityRunnable */
public abstract class h implements Runnable {
    /* access modifiers changed from: protected */
    public abstract void onRun();

    public final void run() {
        Process.setThreadPriority(10);
        onRun();
    }
}
