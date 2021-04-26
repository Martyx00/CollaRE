package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.a.a;
import com.google.firebase.analytics.FirebaseAnalytics;

/* access modifiers changed from: package-private */
/* compiled from: TextPathShadowNode */
public class ag extends aj {

    /* renamed from: b  reason: collision with root package name */
    private String f4580b;
    private ah e;
    private af f;
    private String g;
    private ae h = ae.align;
    private ai i = ai.exact;

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.aj, com.horcrux.svg.o
    public void c() {
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.o
    public void d() {
    }

    ag() {
    }

    @a(a = "href")
    public void setHref(String str) {
        this.f4580b = str;
        markUpdated();
    }

    @a(a = "startOffset")
    public void setStartOffset(String str) {
        this.g = str;
        markUpdated();
    }

    @Override // com.horcrux.svg.aj
    @a(a = FirebaseAnalytics.b.METHOD)
    public void setMethod(String str) {
        this.h = ae.valueOf(str);
        markUpdated();
    }

    @a(a = "spacing")
    public void setSpacing(String str) {
        this.i = ai.valueOf(str);
        markUpdated();
    }

    @a(a = "side")
    public void setSide(String str) {
        this.e = ah.valueOf(str);
        markUpdated();
    }

    @a(a = "midLine")
    public void setSharp(String str) {
        this.f = af.valueOf(str);
        markUpdated();
    }

    /* access modifiers changed from: package-private */
    public ah f() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public af g() {
        return this.f;
    }

    /* access modifiers changed from: package-private */
    public String h() {
        return this.g;
    }

    @Override // com.horcrux.svg.aj, com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        a(canvas, paint, f2);
    }

    public Path i() {
        am definedTemplate = getSvgShadowNode().getDefinedTemplate(this.f4580b);
        if (definedTemplate == null || definedTemplate.getClass() != v.class) {
            return null;
        }
        return ((v) definedTemplate).a();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.aj, com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        return a(canvas, paint);
    }
}
