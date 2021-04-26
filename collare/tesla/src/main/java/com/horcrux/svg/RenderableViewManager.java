package com.horcrux.svg;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;

class RenderableViewManager extends ViewManager<View, h> {
    private static final String CLASS_CIRCLE = "RNSVGCircle";
    private static final String CLASS_CLIP_PATH = "RNSVGClipPath";
    private static final String CLASS_DEFS = "RNSVGDefs";
    private static final String CLASS_ELLIPSE = "RNSVGEllipse";
    private static final String CLASS_GROUP = "RNSVGGroup";
    private static final String CLASS_IMAGE = "RNSVGImage";
    private static final String CLASS_LINE = "RNSVGLine";
    private static final String CLASS_LINEAR_GRADIENT = "RNSVGLinearGradient";
    private static final String CLASS_PATH = "RNSVGPath";
    private static final String CLASS_RADIAL_GRADIENT = "RNSVGRadialGradient";
    private static final String CLASS_RECT = "RNSVGRect";
    private static final String CLASS_SYMBOL = "RNSVGSymbol";
    private static final String CLASS_TEXT = "RNSVGText";
    private static final String CLASS_TEXT_PATH = "RNSVGTextPath";
    private static final String CLASS_TSPAN = "RNSVGTSpan";
    private static final String CLASS_USE = "RNSVGUse";
    private final String mClassName;

    @Override // com.facebook.react.uimanager.ViewManager
    public void onDropViewInstance(View view) {
    }

    static RenderableViewManager createGroupViewManager() {
        return new RenderableViewManager(CLASS_GROUP);
    }

    static RenderableViewManager createPathViewManager() {
        return new RenderableViewManager(CLASS_PATH);
    }

    static RenderableViewManager createTextViewManager() {
        return new RenderableViewManager(CLASS_TEXT);
    }

    static RenderableViewManager createTSpanViewManager() {
        return new RenderableViewManager(CLASS_TSPAN);
    }

    static RenderableViewManager createTextPathViewManager() {
        return new RenderableViewManager(CLASS_TEXT_PATH);
    }

    static RenderableViewManager createImageViewManager() {
        return new RenderableViewManager(CLASS_IMAGE);
    }

    static RenderableViewManager createCircleViewManager() {
        return new RenderableViewManager(CLASS_CIRCLE);
    }

    static RenderableViewManager createEllipseViewManager() {
        return new RenderableViewManager(CLASS_ELLIPSE);
    }

    static RenderableViewManager createLineViewManager() {
        return new RenderableViewManager(CLASS_LINE);
    }

    static RenderableViewManager createRectViewManager() {
        return new RenderableViewManager(CLASS_RECT);
    }

    static RenderableViewManager createClipPathViewManager() {
        return new RenderableViewManager(CLASS_CLIP_PATH);
    }

    static RenderableViewManager createDefsViewManager() {
        return new RenderableViewManager(CLASS_DEFS);
    }

    static RenderableViewManager createUseViewManager() {
        return new RenderableViewManager(CLASS_USE);
    }

    static RenderableViewManager createSymbolManager() {
        return new RenderableViewManager(CLASS_SYMBOL);
    }

    static RenderableViewManager createLinearGradientManager() {
        return new RenderableViewManager(CLASS_LINEAR_GRADIENT);
    }

    static RenderableViewManager createRadialGradientManager() {
        return new RenderableViewManager(CLASS_RADIAL_GRADIENT);
    }

    private RenderableViewManager(String str) {
        this.mClassName = str;
    }

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return this.mClassName;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        char c2;
        String str = this.mClassName;
        switch (str.hashCode()) {
            case -1866779881:
                if (str.equals(CLASS_RADIAL_GRADIENT)) {
                    c2 = 15;
                    break;
                }
                c2 = 65535;
                break;
            case -1487762378:
                if (str.equals(CLASS_ELLIPSE)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -503960650:
                if (str.equals(CLASS_DEFS)) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case -503718244:
                if (str.equals(CLASS_LINE)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -503606579:
                if (str.equals(CLASS_PATH)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -503543668:
                if (str.equals(CLASS_RECT)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -503483435:
                if (str.equals(CLASS_TEXT)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case -154787361:
                if (str.equals(CLASS_USE)) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case -68462182:
                if (str.equals(CLASS_TEXT_PATH)) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 748220957:
                if (str.equals(CLASS_LINEAR_GRADIENT)) {
                    c2 = 14;
                    break;
                }
                c2 = 65535;
                break;
            case 1000530296:
                if (str.equals(CLASS_CIRCLE)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1473223232:
                if (str.equals(CLASS_SYMBOL)) {
                    c2 = '\r';
                    break;
                }
                c2 = 65535;
                break;
            case 1560255703:
                if (str.equals(CLASS_GROUP)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1561939891:
                if (str.equals(CLASS_IMAGE)) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 1571338294:
                if (str.equals(CLASS_TSPAN)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1852960317:
                if (str.equals(CLASS_CLIP_PATH)) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return new o();
            case 1:
                return new v();
            case 2:
                return new c();
            case 3:
                return new h();
            case 4:
                return new q();
            case 5:
                return new y();
            case 6:
                return new aj();
            case 7:
                return new aa();
            case '\b':
                return new ag();
            case '\t':
                return new p();
            case '\n':
                return new d();
            case 11:
                return new f();
            case '\f':
                return new ak();
            case '\r':
                return new z();
            case 14:
                return new s();
            case 15:
                return new x();
            default:
                throw new IllegalStateException("Unexpected type " + this.mClassName);
        }
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends h> getShadowNodeClass() {
        char c2;
        String str = this.mClassName;
        switch (str.hashCode()) {
            case -1866779881:
                if (str.equals(CLASS_RADIAL_GRADIENT)) {
                    c2 = 15;
                    break;
                }
                c2 = 65535;
                break;
            case -1487762378:
                if (str.equals(CLASS_ELLIPSE)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -503960650:
                if (str.equals(CLASS_DEFS)) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case -503718244:
                if (str.equals(CLASS_LINE)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -503606579:
                if (str.equals(CLASS_PATH)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -503543668:
                if (str.equals(CLASS_RECT)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case -503483435:
                if (str.equals(CLASS_TEXT)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case -154787361:
                if (str.equals(CLASS_USE)) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case -68462182:
                if (str.equals(CLASS_TEXT_PATH)) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 748220957:
                if (str.equals(CLASS_LINEAR_GRADIENT)) {
                    c2 = 14;
                    break;
                }
                c2 = 65535;
                break;
            case 1000530296:
                if (str.equals(CLASS_CIRCLE)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1473223232:
                if (str.equals(CLASS_SYMBOL)) {
                    c2 = '\r';
                    break;
                }
                c2 = 65535;
                break;
            case 1560255703:
                if (str.equals(CLASS_GROUP)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1561939891:
                if (str.equals(CLASS_IMAGE)) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 1571338294:
                if (str.equals(CLASS_TSPAN)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 1852960317:
                if (str.equals(CLASS_CLIP_PATH)) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return o.class;
            case 1:
                return v.class;
            case 2:
                return c.class;
            case 3:
                return h.class;
            case 4:
                return q.class;
            case 5:
                return y.class;
            case 6:
                return aj.class;
            case 7:
                return aa.class;
            case '\b':
                return ag.class;
            case '\t':
                return p.class;
            case '\n':
                return d.class;
            case 11:
                return f.class;
            case '\f':
                return ak.class;
            case '\r':
                return z.class;
            case 14:
                return s.class;
            case 15:
                return x.class;
            default:
                throw new IllegalStateException("Unexpected type " + this.mClassName);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(af afVar) {
        throw new IllegalStateException("SVG elements does not map into a native view");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
        throw new IllegalStateException("SVG elements does not map into a native view");
    }
}
