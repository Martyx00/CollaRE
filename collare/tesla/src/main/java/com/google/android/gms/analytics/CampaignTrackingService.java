package com.google.android.gms.analytics;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
@Deprecated
public class CampaignTrackingService extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }
}
