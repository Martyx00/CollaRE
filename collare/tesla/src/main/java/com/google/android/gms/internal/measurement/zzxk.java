package com.google.android.gms.internal.measurement;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* access modifiers changed from: package-private */
public final class zzxk {
    private final ConcurrentHashMap<zzxl, List<Throwable>> zzbqd = new ConcurrentHashMap<>(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzbqe = new ReferenceQueue<>();

    zzxk() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        while (true) {
            Reference<? extends Throwable> poll = this.zzbqe.poll();
            if (poll != null) {
                this.zzbqd.remove(poll);
            } else {
                return this.zzbqd.get(new zzxl(th, null));
            }
        }
    }
}
