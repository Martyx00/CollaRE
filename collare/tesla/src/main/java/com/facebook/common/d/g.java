package com.facebook.common.d;

/* compiled from: Ints */
public class g {
    public static int a(int... iArr) {
        i.a(iArr.length > 0);
        int i = iArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        return i;
    }
}
