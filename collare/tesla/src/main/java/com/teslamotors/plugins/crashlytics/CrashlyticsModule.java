package com.teslamotors.plugins.crashlytics;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrashlyticsModule extends ReactContextBaseJavaModule {
    private static final String REACT_CLASS = "TMCrashlytics";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    public CrashlyticsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void setSessionUsername(String str) {
        b.a(str);
    }

    @ReactMethod
    public void logError(String str, Integer num, ReadableMap readableMap) {
        b.a(str, num, readableMap != null ? recursivelyDeconstructReadableMap(readableMap) : null);
    }

    private static Map<String, Object> recursivelyDeconstructReadableMap(ReadableMap readableMap) {
        HashMap hashMap = new HashMap();
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            switch (readableMap.getType(nextKey)) {
                case Null:
                    hashMap.put(nextKey, null);
                    break;
                case Boolean:
                    hashMap.put(nextKey, Boolean.valueOf(readableMap.getBoolean(nextKey)));
                    break;
                case Number:
                    hashMap.put(nextKey, Double.valueOf(readableMap.getDouble(nextKey)));
                    break;
                case String:
                    hashMap.put(nextKey, readableMap.getString(nextKey));
                    break;
                case Map:
                    hashMap.put(nextKey, recursivelyDeconstructReadableMap(readableMap.getMap(nextKey)));
                    break;
                case Array:
                    hashMap.put(nextKey, recursivelyDeconstructReadableArray(readableMap.getArray(nextKey)));
                    break;
            }
        }
        return hashMap;
    }

    private static List<Object> recursivelyDeconstructReadableArray(ReadableArray readableArray) {
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    arrayList.add(i, null);
                    break;
                case Boolean:
                    arrayList.add(i, Boolean.valueOf(readableArray.getBoolean(i)));
                    break;
                case Number:
                    arrayList.add(i, Double.valueOf(readableArray.getDouble(i)));
                    break;
                case String:
                    arrayList.add(i, readableArray.getString(i));
                    break;
                case Map:
                    arrayList.add(i, recursivelyDeconstructReadableMap(readableArray.getMap(i)));
                    break;
                case Array:
                    arrayList.add(i, recursivelyDeconstructReadableArray(readableArray.getArray(i)));
                    break;
            }
        }
        return arrayList;
    }
}
