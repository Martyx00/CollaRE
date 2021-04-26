package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.a.a;

/* compiled from: UseShadowNode */
class ak extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private String f4591a;

    /* renamed from: b  reason: collision with root package name */
    private String f4592b;

    /* renamed from: c  reason: collision with root package name */
    private String f4593c;

    ak() {
    }

    @a(a = "href")
    public void setHref(String str) {
        this.f4591a = str;
        markUpdated();
    }

    @a(a = "width")
    public void setWidth(String str) {
        this.f4592b = str;
        markUpdated();
    }

    @a(a = "height")
    public void setHeight(String str) {
        this.f4593c = str;
        markUpdated();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f) {
        am definedTemplate = getSvgShadowNode().getDefinedTemplate(this.f4591a);
        if (definedTemplate != null) {
            boolean z = definedTemplate instanceof RenderableShadowNode;
            if (z) {
                ((RenderableShadowNode) definedTemplate).mergeProperties(this);
            }
            int saveAndSetupCanvas = definedTemplate.saveAndSetupCanvas(canvas);
            clip(canvas, paint);
            if (definedTemplate instanceof z) {
                ((z) definedTemplate).a(canvas, paint, f, (float) relativeOnWidth(this.f4592b), (float) relativeOnHeight(this.f4593c));
            } else {
                definedTemplate.draw(canvas, paint, f * this.mOpacity);
            }
            setClientRect(definedTemplate.getClientRect());
            definedTemplate.restoreCanvas(canvas, saveAndSetupCanvas);
            if (z) {
                ((RenderableShadowNode) definedTemplate).resetProperties();
                return;
            }
            return;
        }
        com.facebook.common.e.a.c("ReactNative", "`Use` element expected a pre-defined svg template as `href` prop, template named: " + this.f4591a + " is not defined.");
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public int hitTest(float[] fArr) {
        if (!this.mInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        am definedTemplate = getSvgShadowNode().getDefinedTemplate(this.f4591a);
        int hitTest = definedTemplate.hitTest(fArr2);
        if (hitTest != -1) {
            return (definedTemplate.isResponsible() || hitTest != definedTemplate.getReactTag()) ? hitTest : getReactTag();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        return new Path();
    }
}
