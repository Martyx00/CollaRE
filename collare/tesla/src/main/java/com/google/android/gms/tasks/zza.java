package com.google.android.gms.tasks;

final class zza extends CancellationToken {
    private final zzu<Void> zza = new zzu<>();

    zza() {
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener) {
        this.zza.addOnSuccessListener(new zzb(this, onTokenCanceledListener));
        return this;
    }

    public final void cancel() {
        this.zza.trySetResult(null);
    }
}
