package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzjh implements Runnable {
    private final /* synthetic */ Runnable zzabs;
    private final /* synthetic */ zzjt zzark;

    zzjh(zzje zzje, zzjt zzjt, Runnable runnable) {
        this.zzark = zzjt;
        this.zzabs = runnable;
    }

    public final void run() {
        this.zzark.zzlj();
        this.zzark.zzg(this.zzabs);
        this.zzark.zzle();
    }
}
