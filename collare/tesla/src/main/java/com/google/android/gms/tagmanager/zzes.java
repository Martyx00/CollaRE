package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzl;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public final class zzes implements zzag {
    private boolean closed;
    private final String zzaxm;
    private String zzaym;
    private zzdh<zzl> zzbcn;
    private zzal zzbco;
    private final ScheduledExecutorService zzbcq;
    private final zzev zzbcr;
    private ScheduledFuture<?> zzbcs;
    private final Context zzqx;

    public zzes(Context context, String str, zzal zzal) {
        this(context, str, zzal, null, null);
    }

    @VisibleForTesting
    private zzes(Context context, String str, zzal zzal, zzew zzew, zzev zzev) {
        this.zzbco = zzal;
        this.zzqx = context;
        this.zzaxm = str;
        this.zzbcq = new zzet(this).zzoo();
        this.zzbcr = new zzeu(this);
    }

    private final synchronized void zzon() {
        if (this.closed) {
            throw new IllegalStateException("called method after closed");
        }
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        zzon();
        if (this.zzbcs != null) {
            this.zzbcs.cancel(false);
        }
        this.zzbcq.shutdown();
        this.closed = true;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(long j, String str) {
        String str2 = this.zzaxm;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 55);
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j);
        zzdi.v(sb.toString());
        zzon();
        if (this.zzbcn != null) {
            if (this.zzbcs != null) {
                this.zzbcs.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.zzbcq;
            zzer zza = this.zzbcr.zza(this.zzbco);
            zza.zza(this.zzbcn);
            zza.zzct(this.zzaym);
            zza.zzdl(str);
            this.zzbcs = scheduledExecutorService.schedule(zza, j, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zza(zzdh<zzl> zzdh) {
        zzon();
        this.zzbcn = zzdh;
    }

    @Override // com.google.android.gms.tagmanager.zzag
    public final synchronized void zzct(String str) {
        zzon();
        this.zzaym = str;
    }
}
