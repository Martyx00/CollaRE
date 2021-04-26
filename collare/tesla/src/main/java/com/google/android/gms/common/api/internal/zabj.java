package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;

final class zabj implements Runnable {
    private final /* synthetic */ GoogleApiManager.zaa zaiy;

    zabj(GoogleApiManager.zaa zaa) {
        this.zaiy = zaa;
    }

    public final void run() {
        this.zaiy.zabg();
    }
}
