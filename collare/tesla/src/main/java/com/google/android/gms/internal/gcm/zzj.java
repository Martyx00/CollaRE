package com.google.android.gms.internal.gcm;

final class zzj extends zzg {
    private final zzh zzdi = new zzh();

    zzj() {
    }

    @Override // com.google.android.gms.internal.gcm.zzg
    public final void zzd(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 != null) {
            this.zzdi.zzd(th, true).add(th2);
        } else {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
    }
}
