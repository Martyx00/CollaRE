package com.google.android.gms.stats;

/* access modifiers changed from: package-private */
public final class zzb implements Runnable {
    private final /* synthetic */ WakeLock zzp;

    zzb(WakeLock wakeLock) {
        this.zzp = wakeLock;
    }

    public final void run() {
        this.zzp.zza((WakeLock) 0);
    }
}
