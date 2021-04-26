package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zag<ResultT> extends zac {
    private final TaskCompletionSource<ResultT> zacn;
    private final TaskApiCall<Api.AnyClient, ResultT> zacr;
    private final StatusExceptionMapper zacs;

    public zag(int i, TaskApiCall<Api.AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        super(i);
        this.zacn = taskCompletionSource;
        this.zacr = taskApiCall;
        this.zacs = statusExceptionMapper;
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(GoogleApiManager.zaa<?> zaa) {
        try {
            this.zacr.doExecute(zaa.zaab(), this.zacn);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            zaa(zab.zaa(e2));
        } catch (RuntimeException e3) {
            zaa(e3);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(Status status) {
        this.zacn.trySetException(this.zacs.getException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(RuntimeException runtimeException) {
        this.zacn.trySetException(runtimeException);
    }

    @Override // com.google.android.gms.common.api.internal.zab
    public final void zaa(zaab zaab, boolean z) {
        zaab.zaa(this.zacn, z);
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] zab(GoogleApiManager.zaa<?> zaa) {
        return this.zacr.zabt();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean zac(GoogleApiManager.zaa<?> zaa) {
        return this.zacr.shouldAutoResolveMissingFeatures();
    }
}
