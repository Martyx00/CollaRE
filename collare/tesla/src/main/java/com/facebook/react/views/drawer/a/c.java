package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* compiled from: DrawerSlideEvent */
public class c extends com.facebook.react.uimanager.events.c<c> {

    /* renamed from: a  reason: collision with root package name */
    private final float f3321a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topDrawerSlide";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public c(int i, float f) {
        super(i);
        this.f3321a = f;
    }

    public float j() {
        return this.f3321a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), k());
    }

    private WritableMap k() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("offset", (double) j());
        return createMap;
    }
}
