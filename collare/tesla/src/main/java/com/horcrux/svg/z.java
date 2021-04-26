package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.facebook.react.uimanager.a.a;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: SymbolShadowNode */
public class z extends o {

    /* renamed from: b  reason: collision with root package name */
    private float f4682b;

    /* renamed from: c  reason: collision with root package name */
    private float f4683c;

    /* renamed from: d  reason: collision with root package name */
    private float f4684d;
    private float e;
    private String f;
    private int g;

    z() {
    }

    @a(a = "minX")
    public void setMinX(float f2) {
        this.f4682b = f2;
        markUpdated();
    }

    @a(a = "minY")
    public void setMinY(float f2) {
        this.f4683c = f2;
        markUpdated();
    }

    @a(a = "vbWidth")
    public void setVbWidth(float f2) {
        this.f4684d = f2;
        markUpdated();
    }

    @a(a = "vbHeight")
    public void setVbHeight(float f2) {
        this.e = f2;
        markUpdated();
    }

    @a(a = "align")
    public void setAlign(String str) {
        this.f = str;
        markUpdated();
    }

    @a(a = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.g = i;
        markUpdated();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        saveDefinition();
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas, Paint paint, float f2, float f3, float f4) {
        if (this.f != null) {
            canvas.concat(al.a(new RectF(this.f4682b * this.mScale, this.f4683c * this.mScale, (this.f4682b + this.f4684d) * this.mScale, (this.f4683c + this.e) * this.mScale), new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f3, f4), this.f, this.g));
            super.draw(canvas, paint, f2);
        }
    }
}
