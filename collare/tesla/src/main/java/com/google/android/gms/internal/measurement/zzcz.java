package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* access modifiers changed from: package-private */
public final class zzcz {
    private final Clock clock;
    private long startTime;

    public zzcz(Clock clock2) {
        Preconditions.checkNotNull(clock2);
        this.clock = clock2;
    }

    public zzcz(Clock clock2, long j) {
        Preconditions.checkNotNull(clock2);
        this.clock = clock2;
        this.startTime = j;
    }

    public final void clear() {
        this.startTime = 0;
    }

    public final void start() {
        this.startTime = this.clock.elapsedRealtime();
    }

    public final boolean zzj(long j) {
        return this.startTime == 0 || this.clock.elapsedRealtime() - this.startTime > j;
    }
}
