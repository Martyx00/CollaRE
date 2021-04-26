package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: ModulusAnimatedNode */
public class j extends s {
    private final l g;
    private final int h;
    private final double i;

    public j(ReadableMap readableMap, l lVar) {
        this.g = lVar;
        this.h = readableMap.getInt("input");
        this.i = readableMap.getDouble("modulus");
    }

    @Override // com.facebook.react.animated.b
    public void a() {
        b a2 = this.g.a(this.h);
        if (a2 == null || !(a2 instanceof s)) {
            throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
        }
        this.e = ((s) a2).b() % this.i;
    }
}
