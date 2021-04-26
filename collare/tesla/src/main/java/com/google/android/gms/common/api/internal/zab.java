package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.os.TransactionTooLargeException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zab {
    private final int type;

    public zab(int i) {
        this.type = i;
    }

    public abstract void zaa(Status status);

    public abstract void zaa(GoogleApiManager.zaa<?> zaa);

    public abstract void zaa(zaab zaab, boolean z);

    public abstract void zaa(RuntimeException runtimeException);

    /* access modifiers changed from: private */
    public static Status zaa(RemoteException remoteException) {
        StringBuilder sb = new StringBuilder();
        if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && (remoteException instanceof TransactionTooLargeException)) {
            sb.append("TransactionTooLargeException: ");
        }
        sb.append(remoteException.getLocalizedMessage());
        return new Status(8, sb.toString());
    }
}
