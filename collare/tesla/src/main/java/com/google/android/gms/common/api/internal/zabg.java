package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zap;

/* access modifiers changed from: package-private */
public final class zabg extends zap {
    private final /* synthetic */ zabe zahv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabg(zabe zabe, Looper looper) {
        super(looper);
        this.zahv = zabe;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                ((zabf) message.obj).zac(this.zahv);
                return;
            case 2:
                throw ((RuntimeException) message.obj);
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Unknown message id: ");
                sb.append(i);
                Log.w("GACStateManager", sb.toString());
                return;
        }
    }
}
