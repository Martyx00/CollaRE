package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzwt extends ContentObserver {
    zzwt(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zzws.zzsg().set(true);
    }
}
