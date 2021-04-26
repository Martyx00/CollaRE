package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Message;

/* access modifiers changed from: package-private */
public final class zzfs implements Handler.Callback {
    private final /* synthetic */ zzfr zzbeh;

    zzfs(zzfr zzfr) {
        this.zzbeh = zzfr;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfn.zzbdu.equals(message.obj)) {
            this.zzbeh.zzbeg.dispatch();
            if (!(this.zzbeh.zzbeg.isPowerSaveMode())) {
                zzfr zzfr = this.zzbeh;
                zzfr.zzh((long) zzfr.zzbeg.zzbdy);
            }
        }
        return true;
    }
}
