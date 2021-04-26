package com.facebook.react.bridge;

public interface WritableArray extends ReadableArray {
    void pushArray(WritableArray writableArray);

    void pushBoolean(boolean z);

    void pushDouble(double d2);

    void pushInt(int i);

    void pushMap(WritableMap writableMap);

    void pushNull();

    void pushString(String str);
}
