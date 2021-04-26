package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: DivisionAnimatedNode */
public class g extends s {
    private final l g;
    private final int[] h;

    public g(ReadableMap readableMap, l lVar) {
        this.g = lVar;
        ReadableArray array = readableMap.getArray("input");
        this.h = new int[array.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.h;
            if (i < iArr.length) {
                iArr[i] = array.getInt(i);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.facebook.react.animated.b
    public void a() {
        int i = 0;
        while (true) {
            int[] iArr = this.h;
            if (i < iArr.length) {
                b a2 = this.g.a(iArr[i]);
                if (a2 != null && (a2 instanceof s)) {
                    double b2 = ((s) a2).b();
                    if (i == 0) {
                        this.e = b2;
                    } else if (b2 != 0.0d) {
                        this.e /= b2;
                    } else {
                        throw new JSApplicationCausedNativeException("Detected a division by zero in Animated.divide node");
                    }
                    i++;
                }
            } else {
                return;
            }
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.divide node");
    }
}
