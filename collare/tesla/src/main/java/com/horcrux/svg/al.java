package com.horcrux.svg;

import android.graphics.Matrix;
import android.graphics.RectF;

/* access modifiers changed from: package-private */
/* compiled from: ViewBox */
public class al extends o {
    static Matrix a(RectF rectF, RectF rectF2, String str, int i) {
        double d2;
        double d3;
        double width = (double) rectF.width();
        double height = (double) rectF.height();
        double d4 = (double) rectF2.left;
        double d5 = (double) rectF2.top;
        double width2 = (double) rectF2.width();
        double height2 = (double) rectF2.height();
        double d6 = width2 / width;
        double d7 = height2 / height;
        double d8 = d4 - (((double) rectF.left) * d6);
        double d9 = d5 - (((double) rectF.top) * d7);
        if (i == 2) {
            d3 = Math.min(d6, d7);
            if (d3 > 1.0d) {
                d8 -= ((width2 / d3) - width) / 2.0d;
                d9 -= ((height2 / d3) - height) / 2.0d;
            } else {
                d8 -= (width2 - (width * d3)) / 2.0d;
                d9 -= (height2 - (height * d3)) / 2.0d;
            }
            d2 = d3;
        } else {
            if (!str.equals("none") && i == 0) {
                d2 = Math.min(d6, d7);
                d3 = d2;
            } else if (str.equals("none") || i != 1) {
                d3 = d6;
                d2 = d7;
            } else {
                d2 = Math.max(d6, d7);
                d3 = d2;
            }
            if (str.contains("xMid")) {
                d8 += (width2 - (width * d3)) / 2.0d;
            }
            if (str.contains("xMax")) {
                d8 += width2 - (width * d3);
            }
            if (str.contains("YMid")) {
                d9 += (height2 - (height * d2)) / 2.0d;
            }
            if (str.contains("YMax")) {
                d9 += height2 - (height * d2);
            }
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate((float) d8, (float) d9);
        matrix.preScale((float) d3, (float) d2);
        return matrix;
    }
}
