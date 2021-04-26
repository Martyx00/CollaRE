package com.facebook.react.views.drawer.a;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.c;

/* compiled from: DrawerStateChangedEvent */
public class d extends c<d> {

    /* renamed from: a  reason: collision with root package name */
    private final int f3322a;

    @Override // com.facebook.react.uimanager.events.c
    public String a() {
        return "topDrawerStateChanged";
    }

    @Override // com.facebook.react.uimanager.events.c
    public short f() {
        return 0;
    }

    public d(int i, int i2) {
        super(i);
        this.f3322a = i2;
    }

    public int j() {
        return this.f3322a;
    }

    @Override // com.facebook.react.uimanager.events.c
    public void a(RCTEventEmitter rCTEventEmitter) {
        rCTEventEmitter.receiveEvent(d(), a(), k());
    }

    private WritableMap k() {
        WritableMap createMap = Arguments.createMap();
        createMap.putDouble("drawerState", (double) j());
        return createMap;
    }
}
