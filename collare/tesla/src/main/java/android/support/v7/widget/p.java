package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.g.s;
import android.support.v4.widget.r;
import android.util.AttributeSet;
import android.widget.ImageView;

/* compiled from: AppCompatImageView */
public class p extends ImageView implements s, r {

    /* renamed from: a  reason: collision with root package name */
    private final f f1267a;

    /* renamed from: b  reason: collision with root package name */
    private final o f1268b;

    public p(Context context) {
        this(context, null);
    }

    public p(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public p(Context context, AttributeSet attributeSet, int i) {
        super(as.a(context), attributeSet, i);
        this.f1267a = new f(this);
        this.f1267a.a(attributeSet, i);
        this.f1268b = new o(this);
        this.f1268b.a(attributeSet, i);
    }

    public void setImageResource(int i) {
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.a(i);
        }
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.d();
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.d();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.d();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f fVar = this.f1267a;
        if (fVar != null) {
            fVar.a(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f fVar = this.f1267a;
        if (fVar != null) {
            fVar.a(drawable);
        }
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f fVar = this.f1267a;
        if (fVar != null) {
            fVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.g.s
    public ColorStateList getSupportBackgroundTintList() {
        f fVar = this.f1267a;
        if (fVar != null) {
            return fVar.a();
        }
        return null;
    }

    @Override // android.support.v4.g.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f fVar = this.f1267a;
        if (fVar != null) {
            fVar.a(mode);
        }
    }

    @Override // android.support.v4.g.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f fVar = this.f1267a;
        if (fVar != null) {
            return fVar.b();
        }
        return null;
    }

    @Override // android.support.v4.widget.r
    public void setSupportImageTintList(ColorStateList colorStateList) {
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.a(colorStateList);
        }
    }

    @Override // android.support.v4.widget.r
    public ColorStateList getSupportImageTintList() {
        o oVar = this.f1268b;
        if (oVar != null) {
            return oVar.b();
        }
        return null;
    }

    @Override // android.support.v4.widget.r
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.a(mode);
        }
    }

    @Override // android.support.v4.widget.r
    public PorterDuff.Mode getSupportImageTintMode() {
        o oVar = this.f1268b;
        if (oVar != null) {
            return oVar.c();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f fVar = this.f1267a;
        if (fVar != null) {
            fVar.c();
        }
        o oVar = this.f1268b;
        if (oVar != null) {
            oVar.d();
        }
    }

    public boolean hasOverlappingRendering() {
        return this.f1268b.a() && super.hasOverlappingRendering();
    }
}
