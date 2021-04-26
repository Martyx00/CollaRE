package com.facebook.react.views.scroll;

import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.af;

@a(a = ReactHorizontalScrollContainerViewManager.REACT_CLASS)
public class ReactHorizontalScrollContainerViewManager extends ViewGroupManager<c> {
    public static final String REACT_CLASS = "AndroidHorizontalScrollContentView";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public c createViewInstance(af afVar) {
        return new c(afVar);
    }
}
