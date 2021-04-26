package com.facebook.react.uimanager.c;

import android.view.View;
import android.view.animation.Animation;

/* compiled from: LayoutUpdateAnimation */
class k extends a {
    k() {
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.react.uimanager.c.a
    public boolean a() {
        return this.f3190b > 0;
    }

    /* access modifiers changed from: package-private */
    @Override // com.facebook.react.uimanager.c.a
    public Animation a(View view, int i, int i2, int i3, int i4) {
        boolean z = false;
        boolean z2 = (view.getX() == ((float) i) && view.getY() == ((float) i2)) ? false : true;
        if (!(view.getWidth() == i3 && view.getHeight() == i4)) {
            z = true;
        }
        if (z2 || z) {
            return new m(view, i, i2, i3, i4);
        }
        return null;
    }
}
