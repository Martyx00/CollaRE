package com.google.android.gms.internal.measurement;

final class zzaau<T> implements zzaba<T> {
    private final zzaaq zzbvr;
    private final zzabo<?, ?> zzbvs;
    private final boolean zzbvt;
    private final zzzo<?> zzbvu;

    private zzaau(zzabo<?, ?> zzabo, zzzo<?> zzzo, zzaaq zzaaq) {
        this.zzbvs = zzabo;
        this.zzbvt = zzzo.zza(zzaaq);
        this.zzbvu = zzzo;
        this.zzbvr = zzaaq;
    }

    static <T> zzaau<T> zza(zzabo<?, ?> zzabo, zzzo<?> zzzo, zzaaq zzaaq) {
        return new zzaau<>(zzabo, zzzo, zzaaq);
    }

    @Override // com.google.android.gms.internal.measurement.zzaba
    public final boolean equals(T t, T t2) {
        if (!this.zzbvs.zzu(t).equals(this.zzbvs.zzu(t2))) {
            return false;
        }
        if (this.zzbvt) {
            return this.zzbvu.zzs(t).equals(this.zzbvu.zzs(t2));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzaba
    public final int hashCode(T t) {
        int hashCode = this.zzbvs.zzu(t).hashCode();
        return this.zzbvt ? (hashCode * 53) + this.zzbvu.zzs(t).hashCode() : hashCode;
    }
}
