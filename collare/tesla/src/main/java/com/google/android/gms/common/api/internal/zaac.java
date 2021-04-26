package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* access modifiers changed from: package-private */
public final class zaac implements PendingResult.StatusListener {
    private final /* synthetic */ BasePendingResult zafm;
    private final /* synthetic */ zaab zafn;

    zaac(zaab zaab, BasePendingResult basePendingResult) {
        this.zafn = zaab;
        this.zafm = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        this.zafn.zafk.remove(this.zafm);
    }
}
