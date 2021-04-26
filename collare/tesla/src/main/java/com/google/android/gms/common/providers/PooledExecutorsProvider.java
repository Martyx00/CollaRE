package com.google.android.gms.common.providers;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;

@KeepForSdk
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzey;

    public interface PooledExecutorFactory {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    @KeepForSdk
    public static synchronized PooledExecutorFactory getInstance() {
        PooledExecutorFactory pooledExecutorFactory;
        synchronized (PooledExecutorsProvider.class) {
            if (zzey == null) {
                zzey = new zza();
            }
            pooledExecutorFactory = zzey;
        }
        return pooledExecutorFactory;
    }

    private PooledExecutorsProvider() {
    }
}
