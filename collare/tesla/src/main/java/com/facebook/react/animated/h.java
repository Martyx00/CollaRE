package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: FrameBasedAnimationDriver */
public class h extends d {
    private long e;
    private double[] f;
    private double g;
    private double h;
    private int i;
    private int j;

    h(ReadableMap readableMap) {
        a(readableMap);
    }

    @Override // com.facebook.react.animated.d
    public void a(ReadableMap readableMap) {
        ReadableArray array = readableMap.getArray("frames");
        int size = array.size();
        double[] dArr = this.f;
        if (dArr == null || dArr.length != size) {
            this.f = new double[size];
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.f[i2] = array.getDouble(i2);
        }
        this.g = readableMap.hasKey("toValue") ? readableMap.getDouble("toValue") : 0.0d;
        boolean z = true;
        this.i = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        this.j = 1;
        if (this.i != 0) {
            z = false;
        }
        this.f2562a = z;
        this.e = -1;
    }

    @Override // com.facebook.react.animated.d
    public void a(long j2) {
        double d2;
        if (this.e < 0) {
            this.e = j2;
            if (this.j == 1) {
                this.h = this.f2563b.e;
            }
        }
        int round = (int) Math.round(((double) ((j2 - this.e) / 1000000)) / 16.666666666666668d);
        if (round < 0) {
            throw new IllegalStateException("Calculated frame index should never be lower than 0");
        } else if (!this.f2562a) {
            double[] dArr = this.f;
            if (round >= dArr.length - 1) {
                d2 = this.g;
                int i2 = this.i;
                if (i2 == -1 || this.j < i2) {
                    this.e = -1;
                    this.j++;
                } else {
                    this.f2562a = true;
                }
            } else {
                double d3 = this.h;
                d2 = d3 + (dArr[round] * (this.g - d3));
            }
            this.f2563b.e = d2;
        }
    }
}
