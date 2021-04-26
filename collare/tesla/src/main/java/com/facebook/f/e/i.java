package com.facebook.f.e;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import org.spongycastle.crypto.tls.CipherSuite;

/* compiled from: OrientedDrawable */
public class i extends g {

    /* renamed from: a  reason: collision with root package name */
    final Matrix f1922a;

    /* renamed from: c  reason: collision with root package name */
    private int f1923c;

    /* renamed from: d  reason: collision with root package name */
    private int f1924d;
    private final Matrix e = new Matrix();
    private final RectF f = new RectF();

    public i(Drawable drawable, int i, int i2) {
        super(drawable);
        boolean z = true;
        com.facebook.common.d.i.a(i % 90 == 0);
        com.facebook.common.d.i.a((i2 < 0 || i2 > 8) ? false : z);
        this.f1922a = new Matrix();
        this.f1923c = i;
        this.f1924d = i2;
    }

    @Override // com.facebook.f.e.g
    public void draw(Canvas canvas) {
        int i;
        if (this.f1923c > 0 || !((i = this.f1924d) == 0 || i == 1)) {
            int save = canvas.save();
            canvas.concat(this.f1922a);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    @Override // com.facebook.f.e.g
    public int getIntrinsicWidth() {
        int i = this.f1924d;
        if (i == 5 || i == 7 || this.f1923c % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    @Override // com.facebook.f.e.g
    public int getIntrinsicHeight() {
        int i = this.f1924d;
        if (i == 5 || i == 7 || this.f1923c % CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.f.e.g
    public void onBoundsChange(Rect rect) {
        int i;
        Drawable current = getCurrent();
        if (this.f1923c > 0 || !((i = this.f1924d) == 0 || i == 1)) {
            int i2 = this.f1924d;
            if (i2 == 2) {
                this.f1922a.setScale(-1.0f, 1.0f);
            } else if (i2 != 7) {
                switch (i2) {
                    case 4:
                        this.f1922a.setScale(1.0f, -1.0f);
                        break;
                    case 5:
                        this.f1922a.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                        this.f1922a.postScale(1.0f, -1.0f);
                        break;
                    default:
                        this.f1922a.setRotate((float) this.f1923c, (float) rect.centerX(), (float) rect.centerY());
                        break;
                }
            } else {
                this.f1922a.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                this.f1922a.postScale(-1.0f, 1.0f);
            }
            this.e.reset();
            this.f1922a.invert(this.e);
            this.f.set(rect);
            this.e.mapRect(this.f);
            current.setBounds((int) this.f.left, (int) this.f.top, (int) this.f.right, (int) this.f.bottom);
            return;
        }
        current.setBounds(rect);
    }

    @Override // com.facebook.f.e.s, com.facebook.f.e.g
    public void a(Matrix matrix) {
        b(matrix);
        if (!this.f1922a.isIdentity()) {
            matrix.preConcat(this.f1922a);
        }
    }
}
