package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.firebase_messaging.zza;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

final class g implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    int f3924a;

    /* renamed from: b  reason: collision with root package name */
    final Messenger f3925b;

    /* renamed from: c  reason: collision with root package name */
    l f3926c;

    /* renamed from: d  reason: collision with root package name */
    final Queue<n<?>> f3927d;
    final SparseArray<n<?>> e;
    final /* synthetic */ e f;

    private g(e eVar) {
        this.f = eVar;
        this.f3924a = 0;
        this.f3925b = new Messenger(new zza(Looper.getMainLooper(), new h(this)));
        this.f3927d = new ArrayDeque();
        this.e = new SparseArray<>();
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean a(n nVar) {
        switch (this.f3924a) {
            case 0:
                this.f3927d.add(nVar);
                Preconditions.checkState(this.f3924a == 0);
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Starting bind to GmsCore");
                }
                this.f3924a = 1;
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                if (!ConnectionTracker.getInstance().bindService(e.a(this.f), intent, this, 1)) {
                    a(0, "Unable to bind to service");
                } else {
                    e.b(this.f).schedule(new i(this), 30, TimeUnit.SECONDS);
                }
                return true;
            case 1:
                this.f3927d.add(nVar);
                return true;
            case 2:
                this.f3927d.add(nVar);
                c();
                return true;
            case 3:
            case 4:
                return false;
            default:
                int i = this.f3924a;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i);
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
        r1.a(new com.google.firebase.iid.o(4, "Not supported by GmsCore"));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006b, code lost:
        r1.a(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x006e, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a(android.os.Message r5) {
        /*
        // Method dump skipped, instructions count: 114
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.g.a(android.os.Message):boolean");
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            a(0, "Null service connection");
            return;
        }
        try {
            this.f3926c = new l(iBinder);
            this.f3924a = 2;
            c();
        } catch (RemoteException e2) {
            a(0, e2.getMessage());
        }
    }

    private final void c() {
        e.b(this.f).execute(new j(this));
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        a(2, "Service disconnected");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", valueOf.length() != 0 ? "Disconnected: ".concat(valueOf) : new String("Disconnected: "));
        }
        switch (this.f3924a) {
            case 0:
                throw new IllegalStateException();
            case 1:
            case 2:
                if (Log.isLoggable("MessengerIpcClient", 2)) {
                    Log.v("MessengerIpcClient", "Unbinding service");
                }
                this.f3924a = 4;
                ConnectionTracker.getInstance().unbindService(e.a(this.f), this);
                o oVar = new o(i, str);
                for (n<?> nVar : this.f3927d) {
                    nVar.a(oVar);
                }
                this.f3927d.clear();
                for (int i2 = 0; i2 < this.e.size(); i2++) {
                    this.e.valueAt(i2).a(oVar);
                }
                this.e.clear();
                return;
            case 3:
                this.f3924a = 4;
                return;
            case 4:
                return;
            default:
                int i3 = this.f3924a;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a() {
        if (this.f3924a == 2 && this.f3927d.isEmpty() && this.e.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.f3924a = 3;
            ConnectionTracker.getInstance().unbindService(e.a(this.f), this);
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void b() {
        if (this.f3924a == 1) {
            a(1, "Timed out while binding");
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized void a(int i) {
        n<?> nVar = this.e.get(i);
        if (nVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.e.remove(i);
            nVar.a(new o(3, "Timed out waiting for response"));
            a();
        }
    }
}
