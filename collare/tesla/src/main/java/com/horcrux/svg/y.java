package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.uimanager.a.a;

/* access modifiers changed from: package-private */
/* compiled from: RectShadowNode */
public class y extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private String f4678a;

    /* renamed from: b  reason: collision with root package name */
    private String f4679b;

    /* renamed from: c  reason: collision with root package name */
    private String f4680c;

    /* renamed from: d  reason: collision with root package name */
    private String f4681d;
    private String e;
    private String f;

    y() {
    }

    @a(a = "x")
    public void setX(String str) {
        this.f4678a = str;
        markUpdated();
    }

    @a(a = "y")
    public void setY(String str) {
        this.f4679b = str;
        markUpdated();
    }

    @a(a = "width")
    public void setWidth(String str) {
        this.f4680c = str;
        markUpdated();
    }

    @a(a = "height")
    public void setHeight(String str) {
        this.f4681d = str;
        markUpdated();
    }

    @a(a = "rx")
    public void setRx(String str) {
        this.e = str;
        markUpdated();
    }

    @a(a = "ry")
    public void setRy(String str) {
        this.f = str;
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        Path path = new Path();
        double relativeOnWidth = relativeOnWidth(this.f4678a);
        double relativeOnHeight = relativeOnHeight(this.f4679b);
        double relativeOnWidth2 = relativeOnWidth(this.f4680c);
        double relativeOnHeight2 = relativeOnHeight(this.f4681d);
        double relativeOnWidth3 = relativeOnWidth(this.e);
        double relativeOnHeight3 = relativeOnHeight(this.f);
        int i = (relativeOnWidth3 > 0.0d ? 1 : (relativeOnWidth3 == 0.0d ? 0 : -1));
        if (i == 0 && relativeOnHeight3 == 0.0d) {
            path.addRect((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2), Path.Direction.CW);
        } else {
            if (i == 0) {
                relativeOnWidth3 = relativeOnHeight3;
            } else if (relativeOnHeight3 == 0.0d) {
                relativeOnHeight3 = relativeOnWidth3;
            }
            double d2 = relativeOnWidth2 / 2.0d;
            if (relativeOnWidth3 > d2) {
                relativeOnWidth3 = d2;
            }
            double d3 = relativeOnHeight2 / 2.0d;
            if (relativeOnHeight3 <= d3) {
                d3 = relativeOnHeight3;
            }
            path.addRoundRect(new RectF((float) relativeOnWidth, (float) relativeOnHeight, (float) (relativeOnWidth + relativeOnWidth2), (float) (relativeOnHeight + relativeOnHeight2)), (float) relativeOnWidth3, (float) d3, Path.Direction.CW);
        }
        return path;
    }
}
