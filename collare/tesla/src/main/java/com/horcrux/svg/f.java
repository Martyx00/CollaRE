package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.react.uimanager.w;
import com.horcrux.svg.am;

/* compiled from: DefsShadowNode */
class f extends e {
    f() {
    }

    @Override // com.horcrux.svg.e, com.horcrux.svg.am
    public void draw(Canvas canvas, Paint paint, float f) {
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.f.AnonymousClass1 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                wVar.markUpdateSeen();
                if (wVar instanceof am) {
                    ((am) wVar).traverseChildren(this);
                } else if (wVar instanceof SvgViewShadowNode) {
                    ((SvgViewShadowNode) wVar).traverseChildren(this);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @Override // com.horcrux.svg.am
    public void saveDefinition() {
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.f.AnonymousClass2 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    ((am) wVar).saveDefinition();
                }
            }
        });
    }
}
