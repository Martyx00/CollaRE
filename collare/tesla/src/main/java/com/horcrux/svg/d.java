package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.common.e.a;

/* compiled from: ClipPathShadowNode */
class d extends o {
    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public int hitTest(float[] fArr) {
        return -1;
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public boolean isResponsible() {
        return false;
    }

    @Override // com.horcrux.svg.RenderableShadowNode
    public void mergeProperties(RenderableShadowNode renderableShadowNode) {
    }

    @Override // com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void resetProperties() {
    }

    d() {
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.o, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f) {
        a.c("ReactNative", "RNSVG: ClipPath can't be drawn, it should be defined as a child component for `Defs` ");
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.o
    public void saveDefinition() {
        getSvgShadowNode().defineClipPath(this, this.mName);
    }
}
