package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.uimanager.a.a;

/* compiled from: EllipseShadowNode */
class h extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private String f4614a;

    /* renamed from: b  reason: collision with root package name */
    private String f4615b;

    /* renamed from: c  reason: collision with root package name */
    private String f4616c;

    /* renamed from: d  reason: collision with root package name */
    private String f4617d;

    h() {
    }

    @a(a = "cx")
    public void setCx(String str) {
        this.f4614a = str;
        markUpdated();
    }

    @a(a = "cy")
    public void setCy(String str) {
        this.f4615b = str;
        markUpdated();
    }

    @a(a = "rx")
    public void setRx(String str) {
        this.f4616c = str;
        markUpdated();
    }

    @a(a = "ry")
    public void setRy(String str) {
        this.f4617d = str;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f4614a);
        double relativeOnHeight = relativeOnHeight(this.f4615b);
        double relativeOnWidth2 = relativeOnWidth(this.f4616c);
        double relativeOnHeight2 = relativeOnHeight(this.f4617d);
        path.addOval(new RectF((float) (relativeOnWidth - relativeOnWidth2), (float) (relativeOnHeight - relativeOnHeight2), (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2)), Path.Direction.CW);
        return path;
    }
}
