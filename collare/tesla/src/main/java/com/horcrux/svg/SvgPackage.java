package com.horcrux.svg;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.o;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SvgPackage implements o {
    @Override // com.facebook.react.o
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(RenderableViewManager.createGroupViewManager(), RenderableViewManager.createPathViewManager(), RenderableViewManager.createCircleViewManager(), RenderableViewManager.createEllipseViewManager(), RenderableViewManager.createLineViewManager(), RenderableViewManager.createRectViewManager(), RenderableViewManager.createTextViewManager(), RenderableViewManager.createTSpanViewManager(), RenderableViewManager.createTextPathViewManager(), RenderableViewManager.createImageViewManager(), RenderableViewManager.createClipPathViewManager(), RenderableViewManager.createDefsViewManager(), RenderableViewManager.createUseViewManager(), RenderableViewManager.createSymbolManager(), RenderableViewManager.createLinearGradientManager(), RenderableViewManager.createRadialGradientManager(), new SvgViewManager());
    }

    @Override // com.facebook.react.o
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.singletonList(new SvgViewModule(reactApplicationContext));
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }
}
