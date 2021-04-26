package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* access modifiers changed from: package-private */
public final class zzfr implements zzfq {
    private Handler handler;
    final /* synthetic */ zzfn zzbeg;

    private zzfr(zzfn zzfn) {
        this.zzbeg = zzfn;
        this.handler = new Handler(this.zzbeg.zzbdv.getMainLooper(), new zzfs(this));
    }

    /* synthetic */ zzfr(zzfn zzfn, zzfo zzfo) {
        this(zzfn);
    }

    private final Message obtainMessage() {
        return this.handler.obtainMessage(1, zzfn.zzbdu);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void cancel() {
        this.handler.removeMessages(1, zzfn.zzbdu);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void zzh(long j) {
        this.handler.removeMessages(1, zzfn.zzbdu);
        this.handler.sendMessageDelayed(obtainMessage(), j);
    }

    @Override // com.google.android.gms.tagmanager.zzfq
    public final void zzpf() {
        this.handler.removeMessages(1, zzfn.zzbdu);
        this.handler.sendMessage(obtainMessage());
    }
}
