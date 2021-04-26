package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ReactTextInputKeyPressEvent */
public class j extends c<h> {

    /* renamed from: a  reason: collision with root package name */
    private String f3530a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topKeyPress";
    }

    @Override // com.facebook.react.uimanager.events.c
    public boolean b() {
        return false;
    }

    j(int i, String str) {
        super(i);
        this.f3530a = str;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("key", this.f3530a);
        return createMap;
    }
}
