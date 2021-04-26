package com.facebook.react.views.text;

import android.view.View;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.af;

@a(a = ReactRawTextManager.REACT_CLASS)
public class ReactRawTextManager extends ViewManager<View, k> {
    public static final String REACT_CLASS = "RCTRawText";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
    }

    /* Return type fixed from 'com.facebook.react.views.text.r' to match base method */
    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(af afVar) {
        throw new IllegalStateException("Attempt to create a native view for RCTRawText");
    }

    /* Return type fixed from 'java.lang.Class<com.facebook.react.views.text.k>' to match base method */
    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends k> getShadowNodeClass() {
        return k.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public k createShadowNodeInstance() {
        return new k();
    }
}
