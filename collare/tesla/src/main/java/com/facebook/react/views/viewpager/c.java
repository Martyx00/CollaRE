package com.facebook.react.views.viewpager;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* compiled from: PageSelectedEvent */
class c extends com.facebook.react.uimanager.events.c<c> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3581a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topPageSelected";
    }

    protected c(int i, int i2) {
        super(i);
        this.f3581a = i2;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), j());
    }

    private WritableMap j() {
        WritableMap createMap = Arguments.createMap();
        createMap.putInt("position", this.f3581a);
        return createMap;
    }
}
