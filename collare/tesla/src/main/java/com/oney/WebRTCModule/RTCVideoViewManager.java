package com.oney.WebRTCModule;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.a.a;
import com.facebook.react.uimanager.af;

public class RTCVideoViewManager extends SimpleViewManager<k> {
    private static final String REACT_CLASS = "RTCVideoView";

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.uimanager.ViewManager
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public k createViewInstance(af afVar) {
        return new k(afVar);
    }

    @a(a = "mirror")
    public void setMirror(k kVar, boolean z) {
        kVar.setMirror(z);
    }

    @a(a = "objectFit")
    public void setObjectFit(k kVar, String str) {
        kVar.setObjectFit(str);
    }

    @a(a = "streamURL")
    public void setStreamURL(k kVar, String str) {
        kVar.setStreamURL(str);
    }

    @a(a = "zOrder")
    public void setZOrder(k kVar, int i) {
        kVar.setZOrder(i);
    }
}
