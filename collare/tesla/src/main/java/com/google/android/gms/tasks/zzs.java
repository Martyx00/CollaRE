package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener {
    private final /* synthetic */ TaskCompletionSource zzv;

    zzs(TaskCompletionSource taskCompletionSource) {
        this.zzv = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        this.zzv.zza.zza();
    }
}
