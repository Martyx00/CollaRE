package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
@VisibleForTesting
public final class zzfn extends zzfm {
    private static final Object zzbdu = new Object();
    private static zzfn zzbef;
    private boolean connected = true;
    private zzcc zzbbo = new zzfo(this);
    private Context zzbdv;
    private zzcb zzbdw;
    private volatile zzby zzbdx;
    private int zzbdy = 1800000;
    private boolean zzbdz = true;
    private boolean zzbea = false;
    private boolean zzbeb = true;
    private zzfq zzbec;
    private zzdn zzbed;
    private boolean zzbee = false;

    private zzfn() {
    }

    /* access modifiers changed from: private */
    public final boolean isPowerSaveMode() {
        return this.zzbee || !this.connected || this.zzbdy <= 0;
    }

    public static zzfn zzpc() {
        if (zzbef == null) {
            zzbef = new zzfn();
        }
        return zzbef;
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void dispatch() {
        if (!this.zzbea) {
            zzdi.v("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zzbdz = true;
            return;
        }
        this.zzbdx.zzh(new zzfp(this));
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zza(Context context, zzby zzby) {
        if (this.zzbdv == null) {
            this.zzbdv = context.getApplicationContext();
            if (this.zzbdx == null) {
                this.zzbdx = zzby;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public final synchronized void zza(boolean z, boolean z2) {
        boolean isPowerSaveMode = isPowerSaveMode();
        this.zzbee = z;
        this.connected = z2;
        if (isPowerSaveMode() != isPowerSaveMode) {
            if (isPowerSaveMode()) {
                this.zzbec.cancel();
                zzdi.v("PowerSaveMode initiated.");
                return;
            }
            this.zzbec.zzh((long) this.zzbdy);
            zzdi.v("PowerSaveMode terminated.");
        }
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void zzp(boolean z) {
        zza(this.zzbee, z);
    }

    @Override // com.google.android.gms.tagmanager.zzfm
    public final synchronized void zzpb() {
        if (!isPowerSaveMode()) {
            this.zzbec.zzpf();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized zzcb zzpd() {
        if (this.zzbdw == null) {
            if (this.zzbdv != null) {
                this.zzbdw = new zzeb(this.zzbbo, this.zzbdv);
            } else {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
        }
        if (this.zzbec == null) {
            this.zzbec = new zzfr(this, null);
            if (this.zzbdy > 0) {
                this.zzbec.zzh((long) this.zzbdy);
            }
        }
        this.zzbea = true;
        if (this.zzbdz) {
            dispatch();
            this.zzbdz = false;
        }
        if (this.zzbed == null && this.zzbeb) {
            this.zzbed = new zzdn(this);
            zzdn zzdn = this.zzbed;
            Context context = this.zzbdv;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context.registerReceiver(zzdn, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context.getPackageName());
            context.registerReceiver(zzdn, intentFilter2);
        }
        return this.zzbdw;
    }
}
