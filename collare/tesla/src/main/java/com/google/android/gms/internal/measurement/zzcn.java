package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

/* access modifiers changed from: package-private */
public class zzcn extends BroadcastReceiver {
    @VisibleForTesting
    private static final String zzaav = "com.google.android.gms.internal.measurement.zzcn";
    private boolean zzaaw;
    private boolean zzaax;
    private final zzat zzvm;

    zzcn(zzat zzat) {
        Preconditions.checkNotNull(zzat);
        this.zzvm = zzat;
    }

    private final void zzev() {
        this.zzvm.zzbu();
        this.zzvm.zzby();
    }

    @VisibleForTesting
    private final boolean zzex() {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzvm.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (SecurityException unused) {
        }
    }

    public final boolean isConnected() {
        if (!this.zzaaw) {
            this.zzvm.zzbu().zzt("Connectivity unknown. Receiver not registered");
        }
        return this.zzaax;
    }

    public void onReceive(Context context, Intent intent) {
        zzev();
        String action = intent.getAction();
        this.zzvm.zzbu().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzex = zzex();
            if (this.zzaax != zzex) {
                this.zzaax = zzex;
                zzai zzby = this.zzvm.zzby();
                zzby.zza("Network connectivity status changed", Boolean.valueOf(zzex));
                zzby.zzbw().zza(new zzak(zzby, zzex));
            }
        } else if (!"com.google.analytics.RADIO_POWERED".equals(action)) {
            this.zzvm.zzbu().zzd("NetworkBroadcastReceiver received unknown action", action);
        } else if (!intent.hasExtra(zzaav)) {
            zzai zzby2 = this.zzvm.zzby();
            zzby2.zzq("Radio powered up");
            zzby2.zzbo();
        }
    }

    public final void unregister() {
        if (this.zzaaw) {
            this.zzvm.zzbu().zzq("Unregistering connectivity change receiver");
            this.zzaaw = false;
            this.zzaax = false;
            try {
                this.zzvm.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzvm.zzbu().zze("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    public final void zzeu() {
        zzev();
        if (!this.zzaaw) {
            Context context = this.zzvm.getContext();
            context.registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            IntentFilter intentFilter = new IntentFilter("com.google.analytics.RADIO_POWERED");
            intentFilter.addCategory(context.getPackageName());
            context.registerReceiver(this, intentFilter);
            this.zzaax = zzex();
            this.zzvm.zzbu().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzaax));
            this.zzaaw = true;
        }
    }

    @VisibleForTesting
    public final void zzew() {
        Context context = this.zzvm.getContext();
        Intent intent = new Intent("com.google.analytics.RADIO_POWERED");
        intent.addCategory(context.getPackageName());
        intent.putExtra(zzaav, true);
        context.sendOrderedBroadcast(intent, null);
    }
}
