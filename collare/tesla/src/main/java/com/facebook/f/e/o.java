package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.drawable.NinePatchDrawable;

/* compiled from: RoundedNinePatchDrawable */
public class o extends n {
    public o(NinePatchDrawable ninePatchDrawable) {
        super(ninePatchDrawable);
    }

    @Override // com.facebook.f.e.n
    public void draw(Canvas canvas) {
        if (!a()) {
            super.draw(canvas);
            return;
        }
        b();
        c();
        canvas.clipPath(this.f1939d);
        super.draw(canvas);
    }
}
