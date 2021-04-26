package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class zzc<TResult, TContinuationResult> implements zzq<TResult> {
    private final Executor zzd;
    private final Continuation<TResult, TContinuationResult> zze;
    private final zzu<TContinuationResult> zzf;

    public zzc(Executor executor, Continuation<TResult, TContinuationResult> continuation, zzu<TContinuationResult> zzu) {
        this.zzd = executor;
        this.zze = continuation;
        this.zzf = zzu;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void onComplete(Task<TResult> task) {
        this.zzd.execute(new zzd(this, task));
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void cancel() {
        throw new UnsupportedOperationException();
    }
}
