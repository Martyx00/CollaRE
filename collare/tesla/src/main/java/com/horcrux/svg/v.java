package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import com.facebook.react.uimanager.a.a;
import com.horcrux.svg.w;

/* access modifiers changed from: package-private */
/* compiled from: PathShadowNode */
public class v extends RenderableShadowNode {

    /* renamed from: a  reason: collision with root package name */
    private Path f4668a;

    v() {
    }

    @a(a = "d")
    public void setD(String str) {
        this.f4668a = new w.a(str, this.mScale).a();
        markUpdated();
    }

    /* access modifiers changed from: protected */
    @Override // com.horcrux.svg.am, com.horcrux.svg.RenderableShadowNode
    public Path getPath(Canvas canvas, Paint paint) {
        return this.f4668a;
    }

    public Path a() {
        return this.f4668a;
    }
}
