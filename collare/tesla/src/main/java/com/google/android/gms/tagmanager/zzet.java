package com.google.android.gms.tagmanager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* access modifiers changed from: package-private */
public final class zzet implements zzew {
    zzet(zzes zzes) {
    }

    @Override // com.google.android.gms.tagmanager.zzew
    public final ScheduledExecutorService zzoo() {
        return Executors.newSingleThreadScheduledExecutor();
    }
}
