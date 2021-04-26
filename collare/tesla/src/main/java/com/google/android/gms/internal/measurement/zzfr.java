package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
public class zzfr extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzaav = "com.google.android.gms.internal.measurement.zzfr";
    private boolean zzaaw;
    private boolean zzaax;
    private final zzjt zzalo;

    zzfr(zzjt zzjt) {
        Preconditions.checkNotNull(zzjt);
        this.zzalo = zzjt;
    }

    public void onReceive(Context context, Intent intent) {
        this.zzalo.zzlc();
        String action = intent.getAction();
        this.zzalo.zzgi().zzjc().zzg("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzex = this.zzalo.zzkz().zzex();
            if (this.zzaax != zzex) {
                this.zzaax = zzex;
                this.zzalo.zzgh().zzc(new zzfs(this, zzex));
                return;
            }
            return;
        }
        this.zzalo.zzgi().zziy().zzg("NetworkBroadcastReceiver received unknown action", action);
    }

    public final void unregister() {
        this.zzalo.zzlc();
        this.zzalo.zzgh().zzab();
        this.zzalo.zzgh().zzab();
        if (this.zzaaw) {
            this.zzalo.zzgi().zzjc().log("Unregistering connectivity change receiver");
            this.zzaaw = false;
            this.zzaax = false;
            try {
                this.zzalo.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzalo.zzgi().zziv().zzg("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public final void zzeu() {
        this.zzalo.zzlc();
        this.zzalo.zzgh().zzab();
        if (!this.zzaaw) {
            this.zzalo.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzaax = this.zzalo.zzkz().zzex();
            this.zzalo.zzgi().zzjc().zzg("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzaax));
            this.zzaaw = true;
        }
    }
}
