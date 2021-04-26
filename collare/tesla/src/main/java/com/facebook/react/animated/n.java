package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: SpringAnimation */
public class n extends d {
    private long e;
    private boolean f;
    private double g;
    private double h;
    private double i;
    private double j;
    private boolean k;
    private final a l = new a();
    private double m;
    private double n;
    private double o;
    private double p;
    private double q;
    private int r;
    private int s;
    private double t;

    /* access modifiers changed from: private */
    /* compiled from: SpringAnimation */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        double f2572a;

        /* renamed from: b  reason: collision with root package name */
        double f2573b;

        private a() {
        }
    }

    n(ReadableMap readableMap) {
        this.l.f2573b = readableMap.getDouble("initialVelocity");
        a(readableMap);
    }

    @Override // com.facebook.react.animated.d
    public void a(ReadableMap readableMap) {
        this.g = readableMap.getDouble("stiffness");
        this.h = readableMap.getDouble("damping");
        this.i = readableMap.getDouble("mass");
        this.j = this.l.f2573b;
        this.n = readableMap.getDouble("toValue");
        this.o = readableMap.getDouble("restSpeedThreshold");
        this.p = readableMap.getDouble("restDisplacementThreshold");
        this.k = readableMap.getBoolean("overshootClamping");
        boolean z = true;
        this.r = readableMap.hasKey("iterations") ? readableMap.getInt("iterations") : 1;
        if (this.r != 0) {
            z = false;
        }
        this.f2562a = z;
        this.s = 0;
        this.q = 0.0d;
        this.f = false;
    }

    @Override // com.facebook.react.animated.d
    public void a(long j2) {
        long j3 = j2 / 1000000;
        if (!this.f) {
            if (this.s == 0) {
                this.t = this.f2563b.e;
                this.s = 1;
            }
            a aVar = this.l;
            double d2 = this.f2563b.e;
            aVar.f2572a = d2;
            this.m = d2;
            this.e = j3;
            this.q = 0.0d;
            this.f = true;
        }
        a(((double) (j3 - this.e)) / 1000.0d);
        this.e = j3;
        this.f2563b.e = this.l.f2572a;
        if (a()) {
            int i2 = this.r;
            if (i2 == -1 || this.s < i2) {
                this.f = false;
                this.f2563b.e = this.t;
                this.s++;
                return;
            }
            this.f2562a = true;
        }
    }

    private double a(a aVar) {
        return Math.abs(this.n - aVar.f2572a);
    }

    private boolean a() {
        return Math.abs(this.l.f2573b) <= this.o && (a(this.l) <= this.p || this.g == 0.0d);
    }

    private boolean b() {
        return this.g > 0.0d && ((this.m < this.n && this.l.f2572a > this.n) || (this.m > this.n && this.l.f2572a < this.n));
    }

    private void a(double d2) {
        double d3;
        double d4;
        if (!a()) {
            double d5 = 0.064d;
            if (d2 <= 0.064d) {
                d5 = d2;
            }
            this.q += d5;
            double d6 = this.h;
            double d7 = this.i;
            double d8 = this.g;
            double d9 = -this.j;
            double sqrt = d6 / (Math.sqrt(d8 * d7) * 2.0d);
            double sqrt2 = Math.sqrt(d8 / d7);
            double sqrt3 = Math.sqrt(1.0d - (sqrt * sqrt)) * sqrt2;
            double d10 = this.n - this.m;
            double d11 = this.q;
            if (sqrt < 1.0d) {
                double exp = Math.exp((-sqrt) * sqrt2 * d11);
                double d12 = sqrt * sqrt2;
                double d13 = d9 + (d12 * d10);
                double d14 = d11 * sqrt3;
                double sin = this.n - ((((d13 / sqrt3) * Math.sin(d14)) + (Math.cos(d14) * d10)) * exp);
                d4 = ((d12 * exp) * (((Math.sin(d14) * d13) / sqrt3) + (Math.cos(d14) * d10))) - (((Math.cos(d14) * d13) - ((sqrt3 * d10) * Math.sin(d14))) * exp);
                d3 = sin;
            } else {
                double exp2 = Math.exp((-sqrt2) * d11);
                d3 = this.n - (((((sqrt2 * d10) + d9) * d11) + d10) * exp2);
                d4 = exp2 * ((d9 * ((d11 * sqrt2) - 1.0d)) + (d11 * d10 * sqrt2 * sqrt2));
            }
            a aVar = this.l;
            aVar.f2572a = d3;
            aVar.f2573b = d4;
            if (a() || (this.k && b())) {
                if (this.g > 0.0d) {
                    double d15 = this.n;
                    this.m = d15;
                    this.l.f2572a = d15;
                } else {
                    this.n = this.l.f2572a;
                    this.m = this.n;
                }
                this.l.f2573b = 0.0d;
            }
        }
    }
}
