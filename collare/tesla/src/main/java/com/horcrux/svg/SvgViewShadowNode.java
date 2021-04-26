package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Base64;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.ap;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.w;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.horcrux.svg.am;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class SvgViewShadowNode extends h {
    private String mAlign;
    private Canvas mCanvas;
    private final Map<String, b> mDefinedBrushes = new HashMap();
    private final Map<String, am> mDefinedClipPaths = new HashMap();
    private final Map<String, am> mDefinedTemplates = new HashMap();
    private Matrix mInvViewBoxMatrix = new Matrix();
    private boolean mInvertible = true;
    private int mMeetOrSlice;
    private float mMinX;
    private float mMinY;
    private boolean mResponsible = false;
    private final float mScale = c.b().density;
    private float mVbHeight;
    private float mVbWidth;
    private String mbbHeight;
    private String mbbWidth;

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return false;
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtualAnchor() {
        return false;
    }

    @a(a = "minX")
    public void setMinX(float f) {
        this.mMinX = f;
        markUpdated();
    }

    @a(a = "minY")
    public void setMinY(float f) {
        this.mMinY = f;
        markUpdated();
    }

    @a(a = "vbWidth")
    public void setVbWidth(float f) {
        this.mVbWidth = f;
        markUpdated();
    }

    @a(a = "vbHeight")
    public void setVbHeight(float f) {
        this.mVbHeight = f;
        markUpdated();
    }

    @a(a = "bbWidth")
    public void setVbWidth(String str) {
        this.mbbWidth = str;
        markUpdated();
    }

    @a(a = "bbHeight")
    public void setVbHeight(String str) {
        this.mbbHeight = str;
        markUpdated();
    }

    @a(a = "align")
    public void setAlign(String str) {
        this.mAlign = str;
        markUpdated();
    }

    @a(a = "meetOrSlice")
    public void setMeetOrSlice(int i) {
        this.mMeetOrSlice = i;
        markUpdated();
    }

    @Override // com.facebook.react.uimanager.x
    public void onCollectExtraUpdates(ap apVar) {
        super.onCollectExtraUpdates(apVar);
        apVar.a(getReactTag(), drawOutput());
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public void setReactTag(int i) {
        super.setReactTag(i);
        SvgViewManager.setShadowNode(this);
    }

    private Object drawOutput() {
        int layoutWidth = (int) getLayoutWidth();
        int layoutHeight = (int) getLayoutHeight();
        if (layoutHeight == 0 || layoutWidth == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(layoutWidth, layoutHeight, Bitmap.Config.ARGB_8888);
        drawChildren(new Canvas(createBitmap));
        return createBitmap;
    }

    /* access modifiers changed from: package-private */
    public Rect getCanvasBounds() {
        return this.mCanvas.getClipBounds();
    }

    /* access modifiers changed from: package-private */
    public void drawChildren(final Canvas canvas) {
        float f;
        float f2;
        this.mCanvas = canvas;
        if (this.mAlign != null) {
            RectF viewBox = getViewBox();
            boolean z = getNativeParent() instanceof SvgViewShadowNode;
            if (z) {
                f2 = Float.parseFloat(this.mbbWidth) * this.mScale;
                f = Float.parseFloat(this.mbbHeight) * this.mScale;
            } else {
                f2 = getLayoutWidth();
                f = getLayoutHeight();
            }
            RectF rectF = new RectF(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, f2, f);
            if (z) {
                canvas.clipRect(rectF);
            }
            Matrix a2 = al.a(viewBox, rectF, this.mAlign, this.mMeetOrSlice);
            this.mInvertible = a2.invert(this.mInvViewBoxMatrix);
            canvas.concat(a2);
        }
        final Paint paint = new Paint();
        paint.setFlags(385);
        paint.setTypeface(Typeface.DEFAULT);
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.SvgViewShadowNode.AnonymousClass1 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    ((am) wVar).saveDefinition();
                }
            }
        });
        traverseChildren(new am.a() {
            /* class com.horcrux.svg.SvgViewShadowNode.AnonymousClass2 */

            @Override // com.horcrux.svg.am.a
            public void a(w wVar) {
                if (wVar instanceof am) {
                    am amVar = (am) wVar;
                    int saveAndSetupCanvas = amVar.saveAndSetupCanvas(canvas);
                    amVar.draw(canvas, paint, 1.0f);
                    amVar.restoreCanvas(canvas, saveAndSetupCanvas);
                    amVar.markUpdateSeen();
                    if (amVar.isResponsible() && !SvgViewShadowNode.this.mResponsible) {
                        SvgViewShadowNode.this.mResponsible = true;
                        return;
                    }
                    return;
                }
                wVar.calculateLayout();
            }
        });
    }

    private RectF getViewBox() {
        float f = this.mMinX;
        float f2 = this.mScale;
        float f3 = this.mMinY;
        return new RectF(f * f2, f3 * f2, (f + this.mVbWidth) * f2, (f3 + this.mVbHeight) * f2);
    }

    /* access modifiers changed from: package-private */
    public String toDataURL() {
        Bitmap createBitmap = Bitmap.createBitmap((int) getLayoutWidth(), (int) getLayoutHeight(), Bitmap.Config.ARGB_8888);
        drawChildren(new Canvas(createBitmap));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        createBitmap.recycle();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* access modifiers changed from: package-private */
    public void enableTouchEvents() {
        if (!this.mResponsible) {
            this.mResponsible = true;
        }
    }

    /* access modifiers changed from: package-private */
    public int hitTest(Point point) {
        if (!this.mResponsible || !this.mInvertible) {
            return -1;
        }
        float[] fArr = {(float) point.x, (float) point.y};
        this.mInvViewBoxMatrix.mapPoints(fArr);
        int childCount = getChildCount() - 1;
        int i = -1;
        while (childCount >= 0 && (!(getChildAt(childCount) instanceof am) || (i = ((am) getChildAt(childCount)).hitTest(fArr)) == -1)) {
            childCount--;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public void defineClipPath(am amVar, String str) {
        this.mDefinedClipPaths.put(str, amVar);
    }

    /* access modifiers changed from: package-private */
    public am getDefinedClipPath(String str) {
        return this.mDefinedClipPaths.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineTemplate(am amVar, String str) {
        this.mDefinedTemplates.put(str, amVar);
    }

    /* access modifiers changed from: package-private */
    public am getDefinedTemplate(String str) {
        return this.mDefinedTemplates.get(str);
    }

    /* access modifiers changed from: package-private */
    public void defineBrush(b bVar, String str) {
        this.mDefinedBrushes.put(str, bVar);
    }

    /* access modifiers changed from: package-private */
    public b getDefinedBrush(String str) {
        return this.mDefinedBrushes.get(str);
    }

    /* access modifiers changed from: package-private */
    public void traverseChildren(am.a aVar) {
        for (int i = 0; i < getChildCount(); i++) {
            aVar.a(getChildAt(i));
        }
    }
}
