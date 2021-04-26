package com.google.android.gms.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* access modifiers changed from: package-private */
public final class zzt implements ServiceConnection {
    int state;
    final Messenger zzcc;
    zzy zzcd;
    final Queue<zzz<?>> zzce;
    final SparseArray<zzz<?>> zzcf;
    final /* synthetic */ zzr zzcg;

    private zzt(zzr zzr) {
        this.zzcg = zzr;
        this.state = 0;
        this.zzcc = new Messenger(new Handler(Looper.getMainLooper(), new zzu(this)));
        this.zzce = new ArrayDeque();
        this.zzcf = new SparseArray<>();
    }

    private final void zzr() {
        this.zzcg.zzbz.execute(new zzw(this));
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zzd(0, "Null service connection");
            return;
        }
        try {
            this.zzcd = new zzy(iBinder);
            this.state = 2;
            zzr();
        } catch (RemoteException e) {
            zzd(0, e.getMessage());
        }
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzd(2, "Service disconnected");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzd(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        switch (this.state) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.state = 4;
                ConnectionTracker.getInstance().unbindService(this.zzcg.zzk, this);
                zzaa zzaa = new zzaa(i, str);
                for (zzz<?> zzz : this.zzce) {
                    zzz.zzd(zzaa);
                }
                this.zzce.clear();
                for (int i2 = 0; i2 < this.zzcf.size(); i2++) {
                    this.zzcf.valueAt(i2).zzd(zzaa);
                }
                this.zzcf.clear();
                return;
            case 3:
                this.state = 4;
                return;
            case 4:
                return;
            default:
                int i3 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0052, code lost:
        r5 = r5.getData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        if (r5.getBoolean("unsupported", false) == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005f, code lost:
        r1.zzd(new com.google.android.gms.iid.zzaa(4, "Not supported by GmsCore"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006b, code lost:
        r1.zzh(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzd(android.os.Message r5) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.iid.zzt.zzd(android.os.Message):boolean");
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zze(zzz zzz) {
        switch (this.state) {
            case 0:
                this.zzce.add(zzz);
                Preconditions.checkState(this.state == 0);
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                }
                this.state = 1;
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                if (!ConnectionTracker.getInstance().bindService(this.zzcg.zzk, intent, this, 1)) {
                    zzd(0, "Unable to bind to service");
                } else {
                    this.zzcg.zzbz.schedule(new zzv(this), 30, TimeUnit.SECONDS);
                }
                return true;
            case 1:
                this.zzce.add(zzz);
                return true;
            case 2:
                this.zzce.add(zzz);
                zzr();
                return true;
            case 3:
            case 4:
                return false;
            default:
                int i = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzg(int i) {
        zzz<?> zzz = this.zzcf.get(i);
        if (zzz != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzcf.remove(i);
            zzz.zzd(new zzaa(3, "Timed out waiting for response"));
            zzs();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzs() {
        if (this.state == 2 && this.zzce.isEmpty() && this.zzcf.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzcg.zzk, this);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzt() {
        if (this.state == 1) {
            zzd(1, "Timed out while binding");
        }
    }
}
