package com.horcrux.svg;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: Brush */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private a f4594a = a.LINEAR_GRADIENT;

    /* renamed from: b  reason: collision with root package name */
    private final ReadableArray f4595b;

    /* renamed from: c  reason: collision with root package name */
    private ReadableArray f4596c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f4597d;
    private Matrix e;
    private Rect f;

    b(a aVar, ReadableArray readableArray, EnumC0083b bVar) {
        this.f4594a = aVar;
        this.f4595b = readableArray;
        this.f4597d = bVar == EnumC0083b.OBJECT_BOUNDING_BOX;
    }

    /* compiled from: Brush */
    enum a {
        LINEAR_GRADIENT(0),
        RADIAL_GRADIENT(1),
        PATTERN(2);
        

        /* renamed from: d  reason: collision with root package name */
        final int f4601d;

        private a(int i) {
            this.f4601d = i;
        }
    }

    /* renamed from: com.horcrux.svg.b$b  reason: collision with other inner class name */
    /* compiled from: Brush */
    enum EnumC0083b {
        OBJECT_BOUNDING_BOX(0),
        USER_SPACE_ON_USE(1);
        

        /* renamed from: c  reason: collision with root package name */
        final int f4605c;

        private EnumC0083b(int i) {
            this.f4605c = i;
        }
    }

    private static void a(ReadableArray readableArray, int i, float[] fArr, int[] iArr, float f2) {
        int size = readableArray.size() - i;
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = (float) readableArray.getDouble(size + i2);
            int i3 = i2 * 4;
            iArr[i2] = Color.argb((int) (readableArray.getDouble(i3 + 3) * 255.0d * ((double) f2)), (int) (readableArray.getDouble(i3) * 255.0d), (int) (readableArray.getDouble(i3 + 1) * 255.0d), (int) (readableArray.getDouble(i3 + 2) * 255.0d));
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Rect rect) {
        this.f = rect;
    }

    /* access modifiers changed from: package-private */
    public void a(ReadableArray readableArray) {
        this.f4596c = readableArray;
    }

    /* access modifiers changed from: package-private */
    public void a(Matrix matrix) {
        this.e = matrix;
    }

    private RectF a(RectF rectF) {
        float f2;
        if (!this.f4597d) {
            rectF = new RectF(this.f);
        }
        float width = rectF.width();
        float height = rectF.height();
        boolean z = this.f4597d;
        float f3 = BitmapDescriptorFactory.HUE_RED;
        if (z) {
            f3 = rectF.left;
            f2 = rectF.top;
        } else {
            f2 = BitmapDescriptorFactory.HUE_RED;
        }
        return new RectF(f3, f2, width + f3, height + f2);
    }

    /* access modifiers changed from: package-private */
    public void a(Paint paint, RectF rectF, float f2, float f3) {
        float[] fArr;
        int[] iArr;
        Paint paint2;
        RectF a2 = a(rectF);
        float width = a2.width();
        float height = a2.height();
        float f4 = a2.left;
        float f5 = a2.top;
        int size = this.f4596c.size() / 5;
        int[] iArr2 = new int[size];
        float[] fArr2 = new float[size];
        a(this.f4596c, size, fArr2, iArr2, f3);
        if (fArr2.length == 1) {
            iArr = new int[]{iArr2[0], iArr2[0]};
            fArr = new float[]{fArr2[0], fArr2[0]};
            com.facebook.common.e.a.c("ReactNative", "Gradient contains only on stop");
        } else {
            iArr = iArr2;
            fArr = fArr2;
        }
        if (this.f4594a == a.LINEAR_GRADIENT) {
            double d2 = (double) width;
            double d3 = (double) f4;
            double d4 = (double) f2;
            double d5 = (double) height;
            double d6 = (double) f5;
            LinearGradient linearGradient = new LinearGradient((float) w.a(this.f4595b.getString(0), d2, d3, d4, (double) paint.getTextSize()), (float) w.a(this.f4595b.getString(1), d5, d6, d4, (double) paint.getTextSize()), (float) w.a(this.f4595b.getString(2), d2, d3, d4, (double) paint.getTextSize()), (float) w.a(this.f4595b.getString(3), d5, d6, d4, (double) paint.getTextSize()), iArr, fArr, Shader.TileMode.CLAMP);
            if (this.e != null) {
                Matrix matrix = new Matrix();
                matrix.preConcat(this.e);
                linearGradient.setLocalMatrix(matrix);
                paint2 = paint;
            } else {
                paint2 = paint;
            }
            paint2.setShader(linearGradient);
        } else if (this.f4594a == a.RADIAL_GRADIENT) {
            double d7 = (double) width;
            double d8 = (double) f2;
            double a3 = w.a(this.f4595b.getString(2), d7, 0.0d, d8, (double) paint.getTextSize());
            double d9 = (double) height;
            double a4 = w.a(this.f4595b.getString(3), d9, 0.0d, d8, (double) paint.getTextSize());
            double d10 = a4 / a3;
            RadialGradient radialGradient = new RadialGradient((float) w.a(this.f4595b.getString(4), d7, (double) f4, d8, (double) paint.getTextSize()), (float) (w.a(this.f4595b.getString(5), d9, (double) f5, d8, (double) paint.getTextSize()) / d10), (float) a3, iArr, fArr, Shader.TileMode.CLAMP);
            Matrix matrix2 = new Matrix();
            matrix2.preScale(1.0f, (float) d10);
            Matrix matrix3 = this.e;
            if (matrix3 != null) {
                matrix2.preConcat(matrix3);
            }
            radialGradient.setLocalMatrix(matrix2);
            paint.setShader(radialGradient);
        }
    }
}
