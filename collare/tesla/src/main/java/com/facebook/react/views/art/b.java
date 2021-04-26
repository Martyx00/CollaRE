package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: ARTShapeShadowNode */
public class b extends f {

    /* renamed from: a  reason: collision with root package name */
    protected Path f3298a;

    /* renamed from: d  reason: collision with root package name */
    private float[] f3299d;
    private float[] e;
    private float[] f;
    private float g = 1.0f;
    private int h = 1;
    private int i = 1;

    private float a(float f2, float f3) {
        float f4 = f2 % f3;
        return f4 < BitmapDescriptorFactory.HUE_RED ? f4 + f3 : f4;
    }

    @a(a = "d")
    public void setShapePath(ReadableArray readableArray) {
        this.f3298a = a(g.a(readableArray));
        markUpdated();
    }

    @a(a = "stroke")
    public void setStroke(ReadableArray readableArray) {
        this.f3299d = g.a(readableArray);
        markUpdated();
    }

    @a(a = "strokeDash")
    public void setStrokeDash(ReadableArray readableArray) {
        this.f = g.a(readableArray);
        markUpdated();
    }

    @a(a = "fill")
    public void setFill(ReadableArray readableArray) {
        this.e = g.a(readableArray);
        markUpdated();
    }

    @a(a = "strokeWidth", d = 1.0f)
    public void setStrokeWidth(float f2) {
        this.g = f2;
        markUpdated();
    }

    @a(a = "strokeCap", e = 1)
    public void setStrokeCap(int i2) {
        this.h = i2;
        markUpdated();
    }

    @a(a = "strokeJoin", e = 1)
    public void setStrokeJoin(int i2) {
        this.i = i2;
        markUpdated();
    }

    @Override // com.facebook.react.views.art.f
    public void a(Canvas canvas, Paint paint, float f2) {
        float f3 = f2 * this.f3305b;
        if (f3 > 0.01f) {
            a(canvas);
            if (this.f3298a != null) {
                if (b(paint, f3)) {
                    canvas.drawPath(this.f3298a, paint);
                }
                if (a(paint, f3)) {
                    canvas.drawPath(this.f3298a, paint);
                }
                b(canvas);
            } else {
                throw new JSApplicationIllegalArgumentException("Shapes should have a valid path (d) prop");
            }
        }
        markUpdateSeen();
    }

    /* access modifiers changed from: protected */
    public boolean a(Paint paint, float f2) {
        float[] fArr;
        if (this.g == BitmapDescriptorFactory.HUE_RED || (fArr = this.f3299d) == null || fArr.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.STROKE);
        switch (this.h) {
            case 0:
                paint.setStrokeCap(Paint.Cap.BUTT);
                break;
            case 1:
                paint.setStrokeCap(Paint.Cap.ROUND);
                break;
            case 2:
                paint.setStrokeCap(Paint.Cap.SQUARE);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeCap " + this.h + " unrecognized");
        }
        switch (this.i) {
            case 0:
                paint.setStrokeJoin(Paint.Join.MITER);
                break;
            case 1:
                paint.setStrokeJoin(Paint.Join.ROUND);
                break;
            case 2:
                paint.setStrokeJoin(Paint.Join.BEVEL);
                break;
            default:
                throw new JSApplicationIllegalArgumentException("strokeJoin " + this.i + " unrecognized");
        }
        paint.setStrokeWidth(this.g * this.f3306c);
        float[] fArr2 = this.f3299d;
        int i2 = (int) (fArr2.length > 3 ? fArr2[3] * f2 * 255.0f : f2 * 255.0f);
        float[] fArr3 = this.f3299d;
        paint.setARGB(i2, (int) (fArr3[0] * 255.0f), (int) (fArr3[1] * 255.0f), (int) (fArr3[2] * 255.0f));
        float[] fArr4 = this.f;
        if (fArr4 != null && fArr4.length > 0) {
            paint.setPathEffect(new DashPathEffect(fArr4, BitmapDescriptorFactory.HUE_RED));
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean b(Paint paint, float f2) {
        float[] fArr;
        int[] iArr;
        float[] fArr2 = this.e;
        int i2 = 0;
        if (fArr2 == null || fArr2.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        float[] fArr3 = this.e;
        int i3 = (int) fArr3[0];
        switch (i3) {
            case 0:
                float f3 = fArr3.length > 4 ? fArr3[4] * f2 * 255.0f : f2 * 255.0f;
                float[] fArr4 = this.e;
                paint.setARGB((int) f3, (int) (fArr4[1] * 255.0f), (int) (fArr4[2] * 255.0f), (int) (fArr4[3] * 255.0f));
                return true;
            case 1:
                int i4 = 5;
                if (fArr3.length < 5) {
                    com.facebook.common.e.a.c("ReactNative", "[ARTShapeShadowNode setupFillPaint] expects 5 elements, received " + this.e.length);
                    return false;
                }
                float f4 = fArr3[1] * this.f3306c;
                float f5 = this.e[2] * this.f3306c;
                float f6 = this.e[3] * this.f3306c;
                float f7 = this.e[4] * this.f3306c;
                int length = (this.e.length - 5) / 5;
                if (length > 0) {
                    int[] iArr2 = new int[length];
                    float[] fArr5 = new float[length];
                    while (i2 < length) {
                        float[] fArr6 = this.e;
                        fArr5[i2] = fArr6[(length * 4) + i4 + i2];
                        int i5 = (i2 * 4) + i4;
                        iArr2[i2] = Color.argb((int) (fArr6[i5 + 3] * 255.0f), (int) (fArr6[i5 + 0] * 255.0f), (int) (fArr6[i5 + 1] * 255.0f), (int) (fArr6[i5 + 2] * 255.0f));
                        i2++;
                        length = length;
                        i4 = 5;
                    }
                    iArr = iArr2;
                    fArr = fArr5;
                } else {
                    iArr = null;
                    fArr = null;
                }
                paint.setShader(new LinearGradient(f4, f5, f6, f7, iArr, fArr, Shader.TileMode.CLAMP));
                return true;
            default:
                com.facebook.common.e.a.c("ReactNative", "ART: Color type " + i3 + " not supported!");
                return true;
        }
    }

    private Path a(float[] fArr) {
        Path path = new Path();
        path.moveTo(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
        int i2 = 0;
        while (i2 < fArr.length) {
            int i3 = i2 + 1;
            int i4 = (int) fArr[i2];
            switch (i4) {
                case 0:
                    int i5 = i3 + 1;
                    path.moveTo(fArr[i3] * this.f3306c, fArr[i5] * this.f3306c);
                    i2 = i5 + 1;
                    break;
                case 1:
                    path.close();
                    i2 = i3;
                    break;
                case 2:
                    int i6 = i3 + 1;
                    path.lineTo(fArr[i3] * this.f3306c, fArr[i6] * this.f3306c);
                    i2 = i6 + 1;
                    break;
                case 3:
                    int i7 = i3 + 1;
                    float f2 = fArr[i3] * this.f3306c;
                    int i8 = i7 + 1;
                    float f3 = this.f3306c * fArr[i7];
                    int i9 = i8 + 1;
                    float f4 = this.f3306c * fArr[i8];
                    int i10 = i9 + 1;
                    float f5 = this.f3306c * fArr[i9];
                    int i11 = i10 + 1;
                    path.cubicTo(f2, f3, f4, f5, this.f3306c * fArr[i10], fArr[i11] * this.f3306c);
                    i2 = i11 + 1;
                    break;
                case 4:
                    int i12 = i3 + 1;
                    float f6 = fArr[i3] * this.f3306c;
                    int i13 = i12 + 1;
                    float f7 = fArr[i12] * this.f3306c;
                    int i14 = i13 + 1;
                    float f8 = fArr[i13] * this.f3306c;
                    int i15 = i14 + 1;
                    float degrees = (float) Math.toDegrees((double) fArr[i14]);
                    int i16 = i15 + 1;
                    float degrees2 = (float) Math.toDegrees((double) fArr[i15]);
                    int i17 = i16 + 1;
                    boolean z = fArr[i16] != 1.0f;
                    float f9 = degrees2 - degrees;
                    if (Math.abs(f9) >= 360.0f) {
                        path.addCircle(f6, f7, f8, z ? Path.Direction.CCW : Path.Direction.CW);
                    } else {
                        float a2 = a(f9, 360.0f);
                        if (z && a2 < 360.0f) {
                            a2 = (360.0f - a2) * -1.0f;
                        }
                        path.arcTo(new RectF(f6 - f8, f7 - f8, f6 + f8, f7 + f8), degrees, a2);
                    }
                    i2 = i17;
                    break;
                default:
                    throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + i4);
            }
        }
        return path;
    }
}
