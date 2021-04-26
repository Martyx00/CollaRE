package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.a.a;

/* compiled from: CircleShadowNode */
class c extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private String f4606a;

    /* renamed from: b  reason: collision with root package name */
    private String f4607b;

    /* renamed from: c  reason: collision with root package name */
    private String f4608c;

    c() {
    }

    @a(a = "cx")
    public void setCx(String str) {
        this.f4606a = str;
        markUpdated();
    }

    @a(a = "cy")
    public void setCy(String str) {
        this.f4607b = str;
        markUpdated();
    }

    @a(a = "r")
    public void setR(String str) {
        this.f4608c = str;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        double d2;
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f4606a);
        double relativeOnHeight = relativeOnHeight(this.f4607b);
        if (w.a(this.f4608c)) {
            d2 = relativeOnOther(this.f4608c);
        } else {
            d2 = Double.parseDouble(this.f4608c) * ((double) this.mScale);
        }
        path.addCircle((float) relativeOnWidth, (float) relativeOnHeight, (float) d2, Path.Direction.CW);
        return path;
    }
}
