package com.google.android.gms.internal.measurement;

public class zzaad {
    private static final zzzn zzbvd = zzzn.zztt();
    private zzzb zzbve;
    private volatile zzaaq zzbvf;
    private volatile zzzb zzbvg;

    private final zzaaq zzb(zzaaq zzaaq) {
        if (this.zzbvf == null) {
            synchronized (this) {
                if (this.zzbvf == null) {
                    try {
                        this.zzbvf = zzaaq;
                        this.zzbvg = zzzb.zzbte;
                    } catch (zzzy unused) {
                        this.zzbvf = zzaaq;
                        this.zzbvg = zzzb.zzbte;
                    }
                }
            }
        }
        return this.zzbvf;
    }

    private final zzzb zzue() {
        if (this.zzbvg != null) {
            return this.zzbvg;
        }
        synchronized (this) {
            if (this.zzbvg != null) {
                return this.zzbvg;
            }
            this.zzbvg = this.zzbvf == null ? zzzb.zzbte : this.zzbvf.zzue();
            return this.zzbvg;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaad)) {
            return false;
        }
        zzaad zzaad = (zzaad) obj;
        zzaaq zzaaq = this.zzbvf;
        zzaaq zzaaq2 = zzaad.zzbvf;
        return (zzaaq == null && zzaaq2 == null) ? zzue().equals(zzaad.zzue()) : (zzaaq == null || zzaaq2 == null) ? zzaaq != null ? zzaaq.equals(zzaad.zzb(zzaaq.zzuo())) : zzb(zzaaq2.zzuo()).equals(zzaaq2) : zzaaq.equals(zzaaq2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzaaq zzc(zzaaq zzaaq) {
        zzaaq zzaaq2 = this.zzbvf;
        this.zzbve = null;
        this.zzbvg = null;
        this.zzbvf = zzaaq;
        return zzaaq2;
    }
}
