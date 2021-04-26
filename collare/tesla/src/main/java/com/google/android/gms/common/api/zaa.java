package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;

final class zaa implements PendingResult.StatusListener {
    private final /* synthetic */ Batch zabd;

    zaa(Batch batch) {
        this.zabd = batch;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status status) {
        Status status2;
        synchronized (Batch.zaa(this.zabd)) {
            if (!this.zabd.isCanceled()) {
                if (status.isCanceled()) {
                    Batch.zaa(this.zabd, true);
                } else if (!status.isSuccess()) {
                    Batch.zab(this.zabd, true);
                }
                Batch.zab(this.zabd);
                if (Batch.zac(this.zabd) == 0) {
                    if (Batch.zad(this.zabd)) {
                        Batch.zae(this.zabd);
                    } else {
                        if (Batch.zaf(this.zabd)) {
                            status2 = new Status(13);
                        } else {
                            status2 = Status.RESULT_SUCCESS;
                        }
                        this.zabd.setResult(new BatchResult(status2, Batch.zag(this.zabd)));
                    }
                }
            }
        }
    }
}
