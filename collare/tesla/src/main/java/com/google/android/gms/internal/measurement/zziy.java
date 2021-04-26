package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;

public final class zziy implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zzik zzaqv;
    private volatile boolean zzarb;
    private volatile zzfh zzarc;

    protected zziy(zzik zzik) {
        this.zzaqv = zzik;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0022 */
    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r4 = "MeasurementServiceConnection.onConnected"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r4)
            monitor-enter(r3)
            r4 = 0
            com.google.android.gms.internal.measurement.zzfh r0 = r3.zzarc     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            android.os.IInterface r0 = r0.getService()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzfa r0 = (com.google.android.gms.internal.measurement.zzfa) r0     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r3.zzarc = r4     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzik r1 = r3.zzaqv     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzgi r1 = r1.zzgh()     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            com.google.android.gms.internal.measurement.zzjb r2 = new com.google.android.gms.internal.measurement.zzjb     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r2.<init>(r3, r0)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            r1.zzc(r2)     // Catch:{ DeadObjectException | IllegalStateException -> 0x0022 }
            goto L_0x0027
        L_0x0020:
            r4 = move-exception
            goto L_0x0029
        L_0x0022:
            r3.zzarc = r4     // Catch:{ all -> 0x0020 }
            r4 = 0
            r3.zzarb = r4     // Catch:{ all -> 0x0020 }
        L_0x0027:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            return
        L_0x0029:
            monitor-exit(r3)     // Catch:{ all -> 0x0020 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zziy.onConnected(android.os.Bundle):void");
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionFailed");
        zzfi zzjy = this.zzaqv.zzacv.zzjy();
        if (zzjy != null) {
            zzjy.zziy().zzg("Service connection failed", connectionResult);
        }
        synchronized (this) {
            this.zzarb = false;
            this.zzarc = null;
        }
        this.zzaqv.zzgh().zzc(new zzjd(this));
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
        this.zzaqv.zzgi().zzjb().log("Service connection suspended");
        this.zzaqv.zzgh().zzc(new zzjc(this));
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceConnected");
        synchronized (this) {
            if (iBinder == null) {
                this.zzarb = false;
                this.zzaqv.zzgi().zziv().log("Service connected with null binder");
                return;
            }
            zzfa zzfa = null;
            try {
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(interfaceDescriptor)) {
                    if (iBinder != null) {
                        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
                        zzfa = queryLocalInterface instanceof zzfa ? (zzfa) queryLocalInterface : new zzfc(iBinder);
                    }
                    this.zzaqv.zzgi().zzjc().log("Bound to IMeasurementService interface");
                } else {
                    this.zzaqv.zzgi().zziv().zzg("Got binder with a wrong descriptor", interfaceDescriptor);
                }
            } catch (RemoteException unused) {
                this.zzaqv.zzgi().zziv().log("Service connect failed to get IMeasurementService");
            }
            if (zzfa == null) {
                this.zzarb = false;
                try {
                    ConnectionTracker.getInstance().unbindService(this.zzaqv.getContext(), this.zzaqv.zzaqo);
                } catch (IllegalArgumentException unused2) {
                }
            } else {
                this.zzaqv.zzgh().zzc(new zziz(this, zzfa));
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
        this.zzaqv.zzgi().zzjb().log("Service disconnected");
        this.zzaqv.zzgh().zzc(new zzja(this, componentName));
    }

    public final void zzc(Intent intent) {
        this.zzaqv.zzab();
        Context context = this.zzaqv.getContext();
        ConnectionTracker instance = ConnectionTracker.getInstance();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.zzgi().zzjc().log("Connection attempt already in progress");
                return;
            }
            this.zzaqv.zzgi().zzjc().log("Using local app measurement service");
            this.zzarb = true;
            instance.bindService(context, intent, this.zzaqv.zzaqo, 129);
        }
    }

    public final void zzkt() {
        this.zzaqv.zzab();
        Context context = this.zzaqv.getContext();
        synchronized (this) {
            if (this.zzarb) {
                this.zzaqv.zzgi().zzjc().log("Connection attempt already in progress");
            } else if (this.zzarc != null) {
                this.zzaqv.zzgi().zzjc().log("Already awaiting connection attempt");
            } else {
                this.zzarc = new zzfh(context, Looper.getMainLooper(), this, this);
                this.zzaqv.zzgi().zzjc().log("Connecting to remote service");
                this.zzarb = true;
                this.zzarc.checkAvailabilityAndConnect();
            }
        }
    }
}
