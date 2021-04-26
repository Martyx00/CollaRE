package com.facebook.react.uimanager;

import com.facebook.yoga.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: Spacing */
public class ae {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f3083a = {1, 2, 4, 8, 16, 32, 64, 128, 256};

    /* renamed from: b  reason: collision with root package name */
    private final float[] f3084b;

    /* renamed from: c  reason: collision with root package name */
    private int f3085c;

    /* renamed from: d  reason: collision with root package name */
    private final float f3086d;
    private boolean e;

    public ae() {
        this(BitmapDescriptorFactory.HUE_RED);
    }

    public ae(float f) {
        this.f3085c = 0;
        this.f3086d = f;
        this.f3084b = a();
    }

    public boolean a(int i, float f) {
        boolean z = false;
        if (d.a(this.f3084b[i], f)) {
            return false;
        }
        this.f3084b[i] = f;
        if (a.a(f)) {
            this.f3085c = (~f3083a[i]) & this.f3085c;
        } else {
            this.f3085c = f3083a[i] | this.f3085c;
        }
        int i2 = this.f3085c;
        int[] iArr = f3083a;
        if (!((iArr[8] & i2) == 0 && (iArr[7] & i2) == 0 && (i2 & iArr[6]) == 0)) {
            z = true;
        }
        this.e = z;
        return true;
    }

    public float a(int i) {
        float f = (i == 4 || i == 5) ? Float.NaN : this.f3086d;
        int i2 = this.f3085c;
        if (i2 == 0) {
            return f;
        }
        if ((i2 & f3083a[i]) != 0) {
            return this.f3084b[i];
        }
        if (this.e) {
            char c2 = (i == 1 || i == 3) ? (char) 7 : 6;
            int i3 = this.f3085c;
            int[] iArr = f3083a;
            if ((iArr[c2] & i3) != 0) {
                return this.f3084b[c2];
            }
            if ((i3 & iArr[8]) != 0) {
                return this.f3084b[8];
            }
        }
        return f;
    }

    public float b(int i) {
        return this.f3084b[i];
    }

    private static float[] a() {
        return new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
    }
}
