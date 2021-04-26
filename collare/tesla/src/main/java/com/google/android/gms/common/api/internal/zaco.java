package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zap;

/* access modifiers changed from: package-private */
public final class zaco extends zap {
    private final /* synthetic */ zacm zakw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zaco(zacm zacm, Looper looper) {
        super(looper);
        this.zakw = zacm;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                PendingResult<?> pendingResult = (PendingResult) message.obj;
                synchronized (this.zakw.zado) {
                    if (pendingResult == null) {
                        this.zakw.zakp.zad((zacm) new Status(13, "Transform returned null"));
                    } else if (pendingResult instanceof zacd) {
                        this.zakw.zakp.zad((zacm) ((zacd) pendingResult).getStatus());
                    } else {
                        this.zakw.zakp.zaa(pendingResult);
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String valueOf = String.valueOf(runtimeException.getMessage());
                Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
                throw runtimeException;
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(70);
                sb.append("TransformationResultHandler received unknown message type: ");
                sb.append(i);
                Log.e("TransformedResultImpl", sb.toString());
                return;
        }
    }
}
