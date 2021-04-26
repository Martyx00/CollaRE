package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: ValueAnimatedNode */
class s extends b {
    double e = Double.NaN;
    double f = 0.0d;
    private c g;

    public s() {
    }

    public s(ReadableMap readableMap) {
        this.e = readableMap.getDouble(FirebaseAnalytics.b.VALUE);
        this.f = readableMap.getDouble("offset");
    }

    public double b() {
        return this.f + this.e;
    }

    public void c() {
        this.e += this.f;
        this.f = 0.0d;
    }

    public void d() {
        this.f += this.e;
        this.e = 0.0d;
    }

    public void e() {
        c cVar = this.g;
        if (cVar != null) {
            cVar.a(b());
        }
    }

    public void a(c cVar) {
        this.g = cVar;
    }
}
