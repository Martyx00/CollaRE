package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.zac;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zacp {
    public static final Status zakx = new Status(8, "The connection to Google Play services was lost");
    private static final BasePendingResult<?>[] zaky = new BasePendingResult[0];
    private final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    @VisibleForTesting
    final Set<BasePendingResult<?>> zakz = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zacs zala = new zacq(this);

    public zacp(Map<Api.AnyClientKey<?>, Api.Client> map) {
        this.zagz = map;
    }

    /* access modifiers changed from: package-private */
    public final void zab(BasePendingResult<? extends Result> basePendingResult) {
        this.zakz.add(basePendingResult);
        basePendingResult.zaa(this.zala);
    }

    public final void release() {
        BasePendingResult[] basePendingResultArr = (BasePendingResult[]) this.zakz.toArray(zaky);
        for (BasePendingResult basePendingResult : basePendingResultArr) {
            zac zac = null;
            basePendingResult.zaa((zacs) null);
            if (basePendingResult.zam() != null) {
                basePendingResult.setResultCallback(null);
                IBinder serviceBrokerBinder = this.zagz.get(((BaseImplementation.ApiMethodImpl) basePendingResult).getClientKey()).getServiceBrokerBinder();
                if (basePendingResult.isReady()) {
                    basePendingResult.zaa(new zacr(basePendingResult, null, serviceBrokerBinder, null));
                } else if (serviceBrokerBinder == null || !serviceBrokerBinder.isBinderAlive()) {
                    basePendingResult.zaa((zacs) null);
                    basePendingResult.cancel();
                    zac.remove(basePendingResult.zam().intValue());
                } else {
                    zacr zacr = new zacr(basePendingResult, null, serviceBrokerBinder, null);
                    basePendingResult.zaa(zacr);
                    try {
                        serviceBrokerBinder.linkToDeath(zacr, 0);
                    } catch (RemoteException unused) {
                        basePendingResult.cancel();
                        zac.remove(basePendingResult.zam().intValue());
                    }
                }
                this.zakz.remove(basePendingResult);
            } else if (basePendingResult.zat()) {
                this.zakz.remove(basePendingResult);
            }
        }
    }

    public final void zabx() {
        for (BasePendingResult basePendingResult : (BasePendingResult[]) this.zakz.toArray(zaky)) {
            basePendingResult.zab(zakx);
        }
    }
}
