package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.x;

/* compiled from: ARTVirtualNode */
public abstract class f extends x {

    /* renamed from: a  reason: collision with root package name */
    private static final float[] f3303a = new float[9];

    /* renamed from: d  reason: collision with root package name */
    private static final float[] f3304d = new float[9];

    /* renamed from: b  reason: collision with root package name */
    protected float f3305b = 1.0f;

    /* renamed from: c  reason: collision with root package name */
    protected final float f3306c = c.a().density;
    private Matrix e = new Matrix();

    public abstract void a(Canvas canvas, Paint paint, float f);

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final void a(Canvas canvas) {
        canvas.save();
        Matrix matrix = this.e;
        if (matrix != null) {
            canvas.concat(matrix);
        }
    }

    /* access modifiers changed from: protected */
    public void b(Canvas canvas) {
        canvas.restore();
    }

    @a(a = "opacity", d = 1.0f)
    public void setOpacity(float f) {
        this.f3305b = f;
        markUpdated();
    }

    @a(a = "transform")
    public void setTransform(ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = g.a(readableArray, f3303a);
            if (a2 == 6) {
                a();
            } else if (a2 != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        } else {
            this.e = null;
        }
        markUpdated();
    }

    /* access modifiers changed from: protected */
    public void a() {
        float[] fArr = f3304d;
        float[] fArr2 = f3303a;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[2];
        float f = fArr2[4];
        float f2 = this.f3306c;
        fArr[2] = f * f2;
        fArr[3] = fArr2[1];
        fArr[4] = fArr2[3];
        fArr[5] = fArr2[5] * f2;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        if (this.e == null) {
            this.e = new Matrix();
        }
        this.e.setValues(f3304d);
    }
}
