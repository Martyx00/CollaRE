package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class zzgf implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzgn zzana;
    private final /* synthetic */ zzfi zzanb;
    private final /* synthetic */ long zzanc;
    private final /* synthetic */ Bundle zzand;
    private final /* synthetic */ BroadcastReceiver.PendingResult zzqu;

    zzgf(zzgd zzgd, zzgn zzgn, long j, Bundle bundle, Context context, zzfi zzfi, BroadcastReceiver.PendingResult pendingResult) {
        this.zzana = zzgn;
        this.zzanc = j;
        this.zzand = bundle;
        this.val$context = context;
        this.zzanb = zzfi;
        this.zzqu = pendingResult;
    }

    public final void run() {
        long j = this.zzana.zzgj().zzaly.get();
        long j2 = this.zzanc;
        if (j > 0 && (j2 >= j || j2 <= 0)) {
            j2 = j - 1;
        }
        if (j2 > 0) {
            this.zzand.putLong("click_timestamp", j2);
        }
        this.zzand.putString("_cis", "referrer broadcast");
        AppMeasurement.getInstance(this.val$context).logEventInternal("auto", "_cmp", this.zzand);
        this.zzanb.zzjc().log("Install campaign recorded");
        BroadcastReceiver.PendingResult pendingResult = this.zzqu;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}
