package com.facebook.react.animated;

import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;

/* access modifiers changed from: package-private */
/* compiled from: TrackingAnimatedNode */
public class q extends b {
    private final l e;
    private final int f;
    private final int g;
    private final int h;
    private final JavaOnlyMap i;

    q(ReadableMap readableMap, l lVar) {
        this.e = lVar;
        this.f = readableMap.getInt("animationId");
        this.g = readableMap.getInt("toValue");
        this.h = readableMap.getInt(FirebaseAnalytics.b.VALUE);
        this.i = JavaOnlyMap.deepClone(readableMap.getMap("animationConfig"));
    }

    @Override // com.facebook.react.animated.b
    public void a() {
        this.i.putDouble("toValue", ((s) this.e.a(this.g)).b());
        this.e.a(this.f, this.h, this.i, null);
    }
}
