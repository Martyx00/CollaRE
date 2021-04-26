package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

final class zzfl implements zzej {
    private final Clock clock;
    private final long zzaao;
    private final int zzaap;
    private double zzaaq;
    private final Object zzaas;
    private long zzbdt;

    public zzfl() {
        this(60, 2000);
    }

    private zzfl(int i, long j) {
        this.zzaas = new Object();
        this.zzaap = 60;
        this.zzaaq = (double) this.zzaap;
        this.zzaao = 2000;
        this.clock = DefaultClock.getInstance();
    }

    @Override // com.google.android.gms.tagmanager.zzej
    public final boolean zzes() {
        synchronized (this.zzaas) {
            long currentTimeMillis = this.clock.currentTimeMillis();
            if (this.zzaaq < ((double) this.zzaap)) {
                double d2 = ((double) (currentTimeMillis - this.zzbdt)) / ((double) this.zzaao);
                if (d2 > 0.0d) {
                    this.zzaaq = Math.min((double) this.zzaap, this.zzaaq + d2);
                }
            }
            this.zzbdt = currentTimeMillis;
            if (this.zzaaq >= 1.0d) {
                this.zzaaq -= 1.0d;
                return true;
            }
            zzdi.zzab("No more tokens available.");
            return false;
        }
    }
}
