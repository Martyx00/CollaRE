package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzwv extends ContentObserver {
    private final /* synthetic */ zzwu zzbpf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzwv(zzwu zzwu, Handler handler) {
        super(null);
        this.zzbpf = zzwu;
    }

    public final void onChange(boolean z) {
        this.zzbpf.zzsi();
        zzwu.zza(this.zzbpf);
    }
}
