package com.facebook.react.views.image;

import android.graphics.PorterDuff;
import android.view.View;
import com.facebook.f.a.a.c;
import com.facebook.f.c.b;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.o;
import java.util.Map;

@a(a = ReactImageManager.REACT_CLASS)
public class ReactImageManager extends SimpleViewManager<f> {
    public static final String REACT_CLASS = "RCTImageView";
    private final Object mCallerContext;
    private b mDraweeControllerBuilder;
    private a mGlobalImageLoadListener;

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    public ReactImageManager(b bVar, Object obj) {
        this(bVar, null, obj);
    }

    public ReactImageManager(b bVar, a aVar, Object obj) {
        this.mDraweeControllerBuilder = bVar;
        this.mGlobalImageLoadListener = aVar;
        this.mCallerContext = obj;
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }

    public b getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = c.a();
        }
        return this.mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public f createViewInstance(af afVar) {
        return new f(afVar, getDraweeControllerBuilder(), this.mGlobalImageLoadListener, getCallerContext());
    }

    @com.facebook.react.uimanager.a.a(a = "src")
    public void setSource(f fVar, ReadableArray readableArray) {
        fVar.setSource(readableArray);
    }

    @com.facebook.react.uimanager.a.a(a = "blurRadius")
    public void setBlurRadius(f fVar, float f) {
        fVar.setBlurRadius(f);
    }

    @com.facebook.react.uimanager.a.a(a = "defaultSrc")
    public void setDefaultSource(f fVar, String str) {
        fVar.setDefaultSource(str);
    }

    @com.facebook.react.uimanager.a.a(a = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(f fVar, String str) {
        fVar.setLoadingIndicatorSource(str);
    }

    @com.facebook.react.uimanager.a.a(a = "borderColor", b = "Color")
    public void setBorderColor(f fVar, Integer num) {
        if (num == null) {
            fVar.setBorderColor(0);
        } else {
            fVar.setBorderColor(num.intValue());
        }
    }

    @com.facebook.react.uimanager.a.a(a = "overlayColor", b = "Color")
    public void setOverlayColor(f fVar, Integer num) {
        if (num == null) {
            fVar.setOverlayColor(0);
        } else {
            fVar.setOverlayColor(num.intValue());
        }
    }

    @com.facebook.react.uimanager.a.a(a = "borderWidth")
    public void setBorderWidth(f fVar, float f) {
        fVar.setBorderWidth(f);
    }

    @com.facebook.react.uimanager.a.b(a = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"}, c = Float.NaN)
    public void setBorderRadius(f fVar, int i, float f) {
        if (!com.facebook.yoga.a.a(f)) {
            f = o.a(f);
        }
        if (i == 0) {
            fVar.setBorderRadius(f);
        } else {
            fVar.a(f, i - 1);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "resizeMode")
    public void setResizeMode(f fVar, String str) {
        fVar.setScaleType(d.a(str));
        fVar.setTileMode(d.b(str));
    }

    @com.facebook.react.uimanager.a.a(a = "resizeMethod")
    public void setResizeMethod(f fVar, String str) {
        if (str == null || "auto".equals(str)) {
            fVar.setResizeMethod(c.AUTO);
        } else if ("resize".equals(str)) {
            fVar.setResizeMethod(c.RESIZE);
        } else if ("scale".equals(str)) {
            fVar.setResizeMethod(c.SCALE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid resize method: '" + str + "'");
        }
    }

    @com.facebook.react.uimanager.a.a(a = "tintColor", b = "Color")
    public void setTintColor(f fVar, Integer num) {
        if (num == null) {
            fVar.clearColorFilter();
        } else {
            fVar.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @com.facebook.react.uimanager.a.a(a = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(f fVar, boolean z) {
        fVar.setProgressiveRenderingEnabled(z);
    }

    @com.facebook.react.uimanager.a.a(a = "fadeDuration")
    public void setFadeDuration(f fVar, int i) {
        fVar.setFadeDuration(i);
    }

    @com.facebook.react.uimanager.a.a(a = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(f fVar, boolean z) {
        fVar.setShouldNotifyLoadEvents(z);
    }

    @com.facebook.react.uimanager.a.a(a = "headers")
    public void setHeaders(f fVar, ReadableMap readableMap) {
        fVar.setHeaders(readableMap);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a(b.b(4), e.a("registrationName", "onLoadStart"), b.b(2), e.a("registrationName", "onLoad"), b.b(1), e.a("registrationName", "onError"), b.b(3), e.a("registrationName", "onLoadEnd"));
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(f fVar) {
        super.onAfterUpdateTransaction((View) fVar);
        fVar.e();
    }
}
