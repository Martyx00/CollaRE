package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.f.f.a;
import com.facebook.imagepipeline.o.c;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.o;
import com.facebook.react.views.text.x;

/* compiled from: FrescoBasedReactTextInlineImageSpan */
public class b extends x {

    /* renamed from: a  reason: collision with root package name */
    private Drawable f3438a;

    /* renamed from: b  reason: collision with root package name */
    private final com.facebook.f.c.b f3439b;

    /* renamed from: c  reason: collision with root package name */
    private final com.facebook.f.i.b<a> f3440c;

    /* renamed from: d  reason: collision with root package name */
    private final Object f3441d;
    private int e;
    private int f;
    private Uri g;
    private int h;
    private ReadableMap i;
    private TextView j;

    public b(Resources resources, int i2, int i3, int i4, Uri uri, ReadableMap readableMap, com.facebook.f.c.b bVar, Object obj) {
        this.f3440c = new com.facebook.f.i.b<>(com.facebook.f.f.b.a(resources).r());
        this.f3439b = bVar;
        this.f3441d = obj;
        this.f = i4;
        this.g = uri == null ? Uri.EMPTY : uri;
        this.i = readableMap;
        this.h = (int) o.a((float) i3);
        this.e = (int) o.a((float) i2);
    }

    @Override // com.facebook.react.views.text.x
    public void b() {
        this.f3440c.c();
    }

    @Override // com.facebook.react.views.text.x
    public void c() {
        this.f3440c.c();
    }

    @Override // com.facebook.react.views.text.x
    public void d() {
        this.f3440c.b();
    }

    @Override // com.facebook.react.views.text.x
    public void e() {
        this.f3440c.b();
    }

    @Override // com.facebook.react.views.text.x
    public Drawable a() {
        return this.f3438a;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = -this.e;
            fontMetricsInt.descent = 0;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = 0;
        }
        return this.h;
    }

    @Override // com.facebook.react.views.text.x
    public void a(TextView textView) {
        this.j = textView;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        if (this.f3438a == null) {
            this.f3440c.a(this.f3439b.c().c(this.f3440c.d()).a(this.f3441d).b(com.facebook.react.modules.fresco.a.a(c.a(this.g), this.i)).o());
            this.f3439b.c();
            this.f3438a = this.f3440c.f();
            this.f3438a.setBounds(0, 0, this.h, this.e);
            int i7 = this.f;
            if (i7 != 0) {
                this.f3438a.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
            }
            this.f3438a.setCallback(this.j);
        }
        canvas.save();
        canvas.translate(f2, (float) (((i5 + ((int) paint.descent())) - (((int) (paint.descent() - paint.ascent())) / 2)) - ((this.f3438a.getBounds().bottom - this.f3438a.getBounds().top) / 2)));
        this.f3438a.draw(canvas);
        canvas.restore();
    }

    @Override // com.facebook.react.views.text.x
    public int f() {
        return this.e;
    }
}
