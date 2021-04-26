package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.d.d;

@d
public class RealtimeSinceBootClock implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final RealtimeSinceBootClock f1797a = new RealtimeSinceBootClock();

    private RealtimeSinceBootClock() {
    }

    @d
    public static RealtimeSinceBootClock get() {
        return f1797a;
    }

    @Override // com.facebook.common.time.b
    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
