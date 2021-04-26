package com.facebook.react.views.modal;

import android.graphics.Point;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.x;

/* access modifiers changed from: package-private */
/* compiled from: ModalHostShadowNode */
public class b extends h {
    @Override // com.facebook.react.uimanager.x
    public void addChildAt(x xVar, int i) {
        super.addChildAt(xVar, i);
        Point a2 = a.a(getThemedContext());
        xVar.setStyleWidth((float) a2.x);
        xVar.setStyleHeight((float) a2.y);
    }
}
