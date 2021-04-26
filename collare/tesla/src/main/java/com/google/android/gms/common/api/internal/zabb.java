package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.internal.base.zap;

/* access modifiers changed from: package-private */
public final class zabb extends zap {
    private final /* synthetic */ zaaw zahh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zabb(zaaw zaaw, Looper looper) {
        super(looper);
        this.zahh = zaaw;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zahh.zaav();
                return;
            case 2:
                this.zahh.resume();
                return;
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Unknown message id: ");
                sb.append(i);
                Log.w("GoogleApiClientImpl", sb.toString());
                return;
        }
    }
}
