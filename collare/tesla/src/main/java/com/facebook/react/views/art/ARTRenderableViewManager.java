package com.facebook.react.views.art;

import android.view.View;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.w;

public class ARTRenderableViewManager extends ViewManager<View, w> {
    public static final String CLASS_GROUP = "ARTGroup";
    public static final String CLASS_SHAPE = "ARTShape";
    public static final String CLASS_TEXT = "ARTText";
    private final String mClassName;

    public static ARTRenderableViewManager createARTGroupViewManager() {
        return new ARTGroupViewManager();
    }

    public static ARTRenderableViewManager createARTShapeViewManager() {
        return new ARTShapeViewManager();
    }

    public static ARTRenderableViewManager createARTTextViewManager() {
        return new ARTTextViewManager();
    }

    ARTRenderableViewManager(String str) {
        this.mClassName = str;
    }

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return this.mClassName;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public w createShadowNodeInstance() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return new a();
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return new b();
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return new e();
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends w> getShadowNodeClass() {
        if (CLASS_GROUP.equals(this.mClassName)) {
            return a.class;
        }
        if (CLASS_SHAPE.equals(this.mClassName)) {
            return b.class;
        }
        if (CLASS_TEXT.equals(this.mClassName)) {
            return e.class;
        }
        throw new IllegalStateException("Unexpected type " + this.mClassName);
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(af afVar) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
        throw new IllegalStateException("ARTShape does not map into a native view");
    }
}
