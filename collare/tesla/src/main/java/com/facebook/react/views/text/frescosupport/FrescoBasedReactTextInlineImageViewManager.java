package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.f.a.a.c;
import com.facebook.f.c.b;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.af;

@a(a = FrescoBasedReactTextInlineImageViewManager.REACT_CLASS)
public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, a> {
    public static final String REACT_CLASS = "RCTTextInlineImage";
    private final Object mCallerContext;
    private final b mDraweeControllerBuilder;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
    }

    public FrescoBasedReactTextInlineImageViewManager() {
        this(null, null);
    }

    public FrescoBasedReactTextInlineImageViewManager(b bVar, Object obj) {
        this.mDraweeControllerBuilder = bVar;
        this.mCallerContext = obj;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(af afVar) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public a createShadowNodeInstance() {
        b bVar = this.mDraweeControllerBuilder;
        if (bVar == null) {
            bVar = c.a();
        }
        return new a(bVar, this.mCallerContext);
    }

    /* Return type fixed from 'java.lang.Class<com.facebook.react.views.text.frescosupport.a>' to match base method */
    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends a> getShadowNodeClass() {
        return a.class;
    }
}
