package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.GmsClientEventManager;

/* access modifiers changed from: package-private */
public final class zaax implements GmsClientEventManager.GmsClientEventState {
    private final /* synthetic */ zaaw zahh;

    zaax(zaaw zaaw) {
        this.zahh = zaaw;
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final Bundle getConnectionHint() {
        return null;
    }

    @Override // com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final boolean isConnected() {
        return this.zahh.isConnected();
    }
}
