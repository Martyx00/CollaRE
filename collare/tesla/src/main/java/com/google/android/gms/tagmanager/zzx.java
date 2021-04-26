package com.google.android.gms.tagmanager;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.tagmanager.ContainerHolder;

/* access modifiers changed from: package-private */
public final class zzx extends Handler {
    private final ContainerHolder.ContainerAvailableListener zzayb;
    private final /* synthetic */ zzv zzayc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzx(zzv zzv, ContainerHolder.ContainerAvailableListener containerAvailableListener, Looper looper) {
        super(looper);
        this.zzayc = zzv;
        this.zzayb = containerAvailableListener;
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            zzdi.e("Don't know how to handle this message.");
            return;
        }
        this.zzayb.onContainerAvailable(this.zzayc, (String) message.obj);
    }
}
