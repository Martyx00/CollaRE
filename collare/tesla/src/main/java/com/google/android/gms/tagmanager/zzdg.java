package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzdg implements zzej {
    private final Clock clock;
    private final long zzaao = 900000;
    private final int zzaap = 5;
    private double zzaaq = ((double) Math.min(1, 5));
    private long zzaar;
    private final Object zzaas = new Object();
    private final long zzbbi = 5000;
    private final String zztz;

    public zzdg(int i, int i2, long j, long j2, String str, Clock clock2) {
        this.zztz = str;
        this.clock = clock2;
    }

    @Override // com.google.android.gms.tagmanager.zzej
    public final boolean zzes() {
        synchronized (this.zzaas) {
            long currentTimeMillis = this.clock.currentTimeMillis();
            if (currentTimeMillis - this.zzaar < this.zzbbi) {
                String str = this.zztz;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34);
                sb.append("Excessive ");
                sb.append(str);
                sb.append(" detected; call ignored.");
                zzdi.zzab(sb.toString());
                return false;
            }
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
            String str2 = this.zztz;
            StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 34);
            sb2.append("Excessive ");
            sb2.append(str2);
            sb2.append(" detected; call ignored.");
            zzdi.zzab(sb2.toString());
            return false;
        }
    }
}
