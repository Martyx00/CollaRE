package com.facebook.f.d;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.f.d.a.b;
import com.facebook.f.e.q;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* compiled from: DebugControllerOverlayDrawable */
public class a extends Drawable implements b {

    /* renamed from: a  reason: collision with root package name */
    private String f1891a;

    /* renamed from: b  reason: collision with root package name */
    private String f1892b;

    /* renamed from: c  reason: collision with root package name */
    private int f1893c;

    /* renamed from: d  reason: collision with root package name */
    private int f1894d;
    private int e;
    private String f;
    private q.b g;
    private int h;
    private int i;
    private int j = 80;
    private final Paint k = new Paint(1);
    private final Matrix l = new Matrix();
    private final Rect m = new Rect();
    private final RectF n = new RectF();
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private long t;

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(int i2) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    public a() {
        a();
    }

    public void a() {
        this.f1893c = -1;
        this.f1894d = -1;
        this.e = -1;
        this.h = -1;
        this.i = -1;
        this.f = null;
        a((String) null);
        this.t = -1;
        invalidateSelf();
    }

    public void a(String str) {
        if (str == null) {
            str = "none";
        }
        this.f1891a = str;
        invalidateSelf();
    }

    public void a(int i2, int i3) {
        this.f1893c = i2;
        this.f1894d = i3;
        invalidateSelf();
    }

    public void a(int i2) {
        this.e = i2;
    }

    public void a(q.b bVar) {
        this.g = bVar;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        a(rect, 7, 7);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        this.k.setStyle(Paint.Style.STROKE);
        this.k.setStrokeWidth(2.0f);
        this.k.setColor(-26624);
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.k);
        this.k.setStyle(Paint.Style.FILL);
        this.k.setColor(a(this.f1893c, this.f1894d, this.g));
        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.k);
        this.k.setStyle(Paint.Style.FILL);
        this.k.setStrokeWidth(BitmapDescriptorFactory.HUE_RED);
        this.k.setColor(-1);
        this.r = this.o;
        this.s = this.p;
        String str = this.f1892b;
        if (str != null) {
            a(canvas, "IDs: %s, %s", this.f1891a, str);
        } else {
            a(canvas, "ID: %s", this.f1891a);
        }
        a(canvas, "D: %dx%d", Integer.valueOf(bounds.width()), Integer.valueOf(bounds.height()));
        a(canvas, "I: %dx%d", Integer.valueOf(this.f1893c), Integer.valueOf(this.f1894d));
        a(canvas, "I: %d KiB", Integer.valueOf(this.e / 1024));
        String str2 = this.f;
        if (str2 != null) {
            a(canvas, "i format: %s", str2);
        }
        int i2 = this.h;
        if (i2 > 0) {
            a(canvas, "anim: f %d, l %d", Integer.valueOf(i2), Integer.valueOf(this.i));
        }
        q.b bVar = this.g;
        if (bVar != null) {
            a(canvas, "scale: %s", bVar);
        }
        long j2 = this.t;
        if (j2 >= 0) {
            a(canvas, "t: %d ms", Long.valueOf(j2));
        }
    }

    private void a(Rect rect, int i2, int i3) {
        int min = Math.min(40, Math.max(12, Math.min(rect.width() / i3, rect.height() / i2)));
        this.k.setTextSize((float) min);
        this.q = min + 8;
        if (this.j == 80) {
            this.q *= -1;
        }
        this.o = rect.left + 10;
        this.p = this.j == 80 ? rect.bottom - 10 : rect.top + 10 + 12;
    }

    private void a(Canvas canvas, String str, Object... objArr) {
        if (objArr == null) {
            canvas.drawText(str, (float) this.r, (float) this.s, this.k);
        } else {
            canvas.drawText(String.format(str, objArr), (float) this.r, (float) this.s, this.k);
        }
        this.s += this.q;
    }

    /* access modifiers changed from: package-private */
    public int a(int i2, int i3, q.b bVar) {
        int width = getBounds().width();
        int height = getBounds().height();
        if (width <= 0 || height <= 0 || i2 <= 0 || i3 <= 0) {
            return 1727284022;
        }
        if (bVar != null) {
            Rect rect = this.m;
            rect.top = 0;
            rect.left = 0;
            rect.right = width;
            rect.bottom = height;
            this.l.reset();
            bVar.a(this.l, this.m, i2, i3, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED);
            RectF rectF = this.n;
            rectF.top = BitmapDescriptorFactory.HUE_RED;
            rectF.left = BitmapDescriptorFactory.HUE_RED;
            rectF.right = (float) i2;
            rectF.bottom = (float) i3;
            this.l.mapRect(rectF);
            width = Math.min(width, (int) this.n.width());
            height = Math.min(height, (int) this.n.height());
        }
        float f2 = (float) width;
        float f3 = f2 * 0.1f;
        float f4 = f2 * 0.5f;
        float f5 = (float) height;
        float f6 = 0.1f * f5;
        float f7 = f5 * 0.5f;
        int abs = Math.abs(i2 - width);
        int abs2 = Math.abs(i3 - height);
        float f8 = (float) abs;
        if (f8 < f3 && ((float) abs2) < f6) {
            return 1716301648;
        }
        if (f8 >= f4 || ((float) abs2) >= f7) {
            return 1727284022;
        }
        return 1728026624;
    }

    @Override // com.facebook.f.d.a.b
    public void a(long j2) {
        this.t = j2;
        invalidateSelf();
    }
}
