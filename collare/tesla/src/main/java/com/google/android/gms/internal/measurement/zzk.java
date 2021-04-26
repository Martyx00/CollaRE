package com.google.android.gms.internal.measurement;

public final class zzk extends zzacd<zzk> {
    private static volatile zzk[] zzpr;
    public String name = "";
    private zzm zzps = null;
    public zzg zzpt = null;

    public zzk() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzk[] zzf() {
        if (zzpr == null) {
            synchronized (zzach.zzbzn) {
                if (zzpr == null) {
                    zzpr = new zzk[0];
                }
            }
        }
        return zzpr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzk)) {
            return false;
        }
        zzk zzk = (zzk) obj;
        String str = this.name;
        if (str == null) {
            if (zzk.name != null) {
                return false;
            }
        } else if (!str.equals(zzk.name)) {
            return false;
        }
        zzm zzm = this.zzps;
        if (zzm == null) {
            if (zzk.zzps != null) {
                return false;
            }
        } else if (!zzm.equals(zzk.zzps)) {
            return false;
        }
        zzg zzg = this.zzpt;
        if (zzg == null) {
            if (zzk.zzpt != null) {
                return false;
            }
        } else if (!zzg.equals(zzk.zzpt)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzk.zzbzd == null || zzk.zzbzd.isEmpty() : this.zzbzd.equals(zzk.zzbzd);
    }

    public final int hashCode() {
        int hashCode = (getClass().getName().hashCode() + 527) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = hashCode + (str == null ? 0 : str.hashCode());
        zzm zzm = this.zzps;
        int hashCode3 = (hashCode2 * 31) + (zzm == null ? 0 : zzm.hashCode());
        zzg zzg = this.zzpt;
        int hashCode4 = ((hashCode3 * 31) + (zzg == null ? 0 : zzg.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode4 + i;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        String str = this.name;
        if (str != null && !str.equals("")) {
            zza += zzacb.zzc(1, this.name);
        }
        zzm zzm = this.zzps;
        if (zzm != null) {
            zza += zzacb.zzb(2, zzm);
        }
        zzg zzg = this.zzpt;
        return zzg != null ? zza + zzacb.zzb(3, zzg) : zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        String str = this.name;
        if (str != null && !str.equals("")) {
            zzacb.zzb(1, this.name);
        }
        zzm zzm = this.zzps;
        if (zzm != null) {
            zzacb.zza(2, zzm);
        }
        zzg zzg = this.zzpt;
        if (zzg != null) {
            zzacb.zza(3, zzg);
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        zzacj zzacj;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 10) {
                if (zzvl == 18) {
                    if (this.zzps == null) {
                        this.zzps = new zzm();
                    }
                    zzacj = this.zzps;
                } else if (zzvl == 26) {
                    if (this.zzpt == null) {
                        this.zzpt = new zzg();
                    }
                    zzacj = this.zzpt;
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zza(zzacj);
            } else {
                this.name = zzaca.readString();
            }
        }
    }
}
