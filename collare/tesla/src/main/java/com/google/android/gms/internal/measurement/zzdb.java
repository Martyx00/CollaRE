package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public final class zzdb extends zzaq implements zzbt<zzdc> {
    private final zzdc zzabu = new zzdc();

    public zzdb(zzat zzat) {
        super(zzat);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzbt
    public final void zza(String str, boolean z) {
        if ("ga_autoActivityTracking".equals(str)) {
            this.zzabu.zzaby = z;
        } else if ("ga_anonymizeIp".equals(str)) {
            this.zzabu.zzabz = z;
        } else if ("ga_reportUncaughtExceptions".equals(str)) {
            this.zzabu.zzaca = z ? 1 : 0;
        } else {
            zzd("bool configuration name not recognized", str);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzbt
    public final void zzb(String str, int i) {
        if ("ga_sessionTimeout".equals(str)) {
            this.zzabu.zzabx = i;
        } else {
            zzd("int configuration name not recognized", str);
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzbt
    public final void zzb(String str, String str2) {
        this.zzabu.zzacb.put(str, str2);
    }

    @Override // com.google.android.gms.internal.measurement.zzbt
    public final void zzc(String str, String str2) {
        if ("ga_trackingId".equals(str)) {
            this.zzabu.zzabv = str2;
        } else if ("ga_sampleFrequency".equals(str)) {
            try {
                this.zzabu.zzabw = Double.parseDouble(str2);
            } catch (NumberFormatException e) {
                zzc("Error parsing ga_sampleFrequency value", str2, e);
            }
        } else {
            zzd("string configuration name not recognized", str);
        }
    }

    /* Return type fixed from 'com.google.android.gms.internal.measurement.zzbr' to match base method */
    @Override // com.google.android.gms.internal.measurement.zzbt
    public final /* synthetic */ zzdc zzdr() {
        return this.zzabu;
    }
}
