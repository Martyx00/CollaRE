package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzbn implements Callable<String> {
    private final /* synthetic */ zzbl zzxs;

    zzbn(zzbl zzbl) {
        this.zzxs = zzbl;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() {
        return this.zzxs.zzdq();
    }
}
