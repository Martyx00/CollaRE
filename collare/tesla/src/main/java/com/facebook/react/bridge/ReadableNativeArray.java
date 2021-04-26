package com.facebook.react.bridge;

import com.facebook.j.a.a;
import com.facebook.jni.HybridData;
import java.util.ArrayList;
import java.util.Arrays;

@a
public class ReadableNativeArray extends NativeArray implements ReadableArray {
    private static int jniPassCounter = 0;
    private Object[] mLocalArray;
    private ReadableType[] mLocalTypeArray;

    private native ReadableNativeArray getArrayNative(int i);

    private native boolean getBooleanNative(int i);

    private native double getDoubleNative(int i);

    private native int getIntNative(int i);

    private native ReadableNativeMap getMapNative(int i);

    private native String getStringNative(int i);

    private native ReadableType getTypeNative(int i);

    private native Object[] importArray();

    private native Object[] importTypeArray();

    private native boolean isNullNative(int i);

    private native int sizeNative();

    static {
        ReactBridge.staticInit();
    }

    protected ReadableNativeArray(HybridData hybridData) {
        super(hybridData);
    }

    public static void setUseNativeAccessor(boolean z) {
        com.facebook.react.b.a.f2585b = z;
    }

    public static int getJNIPassCounter() {
        return jniPassCounter;
    }

    private Object[] getLocalArray() {
        Object[] objArr = this.mLocalArray;
        if (objArr != null) {
            return objArr;
        }
        synchronized (this) {
            if (this.mLocalArray == null) {
                jniPassCounter++;
                this.mLocalArray = (Object[]) com.facebook.i.a.a.a(importArray());
            }
        }
        return this.mLocalArray;
    }

    private ReadableType[] getLocalTypeArray() {
        ReadableType[] readableTypeArr = this.mLocalTypeArray;
        if (readableTypeArr != null) {
            return readableTypeArr;
        }
        synchronized (this) {
            if (this.mLocalTypeArray == null) {
                jniPassCounter++;
                Object[] objArr = (Object[]) com.facebook.i.a.a.a(importTypeArray());
                this.mLocalTypeArray = (ReadableType[]) Arrays.copyOf(objArr, objArr.length, ReadableType[].class);
            }
        }
        return this.mLocalTypeArray;
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int size() {
        if (!com.facebook.react.b.a.f2585b) {
            return getLocalArray().length;
        }
        jniPassCounter++;
        return sizeNative();
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean isNull(int i) {
        if (com.facebook.react.b.a.f2585b) {
            jniPassCounter++;
            return isNullNative(i);
        } else if (getLocalArray()[i] == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public boolean getBoolean(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return ((Boolean) getLocalArray()[i]).booleanValue();
        }
        jniPassCounter++;
        return getBooleanNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public double getDouble(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return ((Double) getLocalArray()[i]).doubleValue();
        }
        jniPassCounter++;
        return getDoubleNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public int getInt(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return ((Double) getLocalArray()[i]).intValue();
        }
        jniPassCounter++;
        return getIntNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public String getString(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return (String) getLocalArray()[i];
        }
        jniPassCounter++;
        return getStringNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeArray getArray(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return (ReadableNativeArray) getLocalArray()[i];
        }
        jniPassCounter++;
        return getArrayNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableNativeMap getMap(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return (ReadableNativeMap) getLocalArray()[i];
        }
        jniPassCounter++;
        return getMapNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ReadableType getType(int i) {
        if (!com.facebook.react.b.a.f2585b) {
            return getLocalTypeArray()[i];
        }
        jniPassCounter++;
        return getTypeNative(i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public Dynamic getDynamic(int i) {
        return DynamicFromArray.create(this, i);
    }

    @Override // com.facebook.react.bridge.ReadableArray
    public ArrayList<Object> toArrayList() {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            switch (getType(i)) {
                case Null:
                    arrayList.add(null);
                    break;
                case Boolean:
                    arrayList.add(Boolean.valueOf(getBoolean(i)));
                    break;
                case Number:
                    arrayList.add(Double.valueOf(getDouble(i)));
                    break;
                case String:
                    arrayList.add(getString(i));
                    break;
                case Map:
                    arrayList.add(getMap(i).toHashMap());
                    break;
                case Array:
                    arrayList.add(getArray(i).toArrayList());
                    break;
                default:
                    throw new IllegalArgumentException("Could not convert object at index: " + i + ".");
            }
        }
        return arrayList;
    }
}
