package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.CampaignTrackingReceiver;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class InstallReferrerReceiver extends CampaignTrackingReceiver {
    /* access modifiers changed from: protected */
    @Override // com.google.android.gms.analytics.CampaignTrackingReceiver
    public final void zza(Context context, String str) {
        zzcw.zzdh(str);
        zzcw.zzf(context, str);
    }
}
