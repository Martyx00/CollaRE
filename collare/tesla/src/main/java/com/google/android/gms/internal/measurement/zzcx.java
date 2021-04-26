package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzcx implements zzca {
    private final /* synthetic */ Runnable zzabs;
    private final /* synthetic */ zzcu zzabt;

    zzcx(zzcu zzcu, Runnable runnable) {
        this.zzabt = zzcu;
        this.zzabs = runnable;
    }

    @Override // com.google.android.gms.internal.measurement.zzca
    public final void zza(Throwable th) {
        this.zzabt.handler.post(this.zzabs);
    }
}
