package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ReactTextInputFocusEvent */
class i extends c<i> {
    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topFocus";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    public i(int i) {
        super(i);
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("target", d());
        return createMap;
    }
}
