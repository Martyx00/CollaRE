package com.horcrux.svg;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.c;
import com.facebook.react.uimanager.h;
import com.facebook.react.uimanager.n;
import com.facebook.react.uimanager.w;
import com.facebook.react.uimanager.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

/* access modifiers changed from: package-private */
/* compiled from: VirtualNode */
public abstract class am extends h {
    private static final int CLIP_RULE_EVENODD = 0;
    private static final int CLIP_RULE_NONZERO = 1;
    static final float MIN_OPACITY_FOR_DRAW = 0.01f;
    private static final double M_SQRT1_2l = 0.7071067811865476d;
    private static final float[] sRawMatrix = {1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 1.0f};
    private float canvasHeight = -1.0f;
    private float canvasWidth = -1.0f;
    private m glyphContext;
    RectF mBox;
    private Path mCachedClipPath;
    RectF mClientRect;
    private String mClipPath;
    Region mClipRegion;
    Path mClipRegionPath;
    private int mClipRule;
    Matrix mInvMatrix = new Matrix();
    boolean mInvertible = true;
    Matrix mMatrix = new Matrix();
    String mName;
    float mOpacity = 1.0f;
    Path mPath;
    Region mRegion;
    private boolean mResponsible;
    final float mScale;
    private SvgViewShadowNode mSvgShadowNode;
    private o mTextRoot;

    /* access modifiers changed from: package-private */
    /* compiled from: VirtualNode */
    public interface a {
        void a(w wVar);
    }

    public abstract void draw(Canvas canvas, Paint paint, float f);

    /* access modifiers changed from: protected */
    public abstract Path getPath(Canvas canvas, Paint paint);

    public abstract int hitTest(float[] fArr);

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtual() {
        return true;
    }

    @Override // com.facebook.react.uimanager.w, com.facebook.react.uimanager.x
    public boolean isVirtualAnchor() {
        return true;
    }

    am() {
        setIsLayoutOnly(true);
        this.mScale = c.b().density;
    }

    @Override // com.facebook.react.uimanager.x
    public void markUpdated() {
        super.markUpdated();
        this.canvasHeight = -1.0f;
        this.canvasWidth = -1.0f;
        this.mRegion = null;
        this.mPath = null;
        this.mBox = null;
    }

    /* access modifiers changed from: package-private */
    public o getTextRoot() {
        if (this.mTextRoot == null) {
            am amVar = this;
            while (true) {
                if (amVar == null) {
                    break;
                }
                if (amVar instanceof o) {
                    o oVar = (o) amVar;
                    if (oVar.a() != null) {
                        this.mTextRoot = oVar;
                        break;
                    }
                }
                x parent = amVar.getParent();
                if (!(parent instanceof am)) {
                    amVar = null;
                } else {
                    amVar = (am) parent;
                }
            }
        }
        return this.mTextRoot;
    }

    /* access modifiers changed from: package-private */
    public o getParentTextRoot() {
        x parent = getParent();
        if (!(parent instanceof am)) {
            return null;
        }
        return ((am) parent).getTextRoot();
    }

    private double getFontSizeFromContext() {
        o textRoot = getTextRoot();
        if (textRoot == null) {
            return 12.0d;
        }
        if (this.glyphContext == null) {
            this.glyphContext = textRoot.a();
        }
        return this.glyphContext.c();
    }

    /* access modifiers changed from: package-private */
    public int saveAndSetupCanvas(Canvas canvas) {
        int save = canvas.save();
        canvas.concat(this.mMatrix);
        return save;
    }

    /* access modifiers changed from: package-private */
    public void restoreCanvas(Canvas canvas, int i) {
        canvas.restoreToCount(i);
    }

    @com.facebook.react.uimanager.a.a(a = "name")
    public void setName(String str) {
        this.mName = str;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "clipPath")
    public void setClipPath(String str) {
        this.mCachedClipPath = null;
        this.mClipPath = str;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "clipRule", e = 1)
    public void clipRule(int i) {
        this.mClipRule = i;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "opacity", d = 1.0f)
    public void setOpacity(float f) {
        this.mOpacity = f;
        markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "matrix")
    public void setMatrix(ReadableArray readableArray) {
        if (readableArray != null) {
            int a2 = w.a(readableArray, sRawMatrix, this.mScale);
            if (a2 == 6) {
                if (this.mMatrix == null) {
                    this.mMatrix = new Matrix();
                    this.mInvMatrix = new Matrix();
                }
                this.mMatrix.setValues(sRawMatrix);
                this.mInvertible = this.mMatrix.invert(this.mInvMatrix);
            } else if (a2 != -1) {
                com.facebook.common.e.a.c("ReactNative", "RNSVG: Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
            this.mInvMatrix = null;
        }
        super.markUpdated();
    }

    @com.facebook.react.uimanager.a.a(a = "responsible")
    public void setResponsible(boolean z) {
        this.mResponsible = z;
        markUpdated();
    }

    /* access modifiers changed from: package-private */
    public Path getClipPath() {
        return this.mCachedClipPath;
    }

    /* access modifiers changed from: package-private */
    public Path getClipPath(Canvas canvas, Paint paint) {
        if (this.mClipPath != null) {
            am definedClipPath = getSvgShadowNode().getDefinedClipPath(this.mClipPath);
            if (definedClipPath != null) {
                Path path = definedClipPath.getPath(canvas, paint);
                switch (this.mClipRule) {
                    case 0:
                        path.setFillType(Path.FillType.EVEN_ODD);
                        break;
                    case 1:
                        break;
                    default:
                        com.facebook.common.e.a.c("ReactNative", "RNSVG: clipRule: " + this.mClipRule + " unrecognized");
                        break;
                }
                this.mCachedClipPath = path;
            } else {
                com.facebook.common.e.a.c("ReactNative", "RNSVG: Undefined clipPath: " + this.mClipPath);
            }
        }
        return getClipPath();
    }

    /* access modifiers changed from: package-private */
    public void clip(Canvas canvas, Paint paint) {
        Path clipPath = getClipPath(canvas, paint);
        if (clipPath != null) {
            canvas.clipPath(clipPath, Region.Op.REPLACE);
        }
    }

    public boolean isResponsible() {
        return this.mResponsible;
    }

    /* access modifiers changed from: package-private */
    public SvgViewShadowNode getSvgShadowNode() {
        SvgViewShadowNode svgViewShadowNode = this.mSvgShadowNode;
        if (svgViewShadowNode != null) {
            return svgViewShadowNode;
        }
        x parent = getParent();
        if (parent instanceof SvgViewShadowNode) {
            this.mSvgShadowNode = (SvgViewShadowNode) parent;
        } else if (parent instanceof am) {
            this.mSvgShadowNode = ((am) parent).getSvgShadowNode();
        } else {
            com.facebook.common.e.a.d("ReactNative", "RNSVG: " + getClass().getName() + " should be descendant of a SvgViewShadow.");
        }
        return this.mSvgShadowNode;
    }

    /* access modifiers changed from: package-private */
    public double relativeOnWidth(String str) {
        return w.a(str, (double) getCanvasWidth(), 0.0d, (double) this.mScale, getFontSizeFromContext());
    }

    /* access modifiers changed from: package-private */
    public double relativeOnHeight(String str) {
        return w.a(str, (double) getCanvasHeight(), 0.0d, (double) this.mScale, getFontSizeFromContext());
    }

    /* access modifiers changed from: package-private */
    public double relativeOnOther(String str) {
        return w.a(str, Math.sqrt(Math.pow((double) getCanvasWidth(), 2.0d) + Math.pow((double) getCanvasHeight(), 2.0d)) * M_SQRT1_2l, 0.0d, (double) this.mScale, getFontSizeFromContext());
    }

    private float getCanvasWidth() {
        float f = this.canvasWidth;
        if (f != -1.0f) {
            return f;
        }
        o textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasWidth = (float) getSvgShadowNode().getCanvasBounds().width();
        } else {
            this.canvasWidth = textRoot.a().h();
        }
        return this.canvasWidth;
    }

    private float getCanvasHeight() {
        float f = this.canvasHeight;
        if (f != -1.0f) {
            return f;
        }
        o textRoot = getTextRoot();
        if (textRoot == null) {
            this.canvasHeight = (float) getSvgShadowNode().getCanvasBounds().height();
        } else {
            this.canvasHeight = textRoot.a().i();
        }
        return this.canvasHeight;
    }

    /* access modifiers changed from: package-private */
    public void saveDefinition() {
        if (this.mName != null) {
            getSvgShadowNode().defineTemplate(this, this.mName);
        }
    }

    /* access modifiers changed from: package-private */
    public void traverseChildren(a aVar) {
        for (int i = 0; i < getChildCount(); i++) {
            aVar.a(getChildAt(i));
        }
    }

    /* access modifiers changed from: package-private */
    public void setClientRect(RectF rectF) {
        RectF rectF2 = this.mClientRect;
        if (rectF2 == null || !rectF2.equals(rectF)) {
            this.mClientRect = rectF;
            if (this.mClientRect != null) {
                ((UIManagerModule) getThemedContext().getNativeModule(UIManagerModule.class)).getEventDispatcher().a(n.a(getReactTag(), (int) this.mClientRect.left, (int) this.mClientRect.top, (int) this.mClientRect.width(), (int) this.mClientRect.height()));
            }
        }
    }

    public RectF getClientRect() {
        return this.mClientRect;
    }
}
