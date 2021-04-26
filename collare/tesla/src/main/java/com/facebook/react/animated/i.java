package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* access modifiers changed from: package-private */
/* compiled from: InterpolationAnimatedNode */
public class i extends s {
    private final double[] g;
    private final double[] h;
    private final String i;
    private final String j;
    private s k;

    private static double[] a(ReadableArray readableArray) {
        double[] dArr = new double[readableArray.size()];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            dArr[i2] = readableArray.getDouble(i2);
        }
        return dArr;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x008b, code lost:
        if (r22.equals("extend") != false) goto L_0x008f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0060 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b0 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static double a(double r11, double r13, double r15, double r17, double r19, java.lang.String r21, java.lang.String r22) {
        /*
        // Method dump skipped, instructions count: 208
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.i.a(double, double, double, double, double, java.lang.String, java.lang.String):double");
    }

    static double a(double d2, double[] dArr, double[] dArr2, String str, String str2) {
        int a2 = a(d2, dArr);
        int i2 = a2 + 1;
        return a(d2, dArr[a2], dArr[i2], dArr2[a2], dArr2[i2], str, str2);
    }

    private static int a(double d2, double[] dArr) {
        int i2 = 1;
        while (i2 < dArr.length - 1 && dArr[i2] < d2) {
            i2++;
        }
        return i2 - 1;
    }

    public i(ReadableMap readableMap) {
        this.g = a(readableMap.getArray("inputRange"));
        this.h = a(readableMap.getArray("outputRange"));
        this.i = readableMap.getString("extrapolateLeft");
        this.j = readableMap.getString("extrapolateRight");
    }

    @Override // com.facebook.react.animated.b
    public void c(b bVar) {
        if (this.k != null) {
            throw new IllegalStateException("Parent already attached");
        } else if (bVar instanceof s) {
            this.k = (s) bVar;
        } else {
            throw new IllegalArgumentException("Parent is of an invalid type");
        }
    }

    @Override // com.facebook.react.animated.b
    public void d(b bVar) {
        if (bVar == this.k) {
            this.k = null;
            return;
        }
        throw new IllegalArgumentException("Invalid parent node provided");
    }

    @Override // com.facebook.react.animated.b
    public void a() {
        s sVar = this.k;
        if (sVar != null) {
            this.e = a(sVar.b(), this.g, this.h, this.i, this.j);
        }
    }
}
