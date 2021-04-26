package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad<Boolean> {
    private final ListenerHolder.ListenerKey<?> zact;

    public zah(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zact = listenerKey;
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zab
    public final /* bridge */ /* synthetic */ void zaa(zaab zaab, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void zad(GoogleApiManager.zaa<?> zaa) {
        zabw remove = zaa.zabk().remove(this.zact);
        if (remove != null) {
            remove.zajy.unregisterListener(zaa.zaab(), this.zacn);
            remove.zajx.clearListener();
            return;
        }
        this.zacn.trySetResult(false);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        if (zabw == null) {
            return null;
        }
        return zabw.zajx.getRequiredFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        zabw zabw = zaa.zabk().get(this.zact);
        return zabw != null && zabw.zajx.shouldAutoResolveMissingFeatures();
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zab
    public final /* bridge */ /* synthetic */ void zaa(RuntimeException runtimeException) {
        super.zaa(runtimeException);
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zab
    public final /* bridge */ /* synthetic */ void zaa(Status status) {
        super.zaa(status);
    }
}
