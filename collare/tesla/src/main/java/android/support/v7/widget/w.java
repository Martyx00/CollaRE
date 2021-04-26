package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.g.t;
import android.support.v7.a.a;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: AppCompatSeekBarHelper */
public class w extends s {

    /* renamed from: a  reason: collision with root package name */
    private final SeekBar f1281a;

    /* renamed from: b  reason: collision with root package name */
    private Drawable f1282b;

    /* renamed from: c  reason: collision with root package name */
    private ColorStateList f1283c = null;

    /* renamed from: d  reason: collision with root package name */
    private PorterDuff.Mode f1284d = null;
    private boolean e = false;
    private boolean f = false;

    w(SeekBar seekBar) {
        super(seekBar);
        this.f1281a = seekBar;
    }

    /* access modifiers changed from: package-private */
    @Override // android.support.v7.widget.s
    public void a(AttributeSet attributeSet, int i) {
        super.a(attributeSet, i);
        av a2 = av.a(this.f1281a.getContext(), attributeSet, a.j.AppCompatSeekBar, i, 0);
        Drawable b2 = a2.b(a.j.AppCompatSeekBar_android_thumb);
        if (b2 != null) {
            this.f1281a.setThumb(b2);
        }
        a(a2.a(a.j.AppCompatSeekBar_tickMark));
        if (a2.g(a.j.AppCompatSeekBar_tickMarkTintMode)) {
            this.f1284d = ad.a(a2.a(a.j.AppCompatSeekBar_tickMarkTintMode, -1), this.f1284d);
            this.f = true;
        }
        if (a2.g(a.j.AppCompatSeekBar_tickMarkTint)) {
            this.f1283c = a2.e(a.j.AppCompatSeekBar_tickMarkTint);
            this.e = true;
        }
        a2.a();
        d();
    }

    /* access modifiers changed from: package-private */
    public void a(Drawable drawable) {
        Drawable drawable2 = this.f1282b;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f1282b = drawable;
        if (drawable != null) {
            drawable.setCallback(this.f1281a);
            android.support.v4.graphics.drawable.a.b(drawable, t.d(this.f1281a));
            if (drawable.isStateful()) {
                drawable.setState(this.f1281a.getDrawableState());
            }
            d();
        }
        this.f1281a.invalidate();
    }

    private void d() {
        if (this.f1282b == null) {
            return;
        }
        if (this.e || this.f) {
            this.f1282b = android.support.v4.graphics.drawable.a.f(this.f1282b.mutate());
            if (this.e) {
                android.support.v4.graphics.drawable.a.a(this.f1282b, this.f1283c);
            }
            if (this.f) {
                android.support.v4.graphics.drawable.a.a(this.f1282b, this.f1284d);
            }
            if (this.f1282b.isStateful()) {
                this.f1282b.setState(this.f1281a.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void b() {
        Drawable drawable = this.f1282b;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: package-private */
    public void c() {
        Drawable drawable = this.f1282b;
        if (drawable != null && drawable.isStateful() && drawable.setState(this.f1281a.getDrawableState())) {
            this.f1281a.invalidateDrawable(drawable);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        if (this.f1282b != null) {
            int max = this.f1281a.getMax();
            int i = 1;
            if (max > 1) {
                int intrinsicWidth = this.f1282b.getIntrinsicWidth();
                int intrinsicHeight = this.f1282b.getIntrinsicHeight();
                int i2 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                if (intrinsicHeight >= 0) {
                    i = intrinsicHeight / 2;
                }
                this.f1282b.setBounds(-i2, -i, i2, i);
                float width = ((float) ((this.f1281a.getWidth() - this.f1281a.getPaddingLeft()) - this.f1281a.getPaddingRight())) / ((float) max);
                int save = canvas.save();
                canvas.translate((float) this.f1281a.getPaddingLeft(), (float) (this.f1281a.getHeight() / 2));
                for (int i3 = 0; i3 <= max; i3++) {
                    this.f1282b.draw(canvas);
                    canvas.translate(width, BitmapDescriptorFactory.HUE_RED);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
