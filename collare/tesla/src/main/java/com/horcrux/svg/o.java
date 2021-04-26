package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import com.horcrux.svg.am;

/* access modifiers changed from: package-private */
/* compiled from: GroupShadowNode */
public class o extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    ReadableMap f4640a;

    /* renamed from: b  reason: collision with root package name */
    private m f4641b;

    o() {
    }

    @a(a = "font")
    public void setFont(ReadableMap readableMap) {
        this.f4640a = readableMap;
        markUpdated();
    }

    /* access modifiers changed from: package-private */
    public void a(Canvas canvas) {
        RectF rectF = new RectF(canvas.getClipBounds());
        this.mMatrix.mapRect(rectF);
        this.f4641b = new m(this.mScale, rectF.width(), rectF.height());
    }

    /* access modifiers changed from: package-private */
    public m a() {
        return this.f4641b;
    }

    /* access modifiers changed from: package-private */
    public m b() {
        return getTextRoot().a();
    }

    /* access modifiers changed from: package-private */
    public void c() {
        b().a(this, this.f4640a);
    }

    /* access modifiers changed from: package-private */
    public void d() {
        b().b();
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public void draw(Canvas canvas, Paint paint, float f) {
        a(canvas);
        if (f > 0.01f) {
            clip(canvas, paint);
            a(canvas, paint, f);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final Canvas canvas, final Paint paint, final float f) {
        c();
        final SvgViewShadowNode svgShadowNode = getSvgShadowNode();
        final RectF rectF = new RectF();
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.o.AnonymousClass1 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    am amVar = (am) wVar;
                    boolean z = amVar instanceof RenderableShadowNode;
                    if (z) {
                        ((RenderableShadowNode) amVar).mergeProperties(this);
                    }
                    int saveAndSetupCanvas = amVar.saveAndSetupCanvas(canvas);
                    amVar.draw(canvas, paint, f * o.this.mOpacity);
                    RectF clientRect = amVar.getClientRect();
                    if (clientRect != null) {
                        rectF.union(clientRect);
                    }
                    amVar.restoreCanvas(canvas, saveAndSetupCanvas);
                    if (z) {
                        ((RenderableShadowNode) amVar).resetProperties();
                    }
                    amVar.markUpdateSeen();
                    if (amVar.isResponsible()) {
                        svgShadowNode.enableTouchEvents();
                    }
                } else if (wVar instanceof SvgViewShadowNode) {
                    ((SvgViewShadowNode) wVar).drawChildren(canvas);
                } else {
                    wVar.calculateLayout();
                }
            }
        });
        setClientRect(rectF);
        d();
    }

    /* access modifiers changed from: package-private */
    public void b(Canvas canvas, Paint paint, float f) {
        super.draw(canvas, paint, f);
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(final Canvas canvas, final Paint paint) {
        final Path path = new Path();
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.o.AnonymousClass2 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    path.addPath(((am) wVar).getPath(canvas, paint));
                }
            }
        });
        return path;
    }

    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public int hitTest(float[] fArr) {
        am amVar;
        int hitTest;
        if (!this.mInvertible) {
            return -1;
        }
        float[] fArr2 = new float[2];
        this.mInvMatrix.mapPoints(fArr2, fArr);
        int round = Math.round(fArr2[0]);
        int round2 = Math.round(fArr2[1]);
        Path clipPath = getClipPath();
        if (clipPath != null) {
            if (this.mClipRegionPath != clipPath) {
                this.mClipRegionPath = clipPath;
                this.mClipRegion = getRegion(clipPath);
            }
            if (!this.mClipRegion.contains(round, round2)) {
                return -1;
            }
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            x childAt = getChildAt(childCount);
            if ((childAt instanceof am) && (hitTest = (amVar = (am) childAt).hitTest(fArr2)) != -1) {
                return (amVar.isResponsible() || hitTest != childAt.getReactTag()) ? hitTest : getReactTag();
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    @Override // com.horcrux.svg.am
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgShadowNode().defineTemplate(this, this.mName);
        }
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.o.AnonymousClass3 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    ((am) wVar).saveDefinition();
                }
            }
        });
    }

    @Override // com.horcrux.svg.RenderableShadowNode
    public void resetProperties() {
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.o.AnonymousClass4 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof RenderableShadowNode) {
                    ((RenderableShadowNode) wVar).resetProperties();
                }
            }
        });
    }
}
