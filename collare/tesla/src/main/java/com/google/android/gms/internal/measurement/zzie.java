package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* access modifiers changed from: package-private */
public final class zzie implements Runnable {
    private final /* synthetic */ String val$name;
    private final /* synthetic */ String zzadn;
    private final /* synthetic */ boolean zzaek;
    private final /* synthetic */ String zzapg;
    private final /* synthetic */ zzhm zzaps;
    private final /* synthetic */ long zzapu;
    private final /* synthetic */ Bundle zzapx;
    private final /* synthetic */ boolean zzapy;
    private final /* synthetic */ boolean zzapz;

    zzie(zzhm zzhm, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.zzaps = zzhm;
        this.zzadn = str;
        this.val$name = str2;
        this.zzapu = j;
        this.zzapx = bundle;
        this.zzaek = z;
        this.zzapy = z2;
        this.zzapz = z3;
        this.zzapg = str3;
    }

    public final void run() {
        this.zzaps.zza(this.zzadn, this.val$name, this.zzapu, this.zzapx, this.zzaek, this.zzapy, this.zzapz, this.zzapg);
    }
}
