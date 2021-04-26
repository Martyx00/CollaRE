package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.GoogleApiAvailabilityLight;

public final class zzas {
    public static final String VERSION = String.valueOf(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "$1.$2.$3");
    public static final String zzvo;

    static {
        String valueOf = String.valueOf(VERSION);
        zzvo = valueOf.length() != 0 ? "ma".concat(valueOf) : new String("ma");
    }
}
