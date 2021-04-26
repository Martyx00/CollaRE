package com.teslamotors.plugins.colorblending;

import com.facebook.f.a.a.c;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;

public class ColorBlendingImageViewManager extends SimpleViewManager<a> {
    public static final String REACT_CLASS = "TMColorBlendingImageView";
    private static final String TAG = b.class.getSimpleName();
    private final Object mCallerContext;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public ColorBlendingImageViewManager() {
        this.mCallerContext = null;
    }

    public ColorBlendingImageViewManager(Object obj) {
        this.mCallerContext = obj;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar, c.a(), this.mCallerContext);
    }

    @a(a = "blendingSpecification")
    public void setBlendingSpecification(a aVar, ReadableMap readableMap) {
        aVar.setBlendingSpecification(readableMap);
    }

    @a(a = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(a aVar, boolean z) {
        aVar.setShouldNotifyLoadEvents(z);
    }

    @a(a = "fadeDuration")
    public void setFadeDuration(a aVar, int i) {
        aVar.setFadeDuration(i);
    }

    @a(a = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(a aVar, String str) {
        aVar.setLoadingIndicatorSource(str);
    }
}
