package io.a.a.a.a.b;

import android.os.SystemClock;
import android.util.Log;

/* compiled from: TimingMetric */
public class w {

    /* renamed from: a  reason: collision with root package name */
    private final String f5937a;

    /* renamed from: b  reason: collision with root package name */
    private final String f5938b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f5939c;

    /* renamed from: d  reason: collision with root package name */
    private long f5940d;
    private long e;

    public w(String str, String str2) {
        this.f5937a = str;
        this.f5938b = str2;
        this.f5939c = !Log.isLoggable(str2, 2);
    }

    public synchronized void a() {
        if (!this.f5939c) {
            this.f5940d = SystemClock.elapsedRealtime();
            this.e = 0;
        }
    }

    public synchronized void b() {
        if (!this.f5939c) {
            if (this.e == 0) {
                this.e = SystemClock.elapsedRealtime() - this.f5940d;
                c();
            }
        }
    }

    private void c() {
        String str = this.f5938b;
        Log.v(str, this.f5937a + ": " + this.e + "ms");
    }
}
