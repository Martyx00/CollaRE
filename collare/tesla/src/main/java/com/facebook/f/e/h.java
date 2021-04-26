package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* compiled from: MatrixDrawable */
public class h extends g {

    /* renamed from: a  reason: collision with root package name */
    private Matrix f1919a;

    /* renamed from: c  reason: collision with root package name */
    private Matrix f1920c;

    /* renamed from: d  reason: collision with root package name */
    private int f1921d;
    private int e;

    @Override // com.facebook.f.e.g
    public Drawable b(Drawable drawable) {
        Drawable b2 = super.b(drawable);
        c();
        return b2;
    }

    @Override // com.facebook.f.e.g
    public void draw(Canvas canvas) {
        b();
        if (this.f1920c != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.f1920c);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.e.g
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        c();
    }

    private void b() {
        if (this.f1921d != getCurrent().getIntrinsicWidth() || this.e != getCurrent().getIntrinsicHeight()) {
            c();
        }
    }

    private void c() {
        Drawable current = getCurrent();
        Rect bounds = getBounds();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.f1921d = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.e = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.f1920c = null;
            return;
        }
        current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        this.f1920c = this.f1919a;
    }

    @Override // com.facebook.f.e.s, com.facebook.f.e.g
    public void a(Matrix matrix) {
        super.a(matrix);
        Matrix matrix2 = this.f1920c;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
    }
}
