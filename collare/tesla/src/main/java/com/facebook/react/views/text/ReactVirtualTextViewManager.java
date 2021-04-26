package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.af;

@a(a = ReactVirtualTextViewManager.REACT_CLASS)
public class ReactVirtualTextViewManager extends BaseViewManager<View, t> {
    public static final String REACT_CLASS = "RCTVirtualText";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(af afVar) {
        throw new IllegalStateException("Attempt to create a native view for RCTVirtualText");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<t> getShadowNodeClass() {
        return t.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public t createShadowNodeInstance() {
        return new t();
    }
}
