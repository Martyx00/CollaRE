package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: DiffClampAnimatedNode */
public class f extends s {
    private final l g;
    private final int h;
    private final double i;
    private final double j;
    private double k = 0.0d;

    public f(ReadableMap readableMap, l lVar) {
        this.g = lVar;
        this.h = readableMap.getInt("input");
        this.i = readableMap.getDouble("min");
        this.j = readableMap.getDouble("max");
        this.e = 0.0d;
    }

    @Override // com.facebook.react.animated.b
    public void a() {
        double f = f();
        double d2 = f - this.k;
        this.k = f;
        this.e = Math.min(Math.max(this.e + d2, this.i), this.j);
    }

    private double f() {
        b a2 = this.g.a(this.h);
        if (a2 != null && (a2 instanceof s)) {
            return ((s) a2).b();
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
    }
}
