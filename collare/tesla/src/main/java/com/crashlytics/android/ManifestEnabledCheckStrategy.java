package com.crashlytics.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.crashlytics.android.CrashlyticsInitProvider;

class ManifestEnabledCheckStrategy implements CrashlyticsInitProvider.EnabledCheckStrategy {
    ManifestEnabledCheckStrategy() {
    }

    @Override // com.crashlytics.android.CrashlyticsInitProvider.EnabledCheckStrategy
    public boolean isCrashlyticsEnabled(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null || bundle.getBoolean("firebase_crashlytics_collection_enabled", true)) {
                return true;
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }
}
