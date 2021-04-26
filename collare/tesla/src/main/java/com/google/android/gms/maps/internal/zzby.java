package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;

public final class zzby {
    public static void zza(Bundle bundle, Bundle bundle2) {
        if (bundle != null && bundle2 != null) {
            Parcelable zza = zza(bundle, "MapOptions");
            if (zza != null) {
                zza(bundle2, "MapOptions", zza);
            }
            Parcelable zza2 = zza(bundle, "StreetViewPanoramaOptions");
            if (zza2 != null) {
                zza(bundle2, "StreetViewPanoramaOptions", zza2);
            }
            Parcelable zza3 = zza(bundle, "camera");
            if (zza3 != null) {
                zza(bundle2, "camera", zza3);
            }
            if (bundle.containsKey("position")) {
                bundle2.putString("position", bundle.getString("position"));
            }
            if (bundle.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    private static <T extends Parcelable> T zza(Bundle bundle, String str) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(zzby.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(zzby.class.getClassLoader());
        return (T) bundle2.getParcelable(str);
    }

    public static void zza(Bundle bundle, String str, Parcelable parcelable) {
        bundle.setClassLoader(zzby.class.getClassLoader());
        Bundle bundle2 = bundle.getBundle("map_state");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        bundle2.setClassLoader(zzby.class.getClassLoader());
        bundle2.putParcelable(str, parcelable);
        bundle.putBundle("map_state", bundle2);
    }

    private zzby() {
    }
}
