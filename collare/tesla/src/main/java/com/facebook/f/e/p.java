package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.d.h;
import com.facebook.common.d.i;
import com.facebook.f.e.q;

/* compiled from: ScaleTypeDrawable */
public class p extends g {

    /* renamed from: a  reason: collision with root package name */
    q.b f1940a;

    /* renamed from: c  reason: collision with root package name */
    Object f1941c;

    /* renamed from: d  reason: collision with root package name */
    PointF f1942d = null;
    int e = 0;
    int f = 0;
    Matrix g;
    private Matrix h = new Matrix();

    public p(Drawable drawable, q.b bVar) {
        super((Drawable) i.a(drawable));
        this.f1940a = bVar;
    }

    @Override // com.facebook.f.e.g
    public Drawable b(Drawable drawable) {
        Drawable b2 = super.b(drawable);
        c();
        return b2;
    }

    public q.b b() {
        return this.f1940a;
    }

    public void a(q.b bVar) {
        if (!h.a(this.f1940a, bVar)) {
            this.f1940a = bVar;
            this.f1941c = null;
            c();
            invalidateSelf();
        }
    }

    public void a(PointF pointF) {
        if (!h.a(this.f1942d, pointF)) {
            if (this.f1942d == null) {
                this.f1942d = new PointF();
            }
            this.f1942d.set(pointF);
            c();
            invalidateSelf();
        }
    }

    @Override // com.facebook.f.e.g
    public void draw(Canvas canvas) {
        d();
        if (this.g != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.g);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.e.g
    public void onBoundsChange(Rect rect) {
        c();
    }

    private void d() {
        boolean z;
        q.b bVar = this.f1940a;
        boolean z2 = true;
        if (bVar instanceof q.l) {
            Object a2 = ((q.l) bVar).a();
            z = a2 == null || !a2.equals(this.f1941c);
            this.f1941c = a2;
        } else {
            z = false;
        }
        if (this.e == getCurrent().getIntrinsicWidth() && this.f == getCurrent().getIntrinsicHeight()) {
            z2 = false;
        }
        if (z2 || z) {
            c();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        Drawable current = getCurrent();
        Rect bounds = getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int intrinsicWidth = current.getIntrinsicWidth();
        this.e = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        this.f = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            current.setBounds(bounds);
            this.g = null;
        } else if (intrinsicWidth == width && intrinsicHeight == height) {
            current.setBounds(bounds);
            this.g = null;
        } else if (this.f1940a == q.b.f1943a) {
            current.setBounds(bounds);
            this.g = null;
        } else {
            current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            q.b bVar = this.f1940a;
            Matrix matrix = this.h;
            PointF pointF = this.f1942d;
            float f2 = pointF != null ? pointF.x : 0.5f;
            PointF pointF2 = this.f1942d;
            bVar.a(matrix, bounds, intrinsicWidth, intrinsicHeight, f2, pointF2 != null ? pointF2.y : 0.5f);
            this.g = this.h;
        }
    }

    @Override // com.facebook.f.e.s, com.facebook.f.e.g
    public void a(Matrix matrix) {
        b(matrix);
        d();
        Matrix matrix2 = this.g;
        if (matrix2 != null) {
            matrix.preConcat(matrix2);
        }
    }
}
