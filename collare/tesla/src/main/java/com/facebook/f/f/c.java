package com.facebook.f.f;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.facebook.f.e.g;
import com.facebook.f.e.t;
import com.facebook.f.e.u;

/* compiled from: RootDrawable */
public class c extends g implements t {

    /* renamed from: a  reason: collision with root package name */
    Drawable f1955a = null;

    /* renamed from: c  reason: collision with root package name */
    private u f1956c;

    @Override // com.facebook.f.e.g
    public int getIntrinsicHeight() {
        return -1;
    }

    @Override // com.facebook.f.e.g
    public int getIntrinsicWidth() {
        return -1;
    }

    public c(Drawable drawable) {
        super(drawable);
    }

    @Override // com.facebook.f.e.t
    public void a(u uVar) {
        this.f1956c = uVar;
    }

    @Override // com.facebook.f.e.g
    public boolean setVisible(boolean z, boolean z2) {
        u uVar = this.f1956c;
        if (uVar != null) {
            uVar.a(z);
        }
        return super.setVisible(z, z2);
    }

    @Override // com.facebook.f.e.g
    @SuppressLint({"WrongCall"})
    public void draw(Canvas canvas) {
        if (isVisible()) {
            u uVar = this.f1956c;
            if (uVar != null) {
                uVar.a();
            }
            super.draw(canvas);
            Drawable drawable = this.f1955a;
            if (drawable != null) {
                drawable.setBounds(getBounds());
                this.f1955a.draw(canvas);
            }
        }
    }

    public void d(Drawable drawable) {
        this.f1955a = drawable;
        invalidateSelf();
    }
}
