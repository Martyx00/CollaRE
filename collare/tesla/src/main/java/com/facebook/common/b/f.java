package com.facebook.common.b;

import android.os.Handler;
import android.os.Looper;

/* compiled from: UiThreadImmediateExecutorService */
public class f extends c {

    /* renamed from: a  reason: collision with root package name */
    private static f f1736a;

    private f() {
        super(new Handler(Looper.getMainLooper()));
    }

    public static f b() {
        if (f1736a == null) {
            f1736a = new f();
        }
        return f1736a;
    }

    @Override // com.facebook.common.b.c
    public void execute(Runnable runnable) {
        if (a()) {
            runnable.run();
        } else {
            super.execute(runnable);
        }
    }
}
