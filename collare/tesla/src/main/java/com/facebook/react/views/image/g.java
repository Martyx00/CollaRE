package com.facebook.react.views.image;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.f.e.q;

/* compiled from: ScaleTypeStartInside */
public class g extends q.a {
    public static final q.b j = new g();

    public String toString() {
        return "start_inside";
    }

    @Override // com.facebook.f.e.q.a
    public void a(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float min = Math.min(Math.min(f3, f4), 1.0f);
        matrix.setScale(min, min);
        matrix.postTranslate((float) ((int) (((float) rect.left) + 0.5f)), (float) ((int) (((float) rect.top) + 0.5f)));
    }
}
