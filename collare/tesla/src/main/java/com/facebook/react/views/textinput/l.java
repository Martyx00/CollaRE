package com.facebook.react.views.textinput;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: ReactTextInputSelectionEvent */
class l extends c<l> {

    /* renamed from: a  reason: collision with root package name */
    private int f3535a;

    /* renamed from: b  reason: collision with root package name */
    private int f3536b;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topSelectionChange";
    }

    public l(int i, int i2, int i3) {
        super(i);
        this.f3535a = i2;
        this.f3536b = i3;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        WritableMap createMap2 = Arguments.createMap();
        createMap2.putInt("end", this.f3536b);
        createMap2.putInt("start", this.f3535a);
        createMap.putMap("selection", createMap2);
        return createMap;
    }
}
