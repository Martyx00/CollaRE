package com.google.android.gms.internal.measurement;

/* access modifiers changed from: package-private */
public class zzzh extends zzzg {
    protected final byte[] zzbtj;

    zzzh(byte[] bArr) {
        this.zzbtj = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzzb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzzb) || size() != ((zzzb) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzzh)) {
            return obj.equals(this);
        }
        zzzh zzzh = (zzzh) obj;
        int zztm = zztm();
        int zztm2 = zzzh.zztm();
        if (zztm == 0 || zztm2 == 0 || zztm == zztm2) {
            return zza(zzzh, 0, size());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzzb
    public int size() {
        return this.zzbtj.length;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.internal.measurement.zzzb
    public final int zza(int i, int i2, int i3) {
        return zzzw.zza(i, this.zzbtj, zztn(), i3);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzzg
    public final boolean zza(zzzb zzzb, int i, int i2) {
        if (i2 > zzzb.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzzb.size()) {
            int size2 = zzzb.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzzb instanceof zzzh)) {
            return zzzb.zzb(0, i2).equals(zzb(0, i2));
        } else {
            zzzh zzzh = (zzzh) zzzb;
            byte[] bArr = this.zzbtj;
            byte[] bArr2 = zzzh.zzbtj;
            int zztn = zztn() + i2;
            int zztn2 = zztn();
            int zztn3 = zzzh.zztn();
            while (zztn2 < zztn) {
                if (bArr[zztn2] != bArr2[zztn3]) {
                    return false;
                }
                zztn2++;
                zztn3++;
            }
            return true;
        }
    }

    @Override // com.google.android.gms.internal.measurement.zzzb
    public byte zzae(int i) {
        return this.zzbtj[i];
    }

    @Override // com.google.android.gms.internal.measurement.zzzb
    public final zzzb zzb(int i, int i2) {
        int zzb = zzb(0, i2, size());
        return zzb == 0 ? zzzb.zzbte : new zzze(this.zzbtj, zztn(), zzb);
    }

    /* access modifiers changed from: protected */
    public int zztn() {
        return 0;
    }
}
