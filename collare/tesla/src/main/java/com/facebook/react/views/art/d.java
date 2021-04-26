package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.ap;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;

/* compiled from: ARTSurfaceViewShadowNode */
public class d extends h implements TextureView.SurfaceTextureListener {

    /* renamed from: a  reason: collision with root package name */
    private Surface f3300a;

    /* renamed from: b  reason: collision with root package name */
    private Integer f3301b;

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return false;
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtualAnchor() {
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @a(a = "backgroundColor", b = "Color")
    public void setBackgroundColor(Integer num) {
        this.f3301b = num;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.x
    public void onCollectExtraUpdates(ap apVar) {
        super.onCollectExtraUpdates(apVar);
        a();
        apVar.a(getReactTag(), this);
    }

    private void a() {
        Surface surface = this.f3300a;
        if (surface == null || !surface.isValid()) {
            a(this);
            return;
        }
        try {
            Canvas lockCanvas = this.f3300a.lockCanvas(null);
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            if (this.f3301b != null) {
                lockCanvas.drawColor(this.f3301b.intValue());
            }
            Paint paint = new Paint();
            for (int i = 0; i < getChildCount(); i++) {
                f fVar = (f) getChildAt(i);
                fVar.a(lockCanvas, paint, 1.0f);
                fVar.markUpdateSeen();
            }
            if (this.f3300a != null) {
                this.f3300a.unlockCanvasAndPost(lockCanvas);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            com.facebook.common.e.a.d("ReactNative", e.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    private void a(w wVar) {
        for (int i = 0; i < wVar.getChildCount(); i++) {
            w childAt = wVar.getChildAt(i);
            childAt.markUpdateSeen();
            a(childAt);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.f3300a = new Surface(surfaceTexture);
        a();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
        this.f3300a = null;
        return true;
    }
}
