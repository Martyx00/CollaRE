package io.a.a.a.a.d;

import android.content.Context;

/* compiled from: TimeBasedFileRollOverRunnable */
public class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private final Context f5988a;

    /* renamed from: b  reason: collision with root package name */
    private final e f5989b;

    public i(Context context, e eVar) {
        this.f5988a = context;
        this.f5989b = eVar;
    }

    public void run() {
        try {
            io.a.a.a.a.b.i.a(this.f5988a, "Performing time based file roll over.");
            if (!this.f5989b.rollFileOver()) {
                this.f5989b.cancelTimeBasedFileRollOver();
            }
        } catch (Exception e) {
            io.a.a.a.a.b.i.a(this.f5988a, "Failed to roll over file", e);
        }
    }
}
