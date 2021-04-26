package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzck {
    private final Clock clock;
    private final long zzaao;
    private final int zzaap;
    private double zzaaq;
    private long zzaar;
    private final Object zzaas;
    private final String zztz;

    private zzck(int i, long j, String str, Clock clock2) {
        this.zzaas = new Object();
        this.zzaap = 60;
        this.zzaaq = (double) this.zzaap;
        this.zzaao = 2000;
        this.zztz = str;
        this.clock = clock2;
    }

    public zzck(String str, Clock clock2) {
        this(60, 2000, str, clock2);
    }

    public final boolean zzes() {
        synchronized (this.zzaas) {
            long currentTimeMillis = this.clock.currentTimeMillis();
            if (this.zzaaq < ((double) this.zzaap)) {
                double d2 = ((double) (currentTimeMillis - this.zzaar)) / ((double) this.zzaao);
                if (d2 > 0.0d) {
                    this.zzaaq = Math.min((double) this.zzaap, this.zzaaq + d2);
                }
            }
            this.zzaar = currentTimeMillis;
            if (this.zzaaq >= 1.0d) {
                this.zzaaq -= 1.0d;
                return true;
            }
            String str = this.zztz;
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
            sb.append("Excessive ");
            sb.append(str);
            sb.append(" detected; call ignored.");
            zzcl.zzab(sb.toString());
            return false;
        }
    }
}
