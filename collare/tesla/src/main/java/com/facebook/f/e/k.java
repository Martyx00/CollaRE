package com.facebook.f.e;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.ref.WeakReference;

/* compiled from: RoundedBitmapDrawable */
public class k extends n {
    private final Paint w = new Paint();
    private final Paint x = new Paint(1);
    private final Bitmap y;
    private WeakReference<Bitmap> z;

    public k(Resources resources, Bitmap bitmap, Paint paint) {
        super(new BitmapDrawable(resources, bitmap));
        this.y = bitmap;
        if (paint != null) {
            this.w.set(paint);
        }
        this.w.setFlags(1);
        this.x.setStyle(Paint.Style.STROKE);
    }

    @Override // com.facebook.f.e.n
    public void draw(Canvas canvas) {
        if (!a()) {
            super.draw(canvas);
            return;
        }
        b();
        c();
        d();
        int save = canvas.save();
        canvas.concat(this.s);
        canvas.drawPath(this.f1939d, this.w);
        if (this.f1938c > BitmapDescriptorFactory.HUE_RED) {
            this.x.setStrokeWidth(this.f1938c);
            this.x.setColor(e.a(this.f, this.w.getAlpha()));
            canvas.drawPath(this.g, this.x);
        }
        canvas.restoreToCount(save);
    }

    private void d() {
        WeakReference<Bitmap> weakReference = this.z;
        if (weakReference == null || weakReference.get() != this.y) {
            this.z = new WeakReference<>(this.y);
            this.w.setShader(new BitmapShader(this.y, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            this.e = true;
        }
        if (this.e) {
            this.w.getShader().setLocalMatrix(this.v);
            this.e = false;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.f.e.n
    public boolean a() {
        return super.a() && this.y != null;
    }

    @Override // com.facebook.f.e.n
    public void setAlpha(int i) {
        super.setAlpha(i);
        if (i != this.w.getAlpha()) {
            this.w.setAlpha(i);
            super.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.n
    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.w.setColorFilter(colorFilter);
    }
}
