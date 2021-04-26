package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;

/* compiled from: ARTGroupShadowNode */
public class a extends f {

    /* renamed from: a  reason: collision with root package name */
    protected RectF f3297a;

    @Override // com.facebook.react.views.art.f, com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return true;
    }

    @com.facebook.react.uimanager.a.a(a = "clipping")
    public void setClipping(ReadableArray readableArray) {
        float[] a2 = g.a(readableArray);
        if (a2 != null) {
            this.f3297a = a(a2);
            markUpdated();
        }
    }

    @Override // com.facebook.react.views.art.f
    public void a(Canvas canvas, Paint paint, float f) {
        float f2 = f * this.f3305b;
        if (f2 > 0.01f) {
            a(canvas);
            RectF rectF = this.f3297a;
            if (rectF != null) {
                canvas.clipRect(rectF.left * this.f3306c, this.f3297a.top * this.f3306c, this.f3297a.right * this.f3306c, this.f3297a.bottom * this.f3306c, Region.Op.REPLACE);
            }
            for (int i = 0; i < getChildCount(); i++) {
                f fVar = (f) getChildAt(i);
                fVar.a(canvas, paint, f2);
                fVar.markUpdateSeen();
            }
            b(canvas);
        }
    }

    private static RectF a(float[] fArr) {
        if (fArr.length == 4) {
            return new RectF(fArr[0], fArr[1], fArr[0] + fArr[2], fArr[1] + fArr[3]);
        }
        throw new JSApplicationIllegalArgumentException("Clipping should be array of length 4 (e.g. [x, y, width, height])");
    }
}
