package com.google.android.gms.common.api.internal;

import android.support.v4.util.a;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zak {
    private final a<zai<?>, ConnectionResult> zaay = new a<>();
    private final a<zai<?>, String> zadb = new a<>();
    private final TaskCompletionSource<Map<zai<?>, String>> zadc = new TaskCompletionSource<>();
    private int zadd;
    private boolean zade = false;

    public zak(Iterable<? extends GoogleApi<?>> iterable) {
        Iterator<? extends GoogleApi<?>> it = iterable.iterator();
        while (it.hasNext()) {
            this.zaay.put(((GoogleApi) it.next()).zak(), null);
        }
        this.zadd = this.zaay.keySet().size();
    }

    public final Set<zai<?>> zap() {
        return this.zaay.keySet();
    }

    public final Task<Map<zai<?>, String>> getTask() {
        return this.zadc.getTask();
    }

    public final void zaa(zai<?> zai, ConnectionResult connectionResult, String str) {
        this.zaay.put(zai, connectionResult);
        this.zadb.put(zai, str);
        this.zadd--;
        if (!connectionResult.isSuccess()) {
            this.zade = true;
        }
        if (this.zadd != 0) {
            return;
        }
        if (this.zade) {
            this.zadc.setException(new AvailabilityException(this.zaay));
            return;
        }
        this.zadc.setResult(this.zadb);
    }
}
