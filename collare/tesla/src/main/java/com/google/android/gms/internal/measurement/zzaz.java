package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.analytics.zzk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzaz implements ServiceConnection {
    final /* synthetic */ zzax zzwo;
    private volatile zzci zzwp;
    private volatile boolean zzwq;

    protected zzaz(zzax zzax) {
        this.zzwo = zzax;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                try {
                    this.zzwo.zzu("Service connected with null binder");
                } finally {
                    notifyAll();
                }
            } else {
                zzci zzci = null;
                try {
                    String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                    if ("com.google.android.gms.analytics.internal.IAnalyticsService".equals(interfaceDescriptor)) {
                        if (iBinder != null) {
                            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.analytics.internal.IAnalyticsService");
                            zzci = queryLocalInterface instanceof zzci ? (zzci) queryLocalInterface : new zzcj(iBinder);
                        }
                        this.zzwo.zzq("Bound to IAnalyticsService interface");
                    } else {
                        this.zzwo.zze("Got binder with a wrong descriptor", interfaceDescriptor);
                    }
                } catch (RemoteException unused) {
                    this.zzwo.zzu("Service connect failed to get IAnalyticsService");
                }
                if (zzci == null) {
                    try {
                        ConnectionTracker.getInstance().unbindService(this.zzwo.getContext(), this.zzwo.zzwk);
                    } catch (IllegalArgumentException unused2) {
                    }
                } else if (!this.zzwq) {
                    this.zzwo.zzt("onServiceConnected received after the timeout limit");
                    this.zzwo.zzbw().zza(new zzba(this, zzci));
                } else {
                    this.zzwp = zzci;
                }
                notifyAll();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("AnalyticsServiceConnection.onServiceDisconnected");
        this.zzwo.zzbw().zza(new zzbb(this, componentName));
    }

    public final zzci zzcw() {
        zzk.zzab();
        Intent intent = new Intent("com.google.android.gms.analytics.service.START");
        intent.setComponent(new ComponentName("com.google.android.gms", "com.google.android.gms.analytics.service.AnalyticsService"));
        Context context = this.zzwo.getContext();
        intent.putExtra("app_package_name", context.getPackageName());
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            this.zzwp = null;
            this.zzwq = true;
            boolean bindService = instance.bindService(context, intent, this.zzwo.zzwk, 129);
            this.zzwo.zza("Bind to service requested", Boolean.valueOf(bindService));
            if (!bindService) {
                this.zzwq = false;
                return null;
            }
            try {
                wait(zzcc.zzzu.get().longValue());
            } catch (InterruptedException unused) {
                this.zzwo.zzt("Wait for service connect was interrupted");
            }
            this.zzwq = false;
            zzci zzci = this.zzwp;
            this.zzwp = null;
            if (zzci == null) {
                this.zzwo.zzu("Successfully bound to service but never got onServiceConnected callback");
            }
            return zzci;
        }
    }
}
