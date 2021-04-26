package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public final class zzb {
    private SharedPreferences zzs;

    public zzb(Context context) {
        try {
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            this.zzs = remoteContext == null ? null : remoteContext.getSharedPreferences("google_ads_flags", 0);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while getting SharedPreferences ", th);
            this.zzs = null;
        }
    }

    public final boolean getBoolean(String str, boolean z) {
        try {
            if (this.zzs == null) {
                return false;
            }
            return this.zzs.getBoolean(str, false);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final float getFloat(String str, float f) {
        try {
            return this.zzs == null ? BitmapDescriptorFactory.HUE_RED : this.zzs.getFloat(str, BitmapDescriptorFactory.HUE_RED);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return BitmapDescriptorFactory.HUE_RED;
        }
    }

    /* access modifiers changed from: package-private */
    public final String getString(String str, String str2) {
        try {
            return this.zzs == null ? str2 : this.zzs.getString(str, str2);
        } catch (Throwable th) {
            Log.w("GmscoreFlag", "Error while reading from SharedPreferences ", th);
            return str2;
        }
    }
}
