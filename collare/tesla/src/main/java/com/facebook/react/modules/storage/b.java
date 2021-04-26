package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.teslamotors.plugins.biometricauthentication.BiometricAuthenticationModule;

/* compiled from: AsyncStorageErrorUtil */
public class b {
    static WritableMap a(String str, String str2) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString(BiometricAuthenticationModule.BIOMETRICS_BUNDLE_KEY_MESSAGE, str2);
        if (str != null) {
            createMap.putString("key", str);
        }
        return createMap;
    }

    static WritableMap a(String str) {
        return a(str, "Invalid key");
    }

    static WritableMap b(String str) {
        return a(str, "Invalid Value");
    }

    static WritableMap c(String str) {
        return a(str, "Database Error");
    }
}
