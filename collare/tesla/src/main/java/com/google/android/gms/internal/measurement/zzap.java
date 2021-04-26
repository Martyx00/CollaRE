package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

/* access modifiers changed from: package-private */
public final class zzap implements Callable<Void> {
    private final /* synthetic */ zzai zzvg;

    zzap(zzai zzai) {
        this.zzvg = zzai;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Void call() {
        this.zzvg.zzve.zzdh();
        return null;
    }
}
