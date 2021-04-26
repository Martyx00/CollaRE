package com.google.android.gms.internal.measurement;

public final class zzkt extends zzacd<zzkt> {
    public zzku[] zzavf = zzku.zzma();

    public zzkt() {
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkt)) {
            return false;
        }
        zzkt zzkt = (zzkt) obj;
        if (!zzach.equals(this.zzavf, zzkt.zzavf)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkt.zzbzd == null || zzkt.zzbzd.isEmpty() : this.zzbzd.equals(zzkt.zzbzd);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzavf)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final int zza() {
        int zza = super.zza();
        zzku[] zzkuArr = this.zzavf;
        if (zzkuArr != null && zzkuArr.length > 0) {
            int i = 0;
            while (true) {
                zzku[] zzkuArr2 = this.zzavf;
                if (i >= zzkuArr2.length) {
                    break;
                }
                zzku zzku = zzkuArr2[i];
                if (zzku != null) {
                    zza += zzacb.zzb(1, zzku);
                }
                i++;
            }
        }
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzacj, com.google.android.gms.internal.measurement.zzacd
    public final void zza(zzacb zzacb) {
        zzku[] zzkuArr = this.zzavf;
        if (zzkuArr != null && zzkuArr.length > 0) {
            int i = 0;
            while (true) {
                zzku[] zzkuArr2 = this.zzavf;
                if (i >= zzkuArr2.length) {
                    break;
                }
                zzku zzku = zzkuArr2[i];
                if (zzku != null) {
                    zzacb.zza(1, zzku);
                }
                i++;
            }
        }
        super.zza(zzacb);
    }

    @Override // com.google.android.gms.internal.measurement.zzacj
    public final /* synthetic */ zzacj zzb(zzaca zzaca) {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 10) {
                int zzb = zzacm.zzb(zzaca, 10);
                zzku[] zzkuArr = this.zzavf;
                int length = zzkuArr == null ? 0 : zzkuArr.length;
                zzku[] zzkuArr2 = new zzku[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzavf, 0, zzkuArr2, 0, length);
                }
                while (length < zzkuArr2.length - 1) {
                    zzkuArr2[length] = new zzku();
                    zzaca.zza(zzkuArr2[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkuArr2[length] = new zzku();
                zzaca.zza(zzkuArr2[length]);
                this.zzavf = zzkuArr2;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
