package com.facebook.react.uimanager;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

/* compiled from: TransformHelper */
public class ah {

    /* renamed from: a  reason: collision with root package name */
    private static ThreadLocal<double[]> f3092a = new ThreadLocal<double[]>() {
        /* class com.facebook.react.uimanager.ah.AnonymousClass1 */

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public double[] initialValue() {
            return new double[16];
        }
    };

    private static double a(ReadableMap readableMap, String str) {
        double d2;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d2 = (double) Float.parseFloat(string);
        } else {
            d2 = readableMap.getDouble(str);
        }
        return z ? d2 : i.b(d2);
    }

    public static void a(ReadableArray readableArray, double[] dArr) {
        double[] dArr2 = f3092a.get();
        i.e(dArr);
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            String nextKey = map.keySetIterator().nextKey();
            i.e(dArr2);
            if ("matrix".equals(nextKey)) {
                ReadableArray array = map.getArray(nextKey);
                for (int i2 = 0; i2 < 16; i2++) {
                    dArr2[i2] = array.getDouble(i2);
                }
            } else if ("perspective".equals(nextKey)) {
                i.b(dArr2, map.getDouble(nextKey));
            } else if ("rotateX".equals(nextKey)) {
                i.g(dArr2, a(map, nextKey));
            } else if ("rotateY".equals(nextKey)) {
                i.h(dArr2, a(map, nextKey));
            } else if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                i.i(dArr2, a(map, nextKey));
            } else if ("scale".equals(nextKey)) {
                double d2 = map.getDouble(nextKey);
                i.c(dArr2, d2);
                i.d(dArr2, d2);
            } else if ("scaleX".equals(nextKey)) {
                i.c(dArr2, map.getDouble(nextKey));
            } else if ("scaleY".equals(nextKey)) {
                i.d(dArr2, map.getDouble(nextKey));
            } else {
                double d3 = 0.0d;
                if ("translate".equals(nextKey)) {
                    ReadableArray array2 = map.getArray(nextKey);
                    double d4 = array2.getDouble(0);
                    double d5 = array2.getDouble(1);
                    if (array2.size() > 2) {
                        d3 = array2.getDouble(2);
                    }
                    i.a(dArr2, d4, d5, d3);
                } else if ("translateX".equals(nextKey)) {
                    i.a(dArr2, map.getDouble(nextKey), 0.0d);
                } else if ("translateY".equals(nextKey)) {
                    i.a(dArr2, 0.0d, map.getDouble(nextKey));
                } else if ("skewX".equals(nextKey)) {
                    i.e(dArr2, a(map, nextKey));
                } else if ("skewY".equals(nextKey)) {
                    i.f(dArr2, a(map, nextKey));
                } else {
                    throw new JSApplicationIllegalArgumentException("Unsupported transform type: " + nextKey);
                }
            }
            i.a(dArr, dArr, dArr2);
        }
    }
}
