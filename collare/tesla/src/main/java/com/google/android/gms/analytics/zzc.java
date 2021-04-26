package com.google.android.gms.analytics;

import android.content.BroadcastReceiver;

final class zzc implements Runnable {
    private final /* synthetic */ BroadcastReceiver.PendingResult zzqu;

    zzc(CampaignTrackingReceiver campaignTrackingReceiver, BroadcastReceiver.PendingResult pendingResult) {
        this.zzqu = pendingResult;
    }

    public final void run() {
        BroadcastReceiver.PendingResult pendingResult = this.zzqu;
        if (pendingResult != null) {
            pendingResult.finish();
        }
    }
}
