package com.facebook.react.views.art;

import com.facebook.react.bridge.ReadableArray;

/* compiled from: PropHelper */
class g {
    static float[] a(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        float[] fArr = new float[readableArray.size()];
        a(readableArray, fArr);
        return fArr;
    }

    static int a(ReadableArray readableArray, float[] fArr) {
        int length = readableArray.size() > fArr.length ? fArr.length : readableArray.size();
        for (int i = 0; i < length; i++) {
            fArr[i] = (float) readableArray.getDouble(i);
        }
        return readableArray.size();
    }
}
