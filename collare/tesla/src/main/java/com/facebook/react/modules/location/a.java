package com.facebook.react.modules.location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.google.android.gms.iid.InstanceID;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;

/* compiled from: PositionError */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static int f2906a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static int f2907b = 2;

    /* renamed from: c  reason: collision with root package name */
    public static int f2908c = 3;

    public static WritableMap a(int i, String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("code", i);
        if (str != null) {
            createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, str);
        }
        createMap.putInt("PERMISSION_DENIED", f2906a);
        createMap.putInt("POSITION_UNAVAILABLE", f2907b);
        createMap.putInt(InstanceID.ERROR_TIMEOUT, f2908c);
        return createMap;
    }
}
