package com.teslamotors.plugins.devicestorage;

import android.content.SharedPreferences;
import android.util.Pair;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.teslamotors.plugins.client.b;
import com.teslamotors.plugins.client.d.d;
import com.teslamotors.plugins.client.d.e;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DeviceStorageModule extends ReactContextBaseJavaModule {
    private static final String ERROR_NO_VALUE = "ERROR_NO_VALUE";
    private static final String ERROR_UNABLE_TO_CREATE = "ERROR_UNABLE_TO_CREATE";
    private static final String ERROR_UNABLE_TO_DELETE = "ERROR_UNABLE_TO_DELETE";
    private static final String IDENTIFIER = "identifier";
    private static final String NO_ERROR = "NO_ERROR";
    private static final String REACT_CLASS = "TMDeviceStorageAccess";
    private static final String RESULT = "result";
    private static final String TAG = "DeviceStorageModule";
    private static final String VALUE = "value";
    private final SharedPreferences mLegacyNonSecurePrefs;
    private final SharedPreferences mLegacySecurePrefs;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public DeviceStorageModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mLegacySecurePrefs = e.a(reactApplicationContext);
        this.mLegacyNonSecurePrefs = e.b(reactApplicationContext);
    }

    @ReactMethod
    public void storeValues(ReadableMap readableMap, Promise promise) {
        promise.resolve(genResult(readableMap == null || d.a(getReactApplicationContext()).a(Arguments.toBundle(readableMap)), NO_ERROR, ERROR_UNABLE_TO_CREATE));
    }

    @ReactMethod
    public void retrieveValues(ReadableArray readableArray, ReadableArray readableArray2, Promise promise) {
        WritableArray createArray = Arguments.createArray();
        List<String> strFromArray = getStrFromArray(readableArray);
        List<Pair<String, String>> a2 = d.a(getReactApplicationContext()).a(strFromArray, getStrFromArray(readableArray2));
        HashSet hashSet = new HashSet();
        for (Pair<String, String> pair : a2) {
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString(IDENTIFIER, (String) pair.first);
            writableNativeMap.putString("value", (String) pair.second);
            writableNativeMap.putString("result", NO_ERROR);
            hashSet.add(pair.first);
            createArray.pushMap(writableNativeMap);
        }
        for (String str : strFromArray) {
            WritableNativeMap writableNativeMap2 = new WritableNativeMap();
            if (!hashSet.contains(str)) {
                writableNativeMap2.putString(IDENTIFIER, str);
                writableNativeMap2.putString("result", ERROR_NO_VALUE);
            }
            createArray.pushMap(writableNativeMap2);
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void deleteValues(ReadableArray readableArray, ReadableArray readableArray2, Promise promise) {
        promise.resolve(genResult(d.a(getReactApplicationContext()).b(getStrFromArray(readableArray), getStrFromArray(readableArray2)), NO_ERROR, ERROR_UNABLE_TO_DELETE));
    }

    @ReactMethod
    public void clearValuesExcept(ReadableArray readableArray, ReadableArray readableArray2, Promise promise) {
        promise.resolve(genResult(d.a(getReactApplicationContext()).c(getStrFromArray(readableArray), getStrFromArray(readableArray2)), NO_ERROR, ERROR_UNABLE_TO_DELETE));
    }

    private static List<String> getStrFromArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); i++) {
                arrayList.add(readableArray.getString(i));
            }
        }
        return arrayList;
    }

    private WritableMap genResult(boolean z, String str, String str2) {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        if (!z) {
            str = str2;
        }
        writableNativeMap.putString("result", str);
        return writableNativeMap;
    }

    @ReactMethod
    public void retrieveSecureLegacyValues(ReadableArray readableArray, Promise promise) {
        b.b("[REALM STORAGE]", "Retrieving Legacy Secure Value");
        retrieveLegacyValues(readableArray, true, promise);
    }

    @ReactMethod
    public void retrieveNonSecureLegacyValues(ReadableArray readableArray, Promise promise) {
        b.b("[REALM STORAGE]", "Retrieving Legacy NonSecure Value");
        retrieveLegacyValues(readableArray, false, promise);
    }

    private void retrieveLegacyValues(ReadableArray readableArray, boolean z, Promise promise) {
        String str;
        WritableArray createArray = Arguments.createArray();
        if (readableArray == null) {
            promise.resolve(createArray);
            return;
        }
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (z) {
                str = e.a().b(string, this.mLegacySecurePrefs);
            } else {
                str = e.a().b(string, this.mLegacyNonSecurePrefs);
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            if (str != null) {
                b.b("[REALM STORAGE]", "Retrieving Legacy Value " + string + " : " + str);
                writableNativeMap.putString(IDENTIFIER, string);
                writableNativeMap.putString("value", str);
                writableNativeMap.putString("result", NO_ERROR);
            } else {
                b.b("[REALM STORAGE]", "Retrieving Legacy Value Failed : " + string);
                writableNativeMap.putString(IDENTIFIER, string);
                writableNativeMap.putString("result", ERROR_NO_VALUE);
            }
            createArray.pushMap(writableNativeMap);
        }
        promise.resolve(createArray);
    }

    @ReactMethod
    public void clearSecureLegacyValues(ReadableArray readableArray, Promise promise) {
        e.a().a(this.mLegacySecurePrefs, false);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("result", e.a().b() ? NO_ERROR : ERROR_UNABLE_TO_DELETE);
        promise.resolve(writableNativeMap);
    }

    @ReactMethod
    public void clearNonSecureLegacyValues(ReadableArray readableArray, Promise promise) {
        e.a().a(this.mLegacyNonSecurePrefs, false);
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("result", e.a().b() ? NO_ERROR : ERROR_UNABLE_TO_DELETE);
        promise.resolve(writableNativeMap);
    }
}
