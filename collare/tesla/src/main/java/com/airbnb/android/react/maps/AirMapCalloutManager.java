package com.airbnb.android.react.maps;

import com.facebook.react.common.e;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;
import com.facebook.react.uimanager.h;
import java.util.Map;

public class AirMapCalloutManager extends ViewGroupManager<a> {
    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return "AIRMapCallout";
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public a createViewInstance(af afVar) {
        return new a(afVar);
    }

    @a(a = "tooltip", f = false)
    public void setTooltip(a aVar, boolean z) {
        aVar.setTooltip(z);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return e.a("onPress", e.a("registrationName", "onPress"));
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.ViewManager
    public h createShadowNodeInstance() {
        return new r();
    }

    public void updateExtraData(a aVar, Object obj) {
        Map map = (Map) obj;
        float floatValue = ((Float) map.get("width")).floatValue();
        float floatValue2 = ((Float) map.get("height")).floatValue();
        aVar.f1547a = (int) floatValue;
        aVar.f1548b = (int) floatValue2;
    }
}
