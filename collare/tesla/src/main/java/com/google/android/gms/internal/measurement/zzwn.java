package com.google.android.gms.internal.measurement;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzwn {
    private static final Integer zzboj = 0;
    private static final Integer zzbok = 1;
    private final ExecutorService executor;
    private final Context zzqx;

    public zzwn(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    @VisibleForTesting
    private zzwn(Context context, ExecutorService executorService) {
        this.zzqx = context;
        this.executor = executorService;
    }
}
