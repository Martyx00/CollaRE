package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

/* compiled from: DecayAnimation */
public class e extends d {
    private final double e;
    private double f;
    private long g;
    private double h;
    private double i;
    private int j;
    private int k;

    public e(ReadableMap readableMap) {
        this.e = readableMap.getDouble("velocity");
        a(readableMap);
    }

    @Override // com.facebook.react.animated.d
    public void a(ReadableMap readableMap) {
        this.f = readableMap.getDouble("deceleration");
        boolean z = true;
        this.j = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.k = 1;
        if (this.j != 0) {
            z = false;
        }
        this.f2562a = z;
        this.g = -1;
        this.h = 0.0d;
        this.i = 0.0d;
    }

    @Override // com.facebook.react.animated.d
    public void a(long j2) {
        long j3 = j2 / 1000000;
        if (this.g == -1) {
            this.g = j3 - 16;
            if (this.h == this.i) {
                this.h = this.f2563b.e;
            } else {
                this.f2563b.e = this.h;
            }
            this.i = this.f2563b.e;
        }
        double d2 = this.h;
        double d3 = this.e;
        double d4 = this.f;
        double exp = d2 + ((d3 / (1.0d - d4)) * (1.0d - Math.exp((-(1.0d - d4)) * ((double) (j3 - this.g)))));
        if (Math.abs(this.i - exp) < 0.1d) {
            int i2 = this.j;
            if (i2 == -1 || this.k < i2) {
                this.g = -1;
                this.k++;
            } else {
                this.f2562a = true;
                return;
            }
        }
        this.i = exp;
        this.f2563b.e = exp;
    }
}
