package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zaab {
    private final Map<BasePendingResult<?>, Boolean> zafk = Collections.synchronizedMap(new WeakHashMap());
    private final Map<TaskCompletionSource<?>, Boolean> zafl = Collections.synchronizedMap(new WeakHashMap());

    /* access modifiers changed from: package-private */
    public final void zaa(BasePendingResult<? extends Result> basePendingResult, boolean z) {
        this.zafk.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.addStatusListener(new zaac(this, basePendingResult));
    }

    /* access modifiers changed from: package-private */
    public final <TResult> void zaa(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zafl.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zaad(this, taskCompletionSource));
    }

    /* access modifiers changed from: package-private */
    public final boolean zaag() {
        return !this.zafk.isEmpty() || !this.zafl.isEmpty();
    }

    public final void zaah() {
        zaa(false, GoogleApiManager.zahx);
    }

    public final void zaai() {
        zaa(true, zacp.zakx);
    }

    private final void zaa(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.zafk) {
            hashMap = new HashMap(this.zafk);
        }
        synchronized (this.zafl) {
            hashMap2 = new HashMap(this.zafl);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).zab(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }
}
