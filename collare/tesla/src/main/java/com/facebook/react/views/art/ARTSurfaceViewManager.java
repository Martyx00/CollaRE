package com.facebook.react.views.art;

import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.af;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

@a(a = ARTSurfaceViewManager.REACT_CLASS)
public class ARTSurfaceViewManager extends BaseViewManager<c, d> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        /* class com.facebook.react.views.art.ARTSurfaceViewManager.AnonymousClass1 */

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    public static final String REACT_CLASS = "ARTSurfaceView";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public void setBackgroundColor(c cVar, int i) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public d createShadowNodeInstance() {
        d dVar = new d();
        dVar.setMeasureFunction(MEASURE_FUNCTION);
        return dVar;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<d> getShadowNodeClass() {
        return d.class;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public c createViewInstance(af afVar) {
        return new c(afVar);
    }

    public void updateExtraData(c cVar, Object obj) {
        cVar.setSurfaceTextureListener((d) obj);
    }
}
