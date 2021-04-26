package com.google.firebase.iid;

import android.support.v4.util.a;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public final class t {

    /* renamed from: a  reason: collision with root package name */
    private final Executor f3946a;

    /* renamed from: b  reason: collision with root package name */
    private final Map<Pair<String, String>, Task<String>> f3947b = new a();

    t(Executor executor) {
        this.f3946a = executor;
    }

    /* access modifiers changed from: package-private */
    public final synchronized Task<String> a(String str, String str2, v vVar) {
        Pair<String, String> pair = new Pair<>(str, str2);
        Task<String> task = this.f3947b.get(pair);
        if (task != null) {
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                String valueOf = String.valueOf(pair);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 29);
                sb.append("Joining ongoing request for: ");
                sb.append(valueOf);
                Log.d("FirebaseInstanceId", sb.toString());
            }
            return task;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf2 = String.valueOf(pair);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 24);
            sb2.append("Making new request for: ");
            sb2.append(valueOf2);
            Log.d("FirebaseInstanceId", sb2.toString());
        }
        Task<TContinuationResult> continueWithTask = vVar.a().continueWithTask(this.f3946a, new u(this, pair));
        this.f3947b.put(pair, continueWithTask);
        return continueWithTask;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Task a(Pair pair, Task task) {
        synchronized (this) {
            this.f3947b.remove(pair);
        }
        return task;
    }
}
