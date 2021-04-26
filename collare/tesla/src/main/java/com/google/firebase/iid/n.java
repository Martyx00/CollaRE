package com.google.firebase.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

/* access modifiers changed from: package-private */
public abstract class n<T> {

    /* renamed from: a  reason: collision with root package name */
    final int f3935a;

    /* renamed from: b  reason: collision with root package name */
    final TaskCompletionSource<T> f3936b = new TaskCompletionSource<>();

    /* renamed from: c  reason: collision with root package name */
    final int f3937c;

    /* renamed from: d  reason: collision with root package name */
    final Bundle f3938d;

    n(int i, int i2, Bundle bundle) {
        this.f3935a = i;
        this.f3937c = i2;
        this.f3938d = bundle;
    }

    /* access modifiers changed from: package-private */
    public abstract void a(Bundle bundle);

    /* access modifiers changed from: package-private */
    public abstract boolean a();

    /* access modifiers changed from: package-private */
    public final void a(T t) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(t);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16 + String.valueOf(valueOf2).length());
            sb.append("Finishing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.f3936b.setResult(t);
    }

    /* access modifiers changed from: package-private */
    public final void a(o oVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(oVar);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.f3936b.setException(oVar);
    }

    public String toString() {
        int i = this.f3937c;
        int i2 = this.f3935a;
        boolean a2 = a();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=");
        sb.append(a2);
        sb.append("}");
        return sb.toString();
    }
}
