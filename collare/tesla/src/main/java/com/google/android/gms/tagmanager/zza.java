package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zza {
    private static Object zzaxa = new Object();
    private static zza zzaxb;
    private final Clock clock;
    private volatile boolean closed;
    private volatile long zzawt;
    private volatile long zzawu;
    private volatile long zzawv;
    private volatile long zzaww;
    private final Thread zzawx;
    private final Object zzawy;
    private zzd zzawz;
    private final Context zzqx;
    private volatile AdvertisingIdClient.Info zzuz;

    private zza(Context context) {
        this(context, null, DefaultClock.getInstance());
    }

    @VisibleForTesting
    private zza(Context context, zzd zzd, Clock clock2) {
        this.zzawt = 900000;
        this.zzawu = 30000;
        this.closed = false;
        this.zzawy = new Object();
        this.zzawz = new zzb(this);
        this.clock = clock2;
        this.zzqx = context != null ? context.getApplicationContext() : context;
        this.zzawv = this.clock.currentTimeMillis();
        this.zzawx = new Thread(new zzc(this));
    }

    public static zza zzh(Context context) {
        if (zzaxb == null) {
            synchronized (zzaxa) {
                if (zzaxb == null) {
                    zza zza = new zza(context);
                    zzaxb = zza;
                    zza.zzawx.start();
                }
            }
        }
        return zzaxb;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzme() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.closed     // Catch:{ InterruptedException -> 0x0010 }
            if (r0 != 0) goto L_0x0010
            r2.zzmf()     // Catch:{ InterruptedException -> 0x0010 }
            r0 = 500(0x1f4, double:2.47E-321)
            r2.wait(r0)     // Catch:{ InterruptedException -> 0x0010 }
            goto L_0x0010
        L_0x000e:
            r0 = move-exception
            goto L_0x0012
        L_0x0010:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            return
        L_0x0012:
            monitor-exit(r2)     // Catch:{ all -> 0x000e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zza.zzme():void");
    }

    private final void zzmf() {
        if (this.clock.currentTimeMillis() - this.zzawv > this.zzawu) {
            synchronized (this.zzawy) {
                this.zzawy.notify();
            }
            this.zzawv = this.clock.currentTimeMillis();
        }
    }

    private final void zzmg() {
        if (this.clock.currentTimeMillis() - this.zzaww > 3600000) {
            this.zzuz = null;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private final void zzmh() {
        Process.setThreadPriority(10);
        while (!this.closed) {
            AdvertisingIdClient.Info zzmi = this.zzawz.zzmi();
            if (zzmi != null) {
                this.zzuz = zzmi;
                this.zzaww = this.clock.currentTimeMillis();
                zzdi.zzcz("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (this) {
                notifyAll();
            }
            try {
                synchronized (this.zzawy) {
                    this.zzawy.wait(this.zzawt);
                }
            } catch (InterruptedException unused) {
                zzdi.zzcz("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    @VisibleForTesting
    public final void close() {
        this.closed = true;
        this.zzawx.interrupt();
    }

    public final boolean isLimitAdTrackingEnabled() {
        if (this.zzuz == null) {
            zzme();
        } else {
            zzmf();
        }
        zzmg();
        if (this.zzuz == null) {
            return true;
        }
        return this.zzuz.isLimitAdTrackingEnabled();
    }

    public final String zzmd() {
        if (this.zzuz == null) {
            zzme();
        } else {
            zzmf();
        }
        zzmg();
        if (this.zzuz == null) {
            return null;
        }
        return this.zzuz.getId();
    }
}
