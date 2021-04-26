package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import java.util.HashMap;

@a
public class ReadableNativeMap extends NativeMap implements ReadableMap {
    private static int mJniCallCounter;
    private String[] mKeys;
    private HashMap<String, Object> mLocalMap;
    private HashMap<String, ReadableType> mLocalTypeMap;

    private native ReadableNativeArray getArrayNative(String str);

    private native boolean getBooleanNative(String str);

    private native double getDoubleNative(String str);

    private native int getIntNative(String str);

    private native ReadableNativeMap getMapNative(String str);

    private native String getStringNative(String str);

    private native ReadableType getTypeNative(String str);

    private native boolean hasKeyNative(String str);

    private native String[] importKeys();

    private native Object[] importTypes();

    private native Object[] importValues();

    private native boolean isNullNative(String str);

    static {
        ReactBridge.staticInit();
    }

    protected ReadableNativeMap(HybridData hybridData) {
        super(hybridData);
    }

    public static void setUseNativeAccessor(boolean z) {
        com.facebook.react.b.a.f2586c = z;
    }

    public static int getJNIPassCounter() {
        return mJniCallCounter;
    }

    private HashMap<String, Object> getLocalMap() {
        HashMap<String, Object> hashMap = this.mLocalMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) com.facebook.i.a.a.a(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalMap == null) {
                Object[] objArr = (Object[]) com.facebook.i.a.a.a(importValues());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalMap.put(this.mKeys[i], objArr[i]);
                }
            }
        }
        return this.mLocalMap;
    }

    private HashMap<String, ReadableType> getLocalTypeMap() {
        HashMap<String, ReadableType> hashMap = this.mLocalTypeMap;
        if (hashMap != null) {
            return hashMap;
        }
        synchronized (this) {
            if (this.mKeys == null) {
                this.mKeys = (String[]) com.facebook.i.a.a.a(importKeys());
                mJniCallCounter++;
            }
            if (this.mLocalTypeMap == null) {
                Object[] objArr = (Object[]) com.facebook.i.a.a.a(importTypes());
                mJniCallCounter++;
                int length = this.mKeys.length;
                this.mLocalTypeMap = new HashMap<>(length);
                for (int i = 0; i < length; i++) {
                    this.mLocalTypeMap.put(this.mKeys[i], (ReadableType) objArr[i]);
                }
            }
        }
        return this.mLocalTypeMap;
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean hasKey(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return getLocalMap().containsKey(str);
        }
        mJniCallCounter++;
        return hasKeyNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean isNull(String str) {
        if (com.facebook.react.b.a.f2586c) {
            mJniCallCounter++;
            return isNullNative(str);
        } else if (!getLocalMap().containsKey(str)) {
            throw new NoSuchKeyException(str);
        } else if (getLocalMap().get(str) == null) {
            return true;
        } else {
            return false;
        }
    }

    private Object getValue(String str) {
        if (hasKey(str) && !isNull(str)) {
            return com.facebook.i.a.a.a(getLocalMap().get(str));
        }
        throw new NoSuchKeyException(str);
    }

    private <T> T getValue(String str, Class<T> cls) {
        T t = (T) getValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    private Object getNullableValue(String str) {
        if (hasKey(str)) {
            return getLocalMap().get(str);
        }
        throw new NoSuchKeyException(str);
    }

    private <T> T getNullableValue(String str, Class<T> cls) {
        T t = (T) getNullableValue(str);
        checkInstance(str, t, cls);
        return t;
    }

    private void checkInstance(String str, Object obj, Class cls) {
        if (obj != null && !cls.isInstance(obj)) {
            throw new ClassCastException("Value for " + str + " cannot be cast from " + obj.getClass().getSimpleName() + " to " + cls.getSimpleName());
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public boolean getBoolean(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return ((Boolean) getValue(str, Boolean.class)).booleanValue();
        }
        mJniCallCounter++;
        return getBooleanNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public double getDouble(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return ((Double) getValue(str, Double.class)).doubleValue();
        }
        mJniCallCounter++;
        return getDoubleNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public int getInt(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return ((Double) getValue(str, Double.class)).intValue();
        }
        mJniCallCounter++;
        return getIntNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public String getString(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return (String) getNullableValue(str, String.class);
        }
        mJniCallCounter++;
        return getStringNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableArray getArray(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return (ReadableArray) getNullableValue(str, ReadableArray.class);
        }
        mJniCallCounter++;
        return getArrayNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableNativeMap getMap(String str) {
        if (!com.facebook.react.b.a.f2586c) {
            return (ReadableNativeMap) getNullableValue(str, ReadableNativeMap.class);
        }
        mJniCallCounter++;
        return getMapNative(str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableType getType(String str) {
        if (com.facebook.react.b.a.f2586c) {
            mJniCallCounter++;
            return getTypeNative(str);
        } else if (getLocalTypeMap().containsKey(str)) {
            return (ReadableType) com.facebook.i.a.a.a(getLocalTypeMap().get(str));
        } else {
            throw new NoSuchKeyException(str);
        }
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public Dynamic getDynamic(String str) {
        return DynamicFromMap.create(this, str);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public ReadableMapKeySetIterator keySetIterator() {
        return new ReadableNativeMapKeySetIterator(this);
    }

    @Override // com.facebook.react.bridge.ReadableMap
    public HashMap<String, Object> toHashMap() {
        if (com.facebook.react.b.a.f2586c) {
            ReadableMapKeySetIterator keySetIterator = keySetIterator();
            HashMap<String, Object> hashMap = new HashMap<>();
            while (keySetIterator.hasNextKey()) {
                mJniCallCounter++;
                String nextKey = keySetIterator.nextKey();
                mJniCallCounter++;
                switch (getType(nextKey)) {
                    case Null:
                        hashMap.put(nextKey, null);
                        break;
                    case Boolean:
                        hashMap.put(nextKey, Boolean.valueOf(getBoolean(nextKey)));
                        break;
                    case Number:
                        hashMap.put(nextKey, Double.valueOf(getDouble(nextKey)));
                        break;
                    case String:
                        hashMap.put(nextKey, getString(nextKey));
                        break;
                    case Map:
                        hashMap.put(nextKey, ((ReadableNativeMap) com.facebook.i.a.a.a(getMap(nextKey))).toHashMap());
                        break;
                    case Array:
                        hashMap.put(nextKey, ((ReadableArray) com.facebook.i.a.a.a(getArray(nextKey))).toArrayList());
                        break;
                    default:
                        throw new IllegalArgumentException("Could not convert object with key: " + nextKey + ".");
                }
            }
            return hashMap;
        }
        HashMap<String, Object> hashMap2 = new HashMap<>(getLocalMap());
        for (String str : hashMap2.keySet()) {
            switch (getType(str)) {
                case Null:
                case Boolean:
                case Number:
                case String:
                    break;
                case Map:
                    hashMap2.put(str, ((ReadableNativeMap) com.facebook.i.a.a.a(getMap(str))).toHashMap());
                    break;
                case Array:
                    hashMap2.put(str, ((ReadableArray) com.facebook.i.a.a.a(getArray(str))).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object with key: " + str + ".");
            }
        }
        return hashMap2;
    }

    /* access modifiers changed from: private */
    @a
    public static class ReadableNativeMapKeySetIterator implements ReadableMapKeySetIterator {
        @a
        private final HybridData mHybridData;
        @a
        private final ReadableNativeMap mMap;

        private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public native boolean hasNextKey();

        @Override // com.facebook.react.bridge.ReadableMapKeySetIterator
        public native String nextKey();

        public ReadableNativeMapKeySetIterator(ReadableNativeMap readableNativeMap) {
            this.mMap = readableNativeMap;
            this.mHybridData = initHybrid(readableNativeMap);
        }
    }
}
