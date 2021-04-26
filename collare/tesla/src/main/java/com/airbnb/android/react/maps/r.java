package com.airbnb.android.react.maps;

import com.facebook.react.uimanager.ap;
import com.facebook.react.uimanager.h;
import java.util.HashMap;

/* compiled from: SizeReportingShadowNode */
public class r extends h {
    @Override // com.facebook.react.uimanager.x
    public void onCollectExtraUpdates(ap apVar) {
        super.onCollectExtraUpdates(apVar);
        HashMap hashMap = new HashMap();
        hashMap.put("width", Float.valueOf(getLayoutWidth()));
        hashMap.put("height", Float.valueOf(getLayoutHeight()));
        apVar.a(getReactTag(), hashMap);
    }
}
