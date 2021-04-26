package com.horcrux.svg;

import android.graphics.Bitmap;
import android.util.SparseArray;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;

class SvgViewManager extends ViewGroupManager<SvgView> {
    private static final YogaMeasureFunction MEASURE_FUNCTION = new YogaMeasureFunction() {
        /* class com.horcrux.svg.SvgViewManager.AnonymousClass1 */

        @Override // com.facebook.yoga.YogaMeasureFunction
        public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
            throw new IllegalStateException("SurfaceView should have explicit width and height set");
        }
    };
    private static final String REACT_CLASS = "RNSVGSvgView";
    private static final SparseArray<SvgViewShadowNode> mTagToShadowNode = new SparseArray<>();
    private static final SparseArray<SvgView> mTagToSvgView = new SparseArray<>();

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    SvgViewManager() {
    }

    static void setShadowNode(SvgViewShadowNode svgViewShadowNode) {
        mTagToShadowNode.put(svgViewShadowNode.getReactTag(), svgViewShadowNode);
    }

    static void setSvgView(SvgView svgView) {
        mTagToSvgView.put(svgView.getId(), svgView);
    }

    static void dropSvgView(SvgView svgView) {
        int id = svgView.getId();
        mTagToShadowNode.remove(id);
        mTagToSvgView.remove(id);
    }

    static SvgView getSvgViewByTag(int i) {
        return mTagToSvgView.get(i);
    }

    static SvgViewShadowNode getShadowNodeByTag(int i) {
        return mTagToShadowNode.get(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public Class<SvgViewShadowNode> getShadowNodeClass() {
        return SvgViewShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public SvgViewShadowNode createShadowNodeInstance() {
        SvgViewShadowNode svgViewShadowNode = new SvgViewShadowNode();
        svgViewShadowNode.setMeasureFunction(MEASURE_FUNCTION);
        return svgViewShadowNode;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public SvgView createViewInstance(af afVar) {
        return new SvgView(afVar);
    }

    public void updateExtraData(SvgView svgView, Object obj) {
        svgView.setBitmap((Bitmap) obj);
    }
}
