package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable {
    private final /* synthetic */ Result zakv;
    private final /* synthetic */ zacm zakw;

    zacn(zacm zacm, Result result) {
        this.zakw = zacm;
        this.zakv = result;
    }

    public final void run() {
        try {
            BasePendingResult.zadn.set(true);
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(0, this.zakw.zako.onSuccess(this.zakv)));
            BasePendingResult.zadn.set(false);
            zacm zacm = this.zakw;
            zacm.zab(this.zakv);
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient != null) {
                googleApiClient.zab(this.zakw);
            }
        } catch (RuntimeException e) {
            this.zakw.zakt.sendMessage(this.zakw.zakt.obtainMessage(1, e));
            BasePendingResult.zadn.set(false);
            zacm zacm2 = this.zakw;
            zacm.zab(this.zakv);
            GoogleApiClient googleApiClient2 = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient2 != null) {
                googleApiClient2.zab(this.zakw);
            }
        } catch (Throwable th) {
            BasePendingResult.zadn.set(false);
            zacm zacm3 = this.zakw;
            zacm.zab(this.zakv);
            GoogleApiClient googleApiClient3 = (GoogleApiClient) this.zakw.zadq.get();
            if (googleApiClient3 != null) {
                googleApiClient3.zab(this.zakw);
            }
            throw th;
        }
    }
}
