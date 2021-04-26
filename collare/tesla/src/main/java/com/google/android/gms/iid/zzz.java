package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public abstract class zzz<T> {
    final int what;
    final int zzck;
    final TaskCompletionSource<T> zzcl = new TaskCompletionSource<>();
    final Bundle zzcm;

    zzz(int i, int i2, Bundle bundle) {
        this.zzck = i;
        this.what = i2;
        this.zzcm = bundle;
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzck;
        zzu();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=false}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzaa zzaa) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzaa);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzcl.setException(zzaa);
    }

    /* access modifiers changed from: package-private */
    public abstract void zzh(Bundle bundle);

    /* access modifiers changed from: package-private */
    public abstract boolean zzu();
}
