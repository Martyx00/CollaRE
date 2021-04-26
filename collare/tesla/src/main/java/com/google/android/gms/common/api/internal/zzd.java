package com.google.android.gms.common.api.internal;

final class zzd implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbi;
    private final /* synthetic */ String zzbj;
    private final /* synthetic */ zzc zzbl;

    zzd(zzc zzc, LifecycleCallback lifecycleCallback, String str) {
        this.zzbl = zzc;
        this.zzbi = lifecycleCallback;
        this.zzbj = str;
    }

    public final void run() {
        if (this.zzbl.zzbg > 0) {
            this.zzbi.onCreate(this.zzbl.zzbh != null ? this.zzbl.zzbh.getBundle(this.zzbj) : null);
        }
        if (this.zzbl.zzbg >= 2) {
            this.zzbi.onStart();
        }
        if (this.zzbl.zzbg >= 3) {
            this.zzbi.onResume();
        }
        if (this.zzbl.zzbg >= 4) {
            this.zzbi.onStop();
        }
        if (this.zzbl.zzbg >= 5) {
            this.zzbi.onDestroy();
        }
    }
}
