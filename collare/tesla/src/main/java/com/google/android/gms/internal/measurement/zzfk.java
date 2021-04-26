package com.google.android.gms.internal.measurement;

public final class zzfk {
    private final int priority;
    private final /* synthetic */ zzfi zzalb;
    private final boolean zzalc;
    private final boolean zzald;

    zzfk(zzfi zzfi, int i, boolean z, boolean z2) {
        this.zzalb = zzfi;
        this.priority = i;
        this.zzalc = z;
        this.zzald = z2;
    }

    public final void log(String str) {
        this.zzalb.zza(this.priority, this.zzalc, this.zzald, str, null, null, null);
    }

    public final void zzd(String str, Object obj, Object obj2, Object obj3) {
        this.zzalb.zza(this.priority, this.zzalc, this.zzald, str, obj, obj2, obj3);
    }

    public final void zze(String str, Object obj, Object obj2) {
        this.zzalb.zza(this.priority, this.zzalc, this.zzald, str, obj, obj2, null);
    }

    public final void zzg(String str, Object obj) {
        this.zzalb.zza(this.priority, this.zzalc, this.zzald, str, obj, null, null);
    }
}
