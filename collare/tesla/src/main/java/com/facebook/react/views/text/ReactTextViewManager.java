package com.facebook.react.views.text;

import android.text.Spannable;
import android.view.View;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.common.e;
import com.facebook.react.module.a.a;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.y;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;

@a(a = ReactTextViewManager.REACT_CLASS)
public class ReactTextViewManager extends ReactTextAnchorViewManager<r, p> {
    public static final String REACT_CLASS = "RCTText";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public r createViewInstance(af afVar) {
        return new r(afVar);
    }

    public void updateExtraData(r rVar, Object obj) {
        q qVar = (q) obj;
        if (qVar.c()) {
            x.a(qVar.a(), rVar);
        }
        rVar.setText(qVar);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public p createShadowNodeInstance() {
        return new p();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<p> getShadowNodeClass() {
        return p.class;
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(r rVar) {
        super.onAfterUpdateTransaction((View) rVar);
        rVar.a();
    }

    public Object updateLocalData(r rVar, y yVar, y yVar2) {
        Spannable a2 = y.a(rVar.getContext(), yVar2.e("attributedString"));
        rVar.setSpanned(a2);
        v vVar = new v(yVar);
        return new q(a2, -1, false, vVar.d(), vVar.f(), vVar.e(), vVar.c(), vVar.b(), 1, 0);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("topTextLayout", e.a("registrationName", "onTextLayout"));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(ReactContext reactContext, ReadableNativeMap readableNativeMap, ReadableNativeMap readableNativeMap2, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        return y.a(reactContext, readableNativeMap, readableNativeMap2, f, yogaMeasureMode, f2, yogaMeasureMode2);
    }
}
