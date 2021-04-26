package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk
public abstract class DataHolderNotifier<L> implements ListenerHolder.Notifier<L> {
    private final DataHolder mDataHolder;

    @KeepForSdk
    protected DataHolderNotifier(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public abstract void notifyListener(L l, DataHolder dataHolder);

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    @KeepForSdk
    public final void notifyListener(L l) {
        notifyListener(l, this.mDataHolder);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    @KeepForSdk
    public void onNotifyListenerFailed() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }
}
