package com.BV.LinearGradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.o;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: LinearGradientView */
public class b extends View {

    /* renamed from: a  reason: collision with root package name */
    private final Paint f1392a = new Paint(1);

    /* renamed from: b  reason: collision with root package name */
    private Path f1393b;

    /* renamed from: c  reason: collision with root package name */
    private RectF f1394c;

    /* renamed from: d  reason: collision with root package name */
    private LinearGradient f1395d;
    private float[] e;
    private float[] f = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};
    private float[] g = {BitmapDescriptorFactory.HUE_RED, 1.0f};
    private int[] h;
    private int[] i = {0, 0};
    private float[] j = {BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED};

    public b(Context context) {
        super(context);
    }

    public void setStartPosition(ReadableArray readableArray) {
        this.f = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        a();
    }

    public void setEndPosition(ReadableArray readableArray) {
        this.g = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        a();
    }

    public void setColors(ReadableArray readableArray) {
        int[] iArr = new int[readableArray.size()];
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = readableArray.getInt(i2);
        }
        this.h = iArr;
        a();
    }

    public void setLocations(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = (float) readableArray.getDouble(i2);
        }
        this.e = fArr;
        a();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        float[] fArr = new float[readableArray.size()];
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = o.a((float) readableArray.getDouble(i2));
        }
        this.j = fArr;
        b();
        a();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.i = new int[]{i2, i3};
        b();
        a();
    }

    private void a() {
        int[] iArr = this.h;
        if (iArr != null) {
            float[] fArr = this.e;
            if (fArr == null || iArr.length == fArr.length) {
                float[] fArr2 = this.f;
                float f2 = fArr2[0];
                int[] iArr2 = this.i;
                float f3 = fArr2[1] * ((float) iArr2[1]);
                float[] fArr3 = this.g;
                this.f1395d = new LinearGradient(f2 * ((float) iArr2[0]), f3, fArr3[0] * ((float) iArr2[0]), fArr3[1] * ((float) iArr2[1]), this.h, this.e, Shader.TileMode.CLAMP);
                this.f1392a.setShader(this.f1395d);
                invalidate();
            }
        }
    }

    private void b() {
        if (this.f1393b == null) {
            this.f1393b = new Path();
            this.f1394c = new RectF();
        }
        this.f1393b.reset();
        RectF rectF = this.f1394c;
        int[] iArr = this.i;
        rectF.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) iArr[0], (float) iArr[1]);
        this.f1393b.addRoundRect(this.f1394c, this.j, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.f1393b;
        if (path == null) {
            canvas.drawPaint(this.f1392a);
        } else {
            canvas.drawPath(path, this.f1392a);
        }
    }
}
