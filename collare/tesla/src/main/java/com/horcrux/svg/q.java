package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.a.a;

/* access modifiers changed from: package-private */
/* compiled from: LineShadowNode */
public class q extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private String f4657a;

    /* renamed from: b  reason: collision with root package name */
    private String f4658b;

    /* renamed from: c  reason: collision with root package name */
    private String f4659c;

    /* renamed from: d  reason: collision with root package name */
    private String f4660d;

    q() {
    }

    @a(a = "x1")
    public void setX1(String str) {
        this.f4657a = str;
        markUpdated();
    }

    @a(a = "y1")
    public void setY1(String str) {
        this.f4658b = str;
        markUpdated();
    }

    @a(a = "x2")
    public void setX2(String str) {
        this.f4659c = str;
        markUpdated();
    }

    @a(a = "y2")
    public void setY2(String str) {
        this.f4660d = str;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f4657a);
        double relativeOnHeight = relativeOnHeight(this.f4658b);
        double relativeOnWidth2 = relativeOnWidth(this.f4659c);
        double relativeOnHeight2 = relativeOnHeight(this.f4660d);
        path.moveTo((float) relativeOnWidth, (float) relativeOnHeight);
        path.lineTo((float) relativeOnWidth2, (float) relativeOnHeight2);
        return path;
    }
}
