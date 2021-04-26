package com.google.android.gms.internal.measurement;

final class zzaak implements zzaap {
    private zzaap[] zzbvm;

    zzaak(zzaap... zzaapArr) {
        this.zzbvm = zzaapArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzaap
    public final boolean zzd(Class<?> cls) {
        for (zzaap zzaap : this.zzbvm) {
            if (zzaap.zzd(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzaap
    public final zzaao zze(Class<?> cls) {
        zzaap[] zzaapArr = this.zzbvm;
        for (zzaap zzaap : zzaapArr) {
            if (zzaap.zzd(cls)) {
                return zzaap.zze(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
