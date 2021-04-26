package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final /* synthetic */ class zzxa implements zzxg {
    private final String zzbps;
    private final boolean zzbpt = false;

    zzxa(String str, boolean z) {
        this.zzbps = str;
    }

    @Override // com.google.android.gms.internal.measurement.zzxg
    public final Object zzsq() {
        return Boolean.valueOf(zzws.zza(zzwx.zzqx.getContentResolver(), this.zzbps, this.zzbpt));
    }
}
