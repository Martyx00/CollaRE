package com.facebook.react.uimanager;

import android.view.View;

public abstract class SimpleViewManager<T extends View> extends BaseViewManager<T, h> {
    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(T t, Object obj) {
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new h();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<h> getShadowNodeClass() {
        return h.class;
    }
}
